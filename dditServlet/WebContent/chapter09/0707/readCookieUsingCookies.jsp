<%@page import="util.Cookies"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	//Cookie[] cookies = request.getCookies();
	Cookies cookies = new Cookies(request);
%>
<!DOCTYPE html>
<html>
<head>
	<title>Cookies 클래스 사용</title>
</head>
<body>
	<%=cookies.getValue("id") %>
</body>
</html>