package Logistics;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Warehouse.DateLabelFormatter;
import logistics.Logistics;
import logistics.LogisticsDTO;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class LogisticsGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public static Logistics logistics;

	/**
	 * Launch the application.
	 */
	public static void executeLogisticGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogisticsGui frame = new LogisticsGui();
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
	public LogisticsGui() {
		setTitle("Logistics");
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

		String[] headders = { "Transaction ID", "Name", "Amount", "Batch Nos", "Date" };
		DefaultTableModel tdm = new DefaultTableModel(headders, 0);
		table = new JTable(tdm);
		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ArrayList<LogisticsDTO> list = logistics.reportRawMaterials();
		if (list != null) {
			for (LogisticsDTO logisticsDTO : list) {
				Object[] str = { logisticsDTO.getId(), logisticsDTO.getName(), logisticsDTO.getAmount(),
						logisticsDTO.getBatchNos(), logisticsDTO.getDate() };
				tdm.addRow(str);
			}
		}

		scrollPane.setViewportView(table);

		ButtonGroup buttonGroup = new ButtonGroup();

		JRadioButton rdbtnRawMaterials = new JRadioButton("Raw Materials");
		rdbtnRawMaterials.setBounds(1098, 14, 109, 23);
		contentPane.add(rdbtnRawMaterials);

		JRadioButton rdbtnFinalProducts = new JRadioButton("Final Products");
		rdbtnFinalProducts.setBounds(1209, 14, 109, 23);
		contentPane.add(rdbtnFinalProducts);

		buttonGroup.add(rdbtnRawMaterials);
		buttonGroup.add(rdbtnFinalProducts);
		rdbtnRawMaterials.setSelected(true);

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		UtilDateModel startDate = new UtilDateModel();
		JDatePanelImpl startPanel = new JDatePanelImpl(startDate, p);
		DateLabelFormatter startLabelFormatter = new DateLabelFormatter();
		JDatePickerImpl startPicker = new JDatePickerImpl(startPanel, startLabelFormatter);
		startPicker.setBounds(1055, 200, 138, 23);
		contentPane.add(startPicker);

		UtilDateModel stopDate = new UtilDateModel();
		JDatePanelImpl stopPanel = new JDatePanelImpl(stopDate, p);
		DateLabelFormatter stopLabelFormatter = new DateLabelFormatter();
		JDatePickerImpl stopPicker = new JDatePickerImpl(stopPanel, stopLabelFormatter);
		stopPicker.setBounds(1196, 200, 138, 23);
		contentPane.add(stopPicker);

		JButton btnGetTimeDetails = new JButton("Get Time Period Details");
		btnGetTimeDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnRawMaterials.isSelected()) {
					try {
						Date start = startDate.getValue();
						Date end = stopDate.getValue();
						ArrayList<LogisticsDTO> list = logistics.reportRawMaterialsForAPeriod(start, end);
						if (start.before(end)) {
							if (list != null) {
								tdm.setRowCount(0);
								for (LogisticsDTO logisticsDTO : list) {
									Object[] str = { logisticsDTO.getId(), logisticsDTO.getName(),
											logisticsDTO.getAmount(), logisticsDTO.getBatchNos(),
											logisticsDTO.getDate() };
									tdm.addRow(str);
								}
							}
						}

					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}

				} else if (rdbtnFinalProducts.isSelected()) {
					try {
						Date start = startDate.getValue();
						Date end = stopDate.getValue();
						ArrayList<LogisticsDTO> list = logistics.reportFinalProductsForAPeriod(start, end);
						if (start.before(end)) {
							if (list != null) {
								tdm.setRowCount(0);
								for (LogisticsDTO logisticsDTO : list) {
									Object[] str = { logisticsDTO.getId(), logisticsDTO.getName(),
											logisticsDTO.getAmount(), logisticsDTO.getBatchNos(),
											logisticsDTO.getDate() };
									tdm.addRow(str);
								}
							}
						}
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}

			}
		});
		btnGetTimeDetails.setBounds(1055, 244, 286, 47);
		contentPane.add(btnGetTimeDetails);

		JComboBox<String> comboMonth = new JComboBox<String>();
		comboMonth.setBounds(1196, 102, 144, 23);
		contentPane.add(comboMonth);

		JComboBox<Integer> comboYear = new JComboBox<Integer>();
		comboYear.setBounds(1055, 102, 138, 23);
		contentPane.add(comboYear);

		Date toDay = new Date();
		for (int i = 2000; i <= toDay.getYear()+1900; i++) {
			comboYear.addItem(i);
		}
		comboYear.setSelectedItem(toDay.getYear()+1900);

		comboMonth.addItem("January");
		comboMonth.addItem("February");
		comboMonth.addItem("March");
		comboMonth.addItem("April");
		comboMonth.addItem("May");
		comboMonth.addItem("June");
		comboMonth.addItem("July");
		comboMonth.addItem("August");
		comboMonth.addItem("September");
		comboMonth.addItem("October");
		comboMonth.addItem("November");
		comboMonth.addItem("December");

		comboMonth.setSelectedIndex(toDay.getMonth());

		JButton btnGetMonthDetails = new JButton("Get This Month Details");
		btnGetMonthDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnRawMaterials.isSelected()) {
					if (!comboYear.getSelectedItem().equals("") && !comboMonth.getSelectedObjects().equals("")) {
						ArrayList<LogisticsDTO> list = logistics.monthReportRawMaterials(
								(int) (comboYear.getSelectedItem()), comboMonth.getSelectedIndex() + 1);
						if (list != null) {
							tdm.setRowCount(0);
							for (LogisticsDTO logisticsDTO : list) {
								Object[] str = { logisticsDTO.getId(), logisticsDTO.getName(), logisticsDTO.getAmount(),
										logisticsDTO.getBatchNos(), logisticsDTO.getDate() };
								System.out.println(str);
								tdm.addRow(str);
							}
						}
					}
				} else if (rdbtnFinalProducts.isSelected()) {
					if (!comboYear.getSelectedItem().equals("") && !comboMonth.getSelectedObjects().equals("")) {
						ArrayList<LogisticsDTO> list = logistics.monthReportFinalProducts(
								(int) (comboYear.getSelectedItem()), comboMonth.getSelectedIndex() + 1);
						if (list != null) {
							tdm.setRowCount(0);
							for (LogisticsDTO logisticsDTO : list) {
								Object[] str = { logisticsDTO.getId(), logisticsDTO.getName(), logisticsDTO.getAmount(),
										logisticsDTO.getBatchNos(), logisticsDTO.getDate() };
								System.out.println(str);
								tdm.addRow(str);
							}
						}
					}
				}
			}
		});
		btnGetMonthDetails.setBounds(1055, 142, 286, 47);
		contentPane.add(btnGetMonthDetails);

		JButton btnGetAllDetails = new JButton("Get All Details");
		btnGetAllDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnRawMaterials.isSelected()) {
					ArrayList<LogisticsDTO> list = logistics.reportRawMaterials();
					if (list != null) {
						tdm.setRowCount(0);
						for (LogisticsDTO logisticsDTO : list) {
							Object[] str = { logisticsDTO.getId(), logisticsDTO.getName(), logisticsDTO.getAmount(),
									logisticsDTO.getBatchNos(), logisticsDTO.getDate() };
							tdm.addRow(str);
						}
					}
				} else if (rdbtnFinalProducts.isSelected()) {
					ArrayList<LogisticsDTO> list = logistics.reportFinalProducts();
					if (list != null) {
						tdm.setRowCount(0);
						for (LogisticsDTO logisticsDTO : list) {
							Object[] str = { logisticsDTO.getId(), logisticsDTO.getName(), logisticsDTO.getAmount(),
									logisticsDTO.getBatchNos(), logisticsDTO.getDate() };
							tdm.addRow(str);
						}
					}
				}

			}
		});
		btnGetAllDetails.setBounds(1055, 44, 286, 47);
		contentPane.add(btnGetAllDetails);

	}
}
