package biscutquality;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Quality implements IQuality {

	@Override
	public void approveQuality() {
		 // open product approve window
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new QualityApproveWindow();
			}
		});
	}

	@Override
	public void setTransportOrStore() {
		// open set product transport or store  window
		
	}

}
