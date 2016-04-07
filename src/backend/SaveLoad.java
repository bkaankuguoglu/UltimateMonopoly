package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import card.Card;
import boardrelated.*;
/**
 * This class implements the Save/Load functionalities of the class. It uses Scanner's to write/read the game state. 
 * It is an abstract class with no public constructors and have their methods as static.
 * 
 * 
 *  @author Utku Evci
 *  @version     %I%, %G%
 *  
 */
public final class SaveLoad {
	
	Game game;

	private SaveLoad() {
		// TODO Auto-generated constructor stub
	}
	
	/** Saves the state of the game in to the file.
	 * 
	 * @param g game to be saved	 
	 * @param f file that is going to be use to save the state
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 * @returns true if successful, otherwise 0.
	 * @requires game&fieldsOfGame. and file is not null.
	 * @modifies the file given as the input.
	 */
	public static boolean saveGame(Game g,String fname) {
		PrintWriter w;
		try {
			w = new PrintWriter(fname, "UTF-8");
			w.println(g.getNumberOfPlayers());
			w.println(g.getCurrentPlayer());
			w.println(g.getTheTable().getPool().getAmount());
			printDice(g.getTheDice(),w);
			Player[] pa=g.getPlayerArray();
			for (int i=0;i<g.getNumberOfPlayers();i++){
				printPlayer(pa[i],w);
			}
			
			w.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	private static void printPlayer(Player p, PrintWriter w) {
		// TODO Auto-generated method stub
		w.println(p.getBalance());
		w.println(p.getLocation().getName());
		w.println(p.getTurnInJail());
		w.println(p.getTrackNumber());
		w.println(p.isBankrupt());
		w.println(p.isCanRoll());
		w.println(p.isInJail());
		w.println(p.isTakingTurn());
		w.println(p.isReverseMoving());
		printDeedList(p.getBuyableSquares(),w);
		printList(p.getMortgageNames(),w);
		printList(p.getCardNames(),w);
	}

	private static void printList(String[] names, PrintWriter w) {
		int a=names.length;
		w.print(a);
		for(int i=0;i<a;i++){
			w.print(",");
			w.print(names[i]);
		}
		w.println("");
	}
	private static void printDeedList(ArrayList<buyableSquare> bSqs, PrintWriter w) {
		ArrayList<deedSquare> t=getDeeds(bSqs); 
		w.print(t.size());
		for(int i=0;i<t.size();i++){
			w.print(",");
			w.print(t.get(i).getName());
			w.print(",");
			w.print(t.get(i).hasHouse());
			w.print(",");
			w.print(t.get(i).hasHotel());
			w.print(",");
			w.print(t.get(i).hasSkyscraper());
			w.print(",");
			w.print(t.get(i).getNumOfHouses());
		}
		w.println("");
		
		ArrayList<cabSquare> t2=getCabs(bSqs); 
		w.print(t2.size());
		for(int i=0;i<t2.size();i++){
			w.print(",");
			w.print(t2.get(i).getName());
			w.print(",");
			w.print(t2.get(i).isHasStand());
		}
		w.println("");

		ArrayList<RailroadTrackSquare> t3=getRails(bSqs); 
		w.print(t3.size());
		for(int i=0;i<t3.size();i++){
			w.print(",");
			w.print(t3.get(i).getName());
			w.print(",");
			w.print(t3.get(i).hasDepot());
		}
		w.println("");
		
		ArrayList<utilitySquare> t4=getUtils(bSqs); 
		w.print(t4.size());
		for(int i=0;i<t4.size();i++){
			w.print(",");
			w.print(t4.get(i).getName());
		}
		w.println("");
	}
	private static ArrayList<deedSquare> getDeeds(ArrayList<buyableSquare> bSqs) {
		ArrayList<deedSquare> dSqs=new ArrayList<deedSquare>();
		for(buyableSquare ss:bSqs){
			if (ss instanceof deedSquare){
				dSqs.add((deedSquare) ss);
			}
		}
		return dSqs;
	}

	private static ArrayList<cabSquare> getCabs(ArrayList<buyableSquare> bSqs) {
		ArrayList<cabSquare> cSqs=new ArrayList<cabSquare>();
		for(buyableSquare ss:bSqs){
			if (ss instanceof cabSquare){
				cSqs.add((cabSquare) ss);
			}
		}
		return cSqs;
	}

	private static ArrayList<RailroadTrackSquare> getRails(ArrayList<buyableSquare> bSqs) {
		ArrayList<RailroadTrackSquare> rSqs=new ArrayList<RailroadTrackSquare>();
		for(buyableSquare ss:bSqs){
			if (ss instanceof RailroadTrackSquare){
				rSqs.add((RailroadTrackSquare) ss);
			}
		}
		return rSqs;
	}

	private static ArrayList<utilitySquare> getUtils(ArrayList<buyableSquare> bSqs) {
		ArrayList<utilitySquare> uSqs=new ArrayList<utilitySquare>();
		for(buyableSquare ss:bSqs){
			if (ss instanceof utilitySquare){
				uSqs.add((utilitySquare) ss);
			}
		}
		return uSqs;
	}

	private static void printDice(Dice d, PrintWriter w) {
		w.print(String.format("%d,%d,%d,%d,%s,%s,%s,%s,%s\n",d.getCurrentDice1(),d.getCurrentDice2(),
				d.getSpeedDice(),d.getDoubleCount(),d.isThrowedInJail(),d.isDouble(),d.isTriple(),
				d.isMrMonopolyMove(),d.isBusMove()));
	}

	/** Loads the state of the game from the file.
	 * 
	 * @param f file that is going to be use to load the state
	 * @param g a game with right amount of player
	 * @returns the true if succesful, otherwise returns false
	 * @requires file is not null and it is a valid saved game. Game is same as the input
	 */
	public static boolean loadGame(Scanner in,Game g){
		try {
			int nOfP=g.getNumberOfPlayers();
			int t=in.nextInt(); 		     in.nextLine();
		     g.setCurrentPlayer(t); 	
		     t=in.nextInt(); 		     in.nextLine();
		     g.getTheTable().getPool().setAmount(t);
		     updateDice(g.getTheDice(),in.nextLine());
				for (int i=0;i<nOfP;i++){
					updatePlayer(g,g.getSpecificPlayer(i),in);
				}
				in.close();			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ConsoleGenerator.write2Console("Load failed. Invalid file");
			e.printStackTrace();
			return false;
		}
		
		//Loads the game from the file e
		//@returns null if the load is not successful, returns the Game if it is successful.
		
	}

	private static void updatePlayer(Game g,Player p, Scanner in) {
		String ts;
		// TODO Auto-generated method stub
	/*	w.println(p.getBalance());
		w.println(p.getLocation().getName());
		w.println(p.getTurnInJail());
		w.println(p.getTrackNumber());
		w.println(p.isBankrupt());
		w.println(p.isCanRoll());
		w.println(p.isInJail());
		w.println(p.isTakingTurn());
		w.println(p.isReverseMoving());
		printDeedList(p.getPropertyNames(),w);
		printDeedList(p.getMortgageNames(),w);
		printDeedList(p.getCardNames(),w);*/
		p.setBalance(in.nextInt()); in.nextLine();
		p.setLocationDebug(g.getSq(in.nextLine()));
		p.setTurnInJail(in.nextInt()); in.nextLine();
		p.setTrackNumber(in.nextInt()); in.nextLine();
		p.setBankrupt(in.nextBoolean()); in.nextLine();
		p.setCanRoll(in.nextBoolean()); in.nextLine();
		p.setInJail(in.nextBoolean()); in.nextLine();
		p.setTakingTurn(in.nextBoolean()); in.nextLine();
		p.setReverseMoving(in.nextBoolean()); in.nextLine();
		updatePListD(g,p,in.nextLine()); //P for Property D for Deed
		updatePListC(g,p,in.nextLine()); //P for Property C for Cab
		updatePListR(g,p,in.nextLine()); //P for Property R for Rail
		updatePListU(g,p,in.nextLine()); //P for Property U for Util
		updateMList(g,p,in.nextLine()); //M for Mortgage
		updateCList(g,p,in.nextLine()); //C for Cab

	}

	private static void updatePListD(Game g,Player p, String str) {
		buyableSquare tbs;
		Scanner s = new Scanner(str);
		s.useDelimiter(",");
		int n= s.nextInt();
		for(int i=0;i<n;i++){
			tbs=g.getBuyableSq(s.next());
			if(tbs==null || !(tbs instanceof deedSquare)) {
				System.out.println("Please enter a valid deedSquare Name! ERROR");
				continue;
			}
			p.buySquareWithPrice(tbs, 0);
			deedSquare td=(deedSquare) tbs;
			td.setHasHouse(s.nextBoolean());
			td.setHasHotel(s.nextBoolean());
			td.setHasSkyscraper(s.nextBoolean());
			td.setNumOfHouses(s.nextInt());
		}
		s.close();
	}
	private static void updatePListC(Game g,Player p, String str) {
		buyableSquare tbs;
		Scanner s = new Scanner(str);
		s.useDelimiter(",");
		int n= s.nextInt();
		for(int i=0;i<n;i++){
			tbs=g.getBuyableSq(s.next());
			if(tbs==null || !(tbs instanceof cabSquare)) {
				System.out.println("Please enter a valid cabSquare Name! ERROR");
				continue;
			}
			p.buySquareWithPrice(tbs, 0);
			cabSquare tc=(cabSquare) tbs;
			tc.setHasStand(s.nextBoolean());
		}
		s.close();
	}
	private static void updatePListR(Game g,Player p, String str) {
		buyableSquare tbs;
		Scanner s = new Scanner(str);
		s.useDelimiter(",");
		int n= s.nextInt();
		for(int i=0;i<n;i++){
			tbs=g.getBuyableSq(s.next());
			if(tbs==null || !(tbs instanceof RailroadTrackSquare)) {
				System.out.println("Please enter a valid RailroadTrackSquare Name! ERROR");
				continue;
			}
			p.buySquareWithPrice(tbs, 0);
			RailroadTrackSquare tr=(RailroadTrackSquare) tbs;
			tr.setDepot(s.nextBoolean());
		}
		s.close();
	}
	private static void updatePListU(Game g,Player p, String str) {
		buyableSquare tbs;
		Scanner s = new Scanner(str);
		s.useDelimiter(",");
		int n= s.nextInt();
		for(int i=0;i<n;i++){
			tbs=g.getBuyableSq(s.next());
			if(tbs==null || !(tbs instanceof utilitySquare)) {
				System.out.println("Please enter a valid utilitySquare Name! ERROR");
				continue;
			}
			p.buySquareWithPrice(tbs, 0);
		}
		s.close();
	}


	private static void updateMList(Game g,Player p, String str) {
		buyableSquare tbs;
		Scanner s = new Scanner(str);
		s.useDelimiter(",");
		int n= s.nextInt();
		for(int i=0;i<n;i++){
			tbs=g.getBuyableSq(s.next());
			if(tbs==null) {
				System.out.println("Please enter a valid buyableSquare Name! ERROR");
				continue;
			}
			p.buySquareWithPrice(tbs, 0);
			p.mortgagePropertyDebug(tbs);
		}
		s.close();
	}

	private static void updateCList(Game g,Player p, String str) {
		Card tc;
		Scanner s = new Scanner(str);
		s.useDelimiter(",");
		int n= s.nextInt();
		for(int i=0;i<n;i++){
			tc=g.getKeepableCard(s.next());
			if(tc==null) {
				System.out.println("Please enter a valid keepable Cards Name!");
				continue;
			}
			p.addCardDebug(tc);
		}
		s.close();
	}

	private static void updateDice(Dice d, String str) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(str);
		s.useDelimiter(",");
		d.setCurrentDice1(s.nextInt());
		d.setCurrentDice2(s.nextInt());
		d.setSpeedDice(s.nextInt());
		d.setDoubleCount(s.nextInt()); //TODO double count 
		d.setThrowedInJail(s.nextBoolean());
		d.setDoubleDebug(s.nextBoolean());
		d.setTriple(s.nextBoolean());
		d.setMrMonopolyMove(s.nextBoolean());
		d.setBusMove(s.nextBoolean());
		s.close();
	}

}
