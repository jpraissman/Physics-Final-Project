package finalProject;

import processing.core.PApplet;

public class Player {
	
	PApplet window;
	
	public int width, height;
	
	public double x, y, xSpeed, ySpeed;
	
	public Player(PApplet window, double x, double y, int width, int height) {
		this.window = window;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		xSpeed = 0;
		ySpeed = 0;
	}
	
	public void drawSelf() {
		window.fill(255);
		window.rect((int) x, (int) y, width, height);
	}
}
