package main.actions.collide;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Random;

import main.controller.GameController;
import main.interfaces.IAction;
import main.model.Constants;
import main.model.Drawable;
import main.model.GameObject;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class BounceRandomAction implements IAction {
	
	private int min = -1;
	private int max = 1;
	
	@XStreamOmitField
	private transient static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(BounceRandomAction.class);
			
			private String againstObjectName;
			@Override
			public void act(Drawable gameObject) {
				int xSpeed = gameObject.getVx();
				int ySpeed = gameObject.getVy();
				int factor;

				Rectangle2D.Double xReversed = (Rectangle2D.Double) gameObject.getObjectBounds();
				Rectangle2D.Double yReversed = (Rectangle2D.Double) gameObject.getObjectBounds();		
				xReversed.x -= xSpeed;
				yReversed.y -= ySpeed;
				List<GameObject> gameObjects = GameController.getInstance().getChildObjects();
				for(GameObject obj : gameObjects){
					if(obj.equals(gameObject)) continue;
					if(obj.getName().equals(getAgainstObjectName())) {
						if(gameObject.intersects(obj.getObjectBounds())){
							if(!xReversed.intersects(obj.getObjectBounds())){
								while ((factor = chooseRandomDirection(this.min,this.max)) != 0)
								{
									gameObject.setX(gameObject.getX()+ (-5 * gameObject.getVx()));
									factor = chooseRandomDirection(this.min,this.max);
									LOG.info("The factor is " + factor);
								
									swapXtoY(gameObject);
									gameObject.setVy(factor * gameObject.getVy());
								}
							}
							else if(!yReversed.intersects(obj.getObjectBounds())){
								while ((factor = chooseRandomDirection(this.min,this.max)) != 0)
								{
									gameObject.setY(gameObject.getY()+ (-5 *gameObject.getVy()));
									factor = chooseRandomDirection(this.min,this.max);
									LOG.info("The factor is " + factor);
									
									swapYtoX(gameObject);
									gameObject.setVx(factor * gameObject.getVx());
								}
							}
							
						}
					}
				}
				if(getAgainstObjectName().equals(Constants.GAME_WALL)) {
					if(gameObject.getX()<=Constants.BOARD_OFFSET) {
						gameObject.setX(gameObject.getX()+ (-5 * gameObject.getVx()));
							factor = chooseRandomDirection(this.min,this.max);
							LOG.info("The factor is " + factor);
						
							swapXtoY(gameObject);
							gameObject.setVy(factor * gameObject.getVy());
						}
				
					 else if(gameObject.getX()+gameObject.getWidth() >= Constants.BOARD_WIDTH){
							gameObject.setX(gameObject.getX()+(-5 * gameObject.getVx()));
							factor = chooseRandomDirection(this.min,this.max);
							LOG.info("The factor is " + factor);
							
							swapXtoY(gameObject);
							gameObject.setVy(factor * gameObject.getVy());
							
							
						}
					else if(gameObject.getY() <= Constants.BOARD_OFFSET){
						gameObject.setY(gameObject.getY()+ (-5 *gameObject.getVy()));
							factor = chooseRandomDirection(this.min,this.max);
							LOG.info("The factor is " + factor);
							
							swapYtoX(gameObject);
							gameObject.setVx(factor * gameObject.getVx());
							
							
							
						}
					else if(gameObject.getY()+gameObject.getHeight() >= Constants.BOARD_HEIGHT){
						gameObject.setY(gameObject.getY()+ (- 5 *gameObject.getVy()));
							factor = chooseRandomDirection(this.min,this.max);
							LOG.info("The factor is " + factor);
							
							swapYtoX(gameObject);
							gameObject.setVx(factor * gameObject.getVx());
						
							
						}
					

				}
}
			public String getAgainstObjectName() {
				return againstObjectName;
			}
			public void setAgainstObjectName(String againstObjectName) {
				this.againstObjectName = againstObjectName;
			}
			
			private int chooseRandomDirection(int min, int max)
			{
				min = this.min;
				max = this.max;
				Random random = new Random();
				if (min > max) {
				      throw new IllegalArgumentException("Cannot draw random int from invalid range [" + min + ", " + max + "].");
				   }
				   int i;
				   
				   while(true)
				   {
				      i = random.nextInt(3)-1;
				      if(i==1 || i==-1)
				    	  break;
				      LOG.info("The value of i is " + i);
				   } 
				   LOG.info("the random value generated is ====== " + i);
				   return i;
			}
			
			public void swapXtoY(Drawable gameObject)
			{
				int temp = gameObject.getVx();
				gameObject.setVx(gameObject.getVy());
				gameObject.setVy(temp);
				LOG.info("the new velocities are" + gameObject.getVx() + " " + gameObject.getVy());
			}
			
			public void swapYtoX(Drawable gameObject)
			{
				int temp = gameObject.getVy();
				gameObject.setVy(gameObject.getVx());
				gameObject.setVx(temp);
				LOG.info("the new velocities are" + gameObject.getVx() + " " + gameObject.getVy());
			}
}



