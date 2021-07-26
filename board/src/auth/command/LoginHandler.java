package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.vo.User;
import mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler {
	//���ڸ� ���ó�� ��� -> ��� : ���� ������ ����
	private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";
	private LoginService loginService = new LoginService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return FORM_VIEW;
			
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			String memberid = req.getParameter("memberid");
			String password = req.getParameter("password");
			
			System.out.println("memberid : " + memberid);
			System.out.println("password : " + password);
			
			Map<String, Boolean> errors = new HashMap<>();
			
			//java�� jsp ���� errors ���� ����
			req.setAttribute("errors", errors);
			
			//���̵� ���� ����ִٸ�..
			if(memberid==null || memberid.isEmpty()) {
				errors.put("memberid", Boolean.TRUE);
			}
			//��й�ȣ ���� ����ִٸ�..
			if(password==null || password.isEmpty()) {
				errors.put("password", Boolean.TRUE);
			}
			//������ ������� �ʴٸ�.. -> ������ �ִٸ�
			if(!errors.isEmpty()) {
				return FORM_VIEW;
			}
			
			//session.setAttribute : java���� �Ұ���
			try {
				//��й�ȣ�� ��ġ�ϴ� ȸ���� �ִٸ�..
				User user = loginService.login(memberid, password);
				
				//req.getSession() : ������ ������ -> �α��� ó��
				req.getSession().setAttribute("authUser", user); //�̰� �߿�
				/*
				 index.jsp
				 - jsp���� �����ϴ� �⺻ ��ü : 1.page, 2.request, 3.session, 4.application
				 index.jsp�� authUser�� ���.
				 �⺻ ��ü���� authUser�� ã�ƿ�, page? x request? x session o ~~
				 
				 User�� 2���� �ʵ带 ������ �ִ� vo
				 ${authUser.memberid} -> a001 
				 ${authUser.name}	  -> ������
				 */
				System.out.println("req.getContextPath() : " + req.getContextPath());
				resp.sendRedirect(req.getContextPath() + "/index.jsp");
				
				//������ sendRedirect()�� ���������Ƿ�, �Ʒ� �ڵ�� �������� �ʴ´�.
//				for(int i=0; i<100; i++) {
//					System.out.println(i);
//				}
				//�ƴ� ������ �Ѵ�?
				
				return null; //Ȥ�� �𸣴� return �ۼ�
				
			}catch(LoginFailException e) { //loginService.login() => ���̵�/��� �����߻��� 
				errors.put("idOrPwNotMatch", Boolean.TRUE);
				return FORM_VIEW;
			}
			
			
		}else { //SSEM! GET, POST ����� �ƴ� ����� �ֳ���? �׿� --> ��Ƽ��Ʈ 
			return null;			
		}
			
		
	}
	
}
