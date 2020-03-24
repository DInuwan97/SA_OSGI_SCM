package SwingGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import stakeholdermanagement.IStakeHolder;
import stakeholdermanagement.StakeHolderDBQueries;
import stakeholdermanagement.StakeHolderModel;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserLoginGUI {

	private JFrame frame;
	private JTextField txtMobile;
	private JTextField txtPwd;
	
	private IStakeHolder iStakeHolder;
	private StakeHolderModel stakeHolderModel;

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
		txtMobile.setBounds(490, 226, 239, 34);
		frame.getContentPane().add(txtMobile);
		
		txtPwd = new JTextField();
		txtPwd.setColumns(10);
		txtPwd.setBounds(490, 282, 239, 34);
		frame.getContentPane().add(txtPwd);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				iStakeHolder = new StakeHolderDBQueries();
				stakeHolderModel = new StakeHolderModel();
				
				
				stakeHolderModel.setMobile(txtMobile.getText());
				stakeHolderModel.setPwd(txtPwd.getText());
				
				boolean result = iStakeHolder.loginUser(stakeHolderModel);
				
				if(result == true) {				
					JOptionPane.showMessageDialog(null,"Success!!!");	
				}else {
					JOptionPane.showMessageDialog(null,"Fail!!!");	
				}
				
				
				
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnLogin.setBounds(535, 353, 150, 40);
		frame.getContentPane().add(btnLogin);
	}

}
