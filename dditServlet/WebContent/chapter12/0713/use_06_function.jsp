<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<title>함수 사용</title>
</head>
<body>
	<c:set var="str1" value="  Functions <태그>를 사용합니다.    " />
	<c:set var="str2" value="사용" />
	<c:set var="tokens" value="1,2,3,4,5,6,7,8,9,10" />
	
	str1 : ${str1}<br/>
	str1의 length: ${fn:length(str1)}<br/>	
	str1을 모두 대문자로 : ${fn:toUpperCase(str1)}<br/> 	
	str1을 모두 소문자로 : ${fn:toLowerCase(str1)}<br/>
	substring(str1,3,6) : ${fn:substring(str1,3,6)}<br/> 	
	substringBefore(str1,str2) : ${fn:substringBefore(str1,str2)}<br/>
	substringAfter(str1,str2) : ${fn:substringAfter(str1,str2)}<br/>
	trim(str1) 좌우공백제거 : ${fn:trim(str1)}<br />
	replace(str1, src, dest) : ${fn:replace(str1, " ", "-")}<br/>
	indexOf(str1, str2) 사용 index : ${fn:indexOf(str1,str2)}<br/>
	startsWith(str1, str2) '&ensp;Fun'으로 시작하는가? : ${fn:startsWith(str1, '  Fun')}<br/>
	endsWith(str1, str2) '합니다'로 끝나는가? : ${fn:endsWith(str1, '합니다')}<br/>
	contains(str1, str2) str1(${str1})에 str2(${str2})가 포함되는가? : ${fn:contains(str1, str2)}<br/>
	containsIgnoreCase(str1, str2) : ${fn:containsIgnoreCase(str1, str2)}<br/>
	
	<c:set var="array" value="${fn:split(tokens, ',') }" /><br/>
	array[0] : ${array[0]}<br/>
	join(array, "-") 문자열 합침 : "${fn:join(array, "-") }"<br/>
	<!-- fn:escapeXml() : 문자열을 감쌀 때 사용한 ""따옴표까지 같이 출력 -->
	escapeXml(str1) 특수문자처리(&는 &amp;로 변환) : "${fn:escapeXml(str1)}"<br/>
	<!-- xml문서에 못쓰는 <, > 등을 &lt;, &gt; 등으로 대체하여 사용 가능 -->
	< : &lt; <br/>	
	> : &gt; <br/>	
	<= : &le; <br/>	
	>= : &ge; <br/>	
</body>
</html>