package SwingGUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Welcome {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void executeWelcomeWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
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
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(0, 0, 1366, 768);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl = new JLabel("New label");
		lbl.setForeground(Color.LIGHT_GRAY);
		lbl.setFont(new Font("Tahoma", Font.BOLD, 45));
		lbl.setBounds(339, 623, 1075, 77);
		frame.getContentPane().add(lbl);
		
		JLabel lblWelcomeToCyelon = new JLabel("Welcome to Cyelon Biscut Productions");
		lblWelcomeToCyelon.setForeground(Color.LIGHT_GRAY);
		lblWelcomeToCyelon.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblWelcomeToCyelon.setBounds(339, 80, 888, 77);
		frame.getContentPane().add(lblWelcomeToCyelon);
		
		
		JLabel lblImg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/4.gif")).getImage();
		lblImg.setIcon(new ImageIcon(img));
		lblImg.setBounds(454, 145, 805, 468);
		frame.getContentPane().add(lblImg);
		
		
		//Progress Bar Laoding
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 for(int i=0;i<100;i++){
	                   // jProgressBar.setValue(i);
	                    if(i<10){
	                        try {
	                            Thread.sleep(100);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("OGSI Micro-Kernel Project Loading.");
	                    }else if(i<20){
	                        try {
	                            Thread.sleep(90);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("OGSI Micro-Kernel Project Loading..");
	                    }else if(i<30){
	                        try {
	                            Thread.sleep(80);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Configure OSGI Micro-Kernel Settings.");
	                    }else if(i<40){
	                        try {
	                            Thread.sleep(70);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Configure OSGI Micro-Kernel Settings..");
	                    }else if(i<50){
	                        try {
	                            Thread.sleep(60);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Configure Micro-Kernel OGSI Bunldes....");
	                    }else if(i<60){
	                        try {
	                            Thread.sleep(50);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Connecting Micro-Kernel to Database");
	                    }else if(i<70){
	                        try {
	                            Thread.sleep(40);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Connecting Micro-Kernel to Database.");
	                    }else if(i<80){
	                        try {
	                            Thread.sleep(30);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Connecting Micro-Kernel to Database..");
	                    }else if(i<90){
	                        try {
	                            Thread.sleep(20);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Connecting Micro-Kernel to Database...");
	                    }else if(i<=100){
	                        try {
	                            Thread.sleep(10);
	                            
	                            display();
	                            break;
	                            
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Configure Database...."); 
	                       // display();
	                    }
	                }

				
			}
		});
		t.start();
	}
	
	public void display() {
		MenuWindow.executeMenuWindow();
		
		frame.setVisible(false);
	}
	
	
	
}


