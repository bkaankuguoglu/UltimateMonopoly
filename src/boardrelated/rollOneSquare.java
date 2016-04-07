package boardrelated;
import backend.ConsoleGenerator;

import backend.Dice;
import backend.Game;
import backend.Player;

/**
 * This class is provided to meet the needs of the play type called "Roll Once Play". 
 * Its instances provide the procedures that are to be applied upon a game and a player.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */
public class rollOneSquare extends Square {
	/**
	 * Creates an instance of the rollOneSquare class. Its aim is to meet the specific behaviour of the process
	 * that needs to be followed upon the arrival of a player.
	 * 
	 * @param name the name of the square
	 * @param squareNumber the number of the square which is sort of tag
	 * @param trackNumber number of the track that this' instance is on
	 * @param colorID of this square's instance
	 * @param totalPlayers the number of total players on its instance
	 * 
	 */
	public rollOneSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
	}
	/**
	 * The aim of the arrival_proc is to apply the specific arrival procedure of the rollOneSquare. Once a player
	 * lands on it the game rolls dice and the player rolls dice as well. If they are match, then the player 
	 * recieves 100.
	 * 
	 * @requires a player to be on 
	 * @param p player who lands on the rollOneSquare
	 * @param g game the player and the rollOne square is in
	 * 
	 * @modifies the balance of the player depending on the dice
	 * @effects the game
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */

	@Override
	public void arrival_proc(Player p, Game g) {
		//Look here after Dice and Prompt procedure is implemented.
		int rollOnceCard=g.getTheDice().rollDice(6);
		ConsoleGenerator.write2Console("This is roll-once procedure! Your RollOnceCard shows the number: "+rollOnceCard+".");
		int yourDice=g.getTheDice().rollDice(6);
		ConsoleGenerator.write2Console("You rolled the dice and got "+yourDice+".");
		if (rollOnceCard==yourDice){
			p.receivePayment(100); 
			ConsoleGenerator.write2Console("You get the same number! and $100!");
		}
		else{
			ConsoleGenerator.write2Console("You need to get lucky!");
		}
	}

}
