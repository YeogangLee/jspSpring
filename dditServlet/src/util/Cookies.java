package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Cookies {

	// <쿠키이름, Cookie객체> 쌍 형태로 쿠키를 저장하는 맵을 생성
	private Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
	
	// 쿠키 클래스 생성자
	// request(요청) 안에는 쿠키가 있기 때문에 쿠키 정보를 getCookies()로 읽어온다.
	// request 안에는 파라미터로 전달되고 있는 Cookie들이 있음 : 서버로 툭툭 던지는 것, 던지는 것들을 읽는 것 (getCookies())
	public Cookies(HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
		
		//각각의 Cookie 객체를 cookieMap에 저장
		if(cookies!=null) {
			for(int i=0; i<cookies.length; i++) {
								//키(String)		  , value(객체)
				cookieMap.put(cookies[i].getName(), cookies[i]); //getValue() 안해? -> 이미 위에서 처리 했다
			}
		}
	}
	
	/*
	 	공통점 : 아래의 두 메서드 모두 파라미터로 name을 이용한다.
	 	차이점 : get쿠키는 쿠키객체를 return, get밸류는 쿠키 객체의 값을 return
	 */
	
	//cookieMap에서 지정한 이름의 Cookie객체를 get
	//만약, 지정한 이름의 Cookie가 없으면 null을 리턴
	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}
	
	//지정한 이름의 쿠키 객체의 값을 get
	public String getValue(String name) throws IOException {
		Cookie cookie = cookieMap.get(name);
		if(cookie==null) {
			return null;			
		}
		return URLDecoder.decode(cookie.getValue(), "UTF-8");
	}
	
	//지정한 쿠키 이름의 Cookie가 있을 경우 true, 없을 경우 false를 리턴
	public boolean exists(String name) {
		return cookieMap.get(name) != null;
	}
	
	//이름이 name이고, 값이 value인 Cookie객체를 생성하여 리턴
	public static Cookie createCookie(String name, String value) throws IOException {
		return new Cookie(name, URLEncoder.encode(value, "UTF-8"));
	}
	
	//오버로딩
	//이름이 name이고, 값이 value, 요청 경로가 path, 유효 시간이 maxAge인  Cookie객체를 생성하여 리턴
	public static Cookie createCookie(String name, String value, String path, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	//오버로딩
	//이름이 name이고, 값이 value, 도메인이 domain, 요청 경로가 path, 유효 시간이 maxAge인  Cookie객체를 생성하여 리턴
	public static Cookie createCookie(String name, String value, 
										String domain, String path, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
}
