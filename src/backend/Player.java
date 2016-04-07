package backend;

import java.util.ArrayList;
import java.util.HashMap;

import card.Card;
import boardrelated.RailroadTrackSquare;
import boardrelated.Square;
import boardrelated.Stock;
import boardrelated.StockCompany;
import boardrelated.StockExchangeSquare;
import boardrelated.buyableSquare;
import boardrelated.cabSquare;
import boardrelated.deedSquare;
import boardrelated.utilitySquare;

/**
 * This class implements a player of the monopoly game. It has necessary fields to hold the possesions of a player.
 * It also implements the functions needed by his actions.
 * 
 * 
 *  @author Utku Evci
 *  @version     %I%, %G%
 *  
 */
public class Player {
	private final int playerNo;
	private final String playerName;
	private int balance;
	private ArrayList<Stock> GeneralRadioStocks= new ArrayList<Stock>();
	private ArrayList<Stock> UnitedRailwaysStocks= new ArrayList<Stock>();
	private ArrayList<Stock> NationalUtilitiesStocks= new ArrayList<Stock>();
	private ArrayList<Stock> AmericanMotorsStocks= new ArrayList<Stock>();
	private ArrayList<Stock> AlliedSteamshipsStocks= new ArrayList<Stock>();
	private ArrayList<Stock> MotionPicturesStocks= new ArrayList<Stock>();
	private ArrayList<Stock> AllStocks = new ArrayList<Stock>();
	private ArrayList<Stock> MortgagedStocks = new ArrayList <Stock>();
	private HashMap<String,buyableSquare> propertyList; //TODO fix this according to do sell/mortgage stuff
	private HashMap<String,buyableSquare> mortgagedPropertyList;
	private Square location;
	private HashMap<String,Card> cardList;
	private boolean isTakingTurn;
	private boolean canRoll;
	private boolean isBankrupt;
	private boolean reverseMoving;
	private boolean isInJail;
	private int turnInJail;
	private int trackNumber; //This is for train stations to know which track the player is. For transitSquare
	intSq[] interfaceArray = new intSq[1];

	/** Constructor that creates an instance of a player
	 * 
	 * @param playerNo that indicates the player number
	 * @param userName indicates the name of the player
	 * @param balance indicates the initial balance of the player
	 * @param location initial square of the player in the board
	 * @modifies this.
	 * @requries the inputs are not null.
	 */
	public Player(int playerNo, String userName, int balance, Square location) {
		this.playerNo = playerNo;
		this.playerName = userName;
		this.balance = balance;
		propertyList = new HashMap<String,buyableSquare>();
		mortgagedPropertyList = new HashMap<String,buyableSquare>();
		this.location = location;
		cardList= new HashMap<String,Card>();
		this.isTakingTurn=false;
		this.canRoll=false;
		this.isBankrupt=false;
		this.isInJail=false;
		this.trackNumber=location.getTrackNumber();
	}


	/** Returns the names of the properties that the player have as a string array.
	 * 
	 * @returns names of the properties that the player have
	 */
	public String[] getPropertyNames(){
		String[] names = new String[getPropertyList().size()];
		int cc=0;
		for (String key: getPropertyList().keySet()) {
			names[cc++]=getPropertyList().get(key).getName();
		}

		return names;
	}

	/** Returns the names of the mortgaged properties that the player have as a string array.
	 * 
	 * @returns names of the mortgaged properties that the player have
	 */
	public String[] getMortgageNames(){
		String[] names = new String[getMortgagedPropertyList().size()];
		int cc=0;
		for (String key: getMortgagedPropertyList().keySet()) {
			names[cc++]=getMortgagedPropertyList().get(key).getName();
		}

		return names;
	}
	/** Returns the names of the cards that the player have as a string array.
	 * 
	 * @returns names of the cards that the player have
	 */
	public String[] getCardNames(){
		String[] names = new String[getCardList().size()];
		int cc=0;
		for (String key: getCardList().keySet()) {
			names[cc++]=getCardList().get(key).getCardName();
		}
		return names;
	}

	private ArrayList<Square> hashMapToArrayList(HashMap<String,buyableSquare> h){
		ArrayList<Square> result = new ArrayList<Square>();
		for (String str : h.keySet() ) {
			result.add(h.get(str));
		}
		return result;
	}

