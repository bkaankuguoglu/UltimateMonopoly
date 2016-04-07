package boardrelated;
import backend.ConsoleGenerator;

import backend.Game;
import backend.Player;

/**
 * This class is provided to meet the needs of the luxury tax square type. This abstraction allows
 * us to apply those needs.
 * Its instances provide the procedures that are to be applied upon a game and players.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */
public class luxuryTaxSquare extends Square {
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
	public luxuryTaxSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
	}
	
	/**
	 * This method takes in player and game as arguments and makes 75$ payment from the pool of the input game to
	 * the arriving player who is basically the input for this procedure.
	 *  
	 * @param p player who has just arrived on luxuryTaxSquare instance
	 * @param g game in which the square and the player are in
	 * 
	 * @requires the pool to have more than 75$
	 * @modifies decreases the balance of the pool by 75$
	 * @modifies increases the balance of the player by 75$
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */

	@Override
	public void arrival_proc(Player p, Game g) {
		// TODO Auto-generated method stub
		p.givePayment(75,g);
		g.getTheTable().getPool().recievePayment(75);
		ConsoleGenerator.write2Console("Player "+p.getPlayerName()+" paid $75 luxury tax"
				+ "because he landed on Luxury Tax.");
	}

}
