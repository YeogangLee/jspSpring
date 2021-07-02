<%@page import="java.io.IOException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>시 읽기</title>
</head>
<body>
	<%
		String filePath = "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\dditServlet\\WebContent\\chapter05\\message\\poem.txt";
	
		char[] buff = new char[128];
		int len = -1;
		
		try{
			InputStreamReader fr = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
			
			while((len=fr.read(buff))!=-1) {
				out.print(new String(buff,0,len));
			}
			
		}catch(IOException ex) {
			out.print("Exception 발생" + ex.getMessage());			
		}
		
	%>
</body>
</html>