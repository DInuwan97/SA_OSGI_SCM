package SwingGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import biscutfactorygui.BackEndDetails;
import biscutfactorygui.IBackEnd;
import biscutmanafacture.BiscutModel;
import biscutmanafacture.IBiscuitManufactureDBQuries;
import biscutmanafacture.IOBiscutDetails;
import biscutmanafacture.ManufactureBiscuitDBQueries;
import biscutmanafacture.ManufactureStore;


import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Window.Type;
import javax.swing.JScrollPane;

public class BiscutManufactureDetailsGUI extends JFrame{

	

	
	private JFrame frame;
	private JTextField txtManDate;
	private JTextField txtExpDate;
	private JTextField txtquantity;
	private JTextField txtIngredients;
	private JTextField txtMachines;
	private JTextField txtEmployees;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void executeMainGUI(ManufactureStore manufactureStore) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BiscutManufactureDetailsGUI window = new BiscutManufactureDetailsGUI(manufactureStore);
					
					window.frame.setVisible(true);
					initialize(manufactureStore);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	

	public BiscutManufactureDetailsGUI(ManufactureStore manufactureStore) {
		initialize(manufactureStore);	

//		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
//		Vector v = new Vector();
//		
//		IBiscuitManufactureDBQuries iBiscuitManufactureDBQuries = new ManufactureBiscuitDBQueries();
//		ResultSet rs2 = iBiscuitManufactureDBQuries.getAllManfautureProducts();
//		
//		try {
//			while(rs2.next()) {
//				v.add(rs2.getString("demandReqId"));
//				v.add(rs2.getString("reqDate"));
//				v.add(rs2.getString("demadReason"));
//			}
//			
//			dtm.addRow(v);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
	}
	

	
	

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(ManufactureStore manufactureStore) {
		
		
		
		frame = new JFrame();
		frame.setType(Type.POPUP);
		frame.setAlwaysOnTop(true);
		frame.setBounds(0, 0, 1366, 768);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblProductName = new JLabel("Manufacture Date");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setForeground(Color.BLACK);
		lblProductName.setBounds(143, 172, 174, 34);
		frame.getContentPane().add(lblProductName);
		
		JLabel label = new JLabel("Product Name");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(143, 111, 174, 34);
		frame.getContentPane().add(label);
		
		JLabel lblExpireDate = new JLabel("Quantity");
		lblExpireDate.setForeground(Color.BLACK);
		lblExpireDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblExpireDate.setBounds(143, 289, 174, 34);
		frame.getContentPane().add(lblExpireDate);
		
		JLabel label_1 = new JLabel("Expire Date");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_1.setBounds(143, 232, 174, 34);
		frame.getContentPane().add(label_1);
		
		JLabel lblIngridents = new JLabel("Machines");
		lblIngridents.setForeground(Color.BLACK);
		lblIngridents.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIngridents.setBounds(143, 415, 174, 34);
		frame.getContentPane().add(lblIngridents);
		
		JLabel label_2 = new JLabel("Ingridents");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_2.setBounds(143, 352, 174, 34);
		frame.getContentPane().add(label_2);
		
		JLabel lblEmployees = new JLabel("Employees");
		lblEmployees.setForeground(Color.BLACK);
		lblEmployees.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmployees.setBounds(143, 483, 174, 34);
		frame.getContentPane().add(lblEmployees);
		
		JComboBox categories = new JComboBox();
	
		categories.setBounds(456, 113, 239, 34);
		frame.getContentPane().add(categories);
		
		
		//adding categories into combo box
		categories.addItem("LemmonPuff");
		categories.addItem("ChocoCream");
		//adding categories into combo box
		
		
		txtManDate = new JTextField();
		txtManDate.setBounds(456, 175, 239, 34);
		frame.getContentPane().add(txtManDate);
		txtManDate.setColumns(10);
		
		txtExpDate = new JTextField();
		txtExpDate.setColumns(10);
		txtExpDate.setBounds(456, 235, 239, 34);
		frame.getContentPane().add(txtExpDate);
		
		txtquantity = new JTextField();
		txtquantity.setColumns(10);
		txtquantity.setBounds(456, 289, 239, 34);
		frame.getContentPane().add(txtquantity);
		
		txtIngredients = new JTextField();
		txtIngredients.setColumns(10);
		txtIngredients.setBounds(456, 352, 239, 34);
		frame.getContentPane().add(txtIngredients);
		
		txtMachines = new JTextField();
		txtMachines.setColumns(10);
		txtMachines.setBounds(456, 418, 239, 34);
		frame.getContentPane().add(txtMachines);
		
		txtEmployees = new JTextField();
		txtEmployees.setColumns(10);
		txtEmployees.setBounds(456, 486, 239, 34);
		frame.getContentPane().add(txtEmployees);
		
		JButton btnAddProduct = new JButton("ADD PRODUCT");
		btnAddProduct.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnAddProduct.setBounds(545, 558, 150, 40);
		frame.getContentPane().add(btnAddProduct);
		
		JLabel lblImg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/dc.jpg")).getImage();
		lblImg.setIcon(new ImageIcon(img));
		lblImg.setBounds(734, 573, 104, 70);
		frame.getContentPane().add(lblImg);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(24, 514, 101, 22);
		frame.getContentPane().add(menuBar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(734, 123, 758, 289);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

//			
		
		Vector v = new Vector();
		
		IBiscuitManufactureDBQuries iBiscuitManufactureDBQuries = new ManufactureBiscuitDBQueries();
		ResultSet rs2 = iBiscuitManufactureDBQuries.getAllManfautureProducts();
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		try {
			while(rs2.next()) {
				
				v.add(rs2.getString("demandReqId"));
				v.add(rs2.getString("reqDate"));
				v.add(rs2.getString("demadReason"));
				dtm.addRow(v);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		BiscutModel biscutModel = new BiscutModel();
		
		//event handler for the ADDPRODUCT Button
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				
				try {
					//ask to save 
					int x = JOptionPane.showConfirmDialog(rootPane, "Do Yu want to save this record?");
					
					if(x==0) {
						
						biscutModel.setBiscutName(categories.getSelectedItem().toString());
						biscutModel.setExpireDate(txtExpDate.getText().toString());
						biscutModel.setManufactureDate(txtManDate.getText().toString());
						biscutModel.setIngridents(txtIngredients.getText().toString());
						biscutModel.setManufactAmount(Integer.parseInt(txtquantity.getText()));
						biscutModel.setNoOfMachines(Integer.parseInt(txtMachines.getText()));
						biscutModel.setNumOfEmployees(Integer.parseInt(txtEmployees.getText()));
						
						
						manufactureStore.createBiscut(biscutModel);
						
						//getting ack after insert to db
						Boolean result =  manufactureStore.isBiscutProductionInsertered();
						
						if(result == true) {
							JOptionPane.showMessageDialog(rootPane, "Successfully Added!!!");
							//ioBiscutDetails.OutPutBiscutDetails(biscutModel);	//display enterd product details in console
						}else {
							JOptionPane.showMessageDialog(rootPane, "Fail to Add!!!");
						}
						
					}
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
				
			}
		});
		
		//check select---future
		categories.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					
				}
			}
		});
		//check select---future
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
