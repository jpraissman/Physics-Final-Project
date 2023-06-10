package finalProject;

import java.awt.Point;
import java.awt.Rectangle;

public class Resistor {
	
	public int x, y, height, width;
	
	public Resistor() {
		x = 50;
		y = 50;
		height = 15;
		width = 80;
	}
	
	public int resistorCollision (int mX, int mY) {
		Rectangle resistorRec = new Rectangle(x, y, width, height);
		Rectangle leftWireRec = new Rectangle(x - 12, y - 12, 25, 25);
//		Rectangle rightWireRec = new Rectangle(x, y, width, height);
		
		if (leftWireRec.contains(new Point(mX, mY)))
			return 1;
		else if (resistorRec.contains(new Point(mX, mY)))
			return 0;
		
		return -1;
	}
}
