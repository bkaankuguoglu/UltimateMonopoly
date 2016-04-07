package boardrelated;
import java.util.StringTokenizer;

import card.Card;
import card.Deck;
import backend.ConsoleGenerator;
import backend.Dice;
import backend.Game;
import backend.Player;

/**
 * This class is provided to meet the needs of the squares on which players need to draw cards.
 * This abstraction allows us to apply those needs such as giving a card to an arriving player.
 * 
 * Its instances provide the procedures that are to be applied upon a game and players.
 * 
 *  @author Cem Uyuk
 *  @version     %I%, %G%
 */
public class drawCardSquare extends Square {
	private String cardType;
	
	/**
	 * This is the constructor that creates an instance of go2JailSquare.
	 * @param name the name of the square
	 * @param squareNumber the number of the square which is sort of tag
	 * @param trackNumber number of the track that this' instance is on
	 * @param colorID of this square's instance
	 * @param totalPlayers the number of total players on its instance
	 * @param cardType the type that indicates whether the card is from community chest or chance deck
	 * 
	 */
	public drawCardSquare(String name, int squareNumber,int trackNumber, int colorID,int totalPlayers,String cardType) {
		// TODO Auto-generated constructor stub
		super( name,  squareNumber, trackNumber,  colorID, totalPlayers);
		this.cardType=cardType;
	}
	
	/**
	 * This method of drawCardSquare gives a card to the arriving player according to the card type of the square
	 * that is either chance or community chest
	 * 
	 * @param p player who landed on an instance of drawCardSquare
	 * @param g game in which the arriving player and the instance of drawCardSquare exist
	 * @modifies card list that arriving player has
	 * @modifies takes out the added card to the player out of card deck
	 * @see boardrelated.Square#arrival_proc(backend.Player, backend.Game)
	 */

	@Override
	public void arrival_proc(Player p, Game g) {
		// TODO Maybe improved with Deck class which has drawChanceCard methods.
		if (getCardType().equals("Chance")){
			Card c = g.getTheTable().getCardDeck().drawChanceCard();
			if(c.isKeepable()){
				p.addCard(c);
			} else {
				c.applyCard(p, g);
			}
		}
		else if (getCardType().equals("Community")){
			Card c = g.getTheTable().getCardDeck().drawCommunityChanceCard();
			if(c.isKeepable()){
				p.addCard(c);
			} else {
				c.applyCard(p, g);
			}
		}
	}
	
	/**
	 * getCardType method gives out the card type of the drawCardSquare
	 * 
	 * @return the type name of the instance of drawCardSquare
	 */
	public String getCardType(){
		String temp = getName();
		StringTokenizer str = new StringTokenizer(temp);
		String type = str.nextToken();
		return type;
	}

}
