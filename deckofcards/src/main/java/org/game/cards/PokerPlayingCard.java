/**
 * 
 */
package org.game.cards;

/**
 * @author wjohnson
 *
 */
public class PokerPlayingCard implements PlayingCard {
	
	private Suit suit;
	private FaceValue faceVal;
	
	public PokerPlayingCard(Suit suit, FaceValue faceVal) {
		this.suit = suit;
		this.faceVal = faceVal;
	}
	
	public int getPlayValue() {
		return faceVal.rank;
	}
	
	public boolean equals(PokerPlayingCard comparisonCard) {
		return this.suit.equals(comparisonCard.suit) && this.faceVal.equals(comparisonCard.faceVal) ? true : false;
	}
	
	
	public enum Suit {
		HEARTS, DIAMONDS, CLUBS, SPADES
	}
	
	public enum FaceValue {
		TWO (2),
		THREE (3),
		FOUR (4),
		FIVE (5),
		SIX (6),
		SEVEN (7),
		EIGHT (8),
		NINE (9),
		TEN (10),
		JACK (11),
		QUEEN (12),
		KING (13),
		ACE (14);
		
		private final int rank;
		
		private FaceValue(int rank) {
			this.rank = rank;
		}
		
		public int getRank() {
			return rank;
		}
	}
	
	public String toString() {
		return faceVal + " of " + suit; 
	}

	/**
	 * @return the suit
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * @return the faceVal
	 */
	public FaceValue getFaceVal() {
		return faceVal;
	}
	
}
