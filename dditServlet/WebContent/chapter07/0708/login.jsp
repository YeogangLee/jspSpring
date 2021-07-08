<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>layout1</title>
</head>
<body>
	<table border="1" style="width: 100%" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="2">
				<jsp:include page="../../module_0705/top.jsp" flush="false"></jsp:include>
			</td>
		</tr>
		<tr>
			<td style="width: 30%" valign="top">
				<jsp:include page="../../module_0705/left.jsp" flush="false"></jsp:include>
			</td>
			<td>
				<!-- 내용 부분 : 시작 -->
				<jsp:include page="/chapter09/0708/loginForm.jsp" flush="false"></jsp:include>
				<!-- 내용 부분 : 끝 -->
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<jsp:include page="../../module_0705/bottom.jsp" />
			</td>
		</tr>
	</table>
</body>
</html>