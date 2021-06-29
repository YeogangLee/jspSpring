<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! //선언부(Declaration)를 시작함
/*
	스크립트릿이나 표현식에서 사용할 수 있는 함수를 작성할 때 사용
	선언부의 함수는 자바의 메서드와 동일
	정수값 : int, short, long
	실수값 : float, double
*/
/*
	<메서드 이름 규칙>
	- 분석 설계 : 현실세계의 추상화
	
	학생, 교사, 과목이라는 추상화
	그런 것들이 개체, 개체가 나중에 테이블이 된다.
	개체는, 누구나 가지고 있는 속성이 있다.
	학생은 이름, 성별 등의 속성을 가지고 있다.
	
	자바 빈이라는 개념이 있다.
	vo : value object
	좀 더 깊게 들어가보면, 얘네들이 규약이 있어.
	vo는 어떤 규약을 지키고 있냐? 바로 자바 빈 규약
	테이블 하나 만들었으니까 vo 추가해주세요 했던 그 vo
	자바 빈 규약
	: 카멜 표기법 준수, 필드가 있고, getter and setter 메서드 있어야 한다.
	
	<메서드 이름 규칙>
	첫 글자? 문자(알파벳 대소문자) 또는 밑줄로 시작
	첫 글자 제외한 나머지? 문자와 숫자 그리고 밑줄의 조합
	메서드 이름은 대소문자를 구분
	
	논리적 설계      물리적 세계
	개체(명사)-> 테이블		-> vo
	속성		-> 컬럼		-> vo의 필드
	관계		-> 조인 조건
*/
	public int multiply(int a, int b) {
		int c = a * b;
		return c;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	10 * 25 = <%=multiply(10, 25) %>
</body>
</html>