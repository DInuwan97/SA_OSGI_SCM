package Warehouse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import warehouse.AbstractWarehouse;
import warehouse.RawMaterials;
import warehouse.RawMaterialsImpl;
import warehouse.Warehouse;

import java.awt.EventQueue;
import java.awt.Font;

public class UpdateRawMaterial extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	private JTextField txtStoredTemp;
	private JTextField txtPrice;
	public  static Warehouse warh;

	/**
	 * Launch the application.
	 */
	public void main(RawMaterials rawMaterialsUpdate) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateRawMaterial frame = new UpdateRawMaterial( rawMaterialsUpdate);
					frame.setVisible(true);
					frame.setAlwaysOnTop(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateRawMaterial(RawMaterials rawMaterialsUpdate) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Update Raw Material Batch");
		setBounds(100, 100, 421, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		if (rawMaterialsUpdate == null) {
			dispose();
		}

		AbstractWarehouse warehouse = warh.getFactory("rawmaterials");

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(47, 74, 131, 14);
		contentPane.add(lblName);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(47, 105, 131, 14);
		contentPane.add(lblAmount);

		JLabel lblStoredDate = new JLabel("Stored Date");
		lblStoredDate.setBounds(47, 136, 131, 14);
		contentPane.add(lblStoredDate);

		JLabel lblManufactureDate = new JLabel("Manufacture Date");
		lblManufactureDate.setBounds(47, 168, 131, 14);
		contentPane.add(lblManufactureDate);

		JLabel lblExpireDate = new JLabel("Expire Date");
		lblExpireDate.setBounds(47, 199, 131, 14);
		contentPane.add(lblExpireDate);

		JLabel lblStoredTemperature = new JLabel("Stored Temperature");
		lblStoredTemperature.setBounds(47, 230, 131, 14);
		contentPane.add(lblStoredTemperature);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(47, 261, 131, 14);
		contentPane.add(lblPrice);

		JComboBox<String> comboName = new JComboBox<String>();
		comboName.setEditable(true);
		comboName.setBounds(169, 70, 209, 22);
		contentPane.add(comboName);

		ArrayList<String> nameList = warehouse.getRawMaterilNames();
		if (nameList != null) {
			for (String string : nameList) {
				comboName.addItem(string);
			}
		}
		comboName.setSelectedItem(rawMaterialsUpdate.getName());

		txtAmount = new JTextField();
		txtAmount.setBounds(169, 102, 209, 20);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		txtAmount.setText(String.valueOf((rawMaterialsUpdate.getAmount())));

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		UtilDateModel store = new UtilDateModel();
		store.setValue(rawMaterialsUpdate.getStoredDate());
		JDatePanelImpl storePanel = new JDatePanelImpl(store, p);
		DateLabelFormatter storeDateLabelFormatter = new DateLabelFormatter();
		JDatePickerImpl storeDate = new JDatePickerImpl(storePanel, storeDateLabelFormatter);
		storeDate.setBounds(169, 133, 209, 23);
		contentPane.add(storeDate);

		UtilDateModel manu = new UtilDateModel();
		manu.setValue(rawMaterialsUpdate.getManufactureDate());
		JDatePanelImpl manuPanel = new JDatePanelImpl(manu, p);
		DateLabelFormatter manuDateLabelFormatter = new DateLabelFormatter();
		JDatePickerImpl manufactureDate = new JDatePickerImpl(manuPanel, manuDateLabelFormatter);
		manufactureDate.setBounds(169, 162, 209, 23);
		contentPane.add(manufactureDate);

		UtilDateModel expire = new UtilDateModel();
		expire.setValue(rawMaterialsUpdate.getExpireDate());
		JDatePanelImpl expPanel = new JDatePanelImpl(expire, p);
		DateLabelFormatter expDateLabelFormatter = new DateLabelFormatter();
		JDatePickerImpl expireDate = new JDatePickerImpl(expPanel, expDateLabelFormatter);

		expireDate.setBounds(169, 193, 209, 20);
		contentPane.add(expireDate);

		txtStoredTemp = new JTextField();
		txtStoredTemp.setBounds(169, 227, 209, 20);
		contentPane.add(txtStoredTemp);
		txtStoredTemp.setColumns(10);
		txtStoredTemp.setText(String.valueOf(rawMaterialsUpdate.getStoreTemperature()));

		txtPrice = new JTextField();
		txtPrice.setBounds(169, 258, 209, 20);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		txtPrice.setText(String.valueOf(rawMaterialsUpdate.getPrice()));

		JButton btnInsert = new JButton("Update Raw Material");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (comboName.getSelectedItem() != null && !txtAmount.getText().equals("") && !txtPrice.equals("")
							&& !txtStoredTemp.equals("")) {
						String name = (String) comboName.getSelectedItem();
						double price = Double.parseDouble(txtPrice.getText());
						double amount = Double.parseDouble(txtAmount.getText());
						double storedTemp = Double.parseDouble(txtStoredTemp.getText());

						Date manuDate = manu.getValue();
						Date expDate = expire.getValue();
						Date stoDate = store.getValue();

						if (price >= 0 && amount >= 0) {
							if (manuDate.before(expDate) && stoDate.after(manuDate)) {
								RawMaterialsImpl rawMaterials = new RawMaterialsImpl();
								rawMaterials.setId(rawMaterialsUpdate.getId());
								rawMaterials.setName(name);
								rawMaterials.setAmount(amount);
								rawMaterials.setExpireDate(expDate);
								rawMaterials.setPrice(price);
								rawMaterials.setStoreTemperature(storedTemp);
								rawMaterials.setManufactureDate(manuDate);
								rawMaterials.setStoredDate(stoDate);
								warehouse.updateRawMaterial(rawMaterials);

								dispose();
							}
						}
					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(contentPane, "Error!", "Error!", JOptionPane.CANCEL_OPTION);

				}
			}
		});
		btnInsert.setBounds(207, 289, 171, 23);
		contentPane.add(btnInsert);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboName.setSelectedItem("");
				txtAmount.setText("");
				txtPrice.setText("");
				txtStoredTemp.setText("");
			}
		});
		btnClear.setBounds(47, 289, 150, 23);
		contentPane.add(btnClear);

		JLabel lblUpdate = new JLabel("UPDATE");
		lblUpdate.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
		lblUpdate.setBounds(138, 11, 131, 37);
		contentPane.add(lblUpdate);

	}

}
