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
public class ConsultancyFee extends Card {
	
	/**
	 * Constructor for ConsultancyFee class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	public ConsultancyFee(String cardName, int typeID, int cardID,
			boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Player receives $25 as a consultancy fee
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies p 
	 * @effects Player p received a consultancy fee of $25.
	 * 
	 */
	public void applyCard(Player p,Game g) {
		//Receive Consultancy Fee: Collect $25 from the bank.
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		p.receivePayment(25);
	}
	
}
