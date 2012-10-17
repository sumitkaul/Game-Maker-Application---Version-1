package main.actions;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import main.interfaces.KeyAction;
import main.model.Constants;
import main.model.Drawable;

public class RotateCounterClockwiseKeyAction implements KeyAction {
	
	@XStreamOmitField
	private transient static Logger log = Logger.getLogger(RotateCounterClockwiseKeyAction.class);
	private int amount;
	private int keyCode;
	public RotateCounterClockwiseKeyAction(){
		this.amount = Constants.ROTATION_AMOUNT;
	}

	@Override
	public void act(Drawable d, int keyCode) {
		if(keyCode==this.keyCode){
			log.debug("Rotate CounterClockwise is being executed, amount:" + this.amount + " keycode:" + keyCode);
			d.setHeading(d.getHeading()-this.amount);
		}
	}

	@Override
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public String toString(){
		return "Rotate CounterClockwise";
	}
	
	@Override
	public int getKeyCode() {
		return this.keyCode;
	}
}