package boardrelated;
import backend.ConsoleGenerator;

import backend.Game;
import backend.Player;

/**
 * This class is provided to meet the needs of the Bonus square type. This abstraction allows
 * us to apply those needs. For instance, bonus square has different attributes when someone passes or arrives.
 * This abstraction allows us to apply different behaviors.
 * 
 * Its instances provide the procedures that are to be applied upon a game and players.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */
public class bonusSquare extends Square {
	/**
	 * This is the constructor that creates an instance of bonusSquare.
	 * 
	 * @param name The name of the square.
	 * @param squareNumber The number of the square which is sort of tag.
	 * @param trackNumber Number of the track that this class' instance is on.
	 * @param colorID Color Identification number of this class' instance.
	 * @param totalPlayers The number of total players on its instance.
	 * 
	 */
	public bonusSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
	}

	/**
	 * This method of the bonusSquare transfers 250$ to the arriving player without checking anything.
	 * @param p Player who landed on an instance of bonusSquare.
	 * @param g Game in which the arriving player and the instance of bonusSquare exist.
	 * @requires a Player to arrive on.
	 * @modifies Increases the balance of the arriving player by 250$.
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */
	@Override
	public void arrival_proc(Player p, Game g) {
		// TODO Auto-generated method stub
		ConsoleGenerator.write2Console("Bonus Square");
		p.receivePayment(250);
	}
	/**
	 * This passing method of the bonusSquare transfers 300$ to the arriving player without checking anything.
	 * 
	 * 
	 * @param p Player who landed on an instance of bonusSquare.
	 * @param g Game in which the arriving player and the instance of bonusSquare exist.
	 * @requires a Player to pass on.
	 * @modifies Increases the balance of the arriving player by 300$.
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */
	public void passing_proc(Player p, Game g) {
		// TODO Auto-generated method stub
		ConsoleGenerator.write2Console("Bonus Square");
		p.receivePayment(300);
	}

}
