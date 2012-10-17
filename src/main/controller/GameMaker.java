package main.controller;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import main.model.Constants;
import main.view.Board;
import main.view.ControlPanel;
import main.view.GameBoard;
import org.apache.log4j.Logger;

/** @param board
 *            Object of class Board
 * @param control
 *            object of the ControlClass
 * @param scorePanel
 *            object of thE score Panel
 * @param bricks
 *            array of Brick objects
 * @param ball
 *            object of the Ball class
 * @param paddle
 *            object of the Paddle class */

public class GameMaker {

	/** @param args */

	private static Logger log = Logger.getLogger(GameMaker.class);
	private JFrame guiFrame;
	private JSplitPane splitPane;
	private Board controlPanel;
	private Board gameBoard;

	public static void main(String[] args) {
		new GameMaker();
	}

	public GameMaker() {
		log.info("Game Loaded");
		guiFrame = new JFrame();
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Game Maker by Team7 for A7");
		guiFrame.getRootPane().setDoubleBuffered(true);
		guiFrame.setSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
		controlPanel = new ControlPanel();
		gameBoard = GameBoard.getGameBoard();

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gameBoard, controlPanel);
		splitPane.setDividerLocation(Constants.BOARD_WIDTH);

		guiFrame.setContentPane(splitPane);
		guiFrame.setVisible(true);

		guiFrame.getRootPane().addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent arg0) {
				Constants.WINDOW_WIDTH = guiFrame.getWidth();
				Constants.WINDOW_HEIGHT = guiFrame.getHeight();

				// for resizing adding objects
				GameController composite = GameController.getInstance();

				// Every Drawable GameObject should be resized
				composite.setObjectSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
				Constants.BOARD_WIDTH = (int) (0.5 * Constants.WINDOW_WIDTH);
				Constants.BOARD_HEIGHT = (int) (0.95 * Constants.WINDOW_HEIGHT);
				Constants.CONTROLPANEL_WIDTH = (int) (0.5 * Constants.WINDOW_WIDTH);
				Constants.CONTROLPANEL_HEIGHT = (int) (0.95 * Constants.WINDOW_HEIGHT);
				splitPane.setDividerLocation(Constants.BOARD_WIDTH);
			}
		});
	}

}
