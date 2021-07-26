package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import member.service.JoinService;
import member.vo.JoinRequest;
import member.vo.Member;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {
	//회원가입 기본 Form URI
	private static final String FORM_VIEW = "/WEB-INF/view/joinForm.jsp";
	private JoinService joinService = new JoinService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return "/WEB-INF/view/joinForm.jsp";
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}else {
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		JoinRequest joinReq = new JoinRequest();
		joinReq.setMemberid(req.getParameter("memberid"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setConfirmPassword(req.getParameter("confirmPassword"));
		
		System.out.println("joinReq.toString() : " + joinReq.toString());
		
		Map<String, Boolean> errors = new HashMap<>();
		//Handler와 jsp가 공유해서 사용하는 request객체에 errors가 포함됨
		req.setAttribute("errors", errors);
		
		joinReq.validate(errors);
		
		//errors가 비어있으면 모든 필드에 값이 들어있고,
		//password랑 confirmPassword랑 값이 같다는 의미
		if(!errors.isEmpty()) {
			//joinForm.jsp로 리턴. errors가 공유됨
			return FORM_VIEW;
		}
		
		try {
			//member테이블에 해당 데이터를 입력 기능 구현
			joinService.join(joinReq);
			return "/WEB-INF/view/joinSuccess.jsp";
		}catch(DuplicateIdException e) {
			//기존의 사용자 아이디가 있다면 joinForm.jsp로 되돌아 감
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
	
}







