package boardrelated;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import backend.ConsoleGenerator;
import backend.Player;
import card.Card;

/**
 * This class mainly acts as a main abstract type keep different types of squares on it. It's like a depot 
 * Board class mainly acts as a main abstract type keep different types of squares on it. It's like a depot 
 * that contains squares and connects them to each other.
 * 
 * Implementation-Specific: Board class keeps the different squares on it using an arrayList. It also has different
 * fields to indicate how many tracks there are on the board. It basically have the information that keeps
 * squares, tracks, colors and different groups.
 * 
 * It acts as a storage place in which everything is connected in a certain way.
 * 
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 *  
 */
public class Board{
	//COLORS:

	//SqueezePlay, Bus, Subway, Go, Chance, Com, Payday, Bonus, StockExch, Reverse, TaxRef, FreePark,
	//IncomeTax, LuxuryTax, HollandTunnels, GoToJail, BirthdayGift, RollOnce, Jail :0

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




	private String name;
	private int totalPlayers;
	private HashMap<String,Square> squares = new HashMap<String,Square>();
	private ArrayList<Square> squareArrayList = new ArrayList<Square>();
	private static int track1 = 1;
	private static int track2 = 2;
	private static int track3 = 3;

	/** Constructor that creates an instance of a board. It does not only create but also initiates the
	 * board by creating the squares and setting the squareArrayList.
	 * 
	 * @param name that indicates the name of the board
	 * @param totalPlayers number of players that are on the board
	 * 
	 * @modifies the squareArrayList. Fills it up with squares
	 */

	public Board(String name,int totalPlayers) {
		//ConsoleGenerator.write2Console("Board.java2: "+totalPlayers);
		this.name=name;
		this.totalPlayers=totalPlayers;
		initiateSquareArrayList();
		squares=getTheHash(squares);
		fillTheColorGroupArrayList();//Creates the color groups!

	}

	/**
	 * this method connects squares that are inside the squareArrayList.
	 * initiateSquareArrayList method connects squares that are inside the squareArrayList.
	 * 
	 * @requires squareArrayList to be initiated
	 * @modifies the squares' nexts and prevs that are in squareArrayList
	 */
	public void initiateSquareArrayList(){
		getSquareArrayListHelper();
		for(int i=0; i<56;i++){
			int ind1 = i;
			int ind2= (i==55) ?0 :i+1;
			Square temp1 = squareArrayList.get(ind1) ;
			Square temp2 = squareArrayList.get(ind2);
			//System.out.println(squareArrayList.get(ind1).getName()+" connected to the "+squareArrayList.get(ind2).getName());
			temp1.setNxSq(temp2);
			temp2.setPrSq(temp1);
		}

		for(int j=56; j<94; j++){
			int ind1 = j;
			int ind2= (j==93) ?56 :j+1;


			Square temp1 = squareArrayList.get(ind1) ;

			Square temp2 = squareArrayList.get(ind2);

			temp1.setNxSq(temp2);
			temp2.setPrSq(temp1);
		}
		for(int k=94; k<116; k++){
			int ind1 = k;
			int ind2= (k==115) ?94 :k+1;
			Square temp1 = squareArrayList.get(ind1) ;
			Square temp2 = squareArrayList.get(ind2);

			temp1.setNxSq(temp2);
			temp2.setPrSq(temp1);
		}


		//Railroads
		RailroadTrackSquare rail1 = (RailroadTrackSquare) squareArrayList.get(7);
		Square temp1 =  squareArrayList.get(60);
		Square temp2 =  squareArrayList.get(61);
		temp1.setNxSq(rail1);
		temp2.setPrSq(rail1);

		rail1.setPrSq(squareArrayList.get(60));
		rail1.setNxSq(squareArrayList.get(61));
		rail1.setPrSq2(squareArrayList.get(6));
		rail1.setNxSq2(squareArrayList.get(8));
		//end of 1

		RailroadTrackSquare rail2 = (RailroadTrackSquare) squareArrayList.get(35);
		Square temp3 =  squareArrayList.get(79);
		Square temp4 =  squareArrayList.get(80);
		temp3.setNxSq(rail2);
		temp4.setPrSq(rail2);

		rail2.setPrSq(squareArrayList.get(79));
		rail2.setNxSq(squareArrayList.get(80));
		rail2.setPrSq2(squareArrayList.get(34));
		rail2.setNxSq2(squareArrayList.get(36));
		//end of 2

		RailroadTrackSquare rail3 = (RailroadTrackSquare) squareArrayList.get(70);
		Square temp5 =  squareArrayList.get(102);
		Square temp6 =  squareArrayList.get(103);
		temp5.setNxSq(rail3);
		temp6.setPrSq(rail3);

		rail3.setPrSq(squareArrayList.get(69));
		rail3.setNxSq(squareArrayList.get(71));
		rail3.setPrSq2(squareArrayList.get(102));
		rail3.setNxSq2(squareArrayList.get(103));
		//end of 3

		RailroadTrackSquare rail4 = (RailroadTrackSquare) squareArrayList.get(89);
		Square temp7 =  squareArrayList.get(113);
		Square temp8 =  squareArrayList.get(114);
		temp7.setNxSq(rail4);
		temp8.setPrSq(rail4);

		rail4.setPrSq(squareArrayList.get(88));
		rail4.setNxSq(squareArrayList.get(90));
		rail4.setPrSq2(squareArrayList.get(113));
		rail4.setNxSq2(squareArrayList.get(114));
		//end of 4
	}

