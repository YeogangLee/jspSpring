<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Choose 태그 연습</title>
</head>
<body>
	<c:set var="name" value="개똥이2" />
	<c:set var="age" value="25" />
	
	<!-- 
		choose 태그 내의
		when태그는 위에서부터 읽어가며,
		when이 1개라도 true이면 코드를 수행하고 choose문을 나간다.
		그래서 아래의 when태그가 true이더라도, 출력되지 못한다.
		자바와 다른 점이다.
		
		name과 param.name은 다르다.
		그래서 각각 when 태그를 사용할 경우,
		먼저 작성된 when의 true인 게 출력된다.
		
		name=='소똥이' 하면 한글 인식에 오류가 나서 파라미터 인식이 안 된다.
		바로 아래의 age의 when으로 넘어간다 
		그래서 일단 지금 파라미터는 kim 등의 영어, 숫자로 넘긴다.
	 -->
	<c:choose>
		<c:when test="${name=='개똥이'}">
			이름은 ${name}<br/>		
		</c:when>
		<c:when test="${param.name=='kim'}">
			이름은 ${param.name}<br/>		
		</c:when>
		<c:when test="${age>=20}">
			나이는 20 이상<br/>	
		</c:when>
		<c:otherwise>
			이름은 개똥이가 아니고 나이가 20 이상도 아님
		</c:otherwise>
	</c:choose>
</body>
</html>