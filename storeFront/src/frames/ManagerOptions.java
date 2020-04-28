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
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class ManagerOptions extends JFrame {

	private JPanel contentPane;
	private JTextField txtMgrOptions;
	private JTextField txtEnterWastage;
	private JTextField txtUpdateStock;
	private JTextField txtRegisterUser;

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
	public ManagerOptions() {
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
		
		txtMgrOptions = new JTextField();		
		txtMgrOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame dashboard = new Dashboard();
				dashboard.setVisible(true);
			}
		});
		txtMgrOptions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtMgrOptions.setEditable(false);
		txtMgrOptions.setBackground(new Color(105, 105, 105));
		txtMgrOptions.setForeground(new Color(255, 255, 255));
		txtMgrOptions.setHorizontalAlignment(SwingConstants.CENTER);
		txtMgrOptions.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtMgrOptions.setText("CLOSE");
		txtMgrOptions.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtMgrOptions.setBounds(447, 306, 172, 36);
		contentPane.add(txtMgrOptions);
		txtMgrOptions.setColumns(10);
		
		txtEnterWastage = new JTextField();
		txtEnterWastage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame recordWastage = new RecordWastage();
				recordWastage.setVisible(true);				
			}
		});
		txtEnterWastage.setText("ENTER WASTAGE");
		txtEnterWastage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtEnterWastage.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterWastage.setForeground(Color.WHITE);
		txtEnterWastage.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtEnterWastage.setEditable(false);
		txtEnterWastage.setColumns(10);
		txtEnterWastage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtEnterWastage.setBackground(SystemColor.controlDkShadow);
		txtEnterWastage.setBounds(30, 58, 172, 36);
		contentPane.add(txtEnterWastage);
		
		txtUpdateStock = new JTextField();
		txtUpdateStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame editStockInfo = new EditStockInfo();
				editStockInfo.setVisible(true);
			}
		});
		txtUpdateStock.setText("UPDATE STOCK");
		txtUpdateStock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtUpdateStock.setHorizontalAlignment(SwingConstants.CENTER);
		txtUpdateStock.setForeground(Color.WHITE);
		txtUpdateStock.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtUpdateStock.setEditable(false);
		txtUpdateStock.setColumns(10);
		txtUpdateStock.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtUpdateStock.setBackground(SystemColor.controlDkShadow);
		txtUpdateStock.setBounds(226, 57, 172, 36);
		contentPane.add(txtUpdateStock);
		
		txtRegisterUser = new JTextField();
		txtRegisterUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame register = new Register();
				register.setVisible(true);
			}
		});
		txtRegisterUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtRegisterUser.setText("REGISTER USER");
		txtRegisterUser.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegisterUser.setForeground(Color.WHITE);
		txtRegisterUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtRegisterUser.setEditable(false);
		txtRegisterUser.setColumns(10);
		txtRegisterUser.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtRegisterUser.setBackground(SystemColor.controlDkShadow);
		txtRegisterUser.setBounds(434, 57, 172, 36);
		contentPane.add(txtRegisterUser);
	}
}