	/** Returns the number of utilities that a player has.
	 * 
	 * @returns number of utilities that a player has.
	 */
	public int numOfUtilities(){
		int total=0;
		ArrayList<Square> theList = hashMapToArrayList(propertyList);
		for(int i=0; i<propertyList.size();i++){
			if(theList.get(i) instanceof utilitySquare){
				total++;
			}
		}
		return total;
	}
	/** Returns the number of Railroads that a player has.
	 * 
	 * @returns number of Railroads that a player has.
	 */
	//Railroad arrival procedure needs to know how many numOfUtilities are owned by the same person
	public int numOfRailroads(){
		int total=0;
		ArrayList<Square> theList = hashMapToArrayList(propertyList);
		for(int i=0; i<propertyList.size();i++){
			if(theList.get(i) instanceof RailroadTrackSquare){
				total++;
			}
		}
		return total;
	}
	/** Returns the number of cab stations that a player has.
	 * 
	 * @returns number of cab stations that a player has.
	 */
	//Cab deeds' arrival procedure needs to know how many numOfUtilities are owned by the same person
	public int numOfCabs(){
		int total=0;
		ArrayList<Square> theList = hashMapToArrayList(propertyList);
		for(int i=0; i<propertyList.size();i++){
			if(theList.get(i) instanceof cabSquare){
				total++;
			}
		}
		return total;
	}

	/** This function is called when the player wants to buys a square.
	 * 
	 * @param sq the square that the player intends the buy
	 * @requires that the sq is not null
	 */
	public void buySquare(buyableSquare sq){
		int price = sq.getPrice();	
		buySquareWithPrice(sq,price);
	}

	/** This function is called when the player wants to buys a square with a specific price.
	 * This function checks the balance of the player to see whether the player has enough money.
	 * 
	 * @param sq the square that the player intends the buy
	 * @param price the price that the player intends the buy the square for
	 * @requires that the sq is not null
	 */
	public void buySquareWithPrice(buyableSquare sq, int price) {
		// TODO Auto-generated method stub
		assert !sq.isHasAnOwner();
		if(balance>=price){
			propertyList.put(sq.getName(),sq);
			balance -= price;
			sq.setOwner(this);
			sq.setHasAnOwner(true);
			ConsoleGenerator.write2Console("The player " + playerName + " bought "+sq.getName()+" for $"+price+".");
		}else{
			ConsoleGenerator.write2Console("The player " + playerName + " does not have enough money!");
		}
		updateAllViews();
	}

	public void payDividends(){
		payDividendHelper(GeneralRadioStocks);
		payDividendHelper(UnitedRailwaysStocks);
		payDividendHelper(NationalUtilitiesStocks);
		payDividendHelper(AmericanMotorsStocks);
		payDividendHelper(AlliedSteamshipsStocks);
		payDividendHelper(MotionPicturesStocks);
	}

	public void payDividendHelper(ArrayList<Stock> lst){

		int tempSize=lst.size();
		if (tempSize!=0){
			StockCompany tempCompany = lst.get(0).getStCompany();
			ConsoleGenerator.write2Console("Dividends Collected for "+tempCompany.getCompanyName()+" shares of "
					+playerName);
			if (tempSize==1){
				this.receivePayment(tempCompany.getValueOf1Stock());
			}else if (tempSize==2){
				this.receivePayment(tempCompany.getValueOf2Stock());
			}else if (tempSize==3){
				this.receivePayment(tempCompany.getValueOf3Stock());
			}else if (tempSize==4){
				this.receivePayment(tempCompany.getValueOf4Stock());
			}else if (tempSize==5){
				this.receivePayment(tempCompany.getValueOf5Stock());		
			}
		}
	}

	public void buyStockForInheritance(Stock stc){
		buyStockForAuction(stc,0);
	}

	public void buyStockForAuction(Stock sc, int price){
		assert !sc.isHasOwner();
		if(balance>=price){
			balance-=price;
			if(sc.getStCompany().getCompanyName().equals("General Radio")){
				GeneralRadioStocks.add(sc);
				AllStocks.add(sc);
			}else if(sc.getStCompany().getCompanyName().equals("United Railways")){
				UnitedRailwaysStocks.add(sc);
				AllStocks.add(sc);
			}else if(sc.getStCompany().getCompanyName().equals("National Utilities")){
				NationalUtilitiesStocks.add(sc);
				AllStocks.add(sc);
			}else if(sc.getStCompany().getCompanyName().equals("American Motors")){
				AmericanMotorsStocks.add(sc);
				AllStocks.add(sc);
			}else if(sc.getStCompany().getCompanyName().equals("Allied Steamships")){
				AlliedSteamshipsStocks.add(sc);
				AllStocks.add(sc);
			}else if(sc.getStCompany().getCompanyName().equals("Motion Pictures")){
				MotionPicturesStocks.add(sc);
				AllStocks.add(sc);
			}
			sc.setHasOwner(true);
			sc.setOwner(this);
			ConsoleGenerator.write2Console("The player " + playerName + " bought " +sc.getName()+" stock "
					+"in the auction paying "+ price + "$.");
		} else {
			ConsoleGenerator.write2Console(playerName + " does not have enough money although he/she bid "
					+ "the highest amount."
					);

		}
	}


