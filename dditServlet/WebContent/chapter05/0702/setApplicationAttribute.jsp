<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	//1. 어트리뷰트 값을 직접 지정하는 방법
// 	String name = "a001";
// 	String value = "김은대";
	
// 	//name : a001, value : 김은대	
// 	application.setAttribute(name, value);
	
	//2. 어트리뷰트 값을 파라미터로 받는 방법
	//...jsp?name=b001&value=이쁜이
	String name = request.getParameter("name");		//b001
	String value = request.getParameter("value");	//이쁜이
	application.setAttribute(name, value);
	
	//여기까지 코드 작성하고 브라우저에서 확인하고 싶을 때
	//해당 파일을 바로 부르면 안되고, .jsp뒤에 ?로 파라미터를 같이 붙여줘야
	//해당 페이지와 파라미터가 브라우저에 출력된다.
	//=>
	//http://localhost:8090/dditServlet/chapter05/0702/setApplicationAttribute.jsp
	//?name=b001&value=%EC%9D%B4%EC%81%9C%EC%9D%B4
	
	//버튼을 클릭해서 출력해보면, 위에서 처음 실행하고 주석처리했던 a001도 파라미터로 받은 b001이랑 같이 담겨있는 걸 볼 수 있다.
	
	
%>
<!DOCTYPE html>
<html>
<head>
	<title>application 속성 지정</title>
</head>
<body>
	application 기본 객체의 속성 설정 :<br>
	<%=name %> : <%=value %>
	<br /><br />
	<input type="button" id="btn" value="application의 attribute 보기"
		onclick="javascript:location.href='viewApplicationAttribute.jsp';">
</body>
</html>