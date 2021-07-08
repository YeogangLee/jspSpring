<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	Cookie cookies = new Cookie("id","");

	//유효시간을 0으로 설정 => 쿠키가 삭제되는 효과
	cookies.setMaxAge(0);
	cookies.setPath("/");
	//setPath()가 없으면 뒤로 가면 로그인이 되어있는 상태
	//생성할 때 setPath를 해줬으니까, 삭제할 때도 setPath를 해줘야 한다.
	response.addCookie(cookies);
%>
<!DOCTYPE html>
<html>
<head>
	<title>로그아웃 </title>
	<script type="text/javascript">
		function fn_main() {
			var chk = confirm("메인화면으로 이동하시겠습니까?");
			if(chk){
				location.href = "/chapter07/0705/layout.jsp";
			}
			
		}
	
	</script>
</head>
<body>
	로그아웃 되었습니다. <br />
	<input type="button" value="메인" onclick="fn_main()"/>
</body>
</html>