package main.actions;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class JumpDownKeyActionTest {

	private JumpDownKeyAction action;
	@Before
	public void setUp() throws Exception {
		action = new JumpDownKeyAction();
	}

	@Test
	public void testSetKeyCode() {
		action.setKeyCode(14);
		int code = action.getKeyCode();
		assertEquals(14, code);
	}

}
