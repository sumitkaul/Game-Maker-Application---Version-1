package main.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import repos.DBConnector;

import main.controller.GameMaker;
import main.model.Constants;

public class Login {
	
	private JFrame loginFrame;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameText;
	private JPasswordField passwordText;
	private JButton loginButton;
	private JButton signupButton;
	private JPanel contentPane;
	private DBConnector db;

	public static void main(String[] args) {
		new Login();
	}

	public Login() {
		db = new DBConnector();
		create();
		loginFrame.setVisible(true);
	}

	private void create() {
		loginFrame = new JFrame();
		usernameLabel = new JLabel();
		passwordLabel = new JLabel();
		usernameText = new JTextField();
		passwordText = new JPasswordField();
		loginButton = new JButton();
		signupButton = new JButton();
		contentPane = new JPanel();

		usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		usernameLabel.setForeground(new Color(0, 0, 255));
		usernameLabel.setText("Username:");
		passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordLabel.setForeground(new Color(0, 0, 255));
		passwordLabel.setText("Password:");

		usernameText.setForeground(new Color(0, 0, 255));
		usernameText.setSelectedTextColor(new Color(0, 0, 255));
		usernameText.setToolTipText("Enter your username");
		usernameText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==10) {
					login_check();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			@Override
			public void keyTyped(KeyEvent arg0) {				
			}
		});
		passwordText.setForeground(new Color(0, 0, 255));
		passwordText.setToolTipText("Enter your password");
		passwordText.addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent arg0) {
					if(arg0.getKeyCode()==10) {
						login_check();
					}
				}
				@Override
				public void keyReleased(KeyEvent arg0) {
				}
				@Override
				public void keyTyped(KeyEvent arg0) {				
				}
			});

		loginButton.setBackground(new Color(204, 204, 204));
		loginButton.setForeground(new Color(0, 0, 255));
		loginButton.setText("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_check();
			}
		});
		
		signupButton.setBackground(new Color(204, 204, 204));
		signupButton.setForeground(new Color(0, 0, 255));
		signupButton.setText("Signup");
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp signup=new SignUp();
				
			}
		});

		contentPane.setLayout(null);
		contentPane.setBorder(BorderFactory.createEtchedBorder());
		contentPane.setBackground(new Color(204, 204, 204));
		addComponent(contentPane, usernameLabel, 5, 10, 106, 18);
		addComponent(contentPane, passwordLabel, 5, 47, 97, 18);
		addComponent(contentPane, usernameText, 110, 10, 183, 22);
		addComponent(contentPane, passwordText, 110, 45, 183, 22);
		addComponent(contentPane, loginButton, 50, 75, 83, 28);
		addComponent(contentPane, signupButton, 150, 75, 83, 28);

		loginFrame.setTitle("Login");
		loginFrame.setLocation(new Point(500, 300));
		loginFrame.setSize(new Dimension(335, 141));
		loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		loginFrame.setResizable(false);
		loginFrame.setContentPane(contentPane);
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(JPanel contentPane, Component c, int x, int y, int width, int height) {
		c.setBounds(x, y, width, height);
		contentPane.add(c);
	}

	private void login_check() {
		String username = new String(usernameText.getText());
		String password = new String(passwordText.getPassword());

		if (username.equals("") || password.equals("")) {
			loginButton.setEnabled(false);
			JLabel errorMsg = new JLabel(
					"Please enter a username and password to login");
			JOptionPane.showMessageDialog(null, errorMsg);
			usernameText.setText("");
			passwordText.setText("");
			loginButton.setEnabled(true);
			loginFrame.setVisible(true);
		} else {
			if(db.isUserExists(username, password)) {
				Constants.USERNAME = username;
				loginFrame.setVisible(false);
				new GameMaker();
			}
			else {
				loginButton.setEnabled(false);
				JLabel errorMsg = new JLabel(
						"Invalid username/password combination");
				JOptionPane.showMessageDialog(null, errorMsg);
				usernameText.setText("");
				passwordText.setText("");
				loginButton.setEnabled(true);
				loginFrame.setVisible(true);
			}		
		}
	}
}
