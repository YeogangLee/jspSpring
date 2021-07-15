<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>방명록 목록</title>
</head>
<body>
	<form method="post" action="writeMessage.jsp">
		이름 : <input type="text" name="guestName" /><br/>
		비밀번호 : <input type="password" name="password" /><br/>
		메시지 : <textarea rows="3" cols="30" name="message"></textarea><br/>
		<input type="submit" value="메시지 남기기" />	
	</form>
</body>
</html>