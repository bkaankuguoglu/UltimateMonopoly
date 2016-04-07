package test;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.Game;
import backend.Player;
import boardrelated.RailroadTrackSquare;
import boardrelated.buyableSquare;
import boardrelated.cabSquare;
import boardrelated.deedSquare;

public class BuyableSquareTest {
	
	Game game;

	@Test
	public void buildTest1() {
		game = new Game(2);
		buyableSquare sq1 = game.getBuyableSq("South Street");
		buyableSquare sq2 = game.getBuyableSq("Broad Street");
		buyableSquare sq3 = game.getBuyableSq("Walnut Street");
		buyableSquare sq4 = game.getBuyableSq("Market Street");
		buyableSquare[] arr = {sq1,sq2,sq3,sq4};
		Player player = game.getSpecificPlayer(0);
		player.setBalance(700000);
		player.buySquare(game.getBuyableSq("South Street"));
		player.buySquare(game.getBuyableSq("Broad Street"));
		player.buySquare(game.getBuyableSq("Walnut Street"));
		player.buySquare(game.getBuyableSq("Market Street"));
		assertEquals(0,((deedSquare)sq1).getNumOfHouses());
		for(int i=0;i<4;i++){
			arr[i].build(1);
		}
		assertEquals(1,((deedSquare)sq1).getNumOfHouses());
		for(int i=0;i<4;i++){
			arr[i].build(2);
		}
		assertEquals(2,((deedSquare)sq1).getNumOfHouses());
		for(int i=0;i<4;i++){
			arr[i].build(3);
		}
		assertEquals(3,((deedSquare)sq1).getNumOfHouses());
		for(int i=0;i<4;i++){
			arr[i].build(4);
		}
		for(int i=0;i<4;i++){
			assertEquals(4,((deedSquare)arr[i]).getNumOfHouses());
		}
	}
	
	@Test
	public void buildTest2(){
		game = new Game(2);
		buyableSquare sq1 = game.getBuyableSq("Yellow Cab Co.");
		Player player = game.getSpecificPlayer(0);
		player.setBalance(50000);
		player.buySquare(sq1);
		sq1.build(1);
		assertEquals(true,((cabSquare)sq1).isHasStand());
	}
	
	@Test
	public void buildTest3(){
		game = new Game(2);
		buyableSquare sq1 = game.getBuyableSq("B&O Railroad");
		Player player = game.getSpecificPlayer(0);
		player.setBalance(50000);
		player.buySquare(sq1);
		sq1.build(1);
		assertEquals(true,((RailroadTrackSquare)sq1).hasDepot());
	}
	
	@Test
	public void changeOwnerTest(){
		game = new Game(2);
		buyableSquare sq1 = game.getBuyableSq("South Street");
		Player player1 = game.getSpecificPlayer(0);
		Player player2 = game.getSpecificPlayer(1);
		player1.setBalance(4000);
		player1.setLocation(game.getSq("South Street"));
		player1.buySquare(game.getBuyableSq("South Street"));
		assertSame(player1, game.getBuyableSq("South Street").getOwner());
		sq1.changeOwner(player2);
		assertSame(player2, game.getBuyableSq("South Street").getOwner());		
	}
	
	@Test
	public void changeOwnerMortgagedTest(){
		game = new Game(2);
		buyableSquare sq1 = game.getBuyableSq("South Street");
		Player player1 = game.getSpecificPlayer(0);
		Player player2 = game.getSpecificPlayer(1);
		player1.setBalance(4000);
		player1.setLocation(game.getSq("South Street"));
		player1.buySquare(game.getBuyableSq("South Street"));
		player1.mortgageProperty(sq1);
		assertSame(player1, game.getBuyableSq("South Street").getOwner());
		sq1.changeOwnerMortgaged(player2);
		assertSame(player2, game.getBuyableSq("South Street").getOwner());		
	}
	
