package kr.or.ddit.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;

//스프링 프레임워크가 브라우저의 요청(request)을 받아들이는
//컨트롤러라고 인지하여 자바 빈(java bean)으로 등록해서 관리한다.
//bean = 객체, 인스턴스화 된 것
@Controller
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	//스프링 이전
	//private BookService bookService = BookService.getInstance();
	
	//스프링 방식 : 인터페이스(BookService)를 통한 의존성 주입 (DI : Dependency Injection)
	@Autowired
	BookService bookService;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create() {
		//ModelAndView
		//: 컨트롤러가 반환할 데이터를 담당하는 모델(Model), 데이터를 담는 그릇 역할
		//  화면을 담당하는 View의 경로(board/list(.jsp)처럼)
		
		//Autowire 어노테이션 사용 불가
		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/create"); //creaet(jsp) 위에 book폴더를 만들어줘야 한다.
		mav.addObject("message", "환영합니다."); //jsp 파일에서 출력 가능
		
		return mav;
	}
	
	//HTTP 파라미터 --> title=피터팬&category=소설&price=10000
	//       map --> {"title" : "피터팬"}, {"category" : "소설"}, {"price":10000}
	//RequestParam 어노테이션을 통해 HTTP 파라미터를 map변수에 자동으로 바인딩함
	//HTTP 파라미터 : 웹 브라우저에서 서버로 전달하는 데이터.
	
	//# Tip 
	//스프링은 객체 타입이나 스칼라 타입(일반 변수)일 경우
	//특별한 어노테이션 없이도 HTTP 파라미터를 자바 메소드의 파라미터로 변환하여 컨트롤러 메소드를 호출한다
	//하지만 Map 타입은 예외라서, @RequestParam 어노테이션이 필요하다.
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createSubmit(ModelAndView mav, @RequestParam Map<String, Object> map) {
		
		//insert 성공 1, 실패 null
		String bookId = this.bookService.create(map); //의존성 주입 이후에 보이는 create() 메서드
		if(bookId == null) {// insert 실패.
			//다시 데이터를 입력받아야 하므로, 생성 화면으로 redirect 한다. 
			/*
			 redirect
			 - uri 변경
			 - 데이터 전달 불가능, 파라미터만 전달
			 - 주로 컨트롤러의 다른 method로 이동 시 주로 사용됨.
			 - 스프링은 뷰 파일을 찾아가는 것(forward)이 아니라, 웹 페이지의 주소를 변경한다.
			 */
			mav.setViewName("redirect:/create");
			
		}else {
			//추가된 책의 id : book테이블의 book_id 컬럼값
			//AsIs
//			mav.setViewName("redirect:/detail?bookId=" + map.get("bookId"));
			//ToBe
			//book.xml => selectKey order="BEFORE" keyProperty="bookId" resultType="int"
			mav.setViewName("redirect:/detail/" + map.get("bookId"));
		}
		
		return mav; 
	}
	
	//경로 패턴 지정
	//http://localhost:8090/detail/7
	@RequestMapping("/detail/{bookId}")
	public ModelAndView detail(@PathVariable("bookId") int bookId, ModelAndView mav) {
//		logger.debug("bookId : " + bookId);
//		System.out.println("bookId : " + bookId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("bookId", bookId); //이게 아래에 내려가면 안된다, 아래에서 map을 던져야 하므로.
		
		Map<String, Object> detailMap = this.bookService.detail(map);
//		logger.debug("detailMap : " + detailMap);
//		System.out.println("detailMap : " + detailMap);
		
		mav.addObject("bookId", bookId);
		mav.addObject("data", detailMap);
		mav.setViewName("book/detail");
		
		return mav;
	}
	
	/*
		- list() 메서드가 호출되는 2가지 경우
		1. detail.jsp 페이지에서 목록으로 클릭 시
		2. list.jsp 페이지에서 키워드 입력 후 검색 버튼 클릭 시
		 
		1번의 경우 넘어오는 파라미터가 없다. -> 하지만 에러가 발생하지 않는다.
		(스프링의 특징 중 하나라고 예전에 sem한테 들은 것 같다.)
		 
		파라미터가 없을 때 실행하는 select와
		파라미터를 where의 조건값으로 받아서 사용하는 select를
		동일한 쿼리(select_list)를 이용해 동적 쿼리(<if test="조건">)로 처리해주고 있다.
		
		아래 메서드에 req 파라미터 추가하면 sem이 어제 설명한 기능 가능할지도 모른다.
		HttpServletRequest req 
	 */
	//form 태그의 기본 HTTP 메소드는 GET. 검색 버튼 클릭 -> /list?keyword=검색어
	//map => {"keyword", "검색어"}
	@RequestMapping(value="/list", method=RequestMethod.GET)
	//
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		System.out.println("sysout map : " + map);
		logger.debug("logger map : " + map);
		
		List<Map<String, Object>> list = this.bookService.list(map);

