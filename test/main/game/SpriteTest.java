package main.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.File;

import main.model.Sprite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SpriteTest {
	private Sprite sprite;
	int xi = 40,yi = 40; 
	double xd = 40,yd = 40;
	File file1 = new File("images/ball.png");
	File file2 = new File("images/paddle.png");
	boolean movable = true;



	@Before
	public void setUp() throws Exception {
		sprite = new Sprite();
	}

	@After
	public void tearDown() throws Exception {
		sprite = null;
	}

	@Test
	/*
	 * tests whether or not getX returns integer values even when double values are passed as argument
	 */
	public void testGetX() {
		sprite.setX(40);
		int actualXI = sprite.getX();
		int expectedXI = xi;
		assertEquals(expectedXI, actualXI);
		sprite.setX(xd);
		int actualXD = sprite.getX();
		double expectedXD = xd;
		assertNotSame(expectedXD, actualXD);
	}

	@Test
	/*
	 *tests whether or not getY returns integer values even when double values are passed as argument
	 */
	public void testGetY() {
		sprite.setY(40);
		int actualYI = sprite.getY();
		int expectedYI = yi;
		assertEquals(expectedYI, actualYI);
		sprite.setY(yd);
		int actualYD = sprite.getX();
		double expectedYD = yd;
		assertNotSame(expectedYD, actualYD);
	}

	@Test
	/*
	 * tests if a sprite object is movable or not. It also indicates whether or 
	 * not the sprite has been selected to be movable or not. 
	 */
	public void testIsMovable() {
		sprite.setActionsMap("moveable", true);
		boolean expectedVal = sprite.getActionsMap("moveable");
		assertTrue(movable == expectedVal);

	}

}
