/*package main.view;

import static org.junit.Assert.*;

import java.io.File;

import main.controller.GameMaker;

import org.junit.Before;
import org.junit.Test;
import org.uispec4j.UISpec4J;
import org.uispec4j.interception.MainClassAdapter;

public class BackGroundImageTest extends UISpec4J{
	//private 
	BackGroundImage img = new BackGroundImage();
	@Before
	public void setUp() throws Exception {
		new MainClassAdapter(GameMaker.class, new String[0]);
	}

	@Test
	public void testBackGroundImage() {
		assertNotNull(img);
	}

	@Test
	public void testSetBackGroundImage() {
		File file1= new File("src/images/brick.png");
		//assertNotNull(img.setBackGroundImage(file1.toString()));
	}

}
*/