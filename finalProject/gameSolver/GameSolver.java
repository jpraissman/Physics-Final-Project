package gameSolver;

import java.awt.Point;
import java.util.ArrayList;

public class GameSolver {
	
	static int xPos = 0;
	static int yPos = 0;
	
	static int force = 20;
	
	static double curTime = 0;
	
	static ArrayList<Point> points = new ArrayList<Point>();

	public static void main(String[] args) {
		points.add(new Point(690, 0));
		points.add(new Point(690, 190));
		points.add(new Point(665, 190));
		points.add(new Point(665, 675));
		
		solve();
	}
	
	private static void solve() {
		for (Point point : points) {
			int xDiff = Math.abs(point.x - xPos);
			int yDiff = Math.abs(point.y - yPos);
			
			int diff = Math.max(xDiff, yDiff);
			
			double distance = diff/(double) 2;
			
			double time = Math.sqrt((distance * 2)/force);
			
			System.out.print(curTime + " | ");
			
			curTime += time;
			
			System.out.println(curTime);
			
			System.out.print(curTime + " | ");
			
			curTime += time;
			
			System.out.println(curTime);
			
			xPos = point.x;
			yPos = point.y;
			
		}
	}
	

}
