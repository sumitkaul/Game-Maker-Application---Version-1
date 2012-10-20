package main.actions;

import java.awt.geom.Rectangle2D;
import java.util.List;

import main.actions.collide.BounceRandomAction;
import main.controller.GameController;
import main.model.Constants;
import main.model.Drawable;
import main.model.GameObject;
import main.view.GameBoard;

public class ScoreAction {

	int score;
	private String againstObjectName;
	
	
	private transient static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(BounceRandomAction.class);
		
	

	public void act(Drawable gameObject) {
		
		LOG.info("Initial score is:"+GameBoard.getGameBoard().getScore());
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
					
					GameBoard.getGameBoard().setScore(GameBoard.getGameBoard().getScore() + Constants.SCORE_INCR);
					LOG.info("score after collision"+GameBoard.getGameBoard().getScore());
					
				}
			// Collide with particular object
			} else if(obj.getName().equals(getAgainstObjectName())) {
				LOG.debug("Executing score action for:" + gameObject.getName());
				LOG.info("Against object:" + getAgainstObjectName());
//				LOG.info("Object bounds"+obj.getObjectBounds());
//				LOG.info("game object bounds"+gameObject.getObjectBounds());
				if(gameObject.intersects(obj.getObjectBounds())){
					//LOG.info("score before collision"+GameBoard.getGameBoard().getScore());
					GameBoard.getGameBoard().setScore(GameBoard.getGameBoard().getScore()+ Constants.SCORE_INCR);
					//LOG.info("score after collision"+GameBoard.getGameBoard().getScore());
					
				}
			} 
		}
	}
	
	public String getAgainstObjectName() {
		return againstObjectName;
	}
	public void setAgainstObjectName(String againstObjectName) {
		this.againstObjectName = againstObjectName;
		LOG.info("against object set to " + againstObjectName);
	}
	
	

}
