package main.actions;

import main.interfaces.KeyAction;
import main.model.Constants;
import main.model.Drawable;

public class MoveDownKeyAction implements KeyAction {
	private int moveAmount;
	private int keyCode;
	public MoveDownKeyAction(){
		this.keyCode = 0;
		moveAmount = (-1)*Constants.OFFSET;
	}
	public MoveDownKeyAction(int amount){
		this.moveAmount = amount;
	}
	@Override
	public void act(Drawable d, int keyCode) {
		if(keyCode==this.keyCode)
			if((d.getY() - moveAmount +d.getHeight())< Constants.BOARD_HEIGHT)
			d.setY(d.getY() - moveAmount);
	}
	public void setKeyCode(int code){
		this.keyCode = code;
	}
	public void setAmount(int amount){
		this.moveAmount = amount;
	}
	public String toString(){
		return "Move Down";
	}
	
	@Override
	public int getKeyCode() {
		return this.keyCode;
	}

}
