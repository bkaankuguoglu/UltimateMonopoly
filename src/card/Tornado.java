package card;

import java.util.ArrayList;
import java.util.HashMap;

import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;
import boardrelated.buyableSquare;
import boardrelated.deedSquare;
/**
 * This Class is a subclass of Card class that simply holds the fields String cardName, Player owner, int cardID, 
 * int typeID and boolean keepable for the instance of the class and has constructors, setters and getters for the fields specified
 * above. It also has a applyCard method that overrides the main applyCard method in the main Card Class. 
 * 
 *  @author Berk Kaan Kuguoglu
 *  @version   1.0
 */
public class Tornado extends Card {
	
	/**
	 * Constructor for Tornado Class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	public Tornado(String cardName, int typeID, int cardID, boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Player removes one House from each property in any 1 of its own color groups. 
	 * (Downgrade Skyscrapers to Hotels; Hotels to 4 houses.)	
	 *  
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies p g
	 * @effects Player p removes/downgrades one House from each property in any 1 of its own color groups.
	 * 
	 */
	@Override
	public void applyCard(Player p, Game g) {
		// TODO Auto-generated method stub
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		ArrayList<deedSquare> deeds = new ArrayList<deedSquare>();
		HashMap<String,buyableSquare> propertyList = p.getPropertyList();
		// dialog box -> renk listesi
		deeds = null;
		
		for (int i = 0; i < deeds.size(); i++) {
			deedSquare dSq = deeds.get(i);
			if(dSq.hasHouse()){
				dSq.hurricaneOnOneSquare(1);
			}else if(dSq.hasHotel()){
				// remove one hotel add 4 houses
				dSq.hurricaneOnOneSquare(2);
			}else if(dSq.hasSkyscraper()){
				// remove one skyscraper add one hotel
				dSq.hurricaneOnOneSquare(3);
		}
		
	}
		}

}
