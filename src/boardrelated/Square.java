package boardrelated;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import backend.ConsoleGenerator;
import backend.Dice;
import backend.Game;
import backend.Player;
import backend.intSq;

/**
 * This class provides the most methods that are used for the Data Type. It is at the very top of the hierarchy
 * of many other classes. It has no specific attribute but a general one. The different squares have their own
 * behaviours in the subclasses. Square just provides the common methods for them such as addPlayerToSquare(),
 * removePlayerFromSquare() etc.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */
public class Square {
	String name;
	private boolean isAuctioned;
	public boolean isAuctioned() {
		return isAuctioned;
	}

	public void setAuctioned(boolean isAuctioned) {
		this.isAuctioned = isAuctioned;
	}
	private boolean buyable; //Who added this. Looks dangerous! Because w
	private Player[] playerList;
	private int totalPlayersOnTheSquare;
	private int colorID;
	private int squareNumber;
	private int trackNumber;
	intSq[] interfaceArray = new intSq[1];
	private Square nxSq; //next Square
	private Square prSq; //previous Square
	private Color c1 = new Color(255, 153, 204);
	private Color c2 = new Color(102, 255, 102);
	private Color c3 = new Color(255, 255, 153);
	private Color c4 = new Color(255, 204, 204);
	private Color c5 = new Color(102, 0, 51);
	private Color c6 = new Color(153, 153, 0);
	private Color c7 = new Color(255, 204, 153);
	private Color c8 = new Color(153, 0, 0);
	private Color c9 = new Color(102, 0, 204);
	private Color c10 = new Color(204, 153, 255);
	private Color c11 = new Color(255, 0, 127);
	private Color c12 = new Color(255, 128, 0);
	private Color c13 = new Color(255, 0, 0);
	private Color c14 = new Color(255, 255, 0);
	private Color c15 = new Color(20, 153, 20);
	private Color c16 = new Color(0, 128, 255);
	private Color c17 = new Color(255, 255, 255);
	private Color c18 = new Color(51,255,255);
	private Color c19 = new Color(255, 153, 153);
	private Color c20 = new Color(102, 51, 0);
	private Color c21 = new Color(180, 180, 180);

	/**
	 * Creates an instance of the Square class. Sets the previous and the next squares that are connected to it
	 * null as default.
	 * 
	 * @param name the name of the square
	 * @param squareNumber the number of the square which is sort of tag
	 * @param trackNumber number of the track that this' instance is on
	 * @param colorID of this square's instance
	 * @param totalPlayers the number of total players on its instance
	 * 
	 * @modifies sets the next and previous squares to null by default
	 */

	public Square(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		this.name=name;
		this.colorID=colorID;
		this.squareNumber = squareNumber;
		this.trackNumber = trackNumber;
		this.playerList=new Player[totalPlayers];
		this.totalPlayersOnTheSquare=0;
		nxSq=null;
		prSq=null;
		
	}

	/**
	 * A call on it returns the color of the square instance.
	 * 
	 * @return the color of the square that this method is called upon
	 */
	public Color getColor(){
		switch (this.colorID) {
		case 1 : return c1;
		case 2: return c2;
		case 3: return c3;
		case 4: return c4;
		case 5 : return c5;
		case 6: return c6;
		case 7: return c7;
		case 8: return c8;
		case 9 : return c9;
		case 10: return c10;
		case 11: return c11;
		case 12: return c12;
		case 13: return c13;
		case 14: return c14;
		case 15: return c15;
		case 16: return c16;
		case 17: return c17;
		case 18: return c18;
		case 19: return c19;
		case 20: return c20;
		case 21: return c21;
		default: return c21;
		}
	}
	/**
	 * The broad instance of square has no arrival procedure. Hence, this procedure when a player arrives
	 * does nothing but shouting out that the arrival proc is supposedly happening.
	 * 
	 * @param p the player who lands on square instance
	 * @param g the game that the square and player is in
	 */
	public void arrival_proc(Player p,Game g){
		ConsoleGenerator.write2Console("This is a"+this.getName()+" arrival proc:");
	}
	
	/**
	 * The broad instance of square has no passing procedure. Hence, this procedure when a player passes
	 * does nothing but shouting out that the passing proc is supposedly happening.
	 * 
	 * @param p the player who lands on square instance
	 * @param g the game that the square and player is in
	 */
	public void passing_proc(Player p,Game g){
		//ConsoleGenerator.write2Console("This is a Square passing proc:");
	}
	/**
	 * Returns the square after the insatance of Square
	 * 
	 * @param d the dice of the game
	 * @param p any player that can show the reverse direction movement
	 * 
	 * @return a square after the instance that this procedure is called upon
	 */
	public Square next_square(Dice d, Player p) {
		// TODO Implement reverse
		if (p.isReverseMoving()){
			return getPrSq();
		}
		else{
			return getNxSq();
		}
	}
	
	/**
	 * Returns the square after the instance of Square
	 * @param p any player that can show the reverse direction movement
	 * 
	 * @return a square after the instance that this procedure is called upon
	 */
	
	public Square nextSquareWithoutDice(Player p) {
		// TODO Implement reverse
		if (p.isReverseMoving()){
			return getPrSq();
		}
		else{
			return getNxSq();
		}
	}
	
