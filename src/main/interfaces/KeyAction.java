package main.interfaces;

import main.model.Drawable;

public interface KeyAction {
	public void act(Drawable d, int keyCode);
	public void setKeyCode(int keyCode);
	public int getKeyCode();
}
