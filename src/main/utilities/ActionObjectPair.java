package main.utilities;

public class ActionObjectPair {

	private Action action;
	private String gameObjectName;
	
	public ActionObjectPair() {
	}
	
	public ActionObjectPair(Action action, String objectName) {
		this.action = action;
		this.gameObjectName = objectName;
	}
	
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public String getGameObjectName() {
		return gameObjectName;
	}
	public void setGameObjectName(String gameObjectName) {
		this.gameObjectName = gameObjectName;
	}
}
