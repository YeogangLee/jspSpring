<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>쿠키 값 변경</title>
	<script type="text/javascript">
		function fn_view() {
			location.href = "/chapter09/0707/viewCookies.jsp";
			
		}
	
	</script>
</head>
<body>
<!-- 
	Cookie cookie = new Cookie("name", URLEncoder.encode("개똥이", "UTF-8"));
 -->
<%
	//이름이 name인 Cookie 객체를 새롭게 생성
	Cookie cookie = new Cookie("name", URLEncoder.encode("김은대", "UTF-8"));
	//응답 헤더에 추가함 => name 쿠키의 값이 변경됨
	response.addCookie(cookie);
	//서버에서 만들어지기 때문에 response 이용
%>
	name 쿠키의 값을 변경합니다.
	<input type="button" value="쿠키 목록" onclick="fn_view()" />
	
	
</body>
</html>