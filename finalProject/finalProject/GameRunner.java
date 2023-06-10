package finalProject;

import processing.core.PApplet;
import processing.core.PImage;

public class GameRunner extends PApplet {

	PImage resistorImg;
	PImage batteryImg;
	
	Resistor resistor;
	Battery battery;
	
	boolean resistorMoving = false;
	boolean batteryMoving = false;
	
	public static void main(String[] args) {
		PApplet.main("finalProject.GameRunner");

	}
	
	public void settings()
    {
    	size(1000, 700);
    	
    }
    
    public void setup()
    {
    	resistorImg = loadImage("assets/Resistor.png");
    	resistorImg.resize(80, 15);
    	
    	batteryImg = loadImage("assets/Battery.png");
    	batteryImg.resize(20, 60);
    	
    	
    	resistor = new Resistor();
    	battery = new Battery();
    }
    
    public void draw() {
    	background(255);
    	
    	image(resistorImg, resistor.x, resistor.y);
    	image(batteryImg, battery.x, battery.y);
    	
    	
    	if (!resistorMoving && resistor.resistorCollision(mouseX, mouseY) != -1) {
    		fill(0);
    		circle(resistor.x, resistor.y + 7, 20);
    	}
    	
    	
    	if (resistorMoving) {
    		resistor.x = (int)((mouseX) - (resistor.width/2));
    		resistor.y = (int)((mouseY) - (resistor.height/2));
    	}
    	if (batteryMoving) {
    		battery.x = (int)((mouseX) - (battery.width/2));
    		battery.y = (int)((mouseY) - (battery.height/2));
    	}
    }
    
    public void mousePressed() {
    	boolean hitBattery = battery.insideBattery(mouseX, mouseY);
    	
    	
    	if (resistor.resistorCollision(mouseX, mouseY) == 0)
    		resistorMoving = true;
    	
    	if (hitBattery)
    		batteryMoving = true;
    	
    	if (resistor.resistorCollision(mouseX, mouseY) == 1) {
			System.out.println("Left Wire");
		}
    }
    
    public void mouseReleased() {
    	resistorMoving = false;
    	batteryMoving = false;
    }

}
