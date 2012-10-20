package main.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import main.controller.GameMaker;

public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables declaration
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameText;
	private JPasswordField passwordText;
	private JButton loginButton;
	private JPanel contentPane;

	// End of variables declaration

	public static void main(String[] args) {
		new Login();
	}

	public Login() {
		super();
		create();
		this.setVisible(true);
	}

	private void create() {
		usernameLabel = new JLabel();
		passwordLabel = new JLabel();
		usernameText = new JTextField();
		passwordText = new JPasswordField();
		loginButton = new JButton();
		contentPane = (JPanel) this.getContentPane();
		//
		// jLabel1
		//
		usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		usernameLabel.setForeground(new Color(0, 0, 255));
		usernameLabel.setText("username:");
		//
		// jLabel2
		//
		passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordLabel.setForeground(new Color(0, 0, 255));
		passwordLabel.setText("password:");
		//
		// jTextField1
		//
		usernameText.setForeground(new Color(0, 0, 255));
		usernameText.setSelectedTextColor(new Color(0, 0, 255));
		usernameText.setToolTipText("Enter your username");
		usernameText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextField1_actionPerformed(e);
			}

		});
		//
		// jPasswordField1
		//
		passwordText.setForeground(new Color(0, 0, 255));
		passwordText.setToolTipText("Enter your password");
		passwordText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jPasswordField1_actionPerformed(e);
			}

		});
		//
		// jButton1
		//
		loginButton.setBackground(new Color(204, 204, 204));
		loginButton.setForeground(new Color(0, 0, 255));
		loginButton.setText("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton1_actionPerformed(e);
			}

		});
		//
		// contentPane
		//
		contentPane.setLayout(null);
		contentPane.setBorder(BorderFactory.createEtchedBorder());
		contentPane.setBackground(new Color(204, 204, 204));
		addComponent(contentPane, usernameLabel, 5, 10, 106, 18);
		addComponent(contentPane, passwordLabel, 5, 47, 97, 18);
		addComponent(contentPane, usernameText, 110, 10, 183, 22);
		addComponent(contentPane, passwordText, 110, 45, 183, 22);
		addComponent(contentPane, loginButton, 150, 75, 83, 28);
		//
		// login
		//
		this.setTitle("Login");
		this.setLocation(new Point(76, 182));
		this.setSize(new Dimension(335, 141));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(JPanel contentPane2, Component c, int x, int y,
			int width, int height) {
		c.setBounds(x, y, width, height);
		contentPane2.add(c);
	}

	private void jTextField1_actionPerformed(ActionEvent e) {

	}

	private void jPasswordField1_actionPerformed(ActionEvent e) {

	}

	private void jButton1_actionPerformed(ActionEvent e) {
		String username = new String(usernameText.getText());
		String password = new String(passwordText.getPassword());

		if (username.equals("") || password.equals("")) // If password and
														// username is empty >
														// Do this >>>
		{
			loginButton.setEnabled(false);
			JLabel errorFields = new JLabel(
					"You must enter a username and password to login");
			JOptionPane.showMessageDialog(null, errorFields);
			usernameText.setText("");
			passwordText.setText("");
			loginButton.setEnabled(true);
			this.setVisible(true);
		} else {
			JLabel optionLabel = new JLabel(
					"<HTML>You entered"
							+ username
							+ "as your username.<BR> Is this correct?</HTML>");
			int confirm = JOptionPane.showConfirmDialog(null, optionLabel);
			switch (confirm) { // Switch > Case
			case JOptionPane.YES_OPTION: // Attempt to Login user
				loginButton.setEnabled(false); // Set button enable to false to
											// prevent 2 login attempts
				break;
			case JOptionPane.NO_OPTION: // No Case.(Go back. Set text to 0)
				loginButton.setEnabled(false);
				usernameText.setText("");
				passwordText.setText("");
				loginButton.setEnabled(true);
				break;

			case JOptionPane.CANCEL_OPTION: // Cancel Case.(Go back. Set text to
											// 0)
				loginButton.setEnabled(false);
				usernameText.setText("");
				passwordText.setText("");
				loginButton.setEnabled(true);
				break;

			} // End Switch > Case

		}
		new GameMaker();
	}
}