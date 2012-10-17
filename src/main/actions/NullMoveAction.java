package main.actions;

import main.interfaces.IAction;
import main.model.Drawable;


public class NullMoveAction implements IAction {

	@Override
	public void act(Drawable d) {
	}

	public String toString(){
		return "None";
	}

}
