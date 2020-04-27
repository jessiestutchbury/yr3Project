package storeFront;

import com.mysql.*;

import DBConn.DbUtils;
import DBConn.dbConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class stock extends javax.swing.JFrame {

	private JFrame frame;
	private static JTable currentStockTable;
	private JTable deliveryListTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stock window = new stock();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Gets stock info from database
	public void getLowStockInfo() {
		//purchase price, product
		ResultSet rs4 = dbConn.connectToDB("SELECT * FROM YR3_STOCK WHERE PROD_CURRENT_QUANTITY < PROD_REC_QUANTITY;");
		
		try {
			while(rs4.next()) {
				String product = rs4.getString("PROD_NAME");
				int currentQuant = rs4.getInt("PROD_CURRENT_QUANTITY");
				int maxQuant = rs4.getInt("PROD_MAX_QUANTITY");
				int buyPrice = rs4.getInt("PROD_PURCHASE_PRICE");
				int purchaseQuant = maxQuant-currentQuant;
				int totalPrice = buyPrice * purchaseQuant;
				int productID = rs4.getInt("PROD_ID");
				String productIDs = String.valueOf(productID);
				
				try {
					String updateDel = "INSERT INTO DeliveryList (PROD_NAME, PROD_ORDER_QUANT, PROD_ID) values (?,?,?);" ;
					 
					 String host = "jdbc:sqlserver://localhost;databaseName=YR3TEST;Trusted_Connection=True";
			         String uName = "user";
			         String uPass = "pass";
			         String connectionString = "jdbc:sqlserver://localhost;Database=master;Trusted_Connection=True;";
			         Connection con = DriverManager.getConnection(host, uName, uPass);
					 
			         PreparedStatement pstmt = con.prepareStatement(updateDel);
			         pstmt.setString(1, product);
			         pstmt.setLong(2, purchaseQuant);
			         pstmt.setString(3, productIDs);
			  
			         
			         pstmt.executeUpdate();
			         System.out.println("Success!");
			         
			         stock.updateStockTable();
				
				}
				catch(Exception ex1) {
					System.out.println(ex1);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//get all stock info
		
		// if stock is below recommended 
		
	}
	
	
	//Track Wasted Stock
	public void wastage() {
		
	}
	
	

	public void populateTables() {
		//dbConn.loadDriver();
	
		
		ResultSet rs = dbConn.connectToDB("Select PROD_NAME AS 'Product Name', PROD_ID AS 'ID', PROD_CURRENT_QUANTITY AS 'Current Stock', PROD_MIN_QUANTITY AS 'Min Stock', PROD_REC_QUANTITY AS 'Rec Stock',PROD_MAX_QUANTITY AS 'Max Stock' FROM YR3_STOCK");	
	
		try {
			while(rs.next()) {
				currentStockTable.setModel(DbUtils.resultSetToTableModel(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void updateStockTable() {
		ResultSet rsu = dbConn.connectToDB("Select PROD_NAME AS 'Product Name', PROD_ID AS 'ID', PROD_CURRENT_QUANTITY AS 'Current Stock', PROD_MIN_QUANTITY AS 'Min Stock', PROD_REC_QUANTITY AS 'Rec Stock',PROD_MAX_QUANTITY AS 'Max Stock' FROM YR3_STOCK");	
		
		try {
			while(rsu.next()) {
				currentStockTable.setModel(DbUtils.resultSetToTableModel(rsu));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}

	public void populateDeliveryTable() {
		ResultSet rs5 = dbConn.connectToDB("SELECT PROD_ID AS 'ID', PROD_NAME AS 'Name', PROD_ORDER_QUANT AS'Ordered' FROM DeliveryList");
		try {
			while(rs5.next()) {
				deliveryListTable.setModel(DbUtils.resultSetToTableModel(rs5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void saveDeliveryList() {
		
	}
	
	public void confirmDelivery() {
		//empties the deliveryList table
		//adds delivery info to current stock count
		//if product is new, it must be added to the database
	}
	
	
	public void removeProduct() {
		//removes a product from the database
		
	}
	
	
	
	
	
	/**
	 * Create the application.
	 */
	public stock() {
		initialize();
		dbConn.loadDriver();
		getLowStockInfo();
		populateTables();
		populateDeliveryTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 967, 496);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(478, 82, 443, 151);
		frame.getContentPane().add(scrollPane);
		
		currentStockTable = new JTable();
		currentStockTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(currentStockTable);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(478, 259, 307, 143);
		frame.getContentPane().add(scrollPane_1);
		
		deliveryListTable = new JTable();
		deliveryListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product", "Purchase Quant.", "Price Per Unit", "Total Price"
			}
		));
		deliveryListTable.getColumnModel().getColumn(1).setPreferredWidth(97);
		deliveryListTable.getColumnModel().getColumn(2).setPreferredWidth(84);
		scrollPane_1.setViewportView(deliveryListTable);
		
		JButton submitOrderBtn = new JButton("Submit Order");
		submitOrderBtn.setBounds(799, 278, 111, 23);
		frame.getContentPane().add(submitOrderBtn);
		
		JButton confirmDeliveryBtn = new JButton("Confirm Delivery");
		confirmDeliveryBtn.setBounds(795, 312, 126, 23);
		frame.getContentPane().add(confirmDeliveryBtn);
		
		JButton editDeliveryListBtn = new JButton("Save Delivery List");
		editDeliveryListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		editDeliveryListBtn.setBounds(795, 346, 126, 23);
		frame.getContentPane().add(editDeliveryListBtn);
		
		JButton wastageBtn = new JButton("Record Wastage");
		wastageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//use selected product
				
				try{
					int row = currentStockTable.getSelectedRow();
					int column = 1;
					String productID = String.valueOf(currentStockTable.getModel().getValueAt(row, column));
					
					
					recordWastage recordWastage = new recordWastage(productID);
					recordWastage.frame.setVisible(true);
					}
					catch(Exception f) {
						f.printStackTrace();
					}
				

				
				
			}
		});
		wastageBtn.setBounds(314, 103, 126, 23);
		frame.getContentPane().add(wastageBtn);
		
		JLabel lblNewLabel = new JLabel("Current Stock");
		lblNewLabel.setBounds(488, 57, 80, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Delivery List");
		lblNewLabel_1.setBounds(488, 244, 80, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Edit Stock Info");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//opens editStockInfo page for selected stock
				try{
				int row = currentStockTable.getSelectedRow();
				int column = 1;
				String productID = currentStockTable.getModel().getValueAt(row, column).toString();
				editStockInfo editStockInfo = new editStockInfo(productID);
				editStockInfo.frame.setVisible(true);
				}
				catch(Exception f) {
					f.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(324, 137, 116, 23);
		frame.getContentPane().add(btnNewButton);

		
	}
}
