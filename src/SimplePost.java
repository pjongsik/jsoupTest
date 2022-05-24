import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

public class SimplePost {

	public static void requestPost(String uri, String id, String password) throws IOException {
		
		final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
		
		Document doc1 =	Jsoup.connect("https://www.auc.or.kr/SSOService.do")
		                                        .userAgent(USER_AGENT)
		                                        .referrer("https://www.auc.or.kr/sign/in/base/user?menuLevel=2&menuNo=346")
		                                        .data("param1", "param1_value")
		                                        .data("param2", "param2_value")
		                                        .method(Method.POST)
		                                        .get();
		

		String agt_r =doc1.select("input#agt_r").attr("value");
		
		System.out.println("agt_r : " + agt_r );
		
		
		
			
		System.out.println(doc1.html());
		System.out.println("agt_r : " + agt_r);
		
		Document doc2 =	Jsoup.connect("https://newsso.anyang.go.kr/sso/usr/login/link")
                .userAgent(USER_AGENT)
                .referrer("https://www.auc.or.kr/SSOService.do")
                .data("agt_url", "https://www.auc.or.kr")
                .data("agt_r",  agt_r)
                .data("param1", "param1_value")
                .data("param2", "param2_value")
                .method(Method.POST)
                .get();
		
		System.out.println("===");
		System.out.println("===");
		System.out.println("===");
		System.out.println(doc2.html());
		/*
		Connection.Response loginResponse  =Jsoup.connect("https://newsso.anyang.go.kr/sso/usr/login/view")
				.data("login_id", "pjongsik79")
                .data("login_pwd", "pjs091128**")
                .data("user_data", "")
                .data("login_key", "351DF824268ABA5AAE0114727D08435D")
                .method(Method.POST)
                .execute();	            
	
		Map<String, String> cookies = loginResponse.cookies();

		cookies.entrySet().forEach(entry -> {
		    System.out.println(entry.getKey() + " " + entry.getValue());
		});
		
							
		
		Document doc1 = Jsoup.connect("https://www.auc.or.kr/bmacamp/program/camp/list?menuLevel=2&menuNo=133&currentDate=20220605&gubunValue=1").cookies(loginResponse.cookies()).get();
		
		 System.out.println(doc1.html());
		 */
	}
	
	
	public static void formLogin() throws IOException {
		// # Constants used in this example
		final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36"; 
		final String LOGIN_FORM_URL = "https://newsso.anyang.go.kr/sso/usr/login/view";
		final String USERNAME = "pjongsik79";  
		final String PASSWORD = "pjs091128**";  

		// # Go to login page
		Connection.Response loginFormResponse = Jsoup.connect(LOGIN_FORM_URL)
		                                             .method(Connection.Method.GET)
		                                             .userAgent(USER_AGENT)
		                                             .execute();  
		System.out.println("loginFormResponse.parse().toString() ==> ");
		System.out.println(loginFormResponse.parse().toString());
		
		
		// # Fill the login form
		// ## Find the form first...
		FormElement loginForm = (FormElement)loginFormResponse.parse()
		                                         .select("div#wrapper > form").first();
		
		System.out.println("Login Form html == > ");
		System.out.println(loginForm.html());
		
		checkElement("Login Form", loginForm);

		// ## ... then "type" the username ...
		Element loginField = loginForm.select("#login_id").first();
		checkElement("Login Field", loginField);
		loginField.val(USERNAME);

		// ## ... and "type" the password
		Element passwordField = loginForm.select("#login_pwd").first();
		checkElement("Password Field", passwordField);
		passwordField.val(PASSWORD);        


		// # Now send the form for login
		Connection.Response loginActionResponse = loginForm.submit()
		         .cookies(loginFormResponse.cookies())
		         .userAgent(USER_AGENT)  
		         .execute();

		System.out.println(loginActionResponse.parse().html());

		
	}
	
	public static void checkElement(String name, Element elem) {
	    if (elem == null) {
	        throw new RuntimeException("Unable to find " + name);
	    }
	}
}
