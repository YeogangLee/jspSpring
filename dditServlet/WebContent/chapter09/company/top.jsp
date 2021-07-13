<%@page import="util.Cookies"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
Cookies cookies = new Cookies(request);

%>
<table border="0" style="width:100%;" cellspacing="0">
<tr style="height:50px;">
	<td style="width:10%;">
		<a href="/chapter09/company/main.jsp">
			<img src="/chapter09/company/logo.jpg" style="border:0px;cursor:pointer;width:70px;" />
		</a>
	</td>
	<td>
		<div style="width:100%;">
			<div style="width:20%;float:left;">
			<a href="/chapter09/company/main.jsp" style="text-decoration:none;color:black;">Home</a>
			</div>
			<div style="width:20%;float:left;">
			<a href="/chapter09/company/info.jsp" style="text-decoration:none;color:black;">회사정보</a>
			</div>
			<div></div>
		</div>
	</td>
	<td style="width:30%;text-align:right;">
<%
	if(cookies.exists("id")){
		out.print(cookies.getValue("id") + "님 로그인되었습니다.&emsp;");
		out.print("로그아웃");
	}else {		
%>
		<input type="button" value="로그인" onclick="fn_login()" style="margin-right:10px;" />
<%
	}
%>
	</td>
</tr>
</table>
<script type="text/javascript">
function fn_login(){
	location.href="/chapter09/company/login.jsp";
}
</script>








