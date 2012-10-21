package main.controller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import main.model.Constants;
import main.model.MakerState;
import main.view.GameBoard;

import org.apache.log4j.Logger;

import repos.DBConnector;
import repos.GameLoadPanel;

public class Controls implements ActionListener {
    
    private static Controls control;
    
    public static Controls getInstance() {
        if (control == null) {
            control = new Controls();
        }
        return control;
    }
    private JPanel panel;
    private JButton start;
    private JButton stop;
    private JButton load;
    private JButton save;
    
    private JTextField scoreText;
	private JLabel scoreLabel;
	
    private LayoutGrid lg = new LayoutGrid(panel);
    private LayoutFlow lf = new LayoutFlow(panel);
    private Logger log = Logger.getLogger(Controls.class);
    private TimerHandler daemonThread;
    private Timer daemon;
    private GameController compositeClass;
    private GameBoard gameBoard;
    
    public Controls() {
        panel = new JPanel(new GridLayout(3, 2));
        start = new JButton("START");
        stop = new JButton("STOP");
        load = new JButton("LOAD");
        save = new JButton("SAVE");
        
        scoreLabel = new JLabel("Score: ");
        scoreText = new JTextField(5);
        
        panel.add(start);
        panel.add(stop);
        //panel.add(gameList);
        panel.add(load);
        panel.add(save);
        
        panel.add(scoreLabel);
        panel.add(scoreText);
        
        start.addActionListener(this);
        stop.addActionListener(this);
        load.addActionListener(this);
        save.addActionListener(this);
        
        daemonThread = new TimerHandler();
        daemon = new Timer(Constants.TIMER_DELAY, daemonThread);
        compositeClass = GameController.getInstance();
        gameBoard = GameBoard.getGameBoard();
        
    }
    
    public void switchPanel(JPanel panel, GridLayout gl) {
        lg.execute(panel, gl);
    }
    
    public void switchPanel(JPanel panel, FlowLayout fl) {
        lf.execute(panel, fl);
    }
    
    public JPanel getPanel() {
        return panel;
    }
    
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
    public JTextField getScoreText() {
        return scoreText;
    }

    public void setScoreText(JTextField scoreText) {
        this.scoreText = scoreText;
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            log.info("Start is clicked");
            daemon.start();
            gameBoard.requestFocus();
        } else if (e.getSource() == stop) {
            log.info("Stop button is clicked");
            daemon.stop();
        } else if (e.getSource() == load) {
            log.info("Load button is clicked");
            
/*            GameLoadPanel p = new GameLoadPanel(panel);
            String gameData = p.readGameDataFromRemoteList();*/
            DBConnector db = new DBConnector();
            Object[] possibilities = db.getSavedGameNames().toArray();
	        String chosen = (String) JOptionPane.showInputDialog(
	                null,
	                "Select Game from ",
	                "Load Game",
	                JOptionPane.PLAIN_MESSAGE,
	                null, possibilities,
	                null);
	
	        if (chosen == null) {
	            return;
	        }
            String gameData = db.getSavedGame(chosen);
            if (gameData == null || gameData.isEmpty()) {
                return;
            }
            
            MakerState state = new MakerState(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT), compositeClass);
            
            state.load(gameData, false);
            
            gameBoard.draw();

//            Object[] possibilities = GameRepo.getInstance().getGameNameList().toArray();
//            String staeString = (String) JOptionPane.showInputDialog(
//                    panel,
//                    "Select Game : ",
//                    "Load Game",
//                    JOptionPane.PLAIN_MESSAGE,
//                    null, possibilities,
//                    null);
//            state.load(staeString);
//
//            gameBoard.draw();
        } else if (e.getSource() == save) {
            log.info("Save button is clicked");
            MakerState state = new MakerState(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT), compositeClass);
            String gameData = state.getSaveString();
            String gameName = JOptionPane.showInputDialog(null, "Please give your game a name:", "Save Game", JOptionPane.PLAIN_MESSAGE);
            if (gameName == null || gameName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please type something");
                return;
            }
            DBConnector db = new DBConnector();
            db.saveGame(GameBoard.getGameBoard().getScore(), gameName, gameData);
            //GameSavePanel p = new GameSavePanel(panel);
            //p.saveGameToRemoteServer(gameData);
        }
    }
    
    class TimerHandler implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
            compositeClass.moveAllObjects();
            gameBoard.draw();
            gameBoard.requestFocus();
            compositeClass.checkObjectCollision();
            scoreText.setText(new Integer(GameBoard.getGameBoard().getScore()).toString());
        }
    }
    
    public Timer getDaemon() {
        return daemon;
    }
    
    public void setDaemon(Timer daemon) {
        this.daemon = daemon;
    }
}
