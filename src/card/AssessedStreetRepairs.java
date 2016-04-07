/**
 * 
 */
package card;

import java.util.ArrayList;
import java.util.HashMap;

import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;
import boardrelated.buyableSquare;
import boardrelated.cabSquare;
import boardrelated.deedSquare;

/**
 * This Class is a subclass of Card class that simply holds the fields String cardName, Player owner, int cardID, 
 * int typeID and boolean keepable for the instance of the class and has constructors, setters and getters for the fields specified
 * above. It also has a applyCard method that overrides the main applyCard method in the main Card Class. 
 * 
 *  @author Berk Kaan Kuguoglu
 *  @version   1.0
 */

public class AssessedStreetRepairs extends Card {


	/**
	 * Constructor for AssessedStreetRepairs class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */

	public AssessedStreetRepairs(String cardName, int typeID, int cardID,
			boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Player pays $25 per Cab Stand and Transit Station, $40 per House, $115 per Hotel, and $100 per Skyscraper it owns
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies p g
	 * @effects Player p paid $25 per Cab Stand and Transit Station, $40 per House, $115 per Hotel, and $100 per Skyscraper it owned
	 * 
	 */
	@Override
	public void applyCard(Player p, Game g) {
		// TODO Auto-generated method stub
		// $25 per cab-transit station, $40 per house $115 per hotel and $100 per skyscraper.
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		
		int numOfCab = 0;
		int numOfHouse = 0;
		int numOfHotel = 0;
		int numOfSky = 0;
		int price = 0;
		
		ArrayList<buyableSquare> buySq = new ArrayList<buyableSquare>();

		buySq = p.getBuyableSquares();
		
		
		for (int i = 0; i < buySq.size(); i++) {
			if(buySq.get(i) instanceof deedSquare){
				deedSquare dSq = (deedSquare) buySq.get(i);
				
				if(dSq.hasHouse()){
					numOfHouse += dSq.getNumOfHouses();
					
				}else if(dSq.hasHotel()){
					numOfHotel += dSq.getNumberOfHotels();
				
				}else if(dSq.hasHotel()){
					numOfSky += dSq.getNumberOfSkyscrapers();
				
				}					

			}else if (buySq.get(i) instanceof cabSquare){
				cabSquare cSq = (cabSquare) buySq.get(i);
				numOfCab += cSq.getNumOfStand();

			}
		}
		
		price = numOfCab*25 + numOfHouse*40 + numOfHotel*115 + numOfSky*100; 
		
		// ??? it s not clear whether we're gonna pay to pool or to bank
		
		p.pay2Pool(price, g);
	}

}
