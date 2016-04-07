package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import javax.naming.BinaryRefAddr;

import card.Card;
import boardrelated.Square;
import boardrelated.buyableSquare;
import boardrelated.deedSquare;
import boardrelated.drawCardSquare;
/**
 * Game class acts as the controller of the game. It has all the state information of the game and implements the main
 * functions that are called by the GUI components. Game has the playerList, the board, the dice, the pool and initial methods. 
 * 
 * 
 *  @author Utku Evci
 *  @version     %I%, %G%
 *  
 */
public class Game {
	private Table theTable;
	private Dice theDice;
	private Player[] playerArray;
	final int numberOfPlayers; 
	final int initialBalance=2200; 
	private int currentPlayer;
	private boolean isDebugMode;
	private LinkedList<Player> playerList = new LinkedList<Player>();
	static intSq[] interfaceArray = new intSq[1];
	private int testFlag;

	
	/** Constructor that creates an instance of a game. It does not only create but also initiates the
	 * playerList ant other fields like Dice, Board and Pool
	 * 
	 * @param in If in the debug mode the game uses the scanner as the dice input
	 * @param numPlayer number of players that are on the board
	 * 
	 * @modifies the playerList. Fills it up with players after initiating
	 */
	public Game(int numPlayer) {
		isDebugMode=false;
		theDice=new Dice(isDebugMode);
		this.numberOfPlayers=numPlayer;
		// TODO Auto-generated constructor stub
		this.theTable=new Table(numberOfPlayers);
		this.initiatePlayerList(theTable); //TODO ask this as input
		//TODO ask this as input
		currentPlayer=0;		
	}
	public Game(int numPlayer,boolean isDM) {
		isDebugMode=isDM;
		theDice=new Dice(isDebugMode);
		this.numberOfPlayers=numPlayer;
		// TODO Auto-generated constructor stub
		this.theTable=new Table(numberOfPlayers);
		this.initiatePlayerList(theTable); //TODO ask this as input
		//TODO ask this as input
		currentPlayer=0;
	}
	/** Returns all the square names which are on the board as a string array.
	 * 
	 * @return  all the square names which are on the board as a string array.
	 */
	public String[] getSquareNames(){
		String[] result=new String[116];

		for(int i=0;i<116;i++){
			result[i]=this.getSquare(i).getName();
		}


		return result;
	}
	
	/** Initiates the player list with the total players and add all of them to the Pay Day Square
	 * @requires numberOfPlayers are not null	 * 
	 */
	private void initiatePlayerList(Table theTable) {
		// TODO Auto-generated method stub
		/*	if(this.isDebugMode){loadPlayersFromAReadyStateTxt();
		}else{
		 */
		playerArray = new Player[numberOfPlayers];
		for(int i=0;i<numberOfPlayers;i++){
			playerArray[i]=new Player(i,"Player"+i,this.initialBalance,getSq("Pay Day Square"));
			getSq("Pay Day Square").addPlayerToSquareDebug(playerArray[i]);
		}
	}
	/** Updates the view and starts the game by enabling the first player to throw.
	 * @requires currentPlayer and playerArray are not null	 
	 */
	public void playGame(){ //Initiates each player at 0 //
		for(int i=0;i<numberOfPlayers;i++){
			playerArray[i].updateAllViews();
		}

		theTable.updateAllSquareViews();
		ConsoleGenerator.write2Console("The game has begun!\n");
		playerArray[this.currentPlayer].setCanRoll(true);
	}

	/** Throws the dice and makes the necesearry calls. According to the players situation. If he/she is in
	 * the jail than it does a different procedure.
	 * @requires currentPlayer and playerArray are not null. The player is not bankrupt.
	 */
	public void throwDiceAndPerform() {
		Player current=playerArray[this.currentPlayer];
		current.setTakingTurn(true);
		if (current.isInJail()){
			if (current.isGetOutOfJailCard()){
				if(DialogGenerator.AskYesNoDialog("Do you want to use Get Of Jail Card?","Get Out of Jail Card?")){
					current.useCard("Get Out Of Jail", this);
					ConsoleGenerator.write2Console("Bravo you used your Get Out Of Jail Card!");
					throwAndMoveRegularly(current);
				}
			}

			else if (current.getTurnInJail()<2){				
				if (current.askForJailFee(this)){ //TODO implement this in game 
					current.getOutFromJail();
					throwAndMoveRegularly(current);					
				}
				else{
					throwAndMoveInJail(current);
				}
			}
			else{
				throwAndMoveInJail(current);
			}	
		}
		else{
			throwAndMoveRegularly(current);
		}


	}
	
	private void throwAndMoveInJail(Player current) {
		// Jail possibilities with Dice throwing
		theDice.rollDiceInJail();
		playerArray[this.currentPlayer].setCanRoll(false);
		if(theDice.checkTheJailDice()){
			current.getOutFromJail();
			movePlayerWithDice(current);
		}
		else if(current.getTurnInJail()==2){
			ConsoleGenerator.write2Console("You fail roll double and This is the 3th turn in Jail you have to pay");
			current.pay2Pool(50,this);
			current.getOutFromJail();
			movePlayerWithDice(current);
		}
		else{
			current.setTurnInJail(current.getTurnInJail()+1);
			ConsoleGenerator.write2Console("This is the "+current.getTurnInJail()+"th turn in Jail");
			finishTurn();
		}
	}

