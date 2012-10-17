package main.actions;

import main.interfaces.IAction;
import main.model.Constants;
import main.model.Drawable;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class MoveAroundAction implements IAction {
	@XStreamOmitField
	private transient static Logger log = Logger.getLogger(MoveAroundAction.class);
	public void act(Drawable d) {
		log.trace(d.getX());
		d.setX(d.getX() + d.getVx());
		log.trace(d.getX());
		d.setY(d.getY() + d.getVy());
		if(d.getX()>Constants.BOARD_WIDTH)
		{
			d.setX(0);	

		}
		if(d.getX()<0)
		{
			d.setX(Constants.BOARD_WIDTH);	
			d.setX(d.getX() +d.getVx());
		}
		if(d.getY()>Constants.BOARD_HEIGHT)
		{
			d.setY(0);	

		}
		if(d.getY()<0)
		{
			d.setY(Constants.BOARD_HEIGHT);	

		}
	}
	public String toString(){
		return "Move Around";
	}
}
