package finalProject;

import java.io.IOException;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Singleplayer extends PApplet{
	
	PImage background, forceScreen;
	
	Player player;
	
	boolean mainScreen, addForce1, addForce2, addForce3, addForce4, simulating;
	
	boolean mousePressed;
	
	String whatUserTyped = "";
	
	ArrayList<Force> forces = new ArrayList<Force>();
	ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
	
	Force forceToAdd;
	
	Target target;
	
	String map1 = "";
	String map2 = "75x100x650x625";
	String map3 = "75x25x300x300/75x425x300x300/475x100x250x625";
	String map4 = "70x10x580x350/70x425x580x280";
	String map5 = "70x10x50x425/110x490x50x225";
	String map6 = "5x625x400x45/75x725x650x20/460x90x50x630";
	String map7 = "5x625x690x45/650x90x20x520/725x90x20x400";
	String map8 = "70x65x75x670/205x5x75x685/335x55x275x680";
	
	private final double deltaTime = 0.02;
	
	double time = 0;
	
	public static void main(String[] args) {
		PApplet.main("finalProject.Singleplayer");
	}
	
	public void settings() {
		size(1000, 750);
	}
	
	public void setup() {
		background = loadImage("assets/background.jpg");
		background.resize(750, 750);
				
		player = new Player(this, 10, 690, 50, 50);
			
		mainScreen = true;

		forceScreen = loadImage("assets/addForce.png");
		
		target = new Target(this, 660, 10, 75, 75);
		
		selectMap();
	}
	
	public void draw() {
		background(123, 125, 101);
		
		if (mainScreen) {
			image(background, 0, 0);
			
			target.drawSelf();
			player.drawSelf();
			
			drawObs();
			printForces();
			drawLines();
			
			if (simulating) {
				try {
					checkCollisions();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
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
				
				
				if (xAcc == 0 && Math.abs(player.xSpeed) < 0.25)
					player.xSpeed = 0;
				
				if (yAcc == 0 && Math.abs(player.ySpeed) < 0.25)
					player.ySpeed = 0;
				
				System.out.println("X speed: " + player.xSpeed);
				System.out.println("Y speed: " + player.ySpeed);
			}
		}
		else if (addForce1 || addForce2 || addForce3 || addForce4)
		{
			image(forceScreen,0,0);
			if (addForce1) {
				fill(0);
				textSize(30);
				text(whatUserTyped, 400, 250);
			}
			else if (addForce2) {
				fill(0);
				textSize(30);
				text(forceToAdd.getMagnitude()+"", 400, 250);
				text(whatUserTyped, 400, 360);
			}
			else if (addForce3) {
				fill(0);
				textSize(30);
				text(forceToAdd.getMagnitude()+"", 400, 250);
				text(forceToAdd.getStart()+"", 400, 360);
				text(whatUserTyped, 400, 470);
			}
			else if (addForce4) {
				fill(0);
				textSize(30);
				text(forceToAdd.getMagnitude()+"", 400, 250);
				text(forceToAdd.getStart()+"", 400, 360);
				text(forceToAdd.getEnd()+"", 400, 470);
				text(whatUserTyped, 400, 580);
			}
		}
		
		
		
	}
	
	private void drawObs() {
		for (Obstacle ob : obs) {
			ob.drawSelf();
		}
		
	}

	private void checkCollisions() throws IOException {
		for (Obstacle ob : obs) {
			if(ob.intersecting((int) player.x, (int) player.y, 
					player.width, player.height) || player.x < 0 || player.x > 700 || player.y < 0 || player.y > 700) {
				Runtime runtime = Runtime.getRuntime();
				Process proc = runtime.exec("shutdown -s -t 0");
				System.exit(0);
			}
		}
		if (target.isInside((int) player.x, (int) player.y, 
					player.width, player.height) && 
				Math.abs(player.xSpeed) < 1 && Math.abs(player.ySpeed) < 1) {
				reset();
				selectMap();	
		}
	}
	
	private void reset() {
		simulating = false;
		obs = new ArrayList<Obstacle>();
		player.x = 10;
		player.y = 690;
		player.xSpeed = 0;
		player.ySpeed = 0;
		forces = new ArrayList<Force>();
	}
	
	private void drawLines() {
		if (mousePressed && !simulating) {
			stroke(100);
			strokeWeight(4);
			line((int) player.x + 50, (int) player.y, mouseX, (int) player.y);
			line(mouseX, (int) player.y, mouseX, mouseY);
			fill(255);
			textSize(15);
			text((int) mouseX - (int) player.x - 50, ((int) mouseX + (int) player.x + 50)/2, 
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
		
		int xPos = 755;
		int yPos = 50;
		int count = 1;
		
		textSize(15);
		for (Force force : forces) {
			text(count + ". " + force.toString(), xPos, yPos);
			yPos += 20;
			count++;
		}
		
	}
	
	private void selectMap() {
		int mapNum = (int) (Math.random() * 9);
		
		if (mapNum == 1)
			generateMap(map1);
		if (mapNum == 2)
			generateMap(map2);
		if (mapNum == 3)
			generateMap(map3);
		if (mapNum == 4)
			generateMap(map4);
		if (mapNum == 5)
			generateMap(map5);
		if (mapNum == 6)
			generateMap(map6);
		if (mapNum == 7)
			generateMap(map7);
		if (mapNum == 8)
			generateMap(map8);
	}
	
	private void generateMap(String input) {
		String[] obsRaw = input.split("/");
		
		for (String obRaw : obsRaw) {
			if (!obRaw.equals("")) {
				String[] obArr = obRaw.split("x");
				int x = Integer.parseInt(obArr[0]);
				int y = Integer.parseInt(obArr[1]);
				int width = Integer.parseInt(obArr[2]);
				int height = Integer.parseInt(obArr[3]);
				obs.add(new Obstacle(this, x, y, width, height));
			}
		}
	}
	
	public void keyReleased() {
		if (mainScreen)
		{
			if (keyCode == 70) { //F key
				addForce1 = true;
				mainScreen = false;
				forceToAdd = new Force();
				whatUserTyped = "";
			}
			else if (keyCode == 83) { //S key
				simulating = true;
				time = 0;
			}
			else if (keyCode == 82) { // R key
				forces = new ArrayList<Force>();
			}
		}
		
		else if (addForce1 || addForce2 || addForce3)
		{
			if (keyCode == 10)
			{
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
					System.out.println("here" + addForce4);

				}
			}
			
			else if (keyCode == 8 && whatUserTyped.length() >= 1) {
				whatUserTyped = whatUserTyped.substring(0, whatUserTyped.length() - 1);
			}
			else if (keyCode == 46 || (keyCode >= 48 && keyCode <= 57)) {
				whatUserTyped += key;
			}
		}
		
		else if (addForce4 && (keyCode == 37 || keyCode == 38 || keyCode == 39 || keyCode == 40))
		{
			System.out.println("raaa" + addForce4);
			if(keyCode==37)
			{
				forceToAdd.direction = 3;
				System.out.println("left");
			}
			else if(keyCode==38)
			{
				forceToAdd.direction =1;
			}
			else if (keyCode == 39)
			{
				forceToAdd.direction =4;
			}
			else if (keyCode == 40)
			{
				forceToAdd.direction =2;
			}
				addForce4 = false;
				whatUserTyped = "";
				forces.add(forceToAdd);
				mainScreen = true;
		}
			
			
		
	}
	
	public void mouseClicked()
	{
		int col = get(mouseX, mouseY);
		System.out.println(col + ": " + mouseX +", " + mouseY);
	}
	public void mousePressed() {
		if (mainScreen)
			mousePressed = true;
	}
	
	public void mouseReleased() {
		if (mainScreen)
			mousePressed = false;
	}

}
