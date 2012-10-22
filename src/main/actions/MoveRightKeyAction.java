package main.actions;

import main.interfaces.KeyAction;
import main.model.Drawable;

public class MoveRightKeyAction implements KeyAction {
	private int moveAmount;
	private int keyCode;
	public MoveRightKeyAction(){
		this.keyCode = 0;
		moveAmount = 1;
	}
	public MoveRightKeyAction(int amount){
		this.moveAmount = amount;
	}
	@Override
	public void act(Drawable d, int keyCode) {
		if(keyCode==this.keyCode)
			d.setX(d.getX() + moveAmount);
	}
	public void setKeyCode(int code){
		this.keyCode = code;
	}
	public void setAmount(int amount){
		this.moveAmount = amount;
	}
	public String toString(){
		return "Move Right";
	}

	@Override
	public int getKeyCode() {
		return this.keyCode;
	}
}
