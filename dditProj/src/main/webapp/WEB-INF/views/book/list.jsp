<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<title>도서 목록</title>
</head>
<body>
	<h1>도서 목록</h1>
	<!-- 
		form 태그의 기본 HTTP 메소드는 GET.
		검색 버튼 클릭 : /list?keyword=검색어
	 -->
	<form>
		<!-- 
			name속성의 keyword : 컨트롤러로 데이터 보내기
			value속성의 ${keyword} : 컨트롤러에서 데이터 받아오기
		 -->
		<input type="text" name="keyword" placeholder="검색" value="${keyword}" />
		<input type="submit" value="검색" /> 
	</form>
	<br/>
	
	<!-- List<Map<String, Object>> list : data로 담음 -->
	<table border="1" style="width: 100%" cellspacing="0">
		<thead>
			<tr>
				<th>제목</th>
				<th>카테고리</th>
				<th>가격</th>
			</tr>
		</thead>
		<tbody>
		<!-- JSTL에서 반복문 사용, c: 태그를 사용하기 위해서 우리는 taglib(태그 라이브러리) 디렉티브 사용 -->
		<!-- var : 목록의 한 행(row, 튜플, 레코드)을 나타내는 변수명을 넣으면 됨 -->
		<c:forEach var="row" items="${data}">
<!-- 			<tr> -->
<!-- 				<td>톰소여의모험</td> --!>
<!-- 				<td>소설</td> --!>
<!-- 				<td>10000</td> --!>
<!-- 			</tr> -->
			<tr>
			
				<!-- row.map의 키명 -->
				<!-- BOOKID 전부 대문자 -->
				<td><a href="/detail/${row.BOOKID}">${row.TITLE}</a></td>
				<td>${row.CATEGORY}</td>
				<td><fmt:formatNumber type="currency" maxFractionDigits="3" value="${row.PRICE}"/>원</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<p><a href="/create">생성</a></p>
	<h2>요청 처리</h2>
	<h5>스프링 MVC는 요청 데이터를 가져올 수 있는 다양한 방법을 제공한다.</h5>
	<a href="/register?userId=a001&amp;password=1234">/register?userId=a001&amp;password=1234</a>
	<br/>
	<a href="/register/a001">/register/a001</a>
	<br/>
	<form method="post" action="/register02">
		userId : <input type="text" name="userId" value="" /><br/>
		password : <input type="password" name="password" value="" /><br/>
		Coin : <input type="text" name="coin" value="" />
		<input type="submit" value="전송" />
		<br/><br/><br/><br/><br/><br/><br/>
	</form>
</body>
</html>