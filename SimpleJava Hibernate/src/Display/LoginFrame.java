package Display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tre.User;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JPanel contentPane;
	static Point compCoords;
	static LoginFrame frame;

	public static Color screenBackground = new Color(33, 33, 33);
	public static Color labelForground = new Color(0, 145, 234);
	public static Color fieldForground = new Color(225, 245, 254);
	public static Color buttonsBackground = new Color(55, 71, 79);
	public static Color chatBackground = new Color(38, 50, 56);
	public static Color chatForground = new Color(236, 239, 241);
	public static Color exitColor = new Color(173, 20, 87);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		this.setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = 470, h = 300;
		int x = screenSize.width / 2 - w / 2, y = screenSize.height / 2 - h / 2;
		setBounds(x, y, w, h);

		contentPane = new LoginPanel(this, w, h);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		MouseListener mouseWindowPressed = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				compCoords = null;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				compCoords = e.getPoint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		};
		MouseMotionListener mouseWindowDragged = new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				frame.setLocation(currCoords.x - compCoords.x, currCoords.y - compCoords.y);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}
		};
		this.addMouseListener(mouseWindowPressed);
		this.addMouseMotionListener(mouseWindowDragged);
	}
	
	public void openChat(User user, int port){
		frame.dispose();
		ChatFrame chat = new ChatFrame(user);
		chat.setVisible(true);
	}

}
