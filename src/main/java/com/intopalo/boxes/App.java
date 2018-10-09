package com.intopalo.boxes;

import java.util.ArrayList;
import java.util.HashMap;

import io.javalin.Javalin;	
/**
 * Hello world!
 *
 */
public class App 
{
    static Box box;
    static int id = 0;
    static HashMap<String, String> boxes = new HashMap<>();
    static ArrayList<String> boxIds = new ArrayList<String>();
    
    public static void initData() {
	id = id +1;
	boxIds.add(id+"");
	boxes.put(id+"", "green");
	
	id = id +1;
	boxIds.add(id+"");
	boxes.put(id+"", "grey");
	
	id = id +1;
	boxIds.add(id+"");
	boxes.put(id+"", "voilet");
	
	id = id +1;
	boxIds.add(id+"");
	boxes.put(id+"", "yellow");
	
	id = id +1;
	boxIds.add(id+"");
	boxes.put(id+"", "blue");
    }
    
    public static void main( String[] args )
    {
	//Sample Data
	//initData();
	
	Javalin app = Javalin.create().enableCorsForOrigin("*").start(7000);
	app.get("/", ctx -> {
	 	ctx.result("HELLO... WELCOME TO BOXES GAME");
	 	System.out.println("HOME page hit");
	});
	
	app.get("/get", ctx -> {
	 	ctx.json(getBoxStatus());
	});
	
	app.get("/add", ctx -> {
	 	ctx.result(addBox());
	});
	
	app.get("/update", ctx -> {
	 	ctx.json(updateBox(ctx.queryParam("id"),ctx.queryParam("color")));
	});
	
	app.get("/delete", ctx -> {
	 	ctx.result(deleteBox(ctx.queryParam("id")));
	});
    }
    
    public static BoxStatus getBoxStatus() {
	BoxStatus boxstatus = new BoxStatus();
	for(String id:boxIds) {
		box = new Box();
        	box.setBoxId(id);
        	box.setColor(boxes.get(id));
        	boxstatus.addBox(box);
        }
        boxstatus.setSize();
	return boxstatus;
    }
    
    public static BoxStatus updateBox(String id, String color) {
	if(id.toLowerCase().contains("out")) {
	    id = id.split("out")[1];
	}
	if(boxIds.contains(id)) {
	    boxes.put(id,color);
	}
	return getBoxStatus();
    }
    
    public static String addBox() {
	id=id + 1;
	boxIds.add(id+"");
	boxes.put(id+"","");
	return id+"";
    }
    
    public static String deleteBox(String id) {
	if(boxIds.size()==0) {
	    return "";
	}
	if(id==null || id.trim().length()==0) {
	    id = boxIds.get(boxIds.size()-1);
	}
	if(id.toLowerCase().contains("out")) {
	    id = id.split("out")[1];
	}
	
	if(boxIds.contains(id)) {
	    boxIds.remove(id);
	    boxes.remove(id);
	}
	
	return id+"";
    }
    
}
