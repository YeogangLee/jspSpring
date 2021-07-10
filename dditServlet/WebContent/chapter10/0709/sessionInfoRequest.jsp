<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	//request.getSession() : 현재 요청과 관련된 session 객체를 리턴함
	HttpSession httpSession = request.getSession();
	// java.lang.String cannot be cast to java.util.List
	// 아래 주석처러 하면 안 되고 list2처럼 해줘야 한다.
// 	httpSession.setAttribute("list", "사과");
// 	httpSession.setAttribute("list", "바나나");
// 	httpSession.setAttribute("list", "오렌지");

// 	쿼리를 들고올 때 여러 개를 리스트로 많이 담아오니까 그럴 때 이용하면 좋다.
	
	List list2 = new ArrayList();
	list2.add("사과");
	list2.add("배");
	list2.add("오렌지");
	
	httpSession.setAttribute("list2", list2);
	
	List list = null;
	
	if(httpSession!=null){
		list = (List)httpSession.getAttribute("list2");
		
		for(int i=0; i<list.size(); i++) {
			out.print(list.get(i));
		}
	}else {
		list = Collections.emptyList();
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
	<title>request객체를 이용한 세션 관리</title>
</head>
<body>
	
</body>
</html>