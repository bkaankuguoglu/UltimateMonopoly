package test;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.Game;
import backend.Player;

public class PlayerTest {

	Game game;
	
	public void setUp(){
		game = new Game(2);
				
	}
	
	@Test
	public void testCurrentPlayerAtStart() {
		setUp();
		assertEquals(0,game.getCurrentPlayer());
	}
	
	@Test
	public void testLocationAtStart(){
		setUp();
		Player player = game.getSpecificPlayer(0);
		assertEquals("Pay Day Square", player.getLocation().getName());
	}
	
	@Test
	public void testBuyLocation(){
		setUp();
		Player player = game.getSpecificPlayer(0);
		player.setBalance(3000);
		player.setLocation(game.getSq("South Street"));
		player.buySquare(game.getBuyableSq("South Street"));
		String[] properties = player.getPropertyNames();
		assertEquals("South Street", properties[0]);
	}
	
	@Test
	public void testReceivePayment(){
		setUp();
		Player player = game.getSpecificPlayer(0);
		player.setBalance(2000);
		player.receivePayment(400);
		assertEquals(2400,player.getBalance());
	}
	
	@Test
	public void testGivePayment(){
		setUp();
		Player player1 = game.getSpecificPlayer(0);
		player1.setBalance(4000);
		player1.givePayment(1000, game);
		assertEquals(3000,player1.getBalance());
	}
	
	@Test
	public void testTransferMoney(){
		setUp();
		Player player1 = game.getSpecificPlayer(0);
		Player player2 = game.getSpecificPlayer(1);
		player1.setBalance(4000);
		player2.setBalance(1000);
		player1.transferMoney(player2, 1000, game);
		assertEquals(3000,player1.getBalance());
		assertEquals(2000,player2.getBalance());
	}
	
	@Test
	public void testMortgageProperty(){
		setUp();
		Player player = game.getSpecificPlayer(0);
		player.setBalance(3000);
		player.setLocation(game.getSq("South Street"));
		player.buySquare(game.getBuyableSq("South Street"));
		String[] properties = player.getPropertyNames();
		player.mortgageProperty(game.getBuyableSq("South Street"));
		String[] mortgage = player.getMortgageNames();
		assertEquals("South Street", mortgage[0]);
	}
	
	@Test
	public void testAddCard(){
		setUp();
		Player player = game.getSpecificPlayer(0);
		player.addCard(game.getKeepableCard("Special Online Pricing"));
		String[] card = player.getCardNames();
		assertEquals("Special Online Pricing", card[0]);
	}
	
	@Test
	public void testArriveDirectly2Square(){
		setUp();
		Player player = game.getSpecificPlayer(0);
		player.arriveDirectly2Square(game.getSq("South Street"), game);
		assertEquals("South Street", player.getLocation().getName());
	}
	
	@Test
	public void testGo2Jail(){
		setUp();
		Player player = game.getSpecificPlayer(0);
		player.go2Jail(game);
		assertEquals("Jail", player.getLocation().getName());
	}
	
	@Test
	public void testPay2Pool(){
		setUp();
		Player player = game.getSpecificPlayer(0);
		player.setBalance(3000);
		game.getTheTable().getPool().setAmount(0);
		assertEquals(0,game.getTheTable().getPool().getAmount());
		player.pay2Pool(1000, game);
		assertEquals(2000,player.getBalance());
		assertEquals(1000,game.getTheTable().getPool().getAmount());
	}
	
	@Test
	public void testGetOutOfJail(){
		setUp();
		Player player = game.getSpecificPlayer(0);
		player.go2Jail(game);
		assertEquals(true,player.isInJail());
		player.getOutFromJail();
		assertEquals(false,player.isInJail());
	}
	
	@Test
	public void testCheckBankrupcy(){
		setUp();
		Player player1 = game.getSpecificPlayer(0);
		Player player2 = game.getSpecificPlayer(1);
		player1.setBalance(0);
		assertEquals(0, player1.getReceivableWorth());
		player1.checkBankrupcy(3000, player2, game);
		assertEquals(true,player1.isBankrupt());
	}
	
	@Test
	public void testIsEnoughMoneyExist(){
		setUp();
		Player player = game.getSpecificPlayer(0);
		player.setBalance(1000);
		assertEquals(true,player.isEnoughMoneyExist(500));
		assertEquals(false,player.isEnoughMoneyExist(3000));
	}
	
	@Test
	public void move2SquareWithDiceTest(){
		game= new Game(2);
		Player player = game.getSpecificPlayer(0);
		game.getTheDice().setCurrentDice1(3);
		game.getTheDice().setCurrentDice2(3);
		game.getTheDice().setSpeedDice(2);
		game.getTheDice().checkTheFaces();
		player.setLocation(game.getSq("South Street"));
		player.move2SquareWithDice(game);
		assertEquals("Birthday Gift", player.getLocation().getName());
	}
	
	@Test
	public void arriveDirectlyForHolland(){
		game= new Game(2);
		Player player = game.getSpecificPlayer(0);
		player.setLocation(game.getSq("South Street"));
		player.arriveDirectlyForHolland("Broad Street", game);
		assertNotEquals("South Street", player.getLocation().getName());
		assertEquals("Broad Street", player.getLocation().getName());
	}

}
