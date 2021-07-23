package kr.or.ddit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.vo.PointVO;

@Controller
public class BookController_my {
	
	@RequestMapping(value="/diary/title", method=RequestMethod.GET)
	public ModelAndView viewDiary() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("diary/title");
		mav.addObject("date", "2021년 7월 23일 금요일");
		
		return mav;
	}
	
	@RequestMapping("/detail")
	public String detailDiary(@RequestParam String date, Model model) {
		System.out.println(date);
		
		String text = "오늘은 지각을 했다.\n그래서 오늘은 정말 야자를 해야겠고, 그리고 오래 해야하겠구나 생각했다.";
		model.addAttribute("text", text);
		model.addAttribute("date", date);
		return "diary/detail";
	}

	//3.
//	@RequestMapping("/gugu_result")
//	public String gugu_result(@RequestParam int dan, Model model) {
//		
//		String result = "";
//		for(int i=1; i<=9; i++) {
//			result += dan + " x " + i +" = " + dan * i + "<br/>";
//		}
//		
//		//mav를 사용했다면 아래 코드가 이렇게 -> mav.addObject("result", result);
//		model.addAttribute("dan", dan);
//		model.addAttribute("result", result);
//		
//		return "book/gugu_result";
//	}
	
	
	//성적 계산
//	@RequestMapping("/point")
//	public String point() {
//		return "book/point";
//	}
	
	//성적 계산 결과
	/*
	 form 데이터의 전달 방법
	 
	 1) RequestParam 어노테이션 
	 2) ModelAttribute 어노테이션 : 모델클래스(VO)로 한번에 전달 
	 
	 */
//	@RequestMapping("/point_result")
//	public String point_result(@ModelAttribute PointVO pointVo, Model model) {
//		
//		pointVo.setTotal(pointVo.getKor() + pointVo.getEng() + pointVo.getMat());
//		
//		String average = String.format("%.2f", pointVo.getTotal()/3.0);
//		pointVo.setAverage(Double.parseDouble(average));
//		model.addAttribute("pointVo", pointVo);
//		
//		return "book/point_result";
//		
//	}
	
	
}
