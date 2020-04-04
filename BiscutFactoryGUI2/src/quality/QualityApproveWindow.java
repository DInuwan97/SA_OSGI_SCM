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
import biscutquality.QualityDB;

public class QualityApproveWindow extends JFrame {
	JFrame frame;
	JLabel label;
	JTable table;
	DefaultTableModel model;

	IQualityDB db;
	ArrayList<DemandModal> demands;

	public QualityApproveWindow() {
		renderWindow();
	}

	void renderWindow() {
		frame = new JFrame("Quality Approve");
		frame.setLayout(new FlowLayout());
		frame.setSize(850, 600);
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(new Dimension(500, 600));
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// label
		label = new JLabel("Demand Requests");
		label.setFont(label.getFont().deriveFont(40.0f));
		label.setSize(40, 8);
		label.setBorder(BorderFactory.createEmptyBorder(10, 0, 18, 0));
		frame.add(label);
		
		renderTable();

		frame.setVisible(true);
	}

	void renderTable() {
		String[] columns = { "ID", "Product Details", "Demand Reason", "Sales Msg ID", "Request Date", "Status",
				"Action" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);

		db = (IQualityDB) new QualityDB();
		demands = db.getDemand();

		for (DemandModal dm : demands) {
			model.addRow(new Object[] { dm.getDemandReqId(), dm.getProductDetails(), dm.getDemandReason(),
					dm.getSalesMsgId(), dm.getReqDate(), dm.isApprovalState(), "Choose.." });
		}

		table = new JTable();
		table.setModel(model);
		table.setPreferredScrollableViewportSize(new Dimension(780, 400));
		table.setFillsViewportHeight(true);

		// combo box
		String[] positions = { "True", "False" };
		JComboBox combo = new JComboBox<String>(positions);
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg) {
				// JOptionPane.showConfirmDialog(null, arg.toString());
				int selectedIndex = combo.getSelectedIndex();
				int selectedRow = table.getSelectedRow();
				String value;

				if (selectedIndex == 0)
					value = "true";
				else
					value = "false";

				db = (IQualityDB) new QualityDB();
				int result = db.approveDemand(demands, selectedRow, value);

				if (result == 0) {
					JOptionPane.showMessageDialog(null, "Something went wrong.");
				} else {
					// JOptionPane.showMessageDialog(null, "Done.");
					table.setValueAt(value, selectedRow, 5);
					//frame.remove(table);
					//table = null;
					//renderTable();
				}
			}
		});
		TableColumn col = table.getColumnModel().getColumn(6);
		col.setCellEditor(new DefaultCellEditor(combo));

		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
	}

	
}
