package backend;
import java.util.Random;
import java.util.Scanner;

/**
 *This class implements the dices of the monopoly game. There are three dices in monopoly and they have different features than the regular dice
 * 
 *  Implementation-Specific: There are three dices currentDice1 currentDice2 and Speed Dice. The combinations lead the dice different states which is updadet by Check
 * Dice methods.
 * 
 *  @author Utku Evci
 *  @version     %I%, %G%
 *  
 */

public class Dice {
	Random randomGenerator = new Random();
	private int currentDice1=0 ;
	private int currentDice2=0;
	private int speedDice=0 ; //4 is for Mr.Monopoly 5 is BUS
	private boolean isDouble;
	private boolean isTriple;
	private boolean isMrMonopolyMove;
	private boolean isBusMove;
	private boolean throwedInJail;
	private int doubleCount; //to hold consecutive doubles and triples
	private boolean debugMode;
	
	/** Constructor that creates an instance of a dice.
	 * 
	 * @param in If the the game is in the debug mode than the Scanner reads the dice inputs when a dice rolled. If not in the debug mode, it can be null
	 * 
	 */
	public Dice(){
		this.throwedInJail=false;
	}
	public Dice(boolean isDB){
		this.throwedInJail=false;
		setDebugMode(isDB);
	}
	/** Roll the 3 dices and set them to a random values. Then calls the checkTheFaces function to update the state fields.
	 * 
	 * @modifies currentDice1 currentDice2 and speedDice fields of Dice and throwedInJail state-field.
	 */
	public void rollTheDice(){
		setCurrentDice1(rollDice(6));
		ConsoleGenerator.write2Console("First dice is "+currentDice1+".");
		setCurrentDice2(rollDice(6));
		ConsoleGenerator.write2Console("Second dice is "+currentDice2+".");
		setSpeedDice(rollDice(5));
		ConsoleGenerator.write2Console("Third dice is "+speedDice+".");
		throwedInJail=false;
		checkTheFaces();
	}
	
	/** Roll the 2 dices and set them to a random values. Then calls the checkTheFaces function to update the state fields.
	 * 
	 * @modifies currentDice1 currentDice2 fields of Dice and throwedInJail state-field.
	 */
	public void rollDiceInJail(){
		setCurrentDice1(rollDice(6));
		ConsoleGenerator.write2Console("First dice is "+currentDice1+".");
		setCurrentDice2(rollDice(6));
		ConsoleGenerator.write2Console("Second dice is "+currentDice2+".");
		setMrMonopolyMove(false);
		setBusMove(false);
		throwedInJail=true;
		
	}
	/** Returns true if the rolled 2 dice have same value.
	 * 
	 */
	public boolean checkTheJailDice(){
		if(currentDice1==currentDice2){
			return true;
		}
		else{
			return false;
		}
	}
	/** Checks the faces of the dices and updates the states.
	 * 
	 * @modifies isTriple isDouble and isMrMonopolyMove fields.
	 */
	public void checkTheFaces() {
		if (throwedInJail){
			setTriple(false);
			setDouble(false);
			setMrMonopolyMove(false);
			setBusMove(false);
		}
		else{
			if(currentDice1==currentDice2){
				if ((speedDice!=4)&&(currentDice1==speedDice)){
					setTriple(true);
					setDouble(false);
				}
				else {
					setTriple(false);
					setDouble(true);
				}
			}
			else{
				setTriple(false);
				setDouble(false);
			}
			setMrMonopolyMove(speedDice==4);
			setBusMove(speedDice==5);
		}
		
	}
	/** Returns a random integer between 1 and MAX. If debugMode is true, than asks the value to the user.
	 * 
	 * @param max the maximum value of the dice.
	 * @return a random integer within the interval.
	 * @requires max>1 and max is integer.
	 */
	public int rollDice(int max){
		if (this.debugMode){
			 int ti;
			do{
				System.out.println("Enter next dice. Between 1-"+max);
				  System.out.print(">>");
			      ti = DialogGenerator.GetDiceValue();
			} while(ti>max||ti<1);
			return ti;	
		}
		else{
			int dice1 = 1 + randomGenerator.nextInt(max);
			return dice1;
		}
		
		
		
	}
	
	public Random getRandomGenerator() {
		return randomGenerator;
	}

	public void setRandomGenerator(Random randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public boolean isThrowedInJail() {
		return throwedInJail;
	}
	public void setThrowedInJail(boolean throwedInJail) {
		this.throwedInJail = throwedInJail;
	}
	
	
	public int getCurrentDice1() {
		return currentDice1;
	}

	public void setCurrentDice1(int currentDice1) {
		this.currentDice1 = currentDice1;
	}

	public int getCurrentDice2() {
		return currentDice2;
	}

	public void setCurrentDice2(int currentDice2) {
		this.currentDice2 = currentDice2;
	}

	public int getSpeedDice() {
		return speedDice;
	}

	public void setSpeedDice(int speedDice) {
		this.speedDice = speedDice;
	}
	
	/** This function calculates the appropriate total of the dice values.
	 * 
	 * @return the sum of the dices
	 * @requires dice-value fields are not null.
	 */
	public int getTotalDice() {
		if (isMrMonopolyMove||throwedInJail||isBusMove){
			return currentDice1+currentDice2;
		}
		else{
			return currentDice1+currentDice2+speedDice;	
		}
		
	}
	public boolean isTriple() {
		return isTriple;
	}
	public void setTriple(boolean isTriple) {
		this.isTriple = isTriple;
	}
	public int getDoubleCount() {
		return doubleCount;
	}
	public void setDoubleCount(int doubleCount) {
		this.doubleCount = doubleCount;
	}
	/** Sets the doubleState and updates doubleCount accordingly.
	 * 
	 * @param max the maximum value of the dice.
	 * @modifies doubleCount field, which counts the consecutive doubles thrown.
	 */
	public void setDouble(boolean isDouble) {
		if (isDouble) {
			doubleCount++;
		}
		else {
			doubleCount=0;
		}
		this.isDouble = isDouble;
		
	}
	public void setDoubleDebug(boolean isDouble) {
		this.isDouble = isDouble;	
	}
	public boolean isDouble() {
		return isDouble;
	}
	public boolean isMrMonopolyMove() {
		return isMrMonopolyMove;
	}
	public void setMrMonopolyMove(boolean isMrMonopolyMove) {
		this.isMrMonopolyMove = isMrMonopolyMove;
	}
	
	public boolean isBusMove() {
		return isBusMove;
	}
	public void setBusMove(boolean isBusMove) {
		this.isBusMove = isBusMove;
	}
	public boolean isOdd(){
		int mytotalDice = getTotalDice()%2;
		if(mytotalDice == 1) {
			return true;
			}
		else{
			return false;
			}
	}
	public void setDebugMode(boolean b) {
		// TODO Auto-generated method stub
		this.debugMode=b;
	}
	/** This function is called when the player goes to Jail
	 * 
	 * @modifies MrMonopolyMove, isDouble,isTriple field.
	 */
	public void finishTurn(){
		setMrMonopolyMove(false);
		setBusMove(false);
		setDouble(false);
		setTriple(false);
	}
		
}

