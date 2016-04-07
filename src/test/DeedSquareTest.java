package test;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.Game;
import backend.Player;
import boardrelated.deedSquare;

public class DeedSquareTest {

	Game game;
	Player player1;
	Player player2;
	deedSquare sq1;
	deedSquare sq2;
	deedSquare sq3;
	deedSquare sq4;
	
	
	public void setUp(){
		game = new Game(2);
		player1 = game.getSpecificPlayer(0);
		player2 = game.getSpecificPlayer(1);
		sq1 = (deedSquare) game.getBuyableSq("South Street");
		sq2 = (deedSquare) game.getBuyableSq("Broad Street");
		sq3 = (deedSquare) game.getBuyableSq("Walnut Street");
		sq4 = (deedSquare) game.getBuyableSq("Market Street");
		
	}
	
	@Test
	public void getItPaidTest() {
		setUp();
		player1.setLocation(sq1);
		player1.setBalance(1000);
		player2.setBalance(2000);
		player1.buySquare(sq1);
		player1.setBalance(1000);
		sq1.getItPaid(player2, 50, game);
		assertEquals(1050,player1.getBalance());
		assertEquals(1950,player2.getBalance());
	}
	
	@Test
	public void getRecWorthTest(){
		setUp();
		player1.setBalance(50000);
		int a = sq1.getPrice();
		player1.buySquare(sq1);
		player1.buySquare(sq2);
		player1.buySquare(sq3);
		player1.buySquare(sq4);
		sq1.build(1);
		assertNotEquals(a, sq1.getRecWorth());
	}
	
	@Test
	public void arrival_procTest(){
		setUp();
		player1.setBalance(5000);
		player2.setBalance(4000);
		player1.buySquare(sq1);
		player1.buySquare(sq2);
		player1.buySquare(sq3);
		sq1.arrival_proc(player2, game);
		int a = 4000-player2.getBalance();
		player1.buySquare(sq4);
		sq1.arrival_proc(player2, game);
		int b = 4000-player2.getBalance()-a;
		assertNotEquals(b,a);
		
		player1.setBalance(5000);
		player2.setBalance(4000);
		sq1.arrival_proc(player2, game);
		int c = 4000-player2.getBalance();
		player2.setBalance(4000);
		sq1.build(1);
		sq1.arrival_proc(player2, game);
		int d = 4000-player2.getBalance();
		assertNotEquals(d,c);
		
		player1.setBalance(5000);
		player2.setBalance(4000);
		sq2.build(1);
		sq3.build(1);
		sq4.build(1);
		sq1.build(2);
		sq1.arrival_proc(player2, game);
		int e = 4000-player2.getBalance();
		assertNotEquals(e,d);
		
		player1.setBalance(5000);
		player2.setBalance(4000);
		sq2.build(2);
		sq3.build(2);
		sq4.build(2);
		sq1.build(3);
		sq1.arrival_proc(player2, game);
		int f = 4000-player2.getBalance();
		assertNotEquals(f,e);
		
		player1.setBalance(5000);
		player2.setBalance(4000);
		sq2.build(3);
		sq3.build(3);
		sq4.build(3);
		sq1.build(4);
		sq1.arrival_proc(player2, game);
		int g = 4000-player2.getBalance();
		assertNotEquals(g,f);
		
		player1.setBalance(5000);
		player2.setBalance(4000);
		sq2.build(4);
		sq3.build(4);
		sq4.build(4);
		sq1.build(5);
		sq1.arrival_proc(player2, game);
		int h = 4000-player2.getBalance();
		assertNotEquals(h,g);
		
		/*player1.setBalance(5000);
		player2.setBalance(4000);
		sq2.build(5);
		sq3.build(5);
		sq4.build(5);
		sq1.build(6);
		sq1.arrival_proc(player2, game);
		int i = 4000-player2.getBalance();
		assertNotEquals(i,h);*/
	}
	
	@Test
	public void canBuildTest(){
		setUp();
		player1.setBalance(50000);
		player1.buySquare(sq1);
		player1.buySquare(sq2);
		player1.buySquare(sq3);
		player1.buySquare(sq4);
		deedSquare[] arr = {sq1, sq2, sq3, sq4};
		assertEquals(true,sq1.canBuild(1));
		for(int i=0;i<4;i++){
			arr[i].build(1);
		}
		assertEquals(true,sq1.canBuild(2));
		
		for(int i=0;i<4;i++){
			arr[i].build(2);
		}
		assertEquals(true,sq1.canBuild(3));
		
		for(int i=0;i<4;i++){
			arr[i].build(3);
		}
		assertEquals(true,sq1.canBuild(4));
		
		for(int i=0;i<4;i++){
			arr[i].build(4);
		}
		assertEquals(true,sq1.canBuild(5));
	}
	
	@Test
	public void monopolyOwnerTest(){
		setUp();
		player1.setBalance(50000);
		player1.buySquare(sq1);
		player1.buySquare(sq2);
		player1.buySquare(sq3);
		assertEquals(false,sq1.monopolyOwner());
		player1.buySquare(sq4);
		assertEquals(true,sq1.monopolyOwner());
	}
	
	@Test
	public void majorityOwnerTest(){
		setUp();
		player1.setBalance(50000);
		player1.buySquare(sq1);
		player1.buySquare(sq2);
		assertEquals(false,sq1.majorityOwner());
		player1.buySquare(sq3);
		assertEquals(true,sq1.majorityOwner());
	}
	
	@Test
	public void numberOfCommonlyOwnedTest(){
		setUp();
		player1.setBalance(50000);
		player1.buySquare(sq1);
		assertEquals(1,sq1.numberOfCommonlyOwned());
		player1.buySquare(sq2);
		assertEquals(2,sq1.numberOfCommonlyOwned());
		player1.buySquare(sq3);
		assertEquals(3,sq1.numberOfCommonlyOwned());
		player1.buySquare(sq4);
		assertEquals(4,sq1.numberOfCommonlyOwned());
	}
	
	
	@Test
	public void canRemoveTest(){
		setUp();
		player1.setBalance(50000);
		player1.buySquare(sq1);
		player1.buySquare(sq2);
		player1.buySquare(sq3);
		player1.buySquare(sq4);
		deedSquare[] arr = {sq1, sq2, sq3, sq4};
		for(int i=0;i<4;i++){
			arr[i].build(1);
		}
		for(int i=0;i<4;i++){
			arr[i].build(2);
		}
		for(int i=0;i<4;i++){
			arr[i].build(3);
		}
		for(int i=0;i<4;i++){
			arr[i].build(4);
		}
		assertEquals(true,sq1.canRemove(1));
		for(int i=0;i<4;i++){
			arr[i].mortgageOrRemove(1);
		}
		assertEquals(true,sq1.canRemove(1));
		for(int i=0;i<4;i++){
			arr[i].mortgageOrRemove(1);
		}
		for(int i=0;i<4;i++){
			arr[i].mortgageOrRemove(1);
		}
		for(int i=0;i<4;i++){
			arr[i].mortgageOrRemove(1);
		}
		assertEquals(false,sq1.canRemove(1));
		assertEquals(true,sq1.canRemove(4));
	}
	
	

}
