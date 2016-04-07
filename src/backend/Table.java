package backend;




import java.util.ArrayList;
import java.util.HashMap;

import card.Card;
import boardrelated.Board;
import boardrelated.Square;
import boardrelated.StockCompany;
import boardrelated.buyableSquare;
import boardrelated.cabSquare;
import card.Deck;
import boardrelated.buyableSquare;
/**
 * This class mainly acts as a main abstract type keeps the main elements of the game, namely Board, 
 * Deck, Pool, StockMarket and Auction instances. In addition to the methods for drawing card from the deck
 * it also consists of some filter methods that simply searches through the board.
 * 
 * 
 *  @author Utku Evci
 *  @version     %I%, %G%
 *  
 */
public class Table {
	private String name;
	private Board board;
	private Deck cardDeck;
	private Pool pool;
	private StockCompany stockmarket;
	private Auction auction;
	
	/**
	 * Constructor for Table Class. 
	 * 
	 * @param totalPlayers the number of total players
	 * 
	 */
	
	public Table(int totalPlayers) {
		board = new Board("the Board",totalPlayers);
		cardDeck = new Deck();
		pool = new Pool(0);
		auction= new Auction();
	}

	/**
	 * Constructor for Table Class. 
	 * 
	 * @param name the name of the table instance
	 * 
	 */
	
	public Table(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	
	public Deck getCardDeck() {
		return cardDeck;
	}


	public void setCardDeck(Deck cardDeck) {
		this.cardDeck = cardDeck;
	}


	public void printTable() {
		board.printBoard();
		
	}
	
	/**
	 * Draws a chance card from the deck 
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @return c card to be drawn
	 * @requires the deck is previously created and it has chance cards
	 * @modifies p g
	 * @effects Player p draws a chance card from the deck
	 * 
	 */
	
	public Card drawChanceCard(Player p, Game g){
		Card c = new Card();
		c = cardDeck.drawChanceCard();
		return c;
	}
	
	/**
	 * Draws a community chest card from the deck 
	 * 
	 * @param p the player who plays the card
	 * @param g the game instance that is being played
	 * @return c card to be drawn
	 * @requires the deck is previously created and it has community chest cards
	 * @modifies p g
	 * @effects Player p draws a community chest card from the deck
	 * 
	 */
	public Card drawCommunityChestCard(Player p, Game g){
		Card c = new Card();
		c = cardDeck.drawCommunityChanceCard();
		return c;
	}

	
	/**
	 * Updates all the squares on the board 
	 * 
	 * @requires the board is previously created and it has squares
	 * @modifies p g
	 * @effects All the squares on the board are updated
	 * 
	 */
	public void updateAllSquareViews() {
		// TODO Auto-generated method stub
		for (String str : board.getSquaresHashMap().keySet() ) {
			board.getSquaresHashMap().get(str).updateAllViews();
		}
	}
	public int getTotalNumberOfSquares() {
		// TODO Auto-generated method stub
		return board.getSquaresHashMap().keySet().size();
	}


	public Pool getPool() {
		return pool;
	}


	public void setPool(Pool pool) {
		this.pool = pool;
	}
	
	/**
	 * Returns all the squares on the board 
	 * 
	 * @param g the game instance that is being played
	 * @return sqList the ArrayList of squares that are filtered
	 * @requires the table and the board instances are previously created and it has squares
	 * @modifies nothing
	 * @effects the ArrayList of squares to be looked up are returned
	 * 
	 */
	
	public ArrayList<Square> getSquareList(Game g){
		HashMap<String,Square> sqMap = g.getTheTable().getBoard().getSquaresHashMap();
		ArrayList<Square> sqList = new ArrayList<Square>();
	
		for (String propNames : sqMap.keySet()) {
				sqList.add(sqMap.get(propNames));
	}
		return sqList;
	
	}
	
	/**
	 * Returns all the mortgaged squares on the board 
	 * 
	 * @param g the game instance that is being played
	 * @return sqList the ArrayList of squares that are filtered
	 * @requires the table and the board instances are previously created and it has squares
	 * @modifies nothing
	 * @effects the ArrayList of squares to be looked up are returned
	 * 
	 */
	
	public ArrayList<buyableSquare> getMortgagedSquareList(Game g){
		HashMap<String,Square> sqMap = g.getTheTable().getBoard().getSquaresHashMap();
		ArrayList<buyableSquare> sqList = new ArrayList<buyableSquare>();
		buyableSquare curSq = null;
		for (String propNames : sqMap.keySet()) {
			if(sqMap.get(propNames) instanceof buyableSquare)
				curSq = (buyableSquare) sqMap.get(propNames);
				if (curSq.isMortgaged())
					sqList.add(curSq);
	}
		return sqList;
	
	}
	
	/**
	 * Returns all the buyable squares on the board 
	 * 
	 * @param g the game instance that is being played
	 * @return sqList the ArrayList of squares that are filtered
	 * @requires the table and the board instances are previously created and it has squares
	 * @modifies nothing
	 * @effects the ArrayList of squares to be looked up are returned
	 * 
	 */
	
	public ArrayList<buyableSquare> getBuyableSquareList(Game g){
		HashMap<String,Square> sqMap = g.getTheTable().getBoard().getSquaresHashMap();
		ArrayList<buyableSquare> sqList = new ArrayList<buyableSquare>();
		buyableSquare curSq = null;
		for (String propNames : sqMap.keySet()) {
			if(sqMap.get(propNames) instanceof buyableSquare){
				curSq = (buyableSquare) sqMap.get(propNames);
				sqList.add(curSq);
	}}
		return sqList;
	
	}
	
	/**
	 * Returns all the cab squares on the board 
	 * 
	 * @param g the game instance that is being played
	 * @return cabList the ArrayList of cab squares that are filtered
	 * @requires the table and the board instances are previously created and it has squares
	 * @modifies nothing
	 * @effects the ArrayList of squares to be looked up are returned
	 * 
	 */
	public ArrayList<cabSquare> getCabList(Game g){
		HashMap<String,Square> sqMap = g.getTheTable().getBoard().getSquaresHashMap();
		ArrayList<cabSquare> cabList = new ArrayList<cabSquare>();
		cabSquare curSq = null;
		for (String propNames : sqMap.keySet()) {
			if(sqMap.get(propNames) instanceof cabSquare){
				curSq = (cabSquare) sqMap.get(propNames);
					cabList.add(curSq);
			}
		}
		return cabList;
	
	}
}
