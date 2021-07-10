<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	request.setAttribute("name", "민들레");
	session.setAttribute("name", "김은대");
	application.setAttribute("name", "신용재");
%>
<!DOCTYPE html>
<html>
<head>
	<title>EL Object</title>
</head>
<body>

<!-- request 기본 객체의 name 속성의 값을 가져옴 -->
기존 방식<br/>(스크립트 릿 이용) : <%=request.getAttribute("name") %><br/><br/>
<!-- request 영역에 저장된 name 속성의 값을 가져옴 -->
<%-- 새로운 방식<br/>(EL 이용) : ${requestScope.name}<br/><br/> --%>

requestScope 지우기<br/>(EL 이용) : ${name}<br/><br/>
requestScope : request<br/>(EL 이용) : ${requestScope.name}<br/><br/>
requestScope : session<br/>(EL 이용) : ${sessionScope.name}<br/><br/>
requestScope : application<br/>(EL 이용) : ${applicationScope.name}<br/><br/>

요청 URI : ${pageContext.request.requestURI}<br/>

<!-- http://localhost:8090/chapter11/0709/useELObject.jsp?code=P101 -->
<!-- 
	* EL의 장점 *
	code 파라미터 값이 없더라도 null을 출력하는 것이 아니라
	아무것도 출력하지 않음  ==> (오류가 발생하지 않음)
 -->
code 파라미터 : ${param.code}
<!-- 
	* 표현 언어(Expression Language)
	- JSP에서 사용 가능한 새로운 스크립트 언어
	- 간단한 구문 때문에 표현식 (꺽임쇠 퍼센트 =) 대신 사용함
	- JSP의 4가지 기본 객체가 제공하는 영역의 속성 사용
	  (page, request, session, application)
	- 집합 객체에 대한 접근 방법 제공
	- 수치 / 관계 / 논리 연산자 제공
	- 자바 클래스의 메서드 호출 기능 제공
	- 표현 언어만의 기본 객체 제공
	- 스크립트 요소(스크립트 릿, 표현식, 선언부)를 제외한 나머지 부분에서 사용
 -->

</body>
</html>