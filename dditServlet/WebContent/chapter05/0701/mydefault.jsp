<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>JSP 9가지 주요 기본 객체</title>
</head>
<body style="margin-left: 50px">
	<h2>JSP 9가지 주요 기본 객체</h2>                                                                             
	1. request //인터페이스 *******                                                                               <br>
	- (실제타입)HttpServletRequest, ServletRequest(실제 구현한 타입, 위의 스크립트릿에 적힌 request에 마우스 대면 확인 가능) <br>
	- 클라이언트의 요청 정보를 저장                                                                               <br>
	- <%request.setCharacterEncoding("UTF-8"); %>                                                                 <br>
																												  <br>
	2. response ***                                                                                               <br>
	- (실제타입) HttpServletResponse, ServletResponse                                                             <br>
	- 서버로부터 응답 정보를 저장                                                                                 <br>
	- <%response.getBufferSize(); %>                                                                              <br>
	                                                                                                              <br>
	3. session *******                                                                                            <br>
	- (실제타입) HttpSession                                                                                      <br>
	- HTTP 세션 정보를 저장                                                                                       <br>
	- <%session.setAttribute("id", "a001"); %>                                                                    <br>
	                                                                                                              <br>
	4. page *                                                                                                     <br>
	- (실제타입) Object                                                                                           <br>
	- JSP 페이지를 구현한 자바 클래스 인스턴스                                                                    <br>
	<!-- 자꾸 에러 발생 -->
	- <%page.wait(10); %>                                                                                  
	                                                                                                              <br>
	5. out ***                                                                                                    <br>
	- //여러분들이 많이 쓸 것 ex)out.print                                                                        <br>
	- (실제타입) JspWriter                                                                                        <br>
	- JSP 페이지가 생성하는 결과를 출력                                                                           <br>
	- <%out.print("개똥이 <br>"); %>                                                                              <br>
	                                                                                                              <br>
	6. pageContext *****                                                                                          <br>
	- JSP 페이지에 대한 정보 저장                                                                                 <br>
	                                                                                                              <br>
	7. application ***                                                                                            <br>
	- (실제타입) ServletContext                                                                                   <br>
	- 웹 어플리케이션에 대한 정보를 저장(웹 브라우저 간에도 호환)                                                 <br>
	- 싱글 사인온 Single SignOn(부모 사이트에 한번 로그인하면, 자식 사이트에도 자동으로 로그인되고, 그게 계속 유지<br>
	- 단점: 세션은 시간이 지나면 사라지지만, 어플리케이션은 직접 끊어줘야 한다.                                   <br>
	- <%application.setAttribute("name", "개똥이"); %>                                                            <br>
	                                                                                                              <br>
	8. config *                                                                                                   <br>
	- JSP 설정 정보                                                                                               <br>
		                                                                                                          <br>
	9. exception ****                                                                                             <br>
	- (실제타입) Throwable                                                                                        <br>
	- 예외 객체, 오류 페이지에서만 사용됨                                                                         <br>
	
</body>
</html>