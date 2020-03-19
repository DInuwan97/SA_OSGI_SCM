package BiscutManufactureGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;

import biscutmanafacture.BiscutModel;
import biscutmanafacture.IOBiscutDetails;
import biscutmanafacture.ManufactureStore;


import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JMenuBar;

public class ManufactureProductDetailsGUI extends JFrame{

	

	
	private JFrame frame;
	private JTextField txtManDate;
	private JTextField txtExpDate;
	private JTextField txtquantity;
	private JTextField txtIngredients;
	private JTextField txtMachines;
	private JTextField txtEmployees;

	/**
	 * Launch the application.
	 */
	public void executeMainGUI(ManufactureStore manufactureStore,BiscutModel biscutModel) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManufactureProductDetailsGUI window = new ManufactureProductDetailsGUI(manufactureStore,biscutModel);
					window.frame.setVisible(true);
					initialize(manufactureStore,biscutModel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	
	
	public ManufactureProductDetailsGUI(ManufactureStore manufactureStore,BiscutModel biscutModel) {
		initialize(manufactureStore,biscutModel);	
		
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ManufactureStore manufactureStore,BiscutModel biscutModel) {
		frame = new JFrame();
		frame.setBounds(100, 100, 862, 690);
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
		
		
		
		//event handler for the ADDPRODUCT Button
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manufactureStore.createBiscut(categories.getSelectedItem().toString(), biscutModel);
				
				IOBiscutDetails ioBiscutDetails = new IOBiscutDetails();
			
				biscutModel.setBiscutName(categories.getSelectedItem().toString());
				biscutModel.setExpireDate(txtExpDate.getText().toString());
				biscutModel.setManufactureDate(txtManDate.getText().toString());
				biscutModel.setIngridents(txtIngredients.getText().toString());
				biscutModel.setManufactAmount(Integer.parseInt(txtquantity.getText()));
				biscutModel.setNoOfMachines(Integer.parseInt(txtMachines.getText()));
				biscutModel.setNumOfEmployees(Integer.parseInt(txtEmployees.getText()));
				
				//display enterd product details in console
				ioBiscutDetails.OutPutBiscutDetails(biscutModel);
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
