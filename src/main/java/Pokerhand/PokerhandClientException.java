package Pokerhand;

// client exception class
public class PokerhandClientException extends RuntimeException{
	
	private static final long serialVersionUID = 1117213065867535709L;

	public PokerhandClientException(String message)
	{
		super(message);
	}
}