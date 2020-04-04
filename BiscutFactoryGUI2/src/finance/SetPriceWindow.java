package finance;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import biscutfinance.FinanceDB;
import biscutfinance.IFinanceDB;
import biscutquality.IQualityDB;
import biscutquality.ManufactureModal;
import biscutquality.QualityDB;

public class SetPriceWindow {
	JFrame frame, inputWindow;
	JLabel label;
	JTable table;
	DefaultTableModel model;
	JTextField textField;
	
	ArrayList<ManufactureModal> products;
	IFinanceDB db;
	
	public SetPriceWindow() {
		renderWindow();
	}
	
	public SetPriceWindow(int row) {
		
	}
	
	
	void renderWindow() {
		frame = new JFrame("Set Prices");
		frame.setLayout(new FlowLayout());
		frame.setSize(900, 600);
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
		String[] columns = { "Manufacture ID", "Manufacture Date", "Expire Date", "Biscut Name", "Materials", "No of machines",
				"No of employees", "Manufacture amount", "Demand Req ID", "Action" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);

		db = (IFinanceDB) new FinanceDB();
		products = db.getManufactures();

		for (ManufactureModal mn : products) {
			model.addRow(new Object[] { mn.getManufactureId(), mn.getManufactureDate(), mn.getExpireDate(), mn.getBiscutName(), 
					mn.getMaterials(), mn.getNoOfMachines(), mn.getNoOfEmployees(), mn.getManufactureAmount(), 
					mn.getDemandReqId(), "Price"});
		}

		table = new JTable();
		table.setModel(model);
		table.setPreferredScrollableViewportSize(new Dimension(800, 400));
		table.setFillsViewportHeight(true);

		// button
		table.getColumnModel().getColumn(9).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor(new JTextField()));

		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
	}
	
	
	void renderInputWindow(int row) {
		System.out.println("input window");
		int manuID1 = 0, i = 0;
		final int manuID2;
		
		db = (IFinanceDB) new FinanceDB();
		products = db.getManufactures();
		
		for (ManufactureModal manufactureModal : products) {	
			if(i == row) {
				manuID1 = manufactureModal.getManufactureId();
			}
			i++;
		}
		
		System.out.println(manuID1);
		manuID2 = manuID1;
		
		inputWindow = new JFrame("Price");
		inputWindow.setLayout(new FlowLayout());
		inputWindow.setSize(350, 150);
		inputWindow.setLocationRelativeTo(null);
		inputWindow.setMinimumSize(new Dimension(350, 150));
		inputWindow.setMaximumSize(new Dimension(350, 150));
		//inputWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// label
		label = new JLabel("Enter Price");
		label.setFont(label.getFont().deriveFont(15.0f));
		label.setSize(20, 4);
		label.setBorder(BorderFactory.createEmptyBorder(5, 0, 9, 0));
		inputWindow.add(label);
		
		//text field
		textField = new JTextField(10);
		inputWindow.add(textField);
		
		//button
		JButton btn = new JButton("SET");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				System.out.println(text);
								
				try {
					int input = Integer.parseInt(text);
					
					db = (IFinanceDB) new FinanceDB();
					int result = db.setPrice(manuID2, input);
					
					if(result == 0) {
						JOptionPane.showMessageDialog(null, "Something went wrong!");
					}else {
						// close input window
						inputWindow.dispose();
					}
					
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Enter a numeric value");
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		inputWindow.add(btn);
		
		inputWindow.setVisible(true);
	}
	
	
}


class ButtonRenderer extends JButton implements TableCellRenderer{

	public ButtonRenderer() {
		setBackground(Color.LIGHT_GRAY);
		setOpaque(true);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		setText((value == null) ? "" : value.toString());
		
		return this;
	}
	
}

class ButtonEditor extends DefaultCellEditor{
	protected JButton btn;
	private String label;
	private boolean clicked;
	int clickedRow;
	SetPriceWindow priceWindow;
	
	public ButtonEditor(JTextField txt) {
		super(txt);
		btn = new JButton();
		btn.setOpaque(true);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(clickedRow);
				
				// set input window
				priceWindow = new SetPriceWindow(clickedRow);
				priceWindow.renderInputWindow(clickedRow);
				
				fireEditingStopped();
			}
		});
		
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		label = (value == null) ? "" : value.toString();
		btn.setText(label);
		clicked = true;
		clickedRow = row;
		return btn;
	}
	
	
	@Override
	public Object getCellEditorValue() {
		if(clicked) {
			
		}
		clicked = false;
		return new String(label);
	}
	
	private boolean stpoCellEditing() {
		clicked = false;
		return super.stopCellEditing();
		
	}
	
	@Override
	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
	
	
}



