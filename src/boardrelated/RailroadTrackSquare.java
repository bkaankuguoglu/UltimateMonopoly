package boardrelated;

import java.awt.Color;

import backend.ConsoleGenerator;
import backend.DialogGenerator;
import backend.Dice;
import backend.Game;
import backend.Player;

/**
 * This class is provided to meet the needs of the railroads. Railroad squares provide both the
 * behavior of the utility squares and the track changing squares.  RailroadTrackChangeSquare abstraction provides
 * the both classes uses in one.
 * 
 * Its instances provide the procedures that are to be applied upon a game and a player.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */

public class RailroadTrackSquare extends buyableSquare{
	private Square nxSq2;
	private Square prSq2;
	private boolean depot;


	/**
	 * Creates an instance of the railroadTrackSquare class. Sets the second previous and the next squares that are 
	 * connected to it null as default as well as depot.
	 * 
	 * @param name the name of the square
	 * @param squareNumber the number of the square which is sort of tag
	 * @param trackNumber number of the track that this' instance is on
	 * @param colorID of this square's instance
	 * @param totalPlayers the number of total players on its instance
	 * @param price Cost of this railroadTrackChange square
	 * @param rent Cost of the rent without construction when a player lands on 
	 * @modifies sets the second next and previous squares to null by default
	 */
	public RailroadTrackSquare(String name, int squareNumber,
			int trackNumber, int colorID, int totalPlayers, int price, int rent) {
		super(name, squareNumber, trackNumber, colorID, totalPlayers, price, rent);
		// TODO Auto-generated constructor stub
		this.nxSq2=null;
		this.prSq2=null;
		depot=false;
	}

	/**
	 * Once a player has a railroad, this procedure lets know if the player can add a depot on it
	 * 
	 * @return True if the player has enough money to build and the square has no depot on it
	 * 
	 */
	public boolean canBuild(){
		((Square)this).updateAllViews();
		//TODO: Need to add more checks. For instance, if the player has enough money or not.
		if(!this.depot && this.getOwner().getBalance()>=this.getRent()) return true;
		return false;
	}
	/**
	 * This procedure allows us to know if we can remove the property or the depot. or neither
	 * 
	 * @param cases the integer that identifies the kind of removal
	 * @return true if the case is 1 and there is an owner and a depot. 
	 * @return true if the case is 2 and there is no depot but an owner
	 */

	public boolean canRemove(int cases){
		//TODO: need to do more check. For instance: if the owner has enough money.
		switch(cases){
		case 1:
			if(this.hasDepot() && this.isHasAnOwner()) 
				return true;
			return false;
		case 2:
			if(!this.hasDepot() && this.isHasAnOwner()) 
				return true;
			return false;
		}
		((Square)this).updateAllViews();
		return false;
	}

	/**
	 * This procedure gives the next square for track change squares. The next square of the 
	 * track change squares depend on the dice. According to the total dice, the next square that
	 * the player will move depends
	 * 
	 * @param d dice that determines the total
	 * @param p player that rolls the square
	 * 
	 * @requires the player to pass from it with a dice roll
	 * @return the next square that the player is gonna move to
	 * 
	 * @see boardrelated.Square#next_square(backend.Dice, backend.Player)
	 */

	@Override
	public Square next_square(Dice d, Player p){
		int trackNo = p.getTrackNumber();
		ConsoleGenerator.write2Console("Track no is "+trackNo);
		if(trackNo==1){ 
			if(!p.isReverseMoving()){
				if(d.isOdd()){
					return getNxSq2();
				} else {
					return getNxSq();
				}
			} else {
				if(d.isOdd()){
					return getPrSq2();
				} else {
					return getPrSq();
				}
			}
		} else if (trackNo==2){
			if(!p.isReverseMoving()){
				if(d.isOdd()){
					return getNxSq();
				} else {
					return getNxSq2();
				}
			} else {
				if(d.isOdd()){
					return this.getPrSq();
				} else {
					return getPrSq2();
				}
			}
		} else if (trackNo==3){
			if(!p.isReverseMoving()){
				if(d.isOdd()){
					return getNxSq2();
				} else {
					return getNxSq();
				}
			} else {
				if(d.isOdd()){
					return getPrSq2();
				} else {
					return getPrSq();
				}
			}
		}
		((Square)this).updateAllViews();
		return null;
	}

	@Override public int getRecWorth(){
		if(this.hasDepot())
			return this.getPrice()/2 + 50;
		return this.getPrice()/2;
	}
	
