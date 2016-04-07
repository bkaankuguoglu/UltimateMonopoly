package card;

import java.util.ArrayList;
import java.util.Collections;


import java.util.HashMap;

import backend.Game;
import backend.Player;

/**
 * This Class is a class that simply holds two different decks, in other words, ArrayLists composed of Card instances.
 * These decks are chanceCardList and communityChestCardList. In addition, the Class Deck also holds a HashMap that
 * keeps all the card by their names and their Card instances. The class has methods for generating decks, shuffling decks
 * drawing cards and filtering the cards in the deck.
 *  
 *  @author Berk Kaan Kuguoglu
 *  @version     %I%, %G%
 */



public class Deck {
	private ArrayList<Card> chanceCardList;
	private ArrayList<Card> communityChestCardList;
	private HashMap<String,Card> cardMap;
	
	/**
	 * Constructor for Deck Class that holds two different decks, namely chance card deck and community chest card deck 
	 * and also the HashMap of card by their names as keys in the map.
	 * 
	 */		
	
	
	public Deck() {
		// TODO Auto-generated constructor stub
		generateDeck();
		cardMap = getCardMap();
	}
	
	/**
	 * Draws a chance card from the deck
	 * 
	 * @return c Card instance from the ArrayList chanceCardList in the deck
	 * @requires the deck is created and it has chance cards in its field chanceCardList
	 * @modifies shuffles the deck to provide randomness for drawing card on the following turns
	 * @effects Card instance c is returned and the deck is shuffled
	 * 
	 */	
	
	public Card drawChanceCard(){
		Card c = chanceCardList.get(0);
		shuffleDeck();
		return c;
		
	}

	/**
	 * Draws a community chest card from the deck
	 * 
	 * @return c Card instance from the ArrayList communityChestCardList in the deck
	 * @requires the deck is created and it has chance cards in its field communityChestCardList
	 * @modifies shuffles the deck to provide randomness for drawing card on the following turns
	 * @effects Card instance c is returned and the deck is shuffled
	 * 
	 */	
	public Card drawCommunityChanceCard(){
		Card c = communityChestCardList.get(0);
		shuffleDeck();
		return c;
		
	}
	
	/**
	 * Generates a list of cards that is only composed of chance cards
	 * 
	 * @requires nothing
	 * @modifies nothing
	 * @effects creates a chance card deck by creating numerous instances of Cards and adding them to chanceCardList
	 * 
	 */	
	
