<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>새로운 글 작성</title>
	<script type="text/javascript">
		function fn_list() {
			history.go(-1);			
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
						제목 : <br /><input type="text" name="title" placeholder="제목을 입력해주세요." />
					</p>
					<p>
						내용 : <br />
						<textarea name="content" rows="5" cols="30"></textarea>
					</p>
					<input type="submit" value="새글등록" />
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