package main.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

public class RotateClockwiseKeyActionTest {

	private RotateClockwiseKeyAction action;

	@Before
	public void setUp() {
		action = new RotateClockwiseKeyAction();
	}

	@Test
	public void testSetKeyCode() {
		action.setKeyCode(16);
		int code = action.getKeyCode();
		assertEquals(16, code);
		action.setKeyCode(20);
		int expectedKeyCode = 18;
		int actualKeyCode = action.getKeyCode();
		assertNotSame(expectedKeyCode, actualKeyCode);
	}

	@Test
	public void testSetAmount() {
		action.setAmount(20);
		int amount = action.getAmount();
		assertEquals(20, amount);
		action.setAmount(30);
		int expectedAmount = 20;
		int actualAmount = action.getAmount();
		assertNotSame(expectedAmount, actualAmount);
	}
}