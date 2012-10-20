package main.view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import main.controller.GameController;
import main.model.Constants;
import main.model.Drawable;
import main.utilities.ImageConverter;
import main.utilities.JarReader;

import org.apache.log4j.Logger;

import repos.BackGroundRepo;
import repos.ImageRepo;

public class BackGroundImage extends JFrame {
	private Logger log = Logger.getLogger(BackGroundImage.class);
	private BufferedImage myPicture;
	private JLabel picLabel;
	private JPanel panel;
	private JScrollPane scrollBar;
	private String backGroundFile;
	public String getBackGroundFile() {
		return backGroundFile;
	}

	public void setBackGroundFile(String backGroundFile) {
		this.backGroundFile = backGroundFile;
	}

	private List<String> list;
	

	public BackGroundImage()
	{
		panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		scrollBar = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		/// Reading from utility
//		JarReader reader = new JarReader();
	//	reader.setZipStream("background.jar");
		//list = reader.getAllEntries();
		//list = BackGroundRepo.getInstance().getImageNameList();
		list = new ArrayList<String>();

		ZipInputStream zip;
		try {
			zip = new ZipInputStream((getClass().getClassLoader().getResourceAsStream("background.jar")));
			ZipEntry ze = null;

			 while((ze = zip.getNextEntry()) != null) {
			        String entryName = ze.getName();
			        log.debug("entryName="+entryName);
			        if(entryName.endsWith(".png") || entryName.endsWith(".jpg") || entryName.endsWith(".gif") ) {
			            list.add(entryName);
			        }
		    }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

	    JPanel imagePanel = new JPanel(new GridLayout(5,5));
	    for (int i = 0; i < list.size(); i++) {
	    	final String imagePath = (list.get(i));
	   	 	log.info("The image is " + imagePath);
	   	 final Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(imagePath));
	   	 	
	   	 	//final Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(imagePath));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(50, 50, 1));
			final JLabel iconimage = new JLabel(icon);
			iconimage.setToolTipText(list.get(i));
			iconimage.addMouseListener(new MouseAdapter() {
			      public void mouseClicked(MouseEvent e) {
			    	  String filename = iconimage.getToolTipText();
					setBackGroundFile(imagePath);
					setBackGroundImage(imagePath);
					log.info("the selected filename is filepath");
					
					setVisible(false);
					
					//Drawable item = new Drawable(Constants.GAMEOBJECT_INTIAL_XVALUE,Constants.GAMEOBJECT_INTIAL_YVALUE, imagePath);
//						compositeClass = GameController.getInstance();
//						item.setName(name.getText());
//						item.setType(type.getText());
//						
					
//					GameController.getInstance().add(item);
//					ListPanel.getInstance().addAddedSpriteElements(item.getName());
//					
//					ListPanel.getInstance().getGameboard().draw();
//					ObjectConfigurationFrame objFrame = new ObjectConfigurationFrame(item);
//					objFrame.setEnabled(true);
//					
					return;
			  		
			        }
			      });
			if ((i+1)%3==0){
				imagePanel.add(iconimage,"wrap");
				}else{
					imagePanel.add(iconimage);
				}
	   	    setSize(400, 600);
	    add(scrollBar);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setTitle("Choose a image");
	    setVisible(false);
	    }
	    panel.add(imagePanel);
	    this.add(panel);
	    //panel.setVisible(true);
	    
	   /* layerBox.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  layer = ((String) ((JComboBox) e.getSource()).getSelectedItem());
		    	  if(layer.equalsIgnoreCase(Constants.NEW_LAYER)) {
		    		  layer = Layers.getInstance().addNewLayer();
		    	  }
		      }
		 });
	    */	    

	}

	

	public void setBackGroundImage(String fileName)
	{

//		JarReader reader = new JarReader();
//		reader.setZipStream("background.jar");
//		log.info("the background is " + fileName);
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(fileName));
		
		//Image image =  new ImageIcon(this.getClass().getResource(fileName)).getImage();
		ImageConverter converter = new ImageConverter();
		myPicture = converter.toBufferedImage(image);
		//myPicture =ImageIO.read(new File(getClass().getClassLoader().getResource(reader.getFileEntry(fileName)).toString()));
		GameBoard gameboard = GameBoard.getGameBoard();
		if(picLabel!=null)
			gameboard.remove(picLabel);
		
		picLabel = new JLabel(new ImageIcon( myPicture ));
		//return picLabel;
		//return null;
		setPicLabel(picLabel);
		
		//picLabel = backGroundImage.getPicLabel();
		
		gameboard.add(picLabel);
		gameboard.invalidate();
		gameboard.validate();
		gameboard.repaint();


	}

	public JLabel getPicLabel() {
		return picLabel;
	}

	public void setPicLabel(JLabel picLabel) {
		this.picLabel = picLabel;
	}




}