<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<title>도서 상세</title>
</head>
<body>
	<!-- 컨트롤러에서 전달받은 데이터를 보여주기
		 달러기호($){데이터이름} //$를 {}앞에 그냥 쓰니까 에러 
	 -->
	<h1>도서 상세</h1>
	<p>도서번호 : ${bookId}       </p>
	<p>제목 : ${data.TITLE}   </p>
	<p>카테고리 : ${data.CATEGORY}</p>
	<!-- maxFractionDigits : 천 단위 쉼표 구분자 -->
	<p>가격 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${data.PRICE}" /></p>
	
	<p><a href="/update?bookId=${bookId}">수정</a></p><br/>
	
	<form method="post" action="/delete">
		<input type="hidden" name="bookId" value="${bookId}" /> 
		<input type="submit" value="삭제" /> 
	</form>
	<p><a href="/list">목록으로</a></p><br/>
	<p>${bookId}</p><br/>
	<!-- bookId는 왜 data. 사용안하지?
		 => 북컨트롤러 detail() 메서드에서 int형으로 바로 꽂아줬다. 나머지는 쿼리 갔다가 바로 hashMap에 담겨온다. 
		 -> mav.addObject("bookId", bookId);
	-->
</body>
</html>