	/**
	 * The arrival process of the track change squares that makes the payment from the arriving player
	 * to the owner. 
	 * 
	 * @param p player that arrived on
	 * @param g game that the player and the square is on
	 * 
	 * @requires the arriving player to have enough money
	 * @modifies decreases the balance of the arriving player
	 * @modifies increases the balance of the owner player
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */
	@Override
	public void arrival_proc(Player p, Game g) {
		//If 0, the proc below with the number of cabs else dice roll
		if(this.isHasAnOwner() && p!=this.getOwner()){
			if(this.hasDepot()){//If there is a depot here, owner gets 2 times the rent
				if(this.getOwner().numOfRailroads()==2){
					railroadPay(p,100,g);
				}else if(this.getOwner().numOfRailroads()==3){
					railroadPay(p,200,g);
				}else if(this.getOwner().numOfRailroads()==4){
					railroadPay(p,400,g);
				}else{
					railroadPay(p,50,g);
				}
			}else{//if there is no railroad depot, the owner gets the regular rent
				if(this.getOwner().numOfRailroads()==2){
					railroadPay(p,50,g);
				}else if(this.getOwner().numOfRailroads()==3){
					railroadPay(p,100,g);
				}else if(this.getOwner().numOfRailroads()==4){
					railroadPay(p, 200, g);
				}else{
					railroadPay(p,25,g);
				}
			}
		}	
		((Square)this).updateAllViews();
	}
	/**
	 * Specific method that makes the amount of payment from one player to an owner of a RailroadTrackSquare.
	 * Implementation-Specific: If the player has a SpecialPricing card, then the money deducted is less than the
	 * rent, otherwise the amount depends on the number of utilities that the owner has.
	 * 
	 * @param p the player who landed on the RailroadTrackSquare
	 * @param amount is the amount of the money that will be transferred from one player to another
	 * @param g the game in which the player and the square exist
	 * 
	 * @modifies decreases the balance of the arrived player
	 * @modifies increases the balance of the owner player
	 * 
	 */
	public void railroadPay(Player p, int amount, Game g){
		if(p.isSpecialPricing()){
			if(DialogGenerator.AskYesNoDialog("Do you want to use Special Princin Card","Special Pricin?")){
				p.useCard("Special Online Pricing", g);
				payForRailroad(amount/2,p, g);
				ConsoleGenerator.write2Console(p.getPlayerName()+" paid half of the railroad rent "
						+ "because he had special pricing card.");
			}
			else{
				payForRailroad(amount,p,g);
			}
		}else{
			payForRailroad(amount,p,g);
		}
		((Square)this).updateAllViews();
	}
	/**
	 * This method is a helper method for other Railroad payment methods. It does not check for the special pricing
	 * card. Just directly makes the payment from one player to another.
	 * 
	 * @param amount the player who landed on the RailroadTrackSquare
	 * @param p Player is the amount of the money that will be transferred from one player to another
	 * @param g the game in which the player and the square exist
	 * 
	 * @modifies decreases the balance of the arrived player
	 * @modifies increases the balance of the owner player
	 * 
	 */
	public void payForRailroad(int amount, Player p,Game g){
		p.givePayment(amount,g);
		if(this.isHasAnOwner()){
			this.getOwner().receivePayment(amount);
			ConsoleGenerator.write2Console("Player "+p.getPlayerName()+" paid "+amount+
					"$ rent to"+this.getOwner().getPlayerName()+" for his cab company(s).");
		}
	}

	/**
	 * 
	 * @return If this has a depot.
	 */
	public boolean hasDepot() {
		return depot;
	}

	/**
	 * Changes the depot field of this' instance.
	 * @param depot Boolean that is going to be set as the isDepot field of this.
	 * @modifies The depot field of this' instance.
	 */
	public void setDepot(boolean depot) {
		this.depot = depot;
		((Square)this).updateAllViews();
	}

	/**
	 * Gives out the 2nd next squre of this.
	 * @return The 2nd next square of this' instance.
	 */
	public Square getNxSq2() {
		return nxSq2;
	}

	/**
	 * Changes the 2nd next square.
	 * @param nxSq2 The square that is going to be set as the 2nd next square of this.
	 * @modifies  nxSq2 of this' instance.
	 */
	public void setNxSq2(Square nxSq2) {
		this.nxSq2 = nxSq2;
	}

	/**
	 * Gives out the 2nd previous squre of this.
	 * @return The 2nd previous square of this' instance.
	 */
	public Square getPrSq2() {
		return prSq2;
	}

	/**
	 * Changes the 2nd previous square.
	 * @param prSq2 The square that is going to be set as the 2nd previous square of this.
	 * @modifies  prSq1 of this' instance.
	 */
	public void setPrSq2(Square prSq2) {
		this.prSq2 = prSq2;
	}

	/**
	 * Gives out the number of depots.
	 * @return The number of depots on a railroad.
	 */
	public int getNumOfDepot(){
		if(depot==true){
			return 1;
		}
		else{
			return 0;
		}
	}



}
