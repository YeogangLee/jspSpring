<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- <script src="/js/jquery.min.js"></script> -->
<title>로그인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	//https://jquery.com/download/
	$("#btn").on("click",function(){
		var varId = $("#id").val();
		var varPassword = $("#password").val();
		
		$.ajax({
			url:'/chapter09/company/loginok.jsp',	//요청 페이지
			type:'post',	//전송방식 post, get
			data:{'id':varId,'password':varPassword}, //보낼 데이터 json형식
// 			data:$("#frm").serialize() 				  //이렇게 form태그 자체를 보낼 수도 있다, 내가 적은거라 문법 틀릴수있음
			dataType:"json",						  //받을 데이터 형식 ex. json, xml, html, jsonp, text, vo, script...
			success:function(data){
				var rslt = data.loginResult;
				
				if(rslt == "fail"){
					alert("아이디 또는 비밀번호가 올바르지 않습니다.");
				}else{
					alert("로그인되었습니다.");
					location.href="/chapter09/company/main.jsp";
				}
			},
			error:function(err){
				alert("에러 발생");
			}
		});
	});
});
</script>

</head>
<body>

<table border="1" style="width:100%;" cellpadding="0" cellspacing="0">
<!-- top.jsp 시작 -->
<tr style="height:50px;">
	<td colspan="3">
		<jsp:include page="/chapter09/company/top.jsp" />
	</td>
</tr>
<!-- top.jsp 끝 -->
<tr style="height:300px;">
	<td style="width:10%;">
		<jsp:include page="/chapter09/company/left.jsp" />
	</td>
	<td colspan="2">
		<form name="frm" method="post" action="/chapter09/company/loginok.jsp">
			아이디 : <input type="text" id="id" name="id" placeholder="아이디를 입력해주세요" /><br />
			비밀번호 : <input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요" /><br />
			<input type="button" id="btn" value="확인" />
		</form>
	</td>
</tr>
<tr style="height:50px;">
	<td colspan="3">
		<jsp:include page="/chapter09/company/bottom.jsp" />
	</td>
</tr>
</table>

</body>
</html>