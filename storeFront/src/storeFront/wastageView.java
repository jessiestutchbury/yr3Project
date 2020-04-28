package storeFront;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import DBConn.DbUtils;
import DBConn.dbConn;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class wastageView {

	JFrame frame;
	private JTable wastageTable;
	private JButton btnClearWastage;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					wastageView window = new wastageView();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	
	public void populateTable() {
		
		ResultSet rs = dbConn.connectToDB("SELECT STOCK.PRODUCT_NAME AS 'PRODUCT', WASTAGE.WASTED_QUANTITY AS 'Quantity', WASTAGE.REASON AS 'Reason', WASTAGE.ADDITIONAL_INFORMATION AS 'Comments' FROM WASTAGE "
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
	
	public wastageView() {
		initialize();
		populateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 624, 402);
		frame.getContentPane().add(scrollPane);
		
		wastageTable = new JTable();
		scrollPane.setViewportView(wastageTable);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			}
		});
		btnClose.setBounds(185, 424, 89, 23);
		frame.getContentPane().add(btnClose);
		
		btnClearWastage = new JButton("Clear List");
		btnClearWastage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dbConn.executeSQL("TRUNCATE TABLE WASTAGE");
				
				
				System.out.println("Wastage List Cleared!");
				populateTable();
				
			}
		});
		btnClearWastage.setBounds(303, 424, 89, 23);
		frame.getContentPane().add(btnClearWastage);
	}
}
