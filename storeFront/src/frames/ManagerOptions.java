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
import java.sql.SQLException;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class ManagerOptions extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtClose;
	private JTextField txtEnterWastage;
	private JTextField txtUpdateStock;
	private JTextField txtRegisterUser;


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
		
		txtClose = new JTextField();		
		txtClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame Dashboard = null;
				try {
					Dashboard = new Dashboard();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Dashboard.setVisible(true);
			}
		});
		txtClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtClose.setEditable(false);
		txtClose.setBackground(new Color(105, 105, 105));
		txtClose.setForeground(new Color(255, 255, 255));
		txtClose.setHorizontalAlignment(SwingConstants.CENTER);
		txtClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtClose.setText("CLOSE");
		txtClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtClose.setBounds(447, 306, 172, 36);
		contentPane.add(txtClose);
		txtClose.setColumns(10);
		
		txtEnterWastage = new JTextField();
		/*txtEnterWastage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame RecordWastage = new RecordWastage();
				RecordWastage.setVisible(true);				
			}
		});*/
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
				Stock Stock = new Stock();
				Stock.setVisible(true);
			}
		});
		txtUpdateStock.setText("VIEW STOCK LIST");
		txtUpdateStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Stock Stock = new Stock();
				Stock.setVisible(true);
			}
		});
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