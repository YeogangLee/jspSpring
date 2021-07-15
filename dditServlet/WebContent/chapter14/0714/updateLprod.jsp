<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");

// 	int lprodId = Integer.parseInt((request.getParameter("lprodId")));
	int lprodId = request.getParameter("lprodId").equals("") ? 0 : Integer.parseInt((request.getParameter("lprodId")));
	String lprodGu = request.getParameter("lprodGu");
	String lprodNm = request.getParameter("lprodNm");
	
	//드라이버 로딩 이후에 커넥션이 가능하다.
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	//rs는 현재 update 쿼리를 이용할 거라 선언 및 사용X
	
	try {
		String jdbcDriver = "jdbc:oracle:thin:@localhost:1521/xe";
		String dbUser = "stu";
		String dbPass = "java";
		
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);

		//실행할 쿼리를 생성
		pstmt = conn.prepareStatement(
					"update lprod set lprod_id = ?, lprod_nm = ? where lprod_gu = ?"
				);
		
		pstmt.setInt(1, lprodId);
		pstmt.setString(2, lprodNm);
		pstmt.setString(3, lprodGu);
		
		//query 실행
		pstmt.executeUpdate();
		
	}finally { //자원 반납 -> 메모리에서 삭제시키기
		if(pstmt!=null) try{pstmt.close();} catch(SQLException ex){} 
		if(conn!=null) try{conn.close();} catch(SQLException ex){} 
	}
%>
<!DOCTYPE html>
<html>
<head>
	<title>물품 수정 처리</title>
</head>
<body>
	LPROD 테이블에 LPROD(<%=lprodGu %>) 레코드 수정 완료
</body>
</html>