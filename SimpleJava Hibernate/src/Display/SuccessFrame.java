package Display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SuccessFrame extends JFrame {

	private JPanel contentPane;
	private String messege = "Default Messege";
	private int w = 300;
	private int h = 200;
	/**
	 * Launch the application.
	 */
	private Color successBackgroung = LoginFrame.successBackground;
	private Color successForground = LoginFrame.whiteForground;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SuccessFrame frame = new SuccessFrame("Successgul login");
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public SuccessFrame(String messege) {
		this.messege = messege;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) screenSize.getWidth() / 2 - w / 2;
		int y = (int) screenSize.getHeight() / 2 - h / 2;
		setBounds(x, y, w, h);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(successBackgroung);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel messegeLabel = new JLabel(this.messege, SwingConstants.CENTER);
		messegeLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		messegeLabel.setBounds(10, 11, 264, 139);
		messegeLabel.setForeground(successForground);
		panel.add(messegeLabel);
	}

}
