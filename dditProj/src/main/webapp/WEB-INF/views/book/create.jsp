<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>책 생성하기</title>
</head>
<body>
<!--
	View
	: 뷰는 화면을 담당. 웹 어플리케이션에서 화면은 웹 브라우저가 렌더링함.
	    서버에서 응답될 때 브라우저가 읽어서 해석할 수 있는 HTML로 최종 변환됨.
-->
	<h2>${message}</h2>
	<h1>책 생성하기</h1>
	<form method="post" action="">
		<p>제목 : <input type="text" name="title" /></p>
		<p>카테고리 : <input type="text" name="category" /></p>
		<p>가격 : <input type="text" name="price" /></p>
		<p><input type="submit" value="저장" /></p> 
	</form>
	
<!-- 
	* 파일업로드 폼 방식 요청 처리
	: 파일업로드 폼 파일 요소(태그)값을 스프링 MVC가 지원하는 MultipartFile 매개변수로 처리한다.
 -->
	<form method="post" enctype="multipart/form-data" action="/registerFile01">
		<input type="file" name="picture" /><br/> <!-- *****name속성값 -->
		<input type="submit" value="이미지 업로드" />
	</form>
	<br />
	<a href="/list">목록으로</a><br/>
	<a href="/gugu">구구단</a><br/>
	<a href="/point">성적 확인</a><br/>
	<a href="/move">redirect</a><br/>
<!-- 
	form 태그 안의 내용이 서버로 전송됨.
	name 속성을 가져야 함. name 속성은 키, value 속성은 값.
	* 키 :값 쌍을 HTTP 파라미터라고 부른다. 
 -->
</body>
</html>