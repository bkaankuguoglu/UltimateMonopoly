package card;

import backend.Game;
import backend.Player;
import backend.ConsoleGenerator;
import boardrelated.RailroadTrackSquare;
import boardrelated.Square;
import boardrelated.buyableSquare;
import boardrelated.cabSquare;
import boardrelated.utilitySquare;

/**
 * This Class is a class that simply holds the fields String cardName, Player owner, int cardID, int typeID and 
 * boolean keepable for the instance of the class and has constructors, setters and getters for the fields specified
 * above. It also has a applyCard method to be overridden by the subclasses in the hierarchy later on. Additionally,
 * it also keeps toString method for its instances. Lastly, it has several look up methods to be used in its subclasses.
 * 
 *  @author Berk Kaan Kuguoglu
 *  @version     %I%, %G%
 */


public class Card{
	private String cardName;
	private Player owner;
	private int cardID;
	private int typeID;
	private boolean keepable;

/**
 * Constructor for Card class. 
 * 
 */	
	
	public Card(){
		
	}
/**
 * Constructor for Card class. 
 * 
 * @param cardName name of the instance to be created
 * @param cardID of the instance to be created
 * @param typeID of the instance to be created
 * @param kpable the boolean value that determines if the Card instance is keepable
 * 
 */
	
    public Card(String cardName,int cardID, int typeID,boolean kpable) {
    		this.cardName=cardName;
    		this.typeID = typeID;
    		this.cardID=cardID;
    		this.keepable=kpable;
    }
    

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public boolean isKeepable() {
		return keepable;
	}

	public void setKeepable(boolean keepable) {
		this.keepable = keepable;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	
	public int getCardID() {
		return cardID;
	}


	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	/**
	 * The card-specific method to be overridden in subclasses that describes the behaviour of the card.
	 * @param p Player who plays the card
	 * @param g The game that is being played
	 */

	public void applyCard(Player  p, Game g) {
	} 
		// TODO Auto-generated method stub

	@Override
	public String toString() {
		return "Card [cardName=" + cardName + ", owner=" + owner + ", cardID="
				+ cardID + ", typeID=" + typeID + "]";
	}
	
	/**
	 * Looks up a specific Square instance and moves the Player to the Square looked up.
	 * 
	 * @param p the player who is going to look up
	 * @param g the game instance that is being played
	 * @param sq the name of the Square instance to be looked up
	 * @requires sq is already created and it is placed on the board
	 * @modifies p
	 * @effects the Player p is moved to the Square sq
	 * 
	 */
	
	
	public void advanceLookUp (Player p, Game g, String sq){
		Square startSq = p.getLocation();
		Square temp = startSq;
		ConsoleGenerator.write2Console(startSq.getName());
		
		while(!(temp.getName().equals(sq))){
			temp=temp.nextSquareWithoutDice(p);
			temp.passing_proc(p, g);
		}
		
		p.arriveDirectly2Square(temp, g);
				
	}
	
	
	/**
	 * Looks up a RailroadTrackSquare instance and moves the Player to the Square looked up.
	 * 
	 * @param p the player who is going to look up
	 * @param g the game instance that is being played
	 * @requires there must be at least one RailroadTrackSquare instance on the board
	 * @modifies p
	 * @effects the Player p is moved to the nearest RailroadTrackSquare instance
	 * 
	 */
	
	
	public void advanceLookUpRailroad (Player p, Game g){
		Square startSq = p.getLocation();
		Square temp = startSq;
		ConsoleGenerator.write2Console(startSq.getName());
		
		while(!(startSq instanceof RailroadTrackSquare)){
			temp=temp.nextSquareWithoutDice(p);
			temp.passing_proc(p, g);
		}
		
		p.arriveDirectly2Square(temp, g);
				
	}
	
	/**
	 * Looks up a utilitySquare instance and moves the Player to the Square looked up.
	 * 
	 * @param p the player who is going to look up
	 * @param g the game instance that is being played
	 * @requires there must be at least one utilitySquare instance on the board
	 * @modifies p
	 * @effects the Player p is moved to the nearest utilitySquare instance
	 * 
	 */
	
	
	public void advanceLookUpUtility (Player p, Game g){
		Square startSq = p.getLocation();
		Square temp = startSq;
		ConsoleGenerator.write2Console(startSq.getName());
		
		while(!(startSq instanceof utilitySquare)){
			temp=temp.nextSquareWithoutDice(p);
			temp.passing_proc(p, g);
		}
		
		p.arriveDirectly2Square(temp, g);
				
	}
	
	
	/**
	 * Looks up a cabSquare instance and moves the Player to the Square looked up.
	 * 
	 * @param p the player who is going to look up
	 * @param g the game instance that is being played
	 * @requires there must be at least one cabTrackSquare instance on the board
	 * @modifies p
	 * @effects the Player p is moved to the nearest cabSquare instance
	 * 
	 */
	
	
	public void advanceLookUpCabStation (Player p, Game g){
		Square startSq = p.getLocation();
		Square temp = startSq;
		ConsoleGenerator.write2Console(startSq.getName());
		
		while(!(startSq instanceof cabSquare)){
			temp=temp.nextSquareWithoutDice(p);
			temp.passing_proc(p, g);
		}
		
		p.arriveDirectly2Square(temp, g);
		
	}
}
