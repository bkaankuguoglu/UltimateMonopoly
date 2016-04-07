package card;

import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;
import boardrelated.Square;
import boardrelated.utilitySquare;

/**
 * This Class is a subclass of Card class that simply holds the fields String cardName, Player owner, int cardID, 
 * int typeID and boolean keepable for the instance of the class and has constructors, setters and getters for the fields specified
 * above. It also has a applyCard method that overrides the main applyCard method in the main Card Class. 
 * 
 *  @author Berk Kaan Kuguoglu
 *  @version   1.0
 */
public class Advance2SqueezePlay extends Card {

	/**
	 * Constructor for Advance2StockExchange class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	public Advance2SqueezePlay(String cardName, int typeID, int cardID, boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Player advances to Squeeze Play
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies p
	 * @effects Player p advanced to Squeeze Play.
	 * 
	 */
	public void applyCard(Player p,Game g) {
		//Advance to Squeeze Play, if you pass “Go”, collect $200 from the bank.
		//TODO fix this it goes to Squeeze Play
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		advanceLookUp(p,g,"Squeeze Play");
		//g.movePlayerWithDice(20-p.getLocation().getSquareNumber(), p);
	}

}
