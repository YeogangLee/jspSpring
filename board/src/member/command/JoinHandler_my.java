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

	//ȸ������ �⺻ form URI
	private static final String FORM_VIEW = "/WEB-INF/view/joinForm_my.jsp";
	JoinService_my joinService = new JoinService_my();
	//��������  joinService : �޼��尡 ���� ���� ��, �� �޼��� ���ο��� ���񽺸� �������� �ʰ� ��� ����
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//req.getMethod() : ��û��  post����, get���� �Ǵ�
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return FORM_VIEW;
			
		}else if (req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req, resp);
			
		}else {
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) {
		
		//Member vo�� ������� �ʾ��� ��  Map�� �Ѿ�� �Ķ���͵��� put���ش�.
//		System.out.println("memberid : " + req.getParameter("memberid"));
//		System.out.println("name : " + req.getParameter("name"));
//		System.out.println("password : " + req.getParameter("password"));
//		System.out.println("confirmPassword : " + req.getParameter("confirmPassword"));
		
		//Member vo ���
		JoinRequest_my joinReq = new JoinRequest_my();
		joinReq.setMemberid(req.getParameter("memberid"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setConfirmPassword(req.getParameter("confirmPassword"));
		
		System.out.println("joinReq.toString() : " + joinReq.toString());

		Map<String, Boolean> errors = new HashMap<>();

		//Handler�� jsp�� �����ؼ� ����ϴ� request��ü�� errors�� ���Ե�
		req.setAttribute("errors", errors); //�߿�
		
		joinReq.validate(errors);
		
		//errors�� ��������� ��� �ʵ忡 ���� ����ְ�,
		//password, confirmPassword �� ���� ���ٴ� �ǹ�
		//!�� ����ִ� �� �´�.
		if(!errors.isEmpty()) { //�׷��� ������� �ʴٸ�, ������ �߻��� ��
			//joinForm.jsp�� ����. errors�� ���� ������, �׷��� joinForm.jsp������ errors ��� ����
			return FORM_VIEW;
		}
		
		try {
			//member���̺� �ش� ������ �߰� ��� ����
			joinService.join(joinReq);
			return "/WEB-INF/view/joinSuccess.jsp";
			
			//���� �����ϼ��� �����޼����� �߻�ȭ�ȴ�.
			//����� ���� ������ Dupl...��  RuntimeException Ŭ������ ��ӹ޾Ҵ�. 
		}catch(DuplicateIdException ex) { 
			//������ ����� ���̵� �ִٸ� joinForm.jsp�� �ǵ��� ����.
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
			
		}
		
	}
	
}
