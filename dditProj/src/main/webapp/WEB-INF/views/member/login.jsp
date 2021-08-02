<%@page import="kr.or.ddit.member.vo.MemberTableVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//현재 페이지에서는 그렇게 필요해보이지는 않지만.. 이렇게도 사용 가능하다.
	//그렇게 필요해보이지 않는 이유는,
	//session 정보는 loginResult.jsp 파일에서 필요한 정보이기 때문이다..
	/*
		현재 스크립트 릿 : 혼자 추가한 부분
		추가한 이유
		: 로그인한 상태에서 GET방식으로 /member/login요청을 했을 때 로그인 입력창이 안 보이게 하려고.
		그래서 로그인하면 session객체에 담기는 memberVoDB 속성이 비어있는지 아닌지를 조건으로 이용했다.  
	*/
	
	if(session.getAttribute("memberVoDB") != null) {
		//1.forward 방식 (GET, 확인까지 했다.)
		RequestDispatcher rd = request.getRequestDispatcher("/member/loginResult");
		rd.forward(request, response);
		
		//2.redirect 방식 (GET)
// 		response.sendRedirect("/member/loginResult");
		
		//두 방법으로 실험을 해 본 결과, 두 방식은 URL이 다르다.
		/*
		1.forward
		- 로그인한 상태에서 GET /member/login 요청 
		: 요청 변경 X
		
		2.redirect
		- 로그인한 상태에서 GET /member/login 요청
		: url 주소 변경 (요청 변경) => /member/loginResult 요청으로 바뀐다.
		
		*/
		// 잘은 모르겠지만... login에 접근할 수 없게 redirect 해보는게 지금에는 더 적절해 보인다.
		
	}else {
%>
<!DOCTYPE html>
<html>
<head>
	<title>로그인</title>
	<script src="/resources/js/jquery.min.js"></script>
	<script type="text/javascript">
		$(function(){
// 			$("#btnLogin").click(function(){});		//방법1.
			$("#btnLogin").on("click", function(){	//방법2.
				var id = $("#id").val();
				var pwd = $("#password").val();
				var param = {
						"id" : id
						,"pwd" : pwd
						//vo로 받을 때는 이름을 맞춰줘야 하는데 지금은 다른 방식으로 받아본다..
						/*
						  vo를 이용하는 방법 2가지
						  1. 파라미터로 던지기
						  2. 컨트롤러의 메서드 내부에서 new로 vo를 만들어서, 개별 파라미터 변수값들을 vo에 set해주기
						  
						    위에서 말한 다른 방식이란 것은..
						  -> 2번 방식, 결국 vo를 이용하게 되는 것이다
						    왜(결국 vo를 이용)?
						  -> 파라미터로 받은 개별 값들과,
						          이 파라미터를 이용해서 select 쿼리 수행 후 vo(파라미터타입)에 담아온,
						     DB에 있는 id, pwd값과 비교해야 하니까
						    아니면 map에 담아줄 수도 있겠다...
						*/
				};
				$.ajax({
					type:"post"
					,url:"/member/loginResult"
					,data: param
					,dataType:"html" //생략 가능, html만 가능, json jsp x, 실제로 아래에서 result 찍으면  html모양이다.
					,success: function(result){
						console.log("--- result ---");
						console.log(result);
						var pos = result.indexOf("환영합니다"); //로그인 성공 시 항상 있는 문자열 값
						if(pos > 0) { //해당 조건문이 없으면, 아이디 비번을 잘못 입력해도, 로그인 영역이 사라진다.
							//로그인에 성공하면, 로그인 영역 감추기(none), 로그아웃 버튼 보여주기(block)
							$("#spnIn").css("display", "none");
							$("#spnOut").css("display", "block");
						}
						
						//로그인에 성공했든 실패했든, 그 결과는 항상 보여주기
						// : .html()메소드로 result로 반환된 loginResult.jsp를페이지 내용 넣기
						$("#result").html(result); 
					}
				});
				
			});
			
			$("#btnLogout").on("click", function(){
				var formObj = $("#frm");
				//form 태그에 적어도 되지만.. attr() 메서드를 이용한, 이런 방법도 있다
// 				formObj.attr("action", "/member/logout");
// 				formObj.attr("method", "get");
				formObj.submit();
			});
			
		});
	</script>
</head>
<body>
<!-- 로그인 -->
<span id="spnIn" style="display:block;">
	id : <input type="text" id="id" name=""/>
	password : <input type="password" id="password" name=""/><br/>
	<input type="button" id="btnLogin" value="로그인" />
</span>
<!-- 로그아웃 -->
<span id="spnOut" style="display:none;">
	<form id="frm" action="/member/logout" method="get">
		<input type="button" id="btnLogout" value="로그아웃1" /><br/>
	</form>
</span>
<!-- 결과 출력 -->
<div id="result"></div>	
</body>
</html>
<% 	} %>