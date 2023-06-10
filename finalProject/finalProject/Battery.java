package finalProject;

import java.awt.Point;
import java.awt.Rectangle;

public class Battery {
	public int x, y, height, width;
	
	public Battery() {
		x = 500;
		y = 500;
		height = 60;
		width = 20;
	}
	
	public boolean insideBattery(int mX, int mY) {
		Rectangle thisRec = new Rectangle(x, y, width, height);
		return thisRec.contains(new Point(mX, mY));
	}
}
