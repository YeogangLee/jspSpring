<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name") == null ? "없음" : request.getParameter("name");
%>
<!DOCTYPE html>
<html>
<head>
	<title>파라미터 값의 인코딩/디코딩</title>
	<script type="text/javascript">
		function goURL(){
			//대입연산자는 오른쪽부터 실행을 한다.
			var varName = document.getElementById("name");
// 			var loc = '/chapter02/0630/testEncoding.jsp?name='+encodeURI(encodeURIComponent(varName.value));
			var loc = '/chapter02/0630/testEncoding.jsp?name='+encodeURI(varName.value);
			location.href = loc;
		}
	</script>
</head>
<body>
	<h2>파라미터 값의 인코딩/디코딩</h2>
<%--name : <%=URLDecoder.decode(name, "UTF-8") %> --%>
	name : <%=name %>
	<!--
		페이지에서 디코딩처리하지 않는 방법
		1. server.xml 파일 Connector 태그에
		   URIEncoding="UTF-8" 작성
		2. Window탭  Preferences에서 문서 전부 UTF-8로 지정 
	-->
	<br>
	<input type="text" name="name" id="name" value="" placeholder="이름을 입력하세요." /><br>
	<input type="button" id="name" value="이동" onclick="goURL()" /><br>
	<a href="/chapter02/0630/testEncoding.jsp?name=<%=URLEncoder.encode(name, "UTF-8") %>">이동</a>
</body>
</html>