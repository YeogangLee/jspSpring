<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String lprodGu = request.getParameter("lprodGu");

	//파라미터 값이 잘 들어오는지 확인
// 	out.print("lprodGu : " + lprodGu);
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
		String jdbcDriver = "jdbc:oracle:thin:@localhost:1521/xe";
		String dbUser = "stu";
		String dbPass = "java";
		
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		String query = "select LPROD_ID, LPROD_GU, LPROD_NM from lprod where lprod_gu = ?";
		
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, lprodGu);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
%>
<!DOCTYPE html>
<html>
<head>
	<title>LPROD 상세 정보</title>
</head>
<body>
	<form method="post" action="/chapter14/0714/updateFormLprod.jsp">
		<table border="1" style="width: 100%">
			<tr>
				<!-- ?? -->
				<th>아이디</th><td><%=rs.getString("LPROD_ID")%></td>
			</tr>
			<tr>
				<th>구분</th><td><%=lprodGu%></td>
			</tr>
			<tr>
				<th>이름</th><td><%=rs.getString("LPROD_NM")%></td>
			</tr>
		</table>
	<!-- 클릭 시 이동 -->
	<a href="/chapter14/0714/viewLprodList.jsp">목록</a>
<%-- 	<c:set var="lprodGu" value='<%=rs.getString("LPROD_GU") %>' />	 --%>
<%-- 	<a href="/chapter14/0714/updateFormLprod.jsp?lprodGu=${lprodGu}">수정</a> --%>
	<input type="submit" value="수정" />
	</form>
	
	<!-- 아래 JSTL을 사용하면 페이지가 바로 이동하므로, 페이지 이동은 되지만 바람직하지 않다. -->
	<%--<c:redirect url="/chapter14/0714/viewLprodList.jsp" /> --%>
</body>
</html>
<%
		} //endif
		else {
			out.print(lprodGu + "에 해당하는 정보가 없습니다.");
		}
		
	}catch(SQLException ex) {
		ex.printStackTrace();
		
	}finally{
		if(rs != null) try{rs.close();} catch(SQLException ex){}
		if(pstmt != null) try{pstmt.close();} catch(SQLException ex){}
		if(conn != null) try{conn.close();} catch(SQLException ex){}
	}
%>