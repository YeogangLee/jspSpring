<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="/resources/style/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/resources/style/jquery-ui.css">
<link rel="stylesheet" href="/resources/style/style.css">
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/jquery-1.12.4.js"></script>
<script src="/resources/js/jquery-ui.js"></script>
<title>근무 정보 입력</title>
<script type="text/javascript">
$(function(){
	$(".trEmp").on("click",function(e){
		var varEmpNum = $(this).find("td:eq(0)").text();
// 		alert(varEmpNum);
		//JSON 데이터로 만듦
		var data = {"empNum":varEmpNum};
		//사원 상세 정보 가져오기
		$.ajax({
			type:"post"
			,url:"/wk/selectEmpDetail"
			,data:JSON.stringify(data)	//보낼 데이터
			,contentType:"application/json"
			,cache:false
			,success:function(data){
				console.log("empNum : " + data.empNum);
				$("input[name='empNum']").val(data.empNum);
				console.log("empNm : " + data.empNm);
				$("input[name='empNm']").val(data.empNm);
				console.log("zipCode : " + data.zipCode);
				$("input[name='zipCode']").val(data.zipCode);
				console.log("addr1 : " + data.addr1);
				$("input[name='addr1']").val(data.addr1);
				console.log("addr2 : " + data.addr2);
				$("input[name='addr2']").val(data.addr2);
				console.log("phnNum : " + data.phnNum);
				$("input[name='phnNum']").val(data.phnNum);
				console.log("pos : " + data.pos);
				$("input[name='pos']").val(data.pos);
				console.log("pdeptNmos : " + data.deptNm);
				$("input[name='deptNm']").val(data.deptNm);
			}
		});
	});//end trEmp
	//사업장의 특정 행을 클릭한다면..
	$(".trSite").on("click",function(){
		//this : 클릭 된 tr
		//find("td.. : td 요소를 찾으면 3개의 요소가 찾아짐. 0,1,2
		//eq(0) : 인덱스 번호가 0번인 td를 찾음
		//text() => <td>2021001</td> => 2021001
		var varSiteNum = $(this).find("td:eq(0)").text();
		var varSiteNm = $(this).find("td:eq(1)").text();
// 		alert("varSiteNum : " + varSiteNum + ", varSiteNm : " + varSiteNm);
		$("input[name='siteNum']").val(varSiteNum);
		$("input[name='siteNm']").val(varSiteNm);
	});//end trSite
	
	$("#btn").on("click",function(){
		//var f = $("form[name='frm']"); //form 태그 중에서 name속성의 값이 frm의 안의 모든 요소를 f에 담음 
		var f = $("#frm");	//id로 form 안의 모든 요소를 f에 담음
		
		if($("input[name='empNum']").val()==""){
			alert("사원을 선택해주세요");
		}else if($("input[name='empNm']").val()==""){
			alert("사원을 선택해주세요");
		}else if($("input[name='siteNum']").val()==""){
			alert("사업장을 선택해주세요");	
		}else if($("input[name='siteNm']").val()==""){
			alert("사업장을 선택해주세요");
		}else if($("input[name='wkStartDt']").val()==""){
			alert("근무 시작일을 선택해주세요");
		}else{
			f.attr("method","post");
			f.attr("action","/wk/insert");
			f.submit();
		}
		
	});
	
	$(".empDel").on("click", function(e){
		e.stopPropagation();
		
		//선택한 이미지의 value값을 가져옴
		console.log("-----empNum-----" + $(this).attr("value"));
		var varEmpNum = $(this).attr("value");
		var data = {"empNum":varEmpNum};
		
		$.ajax({
			type:"post"
// 			,url:"/wk/myDelete"
			,url:"/wk/delete"
			,data:JSON.stringify(data)		//보내는 형식
			,contentType:"application/json" //받는 형식
			,cache:false
			,success:function(data){
				console.log("empNum(returned) : " + data.empNum);
				
// 				if(data != null) { //삭제 성공
// 					parent.location.reload();
// 				}else {
// 					alert("삭제할 수 없는 데이터입니다.");
// 				}
				
			}
		});
	});
});//end function

function fn_regEmp(){
	window.open("/emp/insert","owin","width=700,height=700");
}

function fn_regSite(){
	window.open("/site/popUp/insert","owin","width=700,height=700");
}

