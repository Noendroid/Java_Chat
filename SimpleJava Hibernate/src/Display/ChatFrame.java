package Display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import tre.User;

public class ChatFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static ChatFrame frame;

	private JPanel contentPane;
	private User connectedUser;

	private Color screenBackground = LoginFrame.screenBackground;
	private Color labelForground = LoginFrame.labelForground;
	private Color buttonsBackground = LoginFrame.buttonsBackground;
	private Color chatBackground = LoginFrame.chatBackground;
	private Color chatForground = LoginFrame.chatForground;
	private Color disconnectColor = LoginFrame.exitColor;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// frame = new ChatFrame(null);
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public ChatFrame(User user) {
		this.connectedUser = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		int w = 650;
		int h = 650;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width / 2 - (w / 2), screenSize.height / 2 - (h / 2), w, h);
		contentPane = new JPanel();
		contentPane.setBackground(screenBackground);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(screenBackground);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JTextArea dialogArea = new JTextArea();
		dialogArea.setBackground(new Color(38, 50, 56));
		dialogArea.setForeground(chatForground);
		dialogArea.setEditable(false);
		dialogArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		dialogArea.setText("sample text");
		dialogArea.setBounds(10, 54, 614, 482);
		panel.add(dialogArea);

		JTextArea newMessegeArea = new JTextArea();
		newMessegeArea.setBackground(chatBackground);
		newMessegeArea.setForeground(chatForground);
		newMessegeArea.setCaretColor(chatForground);
		newMessegeArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		newMessegeArea.setText("sample text");
		newMessegeArea.setBounds(10, 547, 538, 52);
		panel.add(newMessegeArea);

		JLabel loggedInAsLabel = new JLabel("Logged in as:");
		loggedInAsLabel.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		loggedInAsLabel.setForeground(labelForground);
		loggedInAsLabel.setBounds(10, 11, 73, 14);
		panel.add(loggedInAsLabel);

		JLabel firstLastNameLabel = new JLabel("first name and last name");
		firstLastNameLabel.setFont(new Font("Calibri Light", Font.BOLD, 14));
		firstLastNameLabel.setForeground(labelForground);
		firstLastNameLabel.setBounds(10, 29, 321, 14);
		panel.add(firstLastNameLabel);

		JButton sendButton = new JButton("send");
		sendButton.setBackground(buttonsBackground);
		sendButton.setForeground(labelForground);
		sendButton.setBounds(558, 547, 66, 52);
		sendButton.setBorder(null);
		panel.add(sendButton);

		JButton disconnectButton = new JButton("Disconnect");
		disconnectButton.setBackground(disconnectColor);
		disconnectButton.setForeground(chatForground);
		disconnectButton.setBorder(null);
		disconnectButton.setBounds(541, 11, 83, 32);
		disconnectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				disconnect();
			}
		});
		panel.add(disconnectButton);

	}

	public void disconnect() {
		Run.chat.dispose();
		Run.login = new LoginFrame();
		Run.login.setVisible(true);
	}
}
