/**
 * 
 */
package org.game.cards;

import java.util.Optional;

/**
 * Represents a deck of playing cards
 * @author wjohnson
 *
 */
public interface PlayingCardsDeck <T extends PlayingCard> {

	/**
	 * Randomly shuffles the ordering of the cards found in the deck. 
	 * Calling this refreshes the available cards to their full set, and thereby resets any cards dealt using dealOneCard().
	 */
	public void shuffle();
	
	/**
	 * @return The top-most card in the deck, if all available cards in the deck have not been exhausted.
	 */
	public Optional<T> dealOneCard();
	
	/**
	 * @return Number of remaining cards that can be dealt from the deck. (In other words, returns greater than 0 if dealOneCard() would return a PlayingCard rather empty Optional.)
	 */
	public int undealtCardsRemaining();
}
