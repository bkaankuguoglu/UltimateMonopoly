package card;

import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;
/**
 * This Class is a subclass of Card class that simply holds the fields String cardName, Player owner, int cardID, 
 * int typeID and boolean keepable for the instance of the class and has constructors, setters and getters for the fields specified
 * above. It also has a applyCard method that overrides the main applyCard method in the main Card Class. 
 * 
 *  @author Berk Kaan Kuguoglu
 *  @version   1.0
 */
public class Advance2StCharlesPlace extends Card {

	/**
	 * Constructor for Advance2StCharlesPlace class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	public Advance2StCharlesPlace(String cardName, int typeID, int cardID,
			boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Player advances to St. Charles Place
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies p
	 * @effects Player p advanced to St. Charles Place.
	 * 
	 */
	public void applyCard(Player p,Game g) {
		//Advance to St. Charles Place
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		p.arriveDirectly2Square("St. Charles Place",g);

	}

}
