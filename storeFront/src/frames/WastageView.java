package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import DBConn.DbUtils;
import DBConn.DbConn;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class WastageView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable wastageTable;


	/**
	 * Create the application.
	 */
	
	public void populateTable() {
		
		ResultSet rs = DbConn.connectToDB("SELECT STOCK.PRODUCT_NAME AS 'PRODUCT', WASTAGE.WASTED_QUANTITY AS 'Quantity', WASTAGE.REASON AS 'Reason', WASTAGE.ADDITIONAL_INFORMATION AS 'Comments' FROM WASTAGE "
				+ "INNER JOIN STOCK ON WASTAGE.PRODUCT_ID = STOCK.PRODUCT_ID");
		try {
			while(rs.next()) {
				wastageTable.setModel(DbUtils.resultSetToTableModel(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public WastageView() {
		initialize();
		populateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 493);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        // Move the window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 624, 345);
		contentPane.add(scrollPane);
		
		wastageTable = new JTable();
		scrollPane.setViewportView(wastageTable);
		
		JTextField txtClose = new JTextField("CLOSE");
		txtClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtClose.setEditable(false);
		txtClose.setBackground(new Color(105, 105, 105));
		txtClose.setForeground(new Color(255, 255, 255));
		txtClose.setHorizontalAlignment(SwingConstants.CENTER);
		txtClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				dispose();
				
			}
		});
		txtClose.setBounds(545, 459, 89, 23);
		contentPane.add(txtClose);
		
		
		JTextField txtClear = new JTextField("CLEAR LIST");
		txtClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtClear.setEditable(false);
		txtClear.setBackground(new Color(105, 105, 105));
		txtClear.setForeground(new Color(255, 255, 255));
		txtClear.setHorizontalAlignment(SwingConstants.CENTER);
		txtClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtClear.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DbConn.executeSQL("TRUNCATE TABLE WASTAGE");
				
				
				System.out.println("Wastage List Cleared!");
				populateTable();
				
			}
		});
		txtClear.setBounds(436, 459, 99, 23);
		contentPane.add(txtClear);
		
		JLabel subHeaderWastage = new JLabel("CURRENT WASTAGE");
		subHeaderWastage.setHorizontalAlignment(SwingConstants.CENTER);
		subHeaderWastage.setForeground(Color.WHITE);
		subHeaderWastage.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		subHeaderWastage.setBounds(216, 56, 179, 36);
		contentPane.add(subHeaderWastage);
	}
}
