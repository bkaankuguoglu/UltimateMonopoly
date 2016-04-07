package card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;
import boardrelated.RailroadTrackSquare;
import boardrelated.Square;
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
public class _GeneralRepairs extends Card {

	/**
	 * Constructor for GeneralRepairs class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	public _GeneralRepairs(String cardName, int typeID, int cardID,
			boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Player makes General Repairs to all its properties. $25 per House, Cab Stand, and Transit Station
	 * $100 per Hotel and Skyscraper
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies p g
	 * @effects Player pays $25 per House, Cab Stand, and Transit Station; $100 per Hotel and Skyscraper
	 * 
	 */
	@Override
	public void applyCard(Player p, Game g) {
		// TODO Auto-generated method stub
		/*$25 per House, Cab Stand, and 
		Transit Station
		$100 per Hotel and Skyscraper
		Play this card immediately.
		 */
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");

		int numOfCab = 0;
		int numOfRail = 0;
		int numOfHouse = 0;
		int numOfHotel = 0;
		int numOfSky = 0;
		int price = 0;
		
		HashMap<String, buyableSquare> sqMap = p.getPropertyList();
		ArrayList<buyableSquare> sqList = new ArrayList<buyableSquare>();
		
		for (String s : sqMap.keySet()) {
			sqList.add(sqMap.get(s));			
		}
		
		
		
		for (int i = 0; i < sqList.size(); i++) {
			if(sqList.get(i) instanceof deedSquare){
				deedSquare dSq = (deedSquare) sqList.get(i);
				
				if(dSq.hasHouse()){
					numOfHouse += dSq.getVisNumOfHouses();
					
				}else if(dSq.hasHotel()){
					numOfHotel += 1;
				
				}else if(dSq.hasSkyscraper()){
					numOfSky += 1;
				
				}					

			}else if (sqList.get(i) instanceof cabSquare){
				cabSquare cSq = (cabSquare) sqList.get(i);
				numOfCab += cSq.getNumOfStand();

			}else if (sqList.get(i) instanceof RailroadTrackSquare){
				RailroadTrackSquare rSq = (RailroadTrackSquare) sqList.get(i);
				numOfRail += rSq.getNumOfDepot();

			}
		}
		
		ConsoleGenerator.write2Console("The player has " + Integer.toString(numOfHouse) + " houses.");
		ConsoleGenerator.write2Console("The player has " + Integer.toString(numOfHotel) + " hotels.");
		ConsoleGenerator.write2Console("The player has " + Integer.toString(numOfSky) + " skyscrapers.");
		ConsoleGenerator.write2Console("The player has " + Integer.toString(numOfCab) + " cab stations.");
		ConsoleGenerator.write2Console("The player has " + Integer.toString(numOfCab) + " depots.");
		price = numOfCab*25 + numOfRail*25  + numOfHouse*25 + numOfHotel*100 + numOfSky*100; 
				
		p.pay2Pool(price, g);
	}
	}


