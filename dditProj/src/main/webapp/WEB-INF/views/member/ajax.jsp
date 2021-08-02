<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>비동기 테스트</title>
	<script src="/resources/js/jquery.min.js"></script>
	<script type="text/javascript">
		//$(선택자).이벤트함수(코드)
		$(function(){
			$("#btn").on("click", function(){
				$.ajax({
					type:"post"
	// 				,url: "/member/useMap"
					,url: "/member/useVO"
					,success: function(result){ //콜백함수
						console.log("--- result ---");
						console.log(result);
						console.log(result.id);
						console.log(result.name);
						$("#result").html("아이디 : " + result.id + " 이름 : " + result.name);
					}
				});
			});
		});
	</script>
</head>
<body>
	<a href="/member/ajax?id=a001&name=rasmus">비동기식 호출1</a>
	<a href="/member/login">비동기식 호출2</a>
	<button type="button" id="btn">AJAX</button>	
	<div id="result">
		<c:if test="${map.id != null}">
			아이디 : ${map.id} 이름 : ${map.name}
		</c:if>
	</div>
</body>
</html>