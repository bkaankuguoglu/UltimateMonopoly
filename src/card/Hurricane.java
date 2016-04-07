package card;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import backend.ConsoleGenerator;
import backend.DialogGenerator;
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

public class Hurricane extends Card {

	/**
	 * Constructor for Hurricane class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	
	public Hurricane(String cardName, int typeID, int cardID, boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Removes 1 House from each property in any player’s 1 color group. (Downgrade Skyscrapers to Hotels; Hotels to 4 houses.)
	 * 
	 * @param p the player who plays the Hurricane card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies g
	 * @effects one house from a specific color group removed by Player p. (hotel to 4house, skyscraper to hotel)
	 * 
	 */
	
	@Override
	public void applyCard(Player p, Game g) {
		// TODO Auto-generated method stub
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");

		DialogGenerator.hurricaneDialog(p, g);
		
		//Light Pink:1-3
		//Light Green:2-4
		//Light Yellow:3-4
		//Dark Green:4-4
		//Bordeaux:5-4
		//Khaki:6-4
		//Beige:7-4
		//Dark red:8-3
		//Purple:9-2
		//Lilac:10-3
		//Pink:11-3
		//Orange:12-3
		//Red:13-3
		//Yellow:14-3
		//Green:15-3
		//Blue:16-2
		//White:17-3
		//Black:18-3
		//Grey:19-3
		//Brown:20-3
		//Railroads:21-4
		//CabCompanies:22-4
		//ElectricCompany:23-1
		//TrashCollector:24-1
		//Sewage:25-1
		//GasCompany:26-1
		//Waterworks:27-1
		//Telephone:28-1
		//Internet:29-1	
}


}
