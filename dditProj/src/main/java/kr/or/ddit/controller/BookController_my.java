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
public class BookController_my {
	
	@RequestMapping(value="/diary/title", method=RequestMethod.GET)
	public ModelAndView viewDiary() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("diary/title");
		mav.addObject("date", "2021년 7월 24일 토요일");
		
		return mav;
	}
	
	@RequestMapping(value="/detail")
	public String detailDiary(@RequestParam String date, Model model) {
		String text = "오늘은 토요일이다. 현이를 볼 수 있을까? 근데 째끔 피곤하다 ...";
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
	
}
