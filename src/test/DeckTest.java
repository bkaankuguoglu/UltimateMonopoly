package test;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.Game;
import card.Card;
import card.Deck;

public class DeckTest {

	Game game;
	Deck deck;
	
	public void setUp(){
		game = new Game(2);
		deck = game.getTheTable().getCardDeck();
	}
	@Test
	public void drawChanceCardTest() {
		setUp();
		Card card = deck.drawChanceCard();
		assertNotEquals("House Condemned",card.getCardName());
		assertNotEquals("Assessed for Street Repairs", card.getCardName());
		assertNotEquals("Special Online Pricing", card.getCardName());
		assertNotEquals("Go To Jail", card.getCardName());
		
	}
	
	public void drawCommunityChanceCardTest() {
		setUp();
		Card card = deck.drawChanceCard();
		assertNotEquals("Hurricane",card.getCardName());
		assertNotEquals("Mardi Gras", card.getCardName());
		assertNotEquals("Property Tax", card.getCardName());
		assertNotEquals("Get Out Of Jail", card.getCardName());
		
	}

}
