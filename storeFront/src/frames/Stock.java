package frames;

import DBConn.DbUtils;
import DBConn.DbConn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Stock extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable currentStockTable;
	private static JTable deliveryListTable;
	private JTextField txtClose;

	
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
		DbConn.executeSQL("TRUNCATE TABLE DELIVERY;");
		
		ResultSet rs4 = DbConn.connectToDB("SELECT * FROM STOCK WHERE CURRENT_QUANTITY < RECOMMENDED_QUANTITY;");
		
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
    	
    		
    		ResultSet rs = DbConn.connectToDB("Select PRODUCT_ID AS 'ID', PRODUCT_NAME AS 'Product Name', CURRENT_QUANTITY AS 'Current Stock', MINIMUM_QUANTITY AS 'Min Stock', RECOMMENDED_QUANTITY AS 'Rec Stock', MAXIMUM_QUANTITY AS 'Max Stock' FROM STOCK");	
    	
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
    		ResultSet rsu = DbConn.connectToDB("Select PRODUCT_ID AS 'ID', PRODUCT_NAME AS 'Product Name', CURRENT_QUANTITY AS 'Current Stock', MINIMUM_QUANTITY AS 'Min Stock', RECOMMENDED_QUANTITY AS 'Rec Stock',MAXIMUM_QUANTITY AS 'Max Stock' FROM STOCK");	
    		
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
    		ResultSet rsu = DbConn.connectToDB("SELECT STOCK.PRODUCT_ID AS 'ID', STOCK.PRODUCT_NAME AS 'Name', DELIVERY.QUANTITY_ORDERED AS 'Ordered' FROM DELIVERY "
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
    		ResultSet rs5 = DbConn.connectToDB("SELECT STOCK.PRODUCT_ID AS 'ID', STOCK.PRODUCT_NAME AS 'Name', DELIVERY.QUANTITY_ORDERED AS 'Ordered' FROM DELIVERY "
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
	public Stock() {
		initialize();
		DbConn.loadDriver();
		getLowStockInfo();
		populateTables();
		populateDeliveryTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(320, 700, 900, 600);
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
		separator.setBounds(0, 32, 900, 14);
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setAlignmentX(Component.RIGHT_ALIGNMENT);
		contentPane.add(separator);
	
		JLabel subHeaderStock = new JLabel("CURRENT STOCK");
		subHeaderStock.setForeground(Color.WHITE);
		subHeaderStock.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		subHeaderStock.setBounds(141, 55, 153, 23);
		contentPane.add(subHeaderStock);
				
		
		JLabel subHeaderDelivery = new JLabel("DELIVERY LIST");
		subHeaderDelivery.setForeground(Color.WHITE);
		subHeaderDelivery.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		subHeaderDelivery.setBounds(610, 55, 142, 21);
		contentPane.add(subHeaderDelivery);
		
		JScrollPane stockTable = new JScrollPane();
		stockTable.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		stockTable.setBackground(new Color(105, 105, 105));
		stockTable.setBounds(20, 89, 412, 433);
		contentPane.add(stockTable);		
		currentStockTable = new JTable();
		currentStockTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		stockTable.setViewportView(currentStockTable);		
		JScrollPane deliveryTable = new JScrollPane();
		deliveryTable.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		deliveryTable.setBounds(462, 89, 412, 433);
		contentPane.add(deliveryTable);
	
		
		
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
		deliveryTable.setViewportView(deliveryListTable);
	
		
		
		
		JTextField txtSubmit = new JTextField();
		txtSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtSubmit.setEditable(false);
		txtSubmit.setBackground(new Color(105, 105, 105));
		txtSubmit.setForeground(new Color(255, 255, 255));
		txtSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubmit.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSubmit.setText("SUBMIT ORDER");
		txtSubmit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtSubmit.setBounds(697, 533, 166, 23);
		contentPane.add(txtSubmit);
	
		
		JTextField txtConfirmDlvry = new JTextField();
		txtConfirmDlvry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//getOrderQuantity
				
				ResultSet rs7 = DbConn.connectToDB("SELECT PRODUCT_ID, QUANTITY_ORDERED FROM DELIVERY");
				try {
					while (rs7.next()) {
						int orderedProductID = rs7.getInt("PRODUCT_ID");
						int orderQuant = rs7.getInt("QUANTITY_ORDERED");
						String prodIDS = String.valueOf(orderedProductID);
						
						
						//get current stock for product
						ResultSet rs8 = DbConn.connectToDB("SELECT CURRENT_QUANTITY FROM STOCK WHERE PRODUCT_ID = " + orderedProductID);
						
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
				
				
				DbConn.executeSQL("TRUNCATE TABLE DELIVERY");
				
				
				System.out.println("Stock Added, DeliveryList Cleared!");
				//updateDeliveryTable();
				
				
				//add stock to current stock level
				
				
			}
		});

		txtConfirmDlvry.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtConfirmDlvry.setEditable(false);
		txtConfirmDlvry.setBackground(new Color(105, 105, 105));
		txtConfirmDlvry.setForeground(new Color(255, 255, 255));
		txtConfirmDlvry.setHorizontalAlignment(SwingConstants.CENTER);
		txtConfirmDlvry.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtConfirmDlvry.setText("CONFIRM DELIVERY");
		txtConfirmDlvry.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtConfirmDlvry.setBounds(472, 567, 166, 23);
		contentPane.add(txtConfirmDlvry);
	
		
		JTextField txtSaveDelivery = new JTextField();
		txtSaveDelivery.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtSaveDelivery.setEditable(false);
		txtSaveDelivery.setBackground(new Color(105, 105, 105));
		txtSaveDelivery.setForeground(new Color(255, 255, 255));
		txtSaveDelivery.setHorizontalAlignment(SwingConstants.CENTER);
		txtSaveDelivery.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSaveDelivery.setText("SAVE DELIVERY LIST");
		txtSaveDelivery.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtSaveDelivery.setBounds(472, 533, 166, 23);
		contentPane.add(txtSaveDelivery);
	
		
		
		
		JTextField txtRecordWastage = new JTextField();
		txtRecordWastage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtRecordWastage.setEditable(false);
		txtRecordWastage.setBackground(new Color(105, 105, 105));
		txtRecordWastage.setForeground(new Color(255, 255, 255));
		txtRecordWastage.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecordWastage.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtRecordWastage.setText("RECORD WASTAGE");
		txtRecordWastage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtRecordWastage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		//use selected product				
				try{
					int row = currentStockTable.getSelectedRow();
					int column = 1;
					String productID = String.valueOf(currentStockTable.getModel().getValueAt(row, column));
					RecordWastage RecordWastage = new RecordWastage(productID);
					RecordWastage.setVisible(true);
					}
					catch(Exception f) {
						f.printStackTrace();
					}							
			}
		});
		txtRecordWastage.setBounds(30, 566, 166, 23);
		contentPane.add(txtRecordWastage);
		
		
		
		JTextField txtEditStock = new JTextField();
		txtEditStock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtEditStock.setEditable(false);
		txtEditStock.setBackground(new Color(105, 105, 105));
		txtEditStock.setForeground(new Color(255, 255, 255));
		txtEditStock.setHorizontalAlignment(SwingConstants.CENTER);
		txtEditStock.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtEditStock.setText("EDIT STOCK");
		txtEditStock.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtEditStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//opens editStockInfo page for selected stock
				try{
				int row = currentStockTable.getSelectedRow();
				int column = 1;
				String productID = currentStockTable.getModel().getValueAt(row, column).toString();
				EditStockInfo EditStockInfo = new EditStockInfo(productID);
				EditStockInfo.setVisible(true);
				}
				catch(Exception f) {
					f.printStackTrace();
				}
				
				
			}
		});
		txtEditStock.setBounds(30, 533, 166, 23);
		contentPane.add(txtEditStock);
		
		txtClose = new JTextField("CLOSE");
		txtClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtClose.setEditable(false);
		txtClose.setBackground(new Color(105, 105, 105));
		txtClose.setForeground(new Color(255, 255, 255));
		txtClose.setHorizontalAlignment(SwingConstants.CENTER);
		txtClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtClose.setBounds(829, 0, 71, 35);
		contentPane.add(txtClose);

		
	}
}
