package Pokerhand;

// The PokerhandHelper class for helper method dealing with card class
public class PokerhandHelper {
	
	// the number of cards in a hand
	private static final int handLen = 5;
	
	// String indicating no errors
	public static final String ok = "OK";
	
	// 14 by 4 int array indicating the cards that has been dealt
	private static byte[][] cardMap;

	// valid for errors
	public static String validateGame(Game game)
	{
		cardMap = new byte[14][4];
		
		if(game.getHands().length > 2)
    	{
    		return "We can only have two players!";
    	}
    	else if(game.getHands().length < 2)
    	{
    		return "We not enough players!";
    	}
		
		Hand[] hands = game.getHands();
		
		if(hands[0].getName().equals(""))
    	{
    		return "Player 1 has an empty name! Comon, give him/her a name.";
    	}else if(hands[1].getName().equals(""))
    	{
    		return "Player 2 has an empty name! Comon, give him/her a name.";
    	}
    	else if(hands[0].getName().equals(hands[1].getName()))
    	{
    		return "The two players has the same name! Comon, give them a unique name.";
    	}
		
		for(Hand h : hands)
		{
			if(h.getPokerHand().length > 5)
			{
				return String.format("%s has more than 5 cards!", h.getName());
			}
			else if(h.getPokerHand().length < 5)
			{
				return String.format("%s has less than 5 cards!", h.getName());
			}
			if(cardUsed(h))
			{
				return "The game has been rigged! There is a duplicate card somewhere!";
			}
		}
		
		return ok;
	}
	
	// check whether the card has already been dealt.
	// returns true if a card has been already dealt.
	private static boolean cardUsed(Hand h)
	{
		for(Card c : h.getPokerHand())
		{
			int rank = c.getRankValue() -1;
			int suit = c.getSuit() -1;
			if(cardMap[rank][suit] == 0)
			{
				cardMap[rank][suit] = 1;  // indicate the card has been dealt
			}
			else
			{
				return true;
			}
		}
		return false;
	}
	
	// return -1 if leftHand wins, return 1 if rightHand wins and return zero if a draw
	public static short compareHand(Hand leftHand, Hand rightHand)
	{
		PokerHandType leftPokerHand = analyzeHand(leftHand);
		PokerHandType rightPokerHand = analyzeHand(rightHand);
		
		//
		if(leftPokerHand.getPokerHandValue() ==  rightPokerHand.getPokerHandValue())
		{
			if(leftHand.getPokerHandLargestRank() > rightHand.getPokerHandLargestRank())
			{
				return -1;
			}
			else if (leftHand.getPokerHandLargestRank() < rightHand.getPokerHandLargestRank())
			{
				return 1;
			}
			else
			{
				Card[] leftHandCards = leftHand.getPokerHand();
				Card[] rightHandCards = rightHand.getPokerHand();
				for(int i = handLen-2; i > 0; i--)
				{
					if(leftHandCards[i].getRankValue() > rightHandCards[i].getRankValue())
					{
						return -1;
					}
					else if(leftHandCards[i].getRankValue() < rightHandCards[i].getRankValue())
					{
						return 1;
					}
				}
				return 0;
			}
		}
		else if (leftPokerHand.getPokerHandValue() > rightPokerHand.getPokerHandValue())
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
	
	public static PokerHandType analyzeHand(Hand hand)
	{
		// If we have a flush
		if(AllSameSuit(hand))
		{
			int isStraight = AllStraight(hand);
			if( isStraight > 0)
			{
				hand.setPokerHandLargestRank(isStraight);
				return PokerHandType.StraightFlush;
			}
			
			hand.setPokerHandLargestRank(hand.getLargestCardRank());
			return PokerHandType.Flush;
		}
		
		// If we have a straight
		int isStraight = AllStraight(hand);
		if( isStraight > 0)
		{
			hand.setPokerHandLargestRank(isStraight);
			return PokerHandType.Straight;
		}
		
		boolean hasPair = false;
		boolean hasTwoPair = false;
		boolean hasThreeOfAKind = false;
		boolean hasFourOfAKind = false;
		
		int largestRank = 0;
		
		Card[] pokerhand = hand.getPokerHand();
		for(int i = 0; i < handLen-1; i++)
		{
			//We found matching Rank Cards!
			if(pokerhand[i].getRankValue() == pokerhand[i+1].getRankValue())
			{
				// we found our first pair!
				if(!hasPair && largestRank == 0)
				{
					if(largestRank == 0)
					{
						largestRank = pokerhand[i].getRankValue();
						hasPair = true;
					}
				}
				else if (!hasPair && largestRank != pokerhand[i].getRankValue())
				{
					// we have three of a kid and now found a pair 
					// Full house!
					hasPair = true;
				}
				else if (hasPair)
				{
					if (largestRank != pokerhand[i+1].getRankValue())
					{
						// We already have a pair, and we found another pair
						hasPair = false;
						largestRank = pokerhand[i+1].getRankValue();
						hasTwoPair = true;
					}
					else if (!hasThreeOfAKind)
					{
						// We found Three of a Kind since 
						hasPair = false;
						hasThreeOfAKind = true;
					}
				}
				else if(hasTwoPair)
				{
					//Full House!
					hasPair = true;
					hasTwoPair = false;
					hasThreeOfAKind = true;
				}
				else if (hasThreeOfAKind && !hasFourOfAKind)
				{
					// Four of a Kind!
					hasThreeOfAKind = false;
					hasFourOfAKind = true;
				}
			}
		}
		
		// return the Poker Hand Type
		if(largestRank > 0)
		{
			hand.setPokerHandLargestRank(largestRank);
			if(hasFourOfAKind)
			{
				return PokerHandType.FourOfAKind;
			}
			else if(hasThreeOfAKind)
			{
				if(hasPair)
				{
					return PokerHandType.FullHouse;
				}
				return PokerHandType.ThreeOfAKind;
			}
			else if(hasTwoPair)
			{
				return PokerHandType.TwoPair;
			}
			else
			{
				return PokerHandType.Pair;
			}
		}
		else
		{
			hand.setPokerHandLargestRank(hand.getLargestCardRank());
			return PokerHandType.HighCard;
		}
	}
	
	// return true if the hand has all the same suit aka flush
	private static boolean AllSameSuit(Hand hand)
	{
		Card[] pokerhand = hand.getPokerHand();
		int suit = pokerhand[0].getSuit();
		for(int i = 1; i < handLen; i++)
		{
			if(pokerhand[i].getSuit() != suit)
			{
				return false;
			}
		}
		return true;
	}
	
	// On a straight, the difference between the value of each adjacent card is 1
	// return true
	private static int AllStraight(Hand hand)
	{
		Card[] pokerhand = hand.getPokerHand();
		int firstCard = hand.getSmallestCardRank();
		int lastCard = hand.getLargestCardRank();
		// if first card was 2, last card was Ace, check for the others
		if(firstCard == 2 && lastCard == 14)
		{
			for(int i = 0; i < handLen-2; i++)
			{
				int difference = pokerhand[i+1].getRankValue() - pokerhand[i].getRankValue();
				if( difference != 1)
				{
					return 0;
				}
			}
			return pokerhand[3].getRankValue();
		}
		else{
			for(int i = 0; i < handLen-1; i++)
			{
				int difference = pokerhand[i+1].getRankValue() - pokerhand[i].getRankValue();
				if( difference != 1)
				{
					return 0;
				}
			}
			return hand.getLargestCardRank();
		}
	}
}
