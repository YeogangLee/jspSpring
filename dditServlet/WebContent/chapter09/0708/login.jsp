<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="util.Cookies"%>
<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	// id가 001이면서 동시에 비밀번호가 1234인 경우 로그인 성공
	if(id.equals("a001") && password.equals("1234")) {
		
		//새로 생성
		//Cookie cookie = new Cookie("id", id);
		
		response.addCookie(
			//이미 이 쿠키가 있는 것
			Cookies.createCookie("id", id, "/", -1)
			// /는 루트패스 = 쿠키가 생성되는 패스 -> 어디서 접근하든 쿠키를 볼 수 있다
			// -1 : 브라우저가 꺼질 때까지 쿠키가 존재한다.
		);
%>
<!DOCTYPE html>
<html>
<head>
<title>로그인 성공</title>
</head>
<body>
	로그인에 성공했습니다. <br />
	<div class="count" style="font-size:17px;">5</div>
	<script type="text/javascript">
		const countDisplay = document.querySelector(".count");
		
		//1초마다 countDown 함수를 실행
		setInterval(countDown, 1000);
		var time = 5;
		function countDown() {
			if(time > 0) { // 5 4 3 2 1
				time--;
				countDisplay.innerText = time;
			}else { // 0
				location.href = "/chapter07/0705/layout.jsp";
			}
		}
		
	</script>
</body>
</html>
<%
} else { //로그인 실패시
	out.print("<script>alert('로그인 실패');history.go(-1);</script>");	
}
%>
