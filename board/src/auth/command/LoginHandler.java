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
	//문자를 상수처럼 사용 -> 상수 : 값이 변하지 않음
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
			
			//java와 jsp 간에 errors 맵을 공유
			req.setAttribute("errors", errors);
			
			//아이디 값이 비어있다면..
			if(memberid==null || memberid.isEmpty()) {
				errors.put("memberid", Boolean.TRUE);
			}
			//비밀번호 값이 비어있다면..
			if(password==null || password.isEmpty()) {
				errors.put("password", Boolean.TRUE);
			}
			//오류가 비어있지 않다면.. -> 오류가 있다면
			if(!errors.isEmpty()) {
				return FORM_VIEW;
			}
			
			//session.setAttribute : java에서 불가능
			try {
				//비밀번호가 일치하는 회원이 있다면..
				User user = loginService.login(memberid, password);
				
				//req.getSession() : 세션을 가져옴 -> 로그인 처리
				req.getSession().setAttribute("authUser", user); //이게 중요
				/*
				 index.jsp
				 - jsp에서 제공하는 기본 객체 : 1.page, 2.request, 3.session, 4.application
				 index.jsp에 authUser를 썼어.
				 기본 객체에서 authUser를 찾아요, page? x request? x session o ~~
				 
				 User는 2가지 필드를 가지고 있는 vo
				 ${authUser.memberid} -> a001 
				 ${authUser.name}	  -> 김은대
				 */
				System.out.println("req.getContextPath() : " + req.getContextPath());
				resp.sendRedirect(req.getContextPath() + "/index.jsp");
				
				//위에서 sendRedirect()로 보내버리므로, 아래 코드는 실행하지 않는다.
//				for(int i=0; i<100; i++) {
//					System.out.println(i);
//				}
				//아니 실행을 한다?
				
				return null; //혹시 모르니 return 작성
				
			}catch(LoginFailException e) { //loginService.login() => 아이디/비번 문제발생시 
				errors.put("idOrPwNotMatch", Boolean.TRUE);
				return FORM_VIEW;
			}
			
			
		}else { //SSEM! GET, POST 방식이 아닌 방식이 있나요? 네엥 --> 멀티파트 
			return null;			
		}
			
		
	}
	
}
