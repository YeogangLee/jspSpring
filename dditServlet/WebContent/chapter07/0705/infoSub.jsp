<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	//<jsp:param name="type" value="A" />
	String type = request.getParameter("type"); //type파라미터의 value값 A 받기
	if(type==null) {
		return;		
	}
	
	//type : input.jsp?type=B + jsp:param의 A
	String[] names = request.getParameterValues("type");
	for(String name : names) {
		out.print("name : " + name + "<br />");
	}
	//파라미터를 같이 받을 수 있다.
	
%>
<!-- info.jsp 페이지에 include되는 sub페이지 -->
<br />
<table border="1" style="width: 100%">
	<tr>
		<th>타입</th>
		<td><b><%=type %></b></td>
	</tr>
	<tr>
		<th>특징</th>
		<td>
			<%if(type.equals("A")) {
				out.print("강한 내구성");
				
			}else if(type.equals("B")) {
				out.print("뛰어난 대처 능력");
			}else {
				out.print("저렴한 가격");
			}
			%>
		</td>
	</tr>
</table>