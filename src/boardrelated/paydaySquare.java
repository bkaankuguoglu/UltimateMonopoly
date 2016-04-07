package boardrelated;

import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;

/**
 * This class is provided to meet the needs of the "Payday Play". 
 * Its instances provide the procedures that are to be applied upon a game and players.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */
public class paydaySquare extends Square {
	
	/**
	 * paydaySquare is the constructor that creates an instance of paydaySquare.
	 * 
	 * @param name the name of the square
	 * @param squareNumber the number of the square which is sort of tag
	 * @param trackNumber number of the track that this' instance is on
	 * @param colorID of this square's instance
	 * @param totalPlayers the number of total players on its instance
	 * 
	 */
	public paydaySquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
	}
	
	/**
	 * Passing proc of the payday square is different than the others. This method checks if the player who passed
	 * it passed with the odd total of dice or even total of dice to decide how much money to give to the passing
	 * player. If it is odd, than the player receives 300, 400 otherwise.
	 * 
	 * @param p player who passes the paydaySquare
	 * @param g game that payday square and the player are in
	 * 
	 * @modifies increases the balance that player has according to the total of dice
	 * @see boardrelated.Square#passing_proc(backend.Player, backend.Game)
	 */

	@Override
	
	public void passing_proc(Player p, Game g) {
		// TODO Auto-generated method stub
		int totalDice = g.getTheDice().getTotalDice();
		int mod = totalDice % 2;
		if(mod==0){
			p.receivePayment(400);
			ConsoleGenerator.write2Console("Player "+p.getPlayerName()+" recieved 400$"
					+ "from Pay Day because he rolled even.");
		}else{
			p.receivePayment(300);
			ConsoleGenerator.write2Console("Player "+p.getPlayerName()+" recieved 300$"
					+ "from Pay Day because he rolled odd.");
		}
		
	}

}
