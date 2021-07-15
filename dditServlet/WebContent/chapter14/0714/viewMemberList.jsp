<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//1. JDBC 드라이버 로딩(= 메모리에 올리는 것)
// 	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	Connection conn = null;	//연결 객체
	Statement stmt = null;	//형식 지정
	ResultSet rs = null;	//Query 실행 결과를 담을 객체
	
	try{
		//1. 오라클 드라이버 : Thin 드라이버(모듈 필요X), OCI드라이버(모듈 설치 필요)
		String jdbcDriver = "jdbc:oracle:thin:@localhost:1521/xe"; //그래서 thin 드라이버 사용
		String dbUser = "jspexam";
		String dbPass = "java";
		String query = "select * from member order by memberid";
		
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
	<title>회원 목록</title>
</head>
<body>
	<h2>member 테이블의 내용</h2>
	<input type="button" value="회원추가" onclick="javascript:location.href='/chapter14/0714/insertForm.jsp';" />
	<table border="1" style="width: 100%;">
		<tr>
			<th>이름</th><th>아이디</th><th>이메일</th>
		</tr>
		<%
		while(rs.next()) {
		%>
		<c:set var="memberid" value='<%=rs.getString("MEMBERID")%>' />
		<tr>
			<td><a href="/chapter14/0714/viewMember.jsp?memberid=${memberid}"><%=rs.getString("NAME") %></a></td>
			<td><%=rs.getString("MEMBERID") %></td><td><%=rs.getString("EMAIL") %></td>
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