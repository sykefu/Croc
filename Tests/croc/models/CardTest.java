package croc.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void correctValueTest() {
		Card c = new Card(5);
		assertEquals("card equal to 5",5, c.value);
	}
	
	@Test
	public void PlayCardTest() {
		Card c = new Card(1);
		c.playCard();
		assertEquals(false, c.isInHand());
	}
	
	@Test
	public void RecoverCardTest() {
		Card c = new Card(1);
		c.playCard();
		c.recoverCard();
		assertEquals(true, c.isInHand());
	}

}
