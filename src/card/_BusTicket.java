package card;

import java.util.ArrayList;
import java.util.HashMap;

import backend.ConsoleGenerator;
import backend.DialogGenerator;
import backend.Game;
import backend.Player;
import boardrelated.Square;

public class _BusTicket extends Card {

	public _BusTicket(String cardName, int cardID, int typeID, boolean kpable) {
		super(cardName, cardID, typeID, kpable);
		// TODO Auto-generated constructor stub
	}
	
	// The player moves to any square on his side of the board. 
	// Using this card expires all the other chance cards the player has
	public void applyCard(Player p, Game g) {
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		ArrayList<Square> sqList = g.getTheTable().getBoard().getSquareArrayList();
		ArrayList<Square> targetSqList = new ArrayList<Square>();
		String targetSq = "";
		for (Square square : sqList) {
			if(square.getTrackNumber()==p.getTrackNumber()){
				targetSqList.add(square);
			}
		}
		
		targetSq=DialogGenerator.busTicketDialog(g, targetSqList);
		p.arriveDirectly2Square(targetSq, g);
		
		HashMap<String, Card> cardList = p.getCardList();
		for (String c : cardList.keySet()) {
			if(cardList.get(c).getTypeID() == 0){
				cardList.remove(c);
			}
			
		}
	}
}
