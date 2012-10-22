package main.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.actions.DescendLeftAndRightAction;
import main.actions.FireAction;
import main.actions.MoveAroundAction;
import main.actions.NormalMoveAction;
import main.model.Constants;
import main.model.Drawable;
import main.utilities.Action;
import main.utilities.Event;

import org.apache.log4j.Logger;

public class ChildConfigurationFrame extends JFrame implements ActionListener, ItemListener{

	/**
	 * 
	 */
	private Logger log = Logger.getLogger(ChildConfigurationFrame.class);
	private static final long serialVersionUID = 1L;
	private JLabel labelWidth;
	private JLabel labelHeight;
	private JLabel labelVelocityX;
	private JLabel labelVelocityY;
	private JLabel labelAccelerationX;
	private JLabel labelAccelerationY;
	private JLabel labelSpriteSelection;
	private JTextField textWidth;
	private JTextField textHeight;
	private JTextField textVelocityX;
	private JTextField textVelocityY;
	private JTextField textAccelerationX;
	private JTextField textAccelerationY;

	private JLabel labelActions;
	private JButton buttonCreate;
	private JCheckBox checkMovable;
	private JCheckBox checkDeflectable;
	private JCheckBox checkDisappear;
	private JCheckBox checkBounce;
	private JCheckBox checkMoveAround;
	private JCheckBox checkMoveLeftRight;
	private JCheckBox checkFolowParentOreintaion;
	private JComboBox childImageBox;

	private double xCoordinates;
	private double yCoordinates;
	private int childHeight;
	private int childWidth;

	private int velocityX;
	private int velocityY;
	private int accelerationX;
	private int accelerationY;

	private int childKeyCode;
	private boolean movable;
	private boolean collidable;
	private boolean disappear;
	private boolean bounce;
	private boolean deflectable;
	private boolean disappearBoth;
	private boolean moveAround;
	private boolean leftRight;
	private boolean parentOrient;
	private String spriteSelected;
	private KeyListenerPanel keyListenerPanel;
	private Drawable childObject;
	private Drawable parentObject;
	
	private File currentDirectory;
	private JFileChooser fileChooser;
	
	public int getChildHeight() {
		return childHeight;
	}

	public void setChildHeight(int childHeight) {
		this.childHeight = childHeight;
	}

	

	public ChildConfigurationFrame(Drawable parent)

