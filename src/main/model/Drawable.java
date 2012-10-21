package main.model;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import main.actions.NormalMoveAction;
import main.actions.NullMoveAction;
import main.controller.GameController;
import main.interfaces.IAction;
import main.interfaces.KeyAction;
import main.utilities.ImageConverter;
import main.view.ChildConfigurationFrame;
import main.view.GameBoard;
import main.view.ListPanel;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.annotations.XStreamOmitField;


public class Drawable extends Sprite implements GameObject,KeyListener,Cloneable{

	@XStreamOmitField
	private transient static Logger log = Logger.getLogger(Drawable.class);
	@XStreamOmitField
	private transient File file;

	@XStreamOmitField
	private transient Image image;

	private IAction moveAction;
	@XStreamOmitField
	private transient ListPanel listPanel;
	private List <Integer> pressedKey;
	private List<KeyAction> keyActions;

	@XStreamOmitField
	private transient ChildConfigurationFrame childConfigurationFrame;

	private List<Drawable> childrenList=new ArrayList<Drawable>();
	private int keycode;
	private String type;

	public Object clone() {
		try
		{
		return super.clone();
		}
		catch(Exception e){ return null; }
		}

	
	public String getType() {
		return type;
	}
	public int getKeycode() {
		return keycode;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	
	public void setKeycode(int keycode) {
		this.keycode = keycode;
	}
	
	public Drawable(int x,int y, String file){
		
		this.x = x;
		this.y = y;
		this.vx = 0;
		this.vy = 0;
		this.image = (Image)Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(file));
		//this.image = new ImageIcon(this.getClass().getResource(file)).getImage();
		
		imageFilePath = file;
		this.id = ++Constants.ID;
		this.moveAction = new NullMoveAction();

		pressedKey=new ArrayList<Integer>();
		this.keyActions = new ArrayList<KeyAction>();
		if(image==null){
			log.warn("attempt to create Drawable will null File argument, defaulting to brick");
			//file = new Image("images/brick.png");
		}
		BufferedImage img = ImageConverter.toBufferedImage(this.image);
		this.setWidth(img.getWidth());
		this.setHeight(img.getHeight());
		log.debug("constructing drawable with width " + this.getWidth() + " and height " + this.getHeight());
		this.img = img;
		GameBoard gameBoard = GameBoard.getGameBoard();
		gameBoard.addKeyListener(this);
		gameBoard.setFocusable(true);

	}
	public Drawable(int x,int y){
		
		this.x = x;
		this.y = y;
		this.vx = 0;
		this.vy = 0;
	
	}

