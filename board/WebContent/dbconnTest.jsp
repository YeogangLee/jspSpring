<%@page import="java.sql.SQLException"%>
<%@page import="jdbc.ConnectionProvider"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>DB 연결 테스트</title>
</head>
<body>
<%
	try {
		Connection conn = ConnectionProvider.getConnection();
		out.print("커넥션 연결 성공");
		
	}catch(SQLException ex) {
		out.print("커넥션 연결 실패 : " + ex.getMessage());
		application.log("커넥션 연결 실패", ex);
	}
%>
</body>
</html>