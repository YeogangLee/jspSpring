<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try {
// 		String jdbcDriver = "jdbc:oracle:thin:@localhost:1521/xe";
		String jdbcDriver = "jdbc:apache:commons:dbcp:chapter14";
		String query = "select * from member order by memberid";
		conn = DriverManager.getConnection(jdbcDriver);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
%>
<!DOCTYPE html>
<html>
<head>
	<title>회원 목록</title>
</head>
<body>
	<table border="1" style="width: 100%">
		<tr>
			<th>이름</th><th>아이디</th><th>이메일</th>
		</tr>
<%
	while(rs.next()) {
%>
	<tr>
		<td><%=rs.getString("NAME") %></td>
		<td><%=rs.getString("MEMBERID") %></td>
		<td><%=rs.getString("EMAIL") %></td>
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
	if(rs!=null) try{rs.close();} catch(SQLException ex){}
	if(stmt!=null) try{stmt.close();} catch(SQLException ex){}
	if(conn!=null) try{conn.close();} catch(SQLException ex){}
	//커넥션 풀 방식은 conn.close()를 해도 메모리에서 사라지지 않는다.
	//커넥션 풀에 있는 커넥션 객체를 가져와 사용한 것이기 때문
}
%>
