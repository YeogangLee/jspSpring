package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import member.service.JoinService_my;
import member.vo.JoinRequest_my;
import member.vo.Member;
import mvc.command.CommandHandler;

public class JoinHandler_my implements CommandHandler {

	//회원가입 기본 form URI
	private static final String FORM_VIEW = "/WEB-INF/view/joinForm_my.jsp";
	JoinService_my joinService = new JoinService_my();
	//전역변수  joinService : 메서드가 여러 개일 때, 각 메서드 내부에서 서비스를 생성하지 않고 사용 가능
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//req.getMethod() : 요청이  post인지, get인지 판단
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return FORM_VIEW;
			
		}else if (req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req, resp);
			
		}else {
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) {
		
		//Member vo를 사용하지 않았을 시  Map에 넘어온 파라미터들을 put해준다.
//		System.out.println("memberid : " + req.getParameter("memberid"));
//		System.out.println("name : " + req.getParameter("name"));
//		System.out.println("password : " + req.getParameter("password"));
//		System.out.println("confirmPassword : " + req.getParameter("confirmPassword"));
		
		//Member vo 사용
		JoinRequest_my joinReq = new JoinRequest_my();
		joinReq.setMemberid(req.getParameter("memberid"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setConfirmPassword(req.getParameter("confirmPassword"));
		
		System.out.println("joinReq.toString() : " + joinReq.toString());

		Map<String, Boolean> errors = new HashMap<>();

		//Handler와 jsp가 공유해서 사용하는 request객체에 errors가 포함됨
		req.setAttribute("errors", errors); //중요
		
		joinReq.validate(errors);
		
		//errors가 비어있으면 모든 필드에 값이 들어있고,
		//password, confirmPassword 두 값이 같다는 의미
		//!가 들어있는 게 맞다.
		if(!errors.isEmpty()) { //그래서 비어있지 않다면, 오류가 발생한 것
			//joinForm.jsp로 리턴. errors가 같이 공유됨, 그래서 joinForm.jsp에서도 errors 사용 가능
			return FORM_VIEW;
		}
		
		try {
			//member테이블에 해당 데이터 추가 기능 구현
			joinService.join(joinReq);
			return "/WEB-INF/view/joinSuccess.jsp";
			
			//상위 오류일수록 오류메세지가 추상화된다.
			//사용자 정의 예외인 Dupl...은  RuntimeException 클래스를 상속받았다. 
		}catch(DuplicateIdException ex) { 
			//기존의 사용자 아이디가 있다면 joinForm.jsp로 되돌아 간다.
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
			
		}
		
	}
	
}
