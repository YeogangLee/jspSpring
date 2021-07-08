<%@page import="util.Cookies"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
상단 메뉴 :
<a href="../0705/layout.jsp">홈</a>&nbsp; <!-- chapter07경로를 포함하면 오류가 난다... -->
<a href="../0705/info.jsp">정보</a>&nbsp; <!-- jsp파일 바로 상위 폴더는 적어도 되지만, 더 상위 폴더는 적으면 안 되는 거 같다. -->
&emsp;
<%
	//cookieMap.put(cookies[i].getName(), cookies[i]);
	Cookies cookies = new Cookies(request);
	
	//id라는 이름의 쿠키가 존재하면 실행
	if(cookies.exists("id")) { //로그인 상태
		out.print(cookies.getValue("id") + "님 환영!");
%>
	<!-- id쿠키가 존재할 때만 보이는 로그아웃  -->
	<input type="button" value="로그아웃" onclick="fn_logout()" />
<%
	}else { //로그아웃 상태
%>
		<input type="button" value="로그인" onclick="fn_login()" />
<%
	}
%>
<script>
	function fn_logout() {
		location.href= "/chapter09/0708/logout.jsp";
	}
	function fn_login() {
		//chater07/0705/layout.jsp를 복사한 후 내용부분에 chapter09/0708/login.jsp를 include
		location.href= "/chapter07/0708/login.jsp";
	}
</script>