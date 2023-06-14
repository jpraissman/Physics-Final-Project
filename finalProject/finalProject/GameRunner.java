package finalProject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class GameRunner extends PApplet {
	
	PImage background;
	
	Player player;
	
	boolean mainScreen, addForce1, addForce2, addForce3, addForce4, simulating;
	
	boolean mousePressed;
	
	String whatUserTyped = "";
	
	ArrayList<Force> forces = new ArrayList<Force>();
	ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
	
	Force forceToAdd;
	
	Target target;
	
	private final double deltaTime = 0.02;
	
	double time = 0;
	
	public static void main(String[] args) {
		PApplet.main("finalProject.GameRunner");
	}
	
	public void settings() {
		size(1000, 750);
	}
	
	public void setup() {
		background = loadImage("assets/background.jpg");
		background.resize(750, 750);
		
		player = new Player(this, 0, 200, 50, 50);
		
		mainScreen = true;
		
		
		obs.add(new Obstacle(this, 200, 300, 200, 100));
		
		target = new Target(this, 500, 200, 100, 100);
		
	}
	
	public void draw() {
		background(123, 125, 101);
		if (mainScreen) {
			image(background, 0, 0);
			
			player.drawSelf();
			target.drawSelf();
			
			drawObs();
			printForces();
			drawLines();
			
			if (simulating) {
				checkCollisions();
				
				time += deltaTime;
				double xAcc = 0;
				double yAcc = 0;
				
				for (Force force : forces) {
					if (force.startTime <= time && force.endTime >= time) {
						if (force.direction == 1) { //Up
							yAcc -= force.magnitude;
						}
						else if (force.direction == 2) { //Down
							yAcc += force.magnitude;
						}
						else if (force.direction == 3) { //Left
							xAcc -= force.magnitude;
						}
						else if (force.direction == 4) { //Right
							xAcc += force.magnitude;
						}
					}
				}
				
				player.x += (player.xSpeed * deltaTime) + (0.5 * xAcc * deltaTime * deltaTime);
				player.xSpeed += xAcc * deltaTime;
				
				player.y += (player.ySpeed * deltaTime) + (0.5 * yAcc * deltaTime * deltaTime);
				player.ySpeed += yAcc * deltaTime;
				
				if (time >= 10) {
					simulating = false;
				}
			}
		}
		else if (addForce1) {
			fill(0);
			textSize(30);
			text("Enter Force Magnitude (0-10 N):      " + whatUserTyped, 300, 350);
			text("Click Space When Done.", 350, 500);
		}
		else if (addForce2) {
			fill(0);
			textSize(30);
			text("Enter Force Start Time (s):      " + whatUserTyped, 300, 350);
			text("Click Space When Done.", 350, 500);
		}
		else if (addForce3) {
			fill(0);
			textSize(30);
			text("Enter Force End Time (s):      " + whatUserTyped, 300, 350);
			text("Click Space When Done.", 350, 500);
		}
		else if (addForce4) {
			fill(0);
			textSize(30);
			text("Enter Force Direction (1 is up, 2 is down, 3 is left, 4 is right):   " + 
					whatUserTyped, 100, 350);
			text("Click Space When Done.", 350, 500);
		}
		
		
	}
	
	private void drawObs() {
		for (Obstacle ob : obs) {
			ob.drawSelf();
		}
		
	}

	private void checkCollisions() {
		for (Obstacle ob : obs) {
			if(ob.intersecting((int) player.x, (int) player.y, 
					player.width, player.height)) {
				System.out.println("Collision Detected");
			}
		}
		if (target.isInside((int) player.x, (int) player.y, 
					player.width, player.height)) {
				System.out.println("Winner");
			}
	}
	
	private void drawLines() {
		if (mousePressed && !simulating) {
			stroke(100);
			strokeWeight(4);
			line((int) player.x, (int) player.y, mouseX, (int) player.y);
			line(mouseX, (int) player.y, mouseX, mouseY);
			fill(255);
			textSize(15);
			text((int) mouseX - (int) player.x, ((int) mouseX + (int) player.x)/2, 
					(int) player.y - 10);
			text(Math.abs((int) mouseY - (int) player.y), mouseX + 10, 
					((int) mouseY + (int) player.y)/2);
			stroke(0);
			strokeWeight(1);
		}
	}
	
	private void printForces() {
		fill(0);
		textSize(20);
		text("Forces", 850, 20);
		
		int xPos = 775;
		int yPos = 50;
		int count = 1;
		
		textSize(15);
		for (Force force : forces) {
			text(count + ". " + force.toString(), xPos, yPos);
			yPos += 20;
			count++;
		}
		
	}
	
	public void keyReleased() {
		if (mainScreen) {
			if (keyCode == 70) { //F key
				addForce1 = true;
				mainScreen = false;
				forceToAdd = new Force();
			}
			else if (keyCode == 83) { //S key
				simulating = true;
				time = 0;
			}
			else if (keyCode == 82) { // R key
				forces = new ArrayList<Force>();
			}
		}
		else if (addForce1 || addForce2 || addForce3 || addForce4) {
			if (keyCode == 32) {
				if (addForce1) {
					addForce1 = false;
					addForce2 = true;
					forceToAdd.magnitude = Double.parseDouble(whatUserTyped);
					whatUserTyped = "";
				}
				else if (addForce2) {
					addForce2 = false;
					addForce3 = true;
					forceToAdd.startTime = Double.parseDouble(whatUserTyped);
					whatUserTyped = "";
				}
				else if (addForce3) {
					addForce3 = false;
					addForce4 = true;
					forceToAdd.endTime = Double.parseDouble(whatUserTyped);
					whatUserTyped = "";
				}
				else if (addForce4) {
					addForce4 = false;
					forceToAdd.direction = Integer.parseInt(whatUserTyped);
					whatUserTyped = "";
					forces.add(forceToAdd);
					mainScreen = true;
				}
			}
			else if (keyCode == 8 && whatUserTyped.length() >= 1) {
				whatUserTyped = whatUserTyped.substring(0, whatUserTyped.length() - 1);
			}
			else if (keyCode == 46 || (keyCode >= 48 && keyCode <= 57)) {
				whatUserTyped += key;
			}
		}
		
		
	}
	
	public void mousePressed() {
		mousePressed = true;
	}
	
	public void mouseReleased() {
		mousePressed = false;
	}

}
