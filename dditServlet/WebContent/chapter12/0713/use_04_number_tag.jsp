<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<title>numberFormat 태그 연습</title>
</head>
<body>
	<c:set var="price" value="10000" />
	<c:out value="${price}" /> <br/>
	<fmt:formatNumber value="${price}" type="number" var="numberType" />
	<br/>
	<c:out value="${numberType}" />
	<br/>
	<!-- 미국은 달러 기호가 앞에 붙어서, currency타입을 이용하면 '원'이 숫자 앞에 붙는다. -->
	통화 : <fmt:formatNumber value="${price}" type="currency" currencySymbol="원" /><br/>
	숫자 : ${numberType}<br/>
	패턴 : <fmt:formatNumber value="${price}" pattern="000000.00" /><br/>
	<br/>
	<fmt:parseNumber value="1,100.12" pattern="0,000.00" var="num" />
	숫자로 변환한 결과 : ${num} 		
	<br/>
</body>
</html>