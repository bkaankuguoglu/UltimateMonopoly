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
public class AssetsSeized extends Card {

	/**
	 * Constructor for AssetsSeized class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	public AssetsSeized(String cardName, int typeID, int cardID, boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Player surrenders any one undeveloped, unmortgaged property - or any one building to the Bank. 
	 * If it does not own property - goes directly to Jail.
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies p g
	 * @effects Player p surrenders any one undeveloped unmortgaged property of its own or goes to Jail.
	 * 
	 */
	@Override
	public void applyCard(Player p, Game g) {
		// TODO Auto-generated method stub
		
		/*Surrender any one undeveloped, 
		unmortgaged property - or any one 
		building to the Bank. If you do not 
		own property - go 
		directly
		to Jail.
		*/
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		
		if (p.getPropertyList()!=null){
			ArrayList<buyableSquare> propList = p.getBuyableSquares();
			buyableSquare buySq = DialogGenerator.AssetsSeizedDialog(propList, g);
			buySq.mortgageOrRemove(4);
		}else{
			p.go2Jail(g);
		}
	}

}
