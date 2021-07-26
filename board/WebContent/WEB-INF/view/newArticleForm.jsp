<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="auth.vo.User"%>
<%
User user = (User)session.getAttribute("authUser");
%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 쓰기</title>
<script type="text/javascript">
	function fn_owin(){
	   // 경로, 새창의 이름, 옵션
	   window.open("/login.do","owin","width=660, height=500");   
	}
</script>
</head>
<body>
	<c:set var="user" value="<%=user%>"></c:set>
	<a href="javascript:fn_owin();">[로그인]</a><br />
	<form method="post" action="/article/write.do">
		<input type="hidden" name="writerId" value="${user.memberid}" />
		<p>
		제목 : <br /><input type="text" name="title" value="" />
		</p>
		<p>
		작성자 : <br /><input type="text" name="writerName" value="${user.name}" readonly="readonly"/>
		</p>
		<p>
		내용 : <br /><textarea name="content" rows="5" cols="30"></textarea>
		</p>
		<input type="submit" value="새글등록" />
	</form>
</body>
</html>