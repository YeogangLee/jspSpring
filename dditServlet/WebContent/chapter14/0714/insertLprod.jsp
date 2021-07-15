<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");

	//lprodId는 해당 값을 삽입할 컬럼인 LPROD_ID가 NUMBER로 선언되어 있으므로
	//형변환을 통해 마찬가지로 int로 넣어줘야 한다.
	int lprodId = request.getParameter("lprodId") == "" ? 0 : Integer.parseInt((request.getParameter("lprodId")));
	String lprodGu = request.getParameter("lprodGu");
	String lprodNm = request.getParameter("lprodNm");
	
	//드라이버 로딩 이후에 커넥션이 가능하다.
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	//rs는 현재 insert 쿼리를 이용할 거라 선언 및 사용X
	
	try {
		String jdbcDriver = "jdbc:oracle:thin:@localhost:1521/xe";
		String dbUser = "stu";
		String dbPass = "java";
		
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);

		//실행할 쿼리를 생성
		pstmt = conn.prepareStatement(
					"insert into lprod(lprod_id, lprod_gu, lprod_nm) values(?, ?, ?)"
				);
		
		pstmt.setInt(1, lprodId);
		pstmt.setString(2, lprodGu);
		pstmt.setString(3, lprodNm);
		
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
	<title>물품 등록 처리</title>
</head>
<body>
	LPROD 테이블에 새로운 레코드 삽입 완료
</body>
</html>