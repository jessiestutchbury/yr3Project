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
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Dashboard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtMgrOptions;
	private JTextField txtLogOut;
	private JTextField txtPayNow;


	/**
	 * Create the frame.
	 */
	public Dashboard() {
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(194, 41, 425, 254);
		tabbedPane.setBackground(new Color(105, 105, 105));
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(105, 105, 105));
		tabbedPane.addTab("Food", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(23, 24, 89, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(105, 105, 105));
		tabbedPane.addTab("Drink", null, panel_1, null);
		
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
		txtMgrOptions.setText("MANAGER OPTIONS");
		txtMgrOptions.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtMgrOptions.setBounds(303, 306, 172, 36);
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
		txtPayNow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame pay = new Pay();
				pay.setVisible(true);
			}
		});
		txtPayNow.setText("PAY NOW");
		txtPayNow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtPayNow.setHorizontalAlignment(SwingConstants.CENTER);
		txtPayNow.setForeground(Color.WHITE);
		txtPayNow.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtPayNow.setEditable(false);
		txtPayNow.setColumns(10);
		txtPayNow.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtPayNow.setBackground(SystemColor.controlDkShadow);
		txtPayNow.setBounds(485, 306, 134, 36);
		contentPane.add(txtPayNow);
		
		JLabel lblNewLabel = new JLabel("CURRENTLY SELECTED");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 41, 134, 14);
		contentPane.add(lblNewLabel);
	}
}
