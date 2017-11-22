package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class LoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static Point compCoords;
	private JTextField userNameField;

	private LoginFrame container;
	private int width;
	private int height;

	private Color screenBackground = LoginFrame.screenBackground;
	private Color labelForground = LoginFrame.labelForground;
	private Color fieldForground = LoginFrame.fieldForground;
	private Color buttonsBackground = LoginFrame.buttonsBackground;
	
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public LoginPanel(LoginFrame container, int w, int h) {
		this.container = container;
		this.width = w;
		this.height = h;
		setBounds(0, 0, width, height);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);

		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, width, height);
		this.add(loginPanel);
		loginPanel.setLayout(null);

		JPanel register_screen = new JPanel();
		register_screen.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		register_screen.setBounds(0, 0, width, height);
		register_screen.setBackground(screenBackground);
		loginPanel.add(register_screen);
		register_screen.setLayout(null);

		// user name
		JLabel userNameLabel = new JLabel("User name");
		userNameLabel.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		userNameLabel.setForeground(labelForground);
		userNameLabel.setBounds(123, 87, 217, 14);
		register_screen.add(userNameLabel);

		userNameField = new JTextField();
		userNameField.setToolTipText("user name");
		userNameField
				.setBorder(new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null, Color.WHITE));
		userNameField.setBounds(123, 112, 217, 20);
		userNameField.setBackground(screenBackground);
		userNameField.setForeground(fieldForground);
		register_screen.add(userNameField);
		userNameField.setColumns(10);

		// password
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		passwordLabel.setForeground(new Color(0, 145, 234));
		passwordLabel.setBounds(123, 143, 217, 14);
		register_screen.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBackground(screenBackground);
		passwordField
				.setBorder(new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null, Color.WHITE));
		passwordField.setForeground(fieldForground);
		passwordField.setBounds(123, 168, 217, 20);
		register_screen.add(passwordField);

		// exit
		JLabel exitLabel = new JLabel("X");
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		exitLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		exitLabel.setBounds(width - 30, 0, 26, 33);
		exitLabel.setForeground(labelForground);
		register_screen.add(exitLabel);

		// minimize
		JLabel minimizeLabel = new JLabel("-");
		minimizeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPanel.this.container.setState(JFrame.ICONIFIED);
			}
		});
		minimizeLabel.setForeground(new Color(0, 145, 234));
		minimizeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		minimizeLabel.setBounds(width - 60, 0, 26, 33);
		register_screen.add(minimizeLabel);

		// login
		JButton loginButton = new JButton("Login");
		loginButton.setBorder(null);
		loginButton.setBounds(251, 199, 89, 23);
		loginButton.setBackground(buttonsBackground);
		loginButton.setForeground(fieldForground);
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean err = false;
				if (userNameField.getText().isEmpty()) {
					userNameField.setBorder(
							new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null, Color.RED));
					err = true;
				} else {
					userNameField.setBorder(
							new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null, Color.WHITE));
				}
				if (passwordField.getPassword().length == 0) {
					passwordField.setBorder(
							new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null, Color.RED));
					err = true;
				} else {
					passwordField.setBorder(
							new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null, Color.WHITE));
				}
				// start the chat
				if(!err){
					/*
					 * connect to server here
					 */
					LoginPanel.this.container.openChat(null, 0);
				}
			}
		});
		register_screen.add(loginButton);

		// register
		JLabel registerLabel = new JLabel("don't have an account?");
		registerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPanel.this.container.setContentPane(new RegisterPanel(LoginPanel.this.container, width, height));
			}
		});
		registerLabel.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		registerLabel.setForeground(new Color(84, 110, 122));
		registerLabel.setBounds(123, 203, 130, 14);
		register_screen.add(registerLabel);

	}

}