	{
		
		parentObject=parent;
		populateImagesList();
		parent.setChildConfigurationFrame(this);
		labelWidth = new JLabel("Width");
		labelHeight = new JLabel("Height");
		labelVelocityX = new JLabel("Velocity X");
		labelVelocityY = new JLabel("Velocity Y");
		labelAccelerationX = new JLabel("Acceleration X");
		labelAccelerationY = new JLabel("Acceleration Y");
		labelActions = new JLabel("Check the Actions");
		textWidth = new JTextField(1);
		textWidth.setText("10");
		textHeight = new JTextField(1);
		textHeight.setText("10");
		textVelocityX = new JTextField(1);
		textVelocityX.setText("0");
		textVelocityY = new JTextField(1);
		textVelocityY.setText("0");
		textAccelerationX = new JTextField(1);
		textAccelerationX.setText("0");
		textAccelerationY = new JTextField(1);
		textAccelerationY.setText("0");
		labelSpriteSelection = new JLabel("Child Sprite");

		checkMovable = new JCheckBox("Movable");
		//	checkMovable.setSelected(true);
		checkDeflectable = new JCheckBox("Deflectable");
		//	checkDeflectable.setSelected(true);
		checkDisappear = new JCheckBox("Disappear");
		//		checkDisappear.setSelected(true);
		checkBounce = new JCheckBox("Bounce");
		//	checkBounce.setSelected(true);
		checkMoveAround= new JCheckBox("Move Around");	
		//checkMoveAround.setSelected(true);
		checkMoveLeftRight=new JCheckBox("Move Left Right");
		//checkMoveLeftRight.setSelected(true);
		checkFolowParentOreintaion=new JCheckBox("Follow Parent Orientation");
		checkBounce.addItemListener(this);
		checkMovable.addItemListener(this);
		checkMoveAround.addItemListener(this);
		checkMoveLeftRight.addItemListener(this);
		checkDisappear.addItemListener(this);
		checkDeflectable.addItemListener(this);
		checkFolowParentOreintaion.addItemListener(this);


	
		buttonCreate = new JButton("Booom!!!");

		this.keyListenerPanel = new KeyListenerPanel();
		childKeyCode = -1;

		this.setSize(Constants.CHILD_OBJECT_CONFIGURATION_FRAME_WIDTH,
				Constants.CHILD_OBJECT_CONFIGURATION_FRAME_HEIGHT);
		this.setLayout(new GridLayout(0, 1));
		this.add(labelSpriteSelection);
		this.add(this.getBoxSpriteSelection());
		this.add(labelWidth);
		this.add(textWidth);
		this.add(labelHeight);
		this.add(textHeight);

		this.add(labelVelocityX);
		this.add(textVelocityX);
		this.add(labelVelocityY);
		this.add(textVelocityY);
		this.add(labelAccelerationX);
		this.add(textAccelerationX);
		this.add(labelAccelerationY);
		this.add(textAccelerationY);
		this.add(labelActions);
		this.add(checkMovable);
		this.add(checkMoveAround);
		this.add(checkMoveLeftRight);
		this.add(checkDeflectable);
		this.add(checkDisappear);
		this.add(checkBounce);
		this.add(checkFolowParentOreintaion);
		this.add(this.keyListenerPanel);
		this.add(buttonCreate);
		buttonCreate.addActionListener(this);
		
		movable = false;
		collidable = false;
		disappear = false;
		bounce = false;
		disappearBoth = false;
		parentOrient=false;

		this.pack();
		this.setFocusable(true);
		this.setVisible(true);
		
		
	}
	public void actionPerformed(ActionEvent event) {

		
		if (event.getSource() == buttonCreate)
		{
		  	log.debug("buttoncreate created childojects");
			childObject = new Drawable(Constants.GAMEOBJECT_INTIAL_XVALUE,Constants.GAMEOBJECT_INTIAL_YVALUE,childImageBox.getSelectedItem().toString());
			childObject.setHeight((Integer.parseInt(textHeight.getText())));
			childObject.setWidth(Integer.parseInt(textWidth.getText()));
			childObject.setVx(Integer.parseInt(textVelocityX.getText()));
			childObject.setVy(Integer.parseInt(textVelocityY.getText()));
			childObject.setHeading(parentObject.getHeading());
			childObject.setX(parentObject.getX()/2);
			childObject.setY(parentObject.getY()/2);
			log.debug("vx and vy values"+childObject.getVx()+"    "+childObject.getVy());
			childObject.setAx(Integer.parseInt(textAccelerationX.getText()));
			childObject.setAy(Integer.parseInt(textAccelerationY.getText()));
			log.debug("values movable "+isMovable()+"   moveAround "+isMoveAround());
			
			if(this.isMovable())
			{
				childObject.setActionsMap("movable", true);
				childObject.setMoveAction(new FireAction());
				childObject.addEvent(Event.MOVE, Action.NORMAL_MOVE, "");
				log.debug("movable with normal action move");
			}
			if(this.isMoveAround())
			{
				childObject.setMoveAction(new MoveAroundAction());
				childObject.addEvent(Event.MOVE, Action.MOVE_AROUND, "");
				childObject.setActionsMap("movable", true);
				log.debug("move around action move");
			}
			if(this.isLeftRight())
			{
				childObject.setMoveAction(new DescendLeftAndRightAction());
			}
			if(this.isCollidable())
			{
				childObject.setActionsMap("collidable", true);
				childObject.setActionsMap("wallDeflectableBottom", true);
				childObject.setActionsMap("wallDeflectableTop", true);
				childObject.setActionsMap("wallDeflectableRight", true);
				childObject.setActionsMap("wallDeflectableLeft", true);
			}
			if(this.isDisappear())
			{
				childObject.setActionsMap("collidable", true);
				childObject.setActionsMap("disappear", true);
				childObject.addEvent(Event.COLLISION, Action.DISAPPEAR, Constants.ANY_OBJECT);
			}
			if(this.isParentOrient())
			{
				childObject.setActionsMap("follow", true);
			}
			
		
					
			childObject.setName("bullets");
			childObject.setKeycode(this.keyListenerPanel.getKeyCode());
			parentObject.getChildrenList().add(childObject);

		}
		
		
		
		
		}

		
	public double getxCoordinates() {
		return xCoordinates;
	}

