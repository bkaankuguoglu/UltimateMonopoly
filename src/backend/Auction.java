package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import boardrelated.Auctionable;
import boardrelated.Stock;
import boardrelated.StockExchangeSquare;
import boardrelated.buyableSquare;
import backend.Player;

public class Auction {

	public Auction() {
	}


	public static void putAuction(Auctionable aSq, Game g){
		askBids(aSq, g);
	}


	public static void askBids(Auctionable aSq, Game g){
		int[] details = new int[2];
		details=DialogGenerator.auctionBiddingDialog(g, g.getCurrentPlayer(), aSq);

		// current playerdan baþlayarak tüm playerlara offer sor FURKAAAAAAAAN hehe
		// bir önceki offerdan yüksek vermek zorundalar veremiyorlarsa salsýnlar
		// kazanan player ý dönelim (þimdilik current ile yazdým)

		if(details[0]>=aSq.getPrice()){
			if(aSq instanceof buyableSquare){
				aSq.processAuction(g.getSpecificPlayer(details[1]), details[0]);
			}
			if(aSq instanceof Stock){
				aSq.processAuction(g.getSpecificPlayer(details[1]), details[0]);
			}
		}




	}
}
