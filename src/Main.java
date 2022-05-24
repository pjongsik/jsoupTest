import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			//loginTest();
			
			SimplePost.requestPost("", null, null);
			
		//	SimplePost.formLogin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}
	
	
	public static void loginTest() throws IOException  {

		final String uri = "https://www.auc.or.kr/sign/in/base/user"; 
//		Jsoup.connect(uri).get();
		

		// 쿠키값 셋팅
		Connection.Response loginPageResponse = Jsoup.connect(uri)
				.timeout(3000)
				.header("Origin", "https://www.auc.or.kr")
				.header("Referer", uri)
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.header("Accept-Encoding", "gzip, deflate, br")
				.header("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4")
				.method(Connection.Method.GET)
				.execute();

				Map<String, String> cookies = loginPageResponse.cookies();

		// 로그인 호출
		Connection.Response response = Jsoup.connect(uri)
				.data("member_id", "pjongsik", "member_password", "pjs091128**")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36")
				.cookie("PHPSESSID", loginPageResponse.cookies().get("PHPSESSID"))
				.method(Connection.Method.POST)
				.timeout(5000)
				.execute();
			
					
		// 로그인후 쿠키값 셋팅
		//Document doc = Jsoup.connect("http://www.test.co.kr/product/product_detail.htm?ProductNo=test").cookies(resonse.cookies()).get();
		
		// 원하는 값 추출
		//oc.select("span.txt").select(".co_red").text() 
		
			
	}

}
