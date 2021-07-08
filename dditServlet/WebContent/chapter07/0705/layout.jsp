<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>layout1</title>
</head>
<body>
<!-- 
	비교 항목	| jsp:include 액션 태그	  | include 디렉티브의 차이점
	---------------------------------------------------------------------
	처리 시간	| 요청 시간에 처리 (동적)		  | JSP 파일을 자바 소스로 변환시 처리 (정적)
	기능		| 별도 파일로 *요청 처리 흐름 이동  | 현재 파일로 대상 페이지가 들어감
	데이터 전달| request, jsp:param 이용    | 페이지 내에 변수 선언 후 변수에 값 저장
	용도		| 화면 레이아웃 일부분을 *모듈화    | 저작권, 공통 변수 지정    
	
	* 요청 처리 흐름 이동
	소스 코드를 위에서 쭉 읽어오다가
	액션 태그를 만나면, page속성을 처리하러 요청 처리 흐름을 이동시키는 것 
	
	* 모듈화 : 일종의 외부 파일을 붙인 모양
 -->
	<table border="1" style="width: 100%" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="2">
				<jsp:include page="../../module_0705/top.jsp" flush="false"></jsp:include>
			</td>
		</tr>
		<tr>
			<td style="width: 30%" valign="top">
				<jsp:include page="../../module_0705/left.jsp" flush="false"></jsp:include>
			</td>
			<td>
				<!-- 내용 부분 : 시작 -->
				레이아웃1<br /><br /><br />
				<!-- 내용 부분 : 끝 -->
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<jsp:include page="../../module_0705/bottom.jsp" />
			</td>
		</tr>
	</table>
</body>
</html>