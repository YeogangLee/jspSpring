<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>초기화 파라미터 읽어오기</title>
</head>
<body>
	<h2>초기화 파라미터 목록</h2>
	<%
	/*
		<application 객체>
		- 웹 어플리케이션과 관련된 기본 객체
		- 특정 웹 애플리케이션에 포함된 모든 jsp 페이지는
		    하나의 application 기본 객체를 공유한다. -> 같은 application 객체를 바라보고 있더라.
		- 초기 설정 정보를 읽어올 수 있음(ex.서버 정보)
	*/
		Enumeration<String> initParamEnum = application.getInitParameterNames();
		while(initParamEnum.hasMoreElements()) {
			String initParamName = initParamEnum.nextElement();
			out.print(initParamName + " = " + application.getInitParameter(initParamName) + "<br>");
		}
	%>
	서버 정보 : <%=application.getServerInfo() %><br>
	<!-- 메이저는 정수 부분, 마이너는 소수 부분 ex)3.1  메이저:3 마이너:1 -->
	서블릿 규약 메이저 버전 : <%=application.getMajorVersion() %><br>
	서블릿 규약 마이너 버전 : <%=application.getMinorVersion() %><br>
	
	<%
		//[톰캣설치폴더]\logs폴더로 아래 로그가 들어간다.
		application.log("(이여산) 로그 메시지 기록");
	%>
</body>
</html>