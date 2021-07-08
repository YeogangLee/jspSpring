<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

String id = request.getParameter("id");
String password = request.getParameter("password");

out.print("id : " + id + ", password : " + password + "<br />");

Cookie cookie1 = new Cookie("id",id);
Cookie cookie2 = new Cookie("name","개똥이");
response.addCookie(cookie1);
response.addCookie(cookie2);

Cookie[] cookies = request.getCookies();
for(int i=0;i<cookies.length;i++){
	out.print(cookies[i].getName() + " = " 
		+ cookies[i].getValue() + "<br />");
}
%>

