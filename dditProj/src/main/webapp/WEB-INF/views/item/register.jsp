<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<!-- 
	* 뷰에서 컨트롤러를 비동기식(asynch)으로 호출
	: AJAX(Asynchronous JavaScript And XML, 비동기적인 자바스크립트와 XML)
	동기 - 3핸드셰이크, 받았으면 받았다고, 못받았으면 못받았다
	단점 : 던지고 나서 응답을 기다려요, 무한대기 될 수 있다 (해킹 취약한 부분)
	비동기는 던지고 신경 안 써요.
	* ajax
	- 빠르고, 동적인 웹 페이지 구현
	- 페이지 전체가 아닌 일부분을 갱신할 때 사용
	   동기식(Synchronized) : A작업이 종료된 후 B작업 시작 (single thread)
	   비동기식(Asynchronized) : A작업과 B작업을 동시에 실행 가능 (multi thread) 
	- 
 -->
	<title>Item</title>
	<script src="/resources/js/jquery.min.js"></script>
	<script type="text/javascript">
		$(function(){
			//<form id="item" method="post" ...
			var formObj = $("#fmItem");
			//button 클릭 시 처리
			$("#btnRegister").on("click", function(){
				formObj.attr("action", "/item/register");
				formObj.attr("method", "post");
				formObj.submit();
			});
		});
	</script>
</head>
<body>
	<h2>Item 등록</h2>
	<form id="fmItem" action="/item/register" enctype="multipart/form-data" method="post">
		<table>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="itemName" /></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" name="price" /></td>
			</tr>
			<tr>
				<th>파일</th>
				<td><input type="file" name="picture" /></td>
			</tr>
			<tr>
				<th>개요</th>
				<td><textarea rows="5" cols="30" name="description"></textarea></td>
			</tr>
		</table>
		<div>
			<button id="btnRegister">등록</button>
		</div>
	</form>
</body>
</html>