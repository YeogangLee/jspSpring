package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

public class LogoutHandler implements CommandHandler {

	//�α׾ƿ� ����
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//������ ���۵�
		HttpSession session = req.getSession();
		System.out.println("authUser : " + session.getAttribute("authUser"));
		// authUser : auth.vo.User@3dd91d1 ==> ���� ��ü ����
		
		//������ �ִٸ�... �α׾ƿ�
		if(session != null) {
			session.invalidate();
			//���ǿ� ��� ��� �α��� ������ �������.
			//�� ������ �Ʒ����� index.jsp�� ����.
		}
		resp.sendRedirect(req.getContextPath() + "/index.jsp");
		
		return null;
	}
	
}
