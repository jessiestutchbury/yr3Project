package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import DBConn.DbConn;
import javax.swing.BorderFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;

public class RecordWastage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtQuantity;
	private JTextArea txtInfo;
	private JTextField txtProductName;


	/**
	 * Create the application.
	 */
	public RecordWastage(String productID) {
		int oldQuant = getQuant(productID);
		initialize(productID, oldQuant);
		populateFields(productID);
	}

	
	public int getQuant(String productID) {
		
		ResultSet rs2 = DbConn.connectToDB("SELECT CURRENT_QUANTITY FROM STOCK WHERE PRODUCT_ID =" + productID + ";");
		
		try {
			while(rs2.next()) {
				int currentQuant = rs2.getInt("CURRENT_QUANTITY");
				return currentQuant;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	
public void populateFields(String productID) {

		
		ResultSet rs1 = DbConn.connectToDB("SELECT PRODUCT_NAME, CURRENT_QUANTITY FROM STOCK WHERE PRODUCT_ID =" + productID +";");
		
		try {
			while(rs1.next()) {
		String productName = rs1.getString("PRODUCT_NAME");
		int currentQuant = rs1.getInt("CURRENT_QUANTITY");

		txtProductName.setText(productName);
		
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(320, 700, 400, 400);
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
		
		JLabel subHeader = new JLabel("RECORD WASTAGE");
		subHeader.setForeground(Color.WHITE);
		subHeader.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		subHeader.setBounds(123, 29, 177, 49);
		contentPane.add(subHeader);
		
		JLabel lblreason = new JLabel("Reason");
		lblreason.setBounds(93, 175, 37, 14);
		contentPane.add(lblreason);
		
		JLabel lblInfo = new JLabel("Additional Info");
		lblInfo.setBounds(62, 217, 78, 14);
		contentPane.add(lblInfo);
		
		txtProductName = new JTextField();
		txtProductName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtProductName.setText("");
			}
		});
		txtProductName.setText("PRODUCT");
		txtProductName.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));
		txtProductName.setBackground(new Color(105, 105, 105));
		txtProductName.setBounds(151, 86, 101, 20);
		contentPane.add(txtProductName);
		txtProductName.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtQuantity.setText("");
			}
		});
		txtQuantity.setText("QUANTITY");
		txtQuantity.setBounds(151, 131, 101, 20);
		txtQuantity.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));
		txtQuantity.setBackground(new Color(105, 105, 105));
		contentPane.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		JComboBox cmbReason = new JComboBox();
		cmbReason.setModel(new DefaultComboBoxModel(new String[] {"Damage", "Refund", "Quality", "Gift"}));
		cmbReason.setBackground(new Color(105, 105, 105));
		cmbReason.setBounds(150, 171, 102, 22);
		contentPane.add(cmbReason);
		
		txtInfo = new JTextArea();
		txtInfo.setColumns(10);
		txtInfo.setBounds(150, 212, 191, 58);
		contentPane.add(txtInfo);
		
		JTextField txtSubmit = new JTextField("SUBMIT");
		txtSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ResultSet rs = DbConn.connectToDB("SELECT CURRENT_QUANTITY FROM STOCK WHERE PRODUCT_ID = " + productID);
				try {
					while(rs.next()) {
						int oldQuant = rs.getInt("CURRENT_QUANTITY");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				String wastageQuantityS = txtQuantity.getText();
				int wastageQuantity = Integer.parseInt(txtQuantity.getText());
				String reason = (String) cmbReason.getSelectedItem();
				String addInfo = txtInfo.getText();
				//String authName = txtEmpName.getText();
				int newQuantity = oldQuant - wastageQuantity;
				String newQuantS = String.valueOf(newQuantity);
				
				
				
				
				try {
					String updateWastage = "INSERT INTO WASTAGE (PRODUCT_ID, WASTED_QUANTITY, REASON, ADDITIONAL_INFORMATION) values(?,?,?,?)" ;
					 
					 String host = "jdbc:sqlserver://localhost;databaseName=STOREFRONT;Trusted_Connection=True";
			         String uName = "user";
			         String uPass = "pass";
			         String connectionString = "jdbc:sqlserver://localhost;Database=master;Trusted_Connection=True;";
			         Connection con = DriverManager.getConnection(host, uName, uPass);
					 
			         PreparedStatement pstmt = con.prepareStatement(updateWastage);
			         pstmt.setString(1, productID);
			         pstmt.setString(2, wastageQuantityS);
			         pstmt.setString(3, reason);
			         pstmt.setString(4, addInfo);
			         
			         pstmt.executeUpdate();
			         System.out.println("Wastage Updated!");
			        
			        try {  
			         String updateStock = "UPDATE STOCK set CURRENT_QUANTITY = ?" + " WHERE PRODUCT_ID = ?";
			         PreparedStatement pstmt2 = con.prepareStatement(updateStock);
			         pstmt2.setString(1, newQuantS);
			         pstmt2.setString(2, productID);
			         
			         pstmt2.executeUpdate();
			         System.out.println("Success!");
			        }
			        catch(Exception ex2) {
			        	System.out.println(ex2);
			        }
			         Stock.updateStockTable();
			         Stock.updateDeliveryTable();
				
				}
				catch(Exception ex1) {
					System.out.println(ex1);
				}
				
				
				//wastageQuant.getText();
				//ResultSet rs3 = dbConn.connectToDB("SELECT CURRENT_QUANTITY FROM STOCKLIST WHERE PROD_NAME = " + prodSelectBox.getSelectedItem() + ";");
				//System.out.println(rs3);
				dispose();
			}
		});

		txtSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtSubmit.setEditable(false);
		txtSubmit.setBackground(new Color(105, 105, 105));
		txtSubmit.setForeground(new Color(255, 255, 255));
		txtSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubmit.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSubmit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		txtSubmit.setBounds(93, 347, 89, 23);
		contentPane.add(txtSubmit);
		
		
		
		JTextField txtClose = new JTextField("CLOSE");
		txtClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtClose.setEditable(false);
		txtClose.setBackground(new Color(105, 105, 105));
		txtClose.setForeground(new Color(255, 255, 255));
		txtClose.setHorizontalAlignment(SwingConstants.CENTER);
		txtClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));		
		txtClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		txtClose.setBounds(211, 347, 89, 23);
		contentPane.add(txtClose);
		
	}
}