	/**
	 * this method basically constructs all the squares that are on an instance of a board
	/**
	 * getSquareArrayListHelper() basically constructs all the squares that are on an instance of a board
	 * and then adds them to the board's square list.
	 * 
	 * @requires squareArrayList to be not null
	 * @modifies squareArrayList by adding all the constructed squares in it
	 */


	public void getSquareArrayListHelper(){
		//////////////////TRACK NO 1
		Square subway = new Square("Subway", 1, track1, 0, totalPlayers);
		deedSquare LakeSt = new deedSquare("Lake Street", 2, track1, 1, totalPlayers, 30, 1, 5, 15, 45, 80, 125, 625, 50);
		drawCardSquare Com1 = new drawCardSquare("Community 1", 3, track1, 0, totalPlayers, "Community");
		deedSquare NicolletAv = new deedSquare("Nicollet Avenue", 4, track1, 1,totalPlayers, 30, 1, 5,15 ,45, 80, 125, 625, 50);
		deedSquare HennepinAv = new deedSquare("Hennepin Avenue", 5, track1, 1, totalPlayers, 30, 3, 15, 45, 120, 240, 350, 850, 50);
		Square busTicket = new Square("Bus Ticket", 6, track1, 0, totalPlayers);
		cabSquare CabCom1 = new cabSquare("Checker Cab Co.", 7, track1, 22, totalPlayers, 300);
		RailroadTrackSquare readingRailroad = new RailroadTrackSquare("Reading Railroad",8, track1, 21, totalPlayers, 200, 25);
		deedSquare EsplanadeAv = new deedSquare("Esplanade Avenue", 9, track1, 2, totalPlayers, 90, 5, 25, 80, 225, 360, 600, 1000, 50);
		deedSquare CanalSt = new deedSquare("Canal Street", 10, track1, 2, totalPlayers, 90, 5, 25, 80, 225, 360, 600, 1000, 50);
		drawCardSquare Ch1 = new drawCardSquare("Chance 1", 11, track1, 0, totalPlayers, "Chance");
		Square cableCom = new Square("Cable Company", 12, track1, 0, totalPlayers);//dont know if it exists
		deedSquare MagazinSt = new deedSquare("Magazine Street", 13, track1, 2, totalPlayers, 120, 8, 40, 100, 300, 450, 600, 1100, 50);
		deedSquare BourbonSt = new deedSquare("Bourbon Street", 14, track1, 2, totalPlayers, 120, 8, 40, 100, 300, 450, 600, 1100, 50);
		hollandTunnelSquare hollandTunnel1 = new hollandTunnelSquare("Holland Tunnel 1", 15, track1,0,totalPlayers);
		AuctionSquare Auction = new AuctionSquare("Auction", 16, track1, 0, totalPlayers);
		deedSquare KatyFreeway = new deedSquare("Katy Freeway", 17, track1, 3, totalPlayers, 150, 11, 55, 160, 475, 650, 800, 1300, 100);
		deedSquare WestheimerRoad = new deedSquare("Westheimer Road", 18, track1, 3, totalPlayers, 150, 11, 55, 160, 475, 650, 800, 1300, 100);
		utilitySquare Internet = new utilitySquare("Internet", 19, track1, 29, totalPlayers, 150, 0);//Don't know if it exists
		deedSquare KibbyDrive = new deedSquare("Kibby Drive", 20, track1, 3, totalPlayers, 180, 14, 70, 200, 550, 750, 950, 1450, 100);
		deedSquare CullenBoulevard = new deedSquare("Cullen Boulevard", 21, track1, 3, totalPlayers, 180, 14, 70, 200, 550, 750, 950, 1450, 100);
		drawCardSquare Ch2 = new drawCardSquare("Chance 2", 22, track1, 0, totalPlayers, "Chance");
		cabSquare cabCom2 = new cabSquare("Black & White Cab Co.",23, track1, 22, totalPlayers, 300);
		deedSquare DekalbAv = new deedSquare("Dekalb Avenue", 24, track1, 4, totalPlayers, 210, 17, 85, 240, 670, 840, 1025, 1525, 100);
		drawCardSquare Com2 = new drawCardSquare("Community 2", 25, track1, 0, totalPlayers, "Community");
		deedSquare AndrewYoung = new deedSquare("Andrew Young Intl Avenue", 26, track1, 4, totalPlayers, 210, 17, 85, 240, 670, 840, 1025, 1525, 100);
		deedSquare DecaturSt = new deedSquare("Decatur Street", 27, track1, 4, totalPlayers, 240, 20, 100, 300, 750, 925, 1100, 1600, 100);
		deedSquare Peachtree = new deedSquare("Peach Street", 28, track1, 4, totalPlayers, 240, 20, 100, 300, 750, 925, 1100, 1600, 100);
		paydaySquare payday = new paydaySquare("Pay Day Square", 29, track1, 0, totalPlayers);
		deedSquare RandolphSt = new deedSquare("Randolph Street", 30, track1, 5, totalPlayers, 270, 23, 115, 345, 825, 1010, 1180, 2180, 150);
		drawCardSquare Ch3 = new drawCardSquare("Chance 3", 31, track1, 0, totalPlayers, "Chance");
		deedSquare LakeShoreDrive = new deedSquare("Lake Shore Drive", 32, track1, 5, totalPlayers, 270, 23, 115, 345, 825, 1010, 1180, 2180, 150);
		deedSquare WackerDrive = new deedSquare("Wacker Drive", 33, track1, 5, totalPlayers, 300, 26, 130, 390, 900, 1100, 1275, 2275, 150);
		deedSquare MichiganAv = new deedSquare("Michigan Avenue", 34, track1, 5, totalPlayers, 300, 26, 130, 390, 900, 1100, 1275, 2275, 150);
		cabSquare cabCom3 = new cabSquare("Yellow Cab Co.",35, track1, 22, totalPlayers, 300);
		RailroadTrackSquare boRailroad = new RailroadTrackSquare("B&O Railroad",36, track1, 21, totalPlayers, 200, 25);
		drawCardSquare Com3 = new drawCardSquare("Community 3", 37, track1, 0, totalPlayers, "Community");
		deedSquare SouthTemple = new deedSquare("South Temple", 38, track1, 6, totalPlayers, 330, 32, 160, 470, 1050, 1250, 1500, 2500, 200);
		deedSquare WestTemple = new deedSquare("West Temple", 39, track1, 6, totalPlayers, 330, 32, 160, 470, 1050, 1250, 1500, 2500, 200);
		utilitySquare TrashCollector = new utilitySquare("Trash Collector", 40, track1, 24, totalPlayers, 150, 0);
		deedSquare NorthTemple = new deedSquare("North Temple", 41, track1, 6, totalPlayers, 360, 38, 170, 520, 1125, 1425, 1275, 1650, 200);
		deedSquare TempleSquare = new deedSquare("Temple Square", 42, track1, 6, totalPlayers, 360, 38, 170, 520, 1125, 1425, 1275, 1650, 200);
		go2jailSquare goToJail = new go2jailSquare("Go to Jail", 43, track1, 0, totalPlayers);
		deedSquare SouthStreet = new deedSquare("South Street", 44, track1, 7, totalPlayers, 390, 45, 210, 575, 1300, 1600, 1800, 3300, 250);
		deedSquare BroadStreet = new deedSquare("Broad Street", 45, track1, 7, totalPlayers, 390, 45, 210, 575, 1300, 1600, 1800, 3300, 250);
		deedSquare WalnutStreet = new deedSquare("Walnut Street", 46, track1, 7, totalPlayers, 420, 55, 225, 630, 1450, 1750, 2050, 3550, 250);
		drawCardSquare Com4 = new drawCardSquare("Community 4", 47, track1, 0, totalPlayers, "Community");
		deedSquare MarketStreet = new deedSquare("Market Street", 48, track1, 7, totalPlayers, 420, 55, 225, 630, 1450, 1750, 2050, 3550, 250);
		Square busTicket1 = new Square("Bus Ticket 1", 49, track1, 0, totalPlayers);
		utilitySquare SewageSystem = new utilitySquare("Sewage System", 50, track1, 25, totalPlayers, 150, 0);
		cabSquare cabCom4 = new cabSquare("Ute Cab Co.",51, track1, 22, totalPlayers, 300);
		birthdayGiftSquare birthdayGift = new birthdayGiftSquare("Birthday Gift", 52, track1, 0, totalPlayers);
		deedSquare MulhollandDrive = new deedSquare("Mulholland Drive", 53, track1, 8, totalPlayers, 450, 70, 350, 750, 1600, 1850, 2100, 3600, 300);
		deedSquare VenturaBoulevard = new deedSquare("Ventura Boulevard", 54, track1, 8, totalPlayers, 480, 80, 400, 825, 1800, 2175, 2550, 4050, 300);
		drawCardSquare Ch4 = new drawCardSquare("Chance 4", 55, track1, 0, totalPlayers, "Chance");
		deedSquare RodeoDrive = new deedSquare("Rodeo Drive", 56, track1, 8, totalPlayers, 510, 90, 450, 900, 2000, 2500, 3000, 4500, 300);

		//////////////////TRACK NO 2
		goSquare Go = new goSquare("Go Square", 57, track2, 0, totalPlayers);
		deedSquare MediterraneanAv = new deedSquare("Mediterranean Avenue", 58, track2, 9, totalPlayers, 60,  6 , 100, 150, 200, 400, 350, 700, 50);
		drawCardSquare Com5 = new drawCardSquare("Community 5", 59, track2, 0, totalPlayers, "Community");
		deedSquare BalticAv = new deedSquare("Baltic Avenue", 60, track2, 9, totalPlayers, 60 , 6, 100, 150, 200, 400, 350, 700, 50);
		incomeTaxSquare incomeTax = new incomeTaxSquare("Income Tax", 61, track2, 0, totalPlayers);
		//railroadTrackChangeSquare readingRailroadOtherSide = new railroadTrackChangeSquare("Reading Railroad Other Side",62, track2, 100, 0, 200, 0, 8 ,false);
		deedSquare OrientalAv = new deedSquare("Oriental Avenue", 62, track2, 10, totalPlayers, 100,  10, 100, 200, 300, 400, 350, 900, 70);
		drawCardSquare Ch5 = new drawCardSquare("Chance 5", 63, track2, 0, totalPlayers, "Chance");
		deedSquare VermontAv = new deedSquare("Vermont Avenue", 64 , track2, 10, totalPlayers, 100,  10, 100, 200, 300, 400, 350, 900, 70);
		deedSquare ConnecticutAv = new deedSquare("Connecticut Avenue", 65, track2, 10, totalPlayers, 120,  12, 120, 240, 480, 700, 550, 1000, 70);
		Square Jail = new Square("Jail", 66, track2, 0, totalPlayers);
		deedSquare StCharPl = new deedSquare("St. Charles Place", 67, track2, 11, totalPlayers, 140, 14, 120, 240, 480, 700, 550, 1300, 100);
		utilitySquare ElecCom1 = new utilitySquare("Electric Company 1", 68, track2, 23, totalPlayers, 150, 0);
		deedSquare StatesAv = new deedSquare("States Avenue", 69, track2, 11, totalPlayers, 140, 14, 120, 240, 480, 700, 550, 1300, 100);
		deedSquare VirginiaAv = new deedSquare("Virginia Avenue", 70, track2, 11, totalPlayers, 160, 16, 160, 320, 500, 750, 600, 1400, 100);
		//following
		RailroadTrackSquare PennsylvaniaRailroad = new RailroadTrackSquare("Pennsylvania Railroad",71, track2, 21, totalPlayers, 200, 25);
		deedSquare StJamesPl = new deedSquare("St. James Place", 72, track2, 12, totalPlayers, 180,  18, 180, 360, 600 , 800, 700 , 1500, 100);
		drawCardSquare Com6 = new drawCardSquare("Community 6", 73, track2, 0, totalPlayers, "Community");
		deedSquare TennesseeAv = new deedSquare("Tennessee Avenue", 74, track2, 12, totalPlayers, 180,  18, 180, 360, 600 , 800, 700 , 1500, 100);
		deedSquare NewYorkAv = new deedSquare("New York Avenue", 75, track2, 12, totalPlayers, 200,  20, 180, 360, 600 , 800, 700 , 1500, 100);
		Square FreeParking = new Square("Free Parking", 76, track2, 0, totalPlayers);
		deedSquare KentuckyAv = new deedSquare("Kentucky Avenue", 77, track2, 13, totalPlayers, 220,  22, 150, 200, 440, 1000, 800, 1600, 120);
		drawCardSquare Ch6 = new drawCardSquare("Chance 3", 78, track2, 0, totalPlayers, "Chance");
		deedSquare IndianaAv = new deedSquare("Indiana Avenue", 79, track2, 13, totalPlayers, 220,  22, 150, 200, 440, 1000, 800, 1600, 120);
		deedSquare Illinois = new deedSquare("Illinois Avenue",80, track2, 13, totalPlayers, 240, 24,  240, 480, 960, 1200, 1100, 1800,0);
		//railroadTrackChangeSquare boRailroadOtherSide = new railroadTrackChangeSquare("B&O Railroad Otherside",82, track2, 100, 0, 200, 0, 36,false);
		deedSquare AtlanticAv = new deedSquare("Atlantic Avenue", 81, track2, 14, totalPlayers, 220,  26, 150, 200, 440, 1000, 800, 1600, 120);
		deedSquare VentnorAv = new deedSquare("Ventnor Avenue", 82, track2, 14, totalPlayers, 260,  26, 200, 300, 450, 900, 1200, 1900, 120);
		utilitySquare WaterWorks = new utilitySquare("Water Works", 83, track2, 27, totalPlayers, 150, 0);
		deedSquare MarvinGardens = new deedSquare("Marvin Gardens", 84, track2, 14, totalPlayers, 280,  28, 280, 300, 450, 900, 1200, 1900, 120);
		rollOneSquare RollOne = new rollOneSquare("Roll Once Square", 85, track2, 0,totalPlayers);
		deedSquare PacificAv = new deedSquare("Pacific Avenue", 86, track2, 15, totalPlayers, 300,  30, 200, 600, 750, 900, 1200, 2000, 150);
		deedSquare NorthCarolinaAv = new deedSquare("North Carolina Avenue", 87, track2, 15, totalPlayers, 300,  30, 200, 600, 750, 900, 1200, 2000, 150);
		drawCardSquare Com7 = new drawCardSquare("Community 7", 88, track2, 0, totalPlayers, "Community");
		deedSquare PennsylvaniaAv = new deedSquare("Pennysylvania Avenue", 89, track2, 15, totalPlayers, 320,  32, 220, 650, 800, 900, 1250, 2100, 150);
		//following
		RailroadTrackSquare shortlineRailroad = new RailroadTrackSquare("Shortline Railroad",90, track2, 21, totalPlayers, 200, 25);
		drawCardSquare Ch7 = new drawCardSquare("Chance 7", 91, track2, 0, totalPlayers, "Chance");
		deedSquare ParkPlace = new deedSquare("Park Place", 92, track2, 16, totalPlayers, 350,  35, 300, 600, 900, 1220, 1800,2500, 150);
		luxuryTaxSquare LuxuryTax = new luxuryTaxSquare("Luxury Tax", 93, track2, 0, totalPlayers);
		deedSquare Boardwalk = new deedSquare("Boardwalk",94, track2, 16, totalPlayers, 400,  40, 400, 850, 1000, 1500, 2000, 2500, 150);

		//////////////////TRACK NO 3
		squezzePlaySquare SqueezePlay = new squezzePlaySquare("Squeeze Play", 95, track3, 0, totalPlayers);
		deedSquare TheEmbarCadero = new deedSquare("The Embarcadero", 96, track3, 17, totalPlayers, 210, 17, 85, 240, 475, 670, 1025, 1525, 100);
		deedSquare FishermansWharf = new deedSquare("Fisherman's Wharf", 97, track3, 17, totalPlayers, 250, 21, 105, 320, 780, 950, 1125, 1625, 100);
		utilitySquare TelCom1 = new utilitySquare("Telephone Company 1", 98, track3, 28, totalPlayers, 150, 0);
		drawCardSquare Com8 = new drawCardSquare("Community 8", 99, track3, 0, totalPlayers, "Community");
		deedSquare BeaconSt = new deedSquare("Beacon Street", 100, track3, 18, totalPlayers, 330, 30, 160, 470, 1050, 1250, 1500, 2500, 200);
		bonusSquare bonus = new bonusSquare("Bonus Square", 101, track3, 0, totalPlayers);
		deedSquare BolystonSt = new deedSquare("Bolyston Street", 102, track3, 18, totalPlayers, 330, 30, 160, 470, 1050, 1250, 1500, 2500, 200);
		deedSquare NewburySt = new deedSquare("Newbury Street", 103, track3, 18, totalPlayers, 380, 40, 185, 550, 1200, 1500, 1700, 2700, 200);
		//railroadTrackChangeSquare PennsylvaniaRailroadOtherSide = new railroadTrackChangeSquare("Pennsylvania Railroad Otherside",106, track3, 100, 0, 200, 0, 72,false);
		deedSquare FifthAv = new deedSquare("Fifth Avenue", 104, track3, 19, totalPlayers, 430, 60, 220, 650, 1500, 1800, 2100, 3600, 300);
		deedSquare MadisonAv = new deedSquare("Madison Avenue", 105, track3, 19, totalPlayers, 430, 60, 220, 650, 1500, 1800, 2100, 3600, 300);
		StockExchangeSquare StockExch = new StockExchangeSquare("Stock Exchange", 106, track3, 0, totalPlayers);
		deedSquare WallSt = new deedSquare("Wall Street", 107, track3, 19, totalPlayers, 500, 80, 300, 800, 1800, 2200, 2700, 4200, 300);
		taxRefundSquare taxRefund =  new taxRefundSquare("Tax Refund", 108, track3, 0,totalPlayers);
		utilitySquare GasCom = new utilitySquare("Gas Company 1", 109 , track3, 26, totalPlayers, 150, 0);
		drawCardSquare Ch8= new drawCardSquare("Chance 8", 110, track3, 0, totalPlayers, "Chance");
		deedSquare FloridaAv = new deedSquare("Florida Avenue", 111, track3, 20, totalPlayers, 130, 9, 45, 120, 350, 500, 700, 1200, 50);
		hollandTunnelSquare hollandTunnel2 = new hollandTunnelSquare("Holland Tunnel 2", 112, track3, 0, totalPlayers);
		deedSquare MiamiAv = new deedSquare("Miami Avenue", 113, track3, 20, totalPlayers, 130, 9, 45, 120, 350, 500, 700, 1200, 50);
		deedSquare BiscayneAv = new deedSquare("Biscayne Avenue", 114, track3, 20, totalPlayers, 150, 11, 55, 160, 475, 650, 800, 1300, 50);
		//railroadTrackChangeSquare shortlineRailroadOtherSide = new railroadTrackChangeSquare("Shortline Railroad Otherside",118, track1, 100, 0, 200, 0, 92,false);
		reverseDirectionSquare reverse = new reverseDirectionSquare("Reverse Direction", 115, track3, 0, totalPlayers);
		deedSquare LombardSt = new deedSquare("Lombard Street", 116, track3, 17, totalPlayers, 210, 17, 85, 240, 475, 670, 1025, 1525, 100);

		squareArrayList.add(subway);
		squareArrayList.add(LakeSt);
		squareArrayList.add(Com1);
		squareArrayList.add(NicolletAv);
		squareArrayList.add(HennepinAv);
		squareArrayList.add(busTicket);
		squareArrayList.add(CabCom1);
		squareArrayList.add(readingRailroad);
		squareArrayList.add(EsplanadeAv);
		squareArrayList.add(CanalSt);
		squareArrayList.add(Ch1);
		squareArrayList.add(cableCom);
		squareArrayList.add(MagazinSt);
		squareArrayList.add(BourbonSt);
		squareArrayList.add(hollandTunnel1);
		squareArrayList.add(Auction);
		squareArrayList.add(KatyFreeway);
		squareArrayList.add(WestheimerRoad);
		squareArrayList.add(Internet);
		squareArrayList.add(KibbyDrive);
		squareArrayList.add(CullenBoulevard);
		squareArrayList.add(Ch2);
		squareArrayList.add(cabCom2);
		squareArrayList.add(DekalbAv);
		squareArrayList.add(Com2);
		squareArrayList.add(AndrewYoung);
		squareArrayList.add(DecaturSt);
		squareArrayList.add(Peachtree);
		squareArrayList.add(payday);
		squareArrayList.add(RandolphSt);
		squareArrayList.add(Ch3);
		squareArrayList.add(LakeShoreDrive);
		squareArrayList.add(WackerDrive);
		squareArrayList.add(MichiganAv);
		squareArrayList.add(cabCom3);
		squareArrayList.add(boRailroad);
		squareArrayList.add(Com3);
		squareArrayList.add(SouthTemple);
		squareArrayList.add(WestTemple);
		squareArrayList.add(TrashCollector);
		squareArrayList.add(NorthTemple);
		squareArrayList.add(TempleSquare);
		squareArrayList.add(goToJail);
		squareArrayList.add(SouthStreet);
		squareArrayList.add(BroadStreet);
		squareArrayList.add(WalnutStreet);
		squareArrayList.add(Com4);
		squareArrayList.add(MarketStreet);
		squareArrayList.add(busTicket1);
		squareArrayList.add(SewageSystem);
		squareArrayList.add(cabCom4);
		squareArrayList.add(birthdayGift);
		squareArrayList.add(MulhollandDrive);
		squareArrayList.add(VenturaBoulevard);
		squareArrayList.add(Ch4);
		squareArrayList.add(RodeoDrive);

		squareArrayList.add(Go);
		squareArrayList.add(MediterraneanAv);
		squareArrayList.add(Com5);
		squareArrayList.add(BalticAv);
		squareArrayList.add(incomeTax);
		squareArrayList.add(OrientalAv);
		squareArrayList.add(Ch5);
		squareArrayList.add(VermontAv);
		squareArrayList.add(ConnecticutAv);
		squareArrayList.add(Jail);
		squareArrayList.add(StCharPl);
		squareArrayList.add(ElecCom1);
		squareArrayList.add(StatesAv);
		squareArrayList.add(VirginiaAv);
		squareArrayList.add(PennsylvaniaRailroad);
		squareArrayList.add(StJamesPl);
		squareArrayList.add(Com6);
		squareArrayList.add(TennesseeAv);
		squareArrayList.add(NewYorkAv);
		squareArrayList.add(FreeParking);
		squareArrayList.add(KentuckyAv);
		squareArrayList.add(Ch6);
		squareArrayList.add(IndianaAv);
		squareArrayList.add(Illinois);
		squareArrayList.add(AtlanticAv);
		squareArrayList.add(VentnorAv);
		squareArrayList.add(WaterWorks);
		squareArrayList.add(MarvinGardens);
		squareArrayList.add(RollOne);
		squareArrayList.add(PacificAv);
		squareArrayList.add(NorthCarolinaAv);
		squareArrayList.add(Com7);
		squareArrayList.add(PennsylvaniaAv);
		squareArrayList.add(shortlineRailroad);
		squareArrayList.add(Ch7);
		squareArrayList.add(ParkPlace);
		squareArrayList.add(LuxuryTax);
		squareArrayList.add(Boardwalk);

		squareArrayList.add(SqueezePlay);
		squareArrayList.add(TheEmbarCadero);
		squareArrayList.add(FishermansWharf);
		squareArrayList.add(TelCom1);
		squareArrayList.add(Com8);
		squareArrayList.add(BeaconSt);
		squareArrayList.add(bonus);
		squareArrayList.add(BolystonSt);
		squareArrayList.add(NewburySt);
		squareArrayList.add(FifthAv);
		squareArrayList.add(MadisonAv);
		squareArrayList.add(StockExch);
		squareArrayList.add(WallSt);
		squareArrayList.add(taxRefund);
		squareArrayList.add(GasCom);
		squareArrayList.add(Ch8);
		squareArrayList.add(FloridaAv);
		squareArrayList.add(hollandTunnel2);
		squareArrayList.add(MiamiAv);
		squareArrayList.add(BiscayneAv);
		squareArrayList.add(reverse);
		squareArrayList.add(LombardSt);	
	}

