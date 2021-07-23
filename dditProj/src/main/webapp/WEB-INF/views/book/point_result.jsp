<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>점수 결과</title>
</head>
<body>
	이름 : ${pointVo.name}<br/>
	국어 : ${pointVo.kor}<br/>
	영어 : ${pointVo.eng}<br/>
	수학 : ${pointVo.mat}<br/>
	<hr/>
	총점 : ${pointVo.total}<br/>
	평균 : ${pointVo.average}<br/>
</body>
</html>