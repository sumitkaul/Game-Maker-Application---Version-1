package main.actions;

import main.interfaces.KeyAction;
import main.model.Drawable;

public class MoveUpKeyAction implements KeyAction {
	private int moveAmount;
	private int keyCode;
	public MoveUpKeyAction(){
		this.keyCode = 0;
		moveAmount = -1;
	}
	public MoveUpKeyAction(int amount){
		this.moveAmount = amount;
	}
	@Override
	public void act(Drawable d, int keyCode) {
		if(keyCode==this.keyCode)
			d.setY(d.getY() + moveAmount);
	}
	public void setKeyCode(int code){
		this.keyCode = code;
	}
	public void setAmount(int amount){
		this.moveAmount = amount;
	}
	public String toString(){
		return "Move Up";
	}

	@Override
	public int getKeyCode() {
		return this.keyCode;
	}
}
