package test;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.Game;
import backend.Player;
import card.Card;

public class GetOufOfJailTest {

	Game game;
	Player player1;
	Card card;
	
	public void setUp(){
		game = new Game(2);
		player1=game.getSpecificPlayer(0);
		card = game.getKeepableCard("Get Out Of Jail");
	}
	@Test
	public void applyCardTest() {
		setUp();
		player1.setInJail(true);
		card.applyCard(player1, game);
		assertEquals(false,player1.isInJail());
	}

}
