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
public class ExcellentAccounting extends Card {
	
	/**
	 * Constructor for ExcellentAccounting class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	public ExcellentAccounting(String cardName, int typeID, int cardID,
			boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		setKeepable(true);
		// TODO Auto-generated constructor stub
	}


	/**
	 * Player advances to Tax Refund and collects all of the pool. Players can keep this card.
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies p g
	 * @effects Player moved to Tax Refund, and collected all the funds in the pool
	 * 
	 */
	@Override
	public void applyCard(Player p, Game g) {
		// TODO Auto-generated method stub
		/*•	Advance to Tax Refund. Collect ALL of the Pool. 
		•	Keep until needed.
		•	Play at any time on your turn.
		 */
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		p.arriveDirectly2Square("Tax Refund", g);
		int amount = g.getTheTable().getPool().getAmount();
		p.receivePayment(amount);
		g.getTheTable().getPool().setAmount(0);
	}

}
