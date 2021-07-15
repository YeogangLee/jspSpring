<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");

	String memberid = request.getParameter("memberid");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	
	//jdbc 드라이버 로딩 : 메모리에 jdbc드라이버를 올리는 것
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	//드라이버 로딩 이후에 커넥션이 가능하다.
	Connection conn = null;
	/*
		PreparedStatement
		
		- 간결한 코드를 위해 사용
		  (= 쿼리 문자열 사이를 갈라서 +를 넣는다거나, 문자열은 ''사용, 숫자는 ''사용X 등의 작업을 할 필요가  X)
		- 값 변환을 자동으로 처리하기 위해
		- 검색 조건과 같이 값을 지정해야 하는 쿼리 실행 시 선호함	
	*/
	
	//이번에는 pstmt 사용 -> ? 쓰고 나중에 값 넣어주는 쿼리
	PreparedStatement pstmt = null;
	
	//rs는 현재 insert 쿼리를 이용할 거라 선언 및 사용X
	
	try {
		String jdbcDriver = "jdbc:oracle:thin:@localhost:1521/xe";
		String dbUser = "jspexam";
		String dbPass = "java";
		
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);

		//실행할 쿼리를 생성
		pstmt = conn.prepareStatement(
					"insert into member(memberid, password, name, email) values(?, ?, ?, ?)"
				);
		
		pstmt.setString(1, memberid);
		pstmt.setString(2, password);
		pstmt.setString(3, name);
		pstmt.setString(4, email);
		
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
	<title>회원 가입 처리</title>
</head>
<body>
	MEMBER 테이블에 새로운 레코드 삽입 완료
</body>
</html>