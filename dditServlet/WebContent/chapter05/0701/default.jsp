<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>JSP 9가지 주요 기본 객체</title>
</head>
<body>
	<h2>JSP 9가지 주요 기본 객체</h2>
	1. request //인터페이스 *******
	- (실제타입)HttpServletRequest, ServletRequest(실제 구현한 타입, 위의 스크립트릿에 적힌 request에 마우스 대면 확인 가능)
	- 클라이언트의 요청 정보를 저장
	- <%request.setCharacterEncoding("UTF-8"); %>
	2. response ***
	- (실제타입) HttpServletResponse, ServletResponse
	- 서버로부터 응답 정보를 저장
	- <%response.getBufferSize(); %>
	
	3. session *******
	- (실제타입) HttpSession
	- HTTP 세션 정보를 저장
	- <%session.setAttribute("id", "a001"); %>
	
	4. page *
	- (실제타입) Object
	- JSP 페이지를 구현한 자바 클래스 인스턴스
	- <%page.wait(); %>
	
	5. out ***
	- //여러분들이 많이 쓸 것 ex)out.print 
	- (실제타입) JspWriter
	- JSP 페이지가 생성하는 결과를 출력
	- <%out.print("개똥이 <br>"); %>
	
	6. pageContext *****
	- JSP 페이지에 대한 정보 저장
	
	7. application ***
	- (실제타입) ServletContext
	- 웹 어플리케이션에 대한 정보를 저장(웹 브라우저 간에도 호환)
	- 싱글 사인온 Single SignOn(부모 사이트에 한번 로그인하면, 자식 사이트에도 자동으로 로그인되고, 그게 계속 유지되는 것)
	- 단점: 세션은 시간이 지나면 사라지지만, 어플리케이션은 직접 끊어줘야 한다.
	- <%application.setAttribute("name", "개똥이"); %>
	
	8. config *
	- JSP 설정 정보
		
	9. exception ****
	- (실제타입) Throwable
	- 예외 객체, 오류 페이지에서만 사용됨
	
</body>
</html>