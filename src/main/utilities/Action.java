package main.utilities;

import java.util.HashMap;

public enum Action {
	NORMAL_MOVE("Normal Move"),
	MOVE_AROUND("Move Around"),
	DESCEND_LEFT_RIGHT("Descend Left and Right"),
	MOVE_UP("Move Up"),
	MOVE_DOWN("Move Down"),
	MOVE_LEFT("Move Left"),
	MOVE_RIGHT("Move Right"),
	MOVE_FORWARD("Move Forward"),
	ROTATE_CLOCKWISE("Rotate Clockwise"),
	ROTATE_COUNTERCLOCKWISE("Rotate CounterClockwise"),
	CREATE_OBJECT("Create Object"),
	BOUNCE("Bounce"),
	DISAPPEAR("Disappear"),
	STICK("Stick"),
	JUMP_UP("Jump Up"),
	JUMP_DOWN("Jump Down"),
	JUMP_LEFT("Jump Left"),
	JUMP_RIGHT("Jump Right"),
	GAME_OVER("Game Over"),
	GAME_WIN("Game Win"),
	BOUNCE_RANDOM("Bounce Random"),
	SCORE("Add Score");

	private String action;
	private static final HashMap<String, Action> lookup = new HashMap<String, Action>();

	static {
	    for (Action pt : Action.values()) {
	        lookup.put(pt.toString(), pt);
	    }
	}
	
	private Action(String action) {
	    this.action = action;
	}

	public String getAction() {
	   return action;
	}

	@Override
	public String toString() {
	   return action;
	}

	public static Action get(String action) {
	   return lookup.get(action);
	}	
}
