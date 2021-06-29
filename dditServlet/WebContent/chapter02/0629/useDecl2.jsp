<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! //선언부(Declaration)를 시작함

	public int add(int a, int b) {
		int c = a + b;
		return c;
	}

	public int subtract(int a, int b) {
		int c = a - b;
		return c;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트릿에서 선언부 사용하기</title>
</head>
<body>
	3 + 9 = <%=add(3, 9) %><br>
	3 - 9 = <%=subtract(3, 9) %>
</body>
</html>