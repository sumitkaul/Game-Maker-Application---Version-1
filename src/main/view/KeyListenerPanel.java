package main.view;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class KeyListenerPanel extends JPanel implements KeyListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  JLabel infoLbl;
	private String charType;
	private int keyCode;
	/*
	 * This panel will be used to get user inputs from user. Not fully implemented.
	 */
	public KeyListenerPanel(){
		super();
		setFocusable(true);
		infoLbl = new JLabel();
		infoLbl.setText("Click & press a key");
		infoLbl.setForeground(Color.WHITE);
		add(infoLbl);
		addKeyListener(this);
		addMouseListener(this);
		this.setBackground(Color.DARK_GRAY);
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		setCharType(KeyEvent.getKeyText(e.getKeyCode()));
		infoLbl.setText("You have selected :"+getCharType());
		setKeyCode(e.getKeyCode());
	}

	public void setCharType(String charType)
	{
		this.charType=charType;
	}

	public String getCharType()
	{
		return charType;
	}

	public void setKeyCode(int keyCode)
	{
		this.keyCode=keyCode;
		setCharType(KeyEvent.getKeyText(keyCode));
		infoLbl.setText("You have selected :"+getCharType());
		this.keyCode=keyCode;
	}

	public int getKeyCode()
	{
		return keyCode;
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}



	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		requestFocus();

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}



}
