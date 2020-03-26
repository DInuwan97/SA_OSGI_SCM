package Warehouse;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WarehouseManager extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WarehouseManager frame = new WarehouseManager();
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
	public WarehouseManager() {
		setTitle("Warehouse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 744);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnManagerRawMaterials = new JButton("Manager Raw Materials");
		btnManagerRawMaterials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RawMaterials warehouse = new RawMaterials();
				setVisible(false);
				warehouse.main();
				
			}
		});
		btnManagerRawMaterials.setBounds(428, 184, 551, 102);
		contentPane.add(btnManagerRawMaterials);
		
		JButton btnManageFinalProducts = new JButton("Manage Final Products");
		btnManageFinalProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinalProducts finalProducts = new FinalProducts();
				setVisible(false);
				finalProducts.main();
			}
		});
		btnManageFinalProducts.setBounds(428, 411, 544, 102);
		contentPane.add(btnManageFinalProducts);
	}

}
