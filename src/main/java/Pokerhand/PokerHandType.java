package Pokerhand;

// The possible type of Pokerhand
public enum PokerHandType {
	StraightFlush(9),
	FourOfAKind(8),
	FullHouse(7),
	Flush(6),
	Straight(5),
	ThreeOfAKind(4),
	TwoPair(3),
	Pair(2),
	HighCard(1);
	
	public int pokerHandValue;
	
	private PokerHandType (int value)
    {
	    this.pokerHandValue = value;
	}
	 
	public int getPokerHandValue() {
	    return pokerHandValue;
	}
}
