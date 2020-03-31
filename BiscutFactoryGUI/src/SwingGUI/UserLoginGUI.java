package SwingGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biscutfactorygui.Activator;

import stakeholdermanagement.IStakeHolder;
import stakeholdermanagement.StakeHolderDBQueries;
import stakeholdermanagement.StakeHolderModel;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class UserLoginGUI {

	private JFrame frame;
	private JTextField txtMobile;
	private JTextField txtPwd;
	private IStakeHolder iStakeHolder;
	private StakeHolderModel stakeHolderModel;
	private JTextField txtSecureKey;
	private JButton btnSecureKey;
	

	/**
	 * Launch the application.
	 */
	public static void executeLogin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLoginGUI window = new UserLoginGUI();
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
	public UserLoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1366, 768);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtMobile = new JTextField();
		txtMobile.setColumns(10);
		txtMobile.setBounds(602, 245, 376, 34);
		frame.getContentPane().add(txtMobile);
		
		txtPwd = new JTextField();
		txtPwd.setColumns(10);
		txtPwd.setBounds(602, 301, 376, 34);
		frame.getContentPane().add(txtPwd);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		
				iStakeHolder = new StakeHolderDBQueries();
				stakeHolderModel = new StakeHolderModel();
				
				
				String mobile = txtMobile.getText();
				String password = txtPwd.getText();
				
				stakeHolderModel.setMobile(mobile);
				stakeHolderModel.setPwd(password);
				
				boolean result = iStakeHolder.loginUser(stakeHolderModel);
				String secureKey = iStakeHolder.getVerificationKey(stakeHolderModel);
				
				if(result == true) {				
					//JOptionPane.showMessageDialog(null,"Success!!!");	
					
						
					try {
						iStakeHolder.sendSMS(stakeHolderModel);
						 
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					txtMobile.setText("");
					txtPwd.setText("");
					
					txtSecureKey.setEditable(true);
					btnSecureKey.setEnabled(true);
					
					txtMobile.setEditable(false);
					txtPwd.setEditable(false);
					btnLogin.setEnabled(false);
					
					
					btnSecureKey.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							if((txtSecureKey.getText().toString()).equals(secureKey)) {
								MenuWindow.executeMenuWindow();
								frame.setVisible(false);
							}else {
								JOptionPane.showMessageDialog(null,"Invalid Security Key");
							}
							
						}
					});

					
				}else {
					JOptionPane.showMessageDialog(null,"Invalid Credentials!!!");	
				}
				
				
				
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnLogin.setBounds(716, 373, 150, 40);
		frame.getContentPane().add(btnLogin);
		
		txtSecureKey = new JTextField();
		txtSecureKey.setEditable(false);
		txtSecureKey.setColumns(10);
		txtSecureKey.setBounds(602, 490, 246, 34);
		frame.getContentPane().add(txtSecureKey);
		
		btnSecureKey = new JButton("VERIFY");
	
		btnSecureKey.setEnabled(false);
		btnSecureKey.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSecureKey.setBounds(872, 490, 106, 34);
		frame.getContentPane().add(btnSecureKey);
	}
}
