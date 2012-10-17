package main.model;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.HashMap;
import java.util.List;

import main.interfaces.KeyAction;
import main.utilities.Action;
import main.utilities.ActionObjectPair;
import main.utilities.Event;

public interface GameObject {

	public void setId(int id);

	public int getId();

	public void setName(String name);

	public String getName();

	public void setObjectSize(int width, int height);

	public int getX();

	public int getY();
	
	public int getVx();

	public int getVy();

	public int getWidth();

	public int getHeight();

	public void setX(double x);

	public void setY(double y);
	
	public void setVx(double x);

	public void setVy(double y);

	public Image getImage();
	
	public void setImage(Image image);
	
	public void setImageFilePath(String path);
	
	public String getImageFilePath();

	public void addEvents(HashMap<String, List<?>> events);

	public void removeEvents(HashMap<String, List<String>> events);

	public HashMap<Event, List<ActionObjectPair>> getEvents();

	public void addActions(List<?> action);

	public void removeActions(List<?> action);

	public List<String> getActions();

	public void addEvent(Event event, Action action, String objectName);

	public void removeEvent(Event event, Action action, String objectName);

	public boolean getActionsMap(String action);
	
	public void setActionsMap(String action,Boolean value);
	
	public boolean intersects(Rectangle2D rect);
	
	public Double getObjectBounds();
	
	public void setKeyActions(List<KeyAction> keyActions);
	
	public List<KeyAction> getKeyActions();
	
	public List<Integer> getPressedKey();
	
	public void setPressedKey(List<Integer> pressedKey);

}
