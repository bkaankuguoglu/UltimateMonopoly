package test;

import static org.junit.Assert.*;
import org.junit.Test;

import backend.Game;
import backend.Player;
import boardrelated.RailroadTrackSquare;
import boardrelated.cabSquare;
import boardrelated.deedSquare;
import card.AssessedStreetRepairs;

public class AssessedStreetRepairsTest {

	Game game;
	Player player1;
	Player player2;
	deedSquare sq1;
	deedSquare sq4;
	deedSquare sq5;
	cabSquare sq2;
	RailroadTrackSquare sq3;
	AssessedStreetRepairs card;
	public void setUp(){
		game = new Game(2);
		player1 = game.getSpecificPlayer(0);
		player2 = game.getSpecificPlayer(1);
		sq1 = (deedSquare) game.getBuyableSq("South Street");
		sq4 = (deedSquare) game.getBuyableSq("Market Street");
		sq5 = (deedSquare) game.getBuyableSq("Broad Street");
		sq2 = (cabSquare) game.getBuyableSq("Checker Cab Co.");
		sq3 = (RailroadTrackSquare) game.getBuyableSq("Reading Railroad");
		card = (AssessedStreetRepairs) game.getTheTable().getCardDeck().getCard("Assessed for Street Repairs");
	}
	
	@Test
	public void applyCardTest() {
		setUp();
		player1.setBalance(50000);
		player1.buySquare(sq1);
		player1.buySquare(sq4);
		player1.buySquare(sq5);
		sq1.build(1);
		game.getTheTable().getPool().setAmount(0);
		card.applyCard(player1, game);
		int a = game.getTheTable().getPool().getAmount();
		player1.buySquare(sq2);
		sq2.build(1);
		game.getTheTable().getPool().setAmount(0);
		card.applyCard(player1, game);
		int b = game.getTheTable().getPool().getAmount();
		assertNotEquals(a,b);
		
	}

}
