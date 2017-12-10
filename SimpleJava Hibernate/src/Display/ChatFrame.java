package Display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import client.ClientComunicator;
import tre.Message;
import tre.User;

public class ChatFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static ChatFrame frame;

	private ClientComunicator clientComunicator;

	private JPanel contentPane;
	private User connectedUser;

	private Color screenBackground = LoginFrame.screenBackground;
	private Color labelForground = LoginFrame.labelForground;
	private Color buttonsBackground = LoginFrame.buttonsBackground;
	private Color chatBackground = LoginFrame.chatBackground;
	private Color chatForground = LoginFrame.chatForground;
	private Color disconnectColor = LoginFrame.exitColor;
	private Color selfNameColorFont = LoginFrame.selfNameForground;
	private Color otherNameColorFont = LoginFrame.otherNameForground;
	private Color messegeColorFont = LoginFrame.whiteForground;
	private Color dateColorForground = LoginFrame.dateForground;

	private JButton sendButton;
	private JTextArea newMessageArea;
	private JTextPane dialogArea;

	private SimpleAttributeSet selfNameFont = new SimpleAttributeSet();
	private SimpleAttributeSet otherNameFont = new SimpleAttributeSet();
	private SimpleAttributeSet messageFont = new SimpleAttributeSet();
	private SimpleAttributeSet dateFont = new SimpleAttributeSet();

	/**
	 * Create the frame.
	 */

	public ChatFrame(User user, ClientComunicator comunicator) {
		this.connectedUser = user;
		this.clientComunicator = comunicator;

		StyleConstants.setFontFamily(selfNameFont, "Lemon");
		StyleConstants.setForeground(selfNameFont, selfNameColorFont);

		StyleConstants.setFontFamily(otherNameFont, "Lemon");
		StyleConstants.setForeground(otherNameFont, otherNameColorFont);

		StyleConstants.setFontFamily(dateFont, "Lemon");
		StyleConstants.setFontSize(dateFont, 9);
		StyleConstants.setForeground(dateFont, dateColorForground);

		StyleConstants.setFontFamily(messageFont, "Microsoft JhengHei UI");
		StyleConstants.setForeground(messageFont, messegeColorFont);

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

		dialogArea = new JTextPane();
		dialogArea.setBackground(new Color(38, 50, 56));
		dialogArea.setForeground(chatForground);
		dialogArea.setEditable(false);
		dialogArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		dialogArea.setBounds(10, 54, 614, 482);
		JScrollPane dialogScroll = new JScrollPane(dialogArea);
		dialogScroll.setBounds(10, 54, 614, 482);
		panel.add(dialogScroll);

		newMessageArea = new JTextArea();
		newMessageArea.setBackground(chatBackground);
		newMessageArea.setForeground(chatForground);
		newMessageArea.setCaretColor(chatForground);
		newMessageArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		newMessageArea.setText("sample text");
		newMessageArea.setBounds(10, 547, 538, 52);
		newMessageArea.setWrapStyleWord(true);
		newMessageArea.setLineWrap(true);
		JScrollPane newMessageScroll = new JScrollPane(newMessageArea);
		newMessageScroll.setBounds(10, 547, 538, 52);
		panel.add(newMessageScroll);

		JLabel loggedInAsLabel = new JLabel("Logged in as:");
		loggedInAsLabel.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		loggedInAsLabel.setForeground(labelForground);
		loggedInAsLabel.setBounds(10, 11, 73, 14);
		panel.add(loggedInAsLabel);

		JLabel firstLastNameLabel = new JLabel(
				this.connectedUser.getFirstName() + " " + this.connectedUser.getLastName());
		firstLastNameLabel.setFont(new Font("Calibri Light", Font.BOLD, 14));
		firstLastNameLabel.setForeground(labelForground);
		firstLastNameLabel.setBounds(10, 29, 321, 14);
		panel.add(firstLastNameLabel);

		sendButton = new JButton("send");
		sendButton.setBackground(buttonsBackground);
		sendButton.setForeground(labelForground);
		sendButton.setBounds(558, 547, 66, 52);
		sendButton.setBorder(null);
		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String content = newMessageArea.getText();
				clientComunicator.sendMessage(connectedUser, content);
			}
		});
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
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				disconnect();
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	public void writeSelfMessegeToDialog(Message message) {
		// String senderName = connectedUser.getFirstName() + ": ";
		String senderName = "You: ";
		message.setMessage(message.getMessage() + "\n");
		// Add some text
		try {
			dialogArea.getDocument().insertString(dialogArea.getDocument().getEndPosition().getOffset(), senderName,
					selfNameFont);
			
			dialogArea.getDocument().insertString(dialogArea.getDocument().getEndPosition().getOffset(),
					message.getMessage(), messageFont);
			
			dialogArea.getDocument().insertString(dialogArea.getDocument().getEndPosition().getOffset(),
					message.getDate().toString() + "\n\n", dateFont);
			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeOtherMessegeToDialog(User otherUser, Message message) {
		String senderName = otherUser.getFirstName() + ": ";
		message.setMessage(message.getMessage() + "\n");
		// Add some text
		try {
			dialogArea.getDocument().insertString(dialogArea.getDocument().getEndPosition().getOffset(), senderName,
					otherNameFont);
			
			dialogArea.getDocument().insertString(dialogArea.getDocument().getEndPosition().getOffset(),
					message.getMessage(), messageFont);
			
			dialogArea.getDocument().insertString(dialogArea.getDocument().getEndPosition().getOffset(),
					message.getDate().toString() + "\n\n", dateFont);

		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getConnectedUserId() {
		return connectedUser.getUserid();
	}

	public void disconnect() {
		clientComunicator.disconnect();
		Run.chat.dispose();
		Run.login = new LoginFrame(Run.client);
		Run.login.setVisible(true);
	}
}
