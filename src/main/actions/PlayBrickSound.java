package main.actions;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.geom.Rectangle2D;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import main.actions.collide.BounceRandomAction;
import main.controller.GameController;
import main.interfaces.Action;
import main.model.Constants;
import main.model.Drawable;
import main.model.GameObject;

public class PlayBrickSound implements Action 
{

	private String clipName;
	private String againstObjectName;

	@XStreamOmitField
	private transient static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
	.getLogger(BounceRandomAction.class);


	public PlayBrickSound() 
	{
		this.clipName = "/sounds/Brick.au";
	}

	//	@Override
	//	public void act(Drawable d) {
	//		JarReader jarReader = new JarReader();
	//		jarReader.setZipStream("sounds.jar");
	//
	//		//		try {
	//		//			FileInputStream fstream = new FileInputStream(getClass().getClassLoader().getResource(jarReader.getFileEntry(clipName)).toString());
	//		//		} catch (FileNotFoundException e) {
	//		//		}
	//
	//		AudioClip sound = Applet.newAudioClip(PlayBrickSound.class.getClassLoader().getResource(jarReader.getFileEntry(clipName)));
	//		sound.play();
	//	}

	@Override
	public void act(Drawable gameObject) 
	{
		AudioClip sound = Applet.newAudioClip(PlayBrickSound.class.getResource(clipName));
		
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
				//if(obj.equals(gameObject)) continue;
				// Collide with any game object
				if(getAgainstObjectName().equals(Constants.ANY_OBJECT)){
					if(gameObject.intersects(obj.getObjectBounds())){
						if(!xReversed.intersects(obj.getObjectBounds())){
							sound.play();
						}
						if(!yReversed.intersects(obj.getObjectBounds())){
							sound.play();
						}
						else if(yReversed.intersects(obj.getObjectBounds())&&xReversed.intersects(obj.getObjectBounds())){
							sound.play();
						}
					}
				// Collide with particular object
				} 
				else if(obj.getName().equals(getAgainstObjectName())) {
					if(gameObject.intersects(obj.getObjectBounds())){
						LOG.info("Collision detected : sounds");
						if(!xReversed.intersects(obj.getObjectBounds())){
							sound.play();
						}
						else if(!yReversed.intersects(obj.getObjectBounds())){
							sound.play();
						}
					}

				}
			}
		
		if(getAgainstObjectName().equals(Constants.GAME_WALL)) {
			if(gameObject.getX()<=Constants.BOARD_OFFSET) {
				sound.play();
			}
			else if(gameObject.getX()+gameObject.getWidth() >= Constants.BOARD_WIDTH){
				sound.play();
			}
			else if(gameObject.getY() <= Constants.BOARD_OFFSET){
				sound.play();
			}
			else if(gameObject.getY()+gameObject.getHeight() >= Constants.BOARD_HEIGHT){
				sound.play();
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
