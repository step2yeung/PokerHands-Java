import static org.junit.Assert.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;

import Pokerhand.*;

public class PokerhandUnitTest {
	
	private MockMvc mockMvc;
	
//	@Autowired
//	private Pokerhand
//	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	@Test
	public void TestPositiveCardParsing() {
		Card c = null;
		
		c = new Card("2D");
		assertEquals("Valid card", "2D", c.getCardString());
		
		c = new Card("10C");
		assertEquals("Valid card", "10C", c.getCardString());

		c = new Card("JH");
		assertEquals("Valid card", "JH", c.getCardString());
		
		c = new Card("AS");
		assertEquals("Valid card", "AS", c.getCardString());

		c = new Card("kD");
		assertEquals("Valid card", "kD", c.getCardString());
		
		c = new Card("Qd");
		assertEquals("Valid card", "Qd", c.getCardString());
	}
	
	@Test(expected=PokerhandClientException.class)
	public void TestInvalidRankCard_TooSmall() {
		new Card("1D");
	}
	
	@Test(expected=PokerhandClientException.class)
	public void TestInvalidRankCard_TooBig() {
		new Card("20C");
	}
	
	@Test(expected=PokerhandClientException.class)
	public void TestInvalidRankCard_NotAKQJ() {
		new Card("ZH");
	}
	
	@Test(expected=PokerhandClientException.class)
	public void TestInvalidSuitCard_WithNumberRank() {
		new Card("2T");
	}	
	
	@Test(expected=PokerhandClientException.class)
	public void TestInvalidSuitCard_WithFacecardRank() {
		new Card("AP");
	}
	
	@Test(expected=PokerhandClientException.class)
	public void TestInvalidLowerCaseSuitCard_WithFacecardRank() {
		new Card("Kl");
	}
	
	@Test(expected=PokerhandClientException.class)
	public void TestSuitFirstRankSecondCard() {
		new Card("D2");
	}	
	
	@Test(expected=PokerhandClientException.class)
	public void TestOnlyRankCard() {
		new Card("2");
	}
	
	@Test(expected=PokerhandClientException.class)
	public void TestOnlySuitCard() {
		new Card("D");
	}
	
	@Test(expected=PokerhandClientException.class)
	public void TestThreeOrMoreCharCard() {
		new Card("10DD");
	}
	
	@Test(expected=PokerhandClientException.class)
	public void TestNullCard() {
		new Card(null);
	}	
	
	@Test(expected=PokerhandClientException.class)
	public void TestEmptyCard() {
		new Card("");
	}
	
	@Test
	public void TestValidGame() {
		String[] onepair1 = {"10D", "4D", "10S", "8C", "2S" };
		String[] high = {"10H", "8D", "9S", "AC", "2D" };
		
		Hand h1 = new Hand();
		h1.setName("p1");
		h1.setHand(onepair1);
		h1.initializeHand();
		
		Hand h2 = new Hand();
		h2.setName("p2");
		h2.setHand(high);
		h2.initializeHand();
		
		Hand[] hands = {h1, h2};
		
		Game g = new Game();
		g.setHands(hands);
		
		String validGame = PokerhandHelper.validateGame(g);
		assertEquals("OK", validGame);
	}
	
	@Test
	public void TestThreePlayerGame() {
		String[] onepair1 = {"10D", "4D", "10S", "8C", "2S" };
		String[] high = {"10H", "8D", "9S", "AC", "2D" };
		
		Hand h1 = new Hand();
		h1.setName("p1");
		h1.setHand(onepair1);
		
		Hand h2 = new Hand();
		h2.setName("p2");
		h2.setHand(high);
		
		Hand h3 = new Hand();
		h3.setName("p3");
		h3.setHand(high);
		
		Hand[] hands = {h1, h2, h3};
		
		Game g = new Game();
		g.setHands(hands);
		
		String validGame = PokerhandHelper.validateGame(g);
		assertEquals("We can only have two players!", validGame);
	}
	
	@Test
	public void TestOnePlayerGame() {
		String[] onepair1 = {"10D", "4D", "10S", "8C", "2S" };

		Hand h1 = new Hand();
		h1.setName("p1");
		h1.setHand(onepair1);
		h1.initializeHand();
		
		Hand[] hands = {h1};
		
		Game g = new Game();
		g.setHands(hands);
		
		String validGame = PokerhandHelper.validateGame(g);
		assertEquals("We not enough players!", validGame);
	}
	
	@Test
	public void TestEmptyP1NameGame() {
		String[] onepair1 = {"10D", "4D", "10S", "8C", "2S" };
		String[] high = {"10H", "8D", "9S", "AC", "2D" };
		
		Hand h1 = new Hand();
		h1.setName("");
		h1.setHand(onepair1);
		h1.initializeHand();
		
		Hand h2 = new Hand();
		h2.setName("p2");
		h2.setHand(high);
		h2.initializeHand();
		
		Hand[] hands = {h1, h2};
		
		Game g = new Game();
		g.setHands(hands);
		
		String validGame = PokerhandHelper.validateGame(g);
		assertEquals("Player 1 has an empty name! Comon, give him/her a name.", validGame);
	}
	
