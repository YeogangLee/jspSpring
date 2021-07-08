<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>회원가입 입력 폼</title>
</head>
<body>
	<form action="/chapter08/0707/processJoining.jsp" method="post">
		<table border="1" style="width: 50%">
			<tr>
				<th>아이디</th>
				<td colspan="3">
					<input type="text" name="id" size="10" placeholder="아이디를 입력해주세요." />
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text" name="name" size="10" placeholder="이름을 입력해주세요." />
				</td>
				<th>이메일</th>
				<td>
					<input type="text" name="email" size="10" placeholder="이메일을 입력해주세요." />
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center">
					<input type="submit" value="회원가입" />
				</td>
			</tr>
		</table>
		<!-- 
		[회원가입] 버튼을 누르면 입력한 데이터를 POST방식으로 processJoining.jsp에 전달함		
		 -->
	</form>
</body>
</html>