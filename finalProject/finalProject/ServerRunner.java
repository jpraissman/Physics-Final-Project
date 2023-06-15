package finalProject;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import processing.core.PApplet;
import processing.net.Client;
import processing.net.Server;

public class ServerRunner extends PApplet {
	
	Server myServer;
	
	int numPlayers = 0;
	
	boolean gameStarted = false;
	
	String ipAddress;

	public static void main(String[] args) {
		PApplet.main("finalProject.ServerRunner");
		
	}
	
	public void settings() {
		size(300, 300);
	}
	
	public void setup() {
		myServer = new Server(this, 8888);
		
		try {
			ipAddress = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			ipAddress = "Could Not Determine";
		}
	}
	
	public void draw() {
		background(255);
		fill(0);
		textSize(20);
		text("Number of Players: " + numPlayers, 65, 75);
		text("Your IP Address: " + ipAddress, 35, 150);
		text("Press Space To Start.", 65, 225);
	}
	
	public void serverEvent(Server someServer, Client someClient) {
		  numPlayers++;
	}
	
	public void keyReleased() {
		if (keyCode == 32 && !gameStarted) {
			gameStarted = true;
			System.out.println("Starting Game");
		}
	}

}
