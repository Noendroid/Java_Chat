package Display;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class RegisterPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Point coords;
	private JTextField userNameField;
	private JTextField userFirstNameField;
	private JTextField userLastNameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField confirmPassField;

	private LoginFrame container;
	private int width;
	private int height;

	private Color screenBackground = LoginFrame.screenBackground;
	private Color labelForground = LoginFrame.labelForground;
	private Color fieldForground = LoginFrame.fieldForground;
	private Color buttonsBackground = LoginFrame.buttonsBackground;
	private Color alertColor = LoginFrame.alertColor;

	private int labelX = 10, labelY = 19, labelWidth = 217, labelHeight = 20, labelSpacing = 25;

	/**
	 * Create the panel.
	 */
	public RegisterPanel(LoginFrame container, int w, int h) {
		this.container = container;
		this.width = w;
		this.height = h;
		this.setBounds(0, 0, width, height);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		int itemCount = 0;

		JPanel register_screen = new JPanel();
		register_screen.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		register_screen.setBounds(0, 0, width, height);
		register_screen.setBackground(screenBackground);
		this.add(register_screen);
		register_screen.setLayout(null);
		// user name
		JLabel userNameLabel = new JLabel("User name");
		userNameLabel.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		userNameLabel.setForeground(labelForground);
		userNameLabel.setBounds(labelX, labelY + (labelSpacing * itemCount), labelWidth, labelHeight);
		register_screen.add(userNameLabel);
		itemCount++;

		userNameField = new JTextField();
		userNameField.setToolTipText("user name");
		userNameField
				.setBorder(new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null, Color.WHITE));
		userNameField.setBounds(labelX, labelY + (labelSpacing * itemCount), labelWidth, labelHeight);
		userNameField.setBackground(screenBackground);
		userNameField.setForeground(fieldForground);
		register_screen.add(userNameField);
		userNameField.setColumns(10);
		itemCount++;

		// first name and Last name
		// labels
		JLabel userFirstNameLabel = new JLabel("First name");
		userFirstNameLabel.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		userFirstNameLabel.setForeground(labelForground);
		userFirstNameLabel.setBounds(labelX, labelY + (labelSpacing * itemCount), labelWidth, labelHeight);
		register_screen.add(userFirstNameLabel);

		JLabel userLastNameLabel = new JLabel("Last name");
		userLastNameLabel.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		userLastNameLabel.setForeground(labelForground);
		userLastNameLabel.setBounds(labelX + labelWidth + 10, labelY + (labelSpacing * itemCount), labelWidth,
				labelHeight);
		register_screen.add(userLastNameLabel);
		itemCount++;

		// fields
		userFirstNameField = new JTextField();
		userFirstNameField.setToolTipText("user name");
		userFirstNameField
				.setBorder(new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null, Color.WHITE));
		userFirstNameField.setBounds(labelX, labelY + (labelSpacing * itemCount), labelWidth, labelHeight);
		userFirstNameField.setBackground(screenBackground);
		userFirstNameField.setForeground(fieldForground);
		register_screen.add(userFirstNameField);
		userNameField.setColumns(10);

		userLastNameField = new JTextField();
		userLastNameField.setToolTipText("user name");
		userLastNameField
				.setBorder(new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null, Color.WHITE));
		userLastNameField.setBounds(labelX + labelWidth + 10, labelY + (labelSpacing * itemCount), labelWidth,
				labelHeight);
		userLastNameField.setBackground(screenBackground);
		userLastNameField.setForeground(fieldForground);
		register_screen.add(userLastNameField);
		userNameField.setColumns(10);
		itemCount++;

		// email
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		emailLabel.setForeground(new Color(0, 145, 234));
		emailLabel.setBounds(labelX, labelY + (labelSpacing * itemCount), labelWidth, labelHeight);
		register_screen.add(emailLabel);
		itemCount++;

		emailField = new JTextField();
		emailField.setToolTipText("user name");
		emailField.setForeground(new Color(225, 245, 254));
		emailField.setColumns(10);
		emailField
				.setBorder(new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null, Color.WHITE));
		emailField.setBackground(new Color(33, 33, 33));
		emailField.setBounds(labelX, labelY + (labelSpacing * itemCount), labelWidth, labelHeight);
		register_screen.add(emailField);
		itemCount++;

		// password and confirm password labels
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(new Color(0, 145, 234));
		passwordLabel.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		passwordLabel.setBounds(labelX, labelY + (labelSpacing * itemCount), labelWidth, labelHeight);
		register_screen.add(passwordLabel);

		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setForeground(new Color(0, 145, 234));
		lblConfirmPassword.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		lblConfirmPassword.setBounds(labelX + labelWidth + 10, labelY + (labelSpacing * itemCount), labelWidth,
				labelHeight);
		register_screen.add(lblConfirmPassword);
		itemCount++;

		// password and confirm password fields
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(225, 245, 254));
		passwordField
				.setBorder(new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null, Color.WHITE));
		passwordField.setBackground(new Color(33, 33, 33));
		passwordField.setBounds(labelX, labelY + (labelSpacing * itemCount), labelWidth, labelHeight);
		register_screen.add(passwordField);

		confirmPassField = new JPasswordField();
		confirmPassField.setForeground(new Color(225, 245, 254));
		confirmPassField
				.setBorder(new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null, Color.WHITE));
		confirmPassField.setBackground(new Color(33, 33, 33));
		confirmPassField.setBounds(labelX + labelWidth + 10, labelY + (labelSpacing * itemCount), labelWidth,
				labelHeight);
		register_screen.add(confirmPassField);
		itemCount++;

		// login button
		JButton registerButton = new JButton("Register");
		registerButton.setBorder(null);
		registerButton.setBounds(351, 266, 89, 23);
		registerButton.setBackground(buttonsBackground);
		registerButton.setForeground(fieldForground);
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// user name
				BevelBorder errorBorder = new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null,
						alertColor);
				BevelBorder fineBorder = new BevelBorder(BevelBorder.RAISED, screenBackground, screenBackground, null,
						Color.WHITE);

				boolean err = false;
				if (userNameField.getText().isEmpty()) {
					userNameField.setBorder(errorBorder);
					err = true;
				} else {
					userNameField.setBorder(fineBorder);
				}
				// first name
				if (userFirstNameField.getText().isEmpty()) {
					userFirstNameField.setBorder(errorBorder);
					err = true;
				} else {
					userFirstNameField.setBorder(fineBorder);
				}
				// last name
				if (userLastNameField.getText().isEmpty()) {
					userLastNameField.setBorder(errorBorder);
					err = true;
				} else {
					userLastNameField.setBorder(fineBorder);
				}
				// email
				if (emailField.getText().isEmpty()) {
					emailField.setBorder(errorBorder);
					err = true;
				} else {
					emailField.setBorder(fineBorder);
				}
				// password
				if (passwordField.getPassword().length == 0) {
					passwordField.setBorder(errorBorder);
					err = true;
				} else {
					passwordField.setBorder(fineBorder);
				}
				// confirm password
				if (confirmPassField.getPassword().length == 0) {
					confirmPassField.setBorder(errorBorder);
					err = true;
				} else {
					confirmPassField.setBorder(fineBorder);
				}
				if (!err) {
					/*
					 * register user check for errors go to login screen
					 */
					JDialog dialog = new JDialog();
					dialog.setUndecorated(true);
					JLabel label = new JLabel("Please wait...");
					dialog.setLocationRelativeTo(null);
					dialog.setBounds(RegisterPanel.this.container.getX() + 100,
							RegisterPanel.this.container.getY() + 100, 100, 50);
					dialog.setTitle("Please Wait...");
					dialog.add(label);
					dialog.setBackground(labelForground);
					dialog.pack();
					dialog.setVisible(true);
					RegisterPanel.this.container
							.setContentPane(new LoginPanel(RegisterPanel.this.container, width, height));
					dialog.dispose();
				}

			}
		});
		register_screen.add(registerButton);
		// register button
		JLabel registerLabel = new JLabel("Changed my mind");
		registerLabel.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		registerLabel.setForeground(new Color(84, 110, 122));
		registerLabel.setBounds(245, 271, 96, 14);
		register_screen.add(registerLabel);
		registerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterPanel.this.container
						.setContentPane(new LoginPanel(RegisterPanel.this.container, width, height));
			}
		});

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
				RegisterPanel.this.container.setState(JFrame.ICONIFIED);
			}
		});
		minimizeLabel.setForeground(new Color(0, 145, 234));
		minimizeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		minimizeLabel.setBounds(width - 60, 0, 26, 33);
		register_screen.add(minimizeLabel);
	}

}