	public Drawable(int x,int y, File file){
		
		this.x = x;
		this.y = y;
		this.vx = 0;
		this.vy = 0;
		this.file = file;
		this.id = ++Constants.ID;
		this.moveAction = new NullMoveAction();

		pressedKey=new ArrayList<Integer>();
		this.keyActions = new ArrayList<KeyAction>();
		if(file==null){
			log.warn("attempt to create Drawable will null File argument, defaulting to brick");
			file = new File("images/brick.png");
		}
		try {
			BufferedImage img = ImageIO.read(this.file);
			this.setWidth(img.getWidth());
			this.setHeight(img.getHeight());
			log.debug("constructing drawable with width " + this.getWidth() + " and height " + this.getHeight());
			this.img = img;
		} catch (IOException e) {
			log.error("An exception! Oops!",e);
		}
		GameBoard gameBoard = GameBoard.getGameBoard();
		gameBoard.addKeyListener(this);
		gameBoard.setFocusable(true);

	}
	@Override
	public void setObjectSize(int windowWidth, int windowHeight) 
	{
		this.setX(this.getX()*((double)windowWidth/Constants.PREVIOUS_WINDOW_WIDTH));
		this.setY(this.getY()*((double)windowHeight/Constants.PREVIOUS_WINDOW_HEIGHT));
		if(windowWidth>Constants.PREVIOUS_WINDOW_WIDTH && windowHeight<Constants.PREVIOUS_WINDOW_HEIGHT) {
			this.setWidth((double)this.getWidth()/Constants.PREVIOUS_WINDOW_WIDTH*windowWidth+1);
			this.setHeight((double)this.getHeight()/Constants.PREVIOUS_WINDOW_HEIGHT*windowHeight);
		} else if(windowWidth<Constants.PREVIOUS_WINDOW_WIDTH && windowHeight>Constants.PREVIOUS_WINDOW_HEIGHT) {
			this.setWidth((double)this.getWidth()/Constants.PREVIOUS_WINDOW_WIDTH*windowWidth);
			this.setHeight((double)this.getHeight()/Constants.PREVIOUS_WINDOW_HEIGHT*windowHeight+1);
		} else if(windowWidth>Constants.PREVIOUS_WINDOW_WIDTH && windowHeight>Constants.PREVIOUS_WINDOW_HEIGHT) {
			this.setWidth((double)this.getWidth()/Constants.PREVIOUS_WINDOW_WIDTH*windowWidth+1);
			this.setHeight((double)this.getHeight()/Constants.PREVIOUS_WINDOW_HEIGHT*windowHeight+1);
		} else {
			this.setWidth((double)this.getWidth()/Constants.PREVIOUS_WINDOW_WIDTH*windowWidth);
			this.setHeight((double)this.getHeight()/Constants.PREVIOUS_WINDOW_HEIGHT*windowHeight);
		}
	}

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}

	public IAction getMoveAction() {
		return moveAction;
	}
	public void setMoveAction(IAction moveAction) {
		this.moveAction = moveAction;
	}


	public ChildConfigurationFrame getChildConfigurationFrame() {
		return childConfigurationFrame;
	}
	public void setChildConfigurationFrame(
			ChildConfigurationFrame childConfigurationFrame) {
		this.childConfigurationFrame = childConfigurationFrame;
	}


	public void keyPressed(KeyEvent e) {
		pressedKey.add(e.getKeyCode());
		listPanel=ListPanel.getInstance();
		GameController compositeClass=GameController.getInstance();
		
		if(!this.getChildrenList().isEmpty())
		{
			log.debug("list not empty");
			for(Drawable child:this.getChildrenList())
			{	
					if(child.getKeycode()==e.getKeyCode())
					{
						Drawable item = (Drawable) child.clone();
						
					//	item.setX((this.getX())+this.getWidth()/2);
					//	item.setY((this.getY())+this.getHeight()/2);
						item.setX(this.getX());
						item.setY(this.getY()-10);
						if(item.getActionsMap("follow"))
						{
							item.setVx(this.getVx());
							item.setVy(this.getVy());
							item.setMoveAction(new NormalMoveAction());
						}
						log.debug("child present"+item.getHeight()+"   "+item.getWidth()+"    "+item.getVx()+"  "+item.getVy());
						compositeClass.add(item);
						listPanel.addAddedSpriteElements(item.getName());
					}

			
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {	
		log.debug("key release '" + e.getKeyChar() + "' code: " + e.getKeyCode());
		try{
			pressedKey.remove(pressedKey.indexOf(e.getKeyCode()));
		} catch(ArrayIndexOutOfBoundsException err){
			log.debug("released key not in pressedKey list in Drawable:" + this.getName());
		}
	}	   

	public void createChildObject(Drawable d,int shiftX,int shiftY,boolean inheritanceFlag)
	{
		
	  listPanel=ListPanel.getInstance();
  	  GameController compositeClass=GameController.getInstance();
  	  childConfigurationFrame=d.getChildConfigurationFrame();
  	  String filepath="/images/"+childConfigurationFrame.getSpriteSelected();
  	  Drawable item = new Drawable(Constants.GAMEOBJECT_INTIAL_XVALUE,Constants.GAMEOBJECT_INTIAL_YVALUE, new File(getClass().getClassLoader().getResource(filepath).getPath()));
  	  
  	  if(childConfigurationFrame.isMovable()==true)
  		  item.setActionsMap("movable", true);
  		
  	  if(childConfigurationFrame.isDeflectable()==true)
		  item.setActionsMap("deflectable",true);
	  
  	  if(childConfigurationFrame.isCollidable()==true)
  		  item.setActionsMap("collidable",true);
  	  if(childConfigurationFrame.isDisappear()==true)
  		  item.setActionsMap("disappear", true);
  	 if(childConfigurationFrame.isDisappearBoth()==true)
 		  item.setActionsMap("disappearBoth", true);
  	  
  	  if(childConfigurationFrame.isBounce()==true)
  	  {
  	      item.setActionsMap("wallDeflectableBottom", true);
		  item.setActionsMap("wallDeflectableTop", true);
		  item.setActionsMap("wallDeflectableRight", true);
		  item.setActionsMap("wallDeflectableLeft", true);
  	  }

  	  
  	  item.setX((d.getX()+shiftX)+d.getWidth()/2);
  	  item.setY((d.getY()+shiftY)+d.getHeight()/2);
  	  item.setVx(childConfigurationFrame.getVelocityX());
  	  item.setVy(childConfigurationFrame.getVelocityY());
  	  item.setAx(childConfigurationFrame.getAccelerationX());
  	  item.setAy(childConfigurationFrame.getAccelerationY());
  	  item.setHeight(childConfigurationFrame.getChildHeight());
  	  item.setWidth(childConfigurationFrame.getChildWidth());
  	  item.setName(d.getName()+"_"+"Bullets");
  	  compositeClass.add(item);
  	  listPanel.addAddedSpriteElements(item.getName());

		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	public void addKeyAction(KeyAction thisKeyAction) {
		log.info("add new KeyAction to Drawable:" + this.getName() + " action:" + thisKeyAction);
		this.keyActions.add(thisKeyAction);
	}
	public List<KeyAction> getKeyActions() {
		return this.keyActions;
	}
	public List<Drawable> getChildrenList() {
		return childrenList;
	}
	public void setChildrenList(ArrayList<Drawable> childrenList) {
		this.childrenList = childrenList;
	}

	public List<Integer> getPressedKey() {
		return pressedKey;
	}


	public void setPressedKey(List<Integer> pressedKey) {
		this.pressedKey = pressedKey;
	}


	public void setKeyActions(List<KeyAction> keyActions) {
		this.keyActions = keyActions;
	}


	public Double getObjectBounds() {
		return new Rectangle2D.Double(this.getX(), this.getY(),
				this.getWidth(), this.getHeight());
	}
	
	public void setType(String type) {
		this.type = type;
		log.info("the type of the object " + this.name +" is set to " + this.type);
	}


	public boolean intersects(Rectangle2D rect) {
		Rectangle2D.Double gameRect = new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		return gameRect.intersects(rect);
	}

}
