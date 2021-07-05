<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page errorPage="/error_0705/noParamEx.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>파라미터가 없는 오류가 발생함</title>
</head>
<body>
	
<%

/*
	/error/noParamEx.jsp를 생성하여 오류를 처리해보자.
	noParamEx.jsp에는 적당한 오류 이미지를 넣어보자.
	이미지는 chapter06 폴더 안에 넣은 후 <img 태그로 처리해보자.
*/
	String memId = request.getParameter("memId").toUpperCase();	
	String memName = request.getParameter("memName").toUpperCase();	

%>
	
</body>
</html>