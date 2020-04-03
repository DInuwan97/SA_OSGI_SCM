package Warehouse;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
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
import warehouse.FinalProducts;
import warehouse.FinalProductsImpl;
import warehouse.RawMaterialsImpl;
import warehouse.Warehouse;
import warehouse.WarehouseFactoryProducer;

public class UpdateFinalProduct extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	private JTextField txtStoredTemp;
	private JTextField txtPrice;
	private JTextField txtIngredients;

	public  static Warehouse warh;

	/**
	 * Launch the application.
	 */
	public void main(FinalProducts finalProductsUpdate) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateFinalProduct frame = new UpdateFinalProduct(finalProductsUpdate);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateFinalProduct(FinalProducts finalProductsUpdate) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Update Final Product Batch");
		setBounds(100, 100, 421, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		if (finalProductsUpdate == null) {
			dispose();
		}

		AbstractWarehouse warehouse = warh.getFactory("finalproducts");

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

		JLabel lblIngredients = new JLabel("Ingredients");
		lblIngredients.setBounds(47, 293, 131, 14);
		contentPane.add(lblIngredients);

		JComboBox<String> comboName = new JComboBox<String>();
		comboName.setEditable(true);
		comboName.setBounds(169, 70, 209, 22);
		contentPane.add(comboName);

		ArrayList<String> nameList = warehouse.getFinalProductNames();
		if (nameList != null) {
			for (String string : nameList) {
				comboName.addItem(string);
			}
		}
		comboName.setSelectedItem(finalProductsUpdate.getName());

		txtAmount = new JTextField();
		txtAmount.setBounds(169, 102, 209, 20);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		txtAmount.setText(String.valueOf((finalProductsUpdate.getAmount())));

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		UtilDateModel store = new UtilDateModel();
		store.setValue(finalProductsUpdate.getStoredDate());
		JDatePanelImpl storePanel = new JDatePanelImpl(store, p);
		DateLabelFormatter storeDateLabelFormatter = new DateLabelFormatter();
		JDatePickerImpl storeDate = new JDatePickerImpl(storePanel, storeDateLabelFormatter);
		storeDate.setBounds(169, 133, 209, 23);
		contentPane.add(storeDate);

		UtilDateModel manu = new UtilDateModel();
		manu.setValue(finalProductsUpdate.getManufactureDate());
		JDatePanelImpl manuPanel = new JDatePanelImpl(manu, p);
		DateLabelFormatter manuDateLabelFormatter = new DateLabelFormatter();
		JDatePickerImpl manufactureDate = new JDatePickerImpl(manuPanel, manuDateLabelFormatter);
		manufactureDate.setBounds(169, 162, 209, 23);
		contentPane.add(manufactureDate);

		UtilDateModel expire = new UtilDateModel();
		expire.setValue(finalProductsUpdate.getExpireDate());
		JDatePanelImpl expPanel = new JDatePanelImpl(expire, p);
		DateLabelFormatter expDateLabelFormatter = new DateLabelFormatter();
		JDatePickerImpl expireDate = new JDatePickerImpl(expPanel, expDateLabelFormatter);

		expireDate.setBounds(169, 193, 209, 20);
		contentPane.add(expireDate);

		txtStoredTemp = new JTextField();
		txtStoredTemp.setBounds(169, 227, 209, 20);
		contentPane.add(txtStoredTemp);
		txtStoredTemp.setColumns(10);
		txtStoredTemp.setText(String.valueOf(finalProductsUpdate.getStoreTemperature()));

		txtPrice = new JTextField();
		txtPrice.setBounds(169, 258, 209, 20);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		txtPrice.setText(String.valueOf(finalProductsUpdate.getPrice()));

		txtIngredients = new JTextField();
		txtIngredients.setText((String) null);
		txtIngredients.setColumns(10);
		txtIngredients.setBounds(169, 290, 209, 20);
		contentPane.add(txtIngredients);
		txtIngredients.setText(String.valueOf(finalProductsUpdate.getIngrediants()));

		JButton btnInsert = new JButton("Update Final Products");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (comboName.getSelectedItem() != null && !txtAmount.getText().equals("") && !txtPrice.equals("")
							&& !txtStoredTemp.equals("") && !txtIngredients.equals("")) {
						String name = (String) comboName.getSelectedItem();
						double price = Double.parseDouble(txtPrice.getText());
						double amount = Double.parseDouble(txtAmount.getText());
						double storedTemp = Double.parseDouble(txtStoredTemp.getText());
						String ingredients = txtIngredients.getText();

						Date manuDate = manu.getValue();
						Date expDate = expire.getValue();
						Date stoDate = store.getValue();

						if (price >= 0 && amount >= 0) {
							if (manuDate.before(expDate) && stoDate.after(manuDate)) {
								FinalProductsImpl finalProducts = new FinalProductsImpl();
								finalProducts.setId(finalProductsUpdate.getId());
								finalProducts.setName(name);
								finalProducts.setAmount(amount);
								finalProducts.setExpireDate(expDate);
								finalProducts.setPrice(price);
								finalProducts.setStoreTemperature(storedTemp);
								finalProducts.setManufactureDate(manuDate);
								finalProducts.setStoredDate(stoDate);
								finalProducts.setIngrediants(ingredients);

								warehouse.updateFinalProduct(finalProducts);

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
		btnInsert.setBounds(207, 321, 171, 23);
		contentPane.add(btnInsert);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboName.setSelectedItem("");
				txtAmount.setText("");
				txtPrice.setText("");
				txtStoredTemp.setText("");
				txtIngredients.setText("");
			}
		});
		btnClear.setBounds(47, 321, 150, 23);
		contentPane.add(btnClear);

		JLabel lblUpdate = new JLabel("UPDATE");
		lblUpdate.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
		lblUpdate.setBounds(138, 11, 131, 37);
		contentPane.add(lblUpdate);

	}

}
