package frames;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JTextField txtLogin;
	private JLabel lblClose;
	private JLabel userIcon;
	private JLabel lblAlert;
	private JLabel passIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(320, 180, 629, 356);
		contentPane = new JPanel();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;

        // Move the window
        this.setLocation(x, y);
		
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setRequestFocusEnabled(false);
		txtUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtUsername.setText("");
			}
		});
		txtUsername.setText("USERNAME");
		txtUsername.setCaretColor(new Color(0, 0, 0));
		txtUsername.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));
		txtUsername.setBackground(new Color(105, 105, 105));
		txtUsername.setBounds(184, 112, 270, 30);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdPassword.setText("");
			}
		});
		pwdPassword.setText("PASWORD");
		pwdPassword.setBackground(new Color(105, 105, 105));
		pwdPassword.setCaretColor(new Color(0, 0, 0));
		pwdPassword.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));
		pwdPassword.setBounds(184, 173, 270, 30);
		contentPane.add(pwdPassword);
		
		txtLogin = new JTextField();		
		txtLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame dashboard = new Dashboard();
				dashboard.setVisible(true);
			}
		});
		txtLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtLogin.setEditable(false);
		txtLogin.setBackground(new Color(105, 105, 105));
		txtLogin.setForeground(new Color(255, 255, 255));
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtLogin.setText("LOGIN");
		txtLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtLogin.setBounds(231, 257, 165, 36);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		lblClose = new JLabel();
		lblClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblClose.setForeground(new Color(255, 255, 255));
		lblClose.setBackground(new Color(105, 105, 105));
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblClose.setText("X");
		lblClose.setBorder(null);
		lblClose.setBounds(599, 0, 30, 36);
		contentPane.add(lblClose);
		
		JLabel header = new JLabel("SPYROU & SONS");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setForeground(new Color(255, 255, 255));
		header.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		header.setBounds(154, 45, 316, 45);
		contentPane.add(header);
		
		userIcon = new JLabel("");
		userIcon.setIcon(new ImageIcon(Login.class.getResource("/account.png")));
		userIcon.setBounds(147, 94, 43, 68);
		contentPane.add(userIcon);
		
		lblAlert = new JLabel("Incorrect username or password!");
		lblAlert.setIcon(new ImageIcon(Login.class.getResource("/alert.png")));
		lblAlert.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAlert.setForeground(new Color(250, 128, 114));
		lblAlert.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlert.setBounds(147, 220, 323, 26);		
		contentPane.add(lblAlert);
		lblAlert.setVisible(false);
		
		
		passIcon = new JLabel("");
		passIcon.setIcon(new ImageIcon(Login.class.getResource("/lock.png")));
		passIcon.setBounds(147, 153, 48, 68);
		contentPane.add(passIcon);
		setUndecorated(true); //removes frame outline
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setAlignmentX(Component.RIGHT_ALIGNMENT);
		separator.setBounds(231, 87, 162, 14);
		contentPane.add(separator);
	}
	
	                                                                
}
