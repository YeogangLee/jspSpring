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
		
		//req.getSession() : 세션 시작
		User user = (User)req.getSession().getAttribute("authUser");
		
		String curPwd = req.getParameter("curPwd");
		String newPwd = req.getParameter("newPwd");
		
		System.out.println("curPwd : " + curPwd);
		System.out.println("newPwd : " + newPwd);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		//현재 비밀번호 값이 없으면...
		if(curPwd==null || curPwd.isEmpty()) {
			errors.put("curPwd", Boolean.TRUE);
		}
		//신규 비밀번호 값이 없으면...
		if(newPwd==null || newPwd.isEmpty()) {
			errors.put("newPwd", Boolean.TRUE);
		}
		//오류가 발생되었다면... changePwdForm.jsp로 이동
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			//오류가 없다면..
			//서비스에서 비밀번호 변경 기능 수행
			//userid => 세션에서 들고 오면 되겠죠, 그런데 java는 jsp가 아니라 세션객체 제공x -> req 객체 이용
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
