package main.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.utilities.Action;
import main.utilities.ActionObjectPair;
import main.utilities.Event;
import main.view.ChildConfigurationFrame;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * All the classes extend the methods from this class so that the code is reused
 * wherever applicable.
 */

public class Sprite {
	
	@XStreamOmitField
	private transient static Logger log = Logger.getLogger(Sprite.class);

	protected int id;
	protected String name;
	protected int x, y;
	protected int vx,vy;
	protected int ax,ay;
	protected int heading, aHeading, vHeading;
	protected int width, height;
	protected String imageFilePath;

	@XStreamOmitField
	protected transient BufferedImage img;

	@XStreamOmitField
	protected transient ChildConfigurationFrame childConfigurationFrame;

	protected HashMap<Event, List<ActionObjectPair>> events = new HashMap<Event, List<ActionObjectPair>>();
	protected List<String> actions = new ArrayList<String>();
	protected HashMap<String, Boolean> actionsMap = new HashMap<String,Boolean>();
	
	
	public boolean getActionsMap(String action){
		return Boolean.TRUE.equals(actionsMap.get(action));
	}

	public void setActionsMap(String action, Boolean value){
		actionsMap.put(action, value);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = (int)vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = (int)vy;
	}

	public int getX() {
		return x;
	}

	public void setX(double x) {
		log.trace("setX in " + this.getName() + "x=" + x + " oldx=" + this.x);
		this.x = (int)x;
	}

	public int getY() {
		return y;
	}

	public void setY(double y) {
		log.trace("set Y in " + this.getName() + "y=" + y + " oldy=" + this.y);
		this.y = (int)y;
	}

	public int getAx() {
		return ax;
	}

	public void setAx(int ax) {
		this.ax = ax;
	}

	public int getAy() {
		return ay;
	}

	public void setAy(int ay) {
		this.ay = ay;
	}

	public int getHeading() {
		return heading;
	}

	public void setHeading(int heading) {
		log.trace("setHeading " + this.getName());
		this.heading = heading;
	}

	public int getaHeading() {
		return aHeading;
	}

	public void setaHeading(int aHeading) {
		this.aHeading = aHeading;
	}

	public int getvHeading() {
		return vHeading;
	}

	public void setvHeading(int vHeading) {
		this.vHeading = vHeading;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(double width) {
		log.debug("setWidth " + this.getName() + " to " + width);
		this.width = (int)width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(double height) {
		log.debug("setHeight " + this.getName() + " to " + height);
		this.height = (int)height;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public HashMap<Event, List<ActionObjectPair>> getEvents() {
		return events;
	}


	public ChildConfigurationFrame getChildConfigurationFrame() {
		return childConfigurationFrame;
	}
	public void setChildConfigurationFrame(
			ChildConfigurationFrame childConfigurationFrame) {
		this.childConfigurationFrame = childConfigurationFrame;
	}


	public void addEvent(Event event, Action action, String actionObjectName) {
		List<ActionObjectPair> existingActions = this.events.get(event);
		List<ActionObjectPair> newActions = new ArrayList<ActionObjectPair>();;
		if(existingActions!=null)
			newActions.addAll(existingActions);
		ActionObjectPair actionObjectPair = new ActionObjectPair(action,actionObjectName);
			newActions.add(actionObjectPair);
		this.events.put(event, newActions);
	}

	public void addEvents(HashMap<String, List<?>> events) {
		for(Object key : events.keySet()) {
			List<?> actions = events.get(key);
			List<String> newActions = new ArrayList<String>();
			for(Object action : actions) {
				newActions.add(action.toString());
			}
			//this.events.put(key.toString(), newActions);
		}
	}

	public void removeEvent(Event event, Action action, String againstObjectName) {
		List<ActionObjectPair> existingActions = this.events.get(event);
		List<ActionObjectPair> existingActionsCopy = new ArrayList<ActionObjectPair>();
		existingActionsCopy.addAll(existingActions);
		for(ActionObjectPair actionObject : existingActionsCopy) {
			if(actionObject.getAction().equals(action)) {
				if(actionObject.getGameObjectName()!=null && againstObjectName!=null) {
					if(actionObject.getGameObjectName().equals(againstObjectName)) {
						existingActions.remove(actionObject);
					}
				}
				else if(againstObjectName==null && againstObjectName==null) {
					existingActions.remove(actionObject);
				}
			}
		}
		if(existingActions.size()==0) {
			this.events.remove(event);
		}
	}

	public void removeEvents(HashMap<String, List<String>> events) {
		for(String key : events.keySet()) {
			this.events.remove(key);
		}
	}

	public void addActions(List<?> actions) {
		for(Object action : actions) {
			this.actions.add(action.toString());
		}
	}

	public void removeActions(List<?> actions) {
		for(Object action : actions) {
			if(this.actions.contains(action.toString().trim()))
				this.actions.remove(action.toString().trim());
		}
	}

	public List<String> getActions() {
		return this.actions;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

}
