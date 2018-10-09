package com.intopalo.boxes;

import java.util.ArrayList;

public class BoxStatus {
    int size;
    
    ArrayList<Box> boxes = new ArrayList<Box>();

    public int getSize() {
        return size;
    }

    public void setSize() {
        this.size = boxes.size();
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public void addBox(Box box) {
        this.boxes.add(box);
    }

}
