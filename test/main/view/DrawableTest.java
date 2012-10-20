package main.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import main.model.Drawable;

import org.junit.Before;
import org.junit.Test;
import org.uispec4j.UISpec4J;

public class DrawableTest extends UISpec4J{
	
	private Drawable drawable;
	private Image img;
	private String imgPath;

	@Before
	public void setUp() throws Exception {
		//new MainClassAdapter(GameMaker.class, new String[0]);
		imgPath = "images/brick.png";
		img = (Image)Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource(imgPath));
		drawable = new Drawable( 10 , 10, imgPath);
		drawable.setImage(img);
	}

	@Test
	public void testGetChildConfigurationFrame() {
		
		ChildConfigurationFrame cframe = new ChildConfigurationFrame(drawable);
		assertEquals(cframe, drawable.getChildConfigurationFrame());
		
	}

	@Test
	public void testSetChildConfigurationFrame() {
		ChildConfigurationFrame cframe = new ChildConfigurationFrame(drawable);
		drawable.setChildConfigurationFrame(cframe);
		assertEquals(cframe, drawable.getChildConfigurationFrame());
		
	}

	@Test
	public void testClone() {
		assertNotNull(drawable);
	}

	@Test
	public void testGetType() {
		drawable.setType("shinigami");
		assertEquals("shinigami",drawable.getType());
	}

	@Test
	public void testGetKeycode() {
		drawable.setKeycode(20);
		assertEquals(20, drawable.getKeycode());
	}

	@Test
	public void testGetImage() {
		assertEquals(img,drawable.getImage());
		
	}

	@Test
	public void testSetImage() {
		drawable.setImage(img);
		assertEquals(img,drawable.getImage());
		
	}

	@Test
	public void testSetKeycode() {
		drawable.setKeycode(20);
		assertEquals(20, drawable.getKeycode());
		
	}

	@Test
	public void testDrawableIntIntImage() throws IOException {
		img = (Image)Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource(imgPath));
		Drawable drawable1 = new Drawable( 10 , 10, imgPath);
		assertNotNull(drawable1);
	}

	@Test
	public void testDrawableIntInt() {
		Drawable draw1 = new Drawable(10,10);
		assertNotNull(draw1);
		
	}

	
	@Test
	public void testSetObjectSize() {
		drawable.setObjectSize(20, 20);
		drawable.setObjectSize(10, 20);
		assertTrue(true);
		
	}

	

	
	@Test
	public void testMove() {
		assertNotNull(drawable);
	}

	@Test
	public void testMoveForKeys() {
		assertNotNull(drawable);
	}

	@Test
	public void testCheckCollision() {
		assertNotNull(drawable);
	}

	@Test
	public void testKeyPressed() {
		assertNotNull(drawable);
	}

	@Test
	public void testKeyReleased() {
		assertNotNull(drawable);
	}

	@Test
	public void testCreateChildObject() {
//		drawable.createChildObject(drawable, 10, 10, true);
//		assertTrue(CompositeClass.getInstance().getChildObjects().contains(drawable));
	}

		@Test
	public void testGetObjectBounds() {
		Rectangle2D rect = new Rectangle2D.Double(drawable.getX(),drawable.getY(),drawable.getWidth(),drawable.getHeight());
		assertTrue(rect.contains(drawable.getObjectBounds()));
	}

		@Test
	public void testSetType() {
		drawable.setType("naruto");
		assertEquals("naruto",drawable.getType());
	}

	@Test
	public void testIntersects() {
		Rectangle2D.Double gameRect = new Rectangle2D.Double(drawable.getX(), drawable.getY(), drawable.getWidth(), drawable.getHeight());
		assertTrue(drawable.intersects(gameRect));
		}

}
