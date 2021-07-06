<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="member" scope="request" type="chap08.member.MemberInfo" />
<%
/*
	<jsp:useBean의 class속성과 type속성의 차이점>
	
	1) class 속성
	- request 기본 객체에 member 속성이 없으면
	    새로운 MemberInfo 클래스의 member객체를 생성함
	  ex.
	  <jsp:useBean id="member" scope="request" class="chap08.member.MemberInfo" />
	
	2) type 속성
	- 지정한 request 기본 객체에 member 속성이 이미 존재한다고 가정함
	    직접 jsp로 접근시 해당 속성의 값이 없으므로  MemberInfo클래스의 member객체를 생성하지 않고 에러를 발생시킴
	- MemberInfo member = (MemberInfo)request.getAttribute("member");
	  if(member==null) {
		  //에러 발생
	  }
	  ex.
	  <jsp:useBean id="member" scope="request" type="chap08.member.MemberInfo" />
*/
%>
<!DOCTYPE html>
<html>
<head>
	<title>인사말</title>
</head>
<body>
<!-- 
	<각 jsp페이지에서 수행하는 일>
	
	1) makeObject.jsp : MemberInfo클래스의 member객체를 생성 후 
						request 기본 객체의 member 속성에 저장 후 useObject.jsp로 포워딩(요청 흐름 변경)
						
	2) useObject.jsp : jsp:useBean 액션 태그를 사용하여 makeObject.jsp가 생성한 객체를 사용
 -->
	<%=member.getName() %>(<%=member.getId() %>) 회원님<br /> 안녕하세요!
</body>
</html>