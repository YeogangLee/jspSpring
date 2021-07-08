<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
	//읽어올 파라미터의 캐릭터 인코딩을 UTF-8로 지정
	request.setCharacterEncoding("UTF-8");
%>
<!-- MemberInfo 자바빈 클래스 객체 생성, memberInfo 이름으로 저장 -->
<jsp:useBean id="memberInfo" class="chap08.member.MemberInfo" />
<!-- 지금 이 시점에서 세팅 -->
<jsp:setProperty name="memberInfo" property="*" />
<%
	//얘보다 위에 태그 형식이 더 간단하다.
// 	memberInfo.setId(request.getParameter("id"));
// 	memberInfo.setId(request.getParameter("name"));
// 	memberInfo.setId(request.getParameter("email"));
%>
<!DOCTYPE html>
<html>
<head>
	<title>회원 가입</title>
</head>
<body>
<!-- 
	[회원가입] 버튼을 누르면 파라미터로 전송된 데이터가 자바빈 객체의 프로퍼티 값으로 지정됨.
	**jsp:setProperty 액션 태그를 사용하려면 파라미터 이름과 자바빈 프로퍼티의 이름을 동일하게 넣는다.
 -->
	<form action="/chapter08/0707/processJoining.jsp" method="post">
		<table border="1" style="width: 50%">
			<tr>
				<th>아이디</th>
				<td colspan="3">
					<%
						out.print(memberInfo.getId());
					%>
					<jsp:getProperty name="memberInfo" property="id" />
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<%
						out.print(memberInfo.getName());
					%>				
					<jsp:getProperty name="memberInfo" property="name" />
				</td>
				<th>이메일</th>
				<td>
				<td>
					<%
						out.print(memberInfo.getEmail());
					%>			
					<jsp:getProperty name="memberInfo" property="email" />					
				</td>
			</tr>
		</table>
	</form>
</body>
</html>