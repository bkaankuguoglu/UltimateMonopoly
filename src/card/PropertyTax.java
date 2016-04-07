package card;

import java.util.ArrayList;
import java.util.HashMap;

import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;
import boardrelated.buyableSquare;

/**
 * This Class is a subclass of Card class that simply holds the fields String cardName, Player owner, int cardID, 
 * int typeID and boolean keepable for the instance of the class and has constructors, setters and getters for the fields specified
 * above. It also has a applyCard method that overrides the main applyCard method in the main Card Class. 
 * 
 *  @author Berk Kaan Kuguoglu
 *  @version   1.0
 */

public class PropertyTax extends Card {

	
	
	/**
	 * Constructor for PropertyTax class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */

	public PropertyTax(String cardName, int cardID, int typeID, boolean kpable) {
		super(cardName, cardID, typeID, kpable);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Player pays $25 to the Pool for each unmortgaged property it owns.
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies g
	 * @effects Player p paid $25 to the Pool for each unmortgaged property it owned.
	 * 
	 */
	
	public void applyCard(Player  p, Game g) {
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		ArrayList<buyableSquare> buySq = new ArrayList<buyableSquare>();
		buySq = p.getBuyableSquares();
		
		for (int i = 0; i < buySq.size(); i++) {
			p.pay2Pool(25, g);
			
		}
				
	} 

}
