<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>main</title>
</head>
<body>
	main.jsp에서 생성한 내용.<br />
	<!-- 
		jsp:include 액션태그
		- page : 포함할 jsp 페이지의 경로를 지정
		- flush : 지정한 jsp 페이지를 실행하기 전에 버퍼를 플러시할지 여부 지정, 기본값 false
	 -->
	<jsp:include page="sub.jsp"></jsp:include>
	
	include 이후의 내용<br />
	
	
	<!-- 
		jsp:include 액션태그 처리 순서
		1. main.jsp가 웹 브라우저의 요청을 받음
		2. "main.jsp"에서 생성한 내용.<br/>"를 버퍼에 저장
		3. 요청 흐름이 sub.jsp로 이동
		4. "<p> sub.xml에서 생성한 내용<br />"을 버퍼에 저장
		5. 다시 main.jsp로 요청 흐름이 돌아옴
		6. "include 이후의 내용 <br />"를 버퍼에 저장
		7. 출력 버퍼의 내용을 응답 데이터로 전송
	 -->
</body>
</html>