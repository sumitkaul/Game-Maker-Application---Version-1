package main.actions.collide;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import main.controller.GameController;
import main.interfaces.IAction;
import main.model.Constants;
import main.model.Drawable;
import main.model.GameObject;

public class CollideRemoveAction implements IAction {
	
	@XStreamOmitField
	private transient static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
	.getLogger(CollideRemoveAction.class);
	
	private String againstObjectName;
	@Override
	public void act(Drawable gameObject) {
		int xSpeed = gameObject.getVx();
		int ySpeed = gameObject.getVy();

		Rectangle2D.Double xReversed = (Rectangle2D.Double) gameObject.getObjectBounds();
		Rectangle2D.Double yReversed = (Rectangle2D.Double) gameObject.getObjectBounds();		
		xReversed.x -= xSpeed;
		yReversed.y -= ySpeed;
		List<GameObject> originalGameObjects = GameController.getInstance().getChildObjects();
		List<GameObject> objectsList = new ArrayList<GameObject>();
		objectsList.addAll(originalGameObjects);
		for(GameObject obj : objectsList){
			if(obj.equals(gameObject)) continue;
			if(getAgainstObjectName().equals(Constants.ANY_OBJECT)){
				if(gameObject.intersects(obj.getObjectBounds())){
					if(!xReversed.intersects(obj.getObjectBounds())){
						originalGameObjects.remove(gameObject);
					}
					if(!yReversed.intersects(obj.getObjectBounds())){
						originalGameObjects.remove(gameObject);
					}
					else if(yReversed.intersects(obj.getObjectBounds())&&xReversed.intersects(obj.getObjectBounds())){
						originalGameObjects.remove(gameObject);
					}
					
				}
			} else if(obj.getName().equals(getAgainstObjectName())) {
				LOG.debug("Executing collide remove action for " + gameObject.getName());
				if(gameObject.intersects(obj.getObjectBounds())){
					if(!xReversed.intersects(obj.getObjectBounds())){
						originalGameObjects.remove(gameObject);
					}
					if(!yReversed.intersects(obj.getObjectBounds())){
						originalGameObjects.remove(gameObject);
					}
					else if(yReversed.intersects(obj.getObjectBounds())&&xReversed.intersects(obj.getObjectBounds())){
						originalGameObjects.remove(gameObject);
					}
					
				}
			}
		}
		if(getAgainstObjectName().equals(Constants.GAME_WALL)) {
			LOG.debug("Executing collide remove for " + gameObject.getName());
			if(gameObject.getX()<=Constants.BOARD_OFFSET) {
				originalGameObjects.remove(gameObject);
			} else if(gameObject.getX()+gameObject.getWidth() >= Constants.BOARD_WIDTH){
				originalGameObjects.remove(gameObject);
			} else if(gameObject.getY() <= Constants.BOARD_OFFSET){
				originalGameObjects.remove(gameObject);
			} else if(gameObject.getY()+gameObject.getHeight() >= Constants.BOARD_HEIGHT){
				originalGameObjects.remove(gameObject);
			}
		}
	}
	public String getAgainstObjectName() {
		return againstObjectName;
	}
	public void setAgainstObjectName(String againstObjectName) {
		this.againstObjectName = againstObjectName;
	}

}