	/*
	 * Case 1 is to buy stock from General Radio
	 * Case 2 is to buy stock from United Railways
	 * Case 3 is to buy stock from National Utilities
	 * Case 4 is to buy stock from American Motors
	 * Case 5 is to buy stock from Allied Steamships
	 * Case 6 is to buy stock from Motion Pictures
	 * Case 7 The player does not buy and auction process starts.
	 */
	public void buyStock(StockExchangeSquare st, int cases, Game g){
		if(cases==7){
			buyStockWithPrice(st, 0, cases, g);
		}else{
			buyStockWithPrice(st, st.getCompanies()[cases-1].getPrice(), cases, g);
		}
	}

	public void buyStockWithPrice(StockExchangeSquare st, int price, int cases, Game g){
		StockCompany[] comp = new StockCompany[5];
		comp = st.getCompanies();

		switch(cases){
		case 1:
			stockPurchaseHelper(st, price, 0, comp);
			break;
		case 2:
			stockPurchaseHelper(st, price, 1, comp);
			break;
		case 3:
			stockPurchaseHelper(st, price, 2, comp);
			break;
		case 4:
			stockPurchaseHelper(st, price, 3, comp);
			break;
		case 5:
			stockPurchaseHelper(st, price, 4, comp);
			break;
		case 6:
			stockPurchaseHelper(st, price, 5, comp);
			break;
		case 7:
			st.processAuction(g,this);
		}

	}

	public void stockPurchaseHelper(StockExchangeSquare st, int price, int caseNr, StockCompany[] comp){
		if (comp[caseNr].allOwned()){
			ConsoleGenerator.write2Console(comp[caseNr].getCompanyName()+" stock company does not have any shares available.");
		} else if(balance >= price){
			Stock temp = comp[caseNr].getOneThatIsNotOwned();
			addToStockList(caseNr, temp);
			temp.setHasOwner(true);
			temp.setOwner(this);
			balance-=price;
			ConsoleGenerator.write2Console("The player " + playerName + " bought a stock from "
					+ comp[caseNr].getCompanyName() +" for $"+price+".");
		}

		else{
			ConsoleGenerator.write2Console("The player " + playerName + " does not have enough money!");
		}

	}

	public void addToStockList(int cases, Stock stc){
		switch(cases){
		case 0:
			GeneralRadioStocks.add(stc);
			AllStocks.add(stc);
			break;
		case 1:
			UnitedRailwaysStocks.add(stc);
			AllStocks.add(stc);
			break;
		case 2:
			NationalUtilitiesStocks.add(stc);
			AllStocks.add(stc);
			break;
		case 3:
			AmericanMotorsStocks.add(stc);
			AllStocks.add(stc);
			break;
		case 4:
			AlliedSteamshipsStocks.add(stc);
			AllStocks.add(stc);
			break;
		case 5:
			MotionPicturesStocks.add(stc);
			AllStocks.add(stc);
			break;

		}
	}

	/*
	 * Case 1 is to mortgage stock from General Radio
	 * Case 2 is to mortgage stock from United Railways
	 * Case 3 is to mortgage stock from National Utilities
	 * Case 4 is to mortgage stock from American Motors
	 * Case 5 is to mortgage stock from Allied Steamships
	 * Case 6 is to mortgage stock from Motion Pictures
	 */
	
