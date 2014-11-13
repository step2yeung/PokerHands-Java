package Pokerhand;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// The game object that user input get parsed into
public class Game implements Serializable
{
	private static final long serialVersionUID = 6147750078775928106L;
	private Hand[] hands;
	
	public void setHands(Hand[] hands)
	{
		this.hands = hands;
	}
	
	public Hand[] getHands()
	{
		return hands;
	}
	
	// return the left hand
	public Hand getLeftHand()
	{
		return hands[0];
	}
	
	// return the right hand
	public Hand getRightHand()
	{
		return hands[1];
	}
}
