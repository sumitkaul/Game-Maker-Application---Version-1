package main.model;

public class ListItem {

	private String name;
	private int spriteId;

	public ListItem(String name, int spriteId) {
		this.name = name;
		this.spriteId = spriteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpriteId() {
		return spriteId;
	}

	public void setSpriteId(int spriteId) {
		this.spriteId = spriteId;
	}

	public String toString() {
		return this.name;
	}

}
