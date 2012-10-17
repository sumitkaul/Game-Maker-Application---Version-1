package main.actions;

import main.interfaces.KeyAction;
import main.model.Constants;
import main.model.Drawable;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class RotateClockwiseKeyAction implements KeyAction {
	
	@XStreamOmitField
	private transient static Logger log = Logger.getLogger(RotateClockwiseKeyAction.class);
	private int amount;
	private int keyCode;

	public RotateClockwiseKeyAction() {
		this.amount = Constants.ROTATION_AMOUNT;
	}

	@Override
	public void act(Drawable d, int keyCode) {
		if (keyCode == this.keyCode) {
			log.debug("RotateClockwise on " + d.getName() + " amount:" + this.amount + " keycode:" + keyCode);
			d.setHeading(d.getHeading() + this.amount);
		}
	}

	@Override
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public String toString() {
		return "Rotate Clockwise";
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int getKeyCode() {
		return this.keyCode;
	}

	public int getAmount() {
		return amount;
	}
}
