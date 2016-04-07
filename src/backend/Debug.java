package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import card.Card;
import boardrelated.RailroadTrackSquare;
import boardrelated.Square;
import boardrelated.Stock;
import boardrelated.buyableSquare;
import boardrelated.cabSquare;
import boardrelated.deedSquare;
import gui.GUI;

public class Debug {
	private static boolean fromFile=true;
	static ImageIcon icon = createImageIcon("question.gif");
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = DialogGenerator.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private static String inputDialog(String message){
		JFrame frame = new JFrame();
		String ans=JOptionPane.showInputDialog(message);
		return ans;
	}

	private static void messageDialog(String message){
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, message);
	}

	private static int intDialog(String message){
		JFrame frame = new JFrame();
		String ans=(String)JOptionPane.showInputDialog(message);

		return Integer.parseInt(ans);

	}

	public static String stringDialog(String message, String title, Object[] options){
		JFrame frame = new JFrame();
		String ans;
		ans=(String)JOptionPane.showInputDialog(frame,
				message,
				title,
				JOptionPane.PLAIN_MESSAGE,
				icon,
				options,
				"");
		return ans;
	}


	public static void main(String[] args) throws IOException{

		// TODO Auto-generated constructor stub
		File file = new File("debug.txt");
		Scanner in =(fromFile)? new Scanner(file):new Scanner(System.in);
		boolean deb;
		int check = intDialog("Enter 1 to play normal, Enter 2 to play in Debug Mode");
		if(check==1){
			deb=false;
		}else if(check==2){
			deb=true;
		}else{
			deb=false;
		}
		Game newGame = getAndCreateGame(in,deb);
		GUI newGui = new GUI(newGame);
		if(deb==true){
			getAndApplyState(newGame,in);
			in.close();
		}

		//SaveLoad.saveGame(newGame, "save1.txt");
		/*Scanner in = new Scanner(new File("save1.txt"));
		int nOfP=in.nextInt(); 		     in.nextLine();
		Game newGame = new Game(2);
		GUI newGui = new GUI(newGame);
		SaveLoad.loadGame(in,newGame);*/

	}
	private static Game getAndCreateGame(Scanner in, boolean deb) {
		// TODO Auto-generated method stub
		int ti;

		// TODO Auto-generated method stub
		ti = intDialog("Enter the number of players: ");
		assert (ti<5)&&(ti>1); //Dont know why not worki
		Game rg=new Game(ti,deb);
		return rg;
	}
	private Debug(){}
	private static void getAndApplyState(Game rg,Scanner in)  {
		String ts;
		int ti=rg.getNumberOfPlayers();
		for(int i=0; i<ti;i++){
			setUserState(rg,i,in);
		}
		rg.getTheDice().setDebugMode(true);
	}

	private static void setUserState(Game rg, int i,Scanner in) {
		ArrayList<String> list1 = rg.getTheTable().getCardDeck().getKeepableChanceCards();
		ArrayList<String> list2 = rg.getTheTable().getCardDeck().getKeepableCommunityChestCards();
		String[] cards = new String[list1.size()+list2.size()];
		if(list1.size()!=0){
			for(int k=0; k<list1.size();k++){
				cards[k]=list1.get(k);
			}
		}
		if(list2.size()!=0){
			for(int k=0; k<list2.size();k++){
				cards[k+list1.size()]=list2.get(k);
			}
		}
		//asks the location
		String ts;//ts for temporary string
		int ti;
		Square tsq;
		buyableSquare tbs;
		Card tc;
		Stock tsto;
		Player cp=rg.getSpecificPlayer(i);
		messageDialog("Tell me more about Player "+i);
		do{
			ts=inputDialog("Where are you?");
			tsq=rg.getSq(ts);
		} while(tsq==null);
		cp.setLocationDebug(tsq);

		//asks the balance
		ti=intDialog("How much money do you have? >=0");
		assert(ti>=0);
		cp.setBalanceDebug(ti);

		//Deeeds
		
		ti=intDialog("Do you have any Deeds? Enter 1 for YES, 0 for NO");
		while(ti!=0){
			ArrayList<buyableSquare> squares = rg.getTheTable().getBoard().getAllUnownedSquares2();
			String[] squares2 = new String[squares.size()];
			for(int u=0; u<squares.size();u++){
				squares2[u]=squares.get(u).getName();
			}
			ts=stringDialog("What deedSquare do you have?", "Choose Deeds", squares2);
			tbs=rg.getBuyableSq(ts);
			if(tbs==null) {
				messageDialog("Please enter a valid deedSquare name!");
				continue;
			}else{
				cp.buySquareWithPrice(tbs, 0);
				if(tbs instanceof deedSquare){
					int house=intDialog("How many houses do you have on this deed? Between 0-4");
					((deedSquare)tbs).setHasHouse(true);
					((deedSquare)tbs).setNumOfHouses(house);
					((deedSquare)tbs).setVisNumOfHouses(house);
					if(house==4){
						int hotel=intDialog("How many hotels do you have on this deed? 0 or 1");
						if(hotel==1){
							((deedSquare)tbs).setHasHotel(true);
							((deedSquare)tbs).setHasHouse(false);
							((deedSquare)tbs).setNumOfHouses(0);
							int sky=intDialog("How many skyscrapers do you have on this deed? 0 or 1");
							if(sky==1){
								((deedSquare)tbs).setHasSkyscraper(true);
								((deedSquare)tbs).setHasHotel(false);
							}
						}
					}

				}else if(tbs instanceof cabSquare){
					int stand=intDialog("How many cab stands do you have on this deed? 0 or 1");
					if(stand==1){
						((cabSquare)tbs).setHasStand(true);
					}
				}else if(tbs instanceof RailroadTrackSquare){
					int depot=intDialog("How many depots do you have on this deed? 0 or 1");
					if(depot==1){
						((RailroadTrackSquare)tbs).setDepot(true);
					}
				}else{

				}
			}

			ti=intDialog("Do you have more deeds? Enter 1 for YES, 0 for NO");
		} 

		//Cards
		ti=intDialog("Do you have any cards? Enter 1 for YES, 0 for NO");
		while(ti!=0){

			ts=stringDialog("What keepable cards do you have?", "Choose cards",cards);
			tc=rg.getKeepableCard(ts);
			if(tc==null) {
				ti=0;
				continue;
			}else{
				cp.addCardDebug(tc);
			}
			ti=intDialog("Do you have more cards? Enter 1 for YES, 0 for NO");
		}
		//Stocks
		
		ti=intDialog("Do you have any stocks? Enter 1 for YES, 0 for NO");
		while(ti!=0){
			ArrayList<Stock> stocks1 = rg.getTheTable().getBoard().AllUnpurchasedStocks();
			String[] stocks = new String[stocks1.size()];
			for(int o=0; o<stocks1.size();o++){
				stocks[o]=stocks1.get(o).getName();
			}
			ts=stringDialog("What stocks do you have?", "Choose Stock", stocks);
			tsto=rg.getTheTable().getBoard().getStock(ts);
			if(tsto==null){
				ti=0;
				continue;
			}else{
				if(tsto.getStCompany().getCompanyName().equals("General Radio")){
					cp.addToStockList(0, tsto);
				}else if(tsto.getStCompany().getCompanyName().equals("United Railways")){
					cp.addToStockList(1, tsto);
				}else if(tsto.getStCompany().getCompanyName().equals("National Utilities")){
					cp.addToStockList(2, tsto);
				}else if(tsto.getStCompany().getCompanyName().equals("American Motors")){
					cp.addToStockList(3, tsto);
				}else if(tsto.getStCompany().getCompanyName().equals("Allied Steamships")){
					cp.addToStockList(4, tsto);
				}else if(tsto.getStCompany().getCompanyName().equals("Motion Pictures")){
					cp.addToStockList(5, tsto);
				}
				tsto.setHasOwner(true);
			}
			ti=intDialog("Do you have more stocks? Enter 1 for YES, 0 for NO");
		}
	}

}
