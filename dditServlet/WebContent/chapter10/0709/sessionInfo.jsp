<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page session="true" %> <!-- session 속성의 디폴트값, 이걸로 서버에 저장해놓는다. -->
<%
//long 타입의 시간 값을 저장하기 위해 사용
Date time = new Date();

//Date 객체의 시간 값을 지정한 양식으로 출력
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<!DOCTYPE html>
<html>
<head>
	<title>세션정보</title>
</head>
<body>
<!-- 
	* 세션 *
	1. 세션은 웹 컨테이너(tomcat)에 정보를 보관할 때 사용. 세션은 오직 서버에만 생성.
	2. 쿠키와의 차이점은, 세션은 웹 브라우저가 아니라 서버에 값을 저장
	3. 서버는 세션을 사용해서 클라이언트 상태를 유지하므로 로그인한 사용자 정보를 유지하기 위한 목적으로 많이 이용된다.
	4. 웹 컨테이너(tomcat)는 기본적으로 한 웹 브라우저(크롬, 파이어폭스, IE..)마다 한 세션을 생성
	5. 같은 JSP 페이지라도 웹 브라우저에 따라 서로 다른 세션을 사용 
	6. page 디렉티브의 session 속성의 기본값은 true. 즉, false로 억지로 지정하지만 않으면 자동 생성된다.
	7. 웹 브라우저가 처음 접속 시 세션을 1회 생성하고, 그 이후로는 이미 생성된 세션을 사용함
	8. 웹 서버는 세션ID를 이용해서 웹 브라우저를 위한 세션을 찾음. 
	   JSESSIONID인 쿠키가 웹 서버와 웹 브라우저의 세션 ID를 공유할 수 있도록 해줌
 -->
	세션 ID : <%=session.getId() %>
<%
	//세션의 생성시간을 가져와서 Date객체인 time에 저장
	time.setTime(session.getCreationTime());
%>
	<br>
	세션 생성 시간 : <%=formatter.format(time)%>
	<br>
<%
	//세션에 최근 접근한 시간(1970년 1월 1일 이후 흘러간 시간, 1/1000초)
	time.setTime(session.getLastAccessedTime());
%>
	최근 접근 시간 : <%=formatter.format(time) %><br>
<%
	//오브젝트로 받아오기 때문에, 형변환 필요
	String memberId = (String)session.getAttribute("MEMBERID");
	String memberName = (String)session.getAttribute("NAME");
	
	if(memberId!=null) { //로그인되었다면...
		out.print(memberName + "(" + memberId + ")님 환영합니다.");
%>
	<a href="/chapter10/0709/closeSession.jsp">로그아웃</a>
<%
}else {
%>
	<a href="/chapter10/0709/setMemberInfo.jsp">로그인</a>
<%} %>
</body>
</html>