package stakeholdermanagement;

import java.io.IOException;

public interface IStakeHolder {

	public boolean loginUser(StakeHolderModel stakeHolderModel);
	public String getVerificationKey(StakeHolderModel stakeHolderModel);
	public void sendSMS(StakeHolderModel stakeHolderModel) throws IOException;
	
	
}
