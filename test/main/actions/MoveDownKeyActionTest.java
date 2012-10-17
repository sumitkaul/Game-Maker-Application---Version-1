package main.actions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoveDownKeyActionTest {
	
	private MoveDownKeyAction action;

	@Before
	public void setUp() throws Exception {
		action = new MoveDownKeyAction();
	}

	@Test
	public void testMoveDownKeyAction() {
		assertNotNull(action);
	}

	@Test
	public void testMoveDownKeyActionInt() {
		MoveDownKeyAction action1 = new MoveDownKeyAction(10);
		assertNotNull(action1);
	}

	@Test
	public void testAct() {
		
	}

	@Test
	public void testSetKeyCode() {
		action.setKeyCode(14);
		int code = action.getKeyCode();
		assertEquals(14, code);
	}

	@Test
	public void testSetAmount() {
		action.setAmount(10);
		action.setAmount(20);
		assertNotNull(action);
	}

	@Test
	public void testToString() {
		assertEquals("Move Down", action.toString());
	}

	@Test
	public void testGetKeyCode() {
		action.setKeyCode(14);
		int code = action.getKeyCode();
		assertEquals(14, code);
	}

}
