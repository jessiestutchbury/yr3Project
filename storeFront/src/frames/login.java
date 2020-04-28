package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class login {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 488, 165);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(187, 74, 162, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lbl_username = new JLabel("Username:");
		lbl_username.setBounds(112, 52, 62, 14);
		frame.getContentPane().add(lbl_username);
		
		JLabel lbl_password = new JLabel("Password:");
		lbl_password.setBounds(115, 80, 75, 14);
		frame.getContentPane().add(lbl_password);
		
		textField = new JTextField();
		textField.setBounds(187, 46, 162, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lbl_loginprompt = new JLabel("Please enter your user credentials below:");
		lbl_loginprompt.setBounds(128, 11, 205, 14);
		frame.getContentPane().add(lbl_loginprompt);
		
		
	}
}
