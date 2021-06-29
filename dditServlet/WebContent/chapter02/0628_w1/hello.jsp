<!-- 페이지 디렉티브 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello jsp</title>
</head>
<body>
<!-- 스크립트 릿 -->
<%
	String str = "Hello jsp!";
%>
	<!-- 표현식 -->
	<h2><%=str %></h2>
</body>
</html>