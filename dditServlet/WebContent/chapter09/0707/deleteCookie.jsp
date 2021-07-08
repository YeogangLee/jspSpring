<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	Cookie cookie = new Cookie("name","개똥이");
	//Cookie 클래스는 쿠키를 삭제하는 기능이 없음
	//유효 시간을 0으로 지정함(setMaxAge()) => 초단위로 지정
	//60 * 60 => 60초(1분) * 60 = 1시간
	//0초를 초과해서 유효시간을 정해놓으면 웹 브라우저를 종료해도 해당 시간만큼 쿠키가 삭제되지 않고 웹 서버에 전송이 됨 
	cookie.setMaxAge(0); // 60 * 60
	//웹 브라우저를 닫았음에도 불구하고, 쿠키가 살아있다. 개똥이가 살아있다.
	//유효 시간의 특징 : 웹 브라우저를 닫더라도 쿠키가 살아있더라.
	//응답 헤더에 추가
	response.addCookie(cookie);
%>
<!DOCTYPE html>
<html>
<head>
	<title>쿠키 삭제</title>
	<script type="text/javascript">
		function fn_view() {
			location.href = "/chapter09/0707/viewCookies.jsp";
			
		}
	</script>
</head>
<body>
	name 쿠키를 삭제합니다.
	<input type="button" value="쿠키 목록" onclick="fn_view()" />
</body>
</html>