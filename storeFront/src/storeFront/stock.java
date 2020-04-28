package storeFront;


//import com.mysql.*;

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

import DBConn.DbUtils;
import DBConn.dbConn;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class stock extends javax.swing.JFrame {

	private JFrame frame;
	private static JTable currentStockTable;
	private static JTable deliveryListTable;

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
	
	public int purchaseMade(int quantity) {
		//get current quantity & price
		
		//if purchase quant < current quant
		
		//update DB
		
		// return price
		
		//else
		
		//return null + system msg
		
		
		
		return 0;
	}
	
	

	//Gets stock info from database
	public void getLowStockInfo() {
		//purchase price, product
		dbConn.executeSQL("TRUNCATE TABLE DELIVERY;");
		
		ResultSet rs4 = dbConn.connectToDB("SELECT * FROM STOCK WHERE CURRENT_QUANTITY < RECOMMENDED_QUANTITY;");
		
		try {
			while(rs4.next()) {
				String product = rs4.getString("PRODUCT_NAME");
				int currentQuant = rs4.getInt("CURRENT_QUANTITY");
				int maxQuant = rs4.getInt("MAXIMUM_QUANTITY");
				int buyPrice = rs4.getInt("PURCHASE_PRICE");
				int purchaseQuant = maxQuant-currentQuant;
				int productID = rs4.getInt("PRODUCT_ID");
				String productIDs = String.valueOf(productID);
				
				try {
					String updateDel = "INSERT INTO DELIVERY (PRODUCT_ID, QUANTITY_ORDERED) values (?,?);" ;
					 
					 String host = "jdbc:sqlserver://localhost;databaseName=STOREFRONT;Trusted_Connection=True";
			         String uName = "user";
			         String uPass = "pass";
			         String connectionString = "jdbc:sqlserver://localhost;Database=master;Trusted_Connection=True;";
			         Connection con = DriverManager.getConnection(host, uName, uPass);
					 
			         PreparedStatement pstmt = con.prepareStatement(updateDel);
			         pstmt.setString(1, productIDs);
			         pstmt.setLong(2, purchaseQuant);
			         
			  
			         
			         pstmt.executeUpdate();
			         
			         
			         
			         
				}
				catch(Exception ex1) {
					System.out.println(ex1);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		updateStockTable();
        updateDeliveryTable();
        System.out.println("Delivery List Updated!");
		
		//get all stock info
		
		// if stock is below recommended 
		
	}
	
	
	//Track Wasted Stock
	public void wastage() {
		
	}
	
	

	public void populateTables() {
		//dbConn.loadDriver();
	
		
		ResultSet rs = dbConn.connectToDB("Select PRODUCT_ID AS 'ID', PRODUCT_NAME AS 'Product Name', CURRENT_QUANTITY AS 'Current Stock', MINIMUM_QUANTITY AS 'Min Stock', RECOMMENDED_QUANTITY AS 'Rec Stock', MAXIMUM_QUANTITY AS 'Max Stock' FROM STOCK");	
	
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
		ResultSet rsu = dbConn.connectToDB("Select PRODUCT_ID AS 'ID', PRODUCT_NAME AS 'Product Name', CURRENT_QUANTITY AS 'Current Stock', MINIMUM_QUANTITY AS 'Min Stock', RECOMMENDED_QUANTITY AS 'Rec Stock',MAXIMUM_QUANTITY AS 'Max Stock' FROM STOCK");	
		
		try {
			while(rsu.next()) {
				currentStockTable.setModel(DbUtils.resultSetToTableModel(rsu));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	
	public static void updateDeliveryTable() {
		ResultSet rsu = dbConn.connectToDB("SELECT STOCK.PRODUCT_ID AS 'ID', STOCK.PRODUCT_NAME AS 'Name', DELIVERY.QUANTITY_ORDERED AS 'Ordered' FROM DELIVERY "
				+ "INNER JOIN STOCK ON DELIVERY.PRODUCT_ID = STOCK.PRODUCT_ID");
		
		try {
			while(rsu.next()) {
				deliveryListTable.setModel(DbUtils.resultSetToTableModel(rsu));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void populateDeliveryTable() {
		ResultSet rs5 = dbConn.connectToDB("SELECT STOCK.PRODUCT_ID AS 'ID', STOCK.PRODUCT_NAME AS 'Name', DELIVERY.QUANTITY_ORDERED AS 'Ordered' FROM DELIVERY "
				 + " INNER JOIN STOCK ON DELIVERY.PRODUCT_ID = STOCK.PRODUCT_ID");
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
		scrollPane.setBounds(25, 74, 443, 361);
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
		scrollPane_1.setBounds(620, 74, 307, 361);
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
		
		JButton confirmDeliveryBtn = new JButton("Confirm Delivery");
		confirmDeliveryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//getOrderQuantity
				
				ResultSet rs7 = dbConn.connectToDB("SELECT PRODUCT_ID, QUANTITY_ORDERED FROM DELIVERY");
				try {
					while (rs7.next()) {
						int orderedProductID = rs7.getInt("PRODUCT_ID");
						int orderQuant = rs7.getInt("QUANTITY_ORDERED");
						String prodIDS = String.valueOf(orderedProductID);
						
						
						//get current stock for product
						ResultSet rs8 = dbConn.connectToDB("SELECT CURRENT_QUANTITY FROM STOCK WHERE PRODUCT_ID = " + orderedProductID);
						
						while(rs8.next()) {
						int currentStock = rs8.getInt("CURRENT_QUANTITY");
						
						int newStock = currentStock + orderQuant;
						String newStockS = String.valueOf(newStock);
						
						//update the table
						
						
						String updateStock = "UPDATE STOCK set CURRENT_QUANTITY = ? " + "WHERE PRODUCT_ID = ?";
						String host = "jdbc:sqlserver://localhost;databaseName=STOREFRONT;Trusted_Connection=True";
				         String uName = "user";
				         String uPass = "pass";
				         String connectionString = "jdbc:sqlserver://localhost;Database=master;Trusted_Connection=True;";
				         Connection con = DriverManager.getConnection(host, uName, uPass);
						 
				         PreparedStatement pstmt = con.prepareStatement(updateStock);
				         pstmt.setString(1, newStockS);
				         pstmt.setString(2, prodIDS);

				         pstmt.executeUpdate();
				         System.out.println("Stock Info Updated!");
							
				         
							
						}
						
						//Add order quantity to current stock
						
						
						
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				//
				
				
				
				//clear delivery table
				
				updateStockTable();
				
				
				dbConn.executeSQL("TRUNCATE TABLE DELIVERY");
				
				
				System.out.println("Stock Added, DeliveryList Cleared!");
				//updateDeliveryTable();
				
				
				//add stock to current stock level
				
				
			}
		});
		confirmDeliveryBtn.setBounds(478, 402, 126, 33);
		frame.getContentPane().add(confirmDeliveryBtn);
		
		JButton wastageBtn = new JButton("Record Wastage");
		wastageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//use selected product
				
				try{
					int row = currentStockTable.getSelectedRow();
					int column = 0;
					String productID = String.valueOf(currentStockTable.getModel().getValueAt(row, column));
					
					
					recordWastage recordWastage = new recordWastage(productID);
					recordWastage.frame.setVisible(true);
					}
					catch(Exception f) {
						f.printStackTrace();
					}
				

				
				
			}
		});
		wastageBtn.setBounds(478, 176, 126, 39);
		frame.getContentPane().add(wastageBtn);
		
		JLabel lblNewLabel = new JLabel("Current Stock");
		lblNewLabel.setBounds(25, 49, 80, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Delivery List");
		lblNewLabel_1.setBounds(620, 49, 80, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Edit Stock Info");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//opens editStockInfo page for selected stock
				try{
				int row = currentStockTable.getSelectedRow();
				int column = 0;
				String productID = currentStockTable.getModel().getValueAt(row, column).toString();
				editStockInfo editStockInfo = new editStockInfo(productID);
				editStockInfo.frame.setVisible(true);
				}
				catch(Exception f) {
					f.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(478, 126, 126, 39);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCreateDelList = new JButton("Create Order");
		btnCreateDelList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				getLowStockInfo();
				updateDeliveryTable();
				
			}
		});
		btnCreateDelList.setBounds(478, 264, 126, 33);
		frame.getContentPane().add(btnCreateDelList);
		
		JButton btnNewItem = new JButton("Add New Item");
		btnNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addStock addStock = new addStock();
				addStock.frame.setVisible(true);
				
				
			}
		});
		btnNewItem.setBounds(478, 90, 126, 33);
		frame.getContentPane().add(btnNewItem);
		
		JButton btnEditDelList = new JButton("Edit Order");
		btnEditDelList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Selected Row from Delivery Table
				
				
				try{
					int row = deliveryListTable.getSelectedRow();
					int column = 0;
					String productID = deliveryListTable.getModel().getValueAt(row, column).toString();
					editDeliveryInfo editDeliveryInfo = new editDeliveryInfo(productID);
					editDeliveryInfo.frame.setVisible(true);
					}
					catch(Exception f) {
						f.printStackTrace();
					}
				//Get ID
				
				//new window pulls delivery info and can be edited
				
				
			}
		});
		btnEditDelList.setBounds(478, 308, 124, 37);
		frame.getContentPane().add(btnEditDelList);
		
		JButton btnNewButton_1 = new JButton("View Wastage");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				wastageView wastageView = new wastageView();
				wastageView.frame.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(478, 220, 126, 33);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnSubmitOrder = new JButton("Submit Order");
		btnSubmitOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Order Submitted");
				
			}
		});
		btnSubmitOrder.setBounds(478, 356, 126, 35);
		frame.getContentPane().add(btnSubmitOrder);

		
	}
}
