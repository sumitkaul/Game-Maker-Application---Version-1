package main.actions;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import main.interfaces.KeyAction;
import main.model.Constants;
import main.model.Drawable;

public class MoveForwardKeyAction implements KeyAction {
	
	@XStreamOmitField
	private transient static Logger log = Logger.getLogger(MoveForwardKeyAction.class);
	private int keyCode;
	private int moveAmount;
	public MoveForwardKeyAction(){
		this.moveAmount = Constants.OFFSET;
	}
	@Override
	public void act(Drawable d, int keyCode) {
		if(keyCode==this.keyCode){
			log.debug("Move Forward is being executed, amount:" + this.moveAmount + " keycode:" + keyCode);
			d.setX(d.getX() + moveAmount*Math.cos(Math.toRadians(d.getHeading()-90)));
			d.setY(d.getY() + moveAmount*Math.sin(Math.toRadians(d.getHeading()-90)));
		}
	}

	@Override
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public void setMoveAmount(int amount){
		this.moveAmount = amount;
	}

	public String toString(){
		return "Move Forward";
	}

	@Override
	public int getKeyCode() {
		return this.keyCode;
	}

}
