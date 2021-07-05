<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>jsp에서 에러 발생</title>
</head>
<body>
	<!-- 에러 이미지 -->
	<img src="../../error_0705/500error.jpg" alt="500에러" style="height: 400px">
	<br><br>
	500에러 발생
	<!-- 
		만약 에러 페이지의 길이가 512바이트보다 작으면,
		인터넷 익스플로러는 이 페이지가 출력하는 에러 페이지를 출력하지 않고
		자체적으로 제공하는 HTTP 오류 메시지 화면을 출력함.
		인터넷 익스플로러에서도 에러 페이지 내용을 올바르게 출력하려면
		응답 결과에 이 주석과 같은 내용을 포함해서 에러 데이터가 512바이트를 넘도록 해야 함.
		
		이 주석 없애고 익스플로러에서 열면, 익스플로러 자체 에러 페이지가 나온다.
	 -->

</body>
</html>