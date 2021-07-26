package chapter17;

import java.awt.print.Printable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿 클래스를 구현하려면 HttpServlet 클래스를 상속받아야 한다.
/*
	<서블릿>
	- jsp가 나오기 전의 표준, java로 웹 어플리케이션 개발
	- 서블릿 vs jsp => jsp가 더 간단함
	    그러면 jsp만 배우지 서블릿은 왜 배울까? -> 패턴 프로그래밍 때문에 
	- MVC 패턴에서 서블릿 기반 코드로 개발하는 경우가 있다.
 
 	<개발 과정>
 	1) 서블릿 규약에 따라 자바 코드 작성
 	- 아래는 이클립스에서 알아서 수행하는 부분
 	1-2) 자바 코드 컴파일 (클래스 파일 생성)
 	1-3) 클래스 파일을 /WEB-INF/classes 폴더에 저장
 	
 	2) web.xml에 서블릿 클래스를 설정
 	3) Tomcat 실행
 	4) 웹 브라우저에서 확인
 */
public class NowServlet extends HttpServlet {
	
	//doGet() : 서블릿이 GET 방식의 요청을 처리하겠다.
	//HttpServletRequest : JSP의 request 기본 객체
	//HttpServletResponse : JSP의 response 기본 객체
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//request : 웹 브라우저의 요청 정보를 읽어옴
		//response : 서버의 응답을 전송할 수 있음
		//응답을 전송하려면 응답의 컨텐츠 타입을 UTF-8로 지정해야 함.
		resp.setContentType("text/html;charset=UTF-8");
		
		//println() 메서드에 전달한 데이터는 웹 브라우저에 전송되어 화면에 표시됨.
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head><title>현재시간</title></head>");
		out.println("<body>");
		out.println("현재 시간은?");
		out.println(new Date());
		out.println("입니다.");
		out.println("</body></html>");
		
	}
	
}
