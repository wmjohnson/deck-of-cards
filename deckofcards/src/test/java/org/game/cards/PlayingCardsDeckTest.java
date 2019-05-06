/**
 * 
 */
package org.game.cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import org.game.cards.PokerPlayingCard.FaceValue;
import org.game.cards.PokerPlayingCard.Suit;
import org.junit.Test;

/**
 * @author wjohnson
 *
 */
public class PlayingCardsDeckTest {

	@Test
	public void testConstructedDeckIntegrity() {
		
		PokerPlayingCardsDeck deck = new PokerPlayingCardsDeck();
		
		// expected total is correct
		assertEquals(deck.undealtCardsRemaining(), PokerPlayingCardsDeck.CARD_POOL_QUANTITY);
		
		ArrayList<PokerPlayingCard> deckAsList = new ArrayList<>(52);
    	while(deck.undealtCardsRemaining() > 0) {
    		deckAsList.add(deck.dealOneCard().get());
    	}
    	
    	// expected categories of cards are present
    	Set<PokerPlayingCard> clubs = deckAsList.stream()
    				.filter(card -> card.getSuit().equals(Suit.CLUBS))
    				.collect(Collectors.toSet());
		assertEquals(clubs.size(), PokerPlayingCardsDeck.CARD_POOL_QUANTITY / Suit.values().length);
		
		Set<PokerPlayingCard> spades = deckAsList.stream()
				.filter(card -> card.getSuit().equals(Suit.SPADES))
				.collect(Collectors.toSet());
		assertEquals(spades.size(), PokerPlayingCardsDeck.CARD_POOL_QUANTITY / Suit.values().length);
	
		Set<PokerPlayingCard> hearts = deckAsList.stream()
				.filter(card -> card.getSuit().equals(Suit.HEARTS))
				.collect(Collectors.toSet());
		assertEquals(hearts.size(), PokerPlayingCardsDeck.CARD_POOL_QUANTITY / Suit.values().length);
	
		Set<PokerPlayingCard> diamonds = deckAsList.stream()
				.filter(card -> card.getSuit().equals(Suit.DIAMONDS))
				.collect(Collectors.toSet());
		assertEquals(diamonds.size(), PokerPlayingCardsDeck.CARD_POOL_QUANTITY / Suit.values().length);
	
	}
	
	/**
	 * Same as the test for the Constructor, but checking after performing a call to shuffle()
	 */
	@Test
	public void testShuffleDeckIntegrity() {
		PokerPlayingCardsDeck deck = new PokerPlayingCardsDeck();
		
		deck.shuffle();
		
		assertEquals(deck.undealtCardsRemaining(), PokerPlayingCardsDeck.CARD_POOL_QUANTITY);
		
		ArrayList<PokerPlayingCard> deckAsList = new ArrayList<>(52);
    	while(deck.undealtCardsRemaining() > 0) {
    		deckAsList.add(deck.dealOneCard().get());
    	}
    	
    	// confirm quantity found in each suit
    	Set<PokerPlayingCard> clubs = deckAsList.stream()
    				.filter(card -> card.getSuit().equals(Suit.CLUBS))
    				.collect(Collectors.toSet());
		assertEquals(clubs.size(), PokerPlayingCardsDeck.CARD_POOL_QUANTITY / Suit.values().length);
		
		Set<PokerPlayingCard> spades = deckAsList.stream()
				.filter(card -> card.getSuit().equals(Suit.SPADES))
				.collect(Collectors.toSet());
		assertEquals(spades.size(), PokerPlayingCardsDeck.CARD_POOL_QUANTITY / Suit.values().length);
	
		Set<PokerPlayingCard> hearts = deckAsList.stream()
				.filter(card -> card.getSuit().equals(Suit.HEARTS))
				.collect(Collectors.toSet());
		assertEquals(hearts.size(), PokerPlayingCardsDeck.CARD_POOL_QUANTITY / Suit.values().length);
	
		Set<PokerPlayingCard> diamonds = deckAsList.stream()
				.filter(card -> card.getSuit().equals(Suit.DIAMONDS))
				.collect(Collectors.toSet());
		assertEquals(diamonds.size(), PokerPlayingCardsDeck.CARD_POOL_QUANTITY / Suit.values().length);
		
		// Confirm quantity of individual face value
		Set<PokerPlayingCard> aces = deckAsList.stream()
				.filter(card -> card.getFaceVal().equals(FaceValue.ACE))
				.collect(Collectors.toSet());
		assertEquals(aces.size(), PokerPlayingCardsDeck.CARD_POOL_QUANTITY / FaceValue.values().length);
	}
	
	/**
	 * To be reasonably sure that the shuffle implementation is effective,
	 * the first five cards should not be the same among any of three calls to perform shuffle()
	 */
	@Test
	public void testShuffleEffectiveness() {
		boolean cardsShuffled = false;
		
		PokerPlayingCardsDeck deck1 = new PokerPlayingCardsDeck();
		PokerPlayingCardsDeck deck2 = new PokerPlayingCardsDeck();
		PokerPlayingCardsDeck deck3 = new PokerPlayingCardsDeck();
		deck1.shuffle();
		deck2.shuffle();
		deck3.shuffle();
		
		PokerPlayingCard topCard1 = deck1.dealOneCard().get();
		PokerPlayingCard topCard2 = deck2.dealOneCard().get();
		PokerPlayingCard topCard3 = deck3.dealOneCard().get();
		
		PokerPlayingCardsDeck compareFurther1 = null;
		PokerPlayingCardsDeck compareFurther2 = null;
		
		if(topCard1.equals(topCard2)) {
			compareFurther1 = deck1;
			compareFurther2 = deck2;
		}
		else if(topCard1.equals(topCard3)) {
			compareFurther1 = deck1;
			compareFurther2 = deck2;
		}
		else if(topCard2.equals(topCard3)) {
			compareFurther1 = deck2;
			compareFurther2 = deck3;
		}
		else {
			cardsShuffled = true;
		}
		
		if(compareFurther1!=null && compareFurther2!=null) {
			for(int i=0; i < 4; i++) {
				// found a different card at same index
				if(!compareFurther1.dealOneCard().get().equals(compareFurther2.dealOneCard().get())) {
					cardsShuffled = true;
				}
			}
		}
		
		assertTrue(cardsShuffled);		
	}
	
	@Test
	public void testDealOneCard() {
		PokerPlayingCardsDeck deck = new PokerPlayingCardsDeck();
		
		while(deck.undealtCardsRemaining() > 0) {
    		assertTrue(deck.dealOneCard().get() instanceof PokerPlayingCard);
    	}
		
		assertFalse("dealOneCard() produced PlayingCard instance even with 0 remaining available in deck", deck.dealOneCard().isPresent());
		
	}
	
}
