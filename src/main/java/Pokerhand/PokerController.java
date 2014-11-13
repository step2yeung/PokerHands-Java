package Pokerhand;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// This is the rest Controller where the REST call to {url}/poker gets handled
@ControllerAdvice
@RestController
public class PokerController {

	private static final String pokerUri = "/poker";
	private static final String win = "%s wins!";
	private static final String draw = "Draw. No one wins!";
	private static final String clientProblem = "We have a problem! %s";
	private static final String serverProblem = "Oops something wrong with our server, brb.";
	
	// the entry point to the controller for /poker uri
	// only good for POST method
    @RequestMapping(value = pokerUri, method = RequestMethod.POST)
    @ResponseBody
    public Result comparePokerhand(@RequestBody @Valid Game game) 
    {
    	try{
	    	// Get the two hand and initialize the hand
	    	Hand leftHand = game.getLeftHand();
	    	Hand rightHand = game.getRightHand();
	    	if(!leftHand.initializeHand() || !rightHand.initializeHand())
	    	{
	    		// problem while parsing the cards
	    		// return new Result(String.format(clientProblem, "Some weird cards we got here"));
	    		throw new PokerhandClientException("Some weird cards we got here");
	    	}

	    	// Validate the input passed to this service
	    	String validateGameResult = PokerhandHelper.validateGame(game);
	    	if(!validateGameResult.equals(PokerhandHelper.ok))
	    	{
	    		// Return the error
	    		throw new PokerhandClientException(String.format(clientProblem, validateGameResult));
	    	}
	    	
			int result = PokerhandHelper.compareHand(leftHand, rightHand);
	
			if(result > 0)
			{
				String s = String.format(win, rightHand.getName());
				return new Result(s);
			}
			else if(result < 0)
			{
				String s = String.format(win, leftHand.getName());
				return new Result(s);
			}
			else
			{
				return new Result(draw);
			}
    	}
    	catch(PokerhandClientException e)
    	{
    		throw e;
    	}
    	catch(Exception e)
    	{
    		throw new PokerhandServerException(serverProblem);
    	}
    }
    
    // Exception handler for client exception to throw status 400 errors
    @ExceptionHandler(PokerhandClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result processClientError(Exception ex) {
        return new Result(ex.getMessage());
    }
    
    // Exception handler for server exception to throw status 500 errors
    @ExceptionHandler(PokerhandServerException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public Result processServerError(Exception ex) {
        return new Result(ex.getMessage());
    }
}
