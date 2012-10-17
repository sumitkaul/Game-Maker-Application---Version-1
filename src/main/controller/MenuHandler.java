package main.controller;

import main.model.Drawable;
import main.model.GameObject;
import main.view.GameBoard;
import main.view.ListPanel;
import main.view.ObjectConfigurationFrame;

public class MenuHandler {

	public void removeItem(GameObject selectedObject) {
		GameController.getInstance().remove(selectedObject);
		GameBoard.getGameBoard().draw();
	}

	public void showProperties(GameObject selectedObject) {
		new ObjectConfigurationFrame((Drawable) selectedObject);
		
	}
	public void duplicateItem(GameObject selectedObject) {
		Drawable drawNewObj = (Drawable) selectedObject;
		Drawable newObj= (Drawable)drawNewObj.clone();
		GameController.getInstance().add(newObj);
		ListPanel listPanel=ListPanel.getInstance();
		listPanel.addAddedSpriteElements(newObj.getName());
		GameBoard.getGameBoard().repaint();
	}
}
