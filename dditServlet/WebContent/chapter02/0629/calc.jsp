<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	public int add(int a, int b) { int c = a + b; return c; }
	public int minus(int a, int b) { int c = a - b; return c; }
	public int multiply(int a, int b) { int c = a * b; return c; }	
	public int divide(int a, int b) { int c = a / b; return c; }	
%>
<%
	//request는 jsp에서 기본적으로 제공하는 객체 ex. session, application, response, page, ...
	//request: 요청 정보를 가지고 있는 객체, 웹 브라우저를 닫지 않는 이상 계속 존재
	//페이지를 닫지 않고, 이동했을 때에도 계속 존재, 웹 브라우저를 닫는 순간 리퀘스트 객체가 사라진다.
	
/*
	<request> 기본 객체
	1. 웹 브라우저(클라이언트)가 웹 컨테이너(tomcat)(..웹서버)에 전송한 요청 관련 정보 제공
	2. jsp에서 가장 많이 사용되는 기본 객체. 웹 브라우저의 요청과 관련이 있음.
	3. 웹 브라우저에 웹 사이트의 주소를 입력하면, 웹 브라우저는 해당 웹 서버에 연결한 후
	      요청 정보를 전송한다. 이때 이 요청 정보를 제공한다.

	4. 주요 기능
	- 클라이언트(웹 브라우저)와 관련된 정보 읽기
	- 서버와 관련된 정보 읽기
	- 클라이언트가 전송한 요청 파라미터 읽기 (*****중요)
	- 클라이언트가 전송한 요청 헤더 읽기
	- 클라이언트가 전송한 쿠키 읽기
	- 속성도 처리해줌
	
	다음 시간에는 리퀘스트 객체의 파라미터와 관련된 예제를 하며 모두 정리할 것...
	
*/
	
	//만약에 파라미터가 없으면, 여기서 오류 발생
	//널 처리를 해 줄 필요가 있다.
// 	String a = request.getParameter("a");

	//널 처리 적용
	String strA = request.getParameter("a") == null ? "0" : request.getParameter("a");
	String strSik = request.getParameter("sik") == null ? "_" : request.getParameter("sik");
	String strB = request.getParameter("b") == null ? "0" : request.getParameter("b");
	
	int a = Integer.parseInt(strA);
	int b = Integer.parseInt(strB);
	int result = 0;
	
	if(strSik.equals("+")) { //덧셈
		result = add(a, b);
		
	}else if(strSik.equals("-")) {
		result = minus(a, b);
		
	}else if(strSik.equals("x")) {
		result = multiply(a, b);
		
	}else if(strSik.equals("/")) {
		result = divide(a, b);
		
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>계산기</title>
<style type="text/css">
	table {
		width: 300px;
	}
	table, tr, th, td {
		border: 1px solid black;
		border-collapse: collapse;
		text-align: center;
	}
</style>
<script type="text/javascript">
	function calc(eleA, b) {
		var a = eleA.innerHTML;
		
// 		alert(a + ", " + b);
		console.log(a + ", " + b);
		
		var varA = document.getElementById("a");
		var sik = document.getElementById("sik");
		var varB = document.getElementById("b");

		//첫 번째 값이 없을 때 첫 번째 값을 입력
		if(varA.value == "") {
			if(b == 0) { //숫자일 경우만 체크(0:숫자, 1:연산자, 2:실행, 3:초기화)
				varA.value = a;
			}
		}else { //첫 번째 값이 있을 때 두 번째 값으로 입력
			if(b == 0) { //숫자일 경우만 체크(0:숫자, 1:연산자, 2:실행, 3:초기화)
				varB.value = a;			
			}
		}
		//연산자 입력
		if(b == 1) {
			sik.value = a;
		}
	}
	
	function fn_chk() {

		var varA = document.getElementById("a");
		var sik = document.getElementById("sik");
		var varB = document.getElementById("b");

		if(varA.value == "") {
			alert("첫 번째 숫자를 입력해주세요.");
			return false;
		}
		if(sik.value == "") {
			alert("연산자를 입력해주세요.");
			return false;
		}
		if(varB.value == "") {
			alert("두 번째 숫자를 입력해주세요.");
			return false;
		}
		
		return true; //submit이 실행됨.
		//http://localhost:8090/chapter02/0629/calc.jsp?a=9&sik=x&b=7&txtResult=
	}
	
</script>
</head>
<body>
	<!-- get방식으로 자기 자신을 호출하고 있다. -->
	<!-- onsubmit : name, value 속성이 쌍으로 같이 넘어간다 -->
	<form name="fm" id="fm" method="get" action="/chapter02/0629/calc.jsp" onsubmit="return fn_chk()">
		<input type="hidden" name="a" id="a" value="" />&nbsp;
		<input type="hidden" name="sik" id="sik" value="" />&nbsp;
		<input type="hidden" name="b" id="b" value="" />
		<br>
		<table>
			<thead>
				<tr>
					<th colspan="4">
						<input type="text" name="txtResult" id="txtResult" value="<%=result %>"
								style="width: 95%; text-align: right;">
					</th>
				</tr>
				<tr>
					<th colspan="3" onclick="calc(this, 3)">C</th>
					<th onclick="calc(this, 1)">/</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td onclick="calc(this, 0)">7</td>
					<td onclick="calc(this, 0)">8</td>
					<td onclick="calc(this, 0)">9</td>
					<td onclick="calc(this, 1)">x</td>
				</tr>
				<tr>
					<td onclick="calc(this, 0)">4</td>
					<td onclick="calc(this, 0)">5</td>
					<td onclick="calc(this, 0)">6</td>
					<td onclick="calc(this, 1)">-</td>
				</tr>
				<tr>
					<td onclick="calc(this, 0)">1</td>
					<td onclick="calc(this, 0)">2</td>
					<td onclick="calc(this, 0)">3</td>
					<td onclick="calc(this, 1)">+</td>
				</tr>
				<tr>
					<td colspan="3" onclick="calc(this, 0)">0</td>
					<input type="submit" value="=">
<!-- 					<td onclick="calc(this, 2)">=</td> -->
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>