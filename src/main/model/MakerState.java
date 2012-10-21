package main.model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import main.controller.GameController;
import main.utilities.ImageConverter;
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
    
    public MakerState(Dimension windowSize, GameController compositeClass) {
        this.windowSize = windowSize;
        this.compositeClass = compositeClass;
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
            
            for (GameObject oj : compositeClass.getChildObjects()) {
                GameBoard.getGameBoard().removeKeyListener((Drawable) oj);
            }
            GameBoard.getGameBoard().removeAll();
            compositeClass.removeAll();
            
            compositeClass.add(gameState.getCompositeClass().getChildObjects());
            for (GameObject item : compositeClass.getChildObjects()) {
                Drawable ditem = (Drawable) item;
                log.debug("obj id: " + ditem.getName());
                log.debug("get image: " + item.getImageFilePath());
                ditem.setWidth(item.getWidth());
                ditem.setHeight(item.getHeight());
                ditem.setX(item.getX());
                ditem.setY(item.getY());
                
                GameBoard.getGameBoard().addKeyListener(ditem);
                List<Drawable> children = ditem.getChildrenList();
                if (!children.isEmpty()) {
                    for (Drawable child : children) {
                        if (child.getImageFilePath() == null) {
                            log.debug("continue for " + child.getName());
                            continue;
                        }
                        child.setImg(ImageConverter.toBufferedImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(child.getImageFilePath()))));
                    }
                }
                
                ditem.setKeyActions(item.getKeyActions());
                ditem.setPressedKey(item.getPressedKey());
                //ditem.setActionsMap(item.getActions);
                ditem.setImg(ImageConverter.toBufferedImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(item.getImageFilePath()))));
            }
        } catch (Exception e) {
            log.error("An exception! Oops!", e);
        }
    }
}
