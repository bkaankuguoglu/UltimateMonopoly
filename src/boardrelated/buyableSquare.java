package boardrelated;

import backend.Auction;
import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;
/**
 * This class as the sub-type of Squares allows us progress in a manner that
 * the users can buy, sell, mortgage and build constructions on squares.
 * 
 * It allows us to use a declared behavior of buyable properties in many different ways such as utilities, deeds, 
 * cabs etc.
 * @author Cem Uyuk
 *
 */
public abstract class buyableSquare extends Square implements Auctionable{
	private int price;
	private Player owner;
	private boolean hasAnOwner;
	int rent;
	private boolean isMortgaged;
	/**
	 * 
	 * @param name The name of the square.
	 * @param squareNumber The number of the square which is sort of tag.
	 * @param trackNumber Number of the track that this class' instance is on.
	 * @param colorID Color Identification number of this class' instance.
	 * @param totalPlayers The number of total players on its instance.
	 * @param price The cost of this class' instance.
	 * @param rent The rent of this class' instance when someone arrives.
	 */

	public buyableSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers,int price,int rent) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
		this.price=price;
		this.rent=rent;
		this.hasAnOwner=false;
		this.owner=null;
		this.isMortgaged=false;
	}
	
	/**
	 * Gives out the total worth of an instance of this class including the constructions.
	 * @return The total worth of this class' instance.
	 */
	public int getRecWorth(){
		return 0;
	}

	//has to give input 1 for cab squares and railroads
	//1 house = 1, 2 houses = 2, 3 houses = 3, 4 houses = 4, a Hotel = 5, a Skyscraper = 6
	/**
	 * Builds house, hotel and skyscraper according to the need.
	 * 
	 * @requires Owner to have enough money.
	 * @requires The all color group to be owned by the same player and be in the same construction level.
	 * @param i The input integer that describes what is asked to build.
	 * @modifies The buyable deed that it has construction.
	 */
	public void build(int i){
		if(this instanceof deedSquare){
			if(((deedSquare)this).canBuild(i)){
				switch(i){
				case 1:
					((deedSquare)this).setNumOfHouses(1);
					((deedSquare)this).setVisNumOfHouses(1);
					((deedSquare)this).setHasHouse(true);
					((deedSquare)this).getOwner().givePaymentSafe(((deedSquare)this).getCostOfBuilding());
					ConsoleGenerator.write2Console(((deedSquare)this).getName()+" now has its first house.");
					break;
				case 2:
					((deedSquare)this).setNumOfHouses(2);
					((deedSquare)this).setVisNumOfHouses(2);
					((deedSquare)this).getOwner().givePaymentSafe(((deedSquare)this).getCostOfBuilding());
					ConsoleGenerator.write2Console(((deedSquare)this).getName()+" now has its second house.");
					break;
				case 3:
					((deedSquare)this).setNumOfHouses(3);
					((deedSquare)this).setVisNumOfHouses(3);
					((deedSquare)this).getOwner().givePaymentSafe(((deedSquare)this).getCostOfBuilding());
					ConsoleGenerator.write2Console(((deedSquare)this).getName()+" now has its third house.");
					break;
				case 4:
					((deedSquare)this).setNumOfHouses(4);
					((deedSquare)this).setVisNumOfHouses(4);
					((deedSquare)this).getOwner().givePaymentSafe(((deedSquare)this).getCostOfBuilding());
					ConsoleGenerator.write2Console(((deedSquare)this).getName()+" now has its fourth house.");
					break;
				case 5:
					((deedSquare)this).setVisNumOfHouses(0);
					((deedSquare)this).getOwner().givePaymentSafe(((deedSquare)this).getCostOfBuilding());
					((deedSquare)this).setHasHotel(true);
					((deedSquare)this).setHasHouse(false);
					ConsoleGenerator.write2Console(((deedSquare)this).getName()+" now has a hotel.");
					break;
				case 6:
					((deedSquare)this).setHasHotel(false);
					((deedSquare)this).setHasSkyscraper(true);
					((deedSquare)this).getOwner().givePaymentSafe(((deedSquare)this).getCostOfBuilding());
					ConsoleGenerator.write2Console(((deedSquare)this).getName()+" now has a skyscraper.");
					break;
				}
			}

		}else if(this instanceof cabSquare){
			if(((cabSquare)this).canBuild())
				switch(i){
				case 1:
					((cabSquare)this).setHasStand(true);
					((cabSquare)this).getOwner().givePaymentSafe(150);
					ConsoleGenerator.write2Console(((cabSquare)this).getName()+" now has a stand.");
					break;
				}


		}else if(this instanceof RailroadTrackSquare){
			if(((RailroadTrackSquare)this).canBuild())
				switch(i){
				case 1:
					((RailroadTrackSquare)this).setDepot(true);
					((RailroadTrackSquare)this).getOwner().givePaymentSafe(100);
					ConsoleGenerator.write2Console(((RailroadTrackSquare)this).getName()+" now has a depot.");
					break;
				}
		}
	}

	//i from 1 to 4.
	//////
	/**
	 * Removes/Mortgages buyable, depot, house, hotel, skyscraper or stand according to the calling parameter.
	 * When a construction is removed, the deed does not get mortgaged. But it decreases to a lower level.
	 * 
	 * @param i The input integer that describes what is asked to mortgage.
	 * @requires The buyable to have an owner.
	 * @modifies The buyableSquare's situation such as mortgaged, hasHouse, hasHotel etc. according to what is removed.
	 */
	public void mortgageOrRemove(int i){
		//1 for house deconstruction and making money
		//2 for hotel deconstruction and making money
		//3 for skyscraper deconstruction and making money
		//4 for mortgaging the deed
		if(this instanceof deedSquare){
			if(((deedSquare)this).canRemove(i)){
				switch(i){
				case 1:
					((deedSquare)this).setNumOfHouses(((deedSquare)this).getNumOfHouses()-1);
					((deedSquare)this).getOwner().receivePayment(((deedSquare)this).getCostOfBuilding()/2);
					if(((deedSquare)this).getNumOfHouses()==0)
						((deedSquare)this).setHasHouse(false);
					ConsoleGenerator.write2Console(((deedSquare)this).getName()+" 1 house is gone.");
					break;
				case 2:
					((deedSquare)this).setHasHotel(false);
					((deedSquare)this).setNumOfHouses(4);
					((deedSquare)this).getOwner().receivePayment(((deedSquare)this).getCostOfBuilding()/2);
					((deedSquare)this).setHasHouse(true);
					ConsoleGenerator.write2Console(((deedSquare)this).getName()+" now has no hotel.");
					break;
				case 3:
					((deedSquare)this).setHasSkyscraper(false);
					((deedSquare)this).setHasHotel(true);
					((deedSquare)this).getOwner().receivePayment(((deedSquare)this).getCostOfBuilding()/2);
					ConsoleGenerator.write2Console(((deedSquare)this).getName()+" now has no skyscraper house.");
					break;

				case 4:
					((deedSquare)this).setMortgaged(true);
					String name = ((deedSquare)this).getName();
					((deedSquare)this).getOwner().getMortgagedPropertyList().put(name,(deedSquare)this);
					((deedSquare)this).getOwner().getPropertyList().remove(name);
					((deedSquare)this).getOwner().receivePayment(((deedSquare)this).getPrice()/2);
					ConsoleGenerator.write2Console(((deedSquare)this).getName()+" now is mortgaged.");
					break;
				}
			}

			//For the construction of depot input 1. For the mortgaging of the CAB deed input 2.
		}else if(this instanceof cabSquare){
			if(((cabSquare)this).canRemove(i))
				switch(i){
				case 1:
					((cabSquare)this).setHasStand(false);
					((cabSquare)this).getOwner().receivePayment(75);

					ConsoleGenerator.write2Console(((cabSquare)this).getName()+" now has no stand.");

					break;
				case 2:
					((cabSquare)this).setMortgaged(true);
					String name = ((cabSquare)this).getName();
					((cabSquare)this).getOwner().getMortgagedPropertyList().put(name,(cabSquare) this);
					((cabSquare)this).getOwner().receivePayment(((cabSquare)this).getPrice()/2);
					ConsoleGenerator.write2Console(((cabSquare)this).getName()+" now is mortgaged.");
					break;
				}
			//For the construction of depot input 1. For the mortgaging of the railroaad deed input 2.
		}else if(this instanceof RailroadTrackSquare){
			if(((RailroadTrackSquare)this).canRemove(i))
				switch(i){
				case 1:
					((RailroadTrackSquare)this).setDepot(false);
					((RailroadTrackSquare)this).getOwner().receivePayment(50);
					ConsoleGenerator.write2Console(((RailroadTrackSquare)this).getName()+" now has no depot.");
					break;
				case 2:
					((RailroadTrackSquare)this).setMortgaged(true);
					String name = ((RailroadTrackSquare)this).getName();
					((RailroadTrackSquare)this).getOwner().getMortgagedPropertyList().put(name,(RailroadTrackSquare) this);
					((RailroadTrackSquare)this).getOwner().receivePayment(((RailroadTrackSquare)this).getPrice()/2);
					ConsoleGenerator.write2Console(((RailroadTrackSquare)this).getName()+" now is mortgaged.");
					break;
				}
		}else if (this instanceof utilitySquare){//MortgageTheDeed should return 2
			if(((utilitySquare)this).canRemove(i))
				switch(i){
				case 2:
					((utilitySquare)this).setMortgaged(true);
					String name = ((utilitySquare)this).getName();
					((utilitySquare)this).getOwner().getMortgagedPropertyList().put(name,(utilitySquare) this);
					((utilitySquare)this).getOwner().receivePayment(((utilitySquare)this).getPrice()/2);
					ConsoleGenerator.write2Console(((utilitySquare)this).getName()+" now is mortgaged.");
					break;
				}
		}
	}
	/**
	 * Allows players to use hurricane cards in a color group to destroy a level of consturction.
	 * @requires A player to have hurricane card.
	 * @param i Describes what is going to be destroyed in the hurricane.
	 * @modifies The situation of a buyable square. For instance, decreases the construction level etc.
	 * @effects The game flow.
	 */
	public void hurricaneOnOneSquare(int i){
		//1 for house deconstruction and making money
		//2 for hotel deconstruction and making money
		//3 for skyscraper deconstruction and making money
		//4 for mortgaging the deed
		if(this instanceof deedSquare){
			if(((deedSquare)this).canRemove(i)){
				switch(i){
				case 1:
					((deedSquare)this).setNumOfHouses(((deedSquare)this).getNumOfHouses()-1);
					if(((deedSquare)this).getNumOfHouses()==0)
						((deedSquare)this).setHasHouse(false);
					break;
				case 2:
					((deedSquare)this).setHasHotel(false);
					((deedSquare)this).setNumOfHouses(4);
					((deedSquare)this).setHasHouse(true);
					break;
				case 3:
					((deedSquare)this).setHasSkyscraper(false);
					((deedSquare)this).setHasHotel(true);
					break;

				}
			}
		}
	}
	/**
	 * Gives out the cost.
	 * @return The price of this class' instance.
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Changes the cost of a buyable square.
	 * @param price The amount of money that an instance of this class' price is going to be set to.
	 * @modifies The price of a buyable square.
	 */
	public void setPrice(int price) {
		this.price = price;
		((Square)this).updateAllViews();
	}
	
	/**
	 * Gives out the owner of a buyable square.
	 * @return The owner of this class' instance.
	 */
	public Player getOwner() {
		return owner;
	}
	
	/**
	 * Changes the owner of a buyable square.
	 * @param owner The owner that an instance of this class' owner is going to be set to.
	 * @modifies The owner of a buyable square.
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
		((Square)this).updateAllViews();
	}
	
	/**
	 * Set owner in debug mode.
	 * @param owner The owner that an instance of this class' owner is going to be set to.
	 * @modifies The owner of a buyable square.
	 */
	public void setOwnerDebug(Player owner) {
		this.owner = owner;
	}
	/**
	 * Lets the caller know if an instance of this has an owner.
	 * @return True if a buyable square has an owner.
	 */
	public boolean isHasAnOwner() {
		return hasAnOwner;
	}
	/**
	 * Sets the boolean ownership field of a buyable square.
	 * @param hasAnOwner The boolean that is gonna be set for the owner boolean field of an instance.
	 */
	public void setHasAnOwner(boolean hasAnOwner) {
		this.hasAnOwner = hasAnOwner;
		((Square)this).updateAllViews();
	}
	
	/**
	 * Sets the boolean ownership field of a buyable square in debug mode.
	 * @param hasAnOwner The boolean that is gonna be set for the owner boolean field of an instance.
	 */
	public void setHasAnOwnerDebug(boolean hasAnOwner) {
		this.hasAnOwner = hasAnOwner;
	}
	
	/**
	 * Gives out the rent.
	 * @return The rent amount of a buyable square.
	 */
	public int getRent() {
		return rent;
	}
	/**
	 * Changes the rent money of a buyable square's to a certain price.
	 * @param rent The amount of money that a buyable square's rent is gonna be set to.
	 * @modifies The rent field.
	 */
	public void setRent(int rent) {
		this.rent = rent;
		((Square)this).updateAllViews();
	}
	
	/**
	 * 
	 * @return True if mortgaged.
	 */
	public boolean isMortgaged() {
		return isMortgaged;
	}
	/**
	 * Changes the mortgaged field of the instance.
	 * @param isMortgaged Boolean that is going to be set for if this class' instance is mortgaged or not.
	 * @modifies The isMortgaged field.
	 */
	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
		((Square)this).updateAllViews();
	}
	/**
	 * Gives out the mortgage price.
	 * @return The mortgage price of a buyable square.
	 */
	public int getMortgagePrice() {
		// TODO Auto-generated method stub
		return getPrice()/2;
	}
	/**
	 * Informs whether buyable is a deed or not.
	 * @return True if an instance of this is a deed.
	 */
	public boolean isADeedSquare() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Changes the owner of a buyable square.
	 * @param newowner The player that is going to be set as the owner of a buyable square.
	 * @modifies The owner of a buyable square.
	 */
	public void changeOwner(Player newowner){
		this.getOwner().getPropertyList().remove(this.getName());
		this.setOwner(newowner);
		newowner.getPropertyList().put(this.getName(), this);
	}
	
	/**
	 * Changes the owner of a mortgaged square.
	 * @param newowner The player that is going to be set as the owner of a buyable square.
	 * @modifies The owner of a buyable square.
	 * @modifies The buyable's isMortgaged field.
	 * @modifies The mortgaged list of the previous owner.
	 */
	public void changeOwnerMortgaged(Player newowner){
		this.getOwner().getMortgagedPropertyList().remove(this.getName());
		this.setOwner(newowner);
		newowner.getMortgagedPropertyList().put(this.getName(), this);
	}
	/**
	 * Processes the auction in a desired way.
	 * @param g Phe game that auctionable instance is in.
	 * @param p The player who is taking turn in auction.
	 */
	@Override
	public void processAuction(Player p,int price) {
		// TODO process it
		p.buySquareWithPrice(this, price);
	}

}
