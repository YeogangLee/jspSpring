<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%
	//import 속성 사용 전
// 	java.util.Calendar cal = java.util.Calendar.getInstance();

	//import 속성 사용 후
	Calendar cal = Calendar.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendar 클래스 사용</title>
</head>
<body>
<!-- 
	완전한 클래스 이름을 사용해도 JSP 페이지가 올바르게 실행됨.
	하지만, 번거로움
	-> import 속성 사용
 -->
	오늘은
	<%=cal.get(java.util.Calendar.YEAR) %>년
	<%=cal.get(java.util.Calendar.MONTH) %>월
	<%=cal.get(java.util.Calendar.DATE) %>일
</body>
</html>