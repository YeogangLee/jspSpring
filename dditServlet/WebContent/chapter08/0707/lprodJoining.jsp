<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="lprodInfo" class="chap08.lprod.LprodVO" />
<jsp:setProperty name="lprodInfo" property="*" />
<!DOCTYPE html>
<html>
<head>
	<title>상품 추가 완료</title>
</head>
<body>
	<form name="frm" action="/chapter08/0707/lprodJoining.jsp" method="post">
		<table border="1" style="width: 50%">
			<tr>
				<th>상품분류 아이디</th>
				<td>
					<jsp:getProperty property="lprodId" name="lprodInfo" />
				</td>
			</tr>
			<tr>
				<th>상품분류 코드</th>
				<td>
					<jsp:getProperty property="lprodGu" name="lprodInfo" />
				</td>
			</tr>
			<tr>
				<th>상품분류명</th>
				<td>
					<jsp:getProperty property="lprodNm" name="lprodInfo" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>