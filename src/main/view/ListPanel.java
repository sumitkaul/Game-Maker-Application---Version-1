package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileView;

import main.controller.GameController;
import main.controller.LayoutFlow;
import main.controller.LayoutGrid;
import main.model.Constants;
import main.model.GameObject;
import main.model.ListItem;
import main.utilities.Action;
import main.utilities.ActionObjectPair;
import main.utilities.Event;
import main.utilities.ScrollListModel;

import org.apache.log4j.Logger;

public class ListPanel {

	public static ListPanel listPanel; 
	public static ListPanel getInstance() {
		if(listPanel==null)
			listPanel = new ListPanel();
		return listPanel;
	}

	private JPanel panel;
	private JList addedSpriteList;
	private ScrollListModel addedListModel;
	private JList combinedList;
	private ScrollListModel combinedListModel;
	private JButton addButton;
	private JButton removeButton;
	private JCheckBox clockCheckBox;
	private JButton removeCombination;


	private JButton setBackGroundImages;
	private BackGroundImage backGroundImage;
	private JLabel picLabel;


	private JButton setBackGroundColor;

	private JFileChooser fileChooser;

	private LayoutGrid lg = new LayoutGrid(panel);
	private LayoutFlow lf = new LayoutFlow(panel);

	private Logger log = Logger.getLogger(ListPanel.class);
	private GameController compositeClass;
	private GameBoard gameboard;
	private ImageFrame imageFrame;
	
	public ListPanel() {
		
		gameboard = GameBoard.getGameBoard();

		panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));

		addedListModel = new ScrollListModel();
		addedSpriteList = new JList(addedListModel);

		combinedListModel = new ScrollListModel();
		combinedList = new JList(combinedListModel);

		addButton = new JButton("Add New Sprite");
		addButton.setToolTipText("Add a Sprite of Your Choice");
		removeButton = new JButton("Remove Sprite");
		removeButton.setToolTipText("Remove the Sprite of Your Choice");
		clockCheckBox = new JCheckBox("Enable Game Clock");
		removeCombination = new JButton("Remove Combination");
		removeCombination.setToolTipText("Remove the Combination of Event and Sprite ");
		setBackGroundColor= new JButton("Change backGround Color");
		setBackGroundColor.setToolTipText("Set BackGround Color");
		setBackGroundImages= new JButton("Change BackGround Image");
		setBackGroundImages.setToolTipText("Change the BackGround Image");
		addButton.addActionListener(new AddListener());
		removeButton.addActionListener(new RemoveListener());
		removeCombination.addActionListener(new CombinationRemoveListener());
		setBackGroundColor.addActionListener( new SetBackGroundColorListener());
		setBackGroundImages.addActionListener( new SetBackGroundImageListener());
		clockCheckBox.addChangeListener(new GameClockListener()) ;
		fileChooser = new JFileChooser();
		ThumbNailView view = new ThumbNailView();
		fileChooser.setFileView(view);

		JPanel addedSpritePanel = new JPanel(new BorderLayout());
		addedSpritePanel.add(new JLabel("Added Sprites"), BorderLayout.NORTH);
		addedSpritePanel.add(new JScrollPane(addedSpriteList), BorderLayout.CENTER);

		JPanel spriteButtonPanel = new JPanel(new FlowLayout());

		spriteButtonPanel.add(addButton);
		spriteButtonPanel.add(removeButton);
		spriteButtonPanel.add(clockCheckBox);
		imageFrame = new ImageFrame();
		backGroundImage=new BackGroundImage();
		imageFrame.setVisible(false);
		backGroundImage.setVisible(false);

		JPanel combinedPanel = new JPanel(new BorderLayout());
		combinedPanel.add(new JLabel("Event + Action"), BorderLayout.NORTH);
		combinedPanel.add(new JScrollPane(combinedList), BorderLayout.CENTER);

		JPanel combinationButtonPanel = new JPanel(new FlowLayout());
		//combinationButtonPanel.add(addCombination);
		combinationButtonPanel.add(removeCombination);
		combinationButtonPanel.add(setBackGroundColor);
		combinationButtonPanel.add(setBackGroundImages);
