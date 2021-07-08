<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
	//getContextPath() : 톰캣 루트 path를 가져옴
	out.print("getContextPath() : " + request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
	<title>로그인 폼</title>
</head>
<body>

	<form action="<%=request.getContextPath()%>/chapter09/company/login.jsp" method="post">
		아이디 : <input type="text" name="id" size="10" /><br>
		이름 : <input type="text" name="name" size="10" /><br>
		비민번호 : <input type="password" name="password" size="10" /><br>		
		<input type="submit" value="로그인" />
	</form>

</body>
</html>