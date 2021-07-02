<%@page import="java.net.URL"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>application 기본 객체를 사용하여 자원 읽기</title>
</head>
<body>
<%
	//상위 폴더로 간다고 ../ 쓰면 안되고, /상위폴더명/ 이렇게 다 적어줘야 한다.
	String resourcePath = "/chapter05/message/notice.txt";
%>
	자원의 실제 경로<br>
<%
/*
D:\A_TeachingMaterial\6.JspSpring\workspace\.metadata\.plugins
\org.eclipse.wst.server.core
\tmp0\wtpwebapps\dditServlet\chapter05\message\notice.txt
*/
	out.print(application.getRealPath(resourcePath));
%>
	<br>---------------------------------------------<br>
<%
	char[] buff = new char[128];
	int len = -1;
	URL url = application.getResource(resourcePath);
	
	try {
		
		//new FileInputStream(resourcePath) <-- realPath 절대 경로
		//InputStreamReader br = new InputStreamReader(new FileInputStream(resourcePath), "UTF-8");
				
		//어플리케이션으로 파일 읽기, 상대 경로
		InputStreamReader br = new InputStreamReader(application.getResourceAsStream(resourcePath), "UTF-8");
		
		//상대 경로
// 		InputStreamReader br = new InputStreamReader(url.openStream(), "UTF-8");
		
		while((len=br.read(buff))!=-1) {
			out.print(new String(buff, 0, len));
		}
		
	}catch(IOException ex) {
		out.print("Exception 발생 : " + ex.getMessage());
	}
	
%>

<!-- 
	<application기본 객체의 자원 접근 메서드>
	
	- getRealPath(String path) : 리턴 타입  String
	  : 웹 어플리케이션 내에서 지정한 경로에 해당하는 자원의 시스템 상에서의 자원 경로를 리턴
	
	- getResource(String path) : 리턴 타입 URL
	  : 웹 어플리케이션 내에서 지정한 경로에 해당하는 자원에 접근할 수 있는 URL 객체를 리턴
	  
	  ex)
	  URL url = application.getResource(resourcePath);
	  url.openStream();
	  
	- getResourceAsStream(String path) : 리턴 타입 InputStream
	  : 웹 어플리케이션 내에서 지정한 경로에 해당하는 자원으로부터
	  	데이터를 쉽게 읽어올 수 있는 InputStream을 리턴함
	    application.getResourceAsStream(resourcePath)
 -->
	

</body>
</html>