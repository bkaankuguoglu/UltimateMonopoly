package boardrelated;

import backend.ConsoleGenerator;
import backend.DialogGenerator;
import backend.Game;
import backend.Player;
/**
 * This class as the sub-type of buyableSquares allows us progress in a manner that
 * the users can buy cab squares and in addition to that build some stands.
 * 
 * It allows us to use a declared behavior of cab stands properties in many different ways such as the
 * different rent policies under different conditions.
 * 
 * @author Cem Uyuk
 *
 */
public class cabSquare extends buyableSquare {

	private boolean hasStand;
	/**
	 * 
	 * @param name The name of the square.
	 * @param squareNumber The number of the square which is sort of tag.
	 * @param trackNumber Number of the track that this class' instance is on.
	 * @param colorID Color Identification number of this class' instance.
	 * @param totalPlayers The number of total players on its instance.
	 * @param price The cost of this class' instance.
	 * 
	 * @modifies The hasStand field to false as default.
	 */
	public cabSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers,int price) {
		// TODO Auto-generated constructor stub
		super(name,  squareNumber, trackNumber,  colorID, totalPlayers,price, 30);
		this.hasStand=false;

	}
	/**
	 * Lets caller know if the owner can build a stand on it.
	 * @requires To have an owner
	 * @requires The owner to have enough money.
	 * @requires No stand.
	 * @return True if this has no stand on it and has an owner and also the owner has enough money.
	 */
	public boolean canBuild(){
		if(!hasStand && getOwner().getBalance() >= this.getRent() && isHasAnOwner()) return true;
		return false;
	}

	/**
	 * Lets caller know if the owner can remove a stand or remove directly the land.
	 * @param cases The integer input that describes what is asked to be removed.
	 * @return True if it has a stand or an owner.
	 */
	public boolean canRemove(int cases){
		//TODO: need to do more check. For instance: if the owner has enough money.
		switch(cases){
		case 1:
			if(this.hasStand && this.isHasAnOwner()) 
				return true;
			return false;
		case 2:
			if(!this.hasStand && this.isHasAnOwner())
				return true;
		}
		return false;
	}
	/**
	 * 
	 * @see boardrelated.buyableSquare#getRecWorth()
	 */

	@Override
	public int getRecWorth(){
		if(this.hasStand)
			return 75+this.getPrice()/2;
		return this.getPrice()/2;
	}
	/**
	 * Applies the arrival procedure of a cab square. If there are different number of cab squares owned by
	 * the same owner. Than the rent increases gradually. Such as, if there is a cab stand 60 for 1 cab square,
	 * 120 for 2, 240 for 3, 480 for 4. The rents gets half if there is no cab stand.
	 * 
	 * @requires A player to land on.
	 * @param p The player who landed on the square
	 * @param g The game that player and the square exist.
	 * @modifies Increases the balance of the owner.
	 * @modifies Decreases the balance of the arriving player.
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */
	@Override
	public void arrival_proc(Player p, Game g) {
		// TODO Auto-generated method stub
		if(this.isHasAnOwner() && this.getOwner()!=p){
			if(this.hasStand){//If the cabSquare has a stand, owner gets 2 times the rent
				if(this.getOwner().numOfCabs()==2){
					payForCabs(120, p, g);
				}else if(this.getOwner().numOfCabs()==3){
					payForCabs(240, p, g);
				}else if(this.getOwner().numOfCabs()==4){
					payForCabs(480, p, g);
				}else{
					payForCabs(60, p, g);
				}
			}else{//if there is no cab stand, the owner gets the regular rent
				if(this.getOwner().numOfCabs()==2){
					payForCabs(60, p, g);
				}else if(this.getOwner().numOfCabs()==3){
					payForCabs(120, p, g);
				}else if(this.getOwner().numOfCabs()==4){
					payForCabs(240, p, g);
				}else{
					payForCabs(30, p, g);
				}
			}
		}
		String toGo = DialogGenerator.CabSquareDialog();
		if(toGo!=null)
			p.arriveDirectlyForHolland(toGo, g);
		((Square)this).updateAllViews();
	}

	/**
	 * Gives out the number of stands on a cab square, although the max is one.
	 * @return The number of stands on a cab square.
	 */
	public int getNumOfStand(){
		if(hasStand==true){
			return 1;
		}
		else{
			return 0;
		}
	}

	/**
	 * isHasStand() allows us to know if there is a stand on a cabSquare instance or not.
	 * @return True if there is a stand on this.
	 */
	public boolean isHasStand() {
		return hasStand;
	}

	/**
	 * 
	 * @param hasStand The boolean that is going to be set on hasStand field of this' instance.
	 * @modifies The hasStand field according to the input boolean.
	 */
	public void setHasStand(boolean hasStand) {
		this.hasStand = hasStand;
		((Square)this).updateAllViews();
	}
	/**
	 * 
	 * @param amount Money that is going to be transferred from the input player to the owner of this' instance.
	 * @param p Player who has landed on a cab square.
	 * @param g The game in which the player and the cab square that player p landed exist.
	 * 
	 * @modifies Decreases the balance of the arriving player by the amount.
	 * @modifies Increases the balance of the owner player by the input amount.
	 */
	public void payForCabs(int amount, Player p,Game g){
		p.givePayment(amount,g);
		this.getOwner().receivePayment(amount);
		ConsoleGenerator.write2Console("Player "+p.getPlayerName()+" paid "+amount+
				"$ rent to"+this.getOwner().getPlayerName()+" for his cab company(s).");
	}
}

