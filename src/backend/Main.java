package backend;

import java.io.IOException;

import gui.GUI;

public class Main {
	
	public static void main(String[] args) throws IOException{
		
		Game newGame = new Game(4);
		GUI newGui = new GUI(newGame);
	}

}
