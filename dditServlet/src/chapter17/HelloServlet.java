package chapter17;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿 3.0. tomcat7 or tomcat8 그 이후버전부터 사용 가능
//tomcat에서 WebServlet 어노테이션이 적용된 클래스를 검색해서 서블릿으로 자동으로 등록
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
	
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
