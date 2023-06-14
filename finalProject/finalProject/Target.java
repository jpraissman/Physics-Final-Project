package finalProject;

import java.awt.Rectangle;

import processing.core.PApplet;

public class Target {

	public int x, y, width, height;
	
	PApplet window;
	
	public Target(PApplet window, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.window = window;
	}
	
	public void drawSelf() {
		window.fill(133, 34, 186);
		window.rect(x, y, width, height);
	}
	
	public boolean isInside(int x, int y, int width, int height) {
		Rectangle thisRec = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle otherRec = new Rectangle(x, y, width, height);
		
		return thisRec.contains(otherRec);
	}
}
