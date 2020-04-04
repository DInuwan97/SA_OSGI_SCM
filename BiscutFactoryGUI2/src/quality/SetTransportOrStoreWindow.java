package quality;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import biscutquality.DemandModal;
import biscutquality.IQualityDB;
import biscutquality.ManufactureModal;
import biscutquality.QualityDB;

public class SetTransportOrStoreWindow {
	JFrame frame;
	JLabel label;
	JTable table;
	DefaultTableModel model;
	
	ArrayList<ManufactureModal> products;
	IQualityDB db;

	public SetTransportOrStoreWindow() {
		renderWindow();
	}

	void renderWindow() {
		frame = new JFrame("Set to Transport or Store");
		frame.setLayout(new FlowLayout());
		frame.setSize(850, 600);
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(new Dimension(500, 600));
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// label
		label = new JLabel("Manufactured Products");
		label.setFont(label.getFont().deriveFont(40.0f));
		label.setSize(40, 8);
		label.setBorder(BorderFactory.createEmptyBorder(10, 0, 18, 0));
		frame.add(label);

		renderTable();

		frame.setVisible(true);
	}

	void renderTable() {
		String[] columns = { "Manufacture ID", "Manufacture Date", "Biscut Name", "Demand Req ID", "Action" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);

		db = (IQualityDB) new QualityDB();
		products = db.getManufactures();

		for (ManufactureModal mn : products) {
			model.addRow(new Object[] { mn.getManufactureId(), mn.getManufactureDate(), mn.getBiscutName(), mn.getDemandReqId(), "Choose.." });
		}

		table = new JTable();
		table.setModel(model);
		table.setPreferredScrollableViewportSize(new Dimension(780, 400));
		table.setFillsViewportHeight(true);

			// combo box
		String[] positions = { "Store", "Transport" };
		JComboBox combo = new JComboBox<String>(positions);
		
		combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg) {
				// JOptionPane.showConfirmDialog(null, arg.toString());
				int selectedIndex = combo.getSelectedIndex();
				int selectedRow = table.getSelectedRow();
				String value;
				int id = 0, i = 0;

				if (selectedIndex == 0)
					value = "store";
				else
					value = "transport";

				for (ManufactureModal mn : products) {
					if(i > selectedRow)
						break;
					id = mn.getManufactureId();
					i++;
				}
				
				if(id != 0) {
					db = (IQualityDB) new QualityDB();
					int result = db.setAction(id, value);

					if (result == 0) {
						JOptionPane.showMessageDialog(null, "Something went wrong.");
					} else {
						JOptionPane.showMessageDialog(null, "Done.");
						// table.setValueAt(value, selectedRow, 5);
						// frame.remove(table);
						// table = null;
						// renderTable();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Something went wrong.");
				}
				
			}
		});
		TableColumn col = table.getColumnModel().getColumn(4);
		col.setCellEditor(new DefaultCellEditor(combo));

		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
	}
}
