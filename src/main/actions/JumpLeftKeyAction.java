package main.actions;

import main.interfaces.KeyAction;
import main.model.Drawable;

public class JumpLeftKeyAction implements KeyAction {
	private long timeBetweenJumps = 250; // in ms
	private long lastJumpTime = 0;
	private int keyCode = -1;
	@Override
	public void act(Drawable d, int keyCode) {
		if (keyCode == this.keyCode) {
			long curTime = System.currentTimeMillis();
			if (curTime - lastJumpTime >= timeBetweenJumps) {
				d.setX(d.getX() - d.getHeight());
				lastJumpTime = curTime;
			}
		}

	}

	@Override
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}
	
	@Override
	public int getKeyCode() {
		return this.keyCode;
	}
}
