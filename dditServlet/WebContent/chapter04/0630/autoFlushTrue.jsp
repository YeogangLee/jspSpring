<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page buffer="1kb" autoFlush="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>autoFlush 속성값 true 예제</title>
</head>
<body>
<%
    //크기가 4kb 이상의 데이터가 생성됨
//	for(int i=0; i<=1000; i++) {
//		out.print("1234");
//	}

/*
	<request 기본 객체 - 주요 정보 제공 메서드>
	- getRemoteAddr() : 클라이언트의 IP주소를 구함
						ex.게시판 같은거, 작성자의 IP를 구하는 것
	- getMethod() : 웹 브라우저가 정보 전송시 사용한 방식을 구함(GET, POST)
	- getRequestURI() : 웹 브라우저가 요청한 URL 경로
	- getContextPath() : 웹 어플리케이션의 컨텍스트 경로
	- getServerName() : 연결시 사용한 서버 이름을 구함
	- getServerPort() : 서버가 실행 중인 포트 번호를 구함
	
*/
%>
	웹 어플리케이션 컨텍스트 경로 : <%=request.getContextPath() %>
	클라이언트의 IP주소 : <%=request.getRemoteAddr() %>
	웹 브라우저가 요청한 uri경로 : <%=request.getRequestURI() %>
	웹 브라우저가 요청한 url경로 : <%=request.getRequestURL() %>
	연결시 사용한 서버 이름 : <%=request.getServerName() %>
	실행중인 포트 번호 : <%=request.getServerPort() %>
	
</body>
</html>