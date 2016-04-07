package boardrelated;

import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;

/***
 * Utility Squares, as data types, have different arrival procedures than any other buyable properties.
 * In other words, Utility Squares allow us to own some square and get profit from it in a different way than other
 * squares.
 * 
 * For this reason the abstraction of Utility Squares helps to create Utility Square objects for need and
 * apply its methods in its own way.
 * 
 * Implementation-Specific Documentation: UtilitySquare class extends BuyableSquare class since the buyable deeds
 * differ in behaviour. For example, DeedSquares and UtilitySquares have different attributes towards payment,
 * construction and arrival procedures. For this reason, UtilitySquares override the arrival_proc and payment in
 * their own behaviour.
 *
 * 
 * @author Cem Uyuk
 * @author Utku Evci
 * @version     %I%, %G%
 * @since 1.0
 *
 */

public class utilitySquare extends buyableSquare {
	/**
	 * This only constructor of utilitySquares provides the creation of instances of utility squares.
	 * 
	 * @param name the name that is to be the name of the square
	 * @param squareNumber the number of the square
	 * @param trackNumber the number of the track that this utility square is on
	 * @param colorID the colorID of the square
	 * @param totalPlayers the number of players playing the game
	 * @param price the cost of this utility deed
	 * @param rent the rent cost of this utility deed 
	 * 
	 * 
	 */
	public utilitySquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers,int price,int rent) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers,price,rent);
	}

	/**
	 * This only constructor of utilitySquares provides the creation of instances of utility squares.
	 * 
	 * @param cases Case number that identifies if the utility can remove this product on itself
	 * @requires This utility to have an owner
	 * @return True if the property has an owner
	 * 
	 */

	public boolean canRemove (int cases){
		switch(cases){
		case 1:
			if(isHasAnOwner())
				return true;
		}
		return false;
	}
	/**
	 * In case a user needs to get rid of a utility square and able to get some money when mortgaging,
	 * this procedure helps to get the amount of mortgage.
	 * @see boardrelated.buyableSquare#getRecWorth()
	 * @return The mortgage price of a property.
	 */

	@Override
	public int getRecWorth(){
		return this.getPrice()/2;
	}
	/**
	 * Implementation-Specific Documentation: This arrival procedure overrides the one in the buyable square 
	 * and compeletely different than the deed squares. It basically provides the information of what to do if
	 * someone arrives in this utility. 
	 * 
	 * In addition to that, this method uses one quxilary procedure called payForUtility() to help the process of
	 * rent payment of the arriving player. If the owner player has different number of utilities, method takes
	 * different rent amounts into consideration. If this square has no owner, the arrival procedure just updates 
	 * the views.
	 * 
	 * @param p Needs to be provided as the player who arrived on this utility
	 * @param g Needs to be provided for the method to use the dice
	 * @requires A player to be landed on 
	 * @requires This utility to have an owner
	 * @modifies Increases the amount of money that owner has by the rent amount
	 * @modifies Decreases the amount of money that the arriving player has by the rent amount
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 * 
	 */

	@Override
	public void arrival_proc(Player p, Game g) {
		// TODO Auto-generated method stub
		//If 0, the proc below with the number of cabs else dice roll
		if(this.isHasAnOwner() && p!=this.getOwner()){
			if(p.numOfUtilities()==2){
				payForUtility(p, g, 2, 10);
			}else if(p.numOfUtilities()==3){
				payForUtility(p, g, 3, 20);
			}else if(p.numOfUtilities()==4){
				payForUtility(p, g, 4, 40);
			}else if(p.numOfUtilities()==5){
				payForUtility(p, g, 5, 80);
			}else if(p.numOfUtilities()==6){
				payForUtility(p, g, 6, 100);
			}else if(p.numOfUtilities()==7){
				payForUtility(p, g, 7, 120);
			}else if(p.numOfUtilities()==8){
				payForUtility(p, g, 8, 150);
			}else{
				payForUtility(p, g, 1, 4);
			}
			((Square)this).updateAllViews();
		}	
	}
	/**
	 * This method makes a payment from one player to another one considering the number of the utilities
	 * that this utility's owner has.
	 * 
	 * @param p is the player who is supposed to pay the amount of rent
	 * @param g is the game that this payment is taking place
	 * @param util is the number of utilities that this utilities owner has
	 * @param mult is the real rent amount
	 * 
	 * @modifies decreases the balance of the arriving player by the rent amount
	 * @modifies increases the balance of the owner player by the rent amount
	 */

	public void payForUtility(Player p, Game g, int util, int mult){
		p.givePayment(g.getTheDice().getTotalDice()*mult,g);
		this.getOwner().receivePayment(g.getTheDice().getTotalDice()*mult);
		ConsoleGenerator.write2Console("Player "+p.getPlayerName()+" rolled "
				+g.getTheDice().getTotalDice()+" and paid "+mult+" times the dice that is "+
				+g.getTheDice().getTotalDice()*mult+"$ to "+this.getOwner().getPlayerName()
				+" because he has "+util+" utility(s).");
	}
}