	private void throwAndMoveRegularly(Player current){
		theDice.rollTheDice();
		playerArray[this.currentPlayer].setCanRoll(false);
		if(getTheDice().getDoubleCount()==3){
			current.go2Jail(this);
		}
		else if (theDice.isTriple()){
			current.goAnyPlace(this);
		}
		else {
			movePlayerWithDice(current);
		}
	}
	/** Makes the player move just by callin the Player.move2SquareWithDice function.
	 * @param cP Player to be moved
	 * @requires dice is thrown appropriately
	 * @modifies the player and the state
	 */
	public void movePlayerWithDice(Player cP) {
		// TODO Auto-generated method stub
		cP.move2SquareWithDice(this);

		//TODO square deki seyleri de update etmeli. Yani bu fonksiyonu Game'de move diye implement etmeli
	}
	private boolean doMrMonopolyMove(Player cP) {
		ConsoleGenerator.write2Console("This is Mr.MonopolyMove");
		Square cs=cP.getLocation();
		Square ns;
		deedSquare bys=null;
		ns=cs.next_square(getTheDice(), cP);
		while(ns!=cs){
			//System.out.println("Checking"+ ns.getName());
			if(ns==cs){
				ConsoleGenerator.write2Console("The player stayed where he is because all properties are owned.");
				return false; //Stay where you are since no buyablesquare exists.
			}
			if(ns instanceof deedSquare){
				bys=(deedSquare) ns;
				if(!bys.isHasAnOwner()){
					break;
				}
			}
			ns=ns.next_square(getTheDice(), cP);
		}

		cP.move2SquareWithTarget(bys, this);
		ConsoleGenerator.write2Console("The player moved to the closest deedSquare");
		return true;

	}
	
	private boolean doBusMove(Player cP) {
		ConsoleGenerator.write2Console("This is BusMove");
		Square cs=cP.getLocation();
		Square ns;
		drawCardSquare bys=null;
		ns=cs.next_square(getTheDice(), cP);
		while(ns!=cs){
			//System.out.println("Checking"+ ns.getName());
			if(ns==cs){
				ConsoleGenerator.write2Console("The player stayed where he is because there is not any DrawCardSquare, wtf STH must be wrong!.");
				return false; //Stay where you are since no buyablesquare exists.
			}
			if(ns instanceof drawCardSquare){
				bys=(drawCardSquare) ns;		
				break;
			}
			ns=ns.next_square(getTheDice(), cP);
		}
		cP.move2SquareWithTarget(bys, this);
		ConsoleGenerator.write2Console("The player moved to the closest"+bys.getCardType()+"square");
		return true;
	}
	/** Finishes the turn of the current player by preparing the game for the next move, which is either Mr. Monopoly Move,  double move or pass the dice to the next player
	 * @requires dice is thrown appropriately
	 * @modifies the player, dice, game.
	 */
	public void finishTurn(){
		Player cP=playerArray[this.currentPlayer];
		if(theDice.isThrowedInJail()||cP.isBankrupt()){
			passTheDice2NextUser(cP);
		}
		// TODO This method needs to have Game ended Winner!
		else if (theDice.isMrMonopolyMove()){
			doMrMonopolyMove(cP);
			theDice.setMrMonopolyMove(false);
		}
		else if (theDice.isBusMove()){
			doBusMove(cP);
			theDice.setBusMove(false);
		}
		else if (theDice.isDouble()){
			//ConsoleGenerator.write2Console("hey");
			cP.setTakingTurn(false);
			playerArray[this.currentPlayer].setCanRoll(true);
			ConsoleGenerator.write2Console("Player No:" + this.currentPlayer+". Please throw the damn dice since you throwed double\n");
		}
		else{
			passTheDice2NextUser(cP);
		}

	}

	/** Sets the states of the players appropriately such that next player is able to roll the dice if it is not bankrupt.
	 * @requires dice is thrown appropriately
	 * @param cP current player who recently rolled the dice.
	 * @modifies the players and the state
	 */
	public void passTheDice2NextUser(Player cP){
		cP.setTakingTurn(false);
		int oldturnPlayer=currentPlayer; //to check the winner
		this.currentPlayer=(this.currentPlayer+ 1)%this.numberOfPlayers;
		checkTheNextUserBankrupcy();
		if (currentPlayer==oldturnPlayer){
			endTheGameWithWinner(cP);
		}
		playerArray[this.currentPlayer].setCanRoll(true);
		ConsoleGenerator.write2Console("Player No:" + this.currentPlayer+". Please throw the damn dice.\n");

	}

	private void endTheGameWithWinner(Player cP) {
		// TODO If you have time make it fancy
		ConsoleGenerator.write2Console("This game is ended!!"+cP.getPlayerName()+" win!");
	}

