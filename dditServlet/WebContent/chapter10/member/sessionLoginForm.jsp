<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>로그인 폼</title>
</head>
<body>
	<form method="post" action="/chapter10/member/sessionLogin.jsp"> 
		아이디 : <input type="text" name="id" size="10"/><br/>
		비밀번호 : <input type="password" name="password" size="10"/><br/>
		<input type="submit" value="로그인" />
	</form>	
</body>
</html>