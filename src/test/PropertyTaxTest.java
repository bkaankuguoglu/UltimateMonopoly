package test;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.Game;
import backend.Player;
import backend.Pool;
import boardrelated.RailroadTrackSquare;
import boardrelated.cabSquare;
import boardrelated.deedSquare;
import card.Card;

public class PropertyTaxTest {

	Game game;
	Player player1;
	Pool pool;
	deedSquare sq1;
	deedSquare sq2;
	cabSquare sq3;
	RailroadTrackSquare sq4;
	Card card;
	
	public void setUp(){
		game = new Game(2);
		player1 = game.getSpecificPlayer(0);
		pool = game.getTheTable().getPool();
		sq1 = (deedSquare) game.getBuyableSq("South Street");
		sq2 = (deedSquare) game.getBuyableSq("Broad Street");
		sq3 = (cabSquare) game.getBuyableSq("Checker Cab Co.");
		sq4 = (RailroadTrackSquare) game.getBuyableSq("Reading Railroad");
		card = game.getTheTable().getCardDeck().getCard("Property Tax");
	}
	
	
	@Test
	public void applyCardTest() {
		setUp();
		player1.setBalance(5000);
		pool.setAmount(0);
		player1.buySquare(sq1);
		card.applyCard(player1, game);
		int a = pool.getAmount();
		
		pool.setAmount(0);
		player1.buySquare(sq2);
		card.applyCard(player1, game);
		int b = pool.getAmount();
		assertNotEquals(a,b);
		
		pool.setAmount(0);
		player1.buySquare(sq3);
		card.applyCard(player1, game);
		int c = pool.getAmount();
		assertNotEquals(a,c);
		assertNotEquals(c,b);
		
		pool.setAmount(0);
		player1.buySquare(sq4);
		card.applyCard(player1, game);
		int d = pool.getAmount();
		assertNotEquals(a,d);
		assertNotEquals(d,b);
		assertNotEquals(d,c);		
	}

}
