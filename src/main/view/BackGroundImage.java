package main.view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.utilities.ImageConverter;

import org.apache.log4j.Logger;

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
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(backGroundFile));
		
		myPicture = ImageConverter.toBufferedImage(image);
		GameBoard.getGameBoard().setBackgroundImage(myPicture);
	}

	private List<String> list;
	

	public BackGroundImage()
	{
		panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		scrollBar = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
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
			    	GameBoard gameboard = GameBoard.getGameBoard();
					gameboard.setBackgroundImagePath(imagePath);
					Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(imagePath));
					
					myPicture = ImageConverter.toBufferedImage(image);
					gameboard.setBackgroundImage(myPicture);
					log.info("the selected filename is filepath");
					gameboard.repaint();
					setVisible(false);
						
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

	}

	public JLabel getPicLabel() {
		return picLabel;
	}

	public void setPicLabel(JLabel picLabel) {
		this.picLabel = picLabel;
	}




}