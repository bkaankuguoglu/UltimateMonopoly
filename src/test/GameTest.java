package test;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.Game;
import backend.Player;

public class GameTest {

	Game game;
	
	@Test
	public void initiatePlayerListTest() {
		game = new Game(2);
		assertEquals(2,game.getPlayerArray().length);
		assertEquals("Player0", game.getSpecificPlayer(0).getPlayerName());
		assertEquals("Player1", game.getSpecificPlayer(1).getPlayerName());
		assertEquals("Pay Day Square", game.getSpecificPlayer(0).getLocation().getName());
		assertEquals("Pay Day Square", game.getSpecificPlayer(1).getLocation().getName());
	}
	
	@Test
	public void playGameTest(){
		game = new Game(2);
		game.playGame();
		assertEquals(true,game.getSpecificPlayer(0).isCanRoll());
		assertEquals(false,game.getSpecificPlayer(1).isCanRoll());
		
	}
	
	/*@Test
	public void throwDiceAndPerformTest(){
		game = new Game(2);
		Player player = game.getSpecificPlayer(game.getCurrentPlayer());
		player.setInJail(true);
		player.addCard(game.getTheTable().getCardDeck().getCard("Get Out Of Jail"));
		game.throwDiceAndPerform();
		//assertEquals(true,game.getSpecificPlayer(game.getCurrentPlayer()).isCanRoll());
		assertEquals(1,game.getTestFlag());
		player.useCard(game.getTheTable().getCardDeck().getCard("Get Out Of Jail"), game);
		assertEquals(false, player.isGetOutOfJailCard());
		player.setInJail(true);
		player.setTurnInJail(3);
		game.throwDiceAndPerform();
		assertEquals(2, game.getTestFlag());
		player.setInJail(false);
		game.throwDiceAndPerform();
		assertEquals(3, game.getTestFlag());				
	}*/
	
	@Test
	public void throwAndMoveInJailTest(){
		game = new Game(2);
		Player player = game.getSpecificPlayer(game.getCurrentPlayer());
	}
	
	@Test
	public void finishTurnTest(){
		game = new Game(2);
		Player player1 = game.getSpecificPlayer(0);
		Player player2 = game.getSpecificPlayer(1);
		assertSame(player1,game.getSpecificPlayer(game.getCurrentPlayer()));
		game.getTheDice().setThrowedInJail(true);
		game.finishTurn();
		assertSame(player2, game.getSpecificPlayer(game.getCurrentPlayer()));
		game.getTheDice().setThrowedInJail(false);
		game.getTheDice().setDouble(true);
		game.finishTurn();
		assertSame(player2, game.getSpecificPlayer(game.getCurrentPlayer()));
		game.getTheDice().setDouble(false);
		game.finishTurn();
		assertSame(player1, game.getSpecificPlayer(game.getCurrentPlayer()));
		//TODO: Do MR. MONOPOLY RUle
	}
	
	@Test
	public void passTheDice2NextUserTest(){
		game = new Game(2);
		Player player1 = game.getSpecificPlayer(0);
		Player player2 = game.getSpecificPlayer(1);
		assertSame(player1,game.getSpecificPlayer(game.getCurrentPlayer()));
		game.passTheDice2NextUser(player1);
		assertSame(player2,game.getSpecificPlayer(game.getCurrentPlayer()));
		
	}
	
