<%@page import="java.io.IOException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>절대 경로를 사용하여 자원 읽기</title>
</head>
<body>
	<%
		//notice.txt의 경로를 복사해서 붙여넣는다, 단 경로의 역슬래쉬\는 2개여야 한다.
		String filePath = "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\dditServlet\\WebContent\\chapter05\\message\\notice.txt";
	
		//128개씩 잘라서 보관하겠다는 임시 공간
		char[] buff = new char[128];
		int len = -1;
		
		//stream : 물줄기, 파일에 대한 물줄기인 것
		//notice.txt로부터 내용을 읽어오는 스트림(데이터 물줄기)를 생성함.
		try {
			InputStreamReader fr = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
			
			//128개씩 잘라서 읽겠다, 더 이상 읽을 게 없으면 -1을 반환
			//notice.txt로부터 읽어온 데이터를 out 기본 객체를 사용하여 웹 브라우저에 출력함.
			while((len=fr.read(buff))!=-1) {
				//읽을 것이 있을 때...
				out.print(new String(buff,0,len));
			}
		}catch(IOException ex) {
			out.print("Exception 발생" + ex.getMessage());			
		}
		
	%>
</body>
</html>