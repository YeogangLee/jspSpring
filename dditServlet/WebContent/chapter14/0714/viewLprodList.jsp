<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//1. JDBC 드라이버 로딩(= 메모리에 올리는 것)
	/*
		- DBMS와 통신을 담당하는 자바 클래스
		- DBMS와 통신하기 위해서는 먼저 로딩 해줘야 함
		- Class.forName 메서드를 통해 로딩 시 자동으로 jdbc드라이버로 등록함
	
	*/
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	Connection conn = null;	//연결 객체
	Statement stmt = null;	//형식 지정
	ResultSet rs = null;	//Query 실행 결과를 담을 객체
	
	try{
		//1. 오라클 드라이버 : Thin 드라이버(모듈 필요X), OCI드라이버(모듈 설치 필요)
		//JDBC URL : DBMS와의 연결을 위한 식별값
		String jdbcDriver = "jdbc:oracle:thin:@localhost:1521/xe"; //그래서 thin 드라이버 사용
		String dbUser = "stu";
		String dbPass = "java";
		String query = "select * from lprod order by lprod_gu";
		
		//2. 데이터베이스 커넥션 생성
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		
		//3. Statement 객체 생성
		stmt = conn.createStatement();
		
		//4. Query 실행 (execute)
		rs = stmt.executeQuery(query);
%>
<!DOCTYPE html>
<html>
<head>
	<title>LPROD 목록</title>
</head>
<body>
	<h2>LPROD 테이블의 내용</h2>
	<input type="button" value="물품 입력" onclick="javascript:location.href='/chapter14/0714/insertFormLprod.jsp';" />
	<table border="1" style="width: 100%;">
		<tr>
			<th>아이디</th><th>구분</th><th>이름</th>
		</tr>
		<%
		while(rs.next()) {
		/*
			데이터 조회를 위한 주요 메서드
			1. 문자형 : getString()
			2. 숫자형 : getInt(), getLong(), getFloat(), getDouble()
			3. 날짜형 : getTimestamp()(단위 millisec), getDate(), getTime()
		*/
		%>
		<c:set var="lprodGu" value='<%=rs.getString("LPROD_GU") %>' />
		<tr>
			<td><%=rs.getString("LPROD_ID") %></td>
			<td><a href="/chapter14/0714/viewLprod.jsp?lprodGu=${lprodGu}"><%=rs.getString("LPROD_GU") %></a></td>
			<td><%=rs.getString("LPROD_NM") %></td>
		</tr>
		<%			
		}
		%>
	</table>
</body>
</html>
<%
	}catch(SQLException ex) {
		ex.printStackTrace();
		
	}finally {
		if(rs!=null) try{rs.close();} catch(SQLException ex) {}
		if(stmt!=null) try{stmt.close();} catch(SQLException ex) {}
		if(conn!=null) try{conn.close();} catch(SQLException ex) {}
	}
%>