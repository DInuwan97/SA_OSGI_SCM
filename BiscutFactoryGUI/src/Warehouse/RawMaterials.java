package Warehouse;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import warehouse.AbstractWarehouse;
import warehouse.RawMaterialsImpl;
import warehouse.Warehouse;
import warehouse.WarehouseFactoryProducer;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class RawMaterials extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	public static Warehouse warh;
	


	/**
	 * Launch the application.
	 */

	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RawMaterials frame = new RawMaterials();

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
	public RawMaterials() {
		setTitle("Raw Materials");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 744);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1035, 683);
		contentPane.add(scrollPane);

		AbstractWarehouse warehouse = warh.getFactory("rawmaterials");

		String[] headders = { "Batch ID", "Name", "Amount", "Stored Date", "Manufacture Date", "Expire Date",
				"Stored Temperature", "Price" };
		DefaultTableModel tdm = new DefaultTableModel(headders, 0);
		table = new JTable(tdm);
		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ArrayList<warehouse.RawMaterials> list = warehouse.getRawMatetialDetails(true);
		if (list != null) {
			for (warehouse.RawMaterials rawMaterials : list) {
				Object[] str = { rawMaterials.getId(), rawMaterials.getName(), rawMaterials.getAmount(),
						rawMaterials.getStoredDate(), rawMaterials.getManufactureDate(), rawMaterials.getExpireDate(),
						rawMaterials.getStoreTemperature(), rawMaterials.getPrice() };
				tdm.addRow(str);
			}
		}

		scrollPane.setViewportView(table);

		JCheckBox chkExpiredBox = new JCheckBox("Show Expired Products");
		chkExpiredBox.setBounds(1056, 11, 285, 51);
		contentPane.add(chkExpiredBox);
		chkExpiredBox.setSelected(true);

		JButton btnAddRawMaterial = new JButton("Add New Raw Material Batch");
		btnAddRawMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRawMaterial addRawMaterial = new AddRawMaterial();
				addRawMaterial.main();

			}
		});
		btnAddRawMaterial.setBounds(1055, 360, 286, 47);
		contentPane.add(btnAddRawMaterial);

		JButton btnUpdateRawMaterial = new JButton("Update Selected Batch");
		btnUpdateRawMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					RawMaterialsImpl rawMaterialsImpl = new RawMaterialsImpl();
					int row = table.getSelectedRow();
					rawMaterialsImpl.setId(((Integer) table.getValueAt(row, 0)));
					rawMaterialsImpl.setName(((String) table.getValueAt(row, 1)));
					rawMaterialsImpl.setAmount(((Double) table.getValueAt(row, 2)));
					rawMaterialsImpl.setStoredDate(((Date) table.getValueAt(row, 3)));
					rawMaterialsImpl.setManufactureDate(((Date) table.getValueAt(row, 4)));
					rawMaterialsImpl.setExpireDate(((Date) table.getValueAt(row, 5)));
					rawMaterialsImpl.setStoreTemperature(((Double) table.getValueAt(row, 6)));
					rawMaterialsImpl.setPrice(((Double) table.getValueAt(row, 7)));
					
					UpdateRawMaterial updateRawMaterial = new UpdateRawMaterial(rawMaterialsImpl);
					updateRawMaterial.main(rawMaterialsImpl);
				}
			}
		});
		btnUpdateRawMaterial.setBounds(1055, 418, 286, 47);
		contentPane.add(btnUpdateRawMaterial);

		JButton btnDeleteRawMaterial = new JButton("Delete Selected Batch");
		btnDeleteRawMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int column = 0;
					int row = table.getSelectedRow();
					int id = (int) table.getModel().getValueAt(row, column);
					int i = JOptionPane.showConfirmDialog(contentPane, "Do you want to Delete the batch " + id + "?",
							"Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (i == 0) {
						warehouse.deleteRawMaterials(id);
						tdm.removeRow(row);
					}
				}
			}
		});
		btnDeleteRawMaterial.setBounds(1055, 476, 286, 47);
		contentPane.add(btnDeleteRawMaterial);

		JButton btnExpiredProducts = new JButton("Get Expired Products");
		btnExpiredProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tdm.setRowCount(0);
				ArrayList<warehouse.RawMaterials> list = warehouse.checkExpiredRawMaterials();
				if (list != null) {
					for (warehouse.RawMaterials rawMaterials : list) {
						Object[] str = { rawMaterials.getId(), rawMaterials.getName(), rawMaterials.getAmount(),
								rawMaterials.getStoredDate(), rawMaterials.getManufactureDate(),
								rawMaterials.getExpireDate(), rawMaterials.getStoreTemperature(),
								rawMaterials.getPrice() };
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

		ArrayList<String> rawMaterialList = warehouse.getRawMaterilNames();
		if (rawMaterialList != null) {
			for (String string : rawMaterialList) {
				comboBox.addItem(string);
			}
		}

		contentPane.add(comboBox);

		JButton btnSearchRawMaterial = new JButton("Search Raw Material");
		btnSearchRawMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null) {
					tdm.setRowCount(0);
					String name = (String) comboBox.getSelectedItem();
					ArrayList<warehouse.RawMaterials> list = warehouse.checkRawMaterials(name, chkExpiredBox.isSelected());
					if (list != null) {
						for (warehouse.RawMaterials rawMaterials : list) {
							Object[] str = { rawMaterials.getId(), rawMaterials.getName(), rawMaterials.getAmount(),
									rawMaterials.getStoredDate(), rawMaterials.getManufactureDate(),
									rawMaterials.getExpireDate(), rawMaterials.getStoreTemperature(),
									rawMaterials.getPrice() };
							tdm.addRow(str);
						}
					}
				}
			}
		});
		btnSearchRawMaterial.setBounds(1055, 128, 286, 47);
		contentPane.add(btnSearchRawMaterial);

		JButton btnAllRawMaterials = new JButton("Get All Raw Materials");
		btnAllRawMaterials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tdm.setRowCount(0);
				ArrayList<warehouse.RawMaterials> list = warehouse.getRawMatetialDetails(chkExpiredBox.isSelected());
				if (list != null) {
					for (warehouse.RawMaterials rawMaterials : list) {
						Object[] str = { rawMaterials.getId(), rawMaterials.getName(), rawMaterials.getAmount(),
								rawMaterials.getStoredDate(), rawMaterials.getManufactureDate(),
								rawMaterials.getExpireDate(), rawMaterials.getStoreTemperature(),
								rawMaterials.getPrice() };
						tdm.addRow(str);
					}
				}
			}

		});
		btnAllRawMaterials.setBounds(1055, 186, 286, 47);
		contentPane.add(btnAllRawMaterials);

	}
}
