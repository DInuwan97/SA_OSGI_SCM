package DinukaGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import biscutfactorygui.Activator;

import javax.swing.JComboBox;
import javax.swing.JTextField;


import salespublisher.SalesModel;
import salespublisher.SalesService;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalesGui {

	private JFrame frame;
	private JTextField txtmessage;
	private JTextField txtAddTime;
	private JTextField txtdistance;
	private JTextField txtkm;
	private JTextField txttotalCost;
	//private SalesService salesService;
	private int salesId = 1;
	private JTextField dateChooser;
	/**
	 * Launch the application.
	 */
	public static void executeSalesGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesGui window = new SalesGui();
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
	public SalesGui() {
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
		
		JLabel lblManufacturer = new JLabel("Manufacturer");
		lblManufacturer.setHorizontalAlignment(SwingConstants.CENTER);
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblManufacturer.setBounds(54, 165, 203, 61);
		frame.getContentPane().add(lblManufacturer);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMessage.setBounds(54, 279, 203, 61);
		frame.getContentPane().add(lblMessage);
		
		JLabel lblAddedTime = new JLabel("Added Time");
		lblAddedTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddedTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddedTime.setBounds(54, 511, 203, 61);
		frame.getContentPane().add(lblAddedTime);
		
		JLabel label_1 = new JLabel("Manufacturer");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(54, 397, 203, 61);
		frame.getContentPane().add(label_1);
		
		JLabel lblSalesPanel = new JLabel("SALES PANEL");
		lblSalesPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesPanel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSalesPanel.setBounds(219, 21, 420, 61);
		frame.getContentPane().add(lblSalesPanel);
		
		JComboBox combo = new JComboBox();
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salesId = combo.getSelectedIndex();
			}
		});
		combo.setBounds(340, 165, 217, 43);
		combo.setModel(new DefaultComboBoxModel(new String[] {"Munchee", "Maliban", "Hawain", "Crisco", "Oreo"}));
		frame.getContentPane().add(combo);
		
		txtmessage = new JTextField();
		txtmessage.setBounds(340, 279, 217, 42);
		frame.getContentPane().add(txtmessage);
		txtmessage.setColumns(10);

		
		txtAddTime = new JTextField();
		txtAddTime.setBounds(340, 511, 217, 42);
		frame.getContentPane().add(txtAddTime);
		txtAddTime.setColumns(10);
		
		JButton btnNewButton = new JButton("Request Demand");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesModel smodel = new  SalesModel();
				smodel.setSaleesid(1);
				smodel.setMessage(txtmessage.getText().toString());
				smodel.setDate("2019/01/12");
				smodel.setTime(txtAddTime.getText().toString());
				
				int x = JOptionPane.showConfirmDialog(null,"Do you  want to save this Record?");
				if(x == 0) {
					boolean result = (Activator.salesService).sendDemandRequest(smodel);
					if(result == true) {
						JOptionPane.showMessageDialog(null,"Success!!!");
						reset(); 
					}else {
						JOptionPane.showMessageDialog(null,"Fail!!!");
						reset(); 
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(239, 619, 231, 61);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblTransportatoionCost = new JLabel("Transportatoion Cost");
		lblTransportatoionCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransportatoionCost.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTransportatoionCost.setBounds(903, 21, 420, 61);
		frame.getContentPane().add(lblTransportatoionCost);
		
		JLabel lblDistanceTravelled = new JLabel("Distance Travelled");
		lblDistanceTravelled.setHorizontalAlignment(SwingConstants.CENTER);
		lblDistanceTravelled.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDistanceTravelled.setBounds(879, 165, 203, 61);
		frame.getContentPane().add(lblDistanceTravelled);
		
		JLabel lblCost = new JLabel("Cost Per Km");
		lblCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCost.setBounds(879, 279, 203, 61);
		frame.getContentPane().add(lblCost);
		
		JLabel lblTotalCost = new JLabel("Total Cost");
		lblTotalCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalCost.setBounds(879, 397, 203, 61);
		frame.getContentPane().add(lblTotalCost);
		
		txtdistance = new JTextField();
		txtdistance.setColumns(10);
		txtdistance.setBounds(1158, 177, 217, 42);
		frame.getContentPane().add(txtdistance);
		
		txtkm = new JTextField();
		txtkm.setColumns(10);
		txtkm.setBounds(1158, 279, 217, 42);
		frame.getContentPane().add(txtkm);
		
		txttotalCost = new JTextField();
		txttotalCost.setColumns(10);
		txttotalCost.setBounds(1158, 397, 217, 42);
		frame.getContentPane().add(txttotalCost);
		
		JButton button = new JButton("Request Demand");
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setBounds(1025, 619, 231, 61);
		frame.getContentPane().add(button);
		
		dateChooser = new JTextField();
		dateChooser.setColumns(10);
		dateChooser.setBounds(340, 408, 217, 42);
		frame.getContentPane().add(dateChooser);
	}
	
	public void reset() {
		 txtmessage.setText("");
		txtAddTime.setText("");
		 txtdistance.setText("");
		 txtkm.setText("");
		 txttotalCost.setText("");
	}
}
