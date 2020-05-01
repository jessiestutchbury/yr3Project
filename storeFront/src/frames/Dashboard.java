package frames;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DBConn.DbConn;
import DBConn.DbUtils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JScrollPane;
public class Dashboard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMgrOptions;
	private JTextField txtLogOut;
	private JTextField txtPayNow;
	private static JTable tblCurrentSelection;
	private JTextField txtElp;
	private JTextField txtPayCustomCash;
	private JTextField txt5Cash;
	private JTextField txt10Cash;
	private JTextField txt20Cash;
	private JTextField txtClose;
	private int xx, xy;
	private JTextField txtRemoveItem;

	
	public void addToSelection(String itemName) {
		
		ResultSet rs = DbConn.connectToDB("SELECT PRODUCT_NAME AS 'Product', SALE_PRICE AS 'Unit Price' FROM STOCK WHERE PRODUCT_NAME = '" + itemName + "'");
		
		try {
			while(rs.next()) {
				tblCurrentSelection.setModel(DbUtils.resultSetToTableModel(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	/**
	 * Create the frame.
	 */
	public Dashboard() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(320, 700, 629, 356);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;

        // Move the window
        this.setLocation(x, y);
		contentPane.setLayout(null);
		
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
		
		
		JPanel panelProducts = new JPanel();
		panelProducts.setBackground(new Color(105, 105, 105));
		panelProducts.setBounds(303, 57, 316, 238);
		contentPane.add(panelProducts);
		
		ResultSet rs = DbConn.connectToDB("SELECT PRODUCT_NAME FROM PRODUCT");
		final ResultSetMetaData meta = rs.getMetaData();
		final int columnCount = meta.getColumnCount();
		final List<List<String>> rowList = new LinkedList<List<String>>();
		while (rs.next())
		{
			 final List<String> columnList = new LinkedList<String>();
			    rowList.add(columnList);

			    for (int column = 1; column <= columnCount; column++) 
			    {
			        final Object value = rs.getObject(column);
			        columnList.add(String.valueOf(value));
			        JTextField txt = new JTextField(rs.getString("PRODUCT_NAME"));
			        txt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			        txt.setHorizontalAlignment(SwingConstants.CENTER);
			        txt.setForeground(Color.WHITE);
			        txt.setFont(new Font("Tahoma", Font.BOLD, 15));
			        txt.setEditable(false);
			        txt.setColumns(10);
			        txt.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			        txt.setBackground(SystemColor.controlDkShadow);
			        txt.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							addToSelection(txt.getText());
						}
					});
			        panelProducts.add(txt);
			    }
			
		}

		
		txtMgrOptions = new JTextField();		
		txtMgrOptions.setBounds(303, 306, 172, 36);
		txtMgrOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame managerOptions = new ManagerOptions();
				managerOptions.setVisible(true);
			}
		});
		txtMgrOptions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtMgrOptions.setEditable(false);
		txtMgrOptions.setBackground(new Color(105, 105, 105));
		txtMgrOptions.setForeground(new Color(255, 255, 255));
		txtMgrOptions.setHorizontalAlignment(SwingConstants.CENTER);
		txtMgrOptions.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtMgrOptions.setText("MANAGER OPTIONS");
		txtMgrOptions.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(txtMgrOptions);
		txtMgrOptions.setColumns(10);
		
		txtLogOut = new JTextField();
		txtLogOut.setBounds(514, 0, 115, 33);
		txtLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame login = new Login();
				login.setVisible(true);
			}
		});
		txtLogOut.setText("LOG OUT");
		txtLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogOut.setForeground(Color.WHITE);
		txtLogOut.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtLogOut.setEditable(false);
		txtLogOut.setColumns(10);
		txtLogOut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtLogOut.setBackground(SystemColor.controlDkShadow);
		contentPane.add(txtLogOut);
		
		txtPayNow = new JTextField();
		txtPayNow.setBounds(485, 306, 134, 36);
		txtPayNow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelProducts.setVisible(false);
				txtMgrOptions.setVisible(false);
				txtPayNow.setVisible(false);
				txtClose.setVisible(true);
				txtPayCustomCash.setVisible(true);
				txtElp.setVisible(true);
				txt5Cash.setVisible(true);
				txt10Cash.setVisible(true);
				txt20Cash.setVisible(true);
			}
		});
		txtPayNow.setText("PAY NOW");
		txtPayNow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtPayNow.setHorizontalAlignment(SwingConstants.CENTER);
		txtPayNow.setForeground(Color.WHITE);
		txtPayNow.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtPayNow.setEditable(false);
		txtPayNow.setColumns(10);
		txtPayNow.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtPayNow.setBackground(SystemColor.controlDkShadow);
		contentPane.add(txtPayNow);
		
		JLabel lblNewLabel = new JLabel("CURRENTLY SELECTED");
		lblNewLabel.setBounds(10, 41, 134, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblNewLabel);
		
		JScrollPane scrlProductScroll = new JScrollPane();
		scrlProductScroll.setBackground(new Color(105, 105, 105));
		scrlProductScroll.setBounds(10, 57, 283, 239);
		contentPane.add(scrlProductScroll);
		
		tblCurrentSelection = new JTable();
		tblCurrentSelection.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Product", "Unit Price"
				}
			));
		scrlProductScroll.setViewportView(tblCurrentSelection);
		
		
		
		txtElp = new JTextField();		
		txtElp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtElp.setEditable(false);
		txtElp.setBackground(new Color(105, 105, 105));
		txtElp.setForeground(new Color(255, 255, 255));
		txtElp.setHorizontalAlignment(SwingConstants.CENTER);
		txtElp.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtElp.setText("ELP PAYMENT");
		txtElp.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtElp.setBounds(317, 155, 147, 80);
		contentPane.add(txtElp);
		txtElp.setColumns(10);
		
		
		txtPayCustomCash = new JTextField();
		txtPayCustomCash.setText("CASH");
		txtPayCustomCash.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtPayCustomCash.setHorizontalAlignment(SwingConstants.CENTER);
		txtPayCustomCash.setForeground(Color.WHITE);
		txtPayCustomCash.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtPayCustomCash.setEditable(false);
		txtPayCustomCash.setColumns(10);
		txtPayCustomCash.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtPayCustomCash.setBackground(SystemColor.controlDkShadow);
		txtPayCustomCash.setBounds(317, 64, 147, 80);
		contentPane.add(txtPayCustomCash);
		
		txt5Cash = new JTextField();
		txt5Cash.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txt5Cash.setText("\u00A35");
		txt5Cash.setHorizontalAlignment(SwingConstants.CENTER);
		txt5Cash.setForeground(Color.WHITE);
		txt5Cash.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt5Cash.setEditable(false);
		txt5Cash.setColumns(10);
		txt5Cash.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt5Cash.setBackground(SystemColor.controlDkShadow);
		txt5Cash.setBounds(474, 66, 59, 33);
		contentPane.add(txt5Cash);
		
		txt10Cash = new JTextField();
		txt10Cash.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txt10Cash.setText("\u00A310");
		txt10Cash.setHorizontalAlignment(SwingConstants.CENTER);
		txt10Cash.setForeground(Color.WHITE);
		txt10Cash.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt10Cash.setEditable(false);
		txt10Cash.setColumns(10);
		txt10Cash.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt10Cash.setBackground(SystemColor.controlDkShadow);
		txt10Cash.setBounds(543, 66, 59, 33);
		contentPane.add(txt10Cash);
		
		txt20Cash = new JTextField();
		txt20Cash.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txt20Cash.setText("\u00A320");
		txt20Cash.setHorizontalAlignment(SwingConstants.CENTER);
		txt20Cash.setForeground(Color.WHITE);
		txt20Cash.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt20Cash.setEditable(false);
		txt20Cash.setColumns(10);
		txt20Cash.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt20Cash.setBackground(SystemColor.controlDkShadow);
		txt20Cash.setBounds(474, 111, 128, 33);
		contentPane.add(txt20Cash);
		
		txtClose = new JTextField();
		txtClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelProducts.setVisible(true);
				txtMgrOptions.setVisible(true);
				txtPayNow.setVisible(true);
				txtClose.setVisible(false);
				txtPayCustomCash.setVisible(false);
				txtElp.setVisible(false);
				txt5Cash.setVisible(false);
				txt10Cash.setVisible(false);
				txt20Cash.setVisible(false);
			}
		});
		txtClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtClose.setText("CLOSE");
		txtClose.setHorizontalAlignment(SwingConstants.CENTER);
		txtClose.setForeground(Color.WHITE);
		txtClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtClose.setEditable(false);
		txtClose.setColumns(10);
		txtClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtClose.setBackground(SystemColor.controlDkShadow);
		txtClose.setBounds(485, 306, 134, 36);
		contentPane.add(txtClose);
		
		JLabel lblDragger = new JLabel("");
		lblDragger.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
		        int y = e.getYOnScreen();
		        Dashboard.this.setLocation(x - xx, y - xy); 
				
			}
		});
		lblDragger.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
			     xy = e.getY();
				
			}
		});
		lblDragger.setBounds(0, 0, 515, 36);
		contentPane.add(lblDragger);
		
		txtRemoveItem = new JTextField();
		txtRemoveItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//remove item from selection table
			}
		});
		txtRemoveItem.setText("REMOVE ITEM");
		txtRemoveItem.setHorizontalAlignment(SwingConstants.CENTER);
		txtRemoveItem.setForeground(Color.WHITE);
		txtRemoveItem.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtRemoveItem.setEditable(false);
		txtRemoveItem.setColumns(10);
		txtRemoveItem.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtRemoveItem.setBackground(SystemColor.controlDkShadow);
		txtRemoveItem.setBounds(10, 306, 172, 36);
		contentPane.add(txtRemoveItem);
	}
}
