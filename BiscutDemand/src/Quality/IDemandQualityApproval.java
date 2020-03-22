package Quality;

import java.sql.ResultSet;

public interface IDemandQualityApproval {

	public ResultSet viewDemandRequstbyId(int id);
	public ResultSet viewAllDemandRequstes();
	boolean updateDemandRequestApproval(int id, DemandQualityApprovalModel demandQualityApprovalModel);
	
}
