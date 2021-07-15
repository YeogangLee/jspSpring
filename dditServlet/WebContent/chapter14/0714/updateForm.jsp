<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>회원명 변경 form</title>
</head>
<body>
	
	<form name="frm" method="post" action="/chapter14/0714/update.jsp">
		<table border="1" style="width: 100%">
			<tr>
				<th>아이디</th>
				<td><input type="test" name="memberid" placeholder="아이디 입력" /></td>
				<th>이름</th>
				<td><input type="test" name="name" placeholder="이름 입력" /></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="submit" value="변경" />
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>