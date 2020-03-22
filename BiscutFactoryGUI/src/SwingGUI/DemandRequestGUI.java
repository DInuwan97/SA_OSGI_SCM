package SwingGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DemandSales.DemandRequestDBQueries;
import DemandSales.Idemand;
import DemandSales.IdemandRequstDBQueries;
import DemandSales.SalesDemandJunction;
import DemandSales.SalesDemandMsgModel;
import DemandSales.SalesMessageDetailsModel;
import biscutfactorygui.BackEndDetails;
import biscutfactorygui.BackEndMessageDetailsModel;
import biscutfactorygui.DemandRequestDataModel;
import biscutfactorygui.IBackEnd;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class DemandRequestGUI {
	
	//varible for initialize messageId for future use
	private int salesMessageId;
	private IBackEnd iBackEnd;
	private Idemand iDemand;
	private File dataInsertFileName;
	private FileWriter fileWriter;
	private PrintWriter printWriter;
	private FileInputStream fis;
	private DataInputStream dis;
	
	private String productName;
	private int qty;
	private String fileData;
	

	private JFrame frame;
	private JTextField txtReqDate;
	private JTextField txtProductName;
	private JTextField txtQuantity;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void executeDemandGUI(Idemand iDemand)  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemandRequestGUI window = new DemandRequestGUI(iDemand);
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

	
	public DemandRequestGUI(Idemand iDemand) {
		initialize(iDemand);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Idemand iDemand) {
		frame = new JFrame();
		frame.setBounds(0, 0, 1366, 768);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSalesRequest = new JLabel("Sales Request");
		lblSalesRequest.setForeground(Color.BLACK);
		lblSalesRequest.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSalesRequest.setBounds(92, 68, 174, 34);
		frame.getContentPane().add(lblSalesRequest);
		
		JComboBox comboBox = new JComboBox();

		comboBox.setBounds(405, 70, 239, 34);
		frame.getContentPane().add(comboBox);
		
		JLabel lblRequiredDate = new JLabel("Required Date");
		lblRequiredDate.setForeground(Color.BLACK);
		lblRequiredDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRequiredDate.setBounds(92, 192, 174, 34);
		frame.getContentPane().add(lblRequiredDate);
		
		txtReqDate = new JTextField();
		txtReqDate.setColumns(10);
		txtReqDate.setBounds(405, 195, 239, 34);
		frame.getContentPane().add(txtReqDate);
		
		JButton btnSearchSalesRequest = new JButton("SEARCH SALES");
		btnSearchSalesRequest.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSearchSalesRequest.setBounds(494, 114, 150, 40);
		frame.getContentPane().add(btnSearchSalesRequest);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.BLACK);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDescription.setBounds(740, 68, 174, 34);
		frame.getContentPane().add(lblDescription);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setForeground(Color.BLACK);
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setBounds(92, 287, 174, 34);
		frame.getContentPane().add(lblProductName);
		
		txtProductName = new JTextField();
	
		txtProductName.setColumns(10);
		txtProductName.setBounds(405, 287, 239, 34);
		frame.getContentPane().add(txtProductName);
		
		txtQuantity = new JTextField();
	
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(548, 331, 96, 34);
		frame.getContentPane().add(txtQuantity);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(Color.BLACK);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuantity.setBounds(92, 331, 174, 34);
		frame.getContentPane().add(lblQuantity);
		
		JButton btnAddProduct = new JButton("ADD PRODUCT");
	
		btnAddProduct.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnAddProduct.setBounds(494, 374, 150, 40);
		frame.getContentPane().add(btnAddProduct);
		
		JTextArea textReason = new JTextArea();
		textReason.setBounds(92, 451, 552, 76);
		frame.getContentPane().add(textReason);
		
		JTextArea txtDescription = new JTextArea();
		txtDescription.setBounds(738, 97, 389, 76);
		frame.getContentPane().add(txtDescription);
		
		JLabel lblReasonForRequest = new JLabel("Reason for request");
		lblReasonForRequest.setForeground(Color.BLACK);
		lblReasonForRequest.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReasonForRequest.setBounds(92, 416, 174, 34);
		frame.getContentPane().add(lblReasonForRequest);
		
		JButton btnAddDemand = new JButton("ADD PRODUCT");
	
		btnAddDemand.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnAddDemand.setBounds(92, 583, 552, 40);
		frame.getContentPane().add(btnAddDemand);
		
		comboBox.addItem("Select Request..");
		
		table = new JTable();
		table.setBounds(771, 299, 336, 190);
		frame.getContentPane().add(table);
		
		table.setModel(new javax.swing.table.DefaultTableModel(
			new Object[][] {
				{"Product Name","Quantity"}
			},
			new String[] {
					"ProductName","Quantity"
			}
				
		));
		
		
		
		
	
		
		//creating the reference of SQL Backend
		iBackEnd = new BackEndDetails();
		IdemandRequstDBQueries idemandRequstDBQueries = new DemandRequestDBQueries();
	
		
		
		//filling data to the combo box
		try {
			ResultSet rs = iBackEnd.LoadSalesMessages();
			while(rs.next()) {
				
				comboBox.addItem(rs.getString("msgId"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//check select---future
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(!comboBox.getSelectedItem().toString().equals("Select Request..")) {
						
						try {
						
							ResultSet rs1 = idemandRequstDBQueries.getSelectedSalesMessage(Integer.parseInt(comboBox.getSelectedItem().toString()));
							
							if(rs1.next()) {
								txtDescription.setText(rs1.getString("message")); 
							}
							
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						txtDescription.setText("Select Sales Request from left dropdown....."); 
					}
					
					
					salesMessageId = Integer.parseInt(comboBox.getSelectedItem().toString());
				}
			}
		});
		
		
		//Biscut Qty
		txtProductName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtQuantity.grabFocus();
			}
		});
		
		//Biscut quantity 
		txtQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			productName = txtProductName.getText();
			qty = Integer.parseInt(txtQuantity.getText());
			
			String textToAppend = productName+ " : " +qty+ " , ";
			System.out.println(textToAppend);
			
			
			
			try {
				//if doen t use true,file will wb updating to latest modifiction that have been given
				fileWriter = new FileWriter("E:\\SLIIT\\3rd Year\\Semester 1\\3rd yr SE\\SA - Software Architecture\\Assignment\\SCM Assg 1\\SA_OSGI_SCM\\BiscutDemand\\dataFiles\\demandReq.txt",true); 
				printWriter = new PrintWriter(fileWriter);
				printWriter.print(textToAppend);  //New line
			    printWriter.close();
			    
			  
				    
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
				
				
				
				
			//adding to table	
			 DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			 Vector v = new Vector();
			 
			 v.add(txtProductName.getText());
			 v.add(txtQuantity.getText());
			 
			 dtm.addRow(v);
			 
			 txtProductName.grabFocus();
			 
			 txtProductName.setText("");
			 txtQuantity.setText("");
			 
			 
			 
			 
				
			}
		});
		
		
		
		btnAddDemand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SalesDemandMsgModel salesDemandMsgModel = new SalesDemandMsgModel();				
			 
				try {
					fis = new FileInputStream("E:\\SLIIT\\3rd Year\\Semester 1\\3rd yr SE\\SA - Software Architecture\\Assignment\\SCM Assg 1\\SA_OSGI_SCM\\BiscutDemand\\dataFiles\\demandReq.txt");
					
						try {
							dis = new DataInputStream(fis);
							fileData = dis.readLine();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					System.out.println("File Data is : " +fileData);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				try {
					int x = JOptionPane.showConfirmDialog(null, "Do Yu want to save this record?");
					
					if(x==0) {
			
						
						//setting values to the demand request textfileds and call setters
						salesDemandMsgModel.setMsgId(Integer.parseInt(comboBox.getSelectedItem().toString()));
						salesDemandMsgModel.setReqDate(txtReqDate.getText());
						salesDemandMsgModel.setDemandRequest(fileData);
						salesDemandMsgModel.setDescription(textReason.getText());
						

						Boolean result = idemandRequstDBQueries.insertDemandReq(salesDemandMsgModel);
						if(result == true) {
							
							//send to the BiscutDemand Bundle
							JOptionPane.showMessageDialog(null, "Successfully Added!!!");
							
						    ////////////////////////////////////////////Deleting the file////////////
							deleteCurrentFile();
	
						}else {
							JOptionPane.showMessageDialog(null, "Fail to Add!!!");
						}
						
						
						
					}
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
	
			}
		});
		
	
	}
	///////////////////////////////////////////////Deleting the file////////////
	public void deleteCurrentFile() {

		 File currentfile = new File("E:\\SLIIT\\3rd Year\\Semester 1\\3rd yr SE\\SA - Software Architecture\\Assignment\\Biscut Factory\\BiscutDemand\\dataFiles\\demandReq.txt"); 
          
	        if(currentfile.delete()) 
	            System.out.println("File deleted successfully"); 
	        else						        
	            System.out.println("Failed to delete the file");
	}
}
