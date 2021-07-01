<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	// 삼항 연산자를 사용하여 request.getParameter의 NULL을 체크함.
	String id = request.getParameter("memberId") == null ? "" : request.getParameter("memberId");

	if(id.equals("ddit")) {
		
		String name = "개똥이";
		String encodedName = URLEncoder.encode(name, "UTF-8");
		
		response.sendRedirect("/chapter03/0630/index.jsp?name=" + encodedName);
// 		response.sendRedirect("/chapter03/0630/index.jsp");
		//웹 브라우저에게 요청이 한 번 더 가게 되는 것
		// -> 결과적으로 요청 2번 : 1. 해당 문서  2. sendRedirect의 파라미터 문서
		
	}else {
	/*
		여기 문서를 바로 호출하면 파라미터가 없으므로, else를 탄다.
		여기 문서 호출 : localhost:8090/chapter03/0630/login.jsp	
	*/
%>
<!DOCTYPE html>
<html>
<head>
	<title>login 실패</title>
</head>
<body>
	아이디가 ddit가 아닙니다.
</body>
</html>
<%	} %>