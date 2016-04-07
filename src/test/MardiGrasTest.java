package test;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.Game;
import backend.Player;
import card.Card;

public class MardiGrasTest {
	
	Game game;
	Player player1;
	Card card;
	
	public void setUp(){
		game = new Game(2);
		player1=game.getSpecificPlayer(0);
		card = game.getTheTable().getCardDeck().getCard("Mardi Gras");
	}
	@Test
	public void applyCardTest() {
		setUp();
		player1.setLocation(game.getSq("South Street"));
		card.applyCard(player1, game);
		assertNotEquals("South Street", player1.getLocation().getName());
		assertEquals("Canal Street", player1.getLocation().getName());
	}

}
