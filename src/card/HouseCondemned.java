package card;

import java.util.ArrayList;
import java.util.HashMap;

import backend.ConsoleGenerator;
import backend.DialogGenerator;
import backend.Game;
import backend.Player;
import boardrelated.Square;
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

public class HouseCondemned extends Card {
	
	
	/**
	 * Constructor for HouseCondemned class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */

	public HouseCondemned(String cardName, int typeID, int cardID,
			boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Player sells one house back to the Bank at 1/2 the price it paid for it. (Houses only. If you don’t own 
	 * any houses, do nothing.)
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies p g
	 * @effects Player p sold its house at half the price it paid for.
	 * 
	 */
	
	@Override
	public void applyCard(Player p, Game g) {
		// TODO Auto-generated method stub
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		HashMap<String, Square> propertyMap = new HashMap<String, Square>();
		ArrayList<deedSquare> houseDeeds = new ArrayList<deedSquare>();
		houseDeeds = p.getHouseSquares();
		String userInput = DialogGenerator.HouseCondemnedDialog(houseDeeds);
		propertyMap = g.getTheTable().getBoard().getSquaresHashMap();
		deedSquare targetSquare = (deedSquare) propertyMap.get(userInput);
		
		targetSquare.mortgageOrRemove(1);


		}
	}


