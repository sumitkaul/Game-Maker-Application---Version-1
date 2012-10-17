package main.actions;

import main.interfaces.KeyAction;
import main.model.Drawable;

public class NullKeyAction implements KeyAction {

	@Override
	public void act(Drawable d, int keyCode) {

	}

	@Override
	public void setKeyCode(int keyCode) {

	}
	
	@Override
	public int getKeyCode() {
		return -1;
	}

}
