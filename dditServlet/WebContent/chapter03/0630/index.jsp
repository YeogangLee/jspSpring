<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	//"name"인 이유는 해당 페이지를 호출한 곳에서 name속성이름을 이용해서 파라미터를 넘겼기 때문
	String name = request.getParameter("name");
	
	/*
		< Encoding인코딩 >
		- 알파벳과 숫자 그리고 몇몇 기호를 제외한 나머지 글자들을 URL에 포함시키려면 인코딩을 해주어야 함
		- response.sendRedirect() 메서드를 사용하는 경우에도
		    마찬가지로 인코딩한 쿼리 문자열(ex.?name=개똥이)을 사용해야 함.
		- java.net.URLEncoder 클래스가 존재하고,
		  URLEncoder.encode() 메서드를 사용하면 파라미터 값으로 사용될 문자열을,
		   지정한 캐릭터 셋으로 인코딩 할 수 있다.
		   
		   도대체 크롬, 익스플로러는 왜 이렇게 차이가 날까?
		   크롬, 파이어폭스는 웹 브라우저 자체에서 알아서 UTF-8로, 인코딩된 주소 경로나 파라미터 값을 해당 문자로 변환해 준다.
		   반면 인터넷 익스플로러(IE)는 인코딩된 파라미터 값을 그대로 보여줌.  
		
	*/
	
%>
<!DOCTYPE html>
<html>
<head>
	<title>index</title>
</head>
<body>
	여기는 index.jsp 페이지입니다.<br>
	<%=name %>
</body>
</html>