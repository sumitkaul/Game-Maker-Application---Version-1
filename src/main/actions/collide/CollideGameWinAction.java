package main.actions.collide;

import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import main.controller.Controls;
import main.controller.GameController;
import main.interfaces.IAction;
import main.model.Constants;
import main.model.Drawable;
import main.model.GameObject;

public class CollideGameWinAction implements IAction {
	
	@XStreamOmitField
	private transient static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
	.getLogger(CollideGameWinAction.class);
	
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
			if(getAgainstObjectName().equals(Constants.ANY_OBJECT)){
				if(gameObject.intersects(obj.getObjectBounds())){
					if(!xReversed.intersects(obj.getObjectBounds())){
						JOptionPane.showMessageDialog(null, "You Win");
						Controls.getInstance().getDaemon().stop();
					}
					if(!yReversed.intersects(obj.getObjectBounds())){
						JOptionPane.showMessageDialog(null, "You Win");
						Controls.getInstance().getDaemon().stop();
					}
					else if(yReversed.intersects(obj.getObjectBounds())&&xReversed.intersects(obj.getObjectBounds())){
						JOptionPane.showMessageDialog(null, "You Win");
						Controls.getInstance().getDaemon().stop();
					}
					
				}
			} else if(obj.getName().equals(getAgainstObjectName())) {
				LOG.debug("Executing collide win action for " + gameObject.getName());
				if(gameObject.intersects(obj.getObjectBounds())){
					if(!xReversed.intersects(obj.getObjectBounds())){
						JOptionPane.showMessageDialog(null, "You Win");
						Controls.getInstance().getDaemon().stop();
					}
					if(!yReversed.intersects(obj.getObjectBounds())){
						JOptionPane.showMessageDialog(null, "You Win");
						Controls.getInstance().getDaemon().stop();
					}
					else if(yReversed.intersects(obj.getObjectBounds())&&xReversed.intersects(obj.getObjectBounds())){
						JOptionPane.showMessageDialog(null, "You Win");
						Controls.getInstance().getDaemon().stop();
					}
					
				}
			}
		}
		if(getAgainstObjectName().equals(Constants.GAME_WALL)) {
			LOG.debug("Executing collide win action for " + gameObject.getName());
			if(gameObject.getX()<=Constants.BOARD_OFFSET) {
				JOptionPane.showMessageDialog(null, "You Win");
				Controls.getInstance().getDaemon().stop();
			} else if(gameObject.getX()+gameObject.getWidth() >= Constants.BOARD_WIDTH){
				JOptionPane.showMessageDialog(null, "You Win");
				Controls.getInstance().getDaemon().stop();
			} else if(gameObject.getY() <= Constants.BOARD_OFFSET){
				JOptionPane.showMessageDialog(null, "You Win");
				Controls.getInstance().getDaemon().stop();
			} else if(gameObject.getY()+gameObject.getHeight() >= Constants.BOARD_HEIGHT){
				JOptionPane.showMessageDialog(null, "You Win");
				Controls.getInstance().getDaemon().stop();
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
