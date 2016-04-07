package gui;

//This code was inspired by and uses some 
//parts of the project of CS132 course of University of Waterloo in 2005

import backend.Game;

/*import monopoly.model.Monopoly;
*/
public class GUIThread extends Object implements Runnable{
	   
	
	   private Game model;

	   /* package */ GUIThread(Game aGame){
		  super();
	      this.model = aGame;
	   }

	   public void run(){
		  this.model.playGame();
	   }
	   
	}