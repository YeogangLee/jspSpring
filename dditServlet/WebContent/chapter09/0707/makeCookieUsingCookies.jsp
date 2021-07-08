<%@page import="util.Cookies"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	//서버에서 쿠키가 만들어지기 때문에, 응답 정보에 담겨오는 쿠키를 위해, response 객체가 필요
	response.addCookie(
		Cookies.createCookie("id", "ddit", "/", -1)
	);
	
%>
<!DOCTYPE html>
<html>
<head>
	<title>Cookies 클래스 사용 예</title>
</head>
<body>
	<a href="/chapter09/0707/readCookieUsingCookies.jsp">생성된 쿠키 정보 확인</a>
</body>
</html>