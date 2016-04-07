package card;

import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;

public class _EntertainmentRocks extends Card {


	public _EntertainmentRocks(String cardName, int cardID, int typeID,
			boolean kpable) {
		super(cardName, cardID, typeID, kpable);
		// TODO Auto-generated constructor stub
	}
	
	// Stakeholders in Motion Pictures and General can immediately collect dividends
	public void applyCard(Player p, Game g) {
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		boolean hasMPStock = !p.getMotionPicturesStocks().isEmpty();
		if(hasMPStock){
			p.payDividends();
		}
			
	}
}
