package test;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.Game;
import backend.Player;
import boardrelated.RailroadTrackSquare;
import boardrelated.cabSquare;

public class RailroadTrackSquareTest {

	Game game;
	Player player1;
	Player player2;
	RailroadTrackSquare sq1;
	RailroadTrackSquare sq2;	
	
	public void setUp(){
		game = new Game(2);
		player1 = game.getSpecificPlayer(0);
		player2 = game.getSpecificPlayer(1);
		sq1 = (RailroadTrackSquare) game.getBuyableSq("Reading Railroad");
		sq2 = (RailroadTrackSquare) game.getBuyableSq("B&O Railroad");
	}
	
	@Test
	public void canBuildTest() {
		setUp();
		player1.setBalance(5000);
		player1.buySquare(sq1);
		assertEquals(true,sq1.canBuild());
		sq1.build(1);
		assertEquals(false,sq1.canBuild());
	}
	
	@Test
	public void canRemoveTest(){
		setUp();
		player1.setBalance(5000);
		player1.buySquare(sq1);
		sq1.build(1);
		assertEquals(true,sq1.canRemove(1));
		sq1.mortgageOrRemove(1);
		assertEquals(false,sq1.canRemove(1));
		assertEquals(true,sq1.canRemove(2));
	}
	
	@Test
	public void arrival_procTest(){
		setUp();
		player1.setBalance(5000);
		player2.setBalance(4000);
		player1.buySquare(sq1);
		sq1.arrival_proc(player2, game);
		assertEquals(sq1.getRent(),4000-player2.getBalance());
		player2.setBalance(4000);
		player1.buySquare(sq2);
		sq1.arrival_proc(player2, game);
		System.out.println(player1.numOfCabs());
		assertEquals(sq1.getRent()*2,4000-player2.getBalance());
		sq1.build(1);
		player2.setBalance(4000);
		sq1.arrival_proc(player2, game);
		assertEquals(sq1.getRent()*4,4000-player2.getBalance());
	}
	
	@Test
	public void payForRailroadTest(){
		setUp();
		player1.setBalance(5000);
		player2.setBalance(4000);
		player1.buySquare(sq1);
		player1.setBalance(5000);
		sq1.payForRailroad(50, player2, game);
		assertEquals(5050,player1.getBalance());
		assertEquals(3950,player2.getBalance());
	}

}
