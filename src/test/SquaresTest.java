package test;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.Game;
import backend.Player;
import backend.Pool;
import boardrelated.RailroadTrackSquare;
import boardrelated.Square;
import boardrelated.cabSquare;
import boardrelated.deedSquare;
import card.Card;

public class SquaresTest {

	Game game;
	Player player1;
	Pool pool;
	Square sq1;
	Square sq2;
	Square sq3;
	Square sq4;
	Square sq5;
	Square sq6;
	Card card;
	
	public void setUp(){
		game = new Game(2);
		player1 = game.getSpecificPlayer(0);
		pool = game.getTheTable().getPool();
		sq1 = game.getSq("Tax Refund");
		sq2 = game.getSq("Birthday Gift");
		sq3 = game.getSq("Holland Tunnel 1");
		sq4 = game.getSq("Income Tax");
		sq5 = game.getSq("Go Square");
		sq6 = game.getSq("Pay Day Square");
	}
	
	@Test
	public void taxRefundTest(){
		setUp();
		player1.setBalance(1000);
		pool.setAmount(1000);
		sq1.arrival_proc(player1, game);
		assertEquals(500, pool.getAmount());
		assertEquals(1500, player1.getBalance());
	}
	
	@Test
	public void birthdayGiftTest(){
		setUp();
		player1.setBalance(1000);
		sq2.arrival_proc(player1, game);
		assertEquals(1100,player1.getBalance());
	}
	
	@Test
	public void hollandTunnelTest(){
		setUp();
		player1.setLocation(sq3);
		sq3.arrival_proc(player1, game);
		assertEquals("Holland Tunnel 2", player1.getLocation().getName());
	}
	
	@Test
	public void incomeTaxTest(){
		setUp();
		player1.setBalance(5000);
		sq4.arrival_proc(player1, game);
		assertNotEquals(5000,player1.getBalance());
	}
	
	@Test
	public void goTest(){
		setUp();
		player1.setBalance(1000);
		sq5.passing_proc(player1, game);
		assertEquals(1200,player1.getBalance());
	}
	
	@Test
	public void payDayTest(){
		setUp();
		player1.setBalance(1000);
		game.getTheDice().setCurrentDice1(2);
		game.getTheDice().setCurrentDice2(3);
		game.getTheDice().setSpeedDice(2);
		sq6.passing_proc(player1, game);
		assertEquals(1300,player1.getBalance());
		
		player1.setBalance(1000);
		game.getTheDice().setCurrentDice1(3);
		sq6.passing_proc(player1, game);
		assertEquals(1400,player1.getBalance());
	}

}
