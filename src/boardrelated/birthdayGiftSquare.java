package boardrelated;

import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;
import backend.ConsoleGenerator;

/**
 * birthdayGiftSquare class is provided to meet the needs of the Birthday gift square type. This abstraction allows
 * us to apply those needs.
 * Its instances provide the procedures that are to be applied upon a game and players.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */
public class birthdayGiftSquare extends Square {
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
	public birthdayGiftSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
	}

	/**
	 * This method of the birthdayGiftSquare gives 100$ to the arriving player on the instance of birthdayGiftSquare
	 * class.
	 * 
	 * @param p player who landed on an instance of birthdayGiftSquare
	 * @param g game in which the arriving player and the instance of birthdayGiftSquare exist
	 * @modifies increases the balance of the arriving player by 100$
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */
	@Override
	public void arrival_proc(Player p, Game g) {
		// TODO Auto-generated method stub
		p.receivePayment(100);
		ConsoleGenerator.write2Console("Player" + p.getPlayerName() + " recieved 100$ "
				+ "as a birthday gift.");
		// We have no bus tickets now. We just give money for that reason.
	}


}
