<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String memberId = (String)session.getAttribute("MEMBERID");
	//로그인되면 true, 로그인 안되면 false
	boolean login = memberId == null ? false : true;
%>
<!DOCTYPE html>
<html>
<head>
	<title>로그인 여부 검사</title>
</head>
<body>
	
</body>
</html>
<%
if(login) {
%>
	현재 아이디<%=memberId%>로 로그인한 상태입니다.
<%
}else {
%>
	현재 로그아웃 상태입니다.
<%
}
%>