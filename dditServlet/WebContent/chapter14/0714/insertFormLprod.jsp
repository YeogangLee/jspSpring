<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>물품 입력 폼</title>
</head>
<body>
	<form method="post" action="/chapter14/0714/insertLprod.jsp">
		<table border="1" style="width: 100%">
			<tr>
				<th>아이디</th><td><input type="text" name="lprodId" placeholder="물품 아이디 입력" /></td>
			</tr>
			<tr>
				<th>구분</th><td><input type="text" name="lprodGu" placeholder="물품 구분 입력" /></td>
			</tr>
			<tr>
				<th>이름</th><td><input type="text" name="lprodNm" placeholder="물품 이름 입력" /></td>
			</tr>
		</table>
		<input type="submit" value="물품 등록" />
	</form>
	<a href="/chapter14/0714/viewLprodList.jsp">목록</a>
</body>
</html>