	public ArrayList<String> giveOutcompanyNames(){
		ArrayList<String> result = new ArrayList<String>();
		
		if(GeneralRadioStocks.size()!=0)
		result.add("General Radio");
		
		if (UnitedRailwaysStocks.size()!=0)
		result.add("United Railways");
		
		if(NationalUtilitiesStocks.size()!=0)
		result.add("National Utilities");
		
		if(AmericanMotorsStocks.size()!=0)
		result.add("American Motors");
		
		if(AlliedSteamshipsStocks.size()!=0)
		result.add("Allied Steamships");
		
		if(MotionPicturesStocks.size()!=0)
		result.add("Motion Pictures");
		
		
		return result;
		
	}
	public void mortgageStock(int cases){
		if(AllStocks.size()==0){
			ConsoleGenerator.write2Console("You do not have any stocks to mortgage.");
		}else{
			switch(cases){
			case 1:
				if(GeneralRadioStocks.size()==0){
					ConsoleGenerator.write2Console("You do not have any stocks in General Radio Company to mortgage.");
				}else{
					int size = GeneralRadioStocks.size();
					Stock temp = GeneralRadioStocks.get(size-1);
					int money = temp.getPrice()/2;
					MortgagedStocks.add(temp);
					temp.setMortgaged(true);
					ConsoleGenerator.write2Console(playerName +" had "+size+" stocks in General Radio Company. Now, he/she mortgaged one of them.");
					this.receivePayment(money);
					AllStocks.remove(temp);
					GeneralRadioStocks.remove(size-1);
				}
				break;
			case 2:
				if(UnitedRailwaysStocks.size()==0){
					ConsoleGenerator.write2Console("You do not have any stocks in General Radio Company to mortgage.");
				}else{
					int size = UnitedRailwaysStocks.size();
					Stock temp = UnitedRailwaysStocks.get(size-1);
					int money = temp.getPrice()/2;
					MortgagedStocks.add(temp);
					temp.setMortgaged(true);
					ConsoleGenerator.write2Console(playerName +" had "+size+" stocks in United Railways Company. Now, he/she mortgaged one of them.");
					this.receivePayment(money);
					UnitedRailwaysStocks.remove(size-1);
					AllStocks.remove(temp);
				}
				break;
			case 3:
				if(NationalUtilitiesStocks.size()==0){
					ConsoleGenerator.write2Console("You do not have any stocks in General Radio Company to mortgage.");
				}else{
					int size = NationalUtilitiesStocks.size();
					Stock temp = NationalUtilitiesStocks.get(size-1);
					int money = temp.getPrice()/2;
					MortgagedStocks.add(temp);
					temp.setMortgaged(true);
					ConsoleGenerator.write2Console(playerName +" had "+size+" stocks in United Railways Company. Now, he/she mortgaged one of them.");
					this.receivePayment(money);
					NationalUtilitiesStocks.remove(size-1);
					AllStocks.remove(temp);
				}
				break;
			case 4:
				if(AmericanMotorsStocks.size()==0){
					ConsoleGenerator.write2Console("You do not have any stocks in General Radio Company to mortgage.");
				}else{
					int size = AmericanMotorsStocks.size();
					Stock temp = AmericanMotorsStocks.get(size-1);
					int money = temp.getPrice()/2;
					MortgagedStocks.add(temp);
					temp.setMortgaged(true);
					ConsoleGenerator.write2Console(playerName +" had "+size+" stocks in United Railways Company. Now, he/she mortgaged one of them.");
					this.receivePayment(money);
					AmericanMotorsStocks.remove(size-1);
					AllStocks.remove(temp);
				}
				break;
			case 5:
				if(AlliedSteamshipsStocks.size()==0){
					ConsoleGenerator.write2Console("You do not have any stocks in General Radio Company to mortgage.");
				}else{
					int size = AlliedSteamshipsStocks.size();
					Stock temp = AlliedSteamshipsStocks.get(size-1);
					int money = temp.getPrice()/2;
					MortgagedStocks.add(temp);
					temp.setMortgaged(true);
					ConsoleGenerator.write2Console(playerName +" had "+size+" stocks in United Railways Company. Now, he/she mortgaged one of them.");
					this.receivePayment(money);
					AlliedSteamshipsStocks.remove(size-1);
					AllStocks.remove(temp);
				}
				break;
			case 6:
				if(MotionPicturesStocks.size()==0){
					ConsoleGenerator.write2Console("You do not have any stocks in General Radio Company to mortgage.");
				}else{
					int size = MotionPicturesStocks.size();
					Stock temp = MotionPicturesStocks.get(size-1);
					int money = temp.getPrice()/2;
					MortgagedStocks.add(temp);
					temp.setMortgaged(true);
					ConsoleGenerator.write2Console(playerName +" had "+size+" stocks in United Railways Company. Now, he/she mortgaged one of them.");
					this.receivePayment(money);
					MotionPicturesStocks.remove(size-1);
					AllStocks.remove(temp);
				}
				break;
			}
		}
	}

	/** Checks that the square the player on is buyable and it has not any owner.
	 * @return true when the square that the player is  on buyable
	 */
	public boolean canBuyStocks(){
		Square temp = this.getLocation();
		if(temp instanceof StockExchangeSquare)
			return true;
		return false;
	}

	public boolean canBuyCurrentSquare(){

		Square temp= this.getLocation();
		if (temp instanceof buyableSquare){
			buyableSquare temp2=(buyableSquare) temp;
			return !temp2.isHasAnOwner() ;
		}
		else{
			return false;
		}

	}
	/** Receives money and update views.
	 * @param amount the money received.
	 */
	public void receivePayment(int amount){
		balance += amount;
		ConsoleGenerator.write2Console(this.getPlayerName()+" has received $"+amount+".");
		updateAllViews();
	}
	/** Gives money without checking the balance.
	 * @param amount of the money given.
	 * @requires the balance is more than the amount.
	 */
	public void givePaymentSafe(int amount){
		balance -= amount;
		updateAllViews();
		ConsoleGenerator.write2Console("The player"+this.getPlayerNo()+" paid $"+amount);
	}
	/** Gives money when it is given to a player with checking the balance. If not enough balance exist then makes the player sell or the player
	 * go bankrupt.
	 * @param amount of the money given.
	 * @param receiver of the money to be given.
	 * @param g the game player is in.
	 * @requires the receiver and game is not null
	 */
	public void givePayment(int amount,Player receiver,Game g){
		if(this.isEnoughMoneyExist(amount)){
			balance -= amount;

			updateAllViews();
			ConsoleGenerator.write2Console("The player"+this.getPlayerNo()+" paid $"+amount);
		}
		else{
			checkBankrupcy(amount,receiver,g);
			ConsoleGenerator.write2Console("The user " + playerName + " is out of money! Sth is need to be done!");
			while (!this.isEnoughMoneyExist(amount)){
				DialogGenerator.MoneyNeededDialog(this, g);
			}	
		}
	}
	/** Gives money to the game with checking the balance. If not enough balance exist then makes the player sell or the player
	 * go bankrupt.
	 * @param amount of the money given.
	 * @param g the game player is in.
	 * @requires the game is not null
	 */
	public void givePayment(int amount,Game g){
		if(this.isEnoughMoneyExist(amount)){
			balance -= amount;
			updateAllViews();
			ConsoleGenerator.write2Console("The player"+this.getPlayerNo()+" paid $"+amount);
		}
		else{
			checkBankrupcy(amount,null,g);//null to indicate it is going to be paid to the bank
			ConsoleGenerator.write2Console("The user " + playerName + " is out of money! Sth is need to be done!");
			while (!this.isEnoughMoneyExist(amount)){
				DialogGenerator.MoneyNeededDialog(this, g);
			}	
		}
	}

