package main.actions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JumpRightKeyActionTest {
	
	private JumpRightKeyAction action;

	@Before
	public void setUp() throws Exception {
		action = new JumpRightKeyAction();
	}

	@Test
	public void testSetKeyCode() {
		action.setKeyCode(20);
		assertEquals(20, action.getKeyCode());
	}

	@Test
	public void testGetKeyCode() {
		action.setKeyCode(20);
		assertEquals(20, action.getKeyCode());

	}

}
