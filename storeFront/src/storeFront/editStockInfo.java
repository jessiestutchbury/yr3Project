package storeFront;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.sql.*;


public class editStockInfo {

	JFrame frame;
	private JTextField prodName;
	private JTextField prodPrice;
	private JTextField minQuant;
	private JTextField maxQuant;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
			//public void run() {
				//try {
					//editStockInfo window = new editStockInfo(productName);
					//window.frame.setVisible(true);
				//} catch (Exception e) {
					//e.printStackTrace();
				//}
			//}
		//});
	//}

	/**
	 * Create the application.
	 */
	public editStockInfo(String productID) {
		
		initialize(productID);
		dbConn.loadDriver();
		populate(productID);
		
	}
	

	
	public void populate(String productID) {
		
		//int productID = Integer.parseInt(productIDs);

	
		ResultSet rs1 = dbConn.connectToDB("SELECT PROD_NAME, PROD_SALES_PRICE, PROD_MIN_QUANTITY, PROD_MAX_QUANTITY, PROD_REC_QUANTITY FROM YR3_STOCK WHERE PROD_ID =" + productID +";");
		System.out.println(rs1);
		try {
			while(rs1.next()) {
		String productName = rs1.getString("PROD_NAME");
		String productPrice = rs1.getString("PROD_SALES_PRICE");
		int minQuantity = rs1.getInt("PROD_MIN_QUANTITY");
		int maxQuantity = rs1.getInt("PROD_MAX_QUANTITY");
		int recQuantity = rs1.getInt("PROD_REC_QUANTITY");
		
		
		prodName.setText(productName);
		//prodID.setText(productID);
		prodPrice.setText(productPrice);
		minQuant.setText(String.valueOf(minQuantity));
		maxQuant.setText(String.valueOf(maxQuantity));
		}
		
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		

		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String productID) {
		frame = new JFrame();
		frame.setBounds(100, 100, 527, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(27, 31, 455, 344);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("close");
		btnNewButton.setBounds(180, 230, 89, 23);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Product Name");
		lblNewLabel.setBounds(110, 65, 89, 14);
		panel_1.add(lblNewLabel);
		
		prodName = new JTextField();
		
		prodName.setBounds(222, 62, 96, 20);
		panel_1.add(prodName);
		prodName.setColumns(10);
		
		JLabel lblProductPrice = new JLabel("Product Price");
		lblProductPrice.setBounds(120, 96, 73, 14);
		panel_1.add(lblProductPrice);
		
		JLabel lblMinimumQuantity = new JLabel("Minimum Quantity");
		lblMinimumQuantity.setBounds(110, 121, 89, 14);
		panel_1.add(lblMinimumQuantity);
		
		JLabel lblMaximumQuantity = new JLabel("Maximum Quantity");
		lblMaximumQuantity.setBounds(110, 146, 104, 14);
		panel_1.add(lblMaximumQuantity);
		
		prodPrice = new JTextField();

		prodPrice.setColumns(10);
		prodPrice.setBounds(222, 93, 96, 20);
		panel_1.add(prodPrice);
		
		minQuant = new JTextField();

		minQuant.setColumns(10);
		minQuant.setBounds(222, 118, 96, 20);
		panel_1.add(minQuant);
		
		maxQuant = new JTextField();

		maxQuant.setColumns(10);
		maxQuant.setBounds(222, 143, 96, 20);
		panel_1.add(maxQuant);
		
		JButton btnUpdateStockInfo = new JButton("Update Stock Info");
		btnUpdateStockInfo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//updates stock info in DB
				
				String productNameN = prodName.getText();
				
				String productPriceN = prodPrice.getText();
				String minQuantN = minQuant.getText();
				String maxQuantN = maxQuant.getText();
				
				
				try {
					String updateStock = "UPDATE YR3_STOCK set PROD_NAME = ?, " + "PROD_SALES_PRICE = ?, " + "PROD_MIN_QUANTITY = ?, " + "PROD_MAX_QUANTITY = ?" + " WHERE PROD_ID = ?" ;
					 
					 String host = "jdbc:sqlserver://localhost;databaseName=YR3TEST;Trusted_Connection=True";
			         String uName = "user";
			         String uPass = "pass";
			         String connectionString = "jdbc:sqlserver://localhost;Database=master;Trusted_Connection=True;";
			         Connection con = DriverManager.getConnection(host, uName, uPass);
					 
			         PreparedStatement pstmt = con.prepareStatement(updateStock);
			         pstmt.setString(1, productNameN);
			         pstmt.setString(2, productPriceN);
			         pstmt.setString(3, minQuantN);
			         pstmt.setString(4, maxQuantN);
			         pstmt.setString(5, productID);
			         
			         pstmt.executeUpdate();
			         System.out.println("Success!");
			         
			         stock.updateStockTable();
				
				}
				catch(Exception ex1) {
					System.out.println(ex1);
				}
				
				
			}
		});
		btnUpdateStockInfo.setBounds(159, 196, 135, 23);
		panel_1.add(btnUpdateStockInfo);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
}