	@Test
	public void bankruptPlayerTest(){
		game = new Game(2);
		Player player1 = game.getSpecificPlayer(0);
		Player player2 = game.getSpecificPlayer(1);
		assertSame(player1,game.getSpecificPlayer(game.getCurrentPlayer()));
		player1.setBalance(3000);
		player1.setLocation(game.getSq("South Street"));
		player1.buySquare(game.getBuyableSq("South Street"));
		player1.buySquare(game.getBuyableSq("Nicollet Avenue"));
		player1.mortgageProperty(game.getBuyableSq("Nicollet Avenue"));
		assertSame(player1,game.getBuyableSq("South Street").getOwner());
		assertSame(player1,game.getBuyableSq("Nicollet Avenue").getOwner());
		assertEquals(true, game.getBuyableSq("Nicollet Avenue").isMortgaged());
		game.bankruptPlayer(player1, null);
		assertSame(null, game.getBuyableSq("South Street").getOwner());
		assertSame(null,game.getBuyableSq("Nicollet Avenue").getOwner());
		assertEquals(false, game.getBuyableSq("Nicollet Avenue").isMortgaged());
		
		
		game = new Game(2);
		player1 = game.getSpecificPlayer(0);
		player2 = game.getSpecificPlayer(1);
		assertSame(player1,game.getSpecificPlayer(game.getCurrentPlayer()));
		player1.setBalance(3000);
		player1.setLocation(game.getSq("South Street"));
		player1.buySquare(game.getBuyableSq("South Street"));
		player1.buySquare(game.getBuyableSq("Nicollet Avenue"));
		player1.mortgageProperty(game.getBuyableSq("Nicollet Avenue"));
		assertSame(player1,game.getBuyableSq("South Street").getOwner());
		assertSame(player1,game.getBuyableSq("Nicollet Avenue").getOwner());
		assertEquals(true, game.getBuyableSq("Nicollet Avenue").isMortgaged());
		game.bankruptPlayer(player1, player2);
		assertSame(player2, game.getBuyableSq("South Street").getOwner());
		assertSame(player2, game.getBuyableSq("Nicollet Avenue").getOwner());
	}
	
	@Test
	public void movePlayerWithDiceTest(){
		game = new Game(2);
		Player player1 = game.getSpecificPlayer(0);
		Player player2 = game.getSpecificPlayer(1);
		assertSame(player1,game.getSpecificPlayer(game.getCurrentPlayer()));
		player1.setLocation(game.getSq("South Street"));
		game.movePlayerWithDice(player1);
		assertNotSame(game.getSq("South Street"), player1.getLocation());
	}
	
	@Test
	public void throwDiceAndPerformTest(){
		game = new Game(2);
		Player player1 = game.getSpecificPlayer(0);
		Player player2 = game.getSpecificPlayer(1);
		assertSame(player1, game.getSpecificPlayer(game.getCurrentPlayer()));
		player1.setInJail(true);
		player1.addCard(game.getTheTable().getCardDeck().getCard("Get Out Of Jail"));
		assertEquals(true, player1.isGetOutOfJailCard());
		game.throwDiceAndPerform();
		//assertEquals(false,player1.isInJail()); //Only if you choose to use the card
		
		game = new Game(2);
		player1 = game.getSpecificPlayer(0);
		player2 = game.getSpecificPlayer(1);
		player1.setBalance(5000);
		assertSame(player1, game.getSpecificPlayer(game.getCurrentPlayer()));
		player1.setInJail(true);
		player1.setLocation(game.getSq("Jail"));
		player1.setTurnInJail(1);
		game.throwDiceAndPerform();
		//assertNotEquals("Jail", player1.getLocation().getName()); //Only if you choose to pay 50
		
		game = new Game(2);
		player1 = game.getSpecificPlayer(0);
		player2 = game.getSpecificPlayer(1);
		player1.setBalance(5000);
		assertSame(player1, game.getSpecificPlayer(game.getCurrentPlayer()));
		player1.setInJail(true);
		player1.setLocation(game.getSq("Jail"));
		player1.setTurnInJail(3);
		game.throwDiceAndPerform();
		//assertNotEquals("Jail", player1.getLocation().getName()); //It depends on the jail dice
		
		game = new Game(2);
		player1 = game.getSpecificPlayer(0);
		player2 = game.getSpecificPlayer(1);
		player1.setBalance(5000);
		assertSame(player1, game.getSpecificPlayer(game.getCurrentPlayer()));
		player1.setLocation(game.getSq("South Street"));
		player1.setInJail(false);
		game.throwDiceAndPerform();
		assertNotEquals("South Street", player1.getLocation().getName());
	}
	
}
