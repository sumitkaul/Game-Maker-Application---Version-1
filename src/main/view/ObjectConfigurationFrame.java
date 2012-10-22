package main.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.actions.DescendLeftAndRightAction;
import main.actions.JumpDownKeyAction;
import main.actions.JumpLeftKeyAction;
import main.actions.JumpRightKeyAction;
import main.actions.JumpUpKeyAction;
import main.actions.MoveAroundAction;
import main.actions.MoveDownKeyAction;
import main.actions.MoveForwardKeyAction;
import main.actions.MoveLeftKeyAction;
import main.actions.MoveRightKeyAction;
import main.actions.MoveUpKeyAction;
import main.actions.NormalMoveAction;
import main.actions.NullKeyAction;
import main.actions.PlaySoundAction;
import main.actions.RotateClockwiseKeyAction;
import main.actions.RotateCounterClockwiseKeyAction;
import main.controller.GameController;
import main.interfaces.IAction;
import main.interfaces.KeyAction;
import main.model.Constants;
import main.model.Drawable;
import main.model.GameObject;
import main.utilities.Action;
import main.utilities.Event;
import main.utilities.ImageConverter;
import main.utilities.JarReader;
import main.utilities.Sound;

import org.apache.log4j.Logger;

import repos.SoundRepo;

public class ObjectConfigurationFrame extends JFrame implements ActionListener,
ItemListener {
	/**
	 * 
	 */
	private static Logger log = Logger.getLogger(ObjectConfigurationFrame.class);
	private static final long serialVersionUID = 1L;
	private List<JComponent> components;

	private JButton create;
	private JButton update;
	private JButton keyboardEventCreate;
	private JPanel againstObjectPanel;
	private JPanel soundPanel;

	private JTextField widthField;
	private JTextField heightField;
	private JTextField xVelocityField;
	private JTextField yVelocityField;
	private JTextField xAccelerationField;
	private JTextField yAccelerationField;
	private JTextField headingField;

	private JCheckBox checkStickCollision;
	private JCheckBox checkDisappearCollision;
	private JCheckBox checkDefelectableCollision;
	private JCheckBox checkBounceCollision;

	private JComboBox eventSelectionBox;
	private JComboBox actionSelectionBox;
	private JComboBox againstObjectSelectionBox;
	private KeyListenerPanel keyListenerPanel;
	private JComboBox keyActionBox;
	private int gridLayoutRows = 1;

	private Drawable object;
	private JComboBox soundSelectionBox;
	private List<String> list;
	ArrayList<String> audioList = new ArrayList<String>();
	private String previewAudioFile;

	public ObjectConfigurationFrame(Drawable d) {
		
		ZipInputStream zip;
		try {
			zip = new ZipInputStream((getClass().getClassLoader().getResourceAsStream("sound.jar")));
		
			ZipEntry ze = null;
			log.info("ZipEntry is"+ze);

		    while((ze = zip.getNextEntry()) != null) {
		        String entryName = ze.getName();
		        log.debug("entryName="+entryName);
		        if(entryName.endsWith(".wav") ) {
		        	audioList.add(entryName);
		        	}
		    }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		for(int i=0; i<audioList.size();i++)
	    {
	    	log.debug("Audio list entry:"+audioList.get(i));	
	    }
		
		
		this.object = d;
		this.setTitle(d.getName());
		this.setLayout(new GridLayout(1, 1));

		this.widthField = new JTextField(new String(
				Integer.toString(this.object.getWidth())));
		this.addConfigurationItem("Width", widthField);

		this.heightField = new JTextField(new String(
				Integer.toString(this.object.getHeight())));
		this.addConfigurationItem("Height", heightField);

		this.xVelocityField = new JTextField(new String(
				Integer.toString(this.object.getVx())));
		this.addConfigurationItem("X Velocity", xVelocityField);

		this.yVelocityField = new JTextField(new String(
				Integer.toString(this.object.getVy())));
		this.addConfigurationItem("Y Velocity", yVelocityField);

		this.xAccelerationField = new JTextField(new String(
				Integer.toString(this.object.getAx())));
		this.addConfigurationItem("X Acceleration", xAccelerationField);

		this.yAccelerationField = new JTextField(new String(
				Integer.toString(this.object.getAy())));
		this.addConfigurationItem("Y Acceleration", yAccelerationField);

		this.headingField = new JTextField(new String(
				Integer.toString(this.object.getHeading())));
		this.addConfigurationItem("Heading", headingField);

		this.eventSelectionBox = new JComboBox(Event.values());
		this.addConfigurationItem("Events", eventSelectionBox);

		this.actionSelectionBox = new JComboBox(Action.values());
		this.addConfigurationItem("Actions", actionSelectionBox);



		this.againstObjectSelectionBox = new JComboBox();
		this.soundSelectionBox = new JComboBox();
		JButton preview = new JButton("Preview");

		againstObjectPanel = new JPanel();
		againstObjectPanel.add(new JLabel("Collide against Object"));
		againstObjectPanel.add(againstObjectSelectionBox);
		againstObjectPanel.add(new JLabel("Sound"));
		againstObjectPanel.add(soundSelectionBox);
		againstObjectPanel.add(preview);
		
		preview.addActionListener(new ActionListener()   {	
			public void actionPerformed(ActionEvent e) {
				int soundSlection = soundSelectionBox.getSelectedIndex();
				if( soundSelectionBox.getSelectedIndex() != 0)
				{
					Sound.getInstance().setSound_file(audioList.get(soundSlection-1));
					Sound.getInstance().playSound();
				}
				else 
				{	
					JOptionPane.showMessageDialog(soundSelectionBox, "Please select an audio file");
				}
		       }
			
				
		});
		
		this.gridLayoutRows++;
		this.setLayout(new GridLayout(this.gridLayoutRows, 1));
		this.add(againstObjectPanel);
		againstObjectPanel.setVisible(false);

//		this.soundSelectionBox = new JComboBox();
//		soundPanel = new JPanel();
//		soundPanel.add(new JLabel("Select sound : "));
//		
		this.gridLayoutRows++;
		this.setLayout(new GridLayout(this.gridLayoutRows, 1));
		//soundPanel.setVisible(false);
		
//		JarReader reader = new JarReader();
//		reader.setZipStream("sounds.jar");
		list = SoundRepo.getInstance().getSoundNameList();
		for (int i = 0; i < list.size(); i++) {
	    	soundSelectionBox.addItem(list.get(i).toString());
		}
//		soundPanel.add(soundSelectionBox);
//		this.add(soundPanel);
		
		eventSelectionBox.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Event event = (Event) eventSelectionBox.getSelectedItem();
				if(event.equals(Event.COLLISION)) 
				{
					againstObjectPanel.setVisible(true);
					repopulate();
				} else {
					againstObjectPanel.setVisible(false);
					againstObjectSelectionBox.setSelectedItem(Constants.NONE);
					repopulate();
				}
			}
		});

