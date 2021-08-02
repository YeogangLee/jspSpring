<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String method = request.getMethod();
// 	out.print(method);
%>
<!DOCTYPE html>
<html>
<head>
	<title>loginResult</title>
</head>
<body>
	<!-- 실화냐......... -->
	<c:if test="<%=method.equals(\"GET\")%>">
		<form id="frm" action="/member/logout" method="get">
			<input type="submit" id="btnLogout" value="로그아웃2" /><br/>
		</form>
	</c:if>
	<c:if test="${loginOk == 'true'}">
		<h2>${sessionScope.memberVoDB.name}님(${sessionScope.memberVoDB.memberid}) 환영합니다.</h2>
	</c:if>
	<!-- 없는 아이디를 입력하니까.. 500에러 발생 : java.lang.NullPointerException -->
	<!-- 결과 출력 -->
	<div id="result">${result}</div>
</body>
</html>