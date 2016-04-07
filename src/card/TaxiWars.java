package card;

import java.util.ArrayList;

import backend.ConsoleGenerator;
import backend.DialogGenerator;
import backend.Game;
import backend.Player;
import boardrelated.cabSquare;

/**
 * This Class is a subclass of Card class that simply holds the fields String cardName, Player owner, int cardID, 
 * int typeID and boolean keepable for the instance of the class and has constructors, setters and getters for the fields specified
 * above. It also has a applyCard method that overrides the main applyCard method in the main Card Class. 
 * 
 *  @author Berk Kaan Kuguoglu
 *  @version   1.0
 */
public class TaxiWars extends Card {
	/**
	 * Constructor for TaxiWars Class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	public TaxiWars(String cardName, int typeID, int cardID, boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Player takes any Cab Company from any player. If none are owned, player purchases it and goes to that space.
	 * 	 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies p g
	 * @effects Player p takes over any Cab Company from any player. Or it purchases it. Then, player p goes there.
	 * 
	 */
	@Override
	public void applyCard(Player p, Game g) {
		// TODO Auto-generated method stub
		/*
		 * •	Take any 1 Cab Company from any player. If none are owned, purchase your choice from the bank. Advance to that space. If you pass a Pay Corner, collect your income.
			•	Play this card immediately. 
		 */
		ArrayList<cabSquare> cabList = g.getTheTable().getCabList(g);
		cabSquare targetCab = DialogGenerator.TaxiWarsDialog(cabList, g);
		advanceLookUp(p, g, targetCab.getName());
		targetCab.setOwner(p); 
		
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
	}

}
