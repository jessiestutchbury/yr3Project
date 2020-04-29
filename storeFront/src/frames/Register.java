package frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Register extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel subHeader;
	private JTextField txtForename;
	private JTextField txtSurname;
	private JTextField txtAddress;
	private JTextField txtEmail;
	private JTextField txtTelephone;
	private JTextField txtLevel;
	private JTextField txtRegister;
	private JTextField txtId;
	private JTextField txtClose;

	/**
	 * Create the frame.
	 */
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(320, 180, 629, 356);
		contentPane = new JPanel();
		contentPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
        // Move the window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        this.setLocation(x, y);
		
		JLabel header = new JLabel("SPYROU & SONS");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setForeground(new Color(255, 255, 255));
		header.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		header.setBounds(0, 0, 156, 36);
		contentPane.add(header);
		
		subHeader = new JLabel("REGISTER NEW EMPLOYEE");
		subHeader.setHorizontalAlignment(SwingConstants.CENTER);
		subHeader.setForeground(Color.WHITE);
		subHeader.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		subHeader.setBounds(156, 33, 316, 45);
		contentPane.add(subHeader);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setAlignmentX(Component.RIGHT_ALIGNMENT);
		separator.setBounds(0, 33, 629, 14);
		contentPane.add(separator);
		
		txtForename = new JTextField();
		txtForename.setText("FORENAME");
		txtForename.setBackground(new Color(105, 105, 105));
		txtForename.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));;
		txtForename.setBounds(71, 108, 187, 20);
		contentPane.add(txtForename);
		txtForename.setColumns(10);

		txtSurname = new JTextField();
		txtSurname.setText("SURNAME");
		txtSurname.setBackground(new Color(105, 105, 105));
		txtSurname.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));;
		txtSurname.setColumns(10);
		txtSurname.setBounds(71, 159, 187, 20);
		contentPane.add(txtSurname);
		
		txtAddress = new JTextField();
		txtAddress.setText("ADDRESS");
		txtAddress.setBackground(new Color(105, 105, 105));
		txtAddress.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));;
		txtAddress.setColumns(10);
		txtAddress.setBounds(71, 214, 187, 20);
		contentPane.add(txtAddress);
		
		txtEmail = new JTextField();
		txtEmail.setText("EMAIL");
		txtEmail.setBackground(new Color(105, 105, 105));
		txtEmail.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));;
		txtEmail.setColumns(10);
		txtEmail.setBounds(378, 108, 187, 20);
		contentPane.add(txtEmail);
		
		txtTelephone = new JTextField();
		txtTelephone.setText("TELEPHONE");
		txtTelephone.setBackground(new Color(105, 105, 105));
		txtTelephone.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));;
		txtTelephone.setColumns(10);
		txtTelephone.setBounds(378, 159, 187, 20);
		contentPane.add(txtTelephone);
		
		txtLevel = new JTextField();
		txtLevel.setText("CLEARANCE LEVEL");
		txtLevel.setBackground(new Color(105, 105, 105));
		txtLevel.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));;
		txtLevel.setColumns(10);
		txtLevel.setBounds(378, 214, 187, 20);
		contentPane.add(txtLevel);
		
		txtRegister = new JTextField();
		txtRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtRegister.setEditable(false);
		txtRegister.setBackground(new Color(105, 105, 105));
		txtRegister.setForeground(new Color(255, 255, 255));
		txtRegister.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegister.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtRegister.setText("REGISTER");
		txtRegister.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtRegister.setBounds(228, 287, 165, 36);
		contentPane.add(txtRegister);
		txtRegister.setColumns(10);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setText("ID");
		txtId.setColumns(10);
		txtId.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));
		txtId.setBackground(SystemColor.controlDkShadow);
		txtId.setBounds(71, 70, 35, 20);
		contentPane.add(txtId);
		
		txtClose = new JTextField();
		txtClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame managerOptions = new ManagerOptions();
				managerOptions.setVisible(true);
			}
		});
		txtClose.setText("CLOSE");
		txtClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtClose.setHorizontalAlignment(SwingConstants.CENTER);
		txtClose.setForeground(Color.WHITE);
		txtClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtClose.setEditable(false);
		txtClose.setColumns(10);
		txtClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtClose.setBackground(SystemColor.controlDkShadow);
		txtClose.setBounds(429, 287, 165, 36);
		contentPane.add(txtClose);
	}
}