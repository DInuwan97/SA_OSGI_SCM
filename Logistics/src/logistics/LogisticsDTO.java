package logistics;

import java.util.Date;

public interface LogisticsDTO {

	public int getId();

	public String getName();

	public double getAmount();

	public String getBatchNos();

	public Date getDate();

	public String display();

}
