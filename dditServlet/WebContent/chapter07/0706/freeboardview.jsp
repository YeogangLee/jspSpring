<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	//freeboardreg.jsp 에서 보내준 map attr
	Map<String, Object> map = (Map<String, Object>)session.getAttribute("map");
%>
<!DOCTYPE html>
<html>
<head>
	<title>작성글 상세보기</title>
	<script type="text/javascript">
		function fn_list() {
// 			history.go(-1); //글을 등록할 때는 뒤로가기가 되지만, 상세보기에서는 1페이지로 이동하기
			location.href = "/chapter07/0706/freeboard.jsp?currentPage=1";
		}
	</script>
</head>
<body>
	<table border="1" style="width: 100%" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="2">
				<jsp:include page="../../module_0705/top.jsp" flush="false"></jsp:include>
			</td>
		</tr>
		<tr>
			<td style="width: 30%" valign="top">
				<jsp:include page="../../module_0705/left.jsp" flush="false"></jsp:include>
			</td>
			<td>
				<!-- 내용 부분 : 시작 -->
				<form action="/chapter07/0706/freeboardreg.jsp" method="post">
					<p>
						제목 : <br />
						<input type="text" name="title" placeholder="제목을 입력해주세요." 
							value = "<%=map.get("title") %>"
						/>
					</p>
					<p>
						내용 : <br />
						<textarea name="content" rows="5" cols="30">
							<%=map.get("content") %>
						</textarea>
					</p>
					<input type="button" value="목록" onclick="fn_list()"/>
				</form>
				<!-- 내용 부분 : 끝 -->
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<jsp:include page="../../module_0705/bottom.jsp" />
			</td>
		</tr>
	</table>
</body>
</html>