<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	//memberid 및 name 파라미터의 값을 구함
	String memberid = request.getParameter("memberid");
	String name = request.getParameter("name");
	
	//jdbc드라이버 로딩
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	Connection conn = null;
	Statement stmt = null;
// 	ResultSet rs = null;
	/*
		rs를 사용하지 않는 이유는, 해당 페이지에서 select 쿼리를 사용하지 않기 때문이다
		
		select 쿼리를 사용하면,
		그 리스트를 담을 곳이 필요하기 때문에 ResultSet을 사용한다.
		insert, update, delete 쿼리를 사용하면,
		쿼리 이후의 반환 값이 없는 DML들이므로 rs사용 X	
	*/
	
	int updateCount = 0;
	
	try{
		String jdbcDriver = "jdbc:oracle:thin:@localhost:1521/xe"; //xe는 시퀀스(유일한값) 아이디(si)라고 한다
		String dbUser = "jspexam";
		String dbPass = "java";
		
		//name 내부에 만약 '가 들어간다면 name.replaceAll()함수를 사용한다.
		String query = "UPDATE MEMBER " 
					 + " SET NAME = '"+ name +"' "
					 + " WHERE MEMBERID = '"+ memberid +"'";
		
		//데이터베이스와 연결된 Connection을 생성
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		
		//Connection으로부터 Statement(형식)을 생성
		stmt = conn.createStatement();
		
		//return : 변경된 레코드 개수(0보다 크면, 변경된 값이 존재)
		updateCount = stmt.executeUpdate(query);
		/*
			Statement 객체가 제공하는 메서드로 쿼리 실행
			- ResultSet executeQuery(String query) : SELECT 쿼리 실행 시 사용
			- int executeUpdate(String query) : INSERT, UPDATE, DELETE 쿼리 실행 시 사용
			
			//예전에 고급자바쌤이 그렇게 물어보셨던 거다 ㅋㅋ 쿼리써요? 업데이트써요?		
		*/
		
		
	}catch(SQLException ex) {
		ex.printStackTrace();
		
	}finally {
		if(stmt != null) try{stmt.close();} catch(SQLException ex){}
		if(conn != null) try{conn.close();} catch(SQLException ex){}
	}
	
%>
<!DOCTYPE html>
<html>
<head>
	<title>Insert title here</title>
</head>
<body>
<%
if(updateCount > 0) { //업데이트가 되었다면
	out.print(name + "(으)로 이름 변경 성공");	
}else { //업데이트 실패
	out.print(memberid + " 아이디가 없습니다.");
}
%>
	<br/>
	<%=memberid %><br/>
	<%=name %><br/>
</body>
</html>