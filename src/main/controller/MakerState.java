package main.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import main.model.Drawable;
import main.model.GameObject;
import main.model.StateSaver;
import main.utilities.ImageConverter;
import main.view.BackGroundImage;
import main.view.GameBoard;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class MakerState {
    
    @XStreamOmitField
    private transient Logger log = Logger.getLogger(MakerState.class);
    @XStreamOmitField
    private transient StateSaver stateSave;
    private Dimension windowSize;
    private GameController compositeClass;
	private String backgroundImagePath; 
	
    public MakerState(Dimension windowSize, GameController compositeClass, String backgroundImagePath) {
        this.windowSize = windowSize;
        this.compositeClass = compositeClass;
        this.backgroundImagePath = backgroundImagePath;
		stateSave = new StateSaver();        
    }
    
    public Dimension getWindowSize() {
        return windowSize;
    }
    
    public void setWindowSize(Dimension windowSize) {
        this.windowSize = windowSize;
    }
    
    public GameController getCompositeClass() {
        return compositeClass;
    }
    
    public void setCompositeClass(GameController compositeClass) {
        this.compositeClass = compositeClass;
    }
    
	public String getBackgroundImagePath() {
		return backgroundImagePath;
	}

	public void setBackgroundImagePath(String backgroundImagePath) {
		this.backgroundImagePath = backgroundImagePath;
	}

    public void save() {
        try {
            stateSave.write(this);
        } catch (Exception e) {
            log.error("An exception! Oops!", e);
        }
    }
    
    public String getSaveString() {
        return stateSave.writeGameToString(this);
    }
    
    public void load(String gameNameOrData, boolean remoteLoad) {
        
        try {
            MakerState gameState = null;
            if (remoteLoad) {
                gameState = stateSave.readGameFromData(gameNameOrData);
            } else {
            	XStream reader = new XStream(new StaxDriver());
                gameState = (MakerState) reader.fromXML(gameNameOrData);
            }
            
            windowSize.setSize(gameState.getWindowSize().height, gameState.getWindowSize().width);
            
            compositeClass.removeAll();
            GameBoard.getGameBoard().setBackgroundImage(null);
			BackGroundImage img = new BackGroundImage();
			if(gameState.getBackgroundImagePath()!=null && !gameState.getBackgroundImagePath().isEmpty())
				img.setBackGroundFile(gameState.getBackgroundImagePath());
            compositeClass.add(gameState.getCompositeClass().getChildObjects());
            for (GameObject item : compositeClass.getChildObjects()) {
				Drawable dItem = (Drawable)item;
				dItem.setImg(ImageConverter.toBufferedImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(item.getImageFilePath()))));
				if(dItem.getChildrenList()!=null) {
					for(Drawable childItem : dItem.getChildrenList()) {
						childItem.setImg(ImageConverter.toBufferedImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(childItem.getImageFilePath()))));
					}					
				}
			}
        } catch (Exception e) {
            log.error("An exception! Oops!", e);
        }
    }
}
