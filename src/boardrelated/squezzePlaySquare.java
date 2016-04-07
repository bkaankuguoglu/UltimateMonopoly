package boardrelated;
import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;

/**
 * This class is provided to meet the needs of the play type called "Squeeze Play". 
 * Its instances provide the procedures that are to be applied upon a game and a player.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */

public class squezzePlaySquare extends Square {
	/**
	 * Creates an instance of the squeezePlaySquare class. Its aim is to meet the specific behaviour of the process
	 * that needs to be followed upon the arriving player.
	 * 
	 * @param name the name of the square
	 * @param squareNumber the number of the square which is sort of tag
	 * @param trackNumber number of the track that this' instance is on
	 * @param colorID of this square's instance
	 * @param totalPlayers the number of total players on its instance
	 * 
	 */
	public squezzePlaySquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
	}
	/**
	 * 
	 * The aim of the arrival_proc is to apply the legal arrival procedure of the class. That is to calculate the
	 * total of the dice numbers and make a payment in accordance to the total. If the total is 5,6,7,8,9 it pays 50 
	 * to player. If the total is 3,4,10,11, it pays 100. If the total is 2 or 12 then it pays 200 to the player.
	 * 
	 * @param p arriving player on squeezePlaySquare's instance
	 * @param g the game that this taxRefundSquare is in
	 * 
	 * @requires a player to be on 
	 * @requires the player to roll the dice again
	 * @modifies increases the balance of the arriving player according to the new dice amount
	 * @effects the game
	 * 
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */

	@Override
	public void arrival_proc(Player p, Game g) {
		//TODO roll the dices with the button, however there is a need for indication to choose the method squeezeplay_proc_followup(need to be implemented)
		//TODO implement dice and prompt screen and comeback here
		int dice1 = g.getTheDice().rollDice(6);//May need to keep current dice in Player or Game
		ConsoleGenerator.write2Console("First dice is "+dice1);
		int dice2 = g.getTheDice().rollDice(6);
		ConsoleGenerator.write2Console("Second dice is "+dice2);
		int sum_dices=dice1+dice2;
		switch (sum_dices){
		case 5: case 6: case 7: case 8: case 9:
			p.receivePayment(50); break;
		case 3: case 4: case 10: case 11:
			p.receivePayment(100); break;
		case 2: case 12:
			p.receivePayment(200); break;
		}
	}

}
