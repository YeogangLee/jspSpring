<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>상품분류 정보 입력</title>
</head>
<body>
	<form name="frm" action="/chapter08/0707/lprodJoining.jsp" method="post">
		<table border="1" style="width: 50%">
			<tr>
				<th>상품분류 아이디</th>
				<td>
					<input type="text" name="lprodId" placeholder="상품분류 아이디를 입력해주세요" />
				</td>
			</tr>
			<tr>
				<th>상품분류 코드</th>
				<td>
					<input type="text" name="lprodGu" placeholder="상품분류 코드를 입력해주세요" />
				</td>
			</tr>
			<tr>
				<th>상품분류명</th>
				<td>
					<input type="text" name="lprodNm" placeholder="상품분류명을 입력해주세요" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="상품 추가" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>