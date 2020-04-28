package storeFront;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import DBConn.dbConn;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class editDeliveryInfo {

	JFrame frame;
	private JTextField orderQuantField;
	private JTextField prodNameField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					editDeliveryInfo window = new editDeliveryInfo();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	public void populate(String productID) {
		
		//int productID = Integer.parseInt(productIDs);

	
		ResultSet rs1 = dbConn.connectToDB("SELECT DELIVERY.PRODUCT_ID, STOCK.PRODUCT_NAME, DELIVERY.QUANTITY_ORDERED FROM DELIVERY" 
				+ " INNER JOIN STOCK ON DELIVERY.PRODUCT_ID = STOCK.PRODUCT_ID WHERE DELIVERY.PRODUCT_ID = " + productID);
		System.out.println(rs1);
		
		
		try {
			while(rs1.next()) {
		String productName = rs1.getString("PRODUCT_NAME");
		String orderQuant = rs1.getString("QUANTITY_ORDERED");
		prodNameField.setText(productName);
		orderQuantField.setText(orderQuant);
					
		}
				
		}
		catch(Exception e) {
			System.out.println(e);
		}
			
	}
	
	
	
	
	/**
	 * Create the application.
	 */
	public editDeliveryInfo(String productID) {
		initialize(productID);
		populate(productID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String productID) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		orderQuantField = new JTextField();
		orderQuantField.setBounds(192, 114, 96, 20);
		frame.getContentPane().add(orderQuantField);
		orderQuantField.setColumns(10);
		
		prodNameField = new JTextField();
		prodNameField.setColumns(10);
		prodNameField.setBounds(192, 83, 96, 20);
		frame.getContentPane().add(prodNameField);
		
		JLabel lblNewLabel = new JLabel("Product:");
		lblNewLabel.setBounds(134, 86, 48, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Order Quantity:");
		lblNewLabel_1.setBounds(97, 117, 85, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnSubmit = new JButton("Submit Changes");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//connect to db and update delivery list info
				
				String orderQuantS = orderQuantField.getText();
				
				try {
				String update = "UPDATE DELIVERY set QUANTITY_ORDERED = ? " + "WHERE PRODUCT_ID = ?";
				 String host = "jdbc:sqlserver://localhost;databaseName=STOREFRONT;Trusted_Connection=True";
		         String uName = "user";
		         String uPass = "pass";
		         String connectionString = "jdbc:sqlserver://localhost;Database=master;Trusted_Connection=True;";
		         Connection con = DriverManager.getConnection(host, uName, uPass);
				 
		         PreparedStatement pstmt = con.prepareStatement(update);
		         pstmt.setString(1, orderQuantS);
		         pstmt.setString(2, productID);
		        		         
		         pstmt.executeUpdate();
		         System.out.println("Delivery Info Updated!");
		         
				}
				catch(Exception e1) {
					System.out.println(e1);
				}
				
				stock.updateDeliveryTable();
				frame.dispose();
			}
		});
		btnSubmit.setBounds(134, 154, 132, 23);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(134, 188, 132, 23);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblNewLabel_2 = new JLabel("Edit Delivery Info");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(123, 43, 143, 29);
		frame.getContentPane().add(lblNewLabel_2);
	}

}
