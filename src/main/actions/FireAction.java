package main.actions;

import main.interfaces.IAction;
import main.model.Drawable;

public class FireAction implements IAction{
	public void FireAction()
	{
		
	}
	public void act(Drawable d) {
		d.setVx(d.getVx() + d.getAx());
		d.setVy(d.getVy() + d.getAy());
		d.setX(d.getX() + d.getVx() * Math.cos(Math.toRadians(d.getHeading()-90)));
		d.setY(d.getY() + d.getVy() * Math.sin(Math.toRadians(d.getHeading()-90)));
	}
	public String toString(){
		return "Fire";
	}
}
