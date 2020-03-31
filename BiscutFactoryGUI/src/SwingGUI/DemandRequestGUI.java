package SwingGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.mysql.jdbc.Connection;

import DemandSales.DemandRequestDBQueries;
import DemandSales.Idemand;
import DemandSales.IdemandRequstDBQueries;
import DemandSales.SalesDemandJunction;
import DemandSales.SalesDemandMsgModel;
import DemandSales.SalesMessageDetailsModel;
import Quality.DemanQualityApprovalDBQueris;
import Quality.IDemandQualityApproval;
import biscutfactorygui.Activator;
import biscutfactorygui.BackEndDetails;
import biscutfactorygui.BackEndMessageDetailsModel;
import biscutfactorygui.DateLabelFormatter;
import biscutfactorygui.DemandRequestDataModel;
import biscutmanafacture.BiscutManafactureJunction;
import biscutmanafacture.ManufactureStore;

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
	//private Idemand iDemand;
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
	private JTable table_1;
	
	
	private JTextField textField;
	private JTextField textField_1;
	
	UtilDateModel startDate_1;
	UtilDateModel startDate_2;


	/**
	 * Launch the application.
	 */
	public static void executeDemandGUI()  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemandRequestGUI window = new DemandRequestGUI();
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

	
	public DemandRequestGUI() {
		initialize();
		
	//	Activator.idemandRequstDBQueries = new DemandRequestDBQueries();
		//Activator.iDemandQualityApproval = new DemanQualityApprovalDBQueris();
		
		ResultSet rs2 = Activator.iDemandQualityApproval.viewAllDemandRequstes();
		try {
			while(rs2.next()) {
				
				System.out.println(rs2.getString("demandReqId"));		
							
				DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
				Vector v = new Vector();
				
				v.add(rs2.getString("demandReqId"));
				v.add(rs2.getString("reqDate"));
				v.add(rs2.getString("demadReason"));
				v.add(rs2.getString("salesMsgId"));
				v.add(rs2.getString("status"));
				dtm.addRow(v);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1366, 768);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSalesRequest = new JLabel("Sales Request");
		lblSalesRequest.setForeground(Color.BLACK);
		lblSalesRequest.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSalesRequest.setBounds(222, 166, 174, 34);
		frame.getContentPane().add(lblSalesRequest);
		
		JComboBox comboBox = new JComboBox();

		comboBox.setBounds(535, 168, 239, 34);
		frame.getContentPane().add(comboBox);
		
		JLabel lblRequiredDate = new JLabel("Required Date");
		lblRequiredDate.setForeground(Color.BLACK);
		lblRequiredDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRequiredDate.setBounds(222, 278, 174, 34);
		frame.getContentPane().add(lblRequiredDate);
		
		txtReqDate = new JTextField();
		txtReqDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtProductName.grabFocus();
			}
		});

		
		
		JButton btnDelete = new JButton("DELETE ");

		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnDelete.setBounds(624, 212, 150, 40);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.BLACK);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDescription.setBounds(868, 166, 174, 34);
		frame.getContentPane().add(lblDescription);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setForeground(Color.BLACK);
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setBounds(222, 353, 174, 34);
		frame.getContentPane().add(lblProductName);
		
		txtProductName = new JTextField();
	
		txtProductName.setColumns(10);
		txtProductName.setBounds(535, 353, 239, 34);
		frame.getContentPane().add(txtProductName);
		
		txtQuantity = new JTextField();
	
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(678, 397, 96, 34);
		frame.getContentPane().add(txtQuantity);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(Color.BLACK);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuantity.setBounds(222, 397, 174, 34);
		frame.getContentPane().add(lblQuantity);
		
		JTextArea textReason = new JTextArea();
		textReason.setBounds(222, 501, 552, 116);
		frame.getContentPane().add(textReason);
		
		JTextArea txtDescription = new JTextArea();
		txtDescription.setBounds(868, 195, 389, 57);
		frame.getContentPane().add(txtDescription);
		
		JLabel lblReasonForRequest = new JLabel("Reason for request");
		lblReasonForRequest.setForeground(Color.BLACK);
		lblReasonForRequest.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReasonForRequest.setBounds(222, 462, 239, 34);
		frame.getContentPane().add(lblReasonForRequest);
		
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		UtilDateModel startDate_Req = new UtilDateModel();
		JDatePanelImpl startPanel_Req = new JDatePanelImpl(startDate_Req,p);
		DateLabelFormatter startLabelFormatter_Exp = new DateLabelFormatter();
		JDatePickerImpl startPickerImpl_ExpDate = new JDatePickerImpl(startPanel_Req, startLabelFormatter_Exp);
		startPickerImpl_ExpDate.setBounds(535, 278, 239, 34);
		frame.getContentPane().add(startPickerImpl_ExpDate);
		
		
		
		
		JButton btnAddDemand = new JButton("ADD PRODUCT");
	
		btnAddDemand.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnAddDemand.setBounds(222, 697, 552, 40);
		frame.getContentPane().add(btnAddDemand);
		
		comboBox.addItem("Select Request..");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Wearhousing Management");
		mnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menuBar.add(mnNewMenu);
		
		JMenu mnLogistics = new JMenu("Logistics Management");
		menuBar.add(mnLogistics);
		
		JMenu mnManufacture = new JMenu("Manufacture Controlling");
		mnManufacture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManufactureStore manufactureStore = new BiscutManafactureJunction();
				BiscutManufactureDetailsGUI.executeMainGUI();
			}
		});
		menuBar.add(mnManufacture);
		
		JMenu mnDemandForecasting = new JMenu("Demand Forecasting");
		mnDemandForecasting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Activator.iDemand = new SalesDemandJunction();
				DemandRequestGUI.executeDemandGUI();
			}
		});
		menuBar.add(mnDemandForecasting);
		
		JMenu mnSalesForecasting = new JMenu("Sales Forecasting");
		menuBar.add(mnSalesForecasting);
		
		JMenu mnQualityManagement = new JMenu("Quality Management");
		menuBar.add(mnQualityManagement);
		
		JMenu mnFinanceManagement = new JMenu("Finance Management");
		menuBar.add(mnFinanceManagement);
		
		JMenu mnTransportManagement = new JMenu("Transport Management");
		menuBar.add(mnTransportManagement);
		
		table = new JTable();
		table.setBounds(870, 310, 387, 116);
		frame.getContentPane().add(table);
		
		table.setModel(new javax.swing.table.DefaultTableModel(
			new Object[][] {
				{"Product Name","Quantity"}
			},
			new String[] {
					"ProductName","Quantity"
			}
				
		));
		
		JLabel lblProductionDemandForecast = new JLabel("Production Demand Forecasting Control  Pannel");
		lblProductionDemandForecast.setForeground(Color.DARK_GRAY);
		lblProductionDemandForecast.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblProductionDemandForecast.setBounds(222, 36, 1095, 77);
		frame.getContentPane().add(lblProductionDemandForecast);
		
		JLabel lblCurrentPos = new JLabel("Current POS");
		lblCurrentPos.setForeground(Color.BLACK);
		lblCurrentPos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCurrentPos.setBounds(870, 278, 174, 34);
		frame.getContentPane().add(lblCurrentPos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(868, 539, 589, 198);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		
		table_1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
						"Demand ID","Req Date","Req Reason","Sales ID","Status"
				}
					
		));

		
		
		
		 startDate_1 = new UtilDateModel();
		JDatePanelImpl startPanel_1 = new JDatePanelImpl(startDate_1,p);
		DateLabelFormatter startLabelFormatter_1 = new DateLabelFormatter();
		JDatePickerImpl startPickerImpl_1 = new JDatePickerImpl(startPanel_1, startLabelFormatter_1);
		startPickerImpl_1.setBounds(868, 462, 239, 34);
		frame.getContentPane().add(startPickerImpl_1);
		
		
		 startDate_2 = new UtilDateModel();
		JDatePanelImpl startPanel_2 = new JDatePanelImpl(startDate_2,p);
		DateLabelFormatter startLabelFormatter_2 = new DateLabelFormatter();
		JDatePickerImpl startPickerImpl_2 = new JDatePickerImpl(startPanel_2, startLabelFormatter_2);
		startPickerImpl_2.setBounds(1218, 462, 239, 34);
		frame.getContentPane().add(startPickerImpl_2);
		
		JButton btnSeacrhDate = new JButton("FILTER");
		//fILTER dates
		btnSeacrhDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getBetweenDateRequests();
			}
		});
		btnSeacrhDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSeacrhDate.setBounds(1331, 501, 126, 34);
		frame.getContentPane().add(btnSeacrhDate);
		
		
		
		
	
		
		//creating the reference of SQL Backend
		Activator.idemandRequstDBQueries = new DemandRequestDBQueries();
	
		//salese msg model
		SalesDemandMsgModel salesDemandMsgModel = new SalesDemandMsgModel();			
		
		
		
		
		//filling data to the combo box
		try {
			ResultSet rs = Activator.idemandRequstDBQueries.LoadSalesMessages();
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
						
							ResultSet rs1 = Activator.idemandRequstDBQueries.getSelectedSalesMessage(Integer.parseInt(comboBox.getSelectedItem().toString()));
							
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
		
		
		
		//Delete Sales Message
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				salesDemandMsgModel.setMsgId(Integer.parseInt(comboBox.getSelectedItem().toString()));
				boolean result = Activator.idemandRequstDBQueries.deleteSalesMsg(salesDemandMsgModel.getMsgId());
				
				int x= JOptionPane.showConfirmDialog(null,"Do you want to Delete this record ?");
				if(x == 0) {
					if(result == true) {
						JOptionPane.showMessageDialog(null,"Deleted Successfully!!!");	
					}else {
						JOptionPane.showMessageDialog(null,"Fail to Delete!!!");
					}
					
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
					
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
					String reqDate = sdf.format(startDate_Req.getValue());
					
					if(x==0) {
			
						//setting values to the demand request textfileds and call setters
						salesDemandMsgModel.setMsgId(Integer.parseInt(comboBox.getSelectedItem().toString()));
						salesDemandMsgModel.setReqDate(reqDate);
						salesDemandMsgModel.setDemandRequest(fileData);
						salesDemandMsgModel.setDescription(textReason.getText());
						

						Boolean result = Activator.idemandRequstDBQueries.insertDemandReq(salesDemandMsgModel);
						if(result == true) {
							
							//send to the BiscutDemand Bundle
							JOptionPane.showMessageDialog(null, "Successfully Added!!!");
							txtReqDate.setText("");
							textReason.setText("");
							getBetweenDateRequests();
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
	
	
	
	public void getBetweenDateRequests() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		String date_1 = sdf.format(startDate_1.getValue());
		String date_2 = sdf.format(startDate_2.getValue());
		
		SalesMessageDetailsModel salesDemandMsgModel = new SalesMessageDetailsModel();
		
		
		salesDemandMsgModel.setDate_1(date_1);
		salesDemandMsgModel.setDate_2(date_2);
		
		ResultSet rs_betweenDates = Activator.idemandRequstDBQueries.viewDemandsOnDate(salesDemandMsgModel);
		
		try {
			
			if(rs_betweenDates.next() == false) {
				JOptionPane.showMessageDialog(null,"No Data Found!!!");
			}
			while(rs_betweenDates.next()) {
				
				System.out.println(rs_betweenDates.getString("demandReqId"));		
							
				DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
				Vector v = new Vector();
				
				v.add(rs_betweenDates.getString("demandReqId"));
				v.add(rs_betweenDates.getString("reqDate"));
				v.add(rs_betweenDates.getString("demadReason"));
				v.add(rs_betweenDates.getString("salesMsgId"));
				v.add(rs_betweenDates.getString("status"));
				dtm.addRow(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
