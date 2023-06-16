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
	
	int[] firstMapOptions = new int[]{1, 2, 3, 4};
	int[] secondMapOptions = new int[]{5, 6};
	int[] thirdMapOptions = new int[]{7, 8};
	
	Client thisClient;

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
		if (!gameStarted) {
			fill(0);
			textSize(20);
			text("Number of Players: " + numPlayers, 65, 75);
			text("Your IP Address: " + ipAddress, 35, 150);
			text("Press Space To Start.", 65, 225);
		}
		else if (gameStarted) {
			text("Game In Progress", 75, 150);
			thisClient = myServer.available();
			readClientData();
		}
		
	}
	
	public void serverEvent(Server someServer, Client someClient) {
		  numPlayers++;
	}
	
	private void readClientData() {
		String dataFromClient = thisClient.readString();
		if (dataFromClient != null) {
			String[] dataFromClientSeperated = dataFromClient.split("\n");
//			bDirection = dataFromClientSeperated[0];
		}
	}
	
	public void keyReleased() {
		if (keyCode == 32 && !gameStarted) {
			gameStarted = true;
			int randNum = (int) (Math.random() * 4);
			int map1 = firstMapOptions[randNum];
			randNum = (int) (Math.random() * 2);
			int map2 = secondMapOptions[randNum];
			randNum = (int) (Math.random() * 2);
			int map3 = thirdMapOptions[randNum];
			for (int i = 0; i < 10; i++) {
				myServer.write(map1 + "@" + map2 + "@" + map3 +"\n");
			}
		}
	}

}
