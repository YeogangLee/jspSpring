<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>마이페이지</title>
</head>
<body>
<!-- 
* redirect 방식
 => 가장 큰 특징 : URL이 변경된다
 - 데이터가 전달 불가능
 - 파라미터만 전달 가능
 - 주로 컨트롤러의 다른 method로 이동할 경우 사용됨
 - 예) 게시판에서 글을 작성한 후 목록으로 이동
 
 * forward 방식
 - url 고정, 데이터 전달 가능
 - 주로 컨트롤러의 method에서 처리한 내용들을 jsp page로 전달할 경우 사용

 -->
	<h2>id : ${id}</h2>
	<h2>name : ${name}</h2>
	<h2>age : ${age}</h2>
	
</body>
</html>