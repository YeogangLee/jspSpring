package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.vo.PointVO;

//@Controller
public class T00_GuguPointController {
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
	 t.
	 7. WEB-INF/web.xml
	   - 서블릿을 등록하고, 필터를 등록,매핑하는 파일
 */
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
	
	//1. ModelAndView
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
	
	//2. JSP 리턴, ModelAndView X
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
	
}
