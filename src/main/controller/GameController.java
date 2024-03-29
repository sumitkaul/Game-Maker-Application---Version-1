package main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import main.actions.ScoreAction;
import main.actions.collide.BounceRandomAction;
import main.actions.collide.CollideBounceAction;
import main.actions.collide.CollideGameLoseAction;
import main.actions.collide.CollideGameWinAction;
import main.actions.collide.CollideRemoveAction;
import main.actions.collide.CollideStickAction;
import main.interfaces.KeyAction;
import main.model.Drawable;
import main.model.GameObject;
import main.utilities.Action;
import main.utilities.ActionObjectPair;
import main.utilities.Event;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class GameController {

	private List<GameObject> childObjects = new CopyOnWriteArrayList<GameObject>();
	private GameObject selectedObject;
	@XStreamOmitField
	private transient static GameController compositeObject;

	public static GameController getInstance() {
		if (compositeObject == null)
			compositeObject = new GameController();
		return compositeObject;

	}

	public void setObjectSize(int windowWidth, int windowHeight) {
		for (GameObject compositeObject : childObjects) {
			compositeObject.setObjectSize(windowWidth, windowHeight);
		}
	}

	public void add(GameObject compositeObject) {
		childObjects.add(compositeObject);
	}

	public void add(List<GameObject> compositeObject) {
		childObjects.addAll(compositeObject);
	}

	public void remove(GameObject compositeObject) {
		childObjects.remove(compositeObject);
	}

	public void removeAll() {
		childObjects.clear();
	}

	public List<GameObject> getChildObjects() {
		return childObjects;
	}

	public void setChildObjects(ArrayList<GameObject> childObjects) {
		this.childObjects = childObjects;
	}

	public GameObject getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(GameObject selectedObject) {
		this.selectedObject = selectedObject;
	}

	public void moveAllObjects() {
		for (GameObject compositeObject : childObjects) {
			move(compositeObject);
		}
	}

	public void checkObjectCollision() {
		for (GameObject compositeObject : childObjects) {
			checkCollision(compositeObject);
		}
	}
	
	private void move(GameObject gameObject) {
		// Normal Move
		Drawable item = (Drawable) gameObject;
		item.getMoveAction().act(item);
		// Key events move
		for(int key : item.getPressedKey()){
			for(KeyAction a : item.getKeyActions()){
				a.act(item, key);
			}
		}
	}
	
	private void checkCollision(GameObject gameObject) {
		Drawable item = (Drawable) gameObject;
		if(item.getEvents().containsKey(Event.COLLISION)) {
			List<ActionObjectPair> actions = item.getEvents().get(Event.COLLISION);
			for(ActionObjectPair action : actions) {
				if(action.getAction().equals(Action.BOUNCE)) {
					CollideBounceAction bounce = new CollideBounceAction();
					if(action.getRelatedAction() !=null)
						   bounce.setSoundAction(action.getRelatedAction());
					bounce.setAgainstObjectName(action.getGameObjectName());
					bounce.act(item);
				}
				if(action.getAction().equals(Action.BOUNCE_RANDOM)){
					BounceRandomAction bounceRandom = new BounceRandomAction();
					if(action.getRelatedAction() !=null)
						   bounceRandom.setSoundAction(action.getRelatedAction());
					bounceRandom.setAgainstObjectName(action.getGameObjectName());
					bounceRandom.act(item);
				}
				if(action.getAction().equals(Action.DISAPPEAR)) {
					CollideRemoveAction bounce = new CollideRemoveAction();
					if(action.getRelatedAction() !=null)
						   bounce.setSoundAction(action.getRelatedAction());
					bounce.setAgainstObjectName(action.getGameObjectName());
					bounce.act(item);
				}
				if(action.getAction().equals(Action.STICK)) {
					CollideStickAction bounce = new CollideStickAction();
					if(action.getRelatedAction() !=null)
						   bounce.setSoundAction(action.getRelatedAction());
					bounce.setAgainstObjectName(action.getGameObjectName());
					bounce.act(item);
				}
				if(action.getAction().equals(Action.GAME_OVER)) {
					CollideGameLoseAction bounce = new CollideGameLoseAction();
					if(action.getRelatedAction() !=null)
						   bounce.setSoundAction(action.getRelatedAction());
					bounce.setAgainstObjectName(action.getGameObjectName());
					bounce.act(item);
				}
				if(action.getAction().equals(Action.GAME_WIN)) {
					CollideGameWinAction bounce = new CollideGameWinAction();
					if(action.getRelatedAction() !=null)
						   bounce.setSoundAction(action.getRelatedAction());
					bounce.setAgainstObjectName(action.getGameObjectName());
					bounce.act(item);
				}
				if(action.getAction().equals(Action.SCORE)){
					ScoreAction bounce = new ScoreAction();
					bounce.setAgainstObjectName(action.getGameObjectName());
					bounce.act(item);
					
				}
			}
			
		}

	}
}