	public void generateChanceCardDeck(){
		_BusTicket busTicket = new _BusTicket("Bus Ticket",0,0,true);
		_ChangingLanes changingLanes = new _ChangingLanes("Changing Lanes",1,0,false);
		_EntertainmentRocks entertainmentRocks = new _EntertainmentRocks("Entertainment Rocks",2,0,false);
		
		/*
		Phase I: 
		
		Hurricane hurricane = new Hurricane("Hurricane",0,0,false);
		MardiGras mardiG = new MardiGras("Mardi Gras",1,0,false);
		PropertyTax propTax = new PropertyTax("Property Tax", 2, 0, false);
		GetOutOfJail getOutOfJail = new GetOutOfJail("Get Out Of Jail",13,0,true);
		
		
		Advance2StockExchange advance2StockEx = new Advance2StockExchange("Advance to Stock Exchange",3,0,false);
		AssetsSeized assetsSeized = new AssetsSeized("Assets Seized",4,0,false);
		Chairperson chairperson = new Chairperson("Chairperson",5,0,false);
		ChangingLanes1 changingLanes1 = new ChangingLanes1("Changing Lanes",6,0,false);
		ChangingLanes2 changingLanes2 = new ChangingLanes2("Changing Lanes",7,0,false);
		CompedRoom compRoom = new CompedRoom("Comped Room",8,0,true);
		ConsultancyFee consFee = new ConsultancyFee("Consultancy Fee",9,0,false);
		ExcellentAccounting ExcellAcc = new ExcellentAccounting("Excellent Accounting",10,0,true);
		ForeClosedPropertySale foreClosedPropSale = new ForeClosedPropertySale("Foreclosed Property Sale",11,0,true);
		GeneralRepairs generalRep = new GeneralRepairs("General Repairs",12,0,false);
		
		GetRolling getRolling = new GetRolling("Get Rolling",14,0,false);
		GoBackSpace goBack = new GoBackSpace("Go Back A Space",16,0,false);
		GoToJail go2Jail = new GoToJail("Go To Jail",17,0,false);
		HeyTaxi heyTaxi = new HeyTaxi("HEY! TAXI!!! *whistle*",18,0,false);
		Advance2NearestRailroad advance2NearestRR = new Advance2NearestRailroad("Advance to Nearest Railroad",19,0,false);
		Advance2NearestUtility advance2NearestUtil = new Advance2NearestUtility("Advance to Nearest Utility",20,0,false);
		Occupy occupy = new Occupy("Occupy",21,0,false);
		RenovationSuccess renoSucc = new RenovationSuccess("Renovation Success",22,0,false);
		LoanMature loanMature = new LoanMature("Loan Matures",23,0,false);
		RollTheDice rollTheDice = new RollTheDice("Roll the Dice",24,0,false);
		SchoolFees schFees = new SchoolFees("School Fees",25,0,false);
		TaxiWars taxiWars = new TaxiWars("Taxi Wars are not Fare",26,0,false);
		ZeroDollarsDown zeroDollarsD = new ZeroDollarsDown("Zero Dollars Down!",27,0,true);
		WinTheMarathon wtMarathon = new WinTheMarathon("Win the Marathon",28,0,false);	
		SocialMediaFail socMedFail = new SocialMediaFail("Social Media Fail!",29,0,false);
		Advance2SqueezePlay advance2SqueezePl = new Advance2SqueezePlay("Advance to Squeeze Play",30,0,false);
		*/
		
		chanceCardList=new ArrayList<Card>();
	
		chanceCardList.add(busTicket);
		chanceCardList.add(entertainmentRocks);
		chanceCardList.add(changingLanes);
		
		/*
		chanceCardList.add(hurricane);
		chanceCardList.add(mardiG);
		chanceCardList.add(propTax);
		chanceCardList.add(getOutOfJail);
		
		chanceCardList.add(advance2NearestRR);
		chanceCardList.add(advance2NearestUtil);
		chanceCardList.add(advance2StockEx);
		chanceCardList.add(advance2SqueezePl);
		chanceCardList.add(compRoom);
		chanceCardList.add(foreClosedPropSale);
		chanceCardList.add(ExcellAcc);
		chanceCardList.add(consFee);
		chanceCardList.add(changingLanes1);
		chanceCardList.add(changingLanes2);
		chanceCardList.add(chairperson);
		chanceCardList.add(assetsSeized);
		chanceCardList.add(generalRep);
		chanceCardList.add(getRolling);
		chanceCardList.add(go2Jail);
		chanceCardList.add(heyTaxi);
		chanceCardList.add(goBack);
		chanceCardList.add(occupy);
		chanceCardList.add(zeroDollarsD);
		chanceCardList.add(schFees);
		chanceCardList.add(loanMature);
		chanceCardList.add(wtMarathon);
		chanceCardList.add(taxiWars);
		chanceCardList.add(renoSucc);
		chanceCardList.add(rollTheDice);
		chanceCardList.add(socMedFail);
*/
	}
	
	/**
	 * Generates a list of cards that is only composed of community chest cards
	 * 
	 * @requires nothing
	 * @modifies nothing
	 * @effects creates a community chest card deck by creating numerous instances of Cards and adding them to communityChestCardList
	 * 
	 */	
	
	public void generateCommunityChestCardDeck(){
		
		_GeneralRepairs generalRepairs = new _GeneralRepairs("General Repairs", 1, 0, false);
		_InheritStock inheritStock = new _InheritStock("Inherit Stock", 1, 1, false);
		_VehicleImpounded vehicleImpounded = new _VehicleImpounded("Vehicle Impounded.java", 1, 2, false);
		/*
		HouseCondemned houseCond = new HouseCondemned("House Condemned",0,1,false);
		AssessedStreetRepairs assessedStreetRep = new AssessedStreetRepairs("Assessed for Street Repairs",1,1,false);
		SpecialOnlinePricing sOnlinePric = new SpecialOnlinePricing("Special Online Pricing", 2, 1, true);
		GoToJail go2Jail = new GoToJail("Go To Jail",7,1,false);

		Advance2StockExchange advance2StockEx = new Advance2StockExchange("Advance to Stock Exchange",3,1,false);
		ChangingLanes1 changingLanes1 = new ChangingLanes1("Changing Lanes",4,1,false);
		ChangingLanes2 changingLanes2 = new ChangingLanes2("Changing Lanes",5,1,false);
		GetOutOfJail getOutOfJail = new GetOutOfJail("Get Out Of Jail",6,1,true);
		
		HappyBirthday HappyBday = new HappyBirthday("Happy Birthday",8,1,false);
		Inherit inherit = new Inherit("You Inherit $100",9,1,false);
		ReverseRent revRent = new ReverseRent("Reverse Rent",10,1,true);
		MovingExperience MovExp = new MovingExperience("A Moving Experience",11,1,false);
		RollOne roll3 = new RollOne("Roll Three",12,1,false);
		BargainBusiness bargainBus = new BargainBusiness("Bargain Business",13,1,false);
		Tornado tornado = new Tornado("Tornado",14,1,false);
		DoctorsFee docsFee = new DoctorsFee("Doctor's Fee",15,1,false);
		April15TaxesDue april15Taxes = new April15TaxesDue("April 15, Taxes Due!",16,1,false);
*/		

		communityChestCardList=new ArrayList<Card>();
		
		communityChestCardList.add(generalRepairs);
		communityChestCardList.add(inheritStock);
		communityChestCardList.add(vehicleImpounded);
		/*
		communityChestCardList.add(houseCond);
		communityChestCardList.add(sOnlinePric);
		communityChestCardList.add(assessedStreetRep);
		communityChestCardList.add(go2Jail);
	
		communityChestCardList.add(april15Taxes);
		communityChestCardList.add(bargainBus);
		communityChestCardList.add(advance2StockEx);
		communityChestCardList.add(changingLanes1);
		communityChestCardList.add(changingLanes2);
		communityChestCardList.add(getOutOfJail);
		communityChestCardList.add(HappyBday);
		
		communityChestCardList.add(inherit);
		communityChestCardList.add(MovExp);
		communityChestCardList.add(revRent);
		communityChestCardList.add(docsFee);	
		communityChestCardList.add(tornado);
		communityChestCardList.add(roll3);
*/		
	}
	
