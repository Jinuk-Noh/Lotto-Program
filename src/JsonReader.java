import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JsonReader {
	public JSONObject connectionUrlToJSON(String turn) throws Exception{
		try {
		URL url=new URL("https://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo="+turn);
		HttpsURLConnection conn = null;
		HostnameVerifier hostValid = new HostnameVerifier() {
		
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true; //중요
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(hostValid);	
		conn =(HttpsURLConnection) url.openConnection(); // url 에 연결 중요
		
		 //InputStreamReader isr = new InputStreamReader(conn.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); //복잡하게 쌓여있어야함
		String iLine = br.readLine();
		JSONParser ps = new JSONParser();
		JSONObject jObj = (JSONObject) ps.parse(iLine); //중요
		return jObj;
		}catch(Exception e) {
			System.out.println("접속 실패");
			return null;
		}
		
		
		
	}
	
}
