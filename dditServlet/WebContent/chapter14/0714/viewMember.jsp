<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//chapter14/0714/viewMember.jsp?memberid=${memberid}
	//memberid키에 담긴  ${memberid} 파라미터 값을 이용한다.
	
	String memberid = request.getParameter("memberid");

	//파라미터 값이 잘 들어오는지 확인
// 	out.print("memberid : " + memberid);
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	try {
		String jdbcDriver = "jdbc:oracle:thin:@localhost:1521/xe";
		String dbUser = "jspexam";
		String dbPass = "java";
		String query = "select * from member where memberid = '" + memberid + "'";
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);	//select문이라서 executeQuery이용
		
		if(rs.next()){
%>
<!DOCTYPE html>
<html>
<head>
	<title>회원 상세 정보</title>
</head>
<body>
	<table border="1" style="width: 100%">
		<tr>
			<th>아이디</th><td><%=memberid%></td>
		</tr>
		<tr>
			<th>비밀번호</th><td><%=rs.getString("password")%></td>
		</tr>
		<tr>
			<th>이름</th><td><%=rs.getString("name")%></td>
		</tr>
		<tr>
			<th>이메일</th><td><%=rs.getString("email")%></td>
		</tr>
	</table>
	<!-- 클릭 시 이동 -->
	<a href="/chapter14/0714/viewMemberList.jsp">목록</a>
	
	<!-- 아래 JSTL을 사용하면 페이지가 바로 이동하므로, 페이지 이동은 되지만 바람직하지 않다. -->
<%--<c:redirect url="/chapter14/0714/viewMemberList.jsp" /> --%>
</body>
</html>
<%
		} //endif
		else {
			out.print(memberid + "에 해당하는 정보가 없습니다.");
		}
		
	}catch(SQLException ex) {
		ex.printStackTrace();
		
	}finally{
		if(rs != null) try{rs.close();} catch(SQLException ex){}
		if(stmt != null) try{stmt.close();} catch(SQLException ex){}
		if(conn != null) try{conn.close();} catch(SQLException ex){}
	}
%>