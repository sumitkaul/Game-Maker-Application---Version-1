package main.actions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JumpLeftKeyActionTest {
	private JumpLeftKeyAction action;

	@Before
	public void setUp() throws Exception {
		action = new JumpLeftKeyAction();
	}

	@Test
	public void testSetKeyCode() {
		action.setKeyCode(10);
		assertEquals(10, action.getKeyCode());
	}

	@Test
	public void testGetKeyCode() {
		action.setKeyCode(12);
		assertEquals(12, action.getKeyCode());
	}

}
