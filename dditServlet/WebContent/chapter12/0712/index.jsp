<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>HELLO JSTL</title>
</head>
<body>
	1. https://mvnrepository.com 접속<br/>
	2. "JSTL" 검색<br/>
	3. 아래 표의 Version 중에서 1.2 클릭<br/>
	4. Files의 jar 클릭<br/>
	5. WebContent > WEB-INF > lib에 jar파일 드래그 또는 복사 붙이기<br/>
	6. taglib 디렉티브 추가<br/>
	<pre>
	1) 코어 : c (http://java.sun.com/jsp/jstl/core)
			변수지원, 흐름 제어, URL 처리 (코어를 가장 많이 쓴다.)
	2) XML : x (http://java.sun.com/jsp/jstl/core)
			XML 코어, 흐름 제어, XML 변환
	3) 국제화 : fmt (http://java.sun.com/jsp/jstl/core)
			  메시지 형식, 숫자 및 날짜 형식
	4) 데이터베이스 : sql (http://java.sun.com/jsp/jstl/core)
				  SQL 처리
	5) 함수 : fn (http://java.sun.com/jsp/jstl/core)
			Collection 처리, String 처리
	</pre>
	<c:out value="Hello JSTL!"/>
</body>
</html>