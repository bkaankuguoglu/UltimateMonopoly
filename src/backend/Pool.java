package backend;
/**
 *Class implements the the pool, where the moeny is gathered when players pay fines.
 * 
 * 
 *  @author Utku Evci
 *  @version     %I%, %G%
 *  
 */
public class Pool {
	private int amount;

	/** Constructor that creates an instance of a pool. 
	 * 
	 * @param a that indicates the initial amount of the board
	 * 
	 */
	public Pool(int a) {
		// TODO Auto-generated constructor stub
		this.amount=a;
	}

	/** Getter for amount field of pool
	 * 
	 * @return The amount available in the Pool
	 */
	public int getAmount() {
		return amount;
	}
	/** Setter for amount field of pool
	 * 
	 * @param amount New amount in the pool
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/** Increases the money in the pool by the input.
	 * 
	 * @param amount Amount going to be added.
	 * @requires amount to be positive
	 */	
	public void recievePayment(int amount) {
		this.amount += amount;
	}
	/** Decreases the money in the pool by the input.
	 * 
	 * @param amount Amount going to be withdrawn.
	 * @requires amount is more than the amount in the pool.
	 * @requires amount to be positive
	 */
	public void withdrawMoney(int amount){
		this.amount -= amount;
	}

	

}
