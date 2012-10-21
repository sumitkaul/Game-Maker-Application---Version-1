
package main.view;


import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
import main.utilities.JarReader;

import org.apache.log4j.Logger;
import repos.ImageRepo;


//import net.miginfocom.swing.MigLayout;

public class ImageFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollBar;
	private JPanel panel;
	private JTextField type,name;
	//private JComboBox layerBox;
	//private String layer;
	private String file;
	private Logger log = Logger.getLogger(ImageFrame.class);
	private static GameController compositeClass;
	private List<String> list;
	
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public ImageFrame(){
		
		panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		scrollBar = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
//		type= new JTextField("",10);
//		type.setFocusable(true);
		name= new JTextField("",10);
		name.setFocusable(true);
		log.debug("entryNameeeeeeeeeeeeeeeeeeeee");
		panel.setFocusable(true);
		GameBoard.getGameBoard().setFocusable(false);
		
		list = new ArrayList<String>();

		ZipInputStream zip;
		try {
			zip = new ZipInputStream((getClass().getClassLoader().getResourceAsStream("images.jar")));
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
		
		//list = ImageRepo.getInstance().getImageNameList();

	    JPanel namePanel = new JPanel(new GridLayout(5,1));
	    namePanel.add(new JLabel("Object Name: "));
	    namePanel.add(name,"wrap");
	    //namePanel.add(new JLabel("Object Type: "));
	   // namePanel.add(type,"wrap");
	    panel.add(namePanel);
	    JPanel imagePanel = new JPanel(new GridLayout(5,5));
	    //panel.add(new JLabel("Layer: "));
	    //layerBox = new JComboBox(Layers.getInstance().getLayers().toArray()); 
	    //panel.add(layerBox,"wrap");
	   // File currentDirectory = null;
	    for (int i = 0; i < list.size(); i++) {
	    	final String imagePath = (list.get(i));
	   	 	log.info("The image is " + imagePath);
	   	 final Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(imagePath));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(50, 50, 1));
			final JLabel iconimage = new JLabel(icon);
			iconimage.setToolTipText(list.get(i));
			iconimage.addMouseListener(new MouseAdapter() {
			      public void mouseClicked(MouseEvent e) {
			    	  if(!name.getText().equalsIgnoreCase("")){
			  			String filename = iconimage.getToolTipText();
			  			setSelectedFile(filename);
			  			log.debug("the selected filename is filepath");
						
			  			setVisible(false);
			  			
						Drawable item = new Drawable(Constants.GAMEOBJECT_INTIAL_XVALUE,Constants.GAMEOBJECT_INTIAL_YVALUE, imagePath);
						compositeClass = GameController.getInstance();
						item.setName(name.getText());
						//item.setType(type.getText());
						
						
						compositeClass.add(item);
						ListPanel.getInstance().addAddedSpriteElements(item.getName());
						
						ListPanel.getInstance().getGameboard().draw();
						ObjectConfigurationFrame objFrame = new ObjectConfigurationFrame(item);
						objFrame.setEnabled(true);
						
						return;
						

			    	  }
			  		else{
			  			JOptionPane.showMessageDialog(scrollBar, "Please select an image and enter the Object type/name");
			  		}
			  		
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

	public void setSelectedFile(String file) {
		this.file = file;
	}
	
	public String getSelectedFile()
	{
		return this.file;
	}
}

