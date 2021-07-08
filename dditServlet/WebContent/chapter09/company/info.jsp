<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>회사정보</title>
</head>
<body>

<table border="1" style="width:100%;" cellpadding="0" cellspacing="0">
<!-- top.jsp 시작 -->
<tr style="height:50px;">
	<td colspan="3">
		<jsp:include page="/chapter09/company/top.jsp" />
	</td>
</tr>
<!-- top.jsp 끝 -->
<tr style="height:300px;">
	<td style="width:10%;">
		<jsp:include page="/chapter09/company/left.jsp" />
	</td>
	<td colspan="2">
		<img src="/chapter09/company/info.jpg" />
	</td>
</tr>
<tr style="height:50px;">
	<td colspan="3">
		<jsp:include page="/chapter09/company/bottom.jsp" />
	</td>
</tr>
</table>

</body>
</html>