	//Light Pink:1
	//Light Green:2
	//Light Yellow:3
	//Light Pink:4
	//Bordeaux:5
	//Khaki:6
	//Beige:7
	//Dark red:8
	//Purple:9
	//Lilac:10
	//Pink:11
	//Orange:12
	//Red:13
	//Yellow:14
	//Green:15
	//Blue:16
	//White:17
	//Black:18
	//Grey:19
	//Brown:20


	/**
	 * This method method, when called, destroyes the highest level of constructions in a 
	 * color group. If there are no constructions on a deed, then it does nothing to that deed. 
	 * It detects the color group by the case number.
	 * 
	 * @param cases the integer that indicates one certain color group
	 * @modifies the constructions on a deed
	 */

	public void colorListForHurricane(int cases){
		for (int i=0; i<squareArrayList.size();i++){
			int temp=0;
			if(squareArrayList.get(i).getColorID()==cases && squareArrayList.get(i) instanceof deedSquare)
				if(((deedSquare) squareArrayList.get(i)).hasHouse()
						&&
						((deedSquare) squareArrayList.get(i)).getNumOfHouses()==1){
					((deedSquare) squareArrayList.get(i)).setHasHouse(false);
					((deedSquare) squareArrayList.get(i)).setNumOfHouses(0);
					ConsoleGenerator.write2Console("A house in "+ squareArrayList.get(i).getName()+" is destroyed in the hurricane.");
				}else if(((deedSquare) squareArrayList.get(i)).hasHouse()){
					temp = ((deedSquare) squareArrayList.get(i)).getNumOfHouses();
					((deedSquare) squareArrayList.get(i)).setNumOfHouses(temp-1);
					ConsoleGenerator.write2Console("A house in "+ squareArrayList.get(i).getName()+" is destroyed in the hurricane.");
					if(((deedSquare) squareArrayList.get(i)).hasHouse()){
						temp = ((deedSquare) squareArrayList.get(i)).getNumOfHouses();
						((deedSquare) squareArrayList.get(i)).setNumOfHouses(temp);
						ConsoleGenerator.write2Console("A house in "+ squareArrayList.get(i).getName()+" is destroyed in the hurricane.");
					}else if (((deedSquare) squareArrayList.get(i)).hasHotel()){
						((deedSquare) squareArrayList.get(i)).setHasHotel(false);
						((deedSquare) squareArrayList.get(i)).setNumOfHouses(4);
						ConsoleGenerator.write2Console("A hotel in "+ squareArrayList.get(i).getName()+" is destroyed in the hurricane.");
					}else if(((deedSquare) squareArrayList.get(i)).hasSkyscraper()){
						((deedSquare) squareArrayList.get(i)).setHasHotel(true);
						((deedSquare) squareArrayList.get(i)).setHasSkyscraper(false);
						ConsoleGenerator.write2Console("A skyscraper in "+ squareArrayList.get(i).getName()+" is destroyed in the hurricane.");

					}
				}}
	}


