/**
 * 
 */
package org.game.cards;

/**
 * Represents a single card that is used in a standard playing card game.
 * 
 * @author wjohnson
 *
 */
public interface PlayingCard {

	
	/**
	 * Play Value is determined by the game being played, as indicated by the implementing class.  For example, a King in the game of Poker has
	 * value based on its comparative rank of 13, but in Blackjack, a King will have a value of 10 for cumulative hand total purposes.
	 * @return numeric indicator of the card value in the context of a game
	 */
	public int getPlayValue();
}
