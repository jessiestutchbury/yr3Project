package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.Popup;
import javax.swing.PopupFactory;

public class accountScreen {
	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accountScreen window = new accountScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public accountScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("storeFront");
		frame.setBounds(100, 100, 1102, 754);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
				
				
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 235, 552);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JLabel lblCurrentItems = new JLabel("Items");
		lblCurrentItems.setBounds(10, 11, 27, 14);
		panel.add(lblCurrentItems);
		
		
		JTextPane textArea = new JTextPane();
		textArea.setBounds(10, 31, 215, 510);
		textArea.setEditable(false);
		panel.add(textArea);
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(233, 0, 853, 715);
		tabbedPane.setSelectedIndex(0);
		frame.getContentPane().add(tabbedPane);
		
			JPanel panel_2 = new JPanel();
			tabbedPane.addTab("Sweets", null, panel_2, null);
			
			JPanel panel_3 = new JPanel();
			tabbedPane.addTab("Soft Drinks", null, panel_3, null);
			
			JPanel panel_4 = new JPanel();
			tabbedPane.addTab("Alcohol", null, panel_4, null);
			panel_4.setLayout(null);
			
			JButton btnNewButton = new JButton("New button");
			btnNewButton.setBounds(40, 32, 89, 23);
			panel_4.add(btnNewButton);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(0, 552, 235, 163);
			frame.getContentPane().add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblOptions = new JLabel("Options");
			lblOptions.setBounds(10, 11, 37, 14);
			panel_1.add(lblOptions);
			
			
	}
	
}