	/**
	 * This method calls the fillTheColorGroupArrayListHelper(deedSquare sq) method for each square in the 
	 * squareArrayList. 
	 * 
	 * @modifies the color group of each square
	 * 
	 */

	public void fillTheColorGroupArrayList(){
		for (int i=0; i<squareArrayList.size();i++){
			if(squareArrayList.get(i) instanceof deedSquare){
				fillTheColorGroupArrayListHelper((deedSquare) squareArrayList.get(i));
			}
		}
	}

	/**
	 * Implementation-Specific: Checks the color of each square of the squareArrayList with 
	 * the input square's color. If they are match, it adds the square from the array list to the input
	 * square's color group array list.
	 * 
	 * @param sq deedSquare whose color is going to be compared with the square from the squareArrayList
	 * @requires the square from the array list to be a deed square
	 * @modifies the color group of the input deedSquare
	 * 
	 */


	public void fillTheColorGroupArrayListHelper(deedSquare sq){
		for (int i=0; i<squareArrayList.size();i++){
			if(squareArrayList.get(i).getColorID()==sq.getColorID() && !sq.getName().equals(squareArrayList.get(i).getName())){
				if(squareArrayList.get(i) instanceof deedSquare){
					sq.addInColorGroup((deedSquare) squareArrayList.get(i));
				}
			}
		}
	}

