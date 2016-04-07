package card;

import java.util.ArrayList;

import backend.ConsoleGenerator;
import backend.DialogGenerator;
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
public class ForeClosedPropertySale extends Card {

	/**
	 * Constructor for ForeClosedPropertySale class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	public ForeClosedPropertySale(String cardName, int typeID, int cardID,
			boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Player forecloses on any opponent's mortgaged property. Pay the mortgage value to 
	 * the bank to claim the property.
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires There must be at least one mortgaged property in the game.
	 * @modifies p g
	 * @effects Player claims the mortgaged property by paying the mortgage value of it.
	 * 
	 */
	@Override
	public void applyCard(Player p, Game g) {
		// TODO Auto-generated method stub
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		ArrayList<buyableSquare> mortSqs = g.getTheTable().getMortgagedSquareList(g);
		buyableSquare targetSquare = DialogGenerator.ForeclosedPropertySaleDialog(mortSqs, g);
		p.givePaymentSafe(targetSquare.getMortgagePrice());
		targetSquare.setOwner(p);
		targetSquare.setMortgaged(false);
	}

}
