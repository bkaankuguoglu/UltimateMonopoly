package boardrelated;
import backend.ConsoleGenerator;

import backend.Game;
import backend.Player;

/**
 * This class is provided to meet the needs of the Go To Jail square type. This abstraction allows
 * us to apply those needs.
 * Its instances provide the procedures that are to be applied upon a game and players.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */
public class go2jailSquare extends Square {
	
	/**

	 * This is the constructor that creates an instance of go2JailSquare.
	 * 
	 * @param name the name of the square
	 * @param squareNumber the number of the square which is sort of tag
	 * @param trackNumber number of the track that this' instance is on
	 * @param colorID of this square's instance
	 * @param totalPlayers the number of total players on its instance
	 * 
	 */
	
	public go2jailSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
	}


	/**
	 * This method of the go2JailSquare takes the arriving player and places him inside jail.
	 * 
	 * @param p player who landed on an instance of go2JailSquare
	 * @param g game in which the arriving player and the instance of go2JailSquare exist
	 * @modifies the current mood of the player
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */
	@Override
	public void arrival_proc(Player p, Game g) {
		// TODO Auto-generated method stub
		p.go2Jail(g);

	}

}