	public ArrayList<Stock> giveAllTheStocks(){
		StockExchangeSquare temp = (StockExchangeSquare) this.getSquare("Stock Exchange");
		return temp.getAllStocks();
	}

	public ArrayList<Stock> AllUnpurchasedStocks(){
		StockExchangeSquare temp = (StockExchangeSquare) this.getSquare("Stock Exchange");
		return temp.getUnpurchasedStockList();
	}

	public StockCompany[] stockCompanies(){
		StockExchangeSquare temp = (StockExchangeSquare) this.getSquare("Stock Exchange");
		return temp.getCompanies();
	}

	public Stock getStock(String stockName){
		int m=0;
		for(int i=0; i<this.giveAllTheStocks().size();i++){
			Stock result = this.giveAllTheStocks().get(i);
			if(stockName.equals(result.getName())){ 
				m=i;
				return result;
			}
		}
		m=m/6;
		StockExchangeSquare tempSq = (StockExchangeSquare) this.getSquare("Stock Exchange");
		StockCompany tempCompany = tempSq.getCompanies()[m];
		Stock alternative = new Stock(tempCompany.getCompanyName(), tempCompany);
		return alternative;
	}

	/**
	 * This method takes in an empty hashmap and fills it up with name keys and square values.
	 * 
	 * @param h hashmap to be filled with "name, square" pairs.
	 * 
	 * @requires the input h to be not null
	 * @requires the input h to be empty
	 * @modifies the input hashmap h
	 * @return the filled hashmap h with "name, square" pairs
	 * 
	 */

