package member.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.vo.User;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class ChangePasswordHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/changePwdForm.jsp";
	private static ChangePasswordService changePwdSvc = new ChangePasswordService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return FORM_VIEW;
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
		}else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//req.getSession() : ���� ����
		User user = (User)req.getSession().getAttribute("authUser");
		
		String curPwd = req.getParameter("curPwd");
		String newPwd = req.getParameter("newPwd");
		
		System.out.println("curPwd : " + curPwd);
		System.out.println("newPwd : " + newPwd);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		//���� ��й�ȣ ���� ������...
		if(curPwd==null || curPwd.isEmpty()) {
			errors.put("curPwd", Boolean.TRUE);
		}
		//�ű� ��й�ȣ ���� ������...
		if(newPwd==null || newPwd.isEmpty()) {
			errors.put("newPwd", Boolean.TRUE);
		}
		//������ �߻��Ǿ��ٸ�... changePwdForm.jsp�� �̵�
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			//������ ���ٸ�..
			//���񽺿��� ��й�ȣ ���� ��� ����
			//userid => ���ǿ��� ��� ���� �ǰ���, �׷��� java�� jsp�� �ƴ϶� ���ǰ�ü ����x -> req ��ü �̿�
			changePwdSvc.changePassword(user.getMemberid(), curPwd, newPwd);
			return "/WEB-INF/view/changePwdSuccess.jsp";
			
		}catch (MemberNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
			
		}catch (Exception e) {
			errors.put("badCurPwd", Boolean.TRUE);
			return FORM_VIEW;
		}
			
	}
}