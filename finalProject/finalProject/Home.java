package finalProject;
import processing.core.PApplet;
import processing.core.PImage;
//import processing.net.Client;

public class Home extends PApplet
{
	PImage background;
	
	public static void main(String[] args)
	{
		PApplet.main("finalProject.Home");
	}
	
	public void settings() {
		size(1000, 750);
	}
	
	public void setup()
	{
		background = loadImage("assets/Home.png");
		image(background, 0, 0);
	}
	
	public void draw()
	{
		
	}
	
	public void mouseClicked()
	{
		int col = get(mouseX, mouseY);
		System.out.println(col + ": " + mouseX +", " + mouseY);
		if((col == -1 || col == -16711936))
		{
			if(mouseX >= 330 && mouseX <= 660 && mouseY >= 260 && mouseY <= 485)
			{
				if(mouseY < 375)
				{
					System.out.println("singlePlayer");
					
				}
				else
				{
					PApplet.main("finalProject.GameRunner");
				}
			}
			else if (mouseY >= 490 && mouseY <= 600 && mouseX >= 155 && mouseX <= 840)
			{
				if(mouseX <= 500)
				{
					System.out.println("tutorial");
				}
				else
				{
					System.out.println("song");
					link("http://www.processing.org");
				}		
			}
		}

	}
}