	public HashMap<String,Square> getTheHash(HashMap<String,Square> h){
		for(int i=0; i<squareArrayList.size();i++){
			Square temp=squareArrayList.get(i);
			String tempName=temp.getName();
			h.put(tempName,temp);
		}	
		return h;
	}
	/*
	 * Prints the board out.
	 * 
	 */

	public void printBoard() {
		//TODO
	}

	/**
	 * Gives out the name of the board.
	 * 
	 * @return the name of the board.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the name of a board instance.
	 * 
	 * @param name The new name to be set to the baord.
	 * @modifies The name of the board instance.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gives out the square that is looked for with a string key.
	 * @param key Input whose value is the searched square.
	 * @return The square according to the key input.
	 */
	public Square getSquare(String key){
		return squares.get(key);
	}

	/**
	 * Changes the squares hashmap of a board instance.
	 * 
	 * @param squares The hashmap which is going to be set as the hashMap square list of a board instance.
	 * @modifies The board and its square hashmap.
	 */
	public void setSquares(HashMap<String, Square> squares) {
		this.squares = squares;
	}

	/**
	 * Changes the square array list of a board instance.
	 * @param squareArrayList The square arraylist that is going to be set as the new one.
	 * @modifies The board and its square array list. 
	 */
	public void setSquareArrayList(ArrayList<Square> squareArrayList) {
		this.squareArrayList = squareArrayList;
	}