//		@Autowired 사용 불가능 -> 1.파라미터에 선언 / 2.new키워드 이용 
		ModelAndView mav = new ModelAndView();
		//데이터 뷰를 전달할 수 있도록 mav객체에 넣음
		mav.addObject("data", list);
		mav.setViewName("book/list"); //forward
		//list.jsp에 keyword를 추가
		//목록 페이지에서 keyword HTTP 파라미터가 있을 때..
		if(map.containsKey("keyword")) {
			//파라미터가 있다면 뷰(list.jsp)에 keyword 전달
			mav.addObject("keyword", map.get("keyword"));
		}
		
		return mav;
		
	}
	
	/* 책 수정 GET */
	// /update?bookId=4 => String bookId, @RequestParam String bookId
	// /update/4		=> @RequestMapping("/update/{bookId}")
	//					   @PathVariable("bookId") String bookId
	
	//map => {"bookId" : "4"}
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		//map => {"bookId":"5"}
		Map<String, Object> detailMap = this.bookService.detail(map);
		//detailMap => {PRICE=6000, CATEGORY=요리책, BOOKID=5, TITLE=밥}
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap);
		mav.setViewName("/book/update"); //forward
		
		return mav;
	}
	
	/* 책 수정 POST */
	//스프링은 http 메소드가 GET인지 POST인지 상관없이
	//RequestParam 어노테이션이 있으면 무조건 파라미터를 넣어준다.
	//파라미터 : bookId, title, category, price
	//map => {"bookId":1, "title":"제목 수정", "category":"IT", "price":"10000"}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ModelAndView updatePost(@RequestParam Map<String, Object> map, ModelAndView mav) {
		int result = this.bookService.edit(map);
		
		if(result > 0) {//업데이트 성공
			System.out.println("map.get(\"bookId\") : " + map.get("bookId"));
			System.out.println("map.get(\"bookId\").toString() : " + map.get("bookId").toString());
			String bookId = map.get("bookId").toString(); //toString 왜 사용하는 거지?
			mav.setViewName("redirect:/detail/" + bookId);
			
		}else {	//업데이트 실패
			//갱신 화면을 바로 다시 보여줌
			//방법1. 메서드 호출
			mav = this.update(map); //update()메서드로 바로 이동하는 것
			//방법2. redirect
//			mav.setViewName("redirect:/update");
		}
		return mav;
	}
	
	//HTTP 파라미터 => bookId=17 
	//RequestParam = map => {"bookId":"17"} 
	//삭제는 get방식으로 하지 않고 post방식, 왜냐하면 url이용해서 데이터 지워버리면 안되니까.
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView deletePost(@RequestParam Map<String, Object> map, ModelAndView mav) {
		int result = this.bookService.remove(map);
		
		if(result > 0) {//도서 삭제 성공
			//목록을 다시 보여준다.
			//방법1.forward
//			mav = this.list(map);
			//방법2.redirect 
			mav.setViewName("redirect:/list");
		}else {			//도서 삭제 실패
//			String bookId = map.get("map").toString(); 아래 자리에 bookId 이용하는 방법도 가능..
			mav.setViewName("redirect:/detail/" + map.get("map").toString());
		}
		
		return mav; 
	}
	
	//이미지 업로드
	//MultipartFile picture 변수명과 name속성값이 동일해야 한다.
	@RequestMapping(value="/registerFile01", method=RequestMethod.POST)
	public String registerFile01(MultipartFile picture) {
		logger.info("registerFile01");
		
		String uploadFolder = "D:\\upload";
		
		logger.info("originalName : " + picture.getOriginalFilename());
		logger.info("size : " + picture.getSize());
		logger.info("contentType : " + picture.getContentType());
		
		//uploadFolder : 파일 저장 경로 
		File saveFile = new File(uploadFolder, picture.getOriginalFilename());
		try {
			//transferTo()의 파라미터로는 java.io.File의 객체를 지정하면 된다.
			picture.transferTo(saveFile);
		}catch (Exception ex) {
			logger.info(ex.getMessage());
		}
		
		//앞에 폴더 만들어서 경로에 추가하면 좋겠다
		return "success";
	}
	
	/**
	 * 페이지 이동 방법 2가지 - 07.26.월
	 * 1.redirect
	 * 2.forward
	 */
	//1.redirect
	@RequestMapping("/move")
	public String move() {
		//redirect : 데이터 전달 불가능, 파라미터만 전달, 주로 다른 메서드로 이동할 때 이용
		return "redirect:/mypage?id=a001&name=kimchulsu&age=23";
 	}
	//2.forward
	@RequestMapping("/mypage")
	public String mypage(Model model, @RequestParam String id, @RequestParam String name, @RequestParam int age) {
		System.out.println("id : " + id);
		System.out.println("name : " + name);
		System.out.println("age : " + age);

		//model을 리턴하지 않아도 jsp 페이지에서 이 model을 공유해서 사용할 수 있다
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "book/mypage";
	}
	
	/**
	 * 스프링에서 요청 형태에 따른, 파라미터를 전달하는 3가지 방법 - 07.27.화 
	 * : 1.get - 2.post - 3."/경로/{변수명}" + @PathVariable("변수명") 
	 */
	//1. GET
	//register?userId=a001&amp;password=1234
	//RequestParam Map(String Object> map
	//URL 경로상의 쿼리 파라미터 정보로부터 요청 데이터를 각각 받을 수 있음
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
	
	//3. /경로/{변수명} + @PathVariable
	// /register/a001
	@RequestMapping(value="/register/{userId}", method=RequestMethod.GET)
	public String registerByPath(@PathVariable("userId") String userId) {
		logger.info("registerByPath");
		logger.info("userId : " + userId);
		
		return "book/success";
	}
	
	//2.POST
	//name => userId, password, coin
	@RequestMapping("/register02") //POST를 명시하지 않아도 정상적으로 동작되네..
	public String register02(String password, String userId, int coin) {
		logger.info("register02");
		logger.info("userId : " + userId);
		logger.info("password : " + password);
		logger.info("coin : " + coin);
		return "book/success";
	}

	
}
