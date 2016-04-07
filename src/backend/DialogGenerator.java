package backend;

import gui.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;




import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import boardrelated.Auctionable;
import boardrelated.RailroadTrackSquare;
import boardrelated.Square;
import boardrelated.Stock;
import boardrelated.StockCompany;
import boardrelated.buyableSquare;
import boardrelated.cabSquare;
import boardrelated.deedSquare;
import boardrelated.utilitySquare;
import boardrelated.StockExchangeSquare;

/**
 * This class has all the necessary public static dialog methods, which are needed in the game.
 * 
 * 
 *  @author Furkan Yesiler
 *  @version     %I%, %G%
 *  
 */

public class DialogGenerator {

	static ImageIcon icon = createImageIcon("question.gif");

	public DialogGenerator() {
		// TODO Auto-generated constructor stub
	}


	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = DialogGenerator.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/** This dialogs pops up when a player presses "Sell" button on their player panel.
	 *  It asks the player if she wants to sell or mortgage a property and takes action.
	 * 
	 * @param player who called this dialog
	 * @param game in which the arriving player exists
	 * 
	 * @modifies the property list and the mortgaged property list of the player
	 */

	public static void sellDialog(Player player, Game game){
		JFrame frame = new JFrame();
		Object[] options = {"Sell", "Mortgage"};
		Object[] options1 = {"House", "Hotel", "Skyscraper"};
		Object[] options2 = {"Cab Stand"};
		Object[] options3 = {"Train Depot"};
		String result;
		String building;
		int i=0;
		int n = JOptionPane.showOptionDialog(frame,
				"Do you want to Sell or Mortgage Property?",
				"Sell/Mortgage Option",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]); 
		if (n == JOptionPane.YES_OPTION) { //Sell option
			result=(String)JOptionPane.showInputDialog(frame,
					"On which property do you want to sell something?",
					"Choose to Sell",
					JOptionPane.PLAIN_MESSAGE,
					icon,
					player.getPropertyNames(),
					"");
			if(game.getSq(result) instanceof deedSquare){
				building=(String)JOptionPane.showInputDialog(frame,
						"What do you want to sell?",
						"Choose to Sell",
						JOptionPane.PLAIN_MESSAGE,
						icon,
						options1,
						"");
				switch(building){
				case "House":
					i=1;
					break;
				case "Hotel":
					i=2;
					break;
				case "Skyscraper":
					i=3;
					break;
				}
				((buyableSquare)game.getSq(result)).mortgageOrRemove(i);
			}
			else if(game.getSq(result) instanceof cabSquare){
				building=(String)JOptionPane.showInputDialog(frame,
						"What do you want to sell?",
						"Choose to Sell",
						JOptionPane.PLAIN_MESSAGE,
						icon,
						options2,
						"");
				switch(building){
				case "Cab Stand":
					i=1;
					break;
				}
				((buyableSquare)game.getSq(result)).mortgageOrRemove(i);
			} else if(game.getSq(result) instanceof RailroadTrackSquare){
				building=(String)JOptionPane.showInputDialog(frame,
						"What do you want to sell?",
						"Choose to Sell",
						JOptionPane.PLAIN_MESSAGE,
						icon,
						options3,
						"");
				switch(building){
				case "Train Depot":
					i=1;
					break;
				}
				((buyableSquare)game.getSq(result)).mortgageOrRemove(i);
			}
		} else{ //Mortgage Option
			String[] mortgageOptions = {"Property", "Stock"};
			int k = JOptionPane.showOptionDialog(frame,
					"Do you want to Mortgage Property or Stock?",
					"Mortgage Option",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					mortgageOptions,
					mortgageOptions[0]);
			if(k==JOptionPane.YES_OPTION){
				result=(String)JOptionPane.showInputDialog(frame,
						"Which property do you want to mortgage?",
						"Choose to Mortgage",
						JOptionPane.PLAIN_MESSAGE,
						icon,
						player.getPropertyNames(),
						"");

				if(game.getSq(result) instanceof deedSquare){
					i=4;
					((buyableSquare)game.getSq(result)).mortgageOrRemove(i);
				} else if(game.getSq(result) instanceof cabSquare){
					i=2;
					((buyableSquare)game.getSq(result)).mortgageOrRemove(i);
				} else if(game.getSq(result) instanceof RailroadTrackSquare){
					i=2;
					((buyableSquare)game.getSq(result)).mortgageOrRemove(i);
				} else if(game.getSq(result) instanceof utilitySquare){
					i=2;
					((buyableSquare)game.getSq(result)).mortgageOrRemove(i);
				}
			}
			else{
				StockExchangeSquare sq = (StockExchangeSquare)game.getSq("Stock Exchange");
				ArrayList<Stock> stocks1 = player.getAllStocks();
				String[] stocks = new String[stocks1.size()];
				for(int p=0; p<stocks1.size();p++){
					stocks[p]=stocks1.get(p).getName();					
				}
				int cases=0;
				result=(String)JOptionPane.showInputDialog(frame,
						"Which stock do you want to mortgage?",
						"Choose to Mortgage",
						JOptionPane.PLAIN_MESSAGE,
						icon,
						stocks,
						"");
				switch(result){
				case "General Radio":
					cases=1;
					break;
				case "United Railways":
					cases=2;
					break;
				case "National Utilities":
					cases=3;
					break;
				case "American Motors":
					cases=4;
					break;
				case "Allied Steamships":
					cases=5;
					break;
				case "Motion Pictures":
					cases=6;
					break;
				}
				player.mortgageStock(cases);
			}
		}
	}

	/** This dialogs pops up when a player presses "Build" button on their player panel.
	 *  It asks the player if she wants to build a property(house, hotel etc.) and takes action.
	 * 
	 * @param player who called this dialog
	 * @param game in which the arriving player exists
	 * 
	 * @modifies number of houses, hotels, skyscrapers, cab stands, depots on properties
	 */

	public static void buildDialog(Player player, Game game){
		JFrame frame = new JFrame();
		Object[] options1 = {"House 1","House 2","House 3","House 4", "Hotel", "Skyscraper"};
		int result=0;
		String property; 
		String building;
		property=(String)JOptionPane.showInputDialog(frame,
				"On which property do you want to build?",
				"Choose to Build",
				JOptionPane.PLAIN_MESSAGE,
				icon,
				player.getPropertyNames(),
				"");
		if(game.getSq(property) instanceof deedSquare){
			building=(String)JOptionPane.showInputDialog(frame,
					"What do you want to build?",
					"Choose to Build",
					JOptionPane.PLAIN_MESSAGE,
					icon,
					options1,
					"");
			switch(building){
			case "House 1":
				result=1;
				break;
			case "House 2":
				result=2;
				break;
			case "House 3":
				result=3;
				break;
			case "House 4":
				result=4;
				break;
			case "Hotel":
				result=5;
				break;
			case "Skyscraper":
				result=6;
				break;
			}
			((buyableSquare)game.getSq(property)).build(result);

		} else if(game.getSq(property) instanceof cabSquare){
			result=1;
			((buyableSquare)game.getSq(property)).build(result);
		} else if(game.getSq(property) instanceof RailroadTrackSquare){
			result=1;
			((buyableSquare)game.getSq(property)).build(result);
		} else{
			result=-1;
		}

	}

	/** This dialog box pops up when a player is applying House Condemned card.
	 *  It asks the player to choose a deed on which the card should take action.
	 * 
	 * @param deedlist deeds of the player that has houses on them
	 * 
	 * @requires player should have houses on her deeds
	 * @return name of the property player wants to take action on
	 */

	public static String HouseCondemnedDialog(ArrayList<deedSquare> deedsList){
		JFrame frame = new JFrame();
		String result;
		String[] deeds = new String[116];
		for(int i=0;i<deedsList.size();i++){
			deeds[i]=deedsList.get(i).getName();
		}
		result=(String)JOptionPane.showInputDialog(frame,
				"On which property do you want to remove?",
				"House Condemned Card",
				JOptionPane.PLAIN_MESSAGE,
				icon,
				deeds,
				"");

		return result;

	}

	/** This dialogs pops up when a player draws Hurricane card.
	 *  It asks player to choose a color group and then passes it to another method.
	 * 
	 * @param player who called this dialog
	 * @param game in which the arriving player exists
	 * 
	 * @modifies number of houses, hotels, skyscrapers on properties
	 */

	public static void hurricaneDialog(Player player, Game game){
		JFrame frame = new JFrame();
		Object[] options1 = {"Light Pink","Light Green","Light Yellow","Dark Green", "Bordeaux", 
				"Khaki", "Beige", "Dark Red", "Lilac", "Pink", "Orange", "Red",
				"Yellow", "Green", "Blue", "White", "Black", "Grey", "Brown"};
		int result=0;
		String color; 
		color=(String)JOptionPane.showInputDialog(frame,
				"Choose a color group?",
				"Hurricane",
				JOptionPane.PLAIN_MESSAGE,
				icon,
				options1,
				"");

		switch(color){
		case "Light Pink":
			result=1;
			break;
		case "Light Green":
			result=2;
			break;
		case "Light Yellow":
			result=3;
			break;
		case "Dark Green":
			result=4;
			break;
		case "Bordeaux":
			result=5;
			break;
		case "Khaki":
			result=6;
			break;
		case "Beige":
			result=7;
			break;
		case "Dark Red":
			result=8;
			break;
		case "Lilac":
			result=9;
			break;
		case "Pink":
			result=10;
			break;
		case "Orange":
			result=11;
			break;
		case "Red":
			result=12;
			break;
		case "Yellow":
			result=13;
			break;
		case "Green":
			result=14;
			break;
		case "Blue":
			result=15;
			break;
		case "White":
			result=16;
			break;
		case "Black":
			result=17;
			break;
		case "Grey":
			result=18;
			break;
		case "Brown":
			result=19;
			break;
		}
		game.getTheTable().getBoard().colorListForHurricane(result);

	} 

	/** This dialog asks the player on which square she wants to go and passes the result.
	 * 
	 * @param player who called this dialog
	 * @param game in which the arriving player exists
	 * 
	 * @modifies location of the player
	 */

	public static void GoAnySquareDialog(Player player, Game game){
		JFrame frame = new JFrame();
		String result;
		result=(String)JOptionPane.showInputDialog(frame,
				"On which square do you want to go?",
				"Choose to Remove",
				JOptionPane.PLAIN_MESSAGE,
				icon,
				game.getSquareNames(),
				"");

		player.arriveDirectly2Square(result, game);
	}

	/** This dialogs pops up when something inappropriate happens.
	 * 
	 * @requires an error state
	 * 
	 */

	public static void ErrorDialog(){
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "It is not possible to do such thing");
	}

	/** This dialogs pops up when a player needs money to continue playing the game.
	 * 
	 * @param player who called this dialog
	 * @param game in which the arriving player exists
	 * 
	 * @modifies the property list and the mortgaged property list of the player
	 */

	public static void MoneyNeededDialog(Player player, Game game){
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "You don't have enough money. "
				+ "You have to sell or mortgage something");
		DialogGenerator.sellDialog(player, game);
	}

	/** This dialog is a generic one and it uses the parameters to create a yes/no dialog.
	 *  
	 * 
	 * @param title title of the message box
	 * @param message message of the dialog
	 * 
	 * @return player's decision
	 */

	public static boolean AskYesNoDialog(String title,String message){
		JFrame frame = new JFrame();
		Object[] options = {"Yes", "No"};
		boolean result;
		int n = JOptionPane.showOptionDialog(frame,
				title,
				message,
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		if (n == JOptionPane.YES_OPTION) {
			result=true;
		}else{
			result=false;
		}
		return result;
	}

	/** This dialog pops up when a player draws Assets Seized card from the deck.
	 * 
	 * @param propList property list of the player who drew the card
	 * @param game in which the arriving player exists
	 * 
	 * @requires property list of the player should not be null
	 * @return the property player chose from her property list
	 */

	public static buyableSquare AssetsSeizedDialog(ArrayList<buyableSquare> propList, Game game){
		JFrame frame = new JFrame();
		String name;
		String[] props = new String[116];
		for(int i=0;i<propList.size();i++){
			props[i]=propList.get(i).getName();
		}
		name=(String)JOptionPane.showInputDialog(frame,
				"Choose a property",
				"Assets Seized Card",
				JOptionPane.PLAIN_MESSAGE,
				icon,
				props,
				"");

		return game.getBuyableSq(name);

	}

	/** This dialogs pops up when a player draws Foreclosed Property Sale card from the deck.
	 * 
	 * @param propList current mortgaged property list
	 * @param game in which the arriving player exists
	 * 
	 * @return the property player chose
	 */

	public static buyableSquare ForeclosedPropertySaleDialog(ArrayList<buyableSquare> propList, Game game){
		JFrame frame = new JFrame();
		String name;
		String[] props = new String[116];
		for(int i=0;i<propList.size();i++){
			props[i]=propList.get(i).getName();
		}
		name=(String)JOptionPane.showInputDialog(frame,
				"Choose a property",
				"Foreclosed Property Sale Card",
				JOptionPane.PLAIN_MESSAGE,
				icon,
				props,
				"");

		return game.getBuyableSq(name);

	}

	/** This dialogs pops up when a player draws Taxi Wars card from the deck.
	 * 
	 * @param cabList list of cab squares on the board
	 * @param game in which the arriving player exists
	 * 
	 * @return the cab square player chose
	 */

	public static cabSquare TaxiWarsDialog(ArrayList<cabSquare> cabList, Game game){
		JFrame frame = new JFrame();
		String name;
		String[] props = new String[116];
		for(int i=0;i<cabList.size();i++){
			props[i]=cabList.get(i).getName();
		}
		name=(String)JOptionPane.showInputDialog(frame,
				"Choose a cab company",
				"Taxi Wars Card",
				JOptionPane.PLAIN_MESSAGE,
				icon,
				props,
				"");

		return (cabSquare)game.getBuyableSq(name);

	}

	/** This dialogs pops up when a player draws Zero Dollars Down card from the deck.
	 * 
	 * @param deedList deed list of the player
	 * @param game in which the arriving player exists
	 * 
	 * @return the property player chose
	 */

	public static deedSquare ZeroDollarsDownDialog(ArrayList<deedSquare> deedList, Game game){
		JFrame frame = new JFrame();
		String name;
		String[] props = new String[116];
		for(int i=0;i<deedList.size();i++){
			props[i]=deedList.get(i).getName();
		}
		name=(String)JOptionPane.showInputDialog(frame,
				"Choose a property",
				"Zero Dollars Down Card",
				JOptionPane.PLAIN_MESSAGE,
				icon,
				props,
				"");

		return (deedSquare)game.getBuyableSq(name);

	}

	/** This dialogs pops up when a player lands on a Cab Square.
	 * It asks player where to go.
	 * 
	 * @return the square player wants to go
	 */

	public static String CabSquareDialog(){
		JFrame frame = new JFrame();
		String name;
		Object[] options = {"Yes", "No"};
		Object[] cabs = {"Checker Cab Co.", "Black & White Cab Co.", "Yellow Cab Co.", "Ute Cab Co.", "Free Parking"};
		int n = JOptionPane.showOptionDialog(frame,
				"Do you want to go somewhere?",
				"Cab Square Dialog",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		if (n == JOptionPane.YES_OPTION) {
			name=(String)JOptionPane.showInputDialog(frame,
					"Choose where do you want to go",
					"Cab Square Dialog",
					JOptionPane.PLAIN_MESSAGE,
					icon,
					cabs,
					"");
		}else{
			name=null;
		}

		return name;
	}

	/** This dialogs pops up when a player clicks on Use Card button on the player panel.
	 * 
	 * @param player who called this dialog
	 * @param game in which the arriving player exists
	 * 
	 * @modifies card list of the player and other fields depending on the card
	 */

	public static void UseCardDialog(Player player, Game game){
		JFrame frame = new JFrame();
		String result;
		result=(String)JOptionPane.showInputDialog(frame,
				"Which card do you want to use?",
				"Use Card",
				JOptionPane.PLAIN_MESSAGE,
				icon,
				player.getCardNames(),
				"");

		player.useCard(result, game);

	}

	public static String getStringFromUser(String msg) {
		// TODO Asks the msg and gets the answer as String and returns 
		return "gg1";
	}


	public static int GetDiceValue() {
		JFrame frame = new JFrame();
		String result = (String) JOptionPane.showInputDialog("Enter the dice: ");
		return Integer.parseInt(result);
	}

	public static void SaveGame(Game game){
		JFrame frame = new JFrame();
		String result = (String) JOptionPane.showInputDialog("Enter the file name to save:");
		if(SaveLoad.saveGame(game,result)) ConsoleGenerator.write2Console("Saved successfully");
		else ConsoleGenerator.write2Console("Save failed.");	
		}
	public static void LoadGame(Game game){
		JFrame frame = new JFrame();
		String result = (String) JOptionPane.showInputDialog("Enter the file name to load:");
		File f =new File(result);
		boolean r;
		try {
			Scanner in = new Scanner(f);
			int nOfP=in.nextInt(); 		     in.nextLine();
			if (nOfP!=game.getNumberOfPlayers()){
				Game newGame=new Game(nOfP);
				try {
					GUI newGui = new GUI(newGame);
				} catch (IOException e) {
					e.printStackTrace();
					ConsoleGenerator.write2Console("Load Failed");
				}
				r=SaveLoad.loadGame(in,newGame);
			}
			else{
				r=SaveLoad.loadGame(in,game);
			}
			in.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			ConsoleGenerator.write2Console("Load failed. File doesn't exist");	
			e.printStackTrace();
		}
		
		
		System.out.println("Loaded");
	}

	public static String auctionSelectionDialog(Game game){
		JFrame frame = new JFrame();
		String result;
		buyableSquare sq;
		String[] arr = new String[game.getTheTable().getBoard().getAllUnownedSquares().size()];
		for(int i=0; i<arr.length; i++){
			arr[i]=game.getTheTable().getBoard().getAllUnownedSquares().get(i);
		}
		result=(String)JOptionPane.showInputDialog(frame, 
				"Select the square you want to put on auction",
				"Choose for Auction",
				JOptionPane.PLAIN_MESSAGE,
				icon,
				arr,
				"");
		return result;
	}

	public static int[] auctionBiddingDialog(Game game, int currentPlayer, Auctionable sq){
		JFrame frame = new JFrame();
		int[] result = new int[2];
		int[] playerDet = new int[game.getNumberOfPlayers()];
		String str;
		int temp=0;
		int maxBid=0;
		int player=currentPlayer;

		for(int i=0; i<game.numberOfPlayers; i++){
			temp=0;
			str=JOptionPane.showInputDialog("Player "+ (i+currentPlayer)%game.numberOfPlayers + " Write the amount you want to bid\n"
					+ "Write P to Pass");
			if(str=="P"){
				temp=0;
			}else if(str==null){
				temp=0;
			}else{
				temp = Integer.parseInt(str);

				while(temp<maxBid && temp>game.getSpecificPlayer((i+currentPlayer)%game.getNumberOfPlayers()).getBalance()){
					str=JOptionPane.showInputDialog("Player "+ (i+currentPlayer)%game.numberOfPlayers +" You have to bid more than " + maxBid + "\n And less than your balance");
					if(str=="P"){
						temp=maxBid;
					}else if(str==null){
						temp=maxBid;
					}else{
						temp=Integer.parseInt(str);
					}
				}
				playerDet[(i+currentPlayer)%game.getNumberOfPlayers()]=Integer.parseInt(str);
				maxBid=temp;

			}
		}

		for(int i=0;i<game.getNumberOfPlayers();i++){
			if(playerDet[i]==maxBid){
				player=i;
			}
		}
		result[0]=maxBid;
		result[1]=player;

		return result;

	}


	public static void buyStock(Player player, Game game){
		JFrame frame = new JFrame();
		boolean answer1;
		String answer2;
		int cases = 0;
		StockExchangeSquare sq = (StockExchangeSquare)game.getSq("Stock Exchange");
		String[] unowned = new String[sq.getCompanies().length+1];
		int i=0;
		for(StockCompany comp:sq.getCompanies()){
			if(!comp.allOwned()){
				unowned[i]=comp.getCompanyName();
			}
			i++;
		}
		unowned[sq.getCompanies().length]="No Purchase";
		answer1 = DialogGenerator.AskYesNoDialog("Buy a Stock", "Do you want to buy a stock?");
		if(answer1==true){
			answer2=(String)JOptionPane.showInputDialog(frame,
					"Which stock do you want to buy",
					"Choose Stock",
					JOptionPane.PLAIN_MESSAGE,
					icon,
					unowned,
					"");
			switch(answer2){
			case "General Radio":
				cases=1;
				break;
			case "United Railways":
				cases=2;
				break;
			case "National Utilities":
				cases=3;
				break;
			case "American Motors":
				cases=4;
				break;
			case "Allied Steamships":
				cases=5;
				break;
			case "Motion Pictures":
				cases=6;
				break;
			case "No purchase":
				cases=7;
				break;
			}
			player.buyStock(sq, cases, game);
		}
		if(answer1==false){
			((StockExchangeSquare)game.getSq("Stock Exchange")).processAuction(game, player);
		}
	}
	
	public static String busTicketDialog(Game game, ArrayList<Square> list){
		JFrame frame = new JFrame();
		String result;
		String[] options = new String[list.size()];
		for(int i=0; i<list.size();i++){
			options[i]=list.get(i).getName();
		}
		result=(String)JOptionPane.showInputDialog(frame,
				"Where do you want to go?",
				"Bus Ticket",
				JOptionPane.PLAIN_MESSAGE,
				icon,
				options,
				"");
				
		return result;
	}
	
	public static Stock inheritStockDialog(ArrayList<Stock> list, Game game){
		JFrame frame=new JFrame();
		Stock st=null;
		String result;
		String[] options = new String[list.size()];
		for(int i=0; i<list.size();i++){
			options[i]=list.get(i).getName();
		}
		result=(String)JOptionPane.showInputDialog(frame,
				"Which stock do you want to buy?",
				"Inherit Stock",
				JOptionPane.PLAIN_MESSAGE,
				icon,
				options,
				"");
		return game.getTheTable().getBoard().getStock(result);
	}
}
