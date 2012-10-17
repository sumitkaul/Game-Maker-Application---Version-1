package main.actions.collide;

import java.awt.geom.Rectangle2D;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import main.controller.GameController;
import main.interfaces.IAction;
import main.model.Constants;
import main.model.Drawable;
import main.model.GameObject;

public class CollideBounceAction implements IAction {
	
	@XStreamOmitField
	private transient static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
	.getLogger(CollideBounceAction.class);
	
	private String againstObjectName;
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
			// Collide with any game object
			if(getAgainstObjectName().equals(Constants.ANY_OBJECT)){
				if(gameObject.intersects(obj.getObjectBounds())){
					if(!xReversed.intersects(obj.getObjectBounds())){
						gameObject.setVx(xSpeed*-1);
					}
					if(!yReversed.intersects(obj.getObjectBounds())){
						gameObject.setVy(ySpeed*-1);
					}
					else if(yReversed.intersects(obj.getObjectBounds())&&xReversed.intersects(obj.getObjectBounds())){
						gameObject.setVy(ySpeed*-1);
						gameObject.setVx(xSpeed*-1);
					}
				}
			// Collide with particular object
			} else if(obj.getName().equals(getAgainstObjectName())) {
				LOG.debug("Executing collide bounce action for " + gameObject.getName());
				if(gameObject.intersects(obj.getObjectBounds())){
					if(!xReversed.intersects(obj.getObjectBounds())){
						gameObject.setVx(xSpeed*-1);
					}
					if(!yReversed.intersects(obj.getObjectBounds())){
						gameObject.setVy(ySpeed*-1);
					}
					else if(yReversed.intersects(obj.getObjectBounds())&&xReversed.intersects(obj.getObjectBounds())){
						gameObject.setVy(ySpeed*-1);
						gameObject.setVx(xSpeed*-1);
					}
				}
			} 
		}
		if(getAgainstObjectName().equals(Constants.GAME_WALL)) {
			LOG.debug("Executing collide bounce action for " + gameObject.getName());
			if(gameObject.getX()<=Constants.BOARD_OFFSET) {
				gameObject.setVx(-1 * gameObject.getVx());
				//PlaySound sound = new PlaySound();
				//sound.act(null);
			} else if(gameObject.getX()+gameObject.getWidth() >= Constants.BOARD_WIDTH){
				gameObject.setVx(-1 * gameObject.getVx());
				//PlaySound sound = new PlaySound();
				//sound.act(null);
			} else if(gameObject.getY() <= Constants.BOARD_OFFSET){
				gameObject.setVy(-1 * gameObject.getVy());
				//PlaySound sound = new PlaySound();
				//sound.act(null);
			} else if(gameObject.getY()+gameObject.getHeight() >= Constants.BOARD_HEIGHT){
				gameObject.setVy(-1 * gameObject.getVy());
				//PlaySound sound = new PlaySound();
				//sound.act(null);
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