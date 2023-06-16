package finalProject;

public class Force {

	public double magnitude, startTime, endTime;
	
	public int direction;
	
	public Force() {
		magnitude = 0;
		startTime = 0;
		endTime = 0;
		direction = 0;
	}
	
	public String toString() {
		String dir = getDirection();
		return magnitude + " N " + dir + " from " + startTime + "s to " + endTime + "s";
	}
	
	private String getDirection() {
		if (direction == 1)
			return "Up";
		else if (direction == 2)
			return "Down";
		else if (direction == 3)
			return "Left";
		else
			return "Right";
	}
	
	public double getMagnitude()
	{
		return magnitude;
	}
	
	public double getStart()
	{
		return startTime;
	}
	
	public double getEnd()
	{
		return endTime;
	}
}
