package main.actions;

import main.interfaces.IAction;
import main.model.Drawable;

public class CreateObjectAction implements IAction {
	private Drawable childObject;
	public CreateObjectAction(Drawable childObject){
		this.setChildObject(childObject);
	}
	public void act(Drawable drawable) {
		
	}
	public Drawable getChildObject() {
		return childObject;
	}
	public void setChildObject(Drawable childObject) {
		this.childObject = childObject;
	}
}
