package boardrelated;

import java.util.ArrayList;

import backend.ConsoleGenerator;
import backend.DialogGenerator;
import backend.Game;
import backend.Player;
/**
 * This class as the sub-type of buyableSquares allows us progress in a manner that
 * the users can buy deed squares and in addition to that build some houses, hotels and skyscrapers on.
 * 
 * It allows us to use a declared behavior of house, hotel and skyscraper properties in many different ways such as the
 * different rent policies under different conditions.
 * 
 * @author Cem Uyuk
 *
 */
public class deedSquare extends buyableSquare{
	private int rentFor1House;
	private int rentFor2House;
	private int rentFor3House;
	private int rentFor4House;
	private int rentForHotel;
	private int rentForSkyscraper;
	private int costOfBuilding;

	private boolean hasHouse;
	private boolean hasHotel;
	private boolean hasSkyscraper;

	private int numOfHouses;
	private int visNumOfHouses;

	public int getVisNumOfHouses() {
		return visNumOfHouses;
	}
	public void setVisNumOfHouses(int visNumOfHouses) {
		this.visNumOfHouses = visNumOfHouses;
	}

	private ArrayList<deedSquare> colorGroup = new ArrayList<deedSquare>();

	/**
	 * This is the constructor of deedSquare class. It creates instances of the class.
	 * @param name The name of the square.
	 * @param squareNumber The number of the square which is sort of tag.
	 * @param trackNumber Number of the track that this class' instance is on.
	 * @param colorID Color Identification number of this class' instance.
	 * @param totalPlayers The number of total players on its instance.
	 * @param price The cost of this class' instance.
	 * @param rent The rent amount of the deed.
	 * @param house1Rent Rent cost of 1 house.
	 * @param house2Rent Rent cost of 2 houses.
	 * @param house3Rent Rent cost of 3 houses.
	 * @param house4Rent Rent cost of 4 houses.
	 * @param hotelRent Rent cost of a hotel.
	 * @param skyScraperRent Rent cost of a skyscraper.
	 * @param buildingCost The cost of building any structure.
	 */
	public deedSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers,int price,int rent,
			int house1Rent, int house2Rent, int house3Rent, int house4Rent, int hotelRent, int skyScraperRent, int buildingCost) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers,price,rent);

		this.numOfHouses = 0;
		this.hasHouse = false;
		this.hasHotel = false;
		this.hasSkyscraper = false;

		this.costOfBuilding=buildingCost;

		this.rentFor1House=house1Rent;
		this.rentFor2House=house2Rent;
		this.rentFor3House=house3Rent;
		this.rentFor4House=house4Rent;

		this.rentForHotel=hotelRent;
		this.rentForSkyscraper=skyScraperRent;

	}
	//for rent payment in arrival proc.
	/**
	 * This method transfers some amount of money from the input player to another by the input amount.
	 * @param p Player who takes place in the payment process.
	 * @param amount Amount of money to be transferred from the arriving player to the deed owner.
	 * @param g The game in which the player p and the deed exist.
	 */
	public void getItPaid(Player p, int amount, Game g){
		p.givePayment(amount,g);
		this.getOwner().receivePayment(amount);
		ConsoleGenerator.write2Console("Player "+p.getPlayerName()+" paid "
				+amount+"$ rent to "+this.getOwner().getPlayerName()+".");
	}
	/**
	 * This method gives out the total worth of a deedSquare instance by taking an input integer ranging from 1 to 4. 
	 * This integer describes the current situation of the deed. This means if the deed has some houses or not and 
	 * even hotels and skyscrapers.
	 * 
	 * @requires The input integer to range from 1 to 4.
	 * @param cases To let know what is the current situation on the deedSquare instance.
	 * @return The total amount of worth of a deedSquare instance.
	 */
	public int getWorthHelper(int cases){
		switch(cases){
		case 1:
			int total1=this.getCostOfBuilding()/2 + this.getPrice()/2;
			return total1;
		case 2:
			int total2=this.getCostOfBuilding() + this.getPrice()/2;
			return total2;
		case 3:
			int total3=(this.getCostOfBuilding()*3)/2 + this.getPrice()/2;
			return total3;
		case 4:
			int total4=this.getCostOfBuilding()*2 + this.getPrice()/2;
			return total4;
		}
		return 0;
	}
	/**
	 * This method gives out the total recieevable worth of a deedSquare without asking for an input integer.
	 * 
	 * @return The total recievable worth of a deedSquare.
	 * @see boardrelated.buyableSquare#getRecWorth()
	 */
	@Override
	public int getRecWorth(){
		if(this.hasHouse){
			return this.getWorthHelper(this.numOfHouses);
		}else if(this.hasHotel){
			return this.getWorthHelper(4) + this.costOfBuilding/2;
		}else if(this.hasSkyscraper){
			return this.getWorthHelper(4) + this.costOfBuilding;
		}else{
			return this.getPrice();
		}
	}
	/**
	 * This method applies the proper process to the arriving player to the deed square.
	 * It deducts half of the amount if the player chooses the Comped Room option. 
	 * 
	 * @param p Player who just arrived on the deed square.
	 * @param g Game in which the arriving player and the deed square exist.
	 * @requires A player to land on it.
	 * @requires Another player to own the place.
	 * @modifies Increases the balance of the owner player.
	 * @modifies Decreases amount of money that the arriving player.
	 * @modifies The card deck of the arriving player, if the he/she uses comped room card.
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */

	@Override
	public void arrival_proc(Player p, Game g) {
		if(this.isHasAnOwner() && p!=this.getOwner()){
			boolean isComped=false;

			if(p.isCompedRoom()){
				if(DialogGenerator.AskYesNoDialog("Comped Room?", "Do you want to use Comped Room Card")){
					p.useCard("Comped Room", g);
					isComped=true;
					ConsoleGenerator.write2Console(p.getPlayerName()+"is not using since he/she uses Comped Room Card");
				}
			}
			else if(hasSkyscraper){
				if(isComped){
					ConsoleGenerator.write2Console(p.getPlayerName()+" does not make a rent payment"
							+" for the hotel on this land because he/she has a Comped Room card.");
				}else{
					ConsoleGenerator.write2Console("Wooopss! There is a skyscraper here!");
					getItPaid(p,this.rentForSkyscraper,g);
				}
			}else if(hasHotel){
				if(isComped){
					ConsoleGenerator.write2Console(p.getPlayerName()+" does not make a rent payment"
							+" for the hotel on this land because he/she has a Comped Room card.");
				}else{
					ConsoleGenerator.write2Console("Wooopss! There is a hotel here!");
					getItPaid(p,this.rentForHotel,g);
				}
			}else if(this.numOfHouses==0){
				if(this.monopolyOwner()){
					if(isComped){
						ConsoleGenerator.write2Console(p.getPlayerName()+" does not make a rent payment"
								+" because he/she has a Comped Room card.");
					}else{
						ConsoleGenerator.write2Console("This deed is a part of monopoly: 3 Times the rent!!");
						getItPaid(p,this.rent*3,g);
					}
				}else if(this.majorityOwner()){
					if(isComped){
						ConsoleGenerator.write2Console(p.getPlayerName()+" does not make a rent payment"
								+" because he/she has a Comped Room card.");
					}else{
						ConsoleGenerator.write2Console("This deed is a part of majority: 2 Times the rent!!");
						getItPaid(p,this.rent*2,g);
					}
				}else{
					if(isComped){
						ConsoleGenerator.write2Console(p.getPlayerName()+" does not make a rent payment"
								+" because he/she has a Comped Room card.");
					}else{
						ConsoleGenerator.write2Console("This deed is a regular deed: normal the rent!!");
						getItPaid(p,this.rent,g);
					}
				}
			}else if(numOfHouses==1){
				if(isComped){
					ConsoleGenerator.write2Console(p.getPlayerName()+" does not make a rent payment"
							+" for a house on this land because he/she has a Comped Room card.");
				}else{
					ConsoleGenerator.write2Console("Wooopss! There is a house here!");
					getItPaid(p,this.rentFor1House,g);
				}
			}else if(numOfHouses==2){
				if(isComped){
					ConsoleGenerator.write2Console(p.getPlayerName()+" does not make a rent payment"
							+" for 2 houses on this land because he/she has a Comped Room card.");
				}else{
					ConsoleGenerator.write2Console("Wooopss! There are 2 houses here!");
					getItPaid(p,this.rentFor2House,g);
				}
			}else if(numOfHouses==3){
				if(isComped){
					ConsoleGenerator.write2Console(p.getPlayerName()+" does not make a rent payment"
							+" for 3 houses on this land because he/she has a Comped Room card.");
				}else{
					ConsoleGenerator.write2Console("Wooopss! There are 2 houses here!");
					getItPaid(p,this.rentFor3House,g);
				}
			}else if(numOfHouses==4){
				if(isComped){
					ConsoleGenerator.write2Console(p.getPlayerName()+" does not make a rent payment"
							+" for 4 houses on this land because he/she has a Comped Room card.");
				}else{
					ConsoleGenerator.write2Console("Wooopss! There are 4 houses here!");
					getItPaid(p,this.rentFor4House,g);
				}
			}
		}
		((Square)this).updateAllViews();
	}
	/**
	 * This method decreases the structure level of a deedSquare by 1. To be more specific,
	 * if there are if there is a hotel, it lowers to the level that 4 houses exist,
	 * if this method is called upon 4 houses, the number of houses go down to 3, and so on.
	 * 
	 * @requires The deedSquare to have some sort of structure on it.
	 * @requires All the color group to be on the same construction level or 1 higher or 1 lower.
	 * @param cases The integer number that describes what is asked to removed.
	 * @modifies The constructions on the deedSquare instance which called this method upon
	 * @return True if the owner of this deedSquare's instance can remove some structure out
	 */

	public boolean canRemove(int cases){
		if(this.monopolyOwner()){
			switch(cases){
			//TODO Need to add more checks. 
			case 1: // 1 is to remove a house
				if(this.levelBoolForRemove() && this.numOfHouses>0)
					return true;
				return false;
			case 2: // 2 is to remove a hotel
				if(this.hasHotel && this.readyToRemoveHotel())
					return true;
				return false;
			case 3: // 3 is to remove a skyscraper
				if(this.hasSkyscraper && this.readyToRemoveSkyscraper())
					return true;
				return false;
			case 4: // 4 is to mortgage a field
				if(!this.hasSkyscraper && !this.hasHotel && !this.hasHouse)
					return true;
				return false;
			}
		}else if(this.majorityOwner()){
			switch(cases){
			//TODO Need to add more checks. 
			case 1: // 1 is to build a house
				if(this.levelBoolForRemove() && this.numOfHouses==0)
					return true;
				return false;
			case 2:
				if(this.hasHotel && this.readyToRemoveHotel())
					return true;
				return false;

			case 3:
				return false;
			case 4: // 4 is to mortgage a field
				if(!this.hasSkyscraper && !this.hasHotel && !this.hasHouse)
					return true;
				return false;
			}
		}else{
			switch(cases){
			//TODO Need to add more checks. 
			case 1: // 1 is to remove a house
				if(this.levelBoolForRemove() && this.numOfHouses>0)
					return true;
				return false;
			case 2: // 2 is to remove a hotel
				if(this.hasHotel && this.readyToRemoveHotel())
					return true;
				return false;
			case 3: // 3 is to remove a skyscraper
				if(this.hasSkyscraper && this.readyToRemoveSkyscraper())
					return true;
				return false;
			case 4: // 4 is to mortgage a field
				if(!this.hasSkyscraper && !this.hasHotel && !this.hasHouse)
					return true;
				return false;
			}
		}

		return false;
	}
	/**
	 * This method checks if a player can build on a deedSquare. If yes, it returns true.
	 * To be more specific, input 1 checks if a player can build a house, 2 a hotel and so on.
	 * 
	 * @requires The owner to have enough balance.
	 * @requires The color group to be owned by the same player and be in the same construction level.
	 * @param cases The integer number that describes what is asked to build.
	 * @return True if a player can build on it.
	 */

	public boolean canBuild(int cases){
		if(this.monopolyOwner() && this.getOwner().getBalance()>=costOfBuilding){
			switch(cases){
			case 1: 
				if(this.levelBool() && this.numOfHouses==0)
					return true;
				return false;
			case 2: 
				if(this.levelBool() && this.numOfHouses==1)
					return true;
				return false;
			case 3: 
				if(this.levelBool() && this.numOfHouses==2)
					return true;
				return false;
			case 4:
				if(this.levelBool() && this.numOfHouses==3)
					return true;
				return false;
			case 5:
				if(!this.hasHotel && this.readyForHotel())
					return true;
				return false;
			case 6:
				if(!this.hasSkyscraper && this.readyForSkyscraper())
					return true;
				return false;
			}
		}else if(this.majorityOwner() && this.getOwner().getBalance()>=costOfBuilding){
			switch(cases){
			case 1: // 1 is to build a house
				if(this.levelBool() && this.numOfHouses==0)
					return true;
				return false;
			case 2: // 2 is to build a hotel
				if(this.levelBool() && this.numOfHouses==1)
					return true;
				return false;
			case 3: // 3 is to build a skyscraper
				if(this.levelBool() && this.numOfHouses==2)
					return true;
				return false;
			case 4:
				if(levelBool() && this.numOfHouses==3)
					return true;
				return false;
			case 5:
				if(!this.hasHotel && this.readyForHotel())
					return true;
				return false;
			case 6:
				return false;
			}
		}
		return false;
	}
	/**
	 * This method lets know if a color group is on the same level or 1 higher.
	 * @return True if the color group is on the same construction level or only 1 difference in levels.
	 * To be more specific, levels follow as: number of houses, hotel, skyscraper.
	 */
	public boolean levelBool(){
		((Square)this).updateAllViews();
		ArrayList<deedSquare> group = new ArrayList<deedSquare>();
		group=sameOwnerColorGroupButItself();
		for(int i=0; i<group.size(); i++){
			if(numOfHouses==group.get(i).getNumOfHouses() || (numOfHouses+1)==group.get(i).getNumOfHouses()){
				return true;
			}
		}
		return false;
	}

	/**
	 * This method checks the level of construction in a color group for removal. If it returns true, then it means 
	 * a construction is removable from the deedSquare
	 * @return True if the color group is in the same level or 1 less which allow the deconstruction.
	 */
	public boolean levelBoolForRemove(){
		((Square)this).updateAllViews();
		ArrayList<deedSquare> group = new ArrayList<deedSquare>();
		group=sameOwnerColorGroup();
		for(int i=0; i<group.size(); i++){
			if(this.numOfHouses==group.get(i).getNumOfHouses() || (this.numOfHouses-1)==group.get(i).getNumOfHouses()){
				return true;
			}
		}
		return false;
	}

	/**
	 * Lets us know if a hotel can be removed from a deed.
	 * @requires The deedSquare to have a hotel on it.
	 * @return True if a deed can remove a hotel.
	 */
	public boolean readyToRemoveHotel(){
		for(int i=0; i<sameOwnerColorGroup().size(); i++){
			if(!sameOwnerColorGroup().get(i).hasHotel || sameOwnerColorGroup().get(i).numOfHouses!=4)
				return false;
		}
		return true;
	}

	/**
	 * Lets us know if a skyscraper can be removed from a deed.
	 * @requires The deedSquare to have a skyscraper on it.
	 * @return True if a deed can remove a skyscraper.
	 */
	public boolean readyToRemoveSkyscraper(){
		for(int i=0; i<sameOwnerColorGroup().size(); i++){
			if(!sameOwnerColorGroup().get(i).hasHotel || !sameOwnerColorGroup().get(i).hasSkyscraper)
				return false;
		}
		return true;
	}
	/**
	 * Lets us know if a hotel can be built on a deed.
	 * @requires The deedSquare to have 4 houses on it.
	 * @return True if a deed can build a hotel.
	 */
	public boolean readyForHotel(){
		for(int i=0; i<sameOwnerColorGroup().size(); i++){
			if(sameOwnerColorGroup().get(i).getNumOfHouses()!=4)
				return false;
		}
		return true;
	}

	/**
	 * Lets us know if a skyscraper can be built from on a deed.
	 * @requires The deedSquare to have a hotel on it.
	 * @return True if a deed can build a skyscraper.
	 */
	public boolean readyForSkyscraper(){
		for(int i=0; i<sameOwnerColorGroup().size(); i++){
			if(!sameOwnerColorGroup().get(i).hasHotel || sameOwnerColorGroup().get(i).hasSkyscraper)
				return false;
		}
		return true;
	}
	/**
	 * Gives out the array list of the color group except the instance that this method is called.
	 * @return The array list of the color group but itself.
	 */
	public ArrayList<deedSquare> sameOwnerColorGroupButItself(){
		ArrayList<deedSquare> groupButItself = new ArrayList<deedSquare>();
		for(int i=0;i<colorGroup.size();i++){
			if(colorGroup.get(i)!=this && this.getOwner()==colorGroup.get(i).getOwner()){
				groupButItself.add(colorGroup.get(i));
			}
		}
		return groupButItself;
	}

	/**
	 * Gives out the array list of the color group squares that are owned by the same player..
	 * @return The array list of the color group squares that are owned by the same player.
	 */
	public ArrayList <deedSquare> sameOwnerColorGroup(){
		ArrayList<deedSquare> groupButItself = new ArrayList<deedSquare>();
		for(int i=0;i<colorGroup.size();i++){
			if(this.getOwner()==colorGroup.get(i).getOwner()){
				groupButItself.add(colorGroup.get(i));
			}
		}
		return groupButItself;
	}

	/**
	 * Gives out the number of the squares that are owned by the same player.
	 * @return The number of squares that are owned by the same player.
	 */
	public int numberOfCommonlyOwned(){
		int temp = 0;
		for (int i=0; i<colorGroup.size(); i++){
			if(this.getOwner() == colorGroup.get(i).getOwner())
				temp=temp+1;
		}
		return temp;
	}

	/**
	 * Lets know if a player has the posession over a color group.
	 * @requires The owner to have most of the squares in a color group
	 * @return True if the owner is a majority owner.
	 */
	public boolean majorityOwner(){
		int majorityLength = colorGroup.size()-1;
		int numOwned = this.numberOfCommonlyOwned();
		if(colorGroup.size()>2 && majorityLength == numOwned)
			return true;
		return false;
	}
	/**
	 * Lets know if a player has all of the squares in a color group.
	 * @requires The owner to have all of the squares in a color group
	 * @return True if the owner is a monopoly owner.
	 */
	public boolean monopolyOwner(){
		int monopolyLength = colorGroup.size();
		int numOwned = this.numberOfCommonlyOwned();
		if(monopolyLength == numOwned)
			return true;
		return false;
	}


	/**
	 * Adds an input deed to the color group of another deed.
	 * @param d The input deedSquare to be put in the color group.
	 */
	public void addInColorGroup(deedSquare d){
		colorGroup.add(d);
	}

	/**
	 * Gives out the number of houses on it when called.
	 * @return The number of houses on a deedSquare.
	 */
	public int getNumOfHouses() {
		return numOfHouses;
	}

	/**
	 * Changes the number of houses on a deed square.
	 * @param numOfHouses The integer number that is going to be the new number of houses on a deedSquare.
	 */
	public void setNumOfHouses(int numOfHouses) {
		this.numOfHouses = numOfHouses;
		((Square)this).updateAllViews();
	}
	/**
	 * Lets us know if a deed has a hotel on it.
	 * @requires The deed to have a hotel on it.
	 * @return True if there is a hotel on a deed.
	 */
	public boolean hasHotel() {
		return hasHotel;
	}
	/**
	 * Changes if the deed has a hotel or not.
	 * @param ownsHotel Boolean that is going to be set if the deed has a hotel.
	 */
	public void setHasHotel(boolean ownsHotel) {
		this.hasHotel = ownsHotel;
		((Square)this).updateAllViews();
	}
	/**
	 * Lets know if a deed has a skyscraper.
	 * @requires The deed to have a skyscraper.
	 * @return True if the deed has a skyscraper on it.
	 */
	public boolean hasSkyscraper() {
		return hasSkyscraper;
	}

	/**
	 * Changes if a deed has a skyscraper or not.
	 * @param ownsSkyscraper The new boolean that is going to determine if a deed has a skyscraper on.
	 */
	public void setHasSkyscraper(boolean ownsSkyscraper) {
		this.hasSkyscraper = ownsSkyscraper;
		((Square)this).updateAllViews();
	}

	/**
	 * Gives out the amount of rent for 1 house on the deed.
	 * @return The rent for 1 house.
	 */
	public int getRentFor1House() {
		return rentFor1House;
	}

	/**
	 * Gives out the amount of rent for 2 houses on the deed.
	 * @return The rent for 2 houses.
	 */
	public int getRentFor2House() {
		return rentFor2House;
	}

	/**
	 * Gives out the amount of rent for 3 houses on the deed.
	 * @return The rent for 3 houses.
	 */
	public int getRentFor3House() {
		return rentFor3House;
	}

	/**
	 * Gives out the amount of rent for 4 houses on the deed.
	 * @return The rent for 4 houses.
	 */
	public int getRentFor4House() {
		return rentFor4House;
	}

	/**
	 * Gives out the amount of rent for a hotel on the deed.
	 * @return The rent for a hotel.
	 */
	public int getRentForHotel() {
		return rentForHotel;
	}

	/**
	 * Gives out the amount of rent for a skyscraper on the deed.
	 * @return The rent for a skyscraper.
	 */
	public int getRentForSkyscraper() {
		return rentForSkyscraper;
	}

	/**
	 * Changes the rent amount of a skyscraper.
	 * @param skyscraperRent The new rent amount of a skyscraper.
	 */
	public void setSkyscraperRent(int skyscraperRent) {
		rentForSkyscraper = skyscraperRent;
		((Square)this).updateAllViews();
	}

	/**
	 * Lets know if a deed has a house.
	 * @return True if a deed has a house on.
	 */
	public boolean hasHouse() {
		return hasHouse;
	}

	/**
	 * Changes if a deed has a house or not.
	 * @param hasHouse The new boolean to place if a deed has a house or not.
	 */
	public void setHasHouse(boolean hasHouse) {
		this.hasHouse = hasHouse;
		((Square)this).updateAllViews();
	}

	/**
	 * Gives out the cost to build on a deed.
	 * @return The cost of building on a deed.
	 */
	public int getCostOfBuilding() {
		return costOfBuilding;
	}


	/**
	 * This method gives out the number of hotels on a deed.
	 * @return The number of hotels on a deed.
	 */
	public int getNumberOfHotels(){
		if(hasHotel){
			return 1;
		}else{
			return 0;
		}
	}

	/**
	 * This method gives out the number of skyscrapers on a deed.
	 * @return The number of skyscrapers on a deed.
	 */
	public int getNumberOfSkyscrapers(){
		if(hasSkyscraper){
			return 1;
		}else{
			return 0;
		}
	}

	/**
	 * Changes the cost to build on a deed.
	 * @param costOfBuilding The new cost of building
	 */
	public void setCostOfBuilding(int costOfBuilding) {
		this.costOfBuilding = costOfBuilding;
		((Square)this).updateAllViews();
	}

	/**
	 * Gives out the color group of a deed.
	 * @return The array list that holds the color group of a deed.
	 */
	public ArrayList<deedSquare> getColorGroup() {
		return colorGroup;
	}

	/**
	 * Changes the color group list of a deed.
	 * @param colorGroup The array list that is going to be a new color group of a deed
	 */
	public void setColorGroup(ArrayList<deedSquare> colorGroup) {
		this.colorGroup = colorGroup;
		((Square)this).updateAllViews();
	}

	/**
	 * Lets know if a deed has a house.
	 * @return True if a deed has a house.
	 */
	public boolean isHasHouse() {
		return hasHouse;
	}

	/**
	 * Lets know if a deed has a hotel.
	 * @return True if a deed has a hotel.
	 */
	public boolean isHasHotel() {
		return hasHotel;
	}

	/**
	 * Lets know if a deed has a skyscraper.
	 * @return True if a deed has a skyscraper.
	 */
	public boolean isHasSkyscraper() {
		return hasSkyscraper;
	}

}
