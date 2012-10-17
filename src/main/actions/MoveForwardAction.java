package main.actions;

import main.interfaces.IAction;
import main.model.Constants;
import main.model.Drawable;

public class MoveForwardAction implements IAction {
	private int moveAmount;

	public MoveForwardAction() {
		this.moveAmount = Constants.OFFSET;
	}

	@Override
	public void act(Drawable d) {
		d.setX(d.getX() + moveAmount * Math.cos(Math.toRadians(d.getHeading() - 90)));
		d.setY(d.getY() + moveAmount * Math.sin(Math.toRadians(d.getHeading() - 90)));
	}

	public String toString() {
		return "Normal Move";
	}

	public void setAmount(int amount) {
		this.moveAmount = amount;
	}
}
