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
public class Chairperson extends Card {

	/**
	 * Constructor for Chairperson class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	public Chairperson(String cardName, int typeID, int cardID, boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Player who draws the card pays each player $50.
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies p g
	 * @effects Player p paid each player $50.
	 * 
	 */
	public void applyCard(Player p,Game g) {
		//You are elected as the Chairperson. Pay each player $50.
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		for (int i=0;i<g.getNumberOfPlayers();i++){
			Player t=g.getPlayerArray()[i];
			if (t.getPlayerNo()!=p.getPlayerNo()){
				p.transferMoney(t, 50, g);
			}
		}
	}

}