	@Test
	public void TestEmptyP2NameGame() {
		String[] onepair1 = {"10D", "4D", "10S", "8C", "2S" };
		String[] high = {"10H", "8D", "9S", "AC", "2D" };
		
		Hand h1 = new Hand();
		h1.setName("p1");
		h1.setHand(onepair1);
		h1.initializeHand();
		
		Hand h2 = new Hand();
		h2.setName("");
		h2.setHand(high);
		h2.initializeHand();
		
		Hand[] hands = {h1, h2};
		
		Game g = new Game();
		g.setHands(hands);
		
		String validGame = PokerhandHelper.validateGame(g);
		assertEquals("Player 2 has an empty name! Comon, give him/her a name.", validGame);
	}
	
	@Test
	public void TestPlayersWithSameNameGame() {
		String[] onepair1 = {"10D", "4D", "10S", "8C", "2S" };
		String[] high = {"10H", "8D", "9S", "AC", "2D" };
		
		Hand h1 = new Hand();
		h1.setName("p1");
		h1.setHand(onepair1);
		h1.initializeHand();
		
		Hand h2 = new Hand();
		h2.setName("p1");
		h2.setHand(high);
		h2.initializeHand();
		
		Hand[] hands = {h1, h2};
		
		Game g = new Game();
		g.setHands(hands);
		
		String validGame = PokerhandHelper.validateGame(g);
		assertEquals("The two players has the same name! Comon, give them a unique name.", validGame);
	}
	
	@Test
	public void TestPlayerWithNullHandGame() {
		Hand h1 = new Hand();
		h1.setName("p1");
		h1.setHand(null);
		boolean handInitialized = h1.initializeHand();
		
		assertEquals(false, handInitialized);
	}
	
	@Test
	public void TestPlayerWithNoCardsGame() {
		String[] onepair1 = {};
		String[] high = {"10H", "8D", "9S", "AC", "2D" };
		
		Hand h1 = new Hand();
		h1.setName("p1");
		h1.setHand(onepair1);
		h1.initializeHand();
		
		Hand h2 = new Hand();
		h2.setName("p2");
		h2.setHand(high);
		h2.initializeHand();
		
		Hand[] hands = {h1, h2};
		
		Game g = new Game();
		g.setHands(hands);
		
		String validGame = PokerhandHelper.validateGame(g);
		assertEquals("p1 has less than 5 cards!", validGame);
	}
	
	@Test
	public void TestPlayersWithFourCardsGame() {
		String[] onepair1 = {"10D", "4D", "10S", "8C" };
		String[] high = {"10H", "8D", "9S", "AC", "2D" };
		
		Hand h1 = new Hand();
		h1.setName("p1");
		h1.setHand(onepair1);
		h1.initializeHand();
		
		Hand h2 = new Hand();
		h2.setName("p2");
		h2.setHand(high);
		h2.initializeHand();
		
		Hand[] hands = {h1, h2};
		
		Game g = new Game();
		g.setHands(hands);
		
		String validGame = PokerhandHelper.validateGame(g);
		assertEquals("p1 has less than 5 cards!", validGame);
	}
	
	@Test
	public void TestPlayersWithSixCardsGame() {
		String[] onepair1 = {"10D", "4D", "10S", "8C", "2S", "9H"};
		String[] high = {"10H", "8D", "9S", "AC", "2D" };
		
		Hand h1 = new Hand();
		h1.setName("p1");
		h1.setHand(onepair1);
		h1.initializeHand();
		
		Hand h2 = new Hand();
		h2.setName("p2");
		h2.setHand(high);
		h2.initializeHand();
		
		Hand[] hands = {h1, h2};
		
		Game g = new Game();
		g.setHands(hands);
		
		String validGame = PokerhandHelper.validateGame(g);
		assertEquals("p1 has more than 5 cards!", validGame);
	}
	
	@Test
	public void TestDuplicateInSameHandGame() {
		String[] onepair1 = {"10D", "4D", "10S", "8D", "8D"};
		String[] high = {"10H", "8C", "9S", "AC", "2D" };
		
		Hand h1 = new Hand();
		h1.setName("p1");
		h1.setHand(onepair1);
		h1.initializeHand();
		
		Hand h2 = new Hand();
		h2.setName("p2");
		h2.setHand(high);
		h2.initializeHand();
		
		Hand[] hands = {h1, h2};
		
		Game g = new Game();
		g.setHands(hands);
		
		String validGame = PokerhandHelper.validateGame(g);
		assertEquals("The game has been rigged! There is a duplicate card somewhere!", validGame);
	}
	
	@Test
	public void TestDuplicateInDifHandGame() {
		String[] onepair1 = {"10D", "4D", "10S", "8C", "8D"};
		String[] high = {"10H", "8D", "9S", "AC", "2D" };
		
		Hand h1 = new Hand();
		h1.setName("p1");
		h1.setHand(onepair1);
		h1.initializeHand();
		
		Hand h2 = new Hand();
		h2.setName("p2");
		h2.setHand(high);
		h2.initializeHand();
		
		Hand[] hands = {h1, h2};
		
		Game g = new Game();
		g.setHands(hands);
		
		String validGame = PokerhandHelper.validateGame(g);
		assertEquals("The game has been rigged! There is a duplicate card somewhere!", validGame);
	}
}

