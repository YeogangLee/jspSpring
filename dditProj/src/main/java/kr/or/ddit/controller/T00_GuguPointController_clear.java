package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.vo.PointVO;

@Controller
public class T00_GuguPointController_clear {

	//1. ModelAndView
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create() {
		// @Autowired 어노테이션 사용 불가
		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/create");
		mav.addObject("message", "환영합니다.");
		
		return mav;
	}
		
	//2. JSP 리턴, ModelAndView X
	@RequestMapping(value="/gugu", method=RequestMethod.GET)
	public String gugu() {
		return "book/gugu"; // /WEB-INF/views/book/gugu.jsp
	}
	
	//3. Model
	@RequestMapping("/gugu_result")
	public String gugu_result(@RequestParam(defaultValue="3") int dan, Model model) {
		
		String result = "";
		for(int i=1; i<=9; i++) {
			result += dan + " x " + i +" = " + dan * i + "<br/>";
		}
		
		model.addAttribute("dan", dan);
		model.addAttribute("result", result);	// mav 이용 --> mav.addObject("result", result);
		return "book/gugu_result";
	}
		
	//성적 계산
	@RequestMapping("/point")
	public String point() {
		return "book/point";
	}
	
	//성적 계산 결과
	//파라미터 : 자바 빈 클래스
	@RequestMapping("/point_result")
	public String point_result(@ModelAttribute PointVO pointVo, Model model) {
		pointVo.setTotal(pointVo.getKor() + pointVo.getEng() + pointVo.getMat());
		
		String average = String.format("%.2f", pointVo.getTotal()/3.0);
		pointVo.setAverage(Double.parseDouble(average));
		model.addAttribute("pointVo", pointVo); //이게 중요 ******** 이 이름으로 jsp로 간다는 게 중요
		
		return "book/point_result";
		
	}
	
}
