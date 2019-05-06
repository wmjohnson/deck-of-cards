/**
 * 
 */
package org.game.cards;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import org.game.cards.PokerPlayingCard.FaceValue;
import org.game.cards.PokerPlayingCard.Suit;

/**
 * @author wjohnson
 *
 */
public class PokerPlayingCardsDeck implements PlayingCardsDeck<PokerPlayingCard> {

	public static final int PLAYING_CARD_DECK_QUANTITY = 1;
	public static final int CARD_POOL_QUANTITY = 52 * PLAYING_CARD_DECK_QUANTITY;
	
	private ArrayList<PokerPlayingCard> availableCards = new ArrayList<>(CARD_POOL_QUANTITY);  
	private ArrayList<PokerPlayingCard> dealtCards = new ArrayList<>();
	
	
	/**
	 * Constructs a full deck that has cards grouped together sequentially by suit and then descending order by Poker card rank.
	 */
	public PokerPlayingCardsDeck() {
		for(Suit s : Suit.values()) {
			for(FaceValue f : FaceValue.values()) {
				availableCards.add(new PokerPlayingCard(s, f));
			}
		}
	}
	

	/**
	 * Creates a random ordering of cards within a full deck of playing cards. Any cards that had been dealt, 
	 * are reincorporated and available to be dealt again from the resulting shuffle.
	 */
	@Override
	public void shuffle() {
		availableCards.addAll(dealtCards);
		dealtCards.clear();
		
		Random random = new Random();
		for(int i = CARD_POOL_QUANTITY -1; i > 0; i--) {
			int indexToSwapInto = random.nextInt(CARD_POOL_QUANTITY);
			PokerPlayingCard temp = availableCards.get(indexToSwapInto);
			availableCards.set(indexToSwapInto, availableCards.get(i));
			availableCards.set(i, temp);
		}
	}
	
	/**
	 * @return Returns top-most remaining (undealt) card in the deck.  
	 */
	@Override
	public Optional<PokerPlayingCard> dealOneCard() {
		if(!availableCards.isEmpty()) {
			dealtCards.add(availableCards.get(availableCards.size()-1));
			return Optional.of(availableCards.remove(availableCards.size()-1));
		}
		else {
			return Optional.empty();
		}
	}

	/**
	 * @return Number of remaining cards that can be dealt from the deck. (In other words, returns greater than 0 if dealOneCard() would return a PlayingCard rather empty Optional.)
	 */
	@Override
	public int undealtCardsRemaining() {
		return availableCards.size();
	}
}
