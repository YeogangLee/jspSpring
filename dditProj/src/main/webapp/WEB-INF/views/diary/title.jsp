<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>일기</title>
</head>
<body>
	날짜 : <a href="/detail">${date}</a>
	<input type="hidden" name="date" value="${date}"/>
	<input type="text" name="text" value="설마 이거?"/>
</body>
</html>