package card;

import java.util.HashMap;

import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;
import boardrelated.Square;

public class _ChangingLanes extends Card {

	public _ChangingLanes(String cardName, int typeID, int cardID,
			boolean kpable) {
		super(cardName, typeID, cardID, kpable);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void applyCard(Player p, Game g) {
		// TODO Auto-generated method stub
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		changeLanes1(p, g);
	}
	
	public void changeLanes1(Player p, Game g){
		Square curSq = p.getLocation();
		int loc = curSq.getSquareNumber();
		int nextLoc = nextLoc(loc);
		HashMap<Integer,Square> squareMapByNum = g.getTheTable().getBoard().squareMapByNumber();
		p.arriveDirectly2Square(squareMapByNum.get(nextLoc), g);
		
	}
	public int nextLoc (int loc){
		int nextLoc = 0;
		if (loc<=2)
			nextLoc = 57;
		else if(loc<=11)
			nextLoc = 54 + loc;
		else if(loc<=16)
			nextLoc = 66;
		else if(loc<=25)
			nextLoc = 50 + loc;
		else if(loc<=30)
			nextLoc = 76;
		else if(loc<=39)
			nextLoc = 46 + loc;
		else if(loc<=44)
			nextLoc = 85;
		else if(loc<=53)
			nextLoc = 42 + loc;
		else if(loc<=55)
			nextLoc = 57;
		else if(loc<=58)
			nextLoc = 95;
		else if(loc<=62)
			nextLoc = 36 + loc;
		else if(loc<=67)
			nextLoc = 101;
		else if(loc<=72)
			nextLoc = 32 + loc;
		else if(loc<=77)
			nextLoc = 107;
		else if(loc<=83)
			nextLoc = 28 + loc;
		else if(loc<=86)
			nextLoc = 113;
		else if(loc<=91)
			nextLoc = 24 + loc;
		else if(loc<=93)
			nextLoc = 95;
		return nextLoc;
	}

}
