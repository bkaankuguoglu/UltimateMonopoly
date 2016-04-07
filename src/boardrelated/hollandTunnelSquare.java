package boardrelated;

import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;

/**
 * This class is provided to meet the needs of the Holland Tunnel square type. This abstraction allows
 * us to apply those needs.
 * Its instances provide the procedures that are to be applied upon a game and players.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */
public class hollandTunnelSquare extends Square {
	
	/**
	 * hollandTunnelSquare is the constructor that creates an instance of luxury tax square.
	 * 
	 * @param name the name of the square
	 * @param squareNumber the number of the square which is sort of tag
	 * @param trackNumber number of the track that this' instance is on
	 * @param colorID of this square's instance
	 * @param totalPlayers the number of total players on its instance
	 * 
	 */
	public hollandTunnelSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
	}
	/**
	 * This method of the hollandTunnelSquare class takes the arriving player from one end of the holland tunnel
	 * to the other end. It works vice versa as well.
	 * 
	 * @param p player who landed on an instance of hollandTunnelSquare
	 * @param g game in which the arriving player and the instance of hollandTunnelSquare exist
	 * 
	 * @modifies the location of the arriving player
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */

	@Override
	public void arrival_proc(Player p, Game g) {
		// TODO Auto-generated method stub
		if(p.getLocation().getName().equals("Holland Tunnel 1")){
		p.arriveDirectlyForHolland("Holland Tunnel 2", g); //TODO change this procedures name
	}
		else if(p.getLocation().getName().equals("Holland Tunnel 2")){
			p.arriveDirectlyForHolland("Holland Tunnel 1", g);
		}
		ConsoleGenerator.write2Console("Player "+p.getPlayerName()+" has moved to the other side"
				+ "of the Holland Tunnel.");
	}

}
