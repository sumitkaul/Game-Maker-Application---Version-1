package main.actions;

import main.interfaces.IAction;
import main.model.Drawable;
import main.utilities.Sound;

public class PlaySoundAction implements IAction {

	private String soundFilename;

	public PlaySoundAction(String soundFilename)
	{
		this.soundFilename=soundFilename;
	}
	@Override
	public void act(Drawable d) {

		if(!this.soundFilename.isEmpty())
		{	
		    Sound.getInstance().setSound_file(this.soundFilename);
		    Sound.getInstance().playSound();
		}

	}
	public String toString(){
		return "Make Sound";
	}

}
