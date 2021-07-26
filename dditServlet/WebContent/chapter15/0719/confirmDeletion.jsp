<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>방명록 메시지 삭제 확인</title>
</head>
<body>
	<form method="post" action="deleteMessage.jsp">
		<input type="text" name="messageId" value="${param.messageId}" />
		메시지를 삭제하려면 비밀번호를 입력하세요 <br/>
		비밀번호 : <input type="password" name="password" /><br/>
		<input type="submit" value="메시지 삭제하기" />
	</form>
</body>
</html>