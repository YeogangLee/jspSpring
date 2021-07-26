package chapter17;

import java.awt.print.Printable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//���� Ŭ������ �����Ϸ��� HttpServlet Ŭ������ ��ӹ޾ƾ� �Ѵ�.
/*
	<����>
	- jsp�� ������ ���� ǥ��, java�� �� ���ø����̼� ����
	- ���� vs jsp => jsp�� �� ������
	    �׷��� jsp�� ����� ������ �� ����? -> ���� ���α׷��� ������ 
	- MVC ���Ͽ��� ���� ��� �ڵ�� �����ϴ� ��찡 �ִ�.
 
 	<���� ����>
 	1) ���� �Ծ࿡ ���� �ڹ� �ڵ� �ۼ�
 	- �Ʒ��� ��Ŭ�������� �˾Ƽ� �����ϴ� �κ�
 	1-2) �ڹ� �ڵ� ������ (Ŭ���� ���� ����)
 	1-3) Ŭ���� ������ /WEB-INF/classes ������ ����
 	
 	2) web.xml�� ���� Ŭ������ ����
 	3) Tomcat ����
 	4) �� ���������� Ȯ��
 */
public class NowServlet extends HttpServlet {
	
	//doGet() : ������ GET ����� ��û�� ó���ϰڴ�.
	//HttpServletRequest : JSP�� request �⺻ ��ü
	//HttpServletResponse : JSP�� response �⺻ ��ü
	
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
