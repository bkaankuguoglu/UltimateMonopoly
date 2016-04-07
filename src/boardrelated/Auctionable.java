package boardrelated;

import backend.Game;
import backend.Player;

/**
 * Auctionable interface is provided to allow the use of auctions in subclasses which have different attributes
 * in auctions. 
 * 
 * Its instances provide the procedures that are to be applied upon a game and players.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */
public interface Auctionable {
	/**
	 * Follows the appropriate procedure of an auction.
	 * 
	 * @param g Phe game that auctionable instance is in.
	 * @param p The player who is taking turn in auction.
	 */
	
	public int getPrice();
	
	public void processAuction(Player p,int price);
}
