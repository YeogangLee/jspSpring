<%@page import="chapter11.vo.StudentVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	request.setAttribute("name", "민들레");
	session.setAttribute("name", "김은대");
	application.setAttribute("name", "신용재");
%>
<!DOCTYPE html>
<html>
<head>
	<title>EL Object</title>
</head>
<body>

<!-- request 기본 객체의 name 속성의 값을 가져옴 -->
기존 방식<br/>(스크립트 릿 이용) : <%=request.getAttribute("name") %><br/><br/>
<!-- request 영역에 저장된 name 속성의 값을 가져옴 -->
<%-- 새로운 방식<br/>(EL 이용) : ${requestScope.name}<br/><br/> --%>

requestScope 지우기<br/>(EL 이용) : ${name}<br/><br/>
requestScope : request<br/>(EL 이용) : ${requestScope.name}<br/><br/>
requestScope : session<br/>(EL 이용) : ${sessionScope.name}<br/><br/>
requestScope : application<br/>(EL 이용) : ${applicationScope.name}<br/><br/>

요청 URI : ${pageContext.request.requestURI}<br/>

<!-- http://localhost:8090/chapter11/0709/useELObject.jsp?code=P101 -->
<!-- 
	* EL의 장점 *
	code 파라미터 값이 없더라도 null을 출력하는 것이 아니라
	아무것도 출력하지 않음  ==> (오류가 발생하지 않음)
 -->
code 파라미터 : ${param.code}<br />
<!-- 
	* 표현 언어(Expression Language)
	- JSP에서 사용 가능한 새로운 스크립트 언어
	- 간단한 구문 때문에 표현식 (꺽임쇠 퍼센트 =) 대신 사용함
	- JSP의 4가지 기본 객체가 제공하는 영역의 속성 사용
	  (page, request, session, application)
	- 집합 객체에 대한 접근 방법 제공
	- 수치 / 관계 / 논리 연산자 제공
	- 자바 클래스의 메서드 호출 기능 제공
	- 표현 언어만의 기본 객체 제공
	- 스크립트 요소(스크립트 릿, 표현식, 선언부)를 제외한 나머지 부분에서 사용
 -->
<br />
<!-- caption -->
<!-- 1. EL 리터럴 표현식<br /> -->
<!-- <table> -->
<!-- 	<tr> -->
<%-- 		<td>문자열 : ${"apple"}</td> --%>
<!-- 		<td></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td>나는 ${'학교'}에 갑니다.</td> --%>
<!-- 		<td></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td>맥날 아이스 아메리카 가격은 ${1000}원입니다.</td> --%>
<!-- 		<td></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td>파이는 ${3.14}이다.</td> --%>
<!-- 		<td></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td>오늘 아침 식사는 하셨나요? ${true}</td> --%>
<!-- 		<td></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td>null표현? "${null}" (빈 문자열 whitespace)</td> --%>
<!-- 		<td></td> -->
<!-- 	</tr>	 -->
<!-- </table> -->
1. EL 리터럴 표현식<br />
문자열 : ${"apple"} --> "문자열"<br />
나는 ${'학교'}에 갑니다. --> '문자열'<br />
맥날 아이스 아메리카 가격은 ${1000}원입니다. --> 정수<br />
파이는 ${3.14}이다. --> 소수<br />
오늘 아침 식사는 하셨나요? ${true} --> 논리형<br />
null표현? "${null}" (빈 문자열 whitespace) --> null<br />
<%
// 	out.print("intArr.length : " + intArr.length + "<br/>");
	int[] intArr = new int[]{100, 90, 87, 70, 60};
	out.print("intArr.length : " + intArr.length + "<br/>");
	//pageContext : PAGE 기본 영역의 속성에 배열을 저장
	pageContext.setAttribute("jumsu", intArr);
%>
	<!-- 값 출력 가능 -->
	${jumsu[0]}<br/>
	${jumsu[1]}<br/>
	${jumsu[2]}<br/>
	${jumsu[7]}<br/> <!-- 없는 인덱스를 사용하더라도 EL은 에러발생X -->