	/** Calls the givePayment method of this player and the other player receives the money afterwards.
	 * @param amount of the money given.
	 * @param receiver of the money to be transfered
	 * @param g the game player is in.
	 * @requires the receiver and game is not null
	 */
	public void transferMoney(Player receiver, int amount, Game g){
		this.givePayment(amount,receiver,g);
		receiver.receivePayment(amount);
	}
	/** Checks if the player is bankrupt. If so it does bankrupt the player by transfering his assests to the 
	 * receiver. Receiver null means the player owes to the game
	 * @param amount of the money given.
	 * @param receiver of the money to be given.
	 * @param g the game player is in.
	 * @requires the receiver and game is not null
	 * @modifies The receiver and THIS and appropriate Squares and Cards.
	 */
	public void checkBankrupcy(int amount,Player receiver, Game g) {	
		if (amount>getReceivableWorth()){
			ConsoleGenerator.write2Console("The user " + playerName + " dont have enough assest to pay the debt of " + amount + ". Therefore he is BANKRUPT!!!");
			g.bankruptPlayer(this, receiver);
		}
	}
	/** Compares the given amount with the player's balance.
	 * @param amountNeeded the amount to be compared
	 * @return true if enough money exists, otherwise false.
	 * @requires the receiver and game is not null
	 */
	public boolean isEnoughMoneyExist(int amountNeeded){
		return amountNeeded<=getBalance();
	}

	/**If the current location is a buyable square it does return the location as a buyableSquare
	 * @return the casted location of the player.
	 */
	public buyableSquare getbuyableLocation() {
		// TODO Auto-generated method stub
		if (this.getLocation() instanceof buyableSquare){
			return (buyableSquare) this.getLocation();
		}
		else{
			ConsoleGenerator.write2Console("Ow you touch my falala\n @Player getbuyableLocation");
			return null;
		}
	}

	/**Mortgages the current square by checking whether it is belong to the player.
	 * @param sq Square to be mortgaged.
	 * @return true if succesful, otherwise false.
	 * @modifies the player THIS
	 */
	public boolean mortgageProperty(buyableSquare sq) {
		// TODO Auto-generated method stub
		if(!this.getPropertyList().containsKey(sq.getName())){
			ConsoleGenerator.write2Console("The square "+sq.getName()+" is does not belong to the player");
			return false;
		}
		else{
			buyableSquare s=this.getPropertyList().get(sq.getName()); //TODO fix this
			s.setMortgaged(true);
			this.receivePayment(s.getMortgagePrice());
			ConsoleGenerator.write2Console("The player mortgaged"+s.getName()+"for"+s.getMortgagePrice());
			this.getPropertyList().remove(s.getName());
			this.getMortgagedPropertyList().put(s.getName(),s);
			return true;
		}

	}
	/**Mortgages the current square by checking whether it is belong to the player.
	 * @param sq Square to be mortgaged.
	 * @return true if succesful, otherwise false.
	 * @modifies the player THIS
	 */
	public boolean mortgagePropertyDebug(buyableSquare sq) {
		buyableSquare s=this.getPropertyList().get(sq.getName());
		s.setMortgaged(true);
		this.getPropertyList().remove(s.getName());
		this.getMortgagedPropertyList().put(s.getName(),s);
		return true;

	}
	/**Calculates the total money that can be gained by selling the player's assests. 
	 * @return money that can be gained by selling the player's assests.  
	 */
	public int getReceivableWorth(){
		//TODO add house values
		int propertyWorth=0;
		for (String key: getPropertyList().keySet()) {
			propertyWorth=propertyWorth +getPropertyList().get(key).getRecWorth();
		}
		return this.getBalance() + propertyWorth;
	}

