package frames;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import DBConn.DbConn;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditDeliveryInfo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtQuantity;
	private JTextField txtProduct;

	
	public void populate(String productID) {
		
		//int productID = Integer.parseInt(productIDs);

	
		ResultSet rs1 = DbConn.connectToDB("SELECT DELIVERY.PRODUCT_ID, STOCK.PRODUCT_NAME, DELIVERY.QUANTITY_ORDERED FROM DELIVERY" 
				+ " INNER JOIN STOCK ON DELIVERY.PRODUCT_ID = STOCK.PRODUCT_ID WHERE DELIVERY.PRODUCT_ID = " + productID);
		System.out.println(rs1);
		
		
		try {
			while(rs1.next()) {
		String productName = rs1.getString("PRODUCT_NAME");
		String orderQuant = rs1.getString("QUANTITY_ORDERED");
		txtProduct.setText(productName);
		txtQuantity.setText(orderQuant);
					
		}
				
		}
		catch(Exception e) {
			System.out.println(e);
		}
			
	}
	
	

	/**
	 * Create the application.
	 */
	public EditDeliveryInfo(String productID) {
		initialize(productID);
		populate(productID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String productID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(320, 700, 400, 200);
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
		
		txtQuantity = new JTextField();
		txtQuantity.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtQuantity.setText("");
			}
		});
		txtQuantity.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));;
		txtQuantity.setBackground(new Color(105, 105, 105));
		txtQuantity.setText("QUANTITY");
		txtQuantity.setBounds(161, 114, 96, 20);
		contentPane.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		
		txtProduct = new JTextField();
		txtProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtProduct.setText("");
			}
		});
		txtProduct.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));;
		txtProduct.setBackground(new Color(105, 105, 105));
		txtProduct.setText("PRODUCT");
		txtProduct.setColumns(10);
		txtProduct.setBounds(161, 83, 96, 20);
		contentPane.add(txtProduct);
		
		JTextField txtSubmit = new JTextField("SUBMIT");
		txtSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtSubmit.setEditable(false);
		txtSubmit.setBackground(new Color(105, 105, 105));
		txtSubmit.setForeground(new Color(255, 255, 255));
		txtSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubmit.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSubmit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//connect to db and update delivery list info
				
				String orderQuantS = txtQuantity.getText();
				
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
				
				Stock.updateDeliveryTable();
				dispose();
			}
		});
		txtSubmit.setBounds(97, 155, 85, 23);
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
		txtClose.setBounds(217, 155, 85, 23);
		contentPane.add(txtClose);
		
		JLabel subHeaderEdit = new JLabel("EDIT DELIVERY INFO");
		subHeaderEdit.setForeground(Color.WHITE);
		subHeaderEdit.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		subHeaderEdit.setBounds(123, 43, 179, 29);
		contentPane.add(subHeaderEdit);
	}

}
