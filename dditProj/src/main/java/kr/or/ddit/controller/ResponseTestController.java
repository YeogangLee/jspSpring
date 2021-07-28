package kr.or.ddit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.vo.Member;

@Controller
public class ResponseTestController {

	private static final Logger logger = LoggerFactory.getLogger(BookController_clear.class);
	
	//1. return타입이 void라면.. 요청 경로(/board/goHome0101)와 동일한 뷰(/goHome0101.jsp)를 가리킴
	//현재 forward방식 이용 중
	@RequestMapping(value="/board/goHome0101", method=RequestMethod.GET)
	public void home010() { //return타입이 String이 아니라 void일 때 어떻게 될까
		logger.info("home0101");
	}
	
	//2. String 타입
	//뷰 파일의 경로와 파일 이름을 나타내기 위해 사용
	@RequestMapping(value="/board/goHome0201", method=RequestMethod.GET)
	public String home0201() {
		logger.info("home0201");
		return "board/home0201"; //forward
	}
	
	//3. redirect:
	//반환값이 redirect:로 시작하면 리다이렉트 방식으로 처리(다른 URI 요청)
	@RequestMapping(value="/board/goHome0204", method=RequestMethod.GET)
	public String home0204() {
		logger.info("home0204");
		return "redirect:/board/goHome0205";
	}
	
	//3-2.
	@RequestMapping(value="/board/goHome0205")
	public String home0205() { //t.void로 받아도 되지 않을까?
		logger.info("home0205");
		return "board/home0205";
	}
	
	//4. 자바빈즈 클래스 타입
	//JSON 객체 타입의 데이터를 만들어서 반환하는 용도로 사용됨
	//JSON 형식 : [{"id":"a001", "name":"김은대"}]
	
//	@ResponseBody
/*
 	 @ 있을 때 jsp에서 출력 : ${member} -> Member [userName=김은대, password=1234]
 	 @ 없을 때 jsp에서 출력 : ${member} -> Member [userName=김은대, password=1234]
 	 
	 @ResponseBody 어노테이션이 없더라도 결과가 동일한 이유
	-> 반환값이 객체 타입이면 JSON 타입으로 자동 변환됨.
	
	그래도 반환값이 JSON타입이라면
	분명하게 @ResponseBody 어노테이션 사용을 권장 
 */
	@RequestMapping(value="/board/goHome0301", method=RequestMethod.GET)
	public ModelAndView home0301() {
		Member member = new Member();
		ModelAndView mav = new ModelAndView();
		
		member.setUserName("김은대");
		member.setPassword("1234");
		
		mav.addObject("member", member);
		mav.setViewName("board/home0301");
		
		return mav;
	}
	
	//5. 컬렉션 List 타입
	//JSON 객체 배열 타입의 데이터를 만들어 반환하는 용도로 사용함
	@ResponseBody
	@RequestMapping(value="/board/goHome0401", method=RequestMethod.GET)
	public ModelAndView home0401(ModelAndView mav) {
		logger.info("home0401");
		
		List<Member> list = new ArrayList<Member>();
		Member member = new Member();
		list.add(member);
		Member member2 = new Member();
		list.add(member2);
		
		logger.info("객체 비교 == : " + (member == member2));				//객체 비교 : false
		logger.info("값 비교 == : " + (member.getUserName() == member2.getUserName()));				//객체 비교 : false
		logger.info("객체 비교 equals() : " + (member.equals(member2)));	//객체끼리 비교하고 있으므로, equals()를 사용해도 false
		logger.info("값 비교 equals() : " + (member.getUserName().equals(member2.getUserName())));	//값 비교 : true
		
		mav.addObject("list", list);
		mav.setViewName("board/home0401"); //forward 방식
		return mav;
		
//		return list; //ajax에서 이걸 받아서 사용 가능, 지금은 하지 않지만..
	}
	
	//6. 컬렉션 Map 타입
	//Map 형태의 컬렉션 자료를 JSON 객체 타입의 데이터로 만들어서 반환하는 용도로 사용
	@ResponseBody
	@RequestMapping(value="/board/goHome0501")
	//ajax 사용 시 ajax의 경로를 위와 같이 사용한다
	public ModelAndView home0501(ModelAndView mav) {
		logger.info("home0501");
		Map<String, Member> map = new HashMap<String, Member>();
		//HashMap의 제너릭 안에 아무것도 없어도 된다 : HashMap<>
		//하지만 자바 1.6? 1.8? 에서는 <>지원안한다.. 이런식의 오류 발생
		
		Member member = new Member();
		map.put("key1", member);
		Member member2 = new Member();
		map.put("key2", member2);
		
		mav.addObject("map", map);
		mav.setViewName("board/home0501");
		
		return mav;
		//jsp 출력 
		//${map} -> {key1=Member [userName=김은대, password=1234], key2=Member [userName=김은대, password=1234]} 
	}
	
	//7. ResponseEntity<Void>타입
	//response할 때 Http 헤더 정보와 내용을 가공하는 용도로 사용
	//심화 : jsp에서 응답 상태값을 볼 거다.
	@RequestMapping(value="/board/goHome0601", method=RequestMethod.GET)
	public String home0601(Model model) {
		logger.info("home0601");
		model.addAttribute("status", new ResponseEntity<Void>(HttpStatus.OK));
		
		return "board/home0601";
	}
	
	//8. ResponseEntity<String> 타입
	//response할 때 Http 헤더 정보와 문자열 데이터를 전달하는 용도로 사용
	//SUCCESS 메시지와 200 OK 상태코드를 전송
	@RequestMapping(value="/board/goHome0701", method=RequestMethod.GET)
	public String home0701(Model model) {
		logger.info("home0701");
		//SUCCESS를 같이 보내주므로 제너릭 안에 String을 넣어준다.
		model.addAttribute("statusMessage", new ResponseEntity<String>("SUCCESS", HttpStatus.OK));
		
		return "board/home0701";
		//jsp출력
		//${statusMessage} -> <200 OK OK,SUCCESS,[]>
	}
	
	//9. ResponseEntity<자바빈즈 클래스> 타입
	//response 할 때 Http 헤더 정보와 객체 데이터를 전달하는 용도로 사용
	//객체의 JSON 타입의 데이터와 200 OK 상태코드를 전송
	//JSON 타입을 이용했으면 반사적으로 @ResponseBody 어노테이션 이용
	//??
/*
	@ResponseBody
	board/home08	--> 이건 여기 메서드에서 return해준 jsp 파일 경로인데 ...
	jsp 결과가 addAttr..해주는 값에 상관없이, 출력하는 표현식 개수에 상관없이 이렇게 뜬다
	
	X @ResponseBody X 
	Member [userName=김은대, password=1234]
	<200 OK OK,Member [userName=김은대, password=1234],[]> 
 */
	@ResponseBody
	@RequestMapping(value="/board/goHome08", method=RequestMethod.GET)
	public String home08(Model model) {
		logger.info("home08");
		Member member = new Member();
		
		model.addAttribute("member", member);
		model.addAttribute("member2", new ResponseEntity<Member>(member, HttpStatus.OK));
		return "board/home08";
	}
	
}
