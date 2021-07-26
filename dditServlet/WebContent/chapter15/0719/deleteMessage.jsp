<%@page import="chapter15.service.InvalidPasswordException"%>
<%@page import="chapter15.service.DeleteMessageService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int messageId = Integer.parseInt(request.getParameter("messageId"));
	String password = request.getParameter("password");
	
	out.print("messageId : " + messageId);
	out.print("password : " + password);
	
	//false : 오류없음
	boolean invalidPassword = false;
	
	try {
		DeleteMessageService deleteService = DeleteMessageService.getInstance();
		deleteService.deleteMessage(messageId, password);
		
	}catch(InvalidPasswordException ex) {
		//true : 오류 발생
		invalidPassword = true;
	}
%>
<!DOCTYPE html>
<html>
<head>
	<title>방명록 메시지 삭제 완료</title>
</head>
<body>
<%
	//invalidPassword : false면 정상
	if(!invalidPassword) {
		out.print("메시지를 삭제하였습니다.");
	}else {
		//invalidPassword : true면 오류 발생 (특히 비밀번호가 매칭되지 않은 오류 발생)
		out.print("입력한 비밀번호가 올바르지 않습니다.");
	}
%>
<br/>
<a href="list.jsp">[목록 보기]</a>
</body>
</html>