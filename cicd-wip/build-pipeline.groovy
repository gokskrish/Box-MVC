#!groovy
node() {
    
    env.JAVA_HOME = "/opt/java/jdk1.8.0_162"

    stage('Download Fortune Sources') { 
        sh "rm -rf *"
        echo "1. Copy Sources"
        sh "cp -R /home/* ."
    }

    stage('Run Code Analysis') { 
        echo "2. Run Code Analysis"
        dir("boxes") {
            sh "mvn clean"
            sh "mvn checkstyle:checkstyle"
        }
        step([$class: 'hudson.plugins.checkstyle.CheckStylePublisher', pattern: '**/target/checkstyle-result.xml', healthy:'20', unHealthy:'100'])
    }
    
    stage('Build Jar') { 
        echo "3. Build Jar"
        dir("boxes") {
            sh "mvn install"
        }
    }
    
    stage('Build Docker') { 
        echo "4. Build Docker"
        dir("boxes") {
            sh "docker build -t gokskrish/boxes:latest ."
        }
    }
    
    stage('Upload to Docker Hub') { 
        echo "5. Upload to Docker Hub"
        dir("boxes") {
            sh "docker push gokskrish/boxes"
        }
    }

}
	