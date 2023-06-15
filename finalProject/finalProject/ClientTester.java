package finalProject;

import processing.core.PApplet;
import processing.net.Client;

public class ClientTester extends PApplet {
	
	private Client myClient;

	public static void main(String[] args) {
		PApplet.main("finalProject.ClientTester");
	}
	
	public void settings() {
		size(750, 750);
	}
	
	public void setup() {
		myClient = new Client(this, "127.0.0.1", 8888);
	}
	
	public void draw() {
		
	}

}
