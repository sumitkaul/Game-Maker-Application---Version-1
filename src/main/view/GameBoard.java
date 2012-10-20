package main.view;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import main.controller.GameController;
import main.controller.MenuHandler;
import main.model.Constants;
import main.model.Drawable;
import main.model.GameObject;
import main.utilities.ActionObjectPair;
import main.utilities.Event;
import main.utilities.ImageConverter;

import org.apache.log4j.Logger;


public class GameBoard extends Board implements MouseListener,MouseMotionListener, ActionListener, MouseWheelListener{

	private static final long serialVersionUID = 1L;


	private static GameBoard gameBoard;
	private Logger log = Logger.getLogger(GameBoard.class);
	private JPopupMenu pMenu;
	private JMenuItem removeMenuItem;
	private JMenuItem propertiesMenuItem;
	private JMenuItem duplicateMenuItem;
	private MenuHandler menuHandler;
	private int score=0;

	public static GameBoard getGameBoard(){
		if(gameBoard == null)
			gameBoard = new GameBoard();		
		return gameBoard;
	}

	public GameBoard() {
		this.addMouseListener(this);
		menuHandler = new MenuHandler();
		pMenu = new JPopupMenu();
		removeMenuItem = new JMenuItem("Remove");
		removeMenuItem.addActionListener(this);
		propertiesMenuItem = new JMenuItem("Properties");
		propertiesMenuItem.addActionListener(this);
		duplicateMenuItem = new JMenuItem("Duplicate");
		duplicateMenuItem.addActionListener(this);
		pMenu.add(removeMenuItem);
		pMenu.add(duplicateMenuItem);
		pMenu.add(propertiesMenuItem);
		this.addMouseWheelListener(this);
		this.addMouseMotionListener(this);
		Constants.BOARD_WIDTH=(int)(0.5*Constants.WINDOW_WIDTH);
		Constants.BOARD_HEIGHT=(int)(0.95*Constants.WINDOW_HEIGHT);
		super.setSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int score){
		this.score = score;
	}

	public void paint(Graphics g) {
		super.paint(g);
		drawAllObjects(g);
	}

	public void draw() {
		repaint();
	}
	
	public void drawAllObjects(Graphics g) {
		ClockDisplay.getInstance().draw(g);
		for (GameObject compositeObject : GameController.getInstance().getChildObjects()) {
			Drawable item = (Drawable)compositeObject;
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setPaint(new TexturePaint(item.getImg(), new Rectangle2D.Float(0, 0, 
					item.getImg().getWidth(), item.getImg().getHeight())));
			g2.rotate(Math.toRadians(item.getHeading()), item.getX() + item.getWidth() / 2, item.getY() + item.getHeight()
					/ 2);
			//log.debug("drawing " + this.getName() + " width:" + getWidth() + " height:" + getHeight());
			g.drawImage(item.getImg(), (int)item.getX(), (int)item.getY(), item.getWidth(), item.getHeight(), null);
			g2.rotate(Math.toRadians(-item.getHeading()), item.getX() + item.getWidth() / 2, item.getY() + item.getHeight()
					/ 2);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {		
	}
	
	
	public boolean contains(int x, int y, GameObject sprite) {
		java.awt.Rectangle rect = new java.awt.Rectangle(sprite.getX(), sprite.getY(), getWidth(), getHeight());
		return rect.contains(x, y);
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		boolean isObjectSelected = false;
		for(GameObject sprite : GameController.getInstance().getChildObjects()) {
			if(x>=sprite.getX() && x<=sprite.getX()+sprite.getWidth() && y>=sprite.getY() && y<=sprite.getY()+sprite.getHeight()) {
				GameController.getInstance().setSelectedObject(sprite);
				log.debug(sprite.getClass());
				isObjectSelected = true;
				break;
			}
		}
		if(!isObjectSelected)
		{
			GameController.getInstance().setSelectedObject(null);
			ListPanel.getInstance().getCombinedListModel().clear();
		}
		else
		{
			ListPanel.getInstance().getCombinedListModel().clear();
			populateCombinedList();
		}
		
		if(e.isPopupTrigger()){
			  pMenu.show(e.getComponent(), e.getX(), e.getY());
			 
			  }
		log.info("The selected object is " + GameController.getInstance().getSelectedObject());
	}

	public void populateCombinedList() {
		
		GameObject sprite = GameController.getInstance().getSelectedObject();
		HashMap<Event,List<ActionObjectPair>> map = sprite.getEvents();
		
		Iterator<Event> it = map.keySet().iterator();
		while  (it.hasNext())
		{
			Event event = (Event)it.next();
			List<ActionObjectPair> actionList = (List<ActionObjectPair>) map.get(event);
			for ( ActionObjectPair actionObject : actionList )
			{
				log.info("The action is " + actionObject.getAction());
				ListPanel.getInstance().addCombinedElements(event, actionObject);
			}
				
		}
		ListPanel.getInstance().getGameboard().revalidate();
		
		
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		if(me.isPopupTrigger()){
			  pMenu.show(me.getComponent(), me.getX(), me.getY());
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		for(GameObject sprite : GameController.getInstance().getChildObjects()) {
			if(mx>=sprite.getX() && mx<=sprite.getX()+sprite.getWidth() && my>=sprite.getY() && my<=sprite.getY()+sprite.getHeight()) {
				sprite.setX(mx-sprite.getWidth()/2);
				sprite.setY(my-sprite.getHeight()/2);
				break;
			}
		}	

		this.repaint();
		
		}


	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(removeMenuItem))
		{
			menuHandler.removeItem(GameController.getInstance().getSelectedObject());
			log.info("Remove menu item selected");
		}
		else if (e.getSource().equals(propertiesMenuItem))
		{
			menuHandler.showProperties(GameController.getInstance().getSelectedObject());
			log.info("properties menu item selected");
		}
		else if (e.getSource().equals(duplicateMenuItem))
		{
			menuHandler.duplicateItem(GameController.getInstance().getSelectedObject());
			log.info("duplicate menu item selected");
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		GameObject sprite = GameController.getInstance().getSelectedObject();
		log.info("in mouse wheel");
		if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
			log.info("In mouse wheel moved");
				Drawable ditem = (Drawable) sprite;
				float amount = e.getWheelRotation() * 5f;
				ditem.setHeight((int)(ditem.getHeight()+amount));
				ditem.setWidth((int)(ditem.getHeight()+amount));
				ditem.setImg(ImageConverter.toBufferedImage(ditem.getImage().getScaledInstance(ditem.getWidth(), ditem.getHeight(), Image.SCALE_SMOOTH)));
				
				log.debug("The new values are " + ditem.getHeight() + ditem.getWidth());
//				sprite.setHeight((int) (sprite.getHeight() + amount));
//				curSpriteObj.setWidth((int) (curSpriteObj.getWidth() + amount));
				//ditem.
				repaint();

			
		}
		
		
	}

}