	public void setxCoordinates(double xCoordinates) {
		this.xCoordinates = xCoordinates;
	}

	public double getyCoordinates() {
		return yCoordinates;
	}

	public void setyCoordinates(double yCoordinates) {
		this.yCoordinates = yCoordinates;
	}

	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

	public int getAccelerationX() {
		return accelerationX;
	}

	public void setAccelerationX(int accelerationX) {
		this.accelerationX = accelerationX;
	}

	public int getAccelerationY() {
		return accelerationY;
	}

	public void setAccelerationY(int accelerationY) {
		this.accelerationY = accelerationY;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	public boolean isDisappear() {
		return disappear;
	}

	public void setDisappear(boolean disappear) {
		this.disappear = disappear;
	}

	public boolean isDeflectable() {
		return deflectable;
	}

	public void setDeflectable(boolean deflectable) {
		this.deflectable = deflectable;
	}

	public boolean isDisappearBoth() {
		return disappearBoth;
	}

	public void setDisappearBoth(boolean disappearBoth) {
		this.disappearBoth = disappearBoth;
	}

	public boolean isBounce() {
		return bounce;
	}

	public void setBounce(boolean bounce) {
		this.bounce = bounce;
	}

	public void setChildKeyCode(int childKeyCode) {
		this.childKeyCode = childKeyCode;
	}

	public int getChildKeyCode() {
		return childKeyCode;
	}
	
	public void setParentObject(Drawable parent)
	{
		this.parentObject = parent;
	}
	
	public Drawable getParentObject(){
		return(parentObject);
	}

	public JComboBox getBoxSpriteSelection() {

		return childImageBox;
	}

	public void setBoxSpriteSelection(JComboBox boxSpriteSelection) {
		this.childImageBox = boxSpriteSelection;
	}

	public String getSpriteSelected() {
		return spriteSelected;
	}

	public void setSpriteSelected(String spriteSelected) {
		this.spriteSelected = spriteSelected;
	}

	public int getChildWidth() {
		return childWidth;
	}

	public void setChildWidth(int childWidth) {
		this.childWidth = childWidth;
	}

	public void populateImagesList() {

		childImageBox = new JComboBox();

			ZipInputStream zip;
			try {
				zip = new ZipInputStream((getClass().getClassLoader().getResourceAsStream("images.jar")));
			
				ZipEntry ze = null;

			    while((ze = zip.getNextEntry()) != null) {
			        String entryName = ze.getName();
			        if(entryName.endsWith(".png") ) {
			        	childImageBox.addItem(entryName);
			        }
			    }
			} catch (IOException e1) {
				e1.printStackTrace();
			}

	}

	public boolean isMoveAround() {
		return moveAround;
	}

	public void setMoveAround(boolean moveAround) {
		this.moveAround = moveAround;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source= e.getItemSelectable();
		if(source == this.checkMovable)
			this.setMovable(true);
		if(source==this.checkMoveAround)
			this.setMoveAround(true);
		if(source ==this.checkMoveLeftRight)
			this.setLeftRight(true);
		if(source==this.checkBounce)
			this.setBounce(true);
		if(source==this.checkDeflectable)
			this.setDeflectable(true);
		if(source==this.checkDisappear)
			this.setDisappear(true);
		if(source==this.checkFolowParentOreintaion)
			this.setParentOrient(true);
		if (e.getStateChange() == ItemEvent.DESELECTED)
		{
			
		}
	}

	public boolean isParentOrient() {
		return parentOrient;
	}

	public void setParentOrient(boolean parentOrient) {
		this.parentOrient = parentOrient;
	}

	

	public boolean isLeftRight() {
		return leftRight;
	}

	public void setLeftRight(boolean leftRight) {
		this.leftRight = leftRight;
	}

	

}

