<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>일기</title>
	<script type="text/javascript">
		
		
	</script>
</head>
<body>
<%-- 	날짜 : <a href="/detail">${date}</a> --%>
<%-- 	<input type="hidden" name="date" value="${date}"/> --%>
	
	<form method="post" action="/detail" id="fmDate">
		날짜 : <a href="#" onclick="document.getElementById('fmDate').submit();">${date}</a>
		<input type="hidden" name="date" value="${date}"/>
	</form>
</body>
</html>