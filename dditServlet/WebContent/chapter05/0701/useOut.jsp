<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String danStr = request.getParameter("dan") == null ? "2" : request.getParameter("dan");
	int danParam = Integer.parseInt(danStr);
%>
<!DOCTYPE html>
<html>
<head>
	<title>구구단 출력</title>
	<script type="text/javascript">
		function fn_chg(getObj) {
// 			alert(getObj.value);			
			
			//select요소의 선택된 option요소의 value값을 varDan 변수에 대입
			var varDan = getObj.value;
			
			//get방식으로 파라미터 넘겨주기
			location.href = "/chapter05/0701/useOut.jsp?dan=" + varDan;
			
		}
	</script>
</head>
<body>
	<select id="sel" name="sel" onchange="fn_chg(this)">
	<%for(int dan=2; dan<10; dan++) { %>
		<option value="<%=dan %>" <%if(dan==danParam) { %> selected<%} %>>
		<!-- 동일한 결과 (selected) -->
<%-- 		<option value="<%=dan %>" <%if(dan==danParam) { out.print("selected");}%>> --%>
<%-- 		<option value="<%=dan %>" <%if(dan==danParam) { out.print("selected='selected'");}%>> --%>
			<%=dan %>단
		</option>
	<%} %>
	</select>
	<table border="1" style="width: 30%">
	<%for(int i=1; i<10; i++) { %>
		<tr>
			<td><%out.print(danParam + " x "+ i + " = " + danParam*i); %></td>
		</tr>
	<%} %>
	</table>
</body>
</html>