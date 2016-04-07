package boardrelated;

import java.util.ArrayList;

import backend.Auction;
import backend.ConsoleGenerator;
import backend.DialogGenerator;
import backend.Game;
import backend.Player;

public class AuctionSquare extends Square {

	/*  If you land here, pick an unowned property for the Banker to auction off. If you
		land here and there are no unowned properties left, you must move ahead to the highest-rent
		property that you can reach and must pay rent within the TRANSIT STATION rules, ignoring any higher-rent 
		properties that can't be reached because of the TRANSIT STATION rules (you must take TRANSIT STATION
		if rolled even number and landed on Auction; you must stay on current board if rolled odd number and 
		landed on AUCTION).
	 */
	
	public AuctionSquare(String name, int squareNumber, int trackNumber,
			int colorID, int totalPlayers) {
		super(name, squareNumber, trackNumber, colorID, totalPlayers);
		// TODO Auto-generated constructor stub
	}
	
	public void arrival_proc(Player p,Game g){
		ConsoleGenerator.write2Console("This is a"+this.getName()+" arrival proc:");
		System.out.println(1);
		
		buyableSquare chosenSq = g.getBuyableSq(DialogGenerator.auctionSelectionDialog(g));
		
		if(!g.getTheTable().getBoard().getAllUnownedSquares().isEmpty()){
			//auction the chosen one
			Auction.putAuction(chosenSq, g);
		}
		else{
			buyableSquare targetSq = null;
			if(g.getTheDice().isOdd()){
				targetSq = g.getTheTable().getBoard().getHighestRentOnTheOuterMost(p);
				p.move2SquareWithTarget(targetSq, g);
			}
			else{
				targetSq = g.getTheTable().getBoard().getHighestRentOnTheBoard();
				p.move2SquareWithTarget(targetSq, g);
			}
			
		}
		//o.w move to the highest-rent property that you can reach
	}
	

}
