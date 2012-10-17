package main.actions;

import main.interfaces.KeyAction;
import main.model.Constants;
import main.model.Drawable;

public class MoveLeftKeyAction implements KeyAction {
	private int moveAmount;
	private int keyCode;
	public MoveLeftKeyAction(){
		this.keyCode = 0;
		moveAmount = (-1)*Constants.OFFSET;
	}
	public MoveLeftKeyAction(int amount){
		this.moveAmount = amount;
	}
	@Override
	public void act(Drawable d, int keyCode) {
		if(keyCode==this.keyCode)
			if(d.getX() + moveAmount > 0)
			d.setX(d.getX() + moveAmount);
	}
	public void setKeyCode(int code){
		this.keyCode = code;
	}
	public void setAmount(int amount){
		this.moveAmount = amount;
	}
	public String toString(){
		return "Move Left";
	}

	@Override
	public int getKeyCode() {
		return this.keyCode;
	}
}
