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

public class MardiGras extends Card {
	
	/**
	 * Constructor for MardiGras class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	public MardiGras(String cardName, int typeID, int cardID, boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * It moves everyone directly to Canal Street.
	 *
	 * @param p the player who plays the MardiGras card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies p g
	 * @effects every player on the board is moved to Canal Street
	 * 
	 */
	
	@Override
	public void applyCard(Player p, Game g) {
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		Player[] playerList = g.getPlayerArray();
		for (int i = 0; i < playerList.length; i++) {
			playerList[i].arriveDirectly2Square("Canal Street", g);
			ConsoleGenerator.write2Console("Player" + playerList[i].getPlayerName() + "is moved to Canal Street");
		}
		ConsoleGenerator.write2Console("The Card Mardi Gras has been applied!");
	}

}
