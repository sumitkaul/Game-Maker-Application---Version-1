package main.actions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JumpUpKeyActionTest {
	
	private JumpUpKeyAction action;

	@Before
	public void setUp() throws Exception {
		action = new JumpUpKeyAction();
	}

	@Test
	public void testSetKeyCode() {
		action.setKeyCode(25);
		assertEquals(25,action.getKeyCode());
	}

	@Test
	public void testGetKeyCode() {
		action.setKeyCode(25);
		assertEquals(25,action.getKeyCode());
	}

}
