package finalProject;
import processing.core.PApplet;
import processing.core.PImage;
//import processing.net.Client;

public class Home extends PApplet
{
	PImage background, helpScreen;
	
	boolean help;
	
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
		helpScreen = loadImage("assets/helpp.png");
		help = false;
	}
	
	public void draw()
	{
		if(help)
		{
			image(helpScreen, 0, 0);
		}
		else
		{
			image(background, 0, 0);
		}
	}
	
	public void mouseClicked()
	{
		if((mouseX > 285 && mouseX < 365 && mouseY > 520 && mouseY < 566) && !help)
			help = true;
		if(help)
		{
			help = false;
		}
		int col = get(mouseX, mouseY);
		System.out.println(col + ": " + mouseX +", " + mouseY);
		if((col == -1 || col == -16711936 || col == 2097185))
		{
			if(mouseX >= 330 && mouseX <= 660 && mouseY >= 260 && mouseY <= 485)
			{
				if(mouseY < 375)
				{
					PApplet.main("finalProject.Singleplayer");
					
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
					help = true;
				}
				else
				{
					System.out.println("song");
					link("https://youtu.be/Kwe8aRVx_iA");
				}		
			}
		}

	}
}
