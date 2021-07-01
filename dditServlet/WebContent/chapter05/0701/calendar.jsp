<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String dayStr = request.getParameter("day") == null ? "1" : request.getParameter("day");
	int dayParam = Integer.parseInt(dayStr);
%>
<!DOCTYPE html>
<html>
<head>
	<title>달력</title>
	<script type="text/javascript">
		function fn_chg_param(ele){
			var varDay = ele.value;
			
			location.href = "/chapter05/0701/calendar.jsp?day=" + varDay;
		}
		
		function fn_chg(ele){
			var varDay = ele.value;
			
			for(var i=0; i<35; i++) {
				document.getElementsByTagName("td")[i].style.backgroundColor = "";
			}
			
			document.getElementsByTagName("td")[varDay-1].style.backgroundColor = "orange";			
		}
	</script>
</head>
<body>
	<!-- 1일 ~ 31일까지 채우기, 선택된 날짜를 ?dd=1 => 달력의 해당 날짜에 배경색 넣기 -->
	<select id="sel" name="sel" onchange="fn_chg_param(this)">
		<%for(int o=1; o<=31; o++) { %>
		<option value="<%=o %>" <%if(o==dayParam) { %> selected<%} %>>
			<%=o %>일
		</option>
		<%} %>
	</select>
	<select id="sel" name="sel" onchange="fn_chg(this)">
		<%for(int o=1; o<=31; o++) { %>
		<option value="<%=o %>"><%=o %>일</option>
		<%} %>
	</select>
	<table border="1" style="width: 50%;">
		<tr>
			<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
		</tr>
		<tr>
<%-- 			<% --%>
<!-- 			for(int i=1; i<=31; i++) {  -->
<!-- 				out.print("<td>" + i + "</td>"); -->
<!-- 				if(i%7==0) { -->
<!-- 					out.print("</tr><tr>"); -->
<!-- 				} -->
<%-- 			} %> --%>
			<%
			int blank = 7;
			for(int i=1; i<=31; i++) { 
				out.print("<td>" + i + "</td>");
				blank--;
				if(i%7==0) {
					out.print("</tr><tr>");
					blank = 7;
				}
			}
			//blank는 7이 주어지고, 행이 끝나면 다시 7로 초기화
			//날짜를 출력할 때마다 1씩 감소, 31일 출력하면 4가 남게됨
			//29-6  30-5  31-4
			for(int j=0; j<blank; j++) {
				out.print("<td>&nbsp;"+(j+1)+"</td>");				
			}
			%>
		</tr>
	</table>
<script type="text/javascript">
	for(var i=0; i<35; i++) {
		document.getElementsByTagName("td")[i].style.backgroundColor = "";
	}
	
	document.getElementsByTagName("td")[<%=dayParam%>-1].style.backgroundColor = "yellow";
</script>
</body>
</html>