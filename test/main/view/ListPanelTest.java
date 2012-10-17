package main.view;

import static org.junit.Assert.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import main.controller.GameMaker;
import main.controller.LayoutGrid;
import main.model.GameObject;
import main.model.ListItem;
import main.utilities.ScrollListModel;

import org.junit.Before;
import org.junit.Test;
import org.uispec4j.UISpec4J;
import org.uispec4j.interception.MainClassAdapter;

public class ListPanelTest extends UISpec4J{
	
	private ListPanel listPanel; 
	
	@Before
	public void setUp() throws Exception {
/*		new MainClassAdapter(GameMaker.class, new String[0]);
		listPanel = new ListPanel();*/
	}

	@Test
	public void testListPanel() {
			}

	@Test
	public void testSwitchPanelJPanelGridLayout() {
/*		JPanel panel = listPanel.getPanel();
		GridLayout grid = new GridLayout(3,2);
		LayoutGrid lg = new LayoutGrid(panel);
		lg.execute(panel, grid);
		FlowLayout fl = new FlowLayout();
		assertNotSame("the layoutchange should not be the same", fl, listPanel.getPanel().getLayout());
*/
	}

	@Test
	public void testGetPanel() {
	/*	JPanel panel = listPanel.getPanel();
		assertNotNull("Panel Instance should not be null", panel);
	*/	
	}

	@Test
	public void testSetPanel() {
/*		JPanel panel = new JPanel();
		listPanel.setPanel(panel);
		assertEquals(" The JPanel of the list panel should be  same " , panel,listPanel.getPanel() );*/
	}

	@Test
	public void testAddAddedSpriteElementsListItem() {
		
/*		ScrollListModel model = new ScrollListModel();
		String name = "name";
		 model.add(name);
		 assertTrue(model.contains(name));
*/		
	}


	@Test
	public void testAddCombinedElements() {
/*		ScrollListModel model = new ScrollListModel();
		String name = "name";
		model.add(name);
		assertTrue(model.contains(name));
*/			
		
	}

	
}