	/**Mortgages the current square by checking whether it is belong to the player.
	 * @param sq Square to be mortgaged.
	 * @return true if succesful, otherwise false.
	 * @modifies the player THIS
	 */
	public void addCard(Card card){
		cardList.put(card.getCardName(),card);
		updateAllViews();
	}
	public void addCardDebug(Card card) {
		cardList.put(card.getCardName(),card);
	}
	/**Uses the card given by calling its apply_card method
	 * @param card Card to be used
	 * @param g The game that the player is in.
	 * @modifies the game
	 * @requires that the player owns the card specified.
	 */
	public void useCard(Card card,Game g){
		assert this.cardList.containsKey(card.getCardName());
		card.applyCard(this, g); 
		cardList.remove(card.getCardName());
		updateAllViews();
	}
	/**Uses the card it name is given by calling its apply_card method
	 * @param card Name of the card to be used
	 * @param g The game that the player is in.
	 * @modifies the game
	 * @requires that the player owns the card specified.
	 */
	public void useCard(String card,Game g){
		assert this.cardList.containsKey(card);
		getCardList().get(card).applyCard(this, g);
		cardList.remove(card);
		updateAllViews();
	}


	/**Directly moves to the given square by not passing any other square and calls the square's arrival_proc.
	 * @param arrivedSqName Square to be arrived
	 * @param g The game that the player is in.
	 * @modifies the player THIS,The square given as input.
	 * @requires that the given square is not null
	 */
	public void arriveDirectly2Square(String arrivedSqName,Game g) {
		Square arrivedSq=g.getSq(arrivedSqName);
		arrivedSq.addPlayerToSquare(this);
		this.getLocation().removePlayerFromSquare(this);
		this.getLocation().updateAllViews();		
		this.setLocation(arrivedSq);
		arrivedSq.arrival_proc(this,g);

	}
	/**Directly moves to the given square by not passing any other square and NOT calling the the square's arrival_proc.
	 * @param arrivedSqName Square to be arrived
	 * @param g The game that the player is in.
	 * @modifies the player THIS,The square given as input.
	 * @requires that the given square is not null
	 */
	public void arriveDirectlyForHolland(String arrivedSqName,Game g) {
		Square arrivedSq=g.getSq(arrivedSqName);
		arrivedSq.addPlayerToSquare(this);
		this.getLocation().removePlayerFromSquare(this);
		this.getLocation().updateAllViews();		
		this.setLocation(arrivedSq);

	}
	/**Directly moves to the given square by not passing any other square and calls the square's  arrival_proc.
	 * @param arrivedSqName Square to be arrived
	 * @param g The game that the player is in.
	 * @modifies the player THIS,The square given as input.
	 * @requires that the given square is not null
	 */
	public void arriveDirectly2Square(Square arrivedSq,Game g) {
		arrivedSq.addPlayerToSquare(this);
		this.getLocation().removePlayerFromSquare(this);
		this.getLocation().updateAllViews();		
		this.setLocation(arrivedSq);
		arrivedSq.arrival_proc(this,g);

	}
	/**Moves to the given square by passing any other square on the way and calling their passing_procs.
	 * Finally it do call the target square's arrival_proc.
	 * @param targetSq Square to be arrived
	 * @param g The game that the player is in.
	 * @modifies the player THIS,The square given as input.
	 * @requires that the given square is not null
	 */
	public void move2SquareWithTarget(Square targetSq, Game g){ //moves to square and arrives there
		Dice d=g.getTheDice();
		Square startSq=getLocation();
		Square ts=startSq.next_square(d, this);
		while(ts!=targetSq){
			ts.passing_proc(this, g);
			ts=ts.next_square(d, this);
		}
		arriveDirectly2Square(ts, g);
	}
	/**Moves according to the dice by passing any other square on the way and calling their passing_procs.
	 * Finally it do call the target square's arrival_proc.
	 * @param g The game that the player is in.
	 * @modifies the player THIS,The square given as input.
	 * @requires that the game is not null
	 */
	public void move2SquareWithDice( Game g){ //moves to square and arrives there
		Dice d=g.getTheDice();
		int n=d.getTotalDice(); 
		Square temp=getLocation();
		for(int i=1;i<n;i++){
			temp=temp.next_square(d, this);
			temp.passing_proc(this, g);
		}
		temp=temp.next_square(d, this);
		arriveDirectly2Square(temp, g);
	}
	/**Directly moves to the square to be selected.
	 * @param g The game that the player is in.
	 * @modifies the player THIS,The square given as input.
	 * @requires that the given game is not null
	 */
	public void goAnyPlace(Game game) {
		DialogGenerator.GoAnySquareDialog(this,game);
		ConsoleGenerator.write2Console("Go anywhere is done");
	}
	/**Directly moves to the jail and finishes the player's turn
	 * @param g The game that the player is in.
	 * @modifies the player THIS,The Game
	 * @requires that the game is not null
	 */
	public void go2Jail(Game game){
		arriveDirectly2Square("Jail",game);
		setInJail(true);
		turnInJail=0;
		game.getTheDice().finishTurn();
		game.finishTurn();

	}
	/**Asks the player whether he/she wants to penalty to get out from the jail
	 * @param g The game that the player is in.
	 * @return true if the player pays, false if the player doesn't pay.
	 * @modifies the player THIS,The Game
	 * @requires that the player is in the JAIL
	 */
	public boolean askForJailFee(Game g) {
		// TODO asks for 50 dolars if the payment is made, then it returns true otherwise 0
		boolean result;
		if (getReceivableWorth()<50) return false;
		result=DialogGenerator.AskYesNoDialog("Pay50?","Do you want to give 50 dollars to get out of jail?");
		if(result==true){
			this.pay2Pool(50, g);
		}
		return result;
	}
	/** Make the player pays to the pool
	 * @param amount of the money given.
	 * @param g the game player is in.
	 * @requires the game is not null
	 */
	public void pay2Pool(int amount,Game g){
		this.givePayment(amount,g);
		g.getTheTable().getPool().recievePayment(amount);
	}
	/**This function is called when the player gets out from the jail
	 * @modifies THIS
	 */
	public void getOutFromJail() {
		setInJail(false);
		setTurnInJail(0);
	}
	/**Checks the SpecialPricing card to be owned by THIS
	 * @return true if the player owns the card, otherwise false
	 */
	public boolean isSpecialPricing() {
		return getCardList().containsKey("Special Online Pricing");
	}

