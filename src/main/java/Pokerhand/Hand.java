package Pokerhand;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// This class represents each player 
public class Hand implements Serializable{

	private static final long serialVersionUID = -2146262003746850570L;

	// the name of the player
    private String name;
    
    // the hand of the player in string format
    private String[] hand;
    
    // the hand of the player in Card format
    private Card[] pokerHand;
    
    // the largest rank in the poker hand
	private int pokerHandLargestRank;
	
	// index of the lowest Rank card after sorting
	private final int lowCardIndex = 0;
	
	// index of the highest Rank card after sorting
	private final int highCardIndex = 4;
	
	// Initialize the hand and sort the cards from smallest to largest
	public boolean initializeHand()
	{
		try{
			pokerHand = new Card[hand.length];
			pokerHandLargestRank = 0;
			for(int i = 0; i < pokerHand.length; i++)
			{
				pokerHand[i] = new Card(hand[i]);
			}
			
			this.sort();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	// Name getter
	public String getName() {
        return name;
    }
    
	// Name setter
    public void setName(String name) {
    	if(this.name == null)
    	{
    		this.name = name;
    	}
    	else
    	{
    		throw new PokerhandClientException("Multiple names? Thats a nono.");
    	}
    }
    
    // Hand getter
    public String[] getHand() {
        return hand;
    }
    
    // Hand setter
    public void setHand(String[] hand) {
    	if(this.hand ==  null)
    	{
	        this.hand = hand;
	    }
		else
		{
			throw new PokerhandClientException("Multiple hands? Thats a nono.");
		}
    }
	
    // sets the largest Rank for the poker hand
	public void setPokerHandLargestRank(int value)
	{
		this.pokerHandLargestRank = value;
	}
	
	// returns the largest Rank for the poker hand
	public int getPokerHandLargestRank()
	{
		return pokerHandLargestRank;
	}
	
	// return the card array of the hand
	public Card[] getPokerHand()
	{
		return pokerHand;
	}
	
	// returns the value of the lowest rank card
	public int getSmallestCardRank()
	{
		return pokerHand[lowCardIndex].getRankValue();
	}
	
	// returns the value of the highest rank card
	public int getLargestCardRank()
	{
		return pokerHand[highCardIndex].getRankValue();
	}
	
	// use insertion sort to sort the cards
	private void sort()
	{
		for(int i = 1; i < this.pokerHand.length; i++)
		{
			Card curCard = pokerHand[i];
			int curIndex = i;
			while( curIndex > 0 && pokerHand[curIndex -1].getRankValue() > curCard.getRankValue())
			{
				pokerHand[curIndex] = pokerHand[curIndex-1];
				curIndex--;
			}
			pokerHand[curIndex] = curCard;
		}
	}
	
	// toString
	public String toString()
	{
		String output = this.name + " : ";
		for(Card c : pokerHand)
		{
			output += c.getCardString() + ", ";
		}
		
		return output;
	}
}
