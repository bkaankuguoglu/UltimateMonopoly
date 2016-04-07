package boardrelated;
import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;

/**
 *  This class is implemented to meet the needs of the tax collection square. 
 *  
 *  Implementation-Specific: The highest class Square has many sub branches with many different attributes.
 *  This class meets one of them, that is tax refunding on a specific square.
 *  
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */

public class taxRefundSquare extends Square {
	/**
	 * Creates an instance of the taxRefundSquare class. Its aim is to meet the specific behaviour of the process
	 * that needs to be followed upon the arriving player.
	 * 
	 * @param name the name of the square
	 * @param squareNumber the number of the square which is sort of tag
	 * @param trackNumber number of the track that this' instance is on
	 * @param colorID of this square's instance
	 * @param totalPlayers the number of total players on its instance
	 * 
	 */
	public taxRefundSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
	}

	@Override
	/**
	 * The aim of the arrival_proc is to apply the legal arrival procedure of the class. That is to take
	 * the half of the money from the pool of the game and give it to the arriving player.
	 * @param p arriving player on this' instance
	 * @param g the game that this taxRefundSquare is in
	 * @requires a player to be on 
	 * @requires the pool to have some money
	 * @modifies increases the balance of the arriving player by the half of the money that pool has
	 * @modifies decreases the amount of money that pool has by half
	 * @effects the game
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */
	public void arrival_proc(Player p, Game g) {
		// TODO Auto-generated method stub
		int amount = g.getTheTable().getPool().getAmount()/2;
		
		p.receivePayment(amount);
		g.getTheTable().getPool().withdrawMoney(amount);
		
		ConsoleGenerator.write2Console("Player " 
							+ p.getPlayerName() 
							+ " has recieved the half of the amount in the pool, which is "
							+ amount);
	}

}
