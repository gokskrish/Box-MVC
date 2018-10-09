#!groovy
node() {
    
    stage('Create Cluster on GKE') { 
        echo "1. Create Cluster on GKE"
        sh 'gcloud beta container --project "rock-loop-418" clusters create "cluster-boxes" --zone "asia-south1-c" --username "admin" --cluster-version "1.8.8-gke.0" --machine-type "f1-micro" --image-type "COS" --disk-size "100" --scopes "https://www.googleapis.com/auth/compute","https://www.googleapis.com/auth/devstorage.read_only","https://www.googleapis.com/auth/logging.write","https://www.googleapis.com/auth/monitoring","https://www.googleapis.com/auth/servicecontrol","https://www.googleapis.com/auth/service.management.readonly","https://www.googleapis.com/auth/trace.append" --num-nodes "3" --network "default" --enable-cloud-logging --enable-cloud-monitoring --subnetwork "default"'
        echo " Get credentials"
        sh 'gcloud container clusters get-credentials cluster-boxes --zone asia-south1-c --project rock-loop-418'
    }

    stage('Deploy on Kubernetes') { 
        echo "2. Deploy on Kubernetes"
         dir("cicd-boxes") {
            sh "kubectl create -f boxes.yaml"
        }
        sh "kubectl get services"
        sh "kubectl get rc"
        sh "kubectl get pods"
    }
    
}