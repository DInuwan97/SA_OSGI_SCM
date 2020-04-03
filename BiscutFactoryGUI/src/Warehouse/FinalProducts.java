package Warehouse;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import warehouse.AbstractWarehouse;
import warehouse.FinalProductsImpl;
import warehouse.RawMaterials;
import warehouse.RawMaterialsImpl;
import warehouse.Warehouse;
import warehouse.WarehouseFactoryProducer;

public class FinalProducts extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public  static Warehouse warh;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalProducts frame = new FinalProducts();
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
	public FinalProducts() {
		setTitle("Final Products");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1035, 683);
		contentPane.add(scrollPane);

		AbstractWarehouse warehouse = warh.getFactory("finalproducts");

		String[] headders = { "Batch ID", "Name", "Amount", "Stored Date", "Manufacture Date", "Expire Date",
				"Stored Temperature", "Price", "Ingredients" };
		DefaultTableModel tdm = new DefaultTableModel(headders, 0);
		table = new JTable(tdm);
		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ArrayList<warehouse.FinalProducts> list = warehouse.getFinalProductDetails(true);
		if (list != null) {
			for (warehouse.FinalProducts finalProducts : list) {
				Object[] str = { finalProducts.getId(), finalProducts.getName(), finalProducts.getAmount(),
						finalProducts.getStoredDate(), finalProducts.getManufactureDate(),
						finalProducts.getExpireDate(), finalProducts.getStoreTemperature(), finalProducts.getPrice(),
						finalProducts.getIngrediants() };
				tdm.addRow(str);
			}
		}

		scrollPane.setViewportView(table);

		JCheckBox chkExpiredBox = new JCheckBox("Show Expired Products");
		chkExpiredBox.setBounds(1056, 11, 285, 51);
		contentPane.add(chkExpiredBox);
		chkExpiredBox.setSelected(true);

		JButton btnUpdateFinalProducts = new JButton("Update Selected Batch");
		btnUpdateFinalProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					FinalProductsImpl finalProductsImpl = new FinalProductsImpl();
					int row = table.getSelectedRow();
					finalProductsImpl.setId(((Integer) table.getValueAt(row, 0)));
					finalProductsImpl.setName(((String) table.getValueAt(row, 1)));
					finalProductsImpl.setAmount(((Double) table.getValueAt(row, 2)));
					finalProductsImpl.setStoredDate(((Date) table.getValueAt(row, 3)));
					finalProductsImpl.setManufactureDate(((Date) table.getValueAt(row, 4)));
					finalProductsImpl.setExpireDate(((Date) table.getValueAt(row, 5)));
					finalProductsImpl.setStoreTemperature(((Double) table.getValueAt(row, 6)));
					finalProductsImpl.setPrice(((Double) table.getValueAt(row, 7)));
					finalProductsImpl.setIngrediants((String) table.getValueAt(row, 8));

					UpdateFinalProduct updateFinalProduct = new UpdateFinalProduct(finalProductsImpl);
					updateFinalProduct.main(finalProductsImpl);
				}
			}
		});
		btnUpdateFinalProducts.setBounds(1055, 359, 286, 47);
		contentPane.add(btnUpdateFinalProducts);

		JButton btnDeleteFinalProduct = new JButton("Delete Selected Batch");
		btnDeleteFinalProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int column = 0;
					int row = table.getSelectedRow();
					int id = (int) table.getModel().getValueAt(row, column);
					int i = JOptionPane.showConfirmDialog(contentPane, "Do you want to Delete the batch " + id + "?",
							"Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (i == 0) {
						warehouse.deleteFinalProducts(id);
						tdm.removeRow(row);
					}
				}
			}
		});
		btnDeleteFinalProduct.setBounds(1055, 417, 286, 47);
		contentPane.add(btnDeleteFinalProduct);

		JButton btnExpiredProducts = new JButton("Get Expired Products");
		btnExpiredProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tdm.setRowCount(0);
				ArrayList<warehouse.FinalProducts> list = warehouse.checkExpiredFinalProducts();
				if (list != null) {
					for (warehouse.FinalProducts finalProducts : list) {
						Object[] str = { finalProducts.getId(), finalProducts.getName(), finalProducts.getAmount(),
								finalProducts.getStoredDate(), finalProducts.getManufactureDate(),
								finalProducts.getExpireDate(), finalProducts.getStoreTemperature(),
								finalProducts.getPrice(), finalProducts.getIngrediants() };
						tdm.addRow(str);
					}
				}
			}
		});
		btnExpiredProducts.setBounds(1055, 244, 286, 47);
		contentPane.add(btnExpiredProducts);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		comboBox.setBounds(1055, 73, 286, 47);

		ArrayList<String> finalProductList = warehouse.getFinalProductNames();
		if (finalProductList != null) {
			for (String string : finalProductList) {
				comboBox.addItem(string);
			}
		}

		contentPane.add(comboBox);

		JButton btnSearchFinalProduct = new JButton("Search Final Product");
		btnSearchFinalProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null) {
					tdm.setRowCount(0);
					String name = (String) comboBox.getSelectedItem();
					ArrayList<warehouse.FinalProducts> list = warehouse
							.checkFinalProducts((String) comboBox.getSelectedItem(), chkExpiredBox.isSelected());
					if (list != null) {
						for (warehouse.FinalProducts finalProducts : list) {
							Object[] str = { finalProducts.getId(), finalProducts.getName(), finalProducts.getAmount(),
									finalProducts.getStoredDate(), finalProducts.getManufactureDate(),
									finalProducts.getExpireDate(), finalProducts.getStoreTemperature(),
									finalProducts.getPrice(), finalProducts.getIngrediants() };
							tdm.addRow(str);
						}
					}
				}
			}
		});
		btnSearchFinalProduct.setBounds(1055, 128, 286, 47);
		contentPane.add(btnSearchFinalProduct);

		JButton btnAllFinalProducts = new JButton("Get All Final Products");
		btnAllFinalProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tdm.setRowCount(0);
				ArrayList<warehouse.FinalProducts> list = warehouse.getFinalProductDetails(chkExpiredBox.isSelected());
				if (list != null) {
					for (warehouse.FinalProducts finalProducts : list) {
						Object[] str = { finalProducts.getId(), finalProducts.getName(), finalProducts.getAmount(),
								finalProducts.getStoredDate(), finalProducts.getManufactureDate(),
								finalProducts.getExpireDate(), finalProducts.getStoreTemperature(),
								finalProducts.getPrice(), finalProducts.getIngrediants() };
						tdm.addRow(str);
					}
				}
			}

		});
		btnAllFinalProducts.setBounds(1055, 186, 286, 47);
		contentPane.add(btnAllFinalProducts);

	}

}
