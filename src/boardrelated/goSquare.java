package boardrelated;
import backend.ConsoleGenerator;

import backend.Game;
import backend.Player;

/**
 * This class is provided to meet the needs of the Go square type. This abstraction allows
 * us to apply those needs.
 * Its instances provide the procedures that are to be applied upon a game and players.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */
public class goSquare extends Square {
	
	/**
	 * goSquare is the constructor that creates an instance of goSquare.
	 * 
	 * @param name the name of the square
	 * @param squareNumber the number of the square which is sort of tag
	 * @param trackNumber number of the track that this' instance is on
	 * @param colorID of this square's instance
	 * @param totalPlayers the number of total players on its instance
	 * 
	 */
	public goSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
	}

	/**
	 * This method of the goSquare class increases the balance of the passing player by 200.
	 * 
	 * @param p player who landed on an instance of goSquare
	 * @param g game in which the arriving player and the instance of goSquare exist
	 * @modifies increases the balance of the passing player by 200$
	 * @see boardrelated.Square#passing_proc(backend.Player, backend.Game)
	 */

	public void passing_proc(Player p, Game g) {
		// TODO Auto-generated method stub
		p.receivePayment(200);
		ConsoleGenerator.write2Console("Player "+p.getPlayerName()+" recieved 200$ as he/she"
				+ " passed the Go Square");
	}

}
