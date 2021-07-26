<%@page import="chapter15.service.MessageListView"%>
<%@page import="chapter15.vo.Message"%>
<%@page import="java.util.List"%>
<%@page import="chapter15.service.GetMessageListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	GetMessageListService massageListService = GetMessageListService.getInstance();
	//페이징 객체 사용 전
// 	List<Message> list = massageListService.getMessageList();
	
	//페이징 객체 사용 후 : 페이징 처리 전용 클래스 
	MessageListView list = massageListService.getMessageList(1);
%>
<c:set var="list" value="<%=list %>" /> <!-- 영역 지정을 안 했기 때문에 scope는 page이다. -->
<!DOCTYPE html>
<html>
<head>
	<title>방명록 목록</title>
</head>
<body>
	<form method="post" action="writeMessage.jsp">
		이름 : <input type="text" name="guestName" /><br/>
		비밀번호 : <input type="password" name="password" /><br/>
		메시지 : <textarea rows="3" cols="30" name="message"></textarea><br/>
		<input type="submit" value="메시지 남기기" />
	</form>
	<hr/>
	<table border="1" style="width: 100%;">
	<!-- 페이징 처리 전에는 ${list} 사용 -->
	<c:forEach var="message" items="${list.messageList}" >
		<tr>
			<td>
				메시지 번호 : ${message.messageId}<br/>
				손님 이름 : ${message.guestName}<br/>
				메시지 : ${message.message}<br/>
				<a href="./confirmDeletion.jsp?messageId=${message.messageId}">삭제하기</a>
			</td>
		</tr>
	</c:forEach>
	</table>
	<c:forEach var="pageNum" begin="1" end="${list.pageTotalCount}" >
		<a href="list.jsp?page=${pageNum}">[${pageNum}]</a>
	</c:forEach>
</body>
</html>