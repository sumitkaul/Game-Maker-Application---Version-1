package main.actions;

import main.interfaces.IAction;
import main.model.Drawable;

public class DescendLeftAndRightAction implements IAction {
	private int leftRightAmount = 40;
	private int descendAmount = 5;
	private int curLeftRight = 0;
	private boolean goingRight = true;
	private int moveAmount = 1;
	private int howOften = 3; //object will get moved 1 out of every howOften+1 frames
	private int frame = 0;
	@Override
	public void act(Drawable d) {
		frame++;
		if(frame%howOften!=0) return;
		if(goingRight){
			if(curLeftRight<leftRightAmount){
				curLeftRight+=moveAmount;
				d.setX(d.getX() + moveAmount);
			}
			else {
				goingRight=false;
				d.setY(d.getY() + this.descendAmount);
			}
		}
		else {
			if(curLeftRight>0){
				curLeftRight-=moveAmount;
				d.setX(d.getX() - moveAmount);
			}
			else {
				goingRight = true;
				d.setY(d.getY() + this.descendAmount);
			}
		}
	}

	public String toString(){
		return "Descend Left and Right";
	}

}
