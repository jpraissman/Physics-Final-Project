package finalProject;

import processing.core.PApplet;
import processing.net.Client;
import processing.net.Server;

public class ServerRunner extends PApplet {
	
	Server myServer;
	
	int numPlayers = 0;

	public static void main(String[] args) {
		PApplet.main("finalProject.ServerRunner");
		
	}
	
	public void settings() {
		size(750, 750);
	}
	
	public void setup() {
		myServer = new Server(this, 8888);
	}
	
	public void draw() {
		background(255);
		fill(0);
		textSize(30);
		text("Number of Players: " + numPlayers, 100, 100);
	}
	
	public void serverEvent(Server someServer, Client someClient) {
		  numPlayers++;
	}
	
	public void disconnectEvent() {
		System.out.println("Disconnected");
	}

}
