<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

String id = request.getParameter("id");
String password = request.getParameter("password");

//out.print("id : " + id + ", password : " + password + "<br />");

//id와 비밀번호 비교
if(id.equals("a001")&&password.equals("1234")){
	Cookie cookie1 = new Cookie("id",id);	//a001
	Cookie cookie2 = new Cookie("name","개똥이");	//
	response.addCookie(cookie1);	//쿠키저장소에 저장
	response.addCookie(cookie2);	//쿠키저장소에 저장
%>
	{"loginResult":"success"}
<%
	//Cookie[] cookies = request.getCookies();
	//for(int i=0;i<cookies.length;i++){
		//out.print(cookies[i].getName() + " = " 
		//	+ cookies[i].getValue() + "<br />");
	//}
}else{
%>
	{"loginResult":"fail"}
<%
}
%>

