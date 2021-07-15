<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int lprodId = request.getParameter("lprodId").equals("") ? 0 : Integer.parseInt((request.getParameter("lprodId")));
	String lprodGu = request.getParameter("lprodGu");
	String lprodNm = request.getParameter("lprodNm");
%>
<!DOCTYPE html>
<html>
<head>
	<title>LPROD 수정 form</title>
</head>
<body>
	<form method="post" action="/chapter14/0714/updateLprod.jsp">
		<table border="1" style="width: 100%">
			<tr>
				<c:set var="lprodId" value='<%=lprodId %>' />
				<th>아이디</th><td><input type="text" name="lprodId" value="${lprodId}" /></td>
			</tr>
			<tr>
				<c:set var="lprodGu" value='<%=lprodGu %>' />
				<th>구분</th><td><input type="text" name="lprodGu" value="${lprodGu}" readonly /></td>
			</tr>
			<tr>
				<c:set var="lprodNm" value='<%=lprodNm %>' />
				<th>이름</th><td><input type="text" name="lprodNm" value="${lprodNm}" /></td>
			</tr>
		</table>
		<input type="submit" value="물품 수정" />
	</form>
	<a href="/chapter14/0714/viewLprodList.jsp">목록</a>
</body>
</html>