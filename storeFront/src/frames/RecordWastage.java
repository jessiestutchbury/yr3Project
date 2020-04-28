package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import DBConn.DbConn;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class RecordWastage {

	JFrame frame;
	private JTextField wastageQuant;
	private JTextField additionalInfo;
	private JTextField wastageNameTxt;
	private JTextField prodNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecordWastage window = new RecordWastage(null);
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
	public RecordWastage(String productID) {
		int oldQuant = getQuant(productID);
		initialize(productID, oldQuant);
		populateFields(productID);
	}

	
	public int getQuant(String productID) {
		
		ResultSet rs2 = DbConn.connectToDB("SELECT PROD_CURRENT_QUANTITY FROM YR3_STOCK WHERE PROD_ID =" + productID + ";");
		
		try {
			while(rs2.next()) {
				int currentQuant = rs2.getInt("PROD_CURRENT_QUANTITY");
				return currentQuant;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 10;
		
	}
	
	
	public void populateFields(String productID) {

		
		ResultSet rs1 = DbConn.connectToDB("SELECT PROD_NAME, PROD_CURRENT_QUANTITY FROM YR3_STOCK WHERE PROD_ID =" + productID +";");
		
		try {
			while(rs1.next()) {
		String productName = rs1.getString("PROD_NAME");
		int currentQuant = rs1.getInt("PROD_CURRENT_QUANTITY");

		prodNameField.setText(productName);
		
		}
		
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String productID, int oldQuant) {
		frame = new JFrame();
		frame.setBounds(100, 100, 366, 427);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	//	JComboBox prodSelectBox = new JComboBox();
	//	ResultSet rs2 = dbConn.connectToDB("SELECT * FROM YR3_STOCK");
		
	//	try {
	//		while(rs2.next()) {
	//			prodSelectBox.addItem(rs2.getString("PROD_NAME"));
	//		}
	//	} catch (SQLException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}


	//	prodSelectBox.setBounds(150, 85, 86, 22);
	//	frame.getContentPane().add(prodSelectBox);
		
		JLabel prodNameLabel = new JLabel("Product");
		prodNameLabel.setBounds(93, 89, 37, 14);
		frame.getContentPane().add(prodNameLabel);
		
		JLabel lblNewLabel = new JLabel("Record Wastage");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(93, 25, 159, 49);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quantity");
		lblNewLabel_1.setBounds(93, 134, 48, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Reason");
		lblNewLabel_1_1.setBounds(104, 175, 37, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel additionalInfoLabel = new JLabel("Additional Info");
		additionalInfoLabel.setBounds(63, 215, 78, 14);
		frame.getContentPane().add(additionalInfoLabel);
		
		wastageQuant = new JTextField();
		wastageQuant.setBounds(164, 131, 64, 20);
		frame.getContentPane().add(wastageQuant);
		wastageQuant.setColumns(10);
		
		JComboBox wastageReason = new JComboBox();
		wastageReason.setBounds(150, 171, 86, 22);
		frame.getContentPane().add(wastageReason);
		
		additionalInfo = new JTextField();
		additionalInfo.setColumns(10);
		additionalInfo.setBounds(150, 212, 150, 20);
		frame.getContentPane().add(additionalInfo);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(93, 256, 37, 14);
		frame.getContentPane().add(nameLabel);
		
		wastageNameTxt = new JTextField();
		wastageNameTxt.setColumns(10);
		wastageNameTxt.setBounds(150, 253, 64, 20);
		frame.getContentPane().add(wastageNameTxt);
		
		JButton wastageSubmitBtn = new JButton("Submit");
		wastageSubmitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ResultSet rs = DbConn.connectToDB("SELECT PROD_CURRENT_QUANTITY FROM YR3_STOCK WHERE PROD_ID = " + productID);
				try {
					while(rs.next()) {
						int oldQuant = rs.getInt("PROD_CURRENT_QUANTITY");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				String wastageQuantityS = wastageQuant.getText();
				int wastageQuantity = Integer.parseInt(wastageQuant.getText());
				String reason = "PRESET";
				String addInfo = additionalInfo.getText();
				String authName = wastageNameTxt.getText();
				int newQuantity = oldQuant - wastageQuantity;
				String newQuantS = String.valueOf(newQuantity);
				
				
				
				
				try {
					String updateWastage = "INSERT INTO WASTAGE_LIST (PROD_ID, WASTED_QUANT, REASON, ADDITIONAL_INFO, AUTH_NAME) values(?,?,?,?,?)" ;
					 
					 String host = "jdbc:sqlserver://localhost;databaseName=YR3TEST;Trusted_Connection=True";
			         String uName = "user";
			         String uPass = "pass";
			         String connectionString = "jdbc:sqlserver://localhost;Database=master;Trusted_Connection=True;";
			         Connection con = DriverManager.getConnection(host, uName, uPass);
					 
			         PreparedStatement pstmt = con.prepareStatement(updateWastage);
			         pstmt.setString(1, productID);
			         pstmt.setString(2, wastageQuantityS);
			         pstmt.setString(3, reason);
			         pstmt.setString(4, addInfo);
			         pstmt.setString(5, authName);
			         
			         pstmt.executeUpdate();
			         System.out.println("Success!");
			        
			          
			         String updateStock = "UPDATE YR3_STOCK set PROD_CURRENT_QUANTITY = ?" + " WHERE PROD_ID = ?" ;
			         PreparedStatement pstmt2 = con.prepareStatement(updateWastage);
			         pstmt2.setString(1, newQuantS);
			         pstmt2.setString(2, productID);
			         
			         pstmt2.executeUpdate();
			         System.out.println("Success!");
			         
			         Stock.updateStockTable();
				
				}
				catch(Exception ex1) {
					System.out.println(ex1);
				}
				
				
				//wastageQuant.getText();
				//ResultSet rs3 = dbConn.connectToDB("SELECT CURRENT_QUANTITY FROM YR3_STOCK WHERE PROD_NAME = " + prodSelectBox.getSelectedItem() + ";");
				//System.out.println(rs3);
				frame.dispose();
			}
		});
		wastageSubmitBtn.setBounds(118, 317, 89, 23);
		frame.getContentPane().add(wastageSubmitBtn);
		
		JButton wastageCancelButton = new JButton("Cancel");
		wastageCancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		wastageCancelButton.setBounds(118, 351, 89, 23);
		frame.getContentPane().add(wastageCancelButton);
		
		prodNameField = new JTextField();
		prodNameField.setEditable(false);
		prodNameField.setBounds(156, 86, 96, 20);
		frame.getContentPane().add(prodNameField);
		prodNameField.setColumns(10);
	}
}
