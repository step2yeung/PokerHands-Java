package Pokerhand;

// The card class representing a playing card with a suit and value
public class Card
{	
	private int suit;
	private int rankValue;
	private String cardString;
	
	// Card constructor
	public Card(String rankAndSuit)
	{
		if(verifyCard(rankAndSuit)){
			this.cardString = rankAndSuit;
			this.suit = parseSuit(rankAndSuit.substring(rankAndSuit.length()-1));
			this.rankValue = parseRank(rankAndSuit.substring(0,rankAndSuit.length()-1));
		}
		else
		{
			throw new PokerhandClientException("Invalid cards");
		}
	}
	
	// CardString getter
	public String getCardString()
	{
		return cardString;
	}
	
	// Suit getter
	public int getSuit()
	{
		return suit;
	}
	
	// RankValue getter
	public int getRankValue()
	{
		return rankValue;
	}
	
	// verify the card is a valid playing card
	private boolean verifyCard(String cardString)
	{
		if(cardString == null)
		{
			return false;
		}
		if(cardString.length() > 3 || cardString.length() < 2)
		{
			return false;
		}
		
		String suit = cardString.substring(cardString.length()-1);
		String rank = cardString.substring(0,cardString.length()-1);
		
		if(!isSuit(suit))
		{
			return false;
		}
		else if(!isRank(rank))
		{
			return false;
		}
		
		return true;
	}
	
	// return true if String s is a suit
	private static boolean isSuit(String s)
	{
		if(s.equalsIgnoreCase("S") || s.equalsIgnoreCase("H") || s.equalsIgnoreCase("C") 
				|| s.equalsIgnoreCase("D"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// return true if String s is a rank
	private boolean isRank(String s)
	{
		if(s.equalsIgnoreCase("A") || s.equalsIgnoreCase("K") || s.equalsIgnoreCase("Q") 
				|| s.equalsIgnoreCase("J") || s.equalsIgnoreCase("10") || s.equalsIgnoreCase("9")
				|| s.equalsIgnoreCase("8") || s.equalsIgnoreCase("7") || s.equalsIgnoreCase("6")
				|| s.equalsIgnoreCase("5") || s.equalsIgnoreCase("4") || s.equalsIgnoreCase("3")
				|| s.equalsIgnoreCase("2"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// Return the value of the Suit
	private int parseSuit(String suit)
	{
		switch(suit) 
		{
		case "S":
			return 4;
		case "H":
			return 3;
		case "C":
			return 2;
		case "D":
		default:
			return 1;
		}
	}
	
	// Return the value of the Rank
	private int parseRank(String rank)
	{
		switch(rank) 
		{
		case "A":
			return 14;
		case "K":
			return 13;
		case "Q":
			return 12;
		case "J":
			return 11;
		case "10":
			return 10;
		case "9":
			return 9;
		case "8":
			return 8;
		case "7":
			return 7;
		case "6":
			return 6;
		case "5":
			return 5;
		case "4":
			return 4;
		case "3":
			return 3;
		case "2":
		default:
			return 2;
		}
	}
}
