<%@page import="chapter11.Thermometer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	//Thermometer 클래스로부터 객체 생성
	Thermometer thermometer = new Thermometer();

	//EL에서 사용 가능하도록 request 기본 객체의 속성으로 추가
	request.setAttribute("t", thermometer);	
%>
<!DOCTYPE html>
<html>
<head>
	<title>온도 변환 예제</title>
</head>
<body>
	${t.setCelsius("대전", 33)}
	${t.setCelsius("서울", 31)}
	
	${t.getInfo()}<br/>
	${t.info}<br/> <!-- 필드값이 아닌, getInfo() 메서드에서 들고온 것 -->
	대전 온도 : 섭씨 ${t.getCelsius("대전")}도  / 화씨 : ${t.getFahrenheit("대전")}<br/> 
	서울 온도 : 섭씨 ${t.getCelsius("서울")}도  / 화씨 : ${t.getFahrenheit("서울")}<br/>
	작성자 : ${t.getName()}<br/>
	작성자 : ${t.name}<br/> <!-- name이란 게 필드가 아니에요, 메서드에서 들고온 것 -->
</body>
</html>