//		actionSelectionBox.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				Action action = (Action) actionSelectionBox.getSelectedItem();
//				if(action.equals(Action.PLAY_SOUND))
//				{
//					soundPanel.setVisible(true);
//				}
//				else
//				{
//					soundPanel.setVisible(false);
//				}
//			}
//		
//		});

		checkBounceCollision = new JCheckBox("Bounce");
		checkDefelectableCollision = new JCheckBox("Deflectable");
		checkDisappearCollision = new JCheckBox("Disappear");
		checkStickCollision = new JCheckBox("Stick");
		checkBounceCollision.addItemListener(this);
		checkDefelectableCollision.addItemListener(this);
		checkDisappearCollision.addItemListener(this);
		checkStickCollision.addItemListener(this);
		create = new JButton("Create");
		this.addConfigurationItem("Events", create);
		
		update = new JButton("Update");
		update.addActionListener(this);
		this.addConfigurationItem("", update);
		
		JPanel collisionBoxesPanel = new JPanel();
		collisionBoxesPanel.add(checkBounceCollision);
		collisionBoxesPanel.add(checkDefelectableCollision);
		collisionBoxesPanel.add(checkDisappearCollision);
		collisionBoxesPanel.add(checkStickCollision);
		//this.addConfigurationItem("Collision", collisionBoxesPanel);

		keyboardEventCreate = new JButton("Create Key Event");
		this.addConfigurationItem("Keyboard Events", keyboardEventCreate);
		keyboardEventCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KeyAction thisKeyAction = new NullKeyAction();
				String selectedAction = (String) keyActionBox.getSelectedItem();
				if (!selectedAction.equals("None")) {
					if (selectedAction.equals("Move Up")) {
						thisKeyAction = new MoveUpKeyAction();
					} else if (selectedAction.equals("Move Down")) {
						thisKeyAction = new MoveDownKeyAction();
					} else if (selectedAction.equals("Move Left")) {
						thisKeyAction = new MoveLeftKeyAction();
					} else if (selectedAction.equals("Move Right")) {
						thisKeyAction = new MoveRightKeyAction();
					} else if (selectedAction.equals("Move Forward")) {
						thisKeyAction = new MoveForwardKeyAction();
					} else if (selectedAction.equals("Rotate Clockwise")) {
						thisKeyAction = new RotateClockwiseKeyAction();
					} else if (selectedAction.equals("Rotate CounterClockwise")) {
						thisKeyAction = new RotateCounterClockwiseKeyAction();
					} else if (selectedAction.equals("Jump Up")) {
						thisKeyAction = new JumpUpKeyAction();
					} else if (selectedAction.equals("Jump Down")) {
						thisKeyAction = new JumpDownKeyAction();
					} else if (selectedAction.equals("Jump Left")) {
						thisKeyAction = new JumpLeftKeyAction();
					} else if (selectedAction.equals("Jump Right")) {
						thisKeyAction = new JumpRightKeyAction();
					}
					thisKeyAction.setKeyCode(keyListenerPanel.getKeyCode());
					object.addKeyAction(thisKeyAction);
					if (e.getSource() == keyboardEventCreate && selectedAction.equals("Create Object")) {
						int ok = JOptionPane.showConfirmDialog(null,
								"Would Like to Create a Child Object", "Child Object",
								JOptionPane.YES_NO_OPTION);
						if (ok == 0) {
							new ChildConfigurationFrame(object);
						}
					}
					keyActionBox = makeKeyActionBox();
					keyListenerPanel = new KeyListenerPanel();
					addKeyActionCombo(keyListenerPanel, keyActionBox);
					validate();
					repaint();
					updateGameObject();
				}
				GameBoard.getGameBoard().repaint();
				repaint();
			}
		});

		for(KeyAction keyAction : this.object.getKeyActions()){
			KeyListenerPanel keyListenerPanel = new KeyListenerPanel();
			keyListenerPanel.setKeyCode(keyAction.getKeyCode());
			this.addKeyActionCombo(keyListenerPanel, this.makeKeyActionBox());
		}

		this.keyListenerPanel = new KeyListenerPanel();
		this.keyActionBox = this.makeKeyActionBox();
		this.addKeyActionCombo(this.keyListenerPanel, this.keyActionBox);

		create.addActionListener(this);
		this.setLayout(new GridLayout(gridLayoutRows, 2));

		this.configureTextFields(this);

		this.pack();
		this.setFocusable(true);
		this.setVisible(true);
		this.setSize(Constants.OBJECT_CONFIGURATION_FRAME_WIDTH,
				Constants.OBJECT_CONFIGURATION_FRAME_HEIGHT);

	}

	private void addConfigurationItem(String labelString, JComponent component){
		JPanel containerPanel = new JPanel();
		containerPanel.add(new JLabel(labelString));
		containerPanel.add(component);
		this.gridLayoutRows++;
		this.setLayout(new GridLayout(this.gridLayoutRows, 1));
		this.add(containerPanel);
	}

	private void addKeyActionCombo(JPanel keyPanel, JComponent component){
		JPanel containerPanel = new JPanel();
		containerPanel.add(keyPanel);
		containerPanel.add(component);
		this.gridLayoutRows++;
		this.setLayout(new GridLayout(this.gridLayoutRows, 1));
		this.add(containerPanel);
	}

	private void configureTextFields(Container container) {
		Component[] components = container.getComponents();
		log.debug("configureTextFields");
		for(int i=0; i<components.length; i++){
			if(components[i] instanceof JTextField){
				log.debug("setting textfield size");
				components[i].setPreferredSize(new Dimension(200, 20));
			}
			if(components[i] instanceof Container){
				this.configureTextFields((Container)components[i]);
			}

		}
	}

	@SuppressWarnings("unused")
	private void addStuff() {
		for (JComponent f : this.components) {
			this.add(f);
			if (f instanceof JTextField) {
				((JTextField) f).addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==create){
			log.debug("enterrrrrrr"+e.getSource());
		Event event = (Event) eventSelectionBox.getSelectedItem();
		Action action = (Action) actionSelectionBox.getSelectedItem();
		String againstObjectName = (String) againstObjectSelectionBox.getSelectedItem();
		
		int soundSlection =  soundSelectionBox.getSelectedIndex();
		if(event.equals(Event.COLLISION) )
		{
			IAction actionObj =null;
			if(soundSlection != 0){
			     actionObj =new PlaySoundAction(this.audioList.get(soundSlection-1));			
			}
			else{
				 actionObj =new PlaySoundAction("");
			}
			this.object.addEvent(event, action,actionObj, againstObjectName);
		}
		else
		{
			this.object.addEvent(event, action, againstObjectName);
			if(event.equals(Event.MOVE)) {
				if(action.equals(Action.NORMAL_MOVE)) {
					this.object.setMoveAction(new NormalMoveAction());
				} else if(action.equals(Action.MOVE_AROUND)) {
					this.object.setMoveAction(new MoveAroundAction());
				} else if(action.equals(Action.DESCEND_LEFT_RIGHT)) {
					this.object.setMoveAction(new DescendLeftAndRightAction());
				}
			}
		}	
		updateGameObject();

		GameBoard.getGameBoard().repaint();
		this.repaint();
		}
		
		if(e.getSource()==update){
			updateGameObject();

			GameBoard.getGameBoard().repaint();
			this.repaint();	
		}
		
		
	}

	private void updateGameObject() {
		this.object.setWidth(Integer.parseInt(widthField.getText()));
		this.object.setHeight(Integer.parseInt(heightField.getText()));
		this.object.setVx(Integer.parseInt(xVelocityField.getText()));
		this.object.setVy(Integer.parseInt(this.yVelocityField.getText()));
		this.object.setAx(Integer.parseInt(this.xAccelerationField.getText()));
		this.object.setAy(Integer.parseInt(this.yAccelerationField.getText()));
		this.object.setHeading(Integer.parseInt(this.headingField.getText()));
	}

	private JComboBox makeKeyActionBox() {
		JComboBox keyActionBox = new JComboBox();
		keyActionBox.addItem("None");
		keyActionBox.addItem("Move Up");
		keyActionBox.addItem("Move Down");
		keyActionBox.addItem("Move Right");
		keyActionBox.addItem("Move Left");
		keyActionBox.addItem("Move Forward");
		keyActionBox.addItem("Rotate Clockwise");
		keyActionBox.addItem("Rotate CounterClockwise");
		keyActionBox.addItem("Create Object");
		keyActionBox.addItem("Jump Up");
		keyActionBox.addItem("Jump Left");
		keyActionBox.addItem("Jump Right");
		keyActionBox.addItem("Jump Down");
		return keyActionBox;
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		Object source = event.getItemSelectable();

		if (source == checkStickCollision) {
			if (checkStickCollision.isSelected()) {
				this.object.setActionsMap("collidable", true);
				this.object.setActionsMap("stickable", true);
			}
		}
		if (source == checkBounceCollision) {
			if (checkBounceCollision.isSelected()) {
				this.object.setActionsMap("collidable", true);
				this.object.setActionsMap("wallDeflectableBottom", true);
				this.object.setActionsMap("wallDeflectableTop", true);
				this.object.setActionsMap("wallDeflectableRight", true);
				this.object.setActionsMap("wallDeflectableLeft", true);
			}
		}
		if (source == checkDisappearCollision) {
			if (checkDisappearCollision.isSelected()) {
				this.object.setActionsMap("collidable", true);
				this.object.setActionsMap("disappear", true);
			}
		}

		if (source == checkDefelectableCollision) {
			if (checkDefelectableCollision.isSelected()) {
				this.object.setActionsMap("collidable", true);
				this.object.setActionsMap("deflectable", true);
			}

		}

	}

	private void repopulate() {
		againstObjectSelectionBox.removeAllItems();
		againstObjectSelectionBox.addItem(Constants.NONE);
		againstObjectSelectionBox.addItem(Constants.GAME_WALL);
		againstObjectSelectionBox.addItem(Constants.ANY_OBJECT);
		for(GameObject object : GameController.getInstance().getChildObjects()) {
			if(object!=this.object)
				againstObjectSelectionBox.addItem(object.getName());
		}
		soundSelectionBox.removeAllItems();
		soundSelectionBox.addItem(Constants.NONE);
		
		
		String [] labels = new String[audioList.size()];
		for(int i=0;i<audioList.size();i++)
			soundSelectionBox.addItem(audioList.get(i).substring(audioList.get(i).indexOf("/")+1));
		this.validate();
		this.repaint();
	}

}
