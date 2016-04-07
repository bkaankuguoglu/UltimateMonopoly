package test;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.Game;
import backend.Player;
import card.Card;

public class GoToJailTest {

	Game game;
	Player player1;
	Card card;
	
	public void setUp(){
		game = new Game(2);
		player1=game.getSpecificPlayer(0);
		card = game.getTheTable().getCardDeck().getCard("Go To Jail");
	}
	@Test
	public void applyCardTest() {
		setUp();
		player1.setInJail(false);
		card.applyCard(player1, game);
		assertEquals(true,player1.isInJail());
	}

}
