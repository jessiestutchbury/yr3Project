package frames;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Pay extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtMgrOptions;
	private JTextField txtLogOut;
	private JTextField txtPayNow;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtClose;

	/**
	 * Create the frame.
	 */
	public Pay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(320, 700, 629, 356);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;

        // Move the window
        this.setLocation(x, y);
		
		JLabel header = new JLabel("SPYROU & SONS");
		header.setBounds(0, 0, 153, 36);
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setForeground(new Color(255, 255, 255));
		header.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		contentPane.add(header);
		setUndecorated(true); //removes frame outline
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 32, 629, 14);
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setAlignmentX(Component.RIGHT_ALIGNMENT);
		contentPane.add(separator);
		
		textField = new JTextField();
		textField.setBounds(10, 66, 172, 276);
		textField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField.setBackground(new Color(105, 105, 105));
		textField.setEditable(false);
		contentPane.add(textField);
		textField.setColumns(10);
		
		txtMgrOptions = new JTextField();		
		txtMgrOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame managerOptions = new ManagerOptions();
				managerOptions.setVisible(true);
			}
		});
		txtMgrOptions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtMgrOptions.setEditable(false);
		txtMgrOptions.setBackground(new Color(105, 105, 105));
		txtMgrOptions.setForeground(new Color(255, 255, 255));
		txtMgrOptions.setHorizontalAlignment(SwingConstants.CENTER);
		txtMgrOptions.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtMgrOptions.setText("ELP PAYMENT");
		txtMgrOptions.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtMgrOptions.setBounds(221, 158, 172, 80);
		contentPane.add(txtMgrOptions);
		txtMgrOptions.setColumns(10);
		
		txtLogOut = new JTextField();
		txtLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame login = new Login();
				login.setVisible(true);
			}
		});
		txtLogOut.setText("LOG OUT");
		txtLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogOut.setForeground(Color.WHITE);
		txtLogOut.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtLogOut.setEditable(false);
		txtLogOut.setColumns(10);
		txtLogOut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtLogOut.setBackground(SystemColor.controlDkShadow);
		txtLogOut.setBounds(514, 0, 115, 33);
		contentPane.add(txtLogOut);
		
		txtPayNow = new JTextField();
		txtPayNow.setText("CASH");
		txtPayNow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtPayNow.setHorizontalAlignment(SwingConstants.CENTER);
		txtPayNow.setForeground(Color.WHITE);
		txtPayNow.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtPayNow.setEditable(false);
		txtPayNow.setColumns(10);
		txtPayNow.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtPayNow.setBackground(SystemColor.controlDkShadow);
		txtPayNow.setBounds(221, 66, 172, 80);
		contentPane.add(txtPayNow);
		
		JLabel lblNewLabel = new JLabel("CURRENTLY SELECTED");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 41, 134, 14);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		textField_1.setText("\u00A35");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.WHITE);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_1.setBackground(SystemColor.controlDkShadow);
		textField_1.setBounds(416, 66, 88, 33);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		textField_2.setText("\u00A310");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(Color.WHITE);
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_2.setBackground(SystemColor.controlDkShadow);
		textField_2.setBounds(514, 66, 88, 33);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		textField_3.setText("\u00A320");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setForeground(Color.WHITE);
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_3.setBackground(SystemColor.controlDkShadow);
		textField_3.setBounds(416, 111, 186, 33);
		contentPane.add(textField_3);
		
		txtClose = new JTextField();
		txtClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame dashboard = new Dashboard();
				dashboard.setVisible(true);
			}
		});
		txtClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtClose.setText("CLOSE");
		txtClose.setHorizontalAlignment(SwingConstants.CENTER);
		txtClose.setForeground(Color.WHITE);
		txtClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtClose.setEditable(false);
		txtClose.setColumns(10);
		txtClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtClose.setBackground(SystemColor.controlDkShadow);
		txtClose.setBounds(468, 290, 134, 52);
		contentPane.add(txtClose);
	}
}