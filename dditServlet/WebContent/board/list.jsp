<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 목록</title>
<style type="text/css">
	table, th, td {
	  border: 1px solid black;
	  border-collapse: collapse;
	}
	table {
		width: 500px;
	}
	th, td {
		padding: 10px;
		text-align: center;
	}
	button {
		width: 90px;
	    height: 40px;
	    margin-right: 10px;
	    cursor: pointer;
	}
	#btnWrite {
		margin-left: 400px; 
	}
	#btnSearch {
		margin-left: 18px;
	}
	#tbSearch {
		width: 380px;
		float: left;
	}
</style>
<script type="text/javascript">
	function searchBoard() {
			
	}	
	
</script>
</head>
<body>
	<table id="tbList">
		<tr>
			<th>글 번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
		</tr>
		<tr>
			<td colspan="4">게시글 정보가 없습니다.</td>
		<tr>
	</table>
	<br>	
		<button type="button" id="btnWrite" onclick="location.href='insertForm.jsp'">글 작성</button>
	<br><br>
	<div>
		<button type="button" id="btnSearch" onclick="searchBoard()">게시글 검색</button>
		<table id="tbSearch">
			<tr>
				<td>글 번호 : <input type="text" name="boardNo"></td>
			</tr>
			<tr>
				<td>제목 : <input type="text" name="boTitle"></td>
			</tr>
			<tr>
				<td>작성자 : <input type="text" name="boWriter"></td>
			</tr>
			<tr>
				<td>내용 : <input type="text" name="boContent"></td>
			</tr>
		</table>
	</div>	
</body>
</html>