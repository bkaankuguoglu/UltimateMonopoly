package backend;
/**
 * Board class mainly acts as a main abstract type keep different types of squares on it. It's like a depot 
 * that contains squares and connects them to each other.
 * 
 * Implementation-Specific: Board class keeps the different squares on it using an arrayList. It also has different
 * fields to indicate how many tracks there are on the board. It basically have the information that keeps
 * squares, tracks, colors and different groups.
 * 
 * It acts as a storage place in which everything is connected in a certain way.
 * 
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 *  
 */
public class ConsoleGenerator {
	public static final int n=20;
	static String[] console=new String[n];
	
	public ConsoleGenerator(){
		
	}
	
	public static String[] getConsole(){
		
		return console;
		
	}
	
	public static void write2Console(String s){
		for(int i=n-1;i>0;i--){
			console[i]=console[i-1];
		}
		console[0]=s;
		Game.updateAllViews();
	}
	
	

}
