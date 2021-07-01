<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
	input {
		width: 40px;
		height: 30px;
		font-size: 17px;
		margin-bottom: 11px;
	}
</style>
<script type="text/javascript">
	function calc(eleA, b) {
		
		var a = eleA.value;
		console.log(a);
		
		var varA = document.getElementById("a");
		var sik = document.getElementById("sik");
		var varB = document.getElementById("b");
		var expression = document.getElementById("txtExpression");
		var valExp = "";
		
		//첫 번째 값이 없을 때 첫 번째 값을 입력
		if(varA.value == "") {
			if(b == 0) { //숫자일 경우만 체크(0:숫자, 1:연산자, 2:실행(=), 3:초기화)
				varA.value = a;
				valExp = $("#txtExpression").val();
				$("#txtExpression").val(valExp + a + " ");
			}
		}else { //첫 번째 값이 있을 때 두 번째 값으로 입력
			if(b == 0) { //숫자일 경우만 체크(0:숫자, 1:연산자, 2:실행(=), 3:초기화)
				varB.value = a;			
				valExp = $("#txtExpression").val();
				$("#txtExpression").val(valExp + a + " ");
			}
		}
		
		//연산자 입력
		if(b == 1) {
			sik.value = a;
			valExp = $("#txtExpression").val();
			$("#txtExpression").val(valExp + a + " ");
		}
		
		// = 입력
		if(b == 2) {
			if(!fn_chk()) {
				return;
				
			}else { //빈값이 없을시 실행
				valExp = $("#txtExpression").val();
				$("#txtExpression").val(valExp + "=");
				
				var param = {
					valA: $("#a").val()
					,valSik : $("#sik").val()
					,valB: $("#b").val()
					
	// 				valA : varA.value
	// 				,valSik : sik.value
	// 				,valB : varB.value
				}
				
				$.ajax({
					url: "/CalcServlet"
					,type: "post"
					,data: param
					,dataType: "json"
					,success: function(data) {
						console.log(data);
	// 					alert("성공");
						showResult(data);
					}
					,error: function(xhr) {
						console.log(xhr);
						alert("오류 발생");
					}
				});
			} //else
				
		} //if b == 2
		
		//초기화 입력
		if(b == 3) {
			$("#a").val("");
			$("#sik").val("");
			$("#b").val("");
			$("#txtResult").val("");
			$("#txtExpression").val("");
		}
			
	}
	
	function showResult(data) {
		
		$("#txtResult").val("");
		
		var result = data.result;

		$("#txtResult").val(result);		
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
		
		return true;
	}
	
</script>
</head>
<body>
	<!-- get방식으로 자기 자신을 호출하고 있다. -->
	<!-- onsubmit : name, value 속성이 쌍으로 같이 넘어간다 -->
	<form id="fm" method="get">
		<input type="hidden" name="a" id="a" value="" />
		<input type="hidden" name="sik" id="sik" value="" />
		<input type="hidden" name="b" id="b" value="" />
		<br>
		<input type="text" name="txtExpression" id="txtExpression" value=""
				style="width: 170px"><br>
		<input type="text" name="txtResult" id="txtResult" value=""
				style="width: 170px; text-align: right;">
		<br>
		<input type="button" value="7" onclick="calc(this, 0)">
		<input type="button" value="8" onclick="calc(this, 0)">
		<input type="button" value="9" onclick="calc(this, 0)">
		<input type="button" value="+" onclick="calc(this, 1)">
		<br>
		<input type="button" value="4" onclick="calc(this, 0)">
		<input type="button" value="5" onclick="calc(this, 0)">
		<input type="button" value="6" onclick="calc(this, 0)">
		<input type="button" value="-" onclick="calc(this, 1)">
		<br>
		<input type="button" value="1" onclick="calc(this, 0)">
		<input type="button" value="2" onclick="calc(this, 0)">
		<input type="button" value="3" onclick="calc(this, 0)">
		<input type="button" value="x" onclick="calc(this, 1)">
		<br>
		<input type="button" value="C" onclick="calc(this, 3)">
		<input type="button" value="0" onclick="calc(this, 0)">
<!-- 	<input type="submit" value="=" > -->
		<input type="button" value="=" onclick="calc(this, 2)">
		<input type="button" value="/" onclick="calc(this, 1)">
		
	</form>
</body>
</html>