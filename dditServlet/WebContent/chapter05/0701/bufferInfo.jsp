<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page buffer="8kb" autoFlush="true" %>
<%
	//page directive
	//buffer속성의 값을 8kb로 설정
	//autoFlush : false => 페이지의 결과 데이터가 8kb를 초과시 오류 발생 
%>
<!DOCTYPE html>
<html>
<head>
	<title>버퍼 정보</title>
</head>
<body>
<%
	for(int i=0; i<10000; i++) {
		out.print("개똥이");
	}
%>
	<br><br>
	버퍼 크기 : <%=out.getBufferSize() %><br>
	남은 버퍼 크기 : <%=out.getRemaining() %><br>
	autoFlush 여부 : <%=out.isAutoFlush() %><br>
</body>
</html>