package boardrelated;
import backend.ConsoleGenerator;

import backend.Game;
import backend.Player;

/**
 * This class is provided to meet the needs of reverse playing. 
 * Its instances provide the procedures that are to be applied upon a game and players.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */
public class reverseDirectionSquare extends Square {
	/**
	 * Creates an instance of the reverseDirectionSquare class. Its aim is to meet the specific behavior of the process
	 * that needs to be followed upon the arrival of a player.
	 * 
	 * @param name the name of the square
	 * @param squareNumber the number of the square which is sort of tag
	 * @param trackNumber number of the track that this' instance is on
	 * @param colorID of this square's instance
	 * @param totalPlayers the number of total players on its instance
	 * 
	 */
	public reverseDirectionSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
	}
	/**
	 * This procedure sets the reverse movement of a player to true.
	 * 
	 * @requires a player to be on 
	 * @param p player that is on the reverseDirectionSquare
	 * @param g game that the player and the reverseDirectionSquare is in
	 * 
	 * @modifies the player movement
	 * @effects the game
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */

	@Override
	public void arrival_proc(Player p, Game g) {
		// TODO Auto-generated method stub
		p.setReverseMoving(true);
		ConsoleGenerator.write2Console("Player "+p.getPlayerName()+" has changed the direction"
				+ "of the game.");
	}

}
