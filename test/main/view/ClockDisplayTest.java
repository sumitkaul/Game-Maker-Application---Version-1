package main.view;

import static org.junit.Assert.*;

import main.controller.GameMaker;

import org.junit.Before;
import org.junit.Test;
import org.uispec4j.UISpec4J;
import org.uispec4j.interception.MainClassAdapter;

public class ClockDisplayTest extends UISpec4J{
	
	private ClockDisplay clock = new ClockDisplay();

	@Before
	public void setUp() throws Exception {
		
		new MainClassAdapter(GameMaker.class, new String[0]);
		
	
	}

	
	@Test
	public void testSetObjectSize() {
		
		assertNotNull(clock);
	}

	
	
		@Test
	public void testGetInterval() {
		clock.setInterval(200);
		assertEquals((int)200, (int)clock.getInterval());
		
	}

	
}
