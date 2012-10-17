package main.utilities;

import java.util.HashMap;

public enum Event {
	MOVE("Move"),
	COLLISION("Collision"),
	MOVE_OUT_OF_FRAME("Move Out of Frame");
	//KEY_INPUT("Key Input");

	private String event;
	private static final HashMap<String, Event> lookup = new HashMap<String, Event>();

	static {
	    for (Event pt : Event.values()) {
	        lookup.put(pt.toString(), pt);
	    }
	}
	
	private Event(String event) {
	    this.event = event;
	}

	public String getEvent() {
	   return event;
	}

	@Override
	public String toString() {
	   return event;
	}

	public static Event get(String event) {
	   return lookup.get(event);
	}	
}
