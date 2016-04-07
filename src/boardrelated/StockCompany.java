package boardrelated;
import backend.Game;
import backend.Player;

/**
 *  This class is implemented to meet the needs of the stock market. There is only one stock market in each game.
 *  In this sense it is a single-ton object that keeps the stocks in and helps making the payment of the stocks.
 *  
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 *  
 */
public class StockCompany {
	String companyName;
	private int valueOf1Stock;
	private int valueOf2Stock;
	private int valueOf3Stock;
	private int valueOf4Stock;
	private int valueOf5Stock;
	private int ParValue;
	Stock[] stocks=new Stock[5];	

	/**
	 * This creates a StockMarket instance.
	 * 
	 */
	public StockCompany(String companyName, int ParValue, int val1, int val2, int val3, int val4, int val5) {
		this.companyName=companyName;
		this.ParValue=ParValue;
		this.valueOf1Stock=val1;
		this.valueOf2Stock=val2;
		this.valueOf3Stock=val3;
		this.valueOf4Stock=val4;
		this.valueOf5Stock=val5;

		for (int i=0; i<5; i++){
			Stock temp = new Stock(companyName+i, this);
			temp.setPrice(ParValue);
			stocks[i]=temp;
		}
	}
	public boolean allOwned(){
		for(int i=0; i<5; i++){
			if (!stocks[i].isHasOwner())
				return false;
		}
		return true;
	}


	public Stock getOneThatIsNotOwned(){
		for(int i=0; i<5; i++){
			if(!stocks[i].isHasOwner())
				return stocks[i];
		}
		return new Stock("NON-EXISTING", this);
	}

	/**
	 * payDividends method makes a payment from one player to owner of different stocks in a certain game.
	 * 
	 * @param g game that this stock market is in
	 * @requires some owned stocks
	 * @modifies decreases the balance of the arriving player by the total dividend amount
	 * @modifies increases the amount of money that the dividend owners have
	 * @effects the game
	 *  
	 */


	/**
	 * Puts a stock of the market into auction.
	 * 
	 * @param name the name of the stock that is going to auction
	 * @requires a player to not buy an auction when had the oppurtunity
	 * @effects the game
	 */

	public void auctionStock(String name){
		for(Stock s:stocks){

		}
	}

	/**
	 * Puts a stock of the market into auction.
	 * 
	 * @param stock an instance of Auctionable that is most likely a stock and going to auction
	 * @requires a player to not buy an auction when had the oppurtunity
	 * @effects the game
	 */

	public void auctionStock(Auctionable stock){

	}


	public int getValueOf1Stock() {
		return valueOf1Stock;
	}
	public void setValueOf1Stock(int valueOf1Stock) {
		this.valueOf1Stock = valueOf1Stock;
	}
	public int getValueOf2Stock() {
		return valueOf2Stock;
	}
	public void setValueOf2Stock(int valueOf2Stock) {
		this.valueOf2Stock = valueOf2Stock;
	}
	public int getValueOf3Stock() {
		return valueOf3Stock;
	}
	public void setValueOf3Stock(int valueOf3Stock) {
		this.valueOf3Stock = valueOf3Stock;
	}
	public int getValueOf4Stock() {
		return valueOf4Stock;
	}
	public void setValueOf4Stock(int valueOf4Stock) {
		this.valueOf4Stock = valueOf4Stock;
	}
	public int getValueOf5Stock() {
		return valueOf5Stock;
	}
	public void setValueOf5Stock(int valueOf5Stock) {
		this.valueOf5Stock = valueOf5Stock;
	}
	public int getPrice() {
		return ParValue;
	}
	public void setPrice(int parValue) {
		ParValue = parValue;
	}
	public Stock[] getStocks() {
		return stocks;
	}
	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
