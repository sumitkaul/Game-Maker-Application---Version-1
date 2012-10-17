package main.game;

import static org.junit.Assert.*;

import main.model.ListItem;

import org.junit.Before;
import org.junit.Test;

public class ListItemTest {
	
	private ListItem listItem;
	private String name = "test";
	private int spriteId = 10;
	
	@Before
	public void setUp() throws Exception {
		listItem = new ListItem(name,spriteId);
	}

	
	@Test
	public void testGetSpriteId() {
		int actualSpriteId = listItem.getSpriteId();
		int expectedSpriteId = spriteId;
		assertEquals(expectedSpriteId, actualSpriteId);
		listItem.setSpriteId(100);
		actualSpriteId = listItem.getSpriteId();
		expectedSpriteId = spriteId;
		assertNotSame(expectedSpriteId,actualSpriteId);
	}
	
	@Test
	public void testGetName() {
		String actualSpriteName = listItem.getName();
		String expectedSpriteName = name;
		assertEquals(expectedSpriteName, actualSpriteName);
		listItem.setName("testing");
		actualSpriteName = listItem.getName();
		assertNotSame(expectedSpriteName,actualSpriteName);
		
	}

	
}