//		addEventElements(new String[] {"Key Presses", "Collision","Move","Create Object"});
//		addActionElements(new String[] {});
//		//eventList.addListSelectionListener( new EventSelectionListener());



		panel.add(addedSpritePanel);
		panel.add(spriteButtonPanel);
		//panel.add(eventActionPanel);
		panel.add(combinedPanel);
		panel.add(combinationButtonPanel);
		

		compositeClass = GameController.getInstance();
	}

	public GameBoard getGameboard() {
		return gameboard;
	}

	public void setGameboard(GameBoard gameboard) {
		this.gameboard = gameboard;
	}

	public void switchPanel(JPanel panel, GridLayout gl){
		lg.execute(panel,gl);
	}

	public void switchPanel(JPanel panel, FlowLayout fl){
		lf.execute(panel,fl);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}


	public void addAddedSpriteElements(String newValue) {
		addedListModel.add(newValue);
	}

	public void addAddedSpriteElements(Object newValue[]) {
		fillListModel(addedListModel, newValue);
	}

	public void addCombinedElements(Event event,ActionObjectPair action) {
		StringBuffer sb = new StringBuffer();
		//sb.append(sprite.getName());
		sb.append(event.getEvent());
		sb.append(" + "+action.getAction());
		if(action.getGameObjectName()!=null 
			&& !action.getGameObjectName().isEmpty() 
			&& !action.getGameObjectName().equals(Constants.NONE)) {
			sb.append(" + "+action.getGameObjectName());
		}
		combinedListModel.add(sb.toString());
	}

	public ScrollListModel getCombinedListModel() {
		return combinedListModel;
	}

	public void setCombinedListModel(ScrollListModel combinedListModel) {
		this.combinedListModel = combinedListModel;
	}

	private void fillListModel(ScrollListModel model, Object newValues[]) {
		model.addAll(newValues);
	}


	private void clearAddedSpriteSelected() {
		Object[] selected = addedSpriteList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i) {
			addedListModel.removeElement(selected[i]);
		}
		addedSpriteList.getSelectionModel().clearSelection();
	}

	public void clearCombinationSelected() {
		Object[] selected = combinedList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i) {
			combinedListModel.removeElement(selected[i]);
		}
		combinedList.getSelectionModel().clearSelection();
	}

	private class AddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			listPanel.getClass();
			imageFrame.setVisible(true);
			//String name =  imageFrame.getSelectedFile();
			
			
		getGameboard().draw();
		//getGameboard().requestFocus();
			gameboard.draw();
			//gameboard.requestFocus();
			panel.setFocusable(true);
			getGameboard().setFocusable(false);
		}
	}

	private class RemoveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object[] selected = addedSpriteList.getSelectedValues();
			clearAddedSpriteSelected();
			for (int i = 0;i<selected.length ; i++) {
				String item = (String) selected[i];
				List<GameObject> list = new ArrayList<GameObject>();
				list.addAll(compositeClass.getChildObjects());
				for(GameObject obj : list) {
					if(obj.getName().equals(item)) {
						compositeClass.remove(obj);
						break;
					}
				}
			}
			gameboard.draw();
			gameboard.requestFocus();
		}
	}

	private class GameClockListener implements ChangeListener {


		@Override
		public void stateChanged(ChangeEvent e) {

			if(clockCheckBox.isSelected()){
				ClockDisplay clock = ClockDisplay.getInstance();
				clock.setEnabled(true);

				log.info("checkbox selected");
			}
			else {
				ClockDisplay.getInstance().setEnabled(false);
			}
		}
	}

	private class CombinationRemoveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object[] selected = combinedList.getSelectedValues();
			GameObject selectedObject = GameController.getInstance().getSelectedObject();
			if(selectedObject!=null && selected!=null && selected.length>0) {
			for(int i=0;i<selected.length;i++) {
				String item = (String) selected[i];
				String[] combination = item.split("\\+");
				Event event = Event.get(combination[0].trim());
				Action action = Action.get(combination[1].trim());
				String againstObjectName = null;
				if(combination.length>2) {
					againstObjectName = combination[2].trim();
				}
				selectedObject.removeEvent(event, action, againstObjectName);
			}
			selectedObject.getName();
			clearCombinationSelected();
			}
		}
	}

	private class SetBackGroundColorListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Color backGroundColor= JColorChooser.showDialog(gameboard.getParent(), "Choose BackGround Color", gameboard.getBackground());
			if(backGroundColor!=null){
				gameboard.setBackground(backGroundColor);
			}		
		}
	}

	private class SetBackGroundImageListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(setBackGroundImages))
			{
				backGroundImage.setVisible(true);
				
							}

		}
	}

	class ThumbNailView extends FileView{
		public Icon getIcon(File f){
			Icon icon=null;
			if(isImageFile(f.getPath())){
				icon=createImageIcon(f.getPath(),null);
			}
			return icon;
		}
		private ImageIcon createImageIcon(String path,String description) {
			if (path != null) {
				ImageIcon icon=new ImageIcon(path);
				Image img = icon.getImage() ; 
				Image newimg = img.getScaledInstance( 16, 16,  java.awt.Image.SCALE_SMOOTH ) ;
				return new ImageIcon(newimg);
			} else {
				System.err.println("Couldn't find file: " + path);
				return null;
			}
		}

		private boolean isImageFile(String filename){
			return true;//if this is image
		}
	}
}
