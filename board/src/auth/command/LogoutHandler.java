package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

public class LogoutHandler implements CommandHandler {

	//로그아웃 구현
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//세션이 시작됨
		HttpSession session = req.getSession();
		System.out.println("authUser : " + session.getAttribute("authUser"));
		// authUser : auth.vo.User@3dd91d1 ==> 세션 객체 있음
		
		//세션이 있다면... 로그아웃
		if(session != null) {
			session.invalidate();
			//세션에 담긴 모든 로그인 정보가 사라진다.
			//그 다음에 아래에서 index.jsp로 간다.
		}
		resp.sendRedirect(req.getContextPath() + "/index.jsp");
		
		return null;
	}
	
}
