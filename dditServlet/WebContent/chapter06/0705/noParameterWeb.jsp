<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>파라미터 없는 오류 처리(web.xml)</title>
</head>
<body>
<!-- 
	<에러 페이지 처리>
	우선순위 1. page 디렉티브의 errorPage 속성 사용 (전용 에러 처리, 해당 페이지 전용 에러 페이지가 필요할 경우 사용, exception객체 사용 가능)
	우선순위 2. web.xml 파일의 <exception-type> (범용 에러 처리)
	우선순위 3. web.xml 파일의 <error-code>		(별도 에러 처리)
	우선순위 4. 아무것도 안 했을 땐... 웹 컨테이너가 제공하는 기본 에러 페이지를 보여준다.
 -->
<%
	String memId = request.getParameter("memId").toString();
%>
</body>
</html>