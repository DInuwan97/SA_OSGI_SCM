package SwingGUI;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Font;

public class Welcome {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		frame.setBounds(100, 100, 1217, 738);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JProgressBar jProgressBar = new JProgressBar();
		jProgressBar.setBounds(312, 379, 618, 23);
		frame.getContentPane().add(jProgressBar);
		
		JLabel lbl = new JLabel("New label");
		lbl.setFont(new Font("Tahoma", Font.BOLD, 45));
		lbl.setBounds(365, 437, 514, 47);
		frame.getContentPane().add(lbl);
		
		
		//Progress Bar Laoding
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 for(int i=0;i<100;i++){
	                    jProgressBar.setValue(i);
	                    if(i<10){
	                        try {
	                            Thread.sleep(100);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Hela Market Loading.");
	                    }else if(i<20){
	                        try {
	                            Thread.sleep(90);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Hela Market Loading..");
	                    }else if(i<30){
	                        try {
	                            Thread.sleep(80);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Configure Settings...");
	                    }else if(i<40){
	                        try {
	                            Thread.sleep(70);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Configure Settings....");
	                    }else if(i<50){
	                        try {
	                            Thread.sleep(60);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Configure Settings....");
	                    }else if(i<60){
	                        try {
	                            Thread.sleep(50);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Configure Database");
	                    }else if(i<70){
	                        try {
	                            Thread.sleep(40);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Configure Database.");
	                    }else if(i<80){
	                        try {
	                            Thread.sleep(30);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Configure Database..");
	                    }else if(i<90){
	                        try {
	                            Thread.sleep(20);
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Configure Database...");
	                    }else if(i<=100){
	                        try {
	                            Thread.sleep(10);
	                            
	                            //display();
	                            break;
	                            
	                        } catch (InterruptedException ex) {
	                            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        lbl.setText("Configure Database...."); 
	                    }
	                }

				
			}
		});
	}
}
