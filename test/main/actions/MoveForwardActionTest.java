package main.actions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoveForwardActionTest {
	
	private MoveForwardAction action;

	@Before
	public void setUp() throws Exception {
		action = new MoveForwardAction();
	}

	@Test
	public void testToString() {
		assertEquals("Normal Move", action.toString());
	}

	

}
