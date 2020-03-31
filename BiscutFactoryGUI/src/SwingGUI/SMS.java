package SwingGUI;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class SMS {

	
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		String msg = "HelloDinuwanKalubowila";
		String mobile = "766061689";
		String username="0766061689";
		String password = "4873";
		
		
		
		URL url = new URL("http://api.liyanagegroup.com/sms_api.php?sms=" +msg+ "&to=" +mobile+ "&usr=" +username+ "&pw=" +password+" ");
		System.out.println(url);
		
		
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
		try(OutputStream os = http.getOutputStream()) {
		    os.write(out);
		}
	}
	
}
