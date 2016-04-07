package boardrelated;

import java.util.ArrayList;

import backend.Auction;
import backend.ConsoleGenerator;
import backend.DialogGenerator;
import backend.Game;
import backend.Player;

/**
 * This class, like any other boardrelated classes, is designed to meet the behaviours of the StockExchangeSquares
 * have. It allows the game to have a stock attribute.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 *  
 */
public class StockExchangeSquare extends Square{
	private StockCompany[] companies = new StockCompany[6];
	private ArrayList<Stock> AllStocks = new ArrayList<Stock>();
	/**
	 * Creates a StockExchangeSquare instance.
	 * 
	 * @param name the name of the square
	 * @param squareNumber the number of the square which is sort of tag
	 * @param trackNumber number of the track that this' instance is on
	 * @param colorID of this square's instance
	 * @param totalPlayers the number of total players on its instance
	 */

	public StockExchangeSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers) {
		// TODO Auto-generated constructor stub
		super(name,  squareNumber, trackNumber,  colorID, totalPlayers);
		StockCompany GeneralRadio = new StockCompany("General Radio", 130, 13, 52, 117, 208, 325);
		StockCompany UnitedRailways = new StockCompany("United Railways", 140, 14, 56, 126, 254, 350);
		StockCompany NationalUtilities = new StockCompany("National Utilities", 120, 12, 48, 108, 192, 300);
		StockCompany AmericanMotors = new StockCompany("American Motors", 150, 15, 60, 135, 240, 375);
		StockCompany AlliedSteamships = new StockCompany("Allied Steamships", 110, 11, 44, 99, 176, 275);
		StockCompany MotionPictures = new StockCompany("Motion Pictures", 100, 10, 40, 90, 100, 250);

		companies[0] = GeneralRadio;
		companies[1] = UnitedRailways;
		companies[2] = NationalUtilities;
		companies[3] = AmericanMotors;
		companies[4] = AlliedSteamships;
		companies[5] = MotionPictures;	

		generateStockList();
	}

	public void generateStockList(){
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				AllStocks.add(companies[i].getStocks()[j]);
			}
		}
	}
	
	public StockCompany getStockCompany(String companyName){
		if(companyName.equals("General Radio")){
			return companies[0];
		}else if(companyName.equals("United Railways")){
			return companies[1];
		}else if(companyName.equals("National Utilities")){
			return companies[2];
		}else if(companyName.equals("American Motors")){
			return companies[3];
		}else if(companyName.equals("Allied Steamships")){
			return companies[4];
		}else if(companyName.equals("Motion Pictures")){
			return companies[5];
		}else{
			return companies[0];
		}
	}

	public ArrayList<Stock> getAllStocks() {
		return AllStocks;
	}

	public void setAllStocks(ArrayList<Stock> allStocks) {
		AllStocks = allStocks;
	}

	@Override
	public void arrival_proc(Player p, Game g){
			DialogGenerator.buyStock(p, g);
			p.payDividends();
	}

	public void processAuction(Game g, Player p){
		for(int i=0; i<companies.length;i++){
			if(!companies[i].allOwned()){
				Stock temp = companies[i].getOneThatIsNotOwned();
				Auction.putAuction(temp , g);
				break;
			}
		}
	}


	public StockCompany[] getCompanies() {
		return companies;
	}

	public void setCompanies(StockCompany[] companies) {
		this.companies = companies;
	}
	
	public ArrayList<Stock> getUnpurchasedStockList(){
		ArrayList<Stock> unpurchasedStockList = new ArrayList<Stock>();
		for (Stock st : AllStocks) {
			if(!st.isHasOwner())
			unpurchasedStockList.add(st);
		}
		return unpurchasedStockList;
	}
}

