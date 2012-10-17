package main.actions;

import java.awt.event.KeyEvent;

import main.model.Constants;
import main.model.Drawable;
import main.interfaces.IAction;

public class MoveKeysAction implements IAction{


	private int keyCode1;
	private int keyCode2;
	private int jumpFactor;
	private String direction1;
	private String direction2;
	private KeyEvent key;

	public MoveKeysAction()
	{

	}

	@Override
	public void act(Drawable d) 
	{

		if(key.getKeyCode()==keyCode1 && this.getDirection1().equalsIgnoreCase("Up"))
		{
			if(d.getY() > 0 && ( d.getY() - Constants.OFFSET > 0) ){
				d.setY(d.getY() - (Constants.OFFSET+getJumpFactor()));
			}


		}

		if(key.getKeyCode()==keyCode1 && this.getDirection1().equalsIgnoreCase("Down"))
		{
			if(d.getY() + d.getHeight() < Constants.BOARD_HEIGHT && (d.getY() + d.getHeight() + Constants.OFFSET < Constants.BOARD_HEIGHT)){
				d.setY(d.getY()+getJumpFactor()+ Constants.OFFSET);
			}

		}

		if(key.getKeyCode()==keyCode1 && this.getDirection1().equalsIgnoreCase("Left"))
		{
			if(d.getX()>0 && (d.getX()- Constants.OFFSET > 0 ))
			{
				d.setX(d.getX()-(Constants.OFFSET+getJumpFactor()));
			}
		}

		if(key.getKeyCode()==keyCode1 && this.getDirection1().equalsIgnoreCase("Right"))
		{
			if (d.getX() + d.getWidth() < Constants.BOARD_WIDTH && ( d.getX() + d.getWidth() + Constants.OFFSET < Constants.BOARD_WIDTH) ) {
				d.setX(d.getX() +getJumpFactor()+Constants.OFFSET);
			}
		}


		if((key.getKeyCode()==keyCode1 && this.getDirection1().equalsIgnoreCase("Left"))
				&& (key.getKeyCode()==keyCode2 && this.getDirection2().equalsIgnoreCase("Up"))
				||((key.getKeyCode()==keyCode1 && this.getDirection1().equalsIgnoreCase("Up"))
						&& (key.getKeyCode()==keyCode2 && this.getDirection2().equalsIgnoreCase("Left"))))
		{

			d.setX(d.getX()-((d.getVx() * (float)Math.cos(Math.toRadians(24))+getJumpFactor())));
			d.setY(d.getY()-((d.getVy() * (float)Math.sin(Math.toRadians(24)))+getJumpFactor()));


		}

		if((key.getKeyCode()==keyCode1 && this.getDirection1().equalsIgnoreCase("Left"))
				&& (key.getKeyCode()==keyCode2 && this.getDirection2().equalsIgnoreCase("Down"))
				||((key.getKeyCode()==keyCode1 && this.getDirection1().equalsIgnoreCase("Down"))
						&& (key.getKeyCode()==keyCode2 && this.getDirection2().equalsIgnoreCase("Left"))))
		{
			d.setX(d.getX()-((d.getVx() * (float)Math.sin(Math.toRadians(24)))+getJumpFactor()));
			d.setY(d.getY()+((d.getVy() * (float)Math.cos(Math.toRadians(24)))+getJumpFactor()));


		}
		if((key.getKeyCode()==keyCode1 && this.getDirection1().equalsIgnoreCase("Right"))
				&& (key.getKeyCode()==keyCode2 && this.getDirection2().equalsIgnoreCase("Up"))
				||((key.getKeyCode()==keyCode1 && this.getDirection1().equalsIgnoreCase("Up"))
						&& (key.getKeyCode()==keyCode2 && this.getDirection2().equalsIgnoreCase("Right"))))
		{
			d.setX(d.getX()+((d.getVx() * (float)Math.cos(Math.toRadians(24)))+getJumpFactor()));
			d.setY(d.getY()-((d.getVy() * (float)Math.sin(Math.toRadians(24)))+getJumpFactor()));
		}

		if((key.getKeyCode()==keyCode1 && this.getDirection1().equalsIgnoreCase("Right"))
				&& (key.getKeyCode()==keyCode2 && this.getDirection2().equalsIgnoreCase("Down"))
				||((key.getKeyCode()==keyCode1 && this.getDirection1().equalsIgnoreCase("Down"))
						&& (key.getKeyCode()==keyCode2 && this.getDirection2().equalsIgnoreCase("Right"))))

		{
			d.setX(d.getX()+((d.getVx() * (float)Math.sin(Math.toRadians(35)))+getJumpFactor()));
			d.setY(d.getY()+((d.getVy() * (float)Math.cos(Math.toRadians(35)))+getJumpFactor()));

		}

	}

	public void setKeyCode1(int keyCode1)
	{

		this.keyCode1=keyCode1;

	}

	public int getKeyCode1()
	{
		return keyCode1; 
	}

	public void setKeyCode2(int keyCode2)
	{

		this.keyCode2=keyCode2;

	}

	public int getKeyCode2()
	{
		return keyCode2; 
	}



	public void setDirection1(String direction)
	{
		this.direction1=direction;
	}

	public String getDirection1()
	{
		return direction1;
	}

	public void setDirection2(String direction)
	{
		this.direction2=direction;
	}

	public String getDirection2()
	{
		return direction2;
	}

	public int getJumpFactor() {
		return jumpFactor;
	}

	public void setJumpFactor(int jumpFactor) {
		this.jumpFactor = jumpFactor;
	}


}
