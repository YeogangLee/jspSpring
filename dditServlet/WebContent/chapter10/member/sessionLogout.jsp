<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	//로그아웃 방법1.
	//세션 무효화(세션 삭제) -> 로그아웃
// 	session.invalidate();
	
	//로그아웃 방법2. 
	//세션 속성 삭제, 잘 쓰지는 않는다.
	//로그인 상태를 보관 시 사용한 session객체 속성 삭제
	//단점) session 기본 객체에 추가하는 속성이 늘어나면 각각을 처리해야 하므로 비효율적임.
	session.removeAttribute("MEMBERID");
%>
<!DOCTYPE html>
<html>
<head>
	<title>로그아웃</title>
</head>
<body>
	로그아웃하였습니다.
	<a href="/chapter10/member/sessionLoginCheck.jsp">로그인 여부 판단</a><br/>
</body>
</html>