	/**
	 * Gives out the name, square number, track number, colorID of the square that this method is called upon
	 * @return a String of the information of square instance
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Square [name=" + name + ", squareNumber=" + squareNumber + ", trackNumber=" + trackNumber
				+ ", colorID=" + colorID + "]";
	}
	
	/**
	 * Puts a player on the square instance
	 * 
	 * @param newcomer a player that is to be put on the square
	 * @modifies the number of players
	 */
	public void addPlayerToSquare(Player newcomer){
		this.playerList[newcomer.getPlayerNo()]=newcomer;
		totalPlayersOnTheSquare++;
		updateAllViews();
	}
	
	/**
	 * Puts a player on the square instance on debug mode
	 * 
	 * @param newcomer a player that is to be put on the square
	 * @modifies the number of players
	 */
	public void addPlayerToSquareDebug(Player newcomer){
		this.playerList[newcomer.getPlayerNo()]=newcomer;
		totalPlayersOnTheSquare++;
	}
	
	/**
	 * Removes a player on the square instance
	 * 
	 * @param leaver a player that is to be removed on the square
	 * @modifies the number of players
	 */
	public void removePlayerFromSquare(Player leaving){
		this.playerList[leaving.getPlayerNo()]=null;
		totalPlayersOnTheSquare--;
		updateAllViews();
	}
	
	/**
	 * Adds another view to the interface array that relates to UI
	 * 
	 * @param i the number of views
	 * @modifies the first element of the interfaceArray
	 * 
	 */
	public void addView(intSq i){
		interfaceArray[0]=i;
	}
	
	/**
	 * Updates all the views of objects of the program
	 * 
	 */
	public synchronized void updateAllViews(){
		interfaceArray[0].updateView();
	}
	
	/**
	 * Gives out the name of the square.
	 * 
	 * @return the name of the square
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Changes the name of the square.
	 * 
	 * @param name the name to be set to the square
	 * @modifies the name of the square
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Allows us to check if a square instance is a buyable one.
	 * 
	 * @return True if the square is a buyable one
	 */
	public boolean isBuyable() {
		return buyable;
	}
	
	/**
	 * Changes the type of the square to a buyable one with the right parameter.
	 * 
	 * @param buyable the boolean that is to be set for squares buyable field
	 * @modifies the buyable field of the square
	 */
	public void setBuyable(boolean buyable) {
		this.buyable = buyable;
	}
	
	/**
	 * Gives out the player list.
	 * @return The player list
	 */
	public Player[] getPlayerList() {
		return playerList;
	}
	
	/**
	 * Changes the player list of this.
	 * @param playerList the player list that is going to be set to the player list of the square.
	 * @modifies The square's player list.
	 */
	public void setPlayerList(Player[] playerList) {
		this.playerList = playerList;
	}
	
	/**
	 * Gives out the total number of players in the same square.
	 * @return The total number of players on this square.
	 */
	public int getTotalPlayersOnTheSquare() {
		return totalPlayersOnTheSquare;
	}
	
	/**
	 * Changes the total number of players of a square.
	 * @param totalPlayersOnTheSquare The number that is going to be set as the total number of players.
	 * @modifies The total number of players field of this' instance.
	 */
	public void setTotalPlayersOnTheSquare(int totalPlayersOnTheSquare) {
		this.totalPlayersOnTheSquare = totalPlayersOnTheSquare;
	}
	
	/**
	 * Gives out the color ID of a square.
	 * @return The color ID of an instance of this.
	 */
	public int getColorID() {
		return colorID;
	}
	/**
	 * Changes the color ID of this.
	 * @param colorID The ID number that is going to be set as the color ID of this' instance.
	 * @modifies the colorID of this.
	 */
	public void setColorID(int colorID) {
		this.colorID = colorID;
	}
	/**
	 * Gives out the square number
	 * @return the square number.
	 */
	public int getSquareNumber() {
		return squareNumber;
	}
	/**
	 * Changes the number of square.
	 * @param squareNumber The number that is going to be set as a square number.
	 * @modifies The number of the square.
	 */
	public void setSquareNumber(int squareNumber) {
		this.squareNumber = squareNumber;
	}
	
	/**
	 * Gives out the track number of the square
	 * @return the track number of the square.
	 */
	public int getTrackNumber() {
		return trackNumber;
	}
	/**
	 * Changes the number of track of the square.
	 * @param trackNumber The number that is going to be set as a track number.
	 * @modifies The track number of the square.
	 */
	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}
	
	/**
	 * Gives out the interface array.
	 * @return The interface array.
	 */
	public intSq[] getInterfaceArray() {
		return interfaceArray;
	}
	
	/**
	 * Changes the interfaceArray of this square.
	 * 
	 * @requires interfaceArray field to be not null.
	 * @param interfaceArray The interfaceArray that is going to be put as the new one.
	 * @modifies The interfaceArray of this' instance.
	 */
	public void setInterfaceArray(intSq[] interfaceArray) {
		this.interfaceArray = interfaceArray;
	}
	
	/**
	 * Gives out the next square of a square.
	 * @return The next square of a square.
	 */
	public Square getNxSq() {
		return nxSq;
	}
	
	/**
	 * Changes the next square of a square.
	 * @param nxSq The square that is going to be set as the next one of the one that calls this method.
	 * @modifies The next square of this' instance.
	 */
	public void setNxSq(Square nxSq) {
		this.nxSq = nxSq;
	}
	
	/**
	 * Gives out the previous square.
	 * @return The previous square of this' instance.
	 */
	public Square getPrSq() {
		return prSq;
	}
	/**
	 * Changes the previous square of a square.
	 * @param prSq The square that is going to be set as the previous one of the one that calls this method.
	 * @modifies The previous square of this' instance.
	 */
	public void setPrSq(Square prSq) {
		this.prSq = prSq;
	}
	
	
}
