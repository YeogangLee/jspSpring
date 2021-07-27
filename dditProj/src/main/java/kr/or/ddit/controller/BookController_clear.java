package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.PointVO;

//@Controller
public class BookController_clear {

	private static final Logger logger = LoggerFactory.getLogger(BookController_clear.class);
	
	//스프링 이전
	//private BookService bookService = BookService.getInstance();
	
	//스프링 방식 : 인터페이스(BookService)를 통한 의존성 주입 (DI : Dependency Injection)
	@Autowired
	BookService bookService;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/create");
		mav.addObject("message", "환영합니다.");
		
		return mav;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createSubmit(ModelAndView mav, @RequestParam Map<String, Object> map) {
		
		String resultRowCount = this.bookService.create(map);
		if(resultRowCount == null) {// insert 실패.
			mav.setViewName("redirect:/create");
		}else {
			mav.setViewName("redirect:/detail/" + map.get("bookId"));
		}
		
		return mav;
	}
	
	@RequestMapping("/detail/{bookId}")
	public ModelAndView detail(@PathVariable("bookId") int bookId, ModelAndView mav) {		
		Map<String, Object> map = new HashMap<>();
		map.put("bookId", bookId);
		
		Map<String, Object> detailMap = this.bookService.detail(map);
//		logger.debug("detailMap : " + detailMap);
//		System.out.println("detailMap : " + detailMap);
		
		mav.addObject("bookId", bookId);
		mav.addObject("data", detailMap);
		mav.setViewName("book/detail");
		
		return mav;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		
		List<Map<String, Object>> list = this.bookService.list(map);
 
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", list);
		mav.setViewName("book/list");
		
		if(map.containsKey("keyword")) {
			//keyword 파라미터가 있다면 뷰(list.jsp)에 파라미터 전달
			mav.addObject("keyword", map.get("keyword"));
		}
		
		return mav;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookService.detail(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap);
		mav.setViewName("/book/update");
		
		return mav; 
	}

	/**
	 * 페이지 이동 방법 2가지 - 07.26.월
	 * 1.redirect
	 * 2.forward
	 */
	@RequestMapping("/move")
	public String move() {
		//redirect : 데이터 전달 불가능, 파라미터만 전달, 주로 다른 메서드로 이동할 때 이용
		return "redirect:/mypage?id=a001&name=kimchulsu&age=23";
 	}
	
	@RequestMapping("/mypage")
	public String mypage(Model model, @RequestParam String id, @RequestParam String name, @RequestParam int age) {
		System.out.println("id : " + id);
		System.out.println("name : " + name);
		System.out.println("age : " + age);

		//model을 리턴하지 않아도 jsp 페이지에서 이 model을 공유해서 사용할 수 있다 -> forward방식을 이용하기 때문
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "book/mypage";	//forward
	}
	
	/**
	 * 스프링에서 요청 형태에 따른, 파라미터를 전달하는 3가지 방법 - 07.27.화 
	 * : 1.get - 2.post - 3."/경로/{변수명}" + @PathVariable("변수명") 
	 */
	//1.GET
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerByParameter(String userId, String password) {
		logger.info("registerByParameter에 왔다");
		logger.info("userId" + userId);
		logger.info("password" + password);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("password", password);
		
		return "book/success"; //forward
	}
	//3. /경로/{변수명} + @PathVariable("변수명") 자료형 변수명
	@RequestMapping(value="/register/{userId}", method=RequestMethod.GET)
	public String registerByPath(@PathVariable("userId") String userId) {
		logger.info("registerByPath");
		logger.info("userId : " + userId);
		
		return "book/success";
	}
	//2.POST
	@RequestMapping("/register02") //POST를 명시하지 않아도 정상적으로 동작되네..
	public String register02(String password, String userId, int coin) {
		logger.info("register02");
		logger.info("userId : " + userId);
		logger.info("password : " + password);
		logger.info("coin : " + coin);
		return "book/success";
	}
	
	
}
