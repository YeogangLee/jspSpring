package kr.or.ddit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.PointVO;

//스프링 프레임워크가 브라우저의 요청(request)을 받아들이는
//컨트롤러라고 인지하여 자바 빈(java bean)으로 등록해서 관리한다.
//bean = 객체, 인스턴스화 된 것
@Controller
public class BookController {

	//스프링 이전
//	private BookService bookService = BookService.getInstance();
	
	// 스프링 방식 : 인터페이스(BookService)를 통한 의존성 주입 (DI : Dependency Injection)
	@Autowired
	BookService bookService;
	
	/*
	 MVC pattern(model2 방식) : 웹 페이지와 java code를 분리
	 
	 1. Model : 데이터 처리 클래스(DAO, DTO), back end
	  - Data Access Object(DAO) : 실질적인 비즈니스 로직을 처리
	  - Data Transfer Object(DTO) : 데이터를 저장, 전달
	    VO(Value Object), bean(빈), TO(Table Object)
	 2. Controller : 프로그램의 흐름을 제어(servlet class)
	 3. View : 화면 처리 (jsp), front end
	 */
	
	//브라우저의 주소(URI)가 /create일 때 실행되는 자바 컨트롤러 메서드
	//RequestMapping 어노테이션 : 브라우저의 요청 URI 요청에 실행되는 자바 메소드 지정
	/*
		value 속성 : 요청 URI
		method 속성 : http 요청 메소드
		1) GET 메소드 : 데이터를 변경하지 않는 경우
		2) POST 메소드 : 데이터가 변경될 경우 
		ex. 현재 GET을 사용 -> 데이터를 변경하지 않구나~ 하고 이해
	 */
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
			mav.setViewName("redirect:/detail?bookId=" + map.get("bookId"));
		}
		
		return mav; 
	}
	
	/*
	 <프로젝트 파일 구성>
	 1. pom.xml
	   - 메이븐 프로젝트의 빌드 파일 
	   - C:\Users\PC-14\.m2\repository\javax\servlet\jsp\jsp-api\2.1 
	 2. src/main/java
	   - 자바 소스 경로
	 3. src/main/resources
	   - 리소스 파일 경로 (mybatis의 xml이 들어감)
	 4. WEB-INF/spring/root-context.xml
	   - 스프링 설정 파일, 스프링 자체를 설정함
	 5. WEB-INF/spring/appServlet/servlet-context.xml
	   - 스프링 웹 설정 파일
	 6. WEB-INF/views
	   - 뷰 파일 경로(jsp)
	 */

	//2. ModelAndView를 사용하지 않고 jsp를 리턴
	@RequestMapping(value="/gugu", method=RequestMethod.GET)
	public String gugu() {
		return "book/gugu"; // /WEB-INF/views/book/gugu.jsp
	}
	
	//action="/gugu_result", method=RequestMethod.POST 생략
	//name="dan"
	//3. Model 사용
	//아래 어노테이션에서 속성 사용 X, 바로 value이름만 넣기
	@RequestMapping("/gugu_result")
	public String gugu_result(@RequestParam(defaultValue="3") int dan, Model model) {
		
		String result = "";
		for(int i=1; i<=9; i++) {
			result += dan + " x " + i +" = " + dan * i + "<br/>";
		}
		
		//mav를 사용했다면 아래 코드가 이렇게 -> mav.addObject("result", result);
		model.addAttribute("dan", dan);
		model.addAttribute("result", result);
		return "book/gugu_result";
	}
	
	//위에서 @RequestMapping 어노테이션에 value를 써도 되고, 안 써도 되는 것을 확인했다.
	
	//성적 계산
	@RequestMapping("/point")
	public String point() {
		return "book/point";
	}
	
	//성적 계산 결과
	/*
	 form 데이터의 전달 방법
	 
	 1) RequestParam 어노테이션 
	 2) ModelAttribute 어노테이션 : 모델클래스(VO)로 한번에 전달 
	 
	 */
	@RequestMapping("/point_result")
	public String point_result(@ModelAttribute PointVO pointVo, Model model) {
		//point.jsp의 name속성이 pointVo에 들어온다.
		//그러면, PointVO의 total, average 빼고 전부 세팅된 것
		
		//total 필드에 총점 입력
		pointVo.setTotal(pointVo.getKor() + pointVo.getEng() + pointVo.getMat());
		
		//average 필드에 평균 입력(%.2f : 소수점 2자리의 실수 값)
		String average = String.format("%.2f", pointVo.getTotal()/3.0);
		pointVo.setAverage(Double.parseDouble(average));
		model.addAttribute("pointVo", pointVo); //이게 중요 ******** 이 이름으로 jsp로 간다는 게 중요
		//--> 데이터를 전달하고 있다 : 포워드 방식
		
		return "book/point_result";
		
		/*
		 포인트 
		 : jsp에서 name값이 여러 개일때 VO로 "싹 한꺼번에" 받을 수 있다.
		      일단 넣어놓고, 그 아래에서 가공을 하고, 결과가 담긴 VO를 리턴할 수 있다. 
		 */
	}
	
	@RequestMapping("/move")
	public String move() {
//		return "redirect:/gugu";
		return "redirect:/mypage?id=a001&name=kimchulsu&age=23";
 	}
	
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
	
	
}