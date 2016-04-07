package card;

import java.util.ArrayList;

import backend.ConsoleGenerator;
import backend.Debug;
import backend.DialogGenerator;
import backend.Game;
import backend.Player;
import boardrelated.Stock;
import boardrelated.StockCompany;
import boardrelated.StockExchangeSquare;

public class _InheritStock extends Card {

	public _InheritStock() {
		// TODO Auto-generated constructor stub
	}

	public _InheritStock(String cardName, int cardID, int typeID, boolean kpable) {
		super(cardName, cardID, typeID, kpable);
		// TODO Auto-generated constructor stub
	}

	//You may chose any 1 share of any unpurchased stock to add to your portfolio
	public void applyCard(Player p, Game g) {
		ArrayList<Stock> stocks1 = g.getTheTable().getBoard().AllUnpurchasedStocks();
		String[] stocks = new String[stocks1.size()];
		for(int o=0; o<stocks1.size();o++){
			stocks[o]=stocks1.get(o).getName();
		}
		String ts=Debug.stringDialog("What stocks do you have?", "Choose Stock", stocks);
		Stock tsto=g.getTheTable().getBoard().getStock(ts);
		if(tsto==null){

		}else{
			p.getAllStocks().add(tsto);
			tsto.setHasOwner(true);
			p.givePayment(tsto.getPrice(), g);
			ConsoleGenerator.write2Console(tsto.getName()+" is now "+p.getPlayerName()+"'s stock.");
		}
	}
}
