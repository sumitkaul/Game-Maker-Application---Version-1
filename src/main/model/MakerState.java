package main.model;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.ImageIcon;
import main.controller.GameController;
import main.utilities.ImageConverter;
import main.view.GameBoard;
import org.apache.log4j.Logger;

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
                gameState = stateSave.readGame(gameNameOrData);
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
                        
                        ImageIcon imgChild = new ImageIcon(this.getClass().getResource(child.getImageFilePath()));
                        BufferedImage bImageChild = ImageConverter.toBufferedImage(imgChild.getImage());
                        child.setImg(bImageChild);
                    }
                }
                ImageIcon img = new ImageIcon(this.getClass().getResource(item.getImageFilePath()));
                
                ditem.setKeyActions(item.getKeyActions());
                ditem.setPressedKey(item.getPressedKey());
                //ditem.setActionsMap(item.getActions);
                BufferedImage bImage = ImageConverter.toBufferedImage(img.getImage());
                //bImage.set
                ditem.setImg(bImage);
            }
        } catch (Exception e) {
            log.error("An exception! Oops!", e);
        }
    }
}
