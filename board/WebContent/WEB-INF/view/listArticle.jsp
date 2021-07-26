<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>게시글 목록</title>
</head>
<body>
	<table border="1" style="width:100%;">
		<tr>
			<th colspan="4"><a href="/article/write.do">[게시글 쓰기]</a></th>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="article" items="${articlePage}" varStatus="stat">
		<tr>
			<td>${stat.count}</td>
			<td>${article.title}</td>
			<td>${article.writer.writerName}</td>
			<td>${article.readCnt}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>