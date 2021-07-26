<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//jsp는 세션을 기본 객체로 제공하므로, 이렇게 이용 가능, 하지만 java는 X
// 	session.setAttribute(name, value);
%>
<!DOCTYPE html>
<html>
<head>
	<title>로그인 FORM</title>
</head>
<body>
	<form method="post" action="login.do">
		<c:if test="${errors.idOrPwNotMatch}">
			아이디 또는 비밀번호가 잘못되었습니다.
		</c:if>
		<p>
			아이디: <br/><input type="text" name="memberid" value="${param.memberid}" />
			<c:if test="${errors.memberid}">아이디를 입력해주세요</c:if>
		</p>
		<p>
			비밀번호: <br/><input type="password" name="password" value="" />
			<c:if test="${errors.password}">비밀번호를 입력해주세요</c:if>
		</p>
		<input type="submit" value="로그인" />
	</form>
</body>
</html>