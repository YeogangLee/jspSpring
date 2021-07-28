<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>일기</title>
</head>
<body>
	<!-- form 태그 없이는 파라미터를 넘길 수 없다. 내가 아는 선에서는.. -->	
	<form method="post" action="/detail" id="fmDate">
		날짜 : <a href="#" onclick="document.getElementById('fmDate').submit();">${date}</a>
		<input type="hidden" name="date" value="${date}"/>
	</form>
</body>
</html>