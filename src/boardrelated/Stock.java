package boardrelated;

import java.util.HashMap;
import backend.Game;
import backend.Player;

/**
 * This class is provided to meet the needs of the StockMarket. In a sense, the stocks are the objects 
 * Stock class is provided to meet the needs of the StockMarket. In a sense, the stocks are the objects 
 * that StockMarkets use to operate.
 * 
 *  @author Cem Uyuk
 *  @version %I%, %G%
 */

public class Stock implements Auctionable{
	private String name;
	private int ParValue;
	private Player owner;
	private boolean hasOwner;
	private boolean mortgaged;
	private StockCompany stCompany;
	
	
	/**
	 * Creates an instance of Stock.
	 * 
	 * @param name takes the name of the stock
	 */
	public Stock(String name, StockCompany stc) {
		this.name=name;
		this.hasOwner=false;
		this.mortgaged=false;
		this.stCompany = stc;
	}

	/**
	 * Adds owner to the stock
	 * 
	 * @modifies the owner of this' instance.
	 * @param g the game that the stock and the auction are in
	 * @param p the player whose turn is to bid in the auction
	 * @see boardrelated.Auctionable#processAuction(backend.Game, backend.Player)
	 */

	public void setOwner(Player p){
		this.owner=p;
	}

	public StockCompany getStCompany() {
		return stCompany;
	}

	public void setStCompany(StockCompany stCompany) {
		this.stCompany = stCompany;
	}

	public int getPrice() {
		return ParValue;
	}

	public void setPrice(int parValue) {
		ParValue = parValue;
	}

	public String getName() {
		return name;
	}
	
	public boolean isMortgaged() {
		return mortgaged;
	}

	public void setMortgaged(boolean mortgaged) {
		this.mortgaged = mortgaged;
	}

	public boolean isHasOwner() {
		return hasOwner;
	}

	public void setHasOwner(boolean hasOwner) {
		this.hasOwner = hasOwner;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player getOwner() {
		return owner;
	}
	@Override
	/**
	 * Processes auction accordingly to the type of the Stock.
	 * 
	 * @param g Game that the player that attends the auction and stock exist.
	 * @param p Player that attend the auction.
	 */
	public void processAuction(Player p,int price) {
		// TODO Auto-generated method stub
		p.buyStockForAuction(this, price);
	}

}
