<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>요청 페이지 없음</title>
</head>
<body>
	<!-- 에러 이미지 -->
	<img src="../../chapter06/404error.png" alt="404에러" style="height: 400px">
	<br><br>
	404에러 발생
	<!-- 
		200 : 요청을 정상적으로 처리함
		307 : 임시로 페이지를 리다이렉트 함.
		400 : 클라이언트의 요청이 잘못됨
		401 : 접근 허용 안 함
		404 : 요청한 URL 경로 없음
		405 : 요청 메서드(GET, POST 등)를 허용하지 않음 
		500 : JSP Exception 발생
		503 : 서버가 일시적으로 서비스 제공 못함(서버 부하, 임시 보수 중)
	 -->
</body>
</html>