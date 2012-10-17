package main.actions;

import main.interfaces.IAction;
import main.model.Drawable;

public class NormalMoveAction implements IAction {

	@Override
	public void act(Drawable d) {
		d.setVx(d.getVx() + d.getAx());
		d.setVy(d.getVy() + d.getAy());
		d.setX(d.getX() + d.getVx());
		d.setY(d.getY() + d.getVy());
	}
	public String toString(){
		return "Normal Move";
	}
}