	private void checkTheNextUserBankrupcy() {
		// TODO This method needs to have Game ended Winner!
		while(playerArray[this.currentPlayer].isBankrupt()){
			this.currentPlayer=(this.currentPlayer+ 1)%this.numberOfPlayers;
		}
	}

	/** Bankrupt the player GIVER(1st argument) and gives its belongings and assests to the receiver, which is either another player or NULL, which means the bank.
	 * @requires giver has to be bankrupt and did not bankrupt earlier. 
	 * @modifies both players and the game in general.
	 */
	public void bankruptPlayer(Player giver, Player receiver) {	
		if(receiver==null){
			//give all the belongings to bank
			ConsoleGenerator.write2Console(giver.getPlayerName()+"giving his/her properities to the bank");	

			ArrayList<buyableSquare> tr=giver.getBuyableSquares();
			for(int i=0; i<tr.size();i++){
				buyableSquare ts=tr.get(i);
				ts.setOwner(null);
				ts.setHasAnOwner(false);
			}
			tr=giver.getMortgagedSquares();
			for(int i=0; i<tr.size();i++){
				//Later put it on the auction
				buyableSquare ts=tr.get(i);
				ts.setOwner(null);
				ts.setHasAnOwner(false);
				ts.setMortgaged(false);
			}
		}
		else{
			//give them to the receiver.
			ConsoleGenerator.write2Console(giver.getPlayerName()+"giving his/her properities to the bank");	

			ArrayList<buyableSquare> tr=giver.getBuyableSquares();
			for(int i=0; i<tr.size();i++){
				buyableSquare ts=tr.get(i);
				ts.changeOwner(receiver);
			}
			tr=giver.getMortgagedSquares();
			for(int i=0; i<tr.size();i++){
				//Later put it on the auction
				buyableSquare ts=tr.get(i);
				ts.changeOwnerMortgaged(receiver);
			}

			HashMap<String,Card> crd=giver.getCardList();
			for(String key: crd.keySet()){
				Card tc=crd.get(key);
				receiver.addCard(tc);
			}	
		}
		giver.getLocation().removePlayerFromSquare(giver);
		giver.setLocation(null);
		giver.setTrackNumber(-1);
		giver.setBalance(0);
		giver.getPropertyList().clear();
		giver.getMortgagedPropertyList().clear();
		giver.getCardList().clear();
		giver.setBankrupt(true);
		///TODO button lari disable etme?
		this.finishTurn();		
	}
	
	/** Gets the square named as ts and casts it into a buyableSquare
	 * @requires table is initiated. 
	 * @param ts is a valid square name, if it is not then null is returned
	 * @return the square named as the input, otherwise null
	 */
	public buyableSquare getBuyableSq(String ts) {
		Square tsq=getSq(ts);
		if(tsq==null) return null;
		if(tsq instanceof buyableSquare) {
			return (buyableSquare) tsq;
		}
		else{
			return null;
		}
	}
	
	
	/** Gets the card named as ts
	 * @requires table and deck is initiated. 
	 * @param ts is a valid card name, if it is not then null is returned
	 * @return the card named as the input, otherwise null
	 */
	public Card getKeepableCard(String ts) {
		// TODO Auto-generated method stub
		Card c=getTheTable().getCardDeck().getCard(ts);
		if (c==null){
			return null;
		}
		{
			return (c.isKeepable()) ? c : null;
		}

	}

	/** Gets the square named as SqNAme
	 * @requires table is initiated. 
	 * @param SqName is a valid square name, if it is not then null is returned
	 * @return the square named as the input, otherwise null
	 */
	public Square getSq(String SqName) {
		return getTheTable().getBoard().getSquare(SqName);
	}


	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	public Player getSpecificPlayer(int i){
		return playerArray[i];
	}

	public Player[] getPlayerArray() {
		return playerArray;
	}
	public void setPlayerArray(Player[] playerArray) {
		this.playerArray = playerArray;
	}
	
	public int getInitialBalance() {
		return initialBalance;
	}
	public Dice getTheDice() {
		return theDice;
	}
	public void setTheDice(Dice theDice) {
		this.theDice = theDice;
	}
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public String[] getConsole() {

		return ConsoleGenerator.getConsole();

	}

	public void addView(intSq i){
		interfaceArray[0]=i;
	}
	public synchronized static void updateAllViews(){
		if(interfaceArray[0]!=null){
			interfaceArray[0].updateView();
		}

	}
	public void pcon(String s){
		ConsoleGenerator.write2Console(s);
		updateAllViews();
	}
	public int getNumberOfSquares(){
		return (theTable.getBoard().getSquareArrayList().size());
	}
	public Square getSquare(int i){
		return theTable.getBoard().getSquareArrayList().get(i);
	}

	public Table getTheTable() {
		return theTable;
	}
	public void setTheTable(Table theTable) {
		this.theTable = theTable;
	}

	public void printGame() {
		theTable.printTable();
	}
	public int getTestFlag() {
		return testFlag;
	}

	public void setTestFlag(int testFlag) {
		this.testFlag = testFlag;
	}

}
