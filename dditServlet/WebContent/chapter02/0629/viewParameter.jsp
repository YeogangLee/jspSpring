<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//form.jsp에서 요청한 페이지

	//요청 파라미터의 캐릭터 인코딩을 UTF-8로 지정
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>요청 파라미터 출력</title>
</head>
<body>
	<!-- 이름, 주소의 값을 출력 -->
	name 파라미터 : <%=request.getParameter("name") %><br>
	address 파라미터 : <%=request.getParameter("address") %><br>
	
	<!-- getParameter()는 값을 1건만 받을 수 있으므로, pet에서는 사용 불가  -->
	<%-- address 파라미터 : <%=request.getParameter("pet") %> --%>

<%
	//이름이 pet인 파라미터 값 목록을 배열(String[])로 리턴함.
	//체크박스를 아무것도 선택하지 않으면 웹 브라우저는 해당 이름의 파라미터를 전송하지 않음 *****
	String[] values = request.getParameterValues("pet");

	if(values!=null) {//이 조건이 없을시 500에러 발생, 아무것도 선택하지 않았을시 values에 null값이 들어오기 때문 
		out.println("좋아하는 동물 : ");
		for(int i=0; i<values.length; i++) { //values.length : 체크된 개수
			out.println(values[i]);
		}
	}
%>
<!-- 아무것도 체크 안했을 시, 500 에러 발생 -->
<!-- 이유 : 체크박스(input태그 checkbox타입)를 아무것도 선택하지 않으면, 웹 브라우저는 해당 이름의 파라미터를 전송하지 않음 ***** 
	             하지만 input태그 text타입은 값을 입력하지 않더라도 빈 문자열로 값을 전송한다.
-->
<%-- values의 크기 : <%=values.length %> --%>

<p />
<%
	//파라미터의 이름을 출력해주는 기본 형태 (Elements를 다룸)
	Enumeration paramEnum = request.getParameterNames();
	out.println("파라미터의 이름 : ");
	while(paramEnum.hasMoreElements()) {
		String name = (String)paramEnum.nextElement();
		out.println(name+" ");
	}
%>

<p />
<%
	//파라미터 이름과 파라미터 값을 리턴.
	//이 맵에는 <파라미터 이름, 파라미터 값 배열>이 쌍을 이루고 있음
	Map parameterMap = request.getParameterMap();
	
	//name, address들은 1개밖에 없어서, nameParam[1] 이상 사용시 에러 발생 
// 	String[] nameParam = (String[])parameterMap.get("name");
	String[] nameParam = (String[])parameterMap.get("address");
// 	String[] nameParam = (String[])parameterMap.get("pet");

	if(nameParam!=null) {
		out.print("name = " + nameParam[0] + "<br>"); //name, address
// 		out.print("name = " + nameParam[1] + "<br>"); //pet
// 		out.print("name = " + nameParam[1] + "<br>"); //pet
// 		out.print("name = " + nameParam[1] + "<br>"); //pet
	}
	
/*
	리퀘스트의 파라미터와 관련한 4가지 메서드
	여러개일때는 values를 사용하고...
	해당 소스 코드 보면서 정리하면 된다.
	
	숙제: 제이쿼리로 계산기 만들기
*/
%>

</body>
</html>