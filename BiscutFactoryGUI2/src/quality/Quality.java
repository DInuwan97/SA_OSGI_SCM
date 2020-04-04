package quality;

import javax.swing.SwingUtilities;

import quality.IQuality;
import quality.QualityApproveWindow;

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
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SetTransportOrStoreWindow();
			}
		});
	}

}
