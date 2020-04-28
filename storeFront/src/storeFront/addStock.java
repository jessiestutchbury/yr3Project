package storeFront;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import DBConn.dbConn;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class addStock {

	JFrame frame;
	private JTextField prodNameField;
	private JTextField purchasePriceField;
	private JTextField salesPriceField;
	private JTextField minQuantField;
	private JTextField maxQuantField;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
			//public void run() {
				//try {
					//addStock window = new addStock();
					//window.frame.setVisible(true);
				//} catch (Exception e) {
				//	e.printStackTrace();
			//}
//			}
	//	});
	//}

	/**
	 * Create the application.
	 */
	public addStock() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 679, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add New Stock");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(241, 28, 155, 82);
		frame.getContentPane().add(lblNewLabel);
		
		prodNameField = new JTextField();
		prodNameField.setBounds(358, 143, 96, 20);
		frame.getContentPane().add(prodNameField);
		prodNameField.setColumns(10);
		
		purchasePriceField = new JTextField();
		purchasePriceField.setColumns(10);
		purchasePriceField.setBounds(358, 174, 96, 20);
		frame.getContentPane().add(purchasePriceField);
		
		salesPriceField = new JTextField();
		salesPriceField.setColumns(10);
		salesPriceField.setBounds(358, 205, 96, 20);
		frame.getContentPane().add(salesPriceField);
		
		minQuantField = new JTextField();
		minQuantField.setColumns(10);
		minQuantField.setBounds(358, 236, 96, 20);
		frame.getContentPane().add(minQuantField);
		
		maxQuantField = new JTextField();
		maxQuantField.setColumns(10);
		maxQuantField.setBounds(358, 267, 96, 20);
		frame.getContentPane().add(maxQuantField);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name");
		lblNewLabel_1.setBounds(241, 146, 76, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Purchase Price");
		lblNewLabel_1_1.setBounds(241, 177, 76, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Minimum Quantity");
		lblNewLabel_1_2.setBounds(241, 239, 89, 14);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Sales Price");
		lblNewLabel_1_3.setBounds(241, 208, 76, 14);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Maximum Quantity");
		lblNewLabel_1_4.setBounds(241, 270, 107, 14);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//insert to database
				String prodName = prodNameField.getText();
				String prodBuyPrice = purchasePriceField.getText();
				String salesPrice = salesPriceField.getText();
				String minimumQuant = minQuantField.getText();
				String maximumQuant = maxQuantField.getText();
				
				int minimumQuantI = Integer.valueOf(minimumQuant);
				int maximumQuantI = Integer.valueOf(maximumQuant);
				
				String currentQuant = "0";
				String recQuantity = String.valueOf(editStockInfo.calcReccomended(minimumQuantI, maximumQuantI));
				
				String insertString = "INSERT INTO STOCK (PRODUCT_NAME, PURCHASE_PRICE, SALE_PRICE, MINIMUM_QUANTITY, RECOMMENDED_QUANTITY, MAXIMUM_QUANTITY, CURRENT_QUANTITY) values (?,?,?,?,?,?,?);";
				
				
				try {
				String host = "jdbc:sqlserver://localhost;databaseName=STOREFRONT;Trusted_Connection=True";
		         String uName = "user";
		         String uPass = "pass";
		         String connectionString = "jdbc:sqlserver://localhost;Database=master;Trusted_Connection=True;";
		         
		         
		         Connection con = DriverManager.getConnection(host, uName, uPass);
				 
		         PreparedStatement pstmt = con.prepareStatement(insertString);
		         pstmt.setString(1, prodName);
		         pstmt.setString(2, prodBuyPrice);
		         pstmt.setString(3, salesPrice);
		         pstmt.setString(4, minimumQuant);
		         pstmt.setString(5, recQuantity);
		         pstmt.setString(6, maximumQuant);
		         pstmt.setString(7, currentQuant);
		         
		  
		         
		         pstmt.executeUpdate();
		         
		         System.out.println("Stock Added to DB!");
		         frame.dispose();
		        
		         
				}
				catch(Exception ee) {
					System.out.println(ee);
				}
				
				 stock.updateStockTable();
				
		
				
				
				
				
				
				
			}
		});
		btnSubmit.setBounds(307, 312, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
			}
		});
		btnCancel.setBounds(307, 346, 89, 23);
		frame.getContentPane().add(btnCancel);
	}
}
