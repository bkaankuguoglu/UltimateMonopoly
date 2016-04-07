package card;

import backend.ConsoleGenerator;
import backend.Game;
import backend.Player;

public class _VehicleImpounded extends Card {

	public _VehicleImpounded() {
		// TODO Auto-generated constructor stub
	}

	public _VehicleImpounded(String cardName, int cardID, int typeID,
			boolean kpable) {
		super(cardName, cardID, typeID, kpable);
		// TODO Auto-generated constructor stub
	}
	// pay $50 to the pool, move "directly" to "Just Visiting" to pick up your car. Lose 1 turn
	public void applyCard(Player p, Game g) {
		ConsoleGenerator.write2Console("The card " + getCardName() + "has been applied to the game");
		p.pay2Pool(50, g);	
		p.arriveDirectly2Square("Jail", g);
	}
}
