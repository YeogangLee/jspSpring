<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String memId = request.getParameter("memId") == null ? "" : request.getParameter("memId");
	String memName = request.getParameter("memName") == null ? "" : request.getParameter("memName");
	String gender = request.getParameter("gender") == null ? "" : request.getParameter("gender");
	String sel = request.getParameter("sel") == null ? "" : request.getParameter("sel");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>registOk2</title>
</head>
<body>
	여기는 registOk2.jsp 페이지입니다.<br>
	: 파라미터 2번 출력하기<br><br>
	
	id 파라미터 : <%=memId %><br>
	name 파라미터 : <%=memName %><br>
	gender 파라미터 : <%=gender %><br>
	sel 파라미터 : <%=sel %><br>
</body>
</html>