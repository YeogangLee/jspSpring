package chapter17;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//���� 3.0. tomcat7 or tomcat8 �� ���Ĺ������� ��� ����
//tomcat���� WebServlet ������̼��� ����� Ŭ������ �˻��ؼ� �������� �ڵ����� ���
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//request : �� �������� ��û ������ �о��
		//response : ������ ������ ������ �� ����
		//������ �����Ϸ��� ������ ������ Ÿ���� UTF-8�� �����ؾ� ��.
		resp.setContentType("text/html;charset=UTF-8");
		
		//println() �޼��忡 ������ �����ʹ� �� �������� ���۵Ǿ� ȭ�鿡 ǥ�õ�.
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head><title>����ð�</title></head>");
		out.println("<body>");
		out.println("���� �ð���?");
		out.println(new Date());
		out.println("�Դϴ�.");
		out.println("</body></html>");
		
	}
}
