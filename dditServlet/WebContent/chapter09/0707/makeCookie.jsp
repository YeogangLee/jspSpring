<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
/*
	<쿠키의 구성>
	1. 이름 : 각각의 쿠키를 구별 ***
	2. 값 : 쿠키의 이름과 관련된 값 ***
*/
	//추가할 쿠키 정보를 담고 있는 Cookie 객체를 생성
	//한글 value가 깨질 경우 URLEncoder 이용
	Cookie cookie = new Cookie("name", URLEncoder.encode("개똥이", "UTF-8"));
	
	//응답 데이터에 쿠키를 추가
	response.addCookie(cookie);
	
%>
<!DOCTYPE html>
<html>
<head>
	<title>쿠키 생성</title>
	<script type="text/javascript">
		function fn_view() {
			location.href = "/chapter09/0707/viewCookies.jsp";
		}
	</script>
</head>
<body>
	<!-- 별도 유효 시간을 지정하지 않으면 웹 브라우저를 종료시 쿠키도 함께 삭제됨 -->
	<%=cookie.getName() %> 쿠키의 값 = <%=cookie.getValue() %>
	<!-- 
		<쿠키 동작 방식>
		1. 생성 : 웹 서버 측에서 생성함.
				생성한 쿠키를 응답 데이터의 헤더에 저장하여 웹 브라우저에 전송함.
		2. 저장 : 웹 브라우저는 응답 데이터에 포함된 쿠키를 쿠키 저장소에 보관함.
		3. 전송 : 웹 브라우저는 요청이 있을 때마다 쿠키를 웹 서버에 전송함.
				
		웹 브라우저 -- 요청 -- > 웹 서버 
		=> 리퀘스트 (+ 쿠키 : 쿠키 저장소에 쿠키가 없을시 요청을 받은 웹 서버에서 새로운 쿠키 생성)
		
		웹 브라우저 < -- 응답 -- 웹 서버 
		=> 리스폰스 + 쿠키 (+ 쿠키 저장 : 새로운 쿠키일시 웹 브라우저가 내부 쿠키 저장소에 쿠키 저장)
		
	 -->
	 <input type="button" value="쿠키보기" onclick="fn_view()" />
	  
</body>
</html>