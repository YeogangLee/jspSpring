<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
//세션 종료. 기존 session 객체가 제거됨.
//다음 요청에서 새로운 session 기본 객체가 생성됨
session.invalidate(); //무효화

%>
<!DOCTYPE html>
<html>
<head>
	<title>세션 종료</title>
</head>
<body>
	세션을 종료하였습니다. <br/>
	<a href="/chapter10/0709/setMemberInfo.jsp">로그인</a><br/>
	<a href="/chapter10/0709/sessionInfo.jsp">세션목록</a><br/>
		
</body>
</html>