	/**
	 * Generates a deck from previously generated chanceCardList and communityChestCardList
	 * 
	 * @requires nothing
	 * @modifies nothing
	 * @effects creates a deck by generating chanceCardList and communityChestCardList
	 * 
	 */	
	
	public void generateDeck(){
		generateChanceCardDeck();
		generateCommunityChestCardDeck();
	
		this.shuffleDeck();
	}
	
	/**
	 * shuffles the deck 
	 * 
	 * @requires there are Card instances in the deck that are previously created
	 * @modifies communityChestCardList chanceCardList
	 * @effects shuffles the deck and changes the order of both chanceCardList and communityChestCardList
	 * 
	 */	
	public void shuffleDeck(){
		Collections.shuffle(communityChestCardList);
		Collections.shuffle(chanceCardList);
		
	}
	
	/**
	 * Returns the list of Card instances that are both chance cards and keepable
	 * 
	 * @return keepableChanceCards ArrayList of Strings that are names of the cards are keepable
	 * @requires the deck is already created and it has chance cards in it
	 * @modifies nothing
	 * @effects the list of Card instances that are both chance cards and keepable are returned
	 * 
	 */	

	public ArrayList<String> getKeepableChanceCards(){
		ArrayList<String> keepableChanceCards = new ArrayList<String>();
		for (int i = 0; i < chanceCardList.size(); i++) {
			Card curCard = chanceCardList.get(i);
			if (curCard.isKeepable())
				keepableChanceCards.add(curCard.getCardName());
		}
		
		return keepableChanceCards;
	}
	

	/**
	 * Returns the list of Card instances that are both community cards and keepable
	 * 
	 * @return keepableCCCards ArrayList of Strings that are names of the cards are keepable
	 * @requires the deck is already created and it has community chest cards in it
	 * @modifies nothing
	 * @effects the list of Card instances that are both community chest cards and keepable are returned
	 * 
	 */	
	public ArrayList<String> getKeepableCommunityChestCards(){
		ArrayList<String> keepableCCCards = new ArrayList<String>();
		for (int i = 0; i < communityChestCardList.size(); i++) {
			Card curCard = communityChestCardList.get(i);
			if (curCard.isKeepable())
				keepableCCCards.add(curCard.getCardName());
		}
		
		return keepableCCCards;
		
	}
	

	/**
	 * Returns a HashMap of all card from the Arraylists chanceCardList and communityChestCardList created
	 * 
	 * @return cardMap HashMap of all card in the deck
	 * @requires chanceCardList and communityChestCardList to be created
	 * @modifies nothing
	 * @effects the HashMap of all Card instances are returned
	 * 
	 */	
	
	public HashMap<String,Card> getCardMap(){
		HashMap<String,Card> cardMap = new HashMap<String,Card>();
		
		for (int i = 0; i < communityChestCardList.size(); i++) {
			Card curCard = communityChestCardList.get(i);
			cardMap.put(curCard.getCardName(), curCard);		
	}
		for (int i = 0; i < chanceCardList.size(); i++) {
			Card curCard = chanceCardList.get(i);
			cardMap.put(curCard.getCardName(), curCard);		
	}


		return cardMap;
	}
	

	/**
	 * Looks up a specific card in HashMap cardMap by its name
	 * 
	 * @param cName name of the Card to be looked up
	 * @return c the card that is looked up
	 * @requires cardMap is created and cName is a name of a Card instance in cardMap
	 * @modifies nothing
	 * @effects The Card instance c that is looked up in the HashMap cardMap is returned
	 * 
	 */	
	
	public Card getCard(String cName){
		Card c = cardMap.get(cName);
		return c;
		
	}
	
}
