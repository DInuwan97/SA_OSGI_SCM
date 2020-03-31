package SwingGUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import DemandSales.Idemand;
import DemandSales.IdemandRequstDBQueries;
import DemandSales.SalesDemandJunction;
import Quality.DemanQualityApprovalDBQueris;
import Quality.IDemandQualityApproval;
import biscutfactorygui.Activator;
import biscutfactorygui.DateLabelFormatter;
import biscutmanafacture.BiscutManafactureJunction;
import biscutmanafacture.BiscutModel;
import biscutmanafacture.IBiscuitManufactureDBQuries;
import biscutmanafacture.ManufactureBiscuitDBQueries;
import biscutmanafacture.ManufactureStore;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;
import java.text.SimpleDateFormat;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
public class BiscutManufactureDetailsGUI  {

	
	private JFrame frame;
	private JTextField txtManDate;
	private JTextField txtExpDate;
	private JTextField txtquantity;
	private JTextField txtIngredients;
	private JTextField txtMachines;
	private JTextField txtEmployees;
	private JTable table;
	private JTextField txtSearch;
	private JTable table_1;
	

	/**
	 * Launch the application.
	 */
	public static void executeMainGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BiscutManufactureDetailsGUI window = new BiscutManufactureDetailsGUI();
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
	

	
	public BiscutManufactureDetailsGUI() {
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		
		JLabel lblProductName = new JLabel("Manufacture Date");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setForeground(Color.BLACK);
		lblProductName.setBounds(175, 299, 174, 34);
		frame.getContentPane().add(lblProductName);
		
		JLabel label = new JLabel("Product Name");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(175, 238, 174, 34);
		frame.getContentPane().add(label);
		
		JLabel lblExpireDate = new JLabel("Quantity");
		lblExpireDate.setForeground(Color.BLACK);
		lblExpireDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblExpireDate.setBounds(175, 416, 174, 34);
		frame.getContentPane().add(lblExpireDate);
		
		JLabel label_1 = new JLabel("Expire Date");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_1.setBounds(175, 359, 174, 34);
		frame.getContentPane().add(label_1);
		
		JLabel lblIngridents = new JLabel("Machines");
		lblIngridents.setForeground(Color.BLACK);
		lblIngridents.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIngridents.setBounds(175, 542, 174, 34);
		frame.getContentPane().add(lblIngridents);
		
		JLabel label_2 = new JLabel("Ingridents");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_2.setBounds(175, 479, 174, 34);
		frame.getContentPane().add(label_2);
		
		JLabel lblEmployees = new JLabel("Employees");
		lblEmployees.setForeground(Color.BLACK);
		lblEmployees.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmployees.setBounds(175, 610, 174, 34);
		frame.getContentPane().add(lblEmployees);
		
		JComboBox categories = new JComboBox();
	
		categories.setBounds(437, 238, 239, 34);
		frame.getContentPane().add(categories);
		
		
		//adding categories into combo box
		categories.addItem("LemmonPuff");
		categories.addItem("ChocoCream");
		//adding categories into combo box
		
		
		txtManDate = new JTextField();
		
		txtManDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtExpDate.grabFocus();
			}
		});
		
		//txtManDate.setBounds(437, 300, 239, 34);
		frame.getContentPane().add(txtManDate);
		txtManDate.setColumns(10);
		
		txtExpDate = new JTextField();
		txtExpDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtquantity.grabFocus();
			}
		});
		//txtExpDate.setColumns(10);
		//txtExpDate.setBounds(437, 360, 239, 34);
		//frame.getContentPane().add(txtExpDate);
		
		txtquantity = new JTextField();
		txtquantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIngredients.grabFocus();
			}
		});
		txtquantity.setColumns(10);
		txtquantity.setBounds(437, 414, 239, 34);
		frame.getContentPane().add(txtquantity);
		
		txtIngredients = new JTextField();
		txtIngredients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMachines.grabFocus();
			}
		});
		txtIngredients.setColumns(10);
		txtIngredients.setBounds(437, 477, 239, 34);
		frame.getContentPane().add(txtIngredients);
		
		txtMachines = new JTextField();
		txtMachines.setColumns(10);
		txtMachines.setBounds(437, 543, 239, 34);
		frame.getContentPane().add(txtMachines);
		
		txtEmployees = new JTextField();
		txtEmployees.setColumns(10);
		txtEmployees.setBounds(437, 611, 239, 34);
		frame.getContentPane().add(txtEmployees);
		
		JButton btnAddProduct = new JButton("ADD PRODUCT");
		btnAddProduct.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnAddProduct.setBounds(526, 683, 150, 40);
		frame.getContentPane().add(btnAddProduct);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(739, 548, 660, 156);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
						"Demand ID","Req Date","Reason"
				}
					
		));
		
		JComboBox comboDemandId = new JComboBox();

		comboDemandId.setBounds(437, 174, 239, 34);
		frame.getContentPane().add(comboDemandId);
		
		JLabel lblDemandReqId = new JLabel("Demand Req ID");
		lblDemandReqId.setForeground(Color.BLACK);
		lblDemandReqId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDemandReqId.setBounds(175, 174, 174, 34);
		frame.getContentPane().add(lblDemandReqId);
		
		txtSearch = new JTextField();
	
		txtSearch.setColumns(10);
		txtSearch.setBounds(1215, 298, 184, 34);
		frame.getContentPane().add(txtSearch);
		
		JLabel lblSearchDemandId = new JLabel("Search Demand ID");
		lblSearchDemandId.setForeground(Color.BLACK);
		lblSearchDemandId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSearchDemandId.setBounds(1031, 299, 174, 34);
		frame.getContentPane().add(lblSearchDemandId);
		
		JTextArea txtProductionInfo = new JTextArea();
		txtProductionInfo.setBounds(906, 172, 493, 96);
		frame.getContentPane().add(txtProductionInfo);
		
		JLabel lblProductionInfo = new JLabel("Production Info");
		lblProductionInfo.setForeground(Color.BLACK);
		lblProductionInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductionInfo.setBounds(739, 170, 150, 34);
		frame.getContentPane().add(lblProductionInfo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(741, 342, 658, 156);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		table_1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
						"ManufactureID","Manu Date","Exp Date","Product Name","Qty","Employees","Machines","Demand ID",
				}
					
		));
		
		JLabel lblTransportPending = new JLabel("Demand Requested Products");
		lblTransportPending.setForeground(Color.BLACK);
		lblTransportPending.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTransportPending.setBounds(739, 518, 325, 34);
		frame.getContentPane().add(lblTransportPending);
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		UtilDateModel startDate_Man = new UtilDateModel();
		JDatePanelImpl startPanel_Man = new JDatePanelImpl(startDate_Man,p);
		DateLabelFormatter startLabelFormatter_Man = new DateLabelFormatter();
		JDatePickerImpl startPickerImpl_ManDate = new JDatePickerImpl(startPanel_Man, startLabelFormatter_Man);
		startPickerImpl_ManDate.setBounds(437, 300, 239, 34);
		frame.getContentPane().add(startPickerImpl_ManDate);
		
		
		UtilDateModel startDate_Exp = new UtilDateModel();
		JDatePanelImpl startPanel_Exp = new JDatePanelImpl(startDate_Exp,p);
		DateLabelFormatter startLabelFormatter_Exp = new DateLabelFormatter();
		JDatePickerImpl startPickerImpl_ExpDate = new JDatePickerImpl(startPanel_Exp, startLabelFormatter_Exp);
		startPickerImpl_ExpDate.setBounds(437, 360, 239, 34);
		frame.getContentPane().add(startPickerImpl_ExpDate);
		


		
		//Demand Id select from Demand ID Combo Box
		comboDemandId.addItem("Select Demand Request ID...");
		
		JButton btnUpdateProduct = new JButton("UPDATE PRODUCT");
	
		btnUpdateProduct.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnUpdateProduct.setBounds(295, 683, 201, 40);
		frame.getContentPane().add(btnUpdateProduct);
		
		JLabel lblProductionManufatureControlling = new JLabel("Production Manufature Control Panel");
		lblProductionManufatureControlling.setForeground(Color.DARK_GRAY);
		lblProductionManufatureControlling.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblProductionManufatureControlling.setBounds(358, 49, 888, 77);
		frame.getContentPane().add(lblProductionManufatureControlling);
		
		JLabel label_3 = new JLabel("Transport Pending");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_3.setBounds(739, 300, 162, 34);
		frame.getContentPane().add(label_3);
		
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
				//ManufactureStore manufactureStore = new BiscutManafactureJunction();
				//BiscutManufactureDetailsGUI.executeMainGUI();
			}
		});
		menuBar.add(mnManufacture);
		
		JMenu mnDemandForecasting = new JMenu("Demand Forecasting");
		mnDemandForecasting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Idemand iDemand = new SalesDemandJunction();
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
		
		
		ResultSet rs_DemandID_combo = Activator.iDemandQualityApproval.viewAllDemandRequstes();
		
		try {
			while(rs_DemandID_combo.next()) {
				comboDemandId.addItem(rs_DemandID_combo.getString("demandReqId"));
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		

		
		comboDemandId.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(!comboDemandId.getSelectedItem().toString().equals("Select Request..")) {
							
						try {
														
							ResultSet rs1 = Activator.iDemandQualityApproval.viewDemandRequstbyId(Integer.parseInt(comboDemandId.getSelectedItem().toString()));
							
							while(rs1.next()) {
								txtProductionInfo.setText(rs1.getString("productdetails"));
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				
				}
			}
		});
		
		
		
		updateManufatureDataTable();
		BiscutModel biscutModel = new BiscutModel();
		
		//event handler for the ADDPRODUCT Button
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
				String expDate = sdf.format(startDate_Exp.getValue());
				String manDate = sdf.format(startDate_Man.getValue());
				
				try {
					//ask to save 
					int x = JOptionPane.showConfirmDialog(null, "Do Yu want to save this record?");
					
					if(x==0) {
						
						
						
						
						System.out.println("Man DAte : " +manDate);
						biscutModel.setBiscutName(categories.getSelectedItem().toString());
						biscutModel.setExpireDate(expDate);
						biscutModel.setManufactureDate(manDate);
						biscutModel.setIngridents(txtIngredients.getText().toString());
						biscutModel.setManufactAmount(Integer.parseInt(txtquantity.getText()));
						biscutModel.setNoOfMachines(Integer.parseInt(txtMachines.getText()));
						biscutModel.setNumOfEmployees(Integer.parseInt(txtEmployees.getText()));
						biscutModel.setDemandReqId(Integer.parseInt(comboDemandId.getSelectedItem().toString()));
						
						
						Activator.manufactureStore.createBiscut(biscutModel);
						
						//getting ack after insert to db
						Boolean result =  Activator.manufactureStore.isBiscutProductionInsertered();
						
						if(result == true) {
							//updateManufatureDataTable();
							JOptionPane.showMessageDialog(null, "Successfully Added!!!");
							clear();
							//ioBiscutDetails.OutPutBiscutDetails(biscutModel);	//display enterd product details in console
						}else {
							JOptionPane.showMessageDialog(null, "Fail to Add!!!");
						}
						
					}
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
				
			}
		});
		
		
		
		//search manufacture id
				txtSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ResultSet rs_Searched_biscuit_details = Activator.iBiscuitManufactureDBQuries.searchManufaturedProduct(Integer.parseInt(txtSearch.getText().toString()));
						
						try {
							if(rs_Searched_biscuit_details.next()) {
							
								//startDate_Man.set
								startDate_Man.setValue(rs_Searched_biscuit_details.getDate("manufactureDate"));
								startDate_Exp.setValue(rs_Searched_biscuit_details.getDate("expireDate"));
								//txtExpDate.setText(rs_Searched_biscuit_details.getString("expireDate"));
								txtIngredients.setText(rs_Searched_biscuit_details.getString("materials"));
								txtMachines.setText(rs_Searched_biscuit_details.getString("noOfMachines"));
								categories.setSelectedItem(rs_Searched_biscuit_details.getString("biscutName"));
								txtquantity.setText(rs_Searched_biscuit_details.getString("manaufactAmount"));
								txtEmployees.setText(rs_Searched_biscuit_details.getString("noOfEmployees"));
								comboDemandId.setSelectedItem(rs_Searched_biscuit_details.getString("demandReqId"));

							}
							else {
								JOptionPane.showMessageDialog(null,"Invalid Production ID!!!");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				
							
				//UPDATE BISCUIT DETAILS
				btnUpdateProduct.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
						String expDate = sdf.format(startDate_Man.getValue());
						String manDate = sdf.format(startDate_Man.getValue());
						
						int x = JOptionPane.showConfirmDialog(null, "Do you want to Update this record ? ");
						if(x == 0) {
							
				
							biscutModel.setBiscutName(categories.getSelectedItem().toString());
							biscutModel.setExpireDate(expDate);
							biscutModel.setManufactureDate(manDate);
							biscutModel.setIngridents(txtIngredients.getText().toString());
							biscutModel.setManufactAmount(Integer.parseInt(txtquantity.getText().toString()));
							biscutModel.setNoOfMachines(Integer.parseInt(txtMachines.getText().toString()));
							biscutModel.setNumOfEmployees(Integer.parseInt(txtEmployees.getText().toString()));
							
							Boolean result = Activator.iBiscuitManufactureDBQuries.updateManufactureProductDetails(biscutModel);
							if(result == true) {
								
								JOptionPane.showMessageDialog(null, "Successfully Updated!!!");
								clear();
								
							}else {
								JOptionPane.showMessageDialog(null, "Fail to Update!!!");
							}
						}
					}
				});
				
		
	}
	
	public void updateManufatureDataTable() {
	
		
		ResultSet rs2 = Activator.iDemandQualityApproval.viewAllDemandRequstes();
		try {
			while(rs2.next()) {
				
				System.out.println(rs2.getString("demandReqId"));		
							
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				Vector v = new Vector();
				
				v.add(rs2.getString("demandReqId"));
				v.add(rs2.getString("reqDate"));
				v.add(rs2.getString("demadReason"));
				dtm.addRow(v);
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs3 = Activator.iBiscuitManufactureDBQuries.getAllManfautureProducts();
		
		try {
			while(rs3.next()) {
				DefaultTableModel dtm2 = (DefaultTableModel) table_1.getModel();
				Vector v2 = new Vector();
				
				v2.add(rs3.getString("manufactureId"));
				v2.add(rs3.getString("manufactureDate"));
				v2.add(rs3.getString("expireDate"));
				v2.add(rs3.getString("biscutName"));
				v2.add(rs3.getString("manaufactAmount"));
				v2.add(rs3.getString("noOfEmployees"));
				v2.add(rs3.getString("noOfMachines"));
				v2.add(rs3.getString("demandReqId"));
				dtm2.addRow(v2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clear() {
		txtEmployees.setText("");
		txtExpDate.setText("");
		txtIngredients.setText("");
		txtMachines.setText("");
		txtManDate.setText("");
		txtquantity.setText("");
	}
	
	private int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);    
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    private int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        return month;
    }

    private int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }
}
