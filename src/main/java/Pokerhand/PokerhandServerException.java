package Pokerhand;

//server exception class
public class PokerhandServerException extends RuntimeException{
	
	private static final long serialVersionUID = 1117213065867535708L;

	public PokerhandServerException(String message)
	{
		super(message);
	}
}
