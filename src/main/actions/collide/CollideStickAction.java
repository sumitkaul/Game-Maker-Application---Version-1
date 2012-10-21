package main.actions.collide;

import java.awt.geom.Rectangle2D;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import main.controller.GameController;
import main.interfaces.IAction;
import main.model.Constants;
import main.model.Drawable;
import main.model.GameObject;

public class CollideStickAction implements IAction {
	
	@XStreamOmitField
	private transient static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
	.getLogger(CollideStickAction.class);
	
	private String againstObjectName;
	private IAction soundAction;
	@Override
	public void act(Drawable gameObject) {
		int xSpeed = gameObject.getVx();
		int ySpeed = gameObject.getVy();

		Rectangle2D.Double xReversed = (Rectangle2D.Double) gameObject.getObjectBounds();
		Rectangle2D.Double yReversed = (Rectangle2D.Double) gameObject.getObjectBounds();		
		xReversed.x -= xSpeed;
		yReversed.y -= ySpeed;
		List<GameObject> gameObjects = GameController.getInstance().getChildObjects();
		for(GameObject obj : gameObjects){
			if(obj.equals(gameObject)) continue;
			if(getAgainstObjectName().equals(Constants.ANY_OBJECT)){
				if(gameObject.intersects(obj.getObjectBounds())){
					gameObject.setVx(obj.getVx());
					gameObject.setX(gameObject.getX() + gameObject.getVx());
					gameObject.setVy(obj.getVy());
					gameObject.setY(gameObject.getY() + gameObject.getVy());
					soundAction.act(gameObject);
				}
			} else if(obj.getName().equals(getAgainstObjectName())) {
				LOG.debug("Executing collide stick action for " + gameObject.getName());
				if(gameObject.intersects(obj.getObjectBounds())){
						gameObject.setVx(obj.getVx());
						gameObject.setX(gameObject.getX() + gameObject.getVx());
						gameObject.setVy(obj.getVy());
						gameObject.setY(gameObject.getY() + gameObject.getVy());
						soundAction.act(gameObject);
				}
			}
		}
	}
	public String getAgainstObjectName() {
		return againstObjectName;
	}
	public void setAgainstObjectName(String againstObjectName) {
		this.againstObjectName = againstObjectName;
	}
	public IAction getSoundAction() {
		return soundAction;
	}
	public void setSoundAction(IAction soundAction) {
		this.soundAction = soundAction;
	}

}
