<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="chapter11.vo.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	StudentVO studentVo = new StudentVO();
	Map<String, String> map = new HashMap<String, String>();
%>
<!DOCTYPE html>
<html>
<head>
	<title>JST if 태그 연습</title>
</head>
<body>
	<!-- 조건문의 값이 항상 true이므로 항상 실행됨 -->
	<c:if test="true">
		작성자 : 홍길동</br>
	</c:if>
	<!-- http://localhost:8090/chapter12/0712/use_if_tag.jsp?name=ddit -->
	<c:if test="${param.name=='ddit'}">
		name 파라미터 값이 ${param.name}입니다. <br/>
	</c:if>
	<!-- http://localhost:8090/chapter12/0712/use_if_tag.jsp?name=ddit&age=25 -->
	<c:if test="${21 < param.age}">
		당신의 나이는 21세 이상입니다.<br/>
	</c:if>
	<!-- 
		* JSTL이란...
		- jsp:include와 같은 개발자가 추가할 수 있음.(커스텀 태그)
		    커스텀 태그 중에서 많이 사용되는 것들을 모아
		  JSTL(JSP(Java Server Page) Standard Tag Library)이라는 표준을 만듦
		- JSTL을 사용하면 스크립트릿 코드의 사용을 줄이면서 간결하고 이해하기 쉬운 JSP 코드를 작성할 수 있음.
		
		* 코어 태그(c) : 많이 사용해서 따로 만들어 둔 것
					     그래서 아까 5가지 태그들 중에서도 코어 태그가 가장 많이 쓰인다.
		- 변수 지원(set, remove)
		- 흐름 제어(if, choose, forEach, forTokens)
		- URL 처리(import, redirect, url)
		- 기타 태그(catch, out)
	-->
	<br/>
	<!--
		var는 엄밀히 말하면 변수는 아니고, 저장되는 곳이 있다 : scope
		기본 scope는 page이므로, scope="page"는 생략해도 된다.
		
		scope를 request를 지정하고 싶으면 scope 속성을 사용한다.
		request 속성을 상당히 많이 사용한다.
		
		scope: page, request, session, application
	-->
	
	<!-- String 넣기 -->
	<c:set var="name" value="개똥이" scope="request"/>
	<!-- 
		scope를 정하지 않으면, 
		모든 scope에 있는 var="name" 속성이 삭제된다.
		그래서 c:remove를 사용할 때는 반드시 scope를 지정해준다.
	-->
	<c:remove var="name" scope="page"/> <!-- 여기의 scope="page"를 지우면, 아래의 이름 : 옆에 값이 나오지 않는다. -->
	이름 : ${name}<br/>
	<c:if test="${name=='개똥이'}">
		이름이 동일함<br/>	
	</c:if>
	<c:if test="${name!='개똥이'}">
		이름이 동일하지 않음<br/>	
	</c:if>
	<c:set target="<%=studentVo%>" property="studentName" value="개똥이" />
	1. <%=studentVo.getStudentName()%><br/>
	<br/>
	<!-- 스크립트 릿으로 객체 넣기 -->
	<c:set var="student" value="<%=studentVo%>"/>
	2. ${student.studentName}<br/>
	<c:set target="${student}" property="studentNo" value="20210712" />
	2-2. ${student.studentNo}<br/>
	<hr />
	<!-- property는 키다. 키와 밸류 ... -->
	<c:set target="<%=map %>" property="id" value="a001" />
	1. <%=map.get("id")%>
	<c:set var="map2" value="<%=map%>" />
	<c:set target="${map2}" property="name" value="김은대" />
	2. ${map2.name}<br/> 
</body>
</html>