	/**
	 * Gives out all the buyable squares from a board.
	 * @return All the buyable square types on a board instance.
	 */
	public ArrayList<String> getAllBuyableSquares() {
		// TODO Auto-generated method stub
		ArrayList<String> byb = new ArrayList<String>();
		for (int i = 0; i < squareArrayList.size(); i++) {
			Square sq = squareArrayList.get(i);
			if (sq instanceof buyableSquare) byb.add(sq.getName());
		}
		return byb;
	}

	public ArrayList<buyableSquare> getAllBuyableSquares2() {
		// TODO Auto-generated method stub
		ArrayList<buyableSquare> byb = new ArrayList<buyableSquare>();
		for (int i = 0; i < squareArrayList.size(); i++) {
			if (squareArrayList.get(i) instanceof buyableSquare){
				byb.add((buyableSquare) squareArrayList.get(i));
			}
		}
		return byb;
	}

	/**
	 * Gives out the square hashmap by a key not as a string but as an integer.
	 * @return The square hashmap.
	 */

	public HashMap<Integer, Square> squareMapByNumber(){
		HashMap<Integer, Square> newMap = new HashMap<Integer,Square>();
		for (String propNames : squares.keySet()) {
			newMap.put(squares.get(propNames).getSquareNumber(), squares.get(propNames));	
		}
		return newMap;}