	/**Gets all the Deed squares that the player has as an array-list.
	 * @return the array-list of the deed-squares that the player owns.
	 */
	public ArrayList<deedSquare> getDeedSquares(){ //TODO remove game for this and the following three
		ArrayList<deedSquare> deeds = new ArrayList<deedSquare>();
		ArrayList<buyableSquare> buySq = getBuyableSquares();


		for (int i = 0; i < buySq.size(); i++) {
			if (buySq.get(i) instanceof deedSquare){
				deeds.add((deedSquare) buySq.get(i));
			}
		}
		return deeds;
	}
	/**Gets all the buyable squares that the player has as an array-list.
	 * @return the array-list of the buyable-squares that the player owns.
	 */
	public ArrayList<buyableSquare> getBuyableSquares(){
		ArrayList<buyableSquare> buySq = new ArrayList<buyableSquare>();
		for (String propNames : getPropertyList().keySet()) {
			if (getPropertyList().get(propNames) instanceof buyableSquare){
				buySq.add(getPropertyList().get(propNames));
			}
		}
		return buySq;	
	}
	/**Gets all the Deed squares that the player has as an array-list.
	 * @return the array-list of the deed-squares that the player owns.
	 */
	public ArrayList<buyableSquare> getBuildableSquares(){
		ArrayList<buyableSquare> buildableSqs = new ArrayList<buyableSquare>();
		ArrayList<buyableSquare> buySq = getBuyableSquares();


		for (int i = 0; i < buySq.size(); i++) {
			if (! (buySq.get(i) instanceof utilitySquare)){
				buildableSqs.add(buySq.get(i));
			}
		}
		return buildableSqs;

	}
	/**Gets all the Deed squares that the player mortgaged as an array-list.
	 * @return the array-list of the deed-squares that the player mortgaged.
	 */
	public ArrayList<buyableSquare> getMortgagedSquares(){
		ArrayList<buyableSquare> mortSqs = new ArrayList<buyableSquare>();
		for (String propNames : getMortgagedPropertyList().keySet()) {
			if (getMortgagedPropertyList().get(propNames) instanceof buyableSquare){
				mortSqs.add(getMortgagedPropertyList().get(propNames));
			}
		}
		return mortSqs;	

	}
	/**Gets all the Deed squares which has house built that the player has as an array-list.
	 * @return the array-list of the deed-squares which has house that the player owns.
	 */
	public ArrayList<deedSquare> getHouseSquares(){
		ArrayList<deedSquare> houseSq = new ArrayList<deedSquare>();
		for (String propNames : getPropertyList().keySet()) {
			if (getPropertyList().get(propNames) instanceof deedSquare){
				deedSquare dSq = (deedSquare) getPropertyList().get(propNames);
				if(dSq.hasHouse())
					houseSq.add(dSq);
			}
		}
		return houseSq;	
	}

	public boolean isCanRoll() {
		return canRoll;
	}
	public void setCanRoll(boolean canRoll) {
		this.canRoll = canRoll;
		updateAllViews();
	}
	public int getPlayerNo() {
		return playerNo;
	}
	public String getPlayerName() {
		return playerName;
	}

	public boolean isInJail() {
		return isInJail;
	}

