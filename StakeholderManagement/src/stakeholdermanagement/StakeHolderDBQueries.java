package stakeholdermanagement;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import biscutdb.DB;
import biscutdb.IDB;

public class StakeHolderDBQueries implements IStakeHolder{

	
	private Connection conn = null;
	private Statement statement = null;
	private IDB db;
	private ResultSet resultSet;
	
	
	public  StakeHolderDBQueries() {
		db = new DB();
		conn = db.dbConn();
	}
	
	
	@Override
	public boolean loginUser(StakeHolderModel stakeHolderModel) {

	String sql = "SELECT * FROM users WHERE mobile= '"+stakeHolderModel.getMobile()+"' AND pwd = '"+stakeHolderModel.getPwd()+"' ";
		
		try {
			
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			return resultSet.next();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	
	}


	@Override
	public String getVerificationKey(StakeHolderModel stakeHolderModel) {

	String sql = "SELECT * FROM users WHERE mobile= '"+stakeHolderModel.getMobile()+"' AND pwd = '"+stakeHolderModel.getPwd()+"' ";
		
		try {
			
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				stakeHolderModel.setSecureKey(resultSet.getString("securekey"));
			}
			
			return stakeHolderModel.getSecureKey();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "Invalid Credetials";
		}
		
		
	}
	
	
	
	
	@Override
	public void sendSMS(StakeHolderModel stakeHolderModel) throws IOException {
		// TODO Auto-generated method stub
		
		String msg = "Welcome_to_OSGI_Supply_Chain_Management_System.Your_Authentiation_code_is_:_"+stakeHolderModel.getSecureKey();
		String mobile = "94"+stakeHolderModel.getMobile();
		String username="0766061689";
		String password = "4873";
		
		
		
		URL url;
		url = new URL("http://api.liyanagegroup.com/sms_api.php?sms=" +msg+ "&to=" +mobile+ "&usr=" +username+ "&pw=" +password+" ");
		
		URLConnection con = url.openConnection();
		HttpURLConnection http = (HttpURLConnection)con;
		http.setRequestMethod("POST"); // PUT is another valid option
		http.setDoOutput(true);
		
		Map<String,String> arguments = new HashMap<>();
		 // This is a fake password obviously
		StringJoiner sj = new StringJoiner("&");
		for(Map.Entry<String,String> entry : arguments.entrySet())
		    sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" 
		         + URLEncoder.encode(entry.getValue(), "UTF-8"));
		byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
		int length = out.length;
		
		http.setFixedLengthStreamingMode(length);
		http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		http.connect();
		
		System.out.println(url);
		try(OutputStream os = http.getOutputStream()) {
		    os.write(out);
		}
		
	}



}
