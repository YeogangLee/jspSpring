<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	//request객체를 통해 넘어오는 모든 정보를 encoding 해준다
	request.setCharacterEncoding("UTF-8");

	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	out.print("title : " + title + "<br>");
	out.print("content : " + content + "<br>");
	
	//Map : java.util 패키지에 있는 interface 
	Map<String, Object> map = new HashMap<>();
	map.put("title", title);
	map.put("content", content);
	
	session.setAttribute("map", map);
	
%>
<!DOCTYPE html>
<html>
<head>
	<title>게시글 등록</title>
</head>
<body>
	게시글을 등록했습니다.<br />
	<a href="/chapter07/0706/freeboardview.jsp">[게시글 내용 보기]</a>
	<%
	Map<String, Object> map2 = (Map<String, Object>)session.getAttribute("map");
	//??
	%>
</body>
</html>