<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Insert title here</title>
</head>
<body>
	<!-- 
		var 속성의 n : 1부터 10까지 차례대로 값을 가짐
		begin ~ end 속성 : 범위 지정
		step 속성 : 증감값, 1인 경우 생략 가능
	 -->
	<h2>1부터 10까지 출력</h2>
	<c:forEach var="n" begin="1" end="10" step="1">
		${n}&emsp;
	</c:forEach>
	<br/>
	
	<h2>1부터 100까지 출력</h2>
	<c:forEach var="n" begin="1" end="100" step="5">
		${n}&emsp;
	</c:forEach>
	<br/>
	
	<h2>자바 Collection</h2>
<%
	List<String> list = new ArrayList<String>();
	String[] arr = {"사과", "귤", "바나나", "배", "오렌지", "키위"};
	for(String str:arr) {
		list.add(str);
	}
	
	//request 객체에 담아보기(영역)
	request.setAttribute("fruitList", list);
%>
<!-- varStatus
index : 인덱스 정보
count : 반복 횟수
begin : begin의 속성값
end : end의 속성값
step : 증감값
first : 첫 번째 실행시 true
last : 마지막 실행시 true
current : collection 중 사용할 객체  -->

	<!-- 영역에 담겨있기 때문에 EL 이용 : ${}  -->
	<c:forEach var="fruitStr" begin="2" end="" items="${fruitList}" varStatus="stat">
		${stat.count} ${stat.index} => ${fruitStr}<br/>	
	</c:forEach>

</body>
</html>