</script>
</head>
<body style="margin:0px;">

<c:if test="${msg!=null}">
	<script type="text/javascript">
		alert("삭제 " + ${msg});
	</script>
</c:if>

<div id="box">
	<!-- 사원 정보 상세 -->
	<div id="top">
	<h2>근무 정보 입력</h2>
		<table border="1" style="width:100%;" cellspacing="0" cellpadding="10">
			<tr style="text-align:left;">
				<th style="width:5%;">사원 번호</th><td style="width:25%;"><input type='text' name='empNum' /></td>
				<th style="width:5%;">사원 명</th><td style="width:25%;"><input type='text' name='empNm' /></td>
				<th style="width:5%;"></th><td style="width:25%;"></td>
			</tr>
			<tr style="text-align:left;">
				<th>우편번호</th><td><input type='text' name='zipCode' /></td>
				<th>주소1</th><td><input type='text' name='addr1' /></td>
				<th>주소2</th><td><input type='text' name='addr2' /></td>
			</tr>
			<tr style="text-align:left;">
				<th>전화번호</th><td><input type='text' name='phnNum' /></td>
				<th>직급</th><td><input type='text' name='pos' /></td>
				<th>부서 명</th><td><input type='text' name='deptNm' /></td>
			</tr>
		</table>
	</div>
<hr />
<!-- 사원 정보 목록 -->
	<div id="left" class="container1">
	<h2>사원 목록&nbsp;&nbsp;<input type="button" value="사원 등록" onclick="fn_regEmp()" /></h2>
		<table border="1" style="width:80%;" cellspacing="0" cellpadding="10">
			<tr>
				<th>사원 번호</th>
				<th>사원 명</th>
			</tr>
<c:forEach var="empVo" items="${empVoList}">
			<tr class="trEmp" style="cursor:pointer;">
				<td>${empVo.empNum}</td>
				<td>${empVo.empNm}&nbsp;
				<img src="/resources/images/delete.png" style="width: 30px; height: 30px; z-index: 1;"
					 alt="삭제" title="삭제" class="empDel" value="${empVo.empNum}"/>	
				</td>
			</tr>
</c:forEach>
		<tr>
			<td colspan="2" style="text-align: center;">
				${pagingStr}
			</td>
		</tr>
		</table>
		
		<br/>
		<!-- 페이징 처리 Pagination -->
<!-- 		[1] [2] [3] -->
<%-- 		${pagingStr} --%>
	</div>
<!-- 사업장 정보 목록 -->
	<div id="right">
	<h2>사업장 정보&nbsp;<input type="button" value="사업장 등록" onclick="fn_regSite()"> </h2>
		<table border="1" style="width:80%;" cellspacing="0" cellpadding="10">
			<tr>
				<th>사업장 번호</th>
				<th>사업장 명</th>
				<th>전화 번호</th>
			</tr>
<c:forEach var="siteVo" items="${siteVoList}">
			<tr class="trSite" style="cursor:pointer;">
				<td>${siteVo.siteNum}</td>
				<td>${siteVo.siteNm}</td>
				<td>${siteVo.phnNum}</td>
			</tr>
</c:forEach>
		<tr>
			<td colspan="2" style="text-align: center;">
				${sitePagingStr}
			</td>
		</tr>
		</table>
	</div>
<!-- 근무 정보 매핑 -->
	<div id="bottom">
	<form name="frm" id="frm" method="post" action="/wk/insert">	
			<h2>근무 정보 매핑</h2>
			사원 : <input type="hidden" name="empNum" /><input type="text" name="empNm" style="width:200px;" readOnly />&nbsp;&nbsp;
			사업장 : <input type="hidden" name="siteNum" /><input type="text" name="siteNm" style="width:200px;" readOnly />&nbsp;&nbsp;
			근무 시작일 : <input type="text" name="wkStartDt" id="datepicker" 
				style="width:200px;" readOnly />&nbsp;&nbsp;
			<input type="button" value="등록" id="btn" />
	</form>		
	</div>
</div>
<script type="text/javascript">
$(function(){
	$("#datepicker").datepicker({
		dateFormat:'yy-mm-dd',
		changeYear:true,
		changeMonth:true,
		showMonthAfterYear:true,
		dayNamesMin:['일','월','화','수','목','금','토'],
		monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
		});
});
</script>
</body>
</html>

