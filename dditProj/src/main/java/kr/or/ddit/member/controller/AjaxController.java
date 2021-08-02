package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.member.vo.MemberTableVO;
import kr.or.ddit.item.vo.MemberVO;
import kr.or.ddit.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class AjaxController {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

	@Autowired
	MemberService memberService;
	
	// a href = 요청 --> /item/ajax?id=a001&name=rasmus
	@RequestMapping(value="/ajax", method=RequestMethod.GET)
	public void ajax(String id, String name, @RequestParam Map<String, Object> map, Model model) {
		logger.info("id : " + id + " / name : " + name);
		logger.info("map : " + map.toString());
		
		model.addAttribute("map", map); //t.forward
	}
	
//	@ResponseBody <- 어노테이션의 기존 위치, 메서드 명의 return값에 작성해도 무관
	@RequestMapping(value="/useMap", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> useMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "a001");
		map.put("name", "김캡스");
		
		return map;
	}

	// @ResponseBody를 이용한 JSON 리턴
	// : pom.xml에서 jackson-databind에 대한 Dependency가 추가되어 있어야 한다.
	/*
		파라미터 VO타입 : @ModelAttribute
		return VO타입 : @ResponseBody  
	 */
	@RequestMapping(value="/useVO", method=RequestMethod.POST)
	public @ResponseBody MemberVO useVO() {
		MemberVO memberVo = new MemberVO();
		memberVo.setId("a001");
		memberVo.setName("김은대");
		
		return memberVo;
	}
	
//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public String login() {
//		return "member/login";	//forward
//	}
//	<=> 두 메서드의 기능은 동일하다.
	@RequestMapping("/login")
	public void login() {
		logger.info("login 메서드 내부");
	}
	
	@RequestMapping(value="/loginResult", method=RequestMethod.GET)
	public String loginResult(Model model, @ModelAttribute HashMap<String, Object> map) {
		logger.info("loginResult 메서드 내부");
		logger.info("model : " + model);
		logger.info("map : " + map);
		logger.info("RequestDispatcher GET");
		
//		return "redirect:/member/loginResult";
//		loginResultPost(id, pwd, map, model, session);
//		model.addAttribute("loginOk", "true");
		return "member/loginResult";
	}
	
	/* GET, POST 방식 둘 다 받는 방법
	  
		method를 지우시거나..
		@RequestMapping("/" )
		
		GET/POST를 둘다 선언해 주세요.
		@RequestMapping(value="/" , method = {RequestMethod.GET, RequestMethod.POST})
	 */
	// {"id" : id, "pwd" : pwd}
	@RequestMapping(value="/loginResult", method=RequestMethod.POST)
	public void loginResultPost(String id, String pwd, @RequestParam Map<String, Object> map, 
							Model model, HttpSession session) {
		logger.info("RequestDispatcher POST");
		String result = "";
		String loginOk = "false";
		
		MemberTableVO memberVo = new MemberTableVO();
		memberVo.setMemberid(id);
//		memberVo.setPassword(pwd); //사용X, select 쿼리문에서 사용하고 있지 않기 때문에...
		MemberTableVO memberVoDB = this.memberService.selectMember(memberVo);
		
		if(memberVoDB != null) { //회원 존재
			if(memberVoDB.getPassword().equals(pwd)) {
				result = "환영합니다 :D<br/>";
				loginOk = "true";
				
				//조건문을 통과했으므로 그냥 변수를 담아줘도 상관없지만,
				//확실한 값을 담아주기 위해, memberVoDB에서 get하기
//				session.setAttribute("memberid", memberVoDB.getMemberid());
//				session.setAttribute("name", memberVoDB.getName());
				
				//아니면 세션에 VO 통으로 넣기
				session.setAttribute("memberVoDB", memberVoDB);
				
			}else { result = "비밀번호 불일치<br/>"; }
			
			result += "확인 => vo값 : " 
					+ memberVoDB.getMemberid() + " : " + memberVoDB.getPassword() + "<br/>"
					+ "  파라미터값 : " + id + " : " + pwd;
			
		}else {
			result = "회원 존재X <br/>확인 => 파라미터값 : " + id + " : " + pwd;
		}
		
//		model.addAttribute("result", result);
//		model.addAttribute("loginOk", loginOk);
		
		session.setAttribute("result", result);
		session.setAttribute("loginOk", loginOk);
		
//		return "member/loginResult";
		
	}
	
	//로그아웃
	//로그아웃은 주로 GET방식을 사용
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {
		//세션 초기화 작업 수행
		this.memberService.logout(session); // login.jsp에서 생성된 session객체
		
		mav.setViewName("member/login"); //방법1.forward : 데이터 전달 가능
//		return "redirect:/member/login"; //방법2.redirect : 데이터 전달 X, 파라미터만 담을 수 있다.
		
		mav.addObject("msg", "logout");
		
		return mav;
	}
	
	
}