	public void setInJail(boolean isInJail) {
		this.isInJail = isInJail;
		updateAllViews();
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
		updateAllViews();
	}

	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
		ConsoleGenerator.write2Console("The balance of "+this.getPlayerName()+" is set to "+balance);
		updateAllViews();
	}

	public void setBalanceDebug(int balance) {
		// TODO Auto-generated method stub
		this.balance = balance;
		ConsoleGenerator.write2Console("The balance of "+this.getPlayerName()+" is set to "+balance);
		updateAllViews();
	}

	public HashMap<String,buyableSquare> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(HashMap<String,buyableSquare> propertyMap) {
		this.propertyList = propertyMap;
		updateAllViews();
	}

	public HashMap<String,buyableSquare> getMortgagedPropertyList() {
		return mortgagedPropertyList;
	}
	public void setMortgagedPropertyList(HashMap<String,buyableSquare> mortgagedPropertyMap) {
		this.mortgagedPropertyList = mortgagedPropertyMap;
		updateAllViews();
	}
	public Square getLocation() {
		return location;
	}

	public void setLocation(Square location) {
		this.location = location;
		if (location==null){
			this.trackNumber=0;
			ConsoleGenerator.write2Console("The player No:" + this.playerNo+". "+this.playerName+" has moved to NULL");
			updateAllViews();
		}
		else{
			this.trackNumber= location.getTrackNumber();
			ConsoleGenerator.write2Console("The player No:" + this.playerNo+". "+this.playerName+" has moved to "+location.getName()+".");
			updateAllViews();
		}


	}
	public void setLocationDebug(Square location) {
		location.addPlayerToSquare(this);
		getLocation().removePlayerFromSquare(this);	
		setLocation(location);
		updateAllViews();
	}
	public HashMap<String,Card> getCardList() {
		return cardList;
	}


	public void setCardList( HashMap<String,Card> cardList) {
		this.cardList = cardList;
		updateAllViews();
	}
	public boolean isBankrupt() {
		return isBankrupt;
	}
	public void setBankrupt(boolean isBankrupt) {
		this.isBankrupt = isBankrupt;
		updateAllViews();
	}
	public boolean isTakingTurn() {
		return isTakingTurn;
	}
	public void setTakingTurn(boolean isTakingTurn) {
		this.isTakingTurn = isTakingTurn;
		updateAllViews();
	}
	public boolean isCompedRoom() {
		return getCardList().containsKey("Comped Room");
	}

	public boolean isGetOutOfJailCard() {
		return getCardList().containsKey("Get Out Of Jail");
	}
	public int getTurnInJail() {
		return turnInJail;
	}

	public void setTurnInJail(int turnInJail) {
		this.turnInJail = turnInJail;
		updateAllViews();
	}

	public boolean isReverseMoving() {
		return reverseMoving;
	}
	public void setReverseMoving(boolean reverseMoving) {
		this.reverseMoving = reverseMoving;
		updateAllViews();
	}

	public void addView(intSq i){
		interfaceArray[0]=i;
	}
	public synchronized void updateAllViews(){
		interfaceArray[0].updateView();
	}


	public ArrayList<Stock> getGeneralRadioStocks() {
		return GeneralRadioStocks;
	}


	public void setGeneralRadioStocks(ArrayList<Stock> generalRadioStocks) {
		GeneralRadioStocks = generalRadioStocks;
		updateAllViews();
	}


	public ArrayList<Stock> getUnitedRailwaysStocks() {
		return UnitedRailwaysStocks;
	}


	public void setUnitedRailwaysStocks(ArrayList<Stock> unitedRailwaysStocks) {
		UnitedRailwaysStocks = unitedRailwaysStocks;
		updateAllViews();
	}


	public ArrayList<Stock> getNationalUtilitiesStocks() {
		return NationalUtilitiesStocks;
	}


	public void setNationalUtilitiesStocks(ArrayList<Stock> nationalUtilitiesStocks) {
		NationalUtilitiesStocks = nationalUtilitiesStocks;
		updateAllViews();
	}


	public ArrayList<Stock> getAmericanMotorsStocks() {
		return AmericanMotorsStocks;
	}


	public void setAmericanMotorsStocks(ArrayList<Stock> americanMotorsStocks) {
		AmericanMotorsStocks = americanMotorsStocks;
		updateAllViews();
	}


	public ArrayList<Stock> getAlliedSteamshipsStocks() {
		return AlliedSteamshipsStocks;
	}



	public ArrayList<Stock> getAllStocks() {
		return AllStocks;
	}


	public void setAllStocks(ArrayList<Stock> allStocks) {
		AllStocks = allStocks;
	}


	public void setAlliedSteamshipsStocks(ArrayList<Stock> alliedSteamshipsStocks) {
		AlliedSteamshipsStocks = alliedSteamshipsStocks;
		updateAllViews();
	}


	public ArrayList<Stock> getMotionPicturesStocks() {
		return MotionPicturesStocks;
	}


	public void setMotionPicturesStocks(ArrayList<Stock> motionPicturesStocks) {
		MotionPicturesStocks = motionPicturesStocks;
		updateAllViews();
	}

	public boolean hasStock(){
		if (AllStocks.size()==0)
			return false;
		return true;
	}


	public ArrayList<Stock> getMortgagedStocks() {
		return MortgagedStocks;
	}


	public void setMortgagedStocks(ArrayList<Stock> mortgagedStocks) {
		MortgagedStocks = mortgagedStocks;
	}



}
