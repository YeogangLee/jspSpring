<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page errorPage="/error_0705/viewErrorMessage.jsp" %>
<!-- 
	JSP는 실행 도중 exception이 발생시 오류 화면 대신 지정한 jsp 페이지를 보여줄 수 있다.
	: page directive(지령, command)에 errorPage 속성 이용
 -->
<!DOCTYPE html>
<html>
<head>
	<title>파라미터 출력</title>
</head>
<body>
<!-- 
	name 파라미터가 없으면 request.getParameter("name")은 null을 리턴하므로
	실행 도중 문제가 생겨 NullPointerException을 발생시킨다.
 -->
<%-- 	기존 방식
		name 파라미터 값 : <%=request.getParameter("name").toUpperCase() %> --%>
		
	name 파라미터 값 :
	
	<%
	
	/* 오류 해결방법1. try-catch 이용 */
// 	try{
		out.print(request.getParameter("name").toUpperCase());
		
// 	}catch(Exception ex) {
// 		out.print("name 파라미터가 올바르지 않습니다.");
// 	}
		
	%>
	
</body>
</html>