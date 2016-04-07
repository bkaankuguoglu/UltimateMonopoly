package card;

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
public class BargainBusiness extends Card {
	
	/**
	 * Constructor for BargainBusiness class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	public BargainBusiness(String cardName, int typeID, int cardID,
			boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * If a player lands on a property that it wants to buy, it can buy it for only $100.
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires The property on which the player is landed on must be unowned
	 * @modifies p g
	 * @effects Player buys the property for $100 or keeps the card for latter use.
	 * 
	 */
	public void applyCard(Player p,Game g) {
		//Bargain Business: When you land on an unowned property you want, buy it for only $100. Keep until needed
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		
		if (p.canBuyCurrentSquare()){
			buyableSquare temp=(buyableSquare) p.getLocation();
			p.buySquareWithPrice(temp,100);
		}
		else{
			ConsoleGenerator.write2Console("STH IS WRONG.YOU CANT BUY HERE");
		}	
		
	}
	
}