	/**
	 * Gives out the square hashmap.
	 * @return the square Hashmap.
	 */
	public HashMap<String,Square> getSquaresHashMap(){
		return squares;
	}

	/**
	 * Gives out the square array list.
	 * @return The square array list of a board instance.
	 */
	public ArrayList<Square> getSquareArrayList() {
		return squareArrayList;
	}

	public ArrayList<String> getAllUnownedSquares() {
		// TODO Auto-generated method stub
		ArrayList<buyableSquare> byb = getAllBuyableSquares2();
		ArrayList<String> unownedList = new ArrayList<String>();
		String sq = "";
		for (int i = 0; i < byb.size(); i++) {
			if (!byb.get(i).isHasAnOwner()){
				sq = byb.get(i).getName();
				unownedList.add(sq);
			}
		}
		return unownedList;
	}

	public ArrayList<buyableSquare> getAllUnownedSquares2() {
		// TODO Auto-generated method stub
		ArrayList<buyableSquare> byb = getAllBuyableSquares2();
		ArrayList<buyableSquare> unownedList = new ArrayList<buyableSquare>();
		for (int i = 0; i < byb.size(); i++) {
			if (!byb.get(i).isHasAnOwner()){
				buyableSquare sq = byb.get(i);
				unownedList.add(sq);
			}
		}
		return unownedList;
	}

	public ArrayList<buyableSquare> getAllUnownedSquaresOnTheOuterMost(Player p) {
		// TODO Auto-generated method stub
		ArrayList<buyableSquare> byb = getAllBuyableSquares2();
		ArrayList<buyableSquare> unownedList = new ArrayList<buyableSquare>();
		for (int i = 0; i < byb.size(); i++) {		
			int pTrackNum = p.getTrackNumber();
			int sqTrackNum = byb.get(i).getTrackNumber();		
			if ((!byb.get(i).isHasAnOwner()) && (pTrackNum <= sqTrackNum)){
				buyableSquare sq = byb.get(i);
				unownedList.add(sq);
			}
		}
		return unownedList;

	} buyableSquare getHighestRentOnTheBoard() {
		// TODO Auto-generated method stub
		ArrayList<buyableSquare> byb = getAllUnownedSquares2();
		ArrayList<Integer> rents = new ArrayList<Integer>();
		Integer highestRent = Collections.max(rents);
		HashMap<Integer,buyableSquare> squareRents = new HashMap<Integer,buyableSquare>();

		for (int i = 0; i < byb.size(); i++) {
			squareRents.put(byb.get(i).getRent(), byb.get(i));
		}

		buyableSquare highestRentSq = squareRents.get(highestRent);


		return highestRentSq;
	}

	public buyableSquare getHighestRentOnTheOuterMost(Player p) {
		// TODO Auto-generated method stub
		ArrayList<buyableSquare> byb = getAllUnownedSquaresOnTheOuterMost(p);
		ArrayList<Integer> rents = new ArrayList<Integer>();
		Integer highestRent = Collections.max(rents);
		HashMap<Integer,buyableSquare> squareRents = new HashMap<Integer,buyableSquare>();

		for (int i = 0; i < byb.size(); i++) {
			squareRents.put(byb.get(i).getRent(), byb.get(i));
		}

		buyableSquare highestRentSq = squareRents.get(highestRent);


		return highestRentSq;
	}

}
