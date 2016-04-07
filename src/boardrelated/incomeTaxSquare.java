package boardrelated;
import backend.ConsoleGenerator;

import backend.DialogGenerator;
import backend.Game;
import backend.Player;
import backend.Game;
import backend.Player;

/**
 * This class is provided to meet the needs of the income tax square type. This abstraction allows
 * us to apply those needs.
 * Its instances provide the procedures that are to be applied upon a game and players.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */
public class incomeTaxSquare extends Square {
	/**
	 * luxuryTaxSquare is the constructor that creates an instance of luxury tax square.
	 * 
	 * @param name the name of the square
	 * @param squareNumber the number of the square which is sort of tag
	 * @param trackNumber number of the track that this' instance is on
	 * @param colorID of this square's instance
	 * @param totalPlayers the number of total players on its instance
	 * 
	 */
	public incomeTaxSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
	}
	/**
	 * This method of the incomeTaxSquare asks if the input player wants to pay 10% of his/her balance
	 * or pay 200$ to the pool. According to the choice transfers the money from the player to the pool.
	 * 
	 * @param p player who landed on an instance of incomeTaxSquare
	 * @param g game in which the arriving player and the instance of incomeTaxSquare exist
	 * 
	 * @requires the player to have some balance
	 * 
	 * @modifies increases the amount of money that pool has
	 * @modifies decreases the amount of money that player has
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */

	@Override
	public void arrival_proc(Player p, Game g) {
		if(DialogGenerator.AskYesNoDialog("Do you want to Pay %10-->Click Yes\n Do you want pay 200-->Click No","Choose One")){
			double total = (double) p.getBalance()/10;
			p.pay2Pool((int)total, g);
		}else{
			p.pay2Pool(200,g);
		}
	}

}
