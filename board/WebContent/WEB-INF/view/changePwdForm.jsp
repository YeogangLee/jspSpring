<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>비밀번호 변경</title>
</head>
<body>
	<form method="post" action="changePwd.do">
		<p>
			현재 비밀번호 : <br/> <input type="password" name="curPwd" />
			<c:if test="${errors.curPwd}">현재 비밀번호를 입력해주세요.</c:if>
			<c:if test="${errors.badCurPwd}">현재 비밀번호가 일치하지 않습니다.</c:if>
		</p>
		<p>
			신규 비밀번호 : <br/> <input type="password" name="newPwd" />
			<c:if test="${errors.newPwd}">신규 비밀번호를 입력해주세요.</c:if>
		</p>
		<input type="submit" value="비밀번호 변경" />
	</form>
</body>
</html>