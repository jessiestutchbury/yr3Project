package frames;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import DBConn.DbConn;
import javax.swing.JPanel;
import java.sql.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class EditStockInfo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8348911250512170383L;
	private JPanel contentPane;
	private JTextField txtProduct;
	private JTextField txtPrice;
	private JTextField txtMin;
	private JTextField txtMax;
	private JTextField txtUpdateStockInfo;
	private JTextField txtClose;


	/**
	 * Create the application.
	 */
	public EditStockInfo(String productID) {
		
		initialize(productID);
		DbConn.loadDriver();
		populate(productID);		
	}
	

	public static int calcReccomended(int minimumQuantity, int maximumQuantity) {
		
		int recQuantity = minimumQuantity + ((maximumQuantity - minimumQuantity) / 2);
		
		
		
		
		return recQuantity;
		
	}
	
	
public void populate(String productID) {
		
		//int productID = Integer.parseInt(productIDs);

	
		ResultSet rs1 = DbConn.connectToDB("SELECT PRODUCT_NAME, SALE_PRICE, MINIMUM_QUANTITY, MAXIMUM_QUANTITY, RECOMMENDED_QUANTITY FROM STOCK WHERE PRODUCT_ID =" + productID +";");
		System.out.println(rs1);
		try {
			while(rs1.next()) {
		String productName = rs1.getString("PRODUCT_NAME");
		String productPrice = rs1.getString("SALE_PRICE");
		int minQuantity = rs1.getInt("MINIMUM_QUANTITY");
		int maxQuantity = rs1.getInt("MAXIMUM_QUANTITY");
	
		txtProduct.setText(productName);
		txtPrice.setText(productPrice);
		txtMin.setText(String.valueOf(minQuantity));
		txtMax.setText(String.valueOf(maxQuantity));				
		
		}
		
			
			
			
		
		}
		catch(Exception e) {
			System.out.println(e);
		}}

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String productID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(320, 700, 400, 356);
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
		
		txtProduct = new JTextField();	
		txtProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtProduct.setText("");
			}
		});
		txtProduct.setText("PRODUCT");
		txtProduct.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));;
		txtProduct.setBackground(new Color(105, 105, 105));
		txtProduct.setBounds(133, 104, 135, 20);
		contentPane.add(txtProduct);
		txtProduct.setColumns(10);
		
		
		txtPrice = new JTextField();
		txtPrice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtPrice.setText("");
			}
		});
		txtPrice.setText("PRICE");
		txtPrice.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));;
		txtPrice.setBackground(new Color(105, 105, 105));
		txtPrice.setBounds(133, 148, 135, 20);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		
		txtMin = new JTextField();
		txtMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMin.setText("");
			}
		});
		txtMin.setText("MIN. QUANTITY");
		txtMin.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));;
		txtMin.setBackground(new Color(105, 105, 105));
		txtMin.setBounds(133, 195, 135, 20);
		contentPane.add(txtMin);
		txtMin.setColumns(10);
		
		
		txtMax = new JTextField();
		txtMax.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMax.setText("");
			}
		});
		txtMax.setText("MAX. QUANTITY");
		txtMax.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));;
		txtMax.setBackground(new Color(105, 105, 105));
		txtMax.setBounds(133, 244, 135, 20);
		contentPane.add(txtMax);
		txtMax.setColumns(10);
		
		JLabel subHeader = new JLabel("STOCK EDITOR");
		subHeader.setForeground(Color.WHITE);
		subHeader.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		subHeader.setBounds(133, 33, 153, 45);
		contentPane.add(subHeader);
		
		txtClose = new JTextField();
		txtClose.setText("CLOSE");
		txtClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtClose.setHorizontalAlignment(SwingConstants.CENTER);
		txtClose.setForeground(Color.WHITE);
		txtClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtClose.setEditable(false);
		txtClose.setColumns(10);
		txtClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtClose.setBackground(SystemColor.controlDkShadow);
		txtClose.setBounds(207, 290, 117, 36);
		contentPane.add(txtClose);	
		
		txtUpdateStockInfo = new JTextField();
		txtUpdateStockInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtUpdateStockInfo.setEditable(false);
		txtUpdateStockInfo.setBackground(new Color(105, 105, 105));
		txtUpdateStockInfo.setForeground(new Color(255, 255, 255));
		txtUpdateStockInfo.setHorizontalAlignment(SwingConstants.CENTER);
		txtUpdateStockInfo.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtUpdateStockInfo.setText("UPDATE");
		txtUpdateStockInfo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtUpdateStockInfo.setBounds(64, 290, 117, 36);
		contentPane.add(txtUpdateStockInfo);
		txtUpdateStockInfo.setColumns(10);
		txtUpdateStockInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//updates stock info in DB
				
				String productNameN = txtProduct.getText();
				
				String productPriceN = txtPrice.getText();
				int minQuantI = Integer.parseInt(txtMin.getText());
				int maxQuantI = Integer.parseInt(txtMax.getText());
				String minQuantU = txtMin.getText();
				String maxQuantN = txtMax.getText();
				String recQuantity = String.valueOf(calcReccomended(minQuantI, maxQuantI));
				
				
				try {
					String updateStock = "UPDATE STOCK set PRODUCT_NAME = ?, " + "SALE_PRICE = ?, " + "MINIMUM_QUANTITY = ?, " + "MAXIMUM_QUANTITY = ?, "  + "RECOMMENDED_QUANTITY = ?" + " WHERE PRODUCT_ID = ?" ;
					 
					 String host = "jdbc:sqlserver://localhost;databaseName=STOREFRONT;Trusted_Connection=True";
			         String uName = "user";
			         String uPass = "pass";
			         String connectionString = "jdbc:sqlserver://localhost;Database=master;Trusted_Connection=True;";
			         Connection con = DriverManager.getConnection(host, uName, uPass);
					 
			         PreparedStatement pstmt = con.prepareStatement(updateStock);
			         pstmt.setString(1, productNameN);
			         pstmt.setString(2, productPriceN);
			         pstmt.setString(3, minQuantU);
			         pstmt.setString(4, maxQuantN);
			         pstmt.setString(5, recQuantity);
			         pstmt.setString(6, productID);
			         
			         pstmt.executeUpdate();
			         System.out.println("Stock Info Updated!");
			         
			         Stock.updateStockTable();
			         dispose();
				}
				catch(Exception ex1) {
					System.out.println(ex1);
				}
				
				
			}
		});
		
		
		}

		
	}
