package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddStock extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProduct;
	private JTextField txtPurchasePrice;
	private JTextField txtSalesPrice;
	private JTextField txtMin;
	private JTextField txtMax;

	/**
	 * Create the application.
	 */
	public AddStock() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
		JLabel lblNewLabel = new JLabel("ADD NEW STOCK");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		lblNewLabel.setBounds(133, 42, 155, 36);
		contentPane.add(lblNewLabel);
		
		txtProduct = new JTextField();
		txtProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtProduct.setText("");
			}
		});
		txtProduct.setText("PRODUCT NAME");
		txtProduct.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));
		txtProduct.setBackground(new Color(105, 105, 105));
		txtProduct.setBounds(143, 89, 130, 20);
		contentPane.add(txtProduct);
		txtProduct.setColumns(10);
		
		
		txtPurchasePrice = new JTextField();
		txtPurchasePrice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtPurchasePrice.setText("");
			}
		});
		txtPurchasePrice.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));
		txtPurchasePrice.setBackground(new Color(105, 105, 105));
		txtPurchasePrice.setText("PURCHASE PRICE");
		txtPurchasePrice.setColumns(10);
		txtPurchasePrice.setBounds(143, 130, 130, 20);
		contentPane.add(txtPurchasePrice);
		
		
		txtSalesPrice = new JTextField();
		txtSalesPrice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSalesPrice.setText("");
			}
		});
		txtSalesPrice.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));
		txtSalesPrice.setBackground(new Color(105, 105, 105));
		txtSalesPrice.setText("SALES PRICE");
		txtSalesPrice.setColumns(10);
		txtSalesPrice.setBounds(143, 174, 130, 20);
		contentPane.add(txtSalesPrice);
		
		
		txtMin = new JTextField();
		txtMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMin.setText("");
			}
		});
		txtMin.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));
		txtMin.setBackground(new Color(105, 105, 105));
		txtMin.setText("MIN. QUANTITY");
		txtMin.setColumns(10);
		txtMin.setBounds(143, 223, 130, 20);
		contentPane.add(txtMin);
		
		
		txtMax = new JTextField();
		txtMax.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMax.setText("");
			}
		});
		txtMax.setBorder(BorderFactory.createMatteBorder(0,  0,  2,  0,  Color.BLACK));
		txtMax.setBackground(new Color(105, 105, 105));
		txtMax.setText("MAX. QUANTITY");
		txtMax.setColumns(10);
		txtMax.setBounds(143, 267, 130, 20);
		contentPane.add(txtMax);
		
		
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
				
				//insert to database
				String prodName = txtProduct.getText();
				String prodBuyPrice = txtPurchasePrice.getText();
				String salesPrice = txtSalesPrice.getText();
				String minimumQuant = txtMin.getText();
				String maximumQuant = txtMax.getText();
				
				int minimumQuantI = Integer.valueOf(minimumQuant);
				int maximumQuantI = Integer.valueOf(maximumQuant);
				
				String currentQuant = "0";
				String recQuantity = String.valueOf(EditStockInfo.calcReccomended(minimumQuantI, maximumQuantI));
				
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
		         dispose();
		        
		         
				}
				catch(Exception ee) {
					System.out.println(ee);
				}
				
				 Stock.updateStockTable();			
			}
		});
		txtSubmit.setBounds(100, 330, 89, 23);
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
		txtClose.setBounds(199, 330, 89, 23);
		contentPane.add(txtClose);
	}
}
