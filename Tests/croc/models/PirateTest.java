package croc.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class PirateTest {

	@Test
	public void LimbPoppingTest() {
		Pirate p = new Pirate(PirateColor.WHITE, 7, null);
		assertEquals(4, p.getLimbCount());
		p.popLeftArm();
		assertEquals(false, p.hasLeftArm());
		assertEquals(3, p.getLimbCount());
		p.popLeftArm();
		assertEquals(3, p.getLimbCount());
		p.popLeftLeg();
		assertEquals(2, p.getLimbCount());
		p.popRightArm();
		assertEquals(1, p.getLimbCount());
		assertEquals(true, p.isAlive());
		p.popRightLeg();
		assertEquals(0, p.getLimbCount());
		assertEquals(false, p.isAlive());
	}

}
