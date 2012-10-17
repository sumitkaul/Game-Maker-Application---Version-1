package main.view;

import static org.junit.Assert.*;

import main.controller.GameMaker;
import main.model.ListItem;
import main.utilities.Action;
import main.utilities.ActionObjectPair;
import main.utilities.Event;
import main.utilities.ScrollListModel;

import org.junit.Before;
import org.junit.Test;
import org.uispec4j.UISpec4J;
import org.uispec4j.interception.MainClassAdapter;

public class GameBoardTest extends UISpec4J{
	
	private GameBoard gameBoard ;

	@Before
	public void setUp() throws Exception {
		
		new MainClassAdapter(GameMaker.class, new String[0]);
		gameBoard  = new GameBoard();
	}

	@Test
	public void testGetGameBoard() {
		assertNotNull(gameBoard);
	}

	@Test
	public void testGameBoard() {
		assertNotNull(gameBoard);
	}

	@Test
	public void testPaintGraphics() {
		assertNotNull(gameBoard);
	}

	@Test
	public void testDraw() {
		assertNotNull(gameBoard);
	}

	@Test
	public void testMouseClicked() {
		assertNotNull(gameBoard);
		
	}

	
	@Test
	public void testContainsIntIntGameObject() {
//		java.awt.Rectangle rect = new java.awt.Rectangle(10,10,20,20);
//		assertTrue(rect.contains(15, 15));
//		
		
	}

	@Test
	public void testPopulateCombinedList() {
//		ListItem item = new ListItem("naruto", 20);
//		Event event = Event.COLLISION;
//		Action action = Action.BOUNCE;
//		ActionObjectPair actionObject = new ActionObjectPair(action, "");
//		ListPanel.getInstance().addCombinedElements(event, actionObject);
//		ScrollListModel model = ListPanel.getInstance().getCombinedListModel();
//		assertTrue(model.contains(event.getEvent()+" + "+action.toString()));
//		
	}

	@Test
	public void testMouseReleased() {
		assertNotNull(gameBoard);
	}

	@Test
	public void testMouseDragged() {
		assertNotNull(gameBoard);
	}

	@Test
	public void testMouseMoved() {
		assertNotNull(gameBoard);
	}

	

}
