<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>자유게시판 목록</title>
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
				<table border="1" style="width: 100%">
					<tr>
						<td colspan="4"><a href="/chapter07/0706/freeboardwrite.jsp">[게시글 쓰기]</a></td>
					</tr>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
					</tr>
					<% 
					int i=0;
					
					String currentPageStr = request.getParameter("currentPage");
					int currentPage = Integer.parseInt(currentPageStr);
					
					//글 전체 개수(123행)
					//SELECT COUNT(*) TOTAL FROM FREEBOARD; => 123행
					int total = 123;
					
					//전체 페이지 수
					int totalPages = total / 10;
					
					//123 % 10 -> 나머지 글 3 => 나머지 글 출력을 위해, 전체 페이지 수 1 증가
					if(total % 10 > 0) {
						totalPages++;
					}
					
					//글 시작 번호
					int startNum = (currentPage * 10) - 9;
					
					//글 종료 번호
					int endNum = currentPage * 10;
					
					//시작페이지 : [이전] 1 2 3 4 5 [다음] => 1
					int startPage = currentPage / 5 * 5 + 1; //currentPage가 7이면 /5하면  -> 1.4 => 정수형이라 1만 담긴다

					//현재 페이지를 5개로 나눈 나머지
					int modVal = currentPage % 5;
					
					//현재 페이지가 종료페이지 일 때(modVal == 0) 보정작업 필요
					if(modVal == 0) startPage -= 5; //보정 작업
										
					//종료페이지 : [이전] 1 2 3 4 5 [다음] => 5
					int endPage = startPage + 4;
					
					//종료페이지 : [이전] 11 12 13 14 15 [다음] => 15 일 때,
					//글 전체 개수(total) 이상의 불필요한 페이지를 출력하지 않는 조건 (14, 15 페이지 버튼 출력X)
					if(endPage > totalPages) endPage = totalPages;
					
					for(i=startNum; i<=endNum; i++) {
					%>
					<tr>
						<td><%=i %></td>
						<td>제목<%=i %></td>
						<td>작성자<%=i %></td>
						<td>0</td>
					</tr>
					<% } %>
					<tr style="text-align: center">
						<td colspan="4">
						<%if(startPage > 5) {%>
							<a href="/chapter07/0706/freeboard.jsp?currentPage=<%=startPage-5%>">
								[이전]
							</a>
						<%} %>
							<%for(int j=startPage; j<=endPage; j++) {%>
								<!-- out.print((j+1) + "&nbsp;"); -->
								<a href='/chapter07/0706/freeboard.jsp?currentPage=<%=j %>'>
									<%=j %>
								</a>
							<%} %>
						<%//endPage와 totalPages가 동일하다면 [다음]버튼이 필요없다. 다음으로 갈 페이지들이 없기 때문 %>
						<%if(endPage < totalPages) {%>
							<a href="/chapter07/0706/freeboard.jsp?currentPage=<%=startPage+5%>">
								[다음]
							</a>
						<%} %>
						</td>
					</tr>
				</table>
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