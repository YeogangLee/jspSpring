<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
	request.setCharacterEncoding("UTF-8");

	String memId = request.getParameter("memId") == null ? "" : request.getParameter("memId");
	String memName = request.getParameter("memName") == null ? "" : request.getParameter("memName");
	String gender = request.getParameter("gender") == null ? "" : request.getParameter("gender");
	String sel = request.getParameter("sel") == null ? "" : request.getParameter("sel");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<% if(memId!=null) { %>
		<title>registOk</title>
	<% }else { %>
		<title>회원가입 실패</title>
	<% } %>
</head>
<body>
	<% if(memId!=null) { %>
		id 파라미터 : <%=memId %><br>
		name 파라미터 : <%=memName %><br>
		gender 파라미터 : <%=gender %><br>
		sel 파라미터 : <%=sel %><br>
		<button type="button" onclick="goRedirect()">리다이렉트</button>
	<% }else { %>
		출력할 값이 없습니다.
	<% } %>
</body>
<script type="text/javascript">
	function goRedirect(){
		
	<%
		String memId2 = URLEncoder.encode(memId, "UTF-8");
		String memName2 = URLEncoder.encode(memName, "UTF-8");
		String gender2 = URLEncoder.encode(gender, "UTF-8");
		
		response.sendRedirect("/chapter03/0630/registOk2.jsp?memId=" + memId2 
															+ "&memName=" + memName2 
															+ "&gender=" + gender2 
															+ "&sel=" + sel);
	%>
	}
	
</script>
</html>