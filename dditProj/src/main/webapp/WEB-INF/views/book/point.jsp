<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>성적 계산</title>
</head>
<body>
	<form method="post" action="/point_result">
		이름 : <input type="text" name="name" /><br/>
		국어 : <input type="text" name="kor" /><br/>
		영어 : <input type="text" name="eng" /><br/>
		수학 : <input type="text" name="mat" /><br/>
		<input type="submit" value="확인" /><br/>
	</form>
</body>
</html>