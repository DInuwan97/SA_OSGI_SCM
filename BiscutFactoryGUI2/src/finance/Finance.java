package finance;

import javax.swing.SwingUtilities;


public class Finance implements IFinance {

	@Override
	public void setPrice() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SetPriceWindow();
			}
		});
		
	}

}
