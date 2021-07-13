<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>redirect태그 사용하기</title>
</head>
<body>
<!-- 
	* c:redirect 태그
	response.sendRedirect()처럼 지정한 페이지로 리다이렉트 시켜줌
 -->
 	<c:if test="${param.dist=='1'}">
 		<c:redirect url="/chapter07/0705/layout.jsp" />
 	</c:if>
 	<c:if test="${param.dist=='2'}">
 		<c:redirect url="/chapter07/0705/info.jsp" />
 	</c:if>
 	<!-- /chapter07/0706/freeboard.jsp?currentPage=1 -->
 	<c:if test="${param.dist=='3'}">
 		<c:redirect url="/chapter07/0706/freeboard.jsp">
			<c:param name="currentPage" value="1" /> 		
 		</c:redirect>
 	</c:if>
 	<c:if test="${param.dist=='4'}">
 		<c:redirect url="http://search.daum.net/search">
			<c:param name="w" value="blog" /> 		
			<c:param name="q" value="봄 라일락" /> 		
 		</c:redirect>
 	</c:if>
</body>
</html>