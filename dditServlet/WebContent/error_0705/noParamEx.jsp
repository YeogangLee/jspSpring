<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>
	<!-- 에러 이미지 -->
	<img src="../../chapter06/error.jpg" alt="에러" style="height: 400px">
	<br />
	<br />
	오류가 발생했습니다.<br /><br />
	빠른 시간 내에 문제를 해결하도록 하겠습니다. <br /><br />
	
	오류 타입 : <%=exception.getClass().getName() %><br />
	오류 메시지 : <%=exception.getMessage() %><br />
</body>
</html>