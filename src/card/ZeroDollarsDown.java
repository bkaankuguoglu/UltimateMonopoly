package card;

import java.util.ArrayList;
import java.util.HashMap;

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
public class ZeroDollarsDown extends Card {
	/**
	 * Constructor for ZeroDollarsDown Class. 
	 * 
	 * @param cardName name of the instance to be created
	 * @param cardID of the instance to be created
	 * @param typeID of the instance to be created
	 * @param kpable the boolean value that determines if the Card instance is keepable
	 * 
	 */
	
	public ZeroDollarsDown(String cardName, int typeID, int cardID,
			boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		setKeepable(true);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Player builds 1 free house on any property in a monopoly it owns.
	 *  
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @requires nothing
	 * @modifies p g
	 * @effects Player p builds 1 free house on any property in a monopoly it owns.
	 * 
	 */
	@Override
	public void applyCard(Player p, Game g) {
		// TODO Auto-generated method stub
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		ArrayList<deedSquare> deeds = p.getDeedSquares();
		//furkan list yap seçilene beleþ ev yap
		deedSquare targetDeed = DialogGenerator.ZeroDollarsDownDialog(deeds, g);
		int i=0;
		if(targetDeed.getNumOfHouses()!=0){
			switch(targetDeed.getNumOfHouses()){
			case 0:
				i=1;
				break;
			case 1: 
				i=2;
				break;
			case 2:
				i=3;
				break;
			case 3:
				i=4;
				break;
			case 4:
				i=5;
				break;
			}
		}else if(targetDeed.getNumberOfHotels()==1){
			i=6;
		}else{

		}

		targetDeed.build(i);


	}

}