<%
	//쌤도 이유는 모르겠는데, 여러가지 방법을 써도, 이렇게 출력하면 변수값 출력이 잘 안 되더라.
	//이렇게는 잘 안되므로 JSTL 때 반복 처리를 배워서 하자.
	for(int i=0; i<intArr.length; i++) {
		out.print("${jumsu[" + i + "]}<br/>");	
// 		out.print(${jumsu[i]} + "<br/>");	
	}
%>
<br />
3. List<br />
<%
	List<String> list = new LinkedList<>();
	list.add("사과");
	list.add("바나나");
	list.add("오렌지");
	
	//pageContext : 페이지 일반 객체의 저장소
	pageContext.setAttribute("list", list);
%>
	<!-- 
		표현식의 변수를 아래의 scope들에서 찾는다.
		pageScope, requestScope, sessionScope, applicationScope
	 -->
	${pageScope.list[0]}<br />
	${requestScope.list[1]}<br /> <!-- 출력x -->
	${list[2]}<br />
	${list[3]}<br /> <!-- 에러X -->
	<!-- 
		기본 객체			| 영역 이름			| 보관소
		-------------------------------------------------------------- 
		1. PAGE			| pageScope			| pageContext
		2. REQUEST		| requestScope		| servletRequest
		3. SESSION		| sessionScope		| httpSession
		4. APPLICATION	| applicationScope	| servletContext
	 -->
	4. Map<br/>
<%
	Map<String, String> map = new HashMap<>();
	map.put("id", "a001");
	map.put("name", "개똥이");
	map.put("age", "25");
	
	pageContext.setAttribute("map", map);
%>
	"\${map["id"]}"<br/>
	: ${map["id"]}<br/>${map["name"]}<br/>${map["age"]}<br/>
	"\${map.id}"<br/>
	: ${map.id}<br/>${map.name}<br/>${map.age}<br/>
	<br/>
	5. 자바 객체 <br/>
<%
	//자바빈 클래스를 인스턴스화 하여 객체를 생성
	StudentVO studentVO = new StudentVO();
	studentVO.setStudentNo("20211117"); 
	studentVO.setStudentName("겨울이");
	
	pageContext.setAttribute("student", studentVO);
%>
	${student["studentNo"]}<br/>${student["studentName"]}<br/>
	${student.studentNo}<br/>${student.studentName}<br/>
	<br/>
	6. 산술 연산자<br/>
	<!-- 자바 : 5/2 = 2
	     EL : 5/2 = 2.5
	     5/2 <=> 5 div 2
	-->
	${5+2}<br/>${5-2}<br/>${5*2}<br/>${5/2}<br/>${5 div 2}<br/>
	<br/>
	7. 논리 연산자<br/>
	${5==5 && 7==7}<br/>${5==5 and 7!=7}<br/>
	${5==5 || 7==7}<br/>${5==5 or 7!=7}<br/>
	${not true}<br/>${!true}<br/>${!(5==5)}<br/>${!("사과"=="사과")}<br/>
	<br/>
	8. 비교 연산자<br/>
	${5==5}<br/>${5 eq 5}<br/>
	${5!=5}<br/>${5 ne 5}<br/> <!-- ne : not equal -->
	less than : ${5<7}<br/>${5 lt 7}<br/>
	less than equal : ${5<=7}<br/>${5 le 7}<br/>
	greater than : ${5>7}<br/>${5 gt 7}<br/>
	greater than equal : ${5>=7}<br/>${5 ge 7}<br/>
	<br/>
	9. null 체킹<br/>
	<!-- 위에서 student 변수를 사용했기 때문에 empty는 false -->
	${empty student}, ${empty studentVO2}<br/>
	<br/>
	10. 삼항연산자<br/>
	${empty param.code ? "code 파라미터 없음" : param.code}<br />
	<br/>
	\${1 + 1 ; 5 + 5}뭘까?<br/>
	${1 + 1 ; 5 + 5}<br/>;뒤의 연산 결과만 출력<br/>
	
</body>
</html>