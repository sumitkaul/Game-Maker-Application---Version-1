package main.actions;

import static org.junit.Assert.assertEquals;
import main.actions.collide.CollideBounceAction;
import main.controller.GameController;
import main.model.Drawable;

import org.junit.Before;
import org.junit.Test;

public class CollideBounceActionTest {

	Drawable sprite1;
	Drawable sprite2;
	CollideBounceAction action;
	
	@Before
	public void setUp() throws Exception {
		action = new CollideBounceAction();
		sprite1 = new Drawable(0, 0);
		sprite1.setName("Sprite 1");
		sprite2 = new Drawable(10, 10);
		sprite2.setName("Sprite 2");
		GameController.getInstance().add(sprite1);
		GameController.getInstance().add(sprite2);
		sprite1.setVx(1);
		sprite1.setVy(1);
		sprite1.setHeight(20);
		sprite1.setWidth(20);
		sprite2.setVx(1);
		sprite2.setVy(1);
		sprite2.setHeight(20);
		sprite2.setWidth(20);
	}

	@Test
	public void testAct() {
		assertEquals(1,sprite1.getVx());
		assertEquals(1,sprite1.getVy());
		action.setAgainstObjectName(sprite2.getName());
		action.act(sprite1);
		assertEquals(-1,sprite1.getVx());
		assertEquals(-1,sprite1.getVy());
	}

}