	@Test
	public void mortgageOrRemoveTest1(){
		game = new Game(2);
		buyableSquare sq1 = game.getBuyableSq("South Street");
		buyableSquare sq2 = game.getBuyableSq("Broad Street");
		buyableSquare sq3 = game.getBuyableSq("Walnut Street");
		buyableSquare sq4 = game.getBuyableSq("Market Street");
		buyableSquare[] arr = {sq1,sq2,sq3,sq4};
		Player player = game.getSpecificPlayer(0);
		player.setBalance(700000);
		player.buySquare(game.getBuyableSq("South Street"));
		player.buySquare(game.getBuyableSq("Broad Street"));
		player.buySquare(game.getBuyableSq("Walnut Street"));
		player.buySquare(game.getBuyableSq("Market Street"));
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
		for(int i=0;i<4;i++){
			arr[i].mortgageOrRemove(1);
		}
		for(int i=0;i<4;i++){
			assertEquals(3,((deedSquare)arr[i]).getNumOfHouses());
		}
		for(int i=0;i<4;i++){
			arr[i].mortgageOrRemove(1);
		}
		for(int i=0;i<4;i++){
			assertEquals(2,((deedSquare)arr[i]).getNumOfHouses());
		}
		for(int i=0;i<4;i++){
			arr[i].mortgageOrRemove(1);
		}
		for(int i=0;i<4;i++){
			assertEquals(1,((deedSquare)arr[i]).getNumOfHouses());
		}
		for(int i=0;i<4;i++){
			arr[i].mortgageOrRemove(1);
		}
		for(int i=0;i<4;i++){
			assertEquals(0,((deedSquare)arr[i]).getNumOfHouses());
		}
		for(int i=0;i<4;i++){
			arr[i].mortgageOrRemove(4);
		}
		for(int i=0;i<4;i++){
			assertEquals(true,arr[i].isMortgaged());
		}
		
	}
	
	@Test
	public void mortgageOrRemoveTest2(){
		game = new Game(2);
		buyableSquare sq1 = game.getBuyableSq("Yellow Cab Co.");
		Player player = game.getSpecificPlayer(0);
		player.setBalance(50000);
		player.buySquare(sq1);
		sq1.build(1);
		sq1.mortgageOrRemove(1);
		assertEquals(false,((cabSquare)sq1).isHasStand());
		sq1.mortgageOrRemove(2);
		assertEquals(true, sq1.isMortgaged());
	}
	
	@Test
	public void mortgageOrRemoveTest3(){
		game = new Game(2);
		buyableSquare sq1 = game.getBuyableSq("Reading Railroad");
		Player player = game.getSpecificPlayer(0);
		player.setBalance(50000);
		player.buySquare(sq1);
		sq1.build(1);
		sq1.mortgageOrRemove(1);
		assertEquals(false,((RailroadTrackSquare)sq1).hasDepot());
		sq1.mortgageOrRemove(2);
		assertEquals(true, sq1.isMortgaged());
	}
	
	@Test
	public void hurricaneOnOneSquareTest(){
		game = new Game(2);
		buyableSquare sq1 = game.getBuyableSq("South Street");
		buyableSquare sq2 = game.getBuyableSq("Broad Street");
		buyableSquare sq3 = game.getBuyableSq("Walnut Street");
		buyableSquare sq4 = game.getBuyableSq("Market Street");
		buyableSquare[] arr = {sq1,sq2,sq3,sq4};
		Player player = game.getSpecificPlayer(0);
		player.setBalance(700000);
		player.buySquare(game.getBuyableSq("South Street"));
		player.buySquare(game.getBuyableSq("Broad Street"));
		player.buySquare(game.getBuyableSq("Walnut Street"));
		player.buySquare(game.getBuyableSq("Market Street"));
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
			arr[i].hurricaneOnOneSquare(1);
		}
		for(int i=0;i<4;i++){
			assertEquals(2,((deedSquare)arr[i]).getNumOfHouses());
		}
	}

}
