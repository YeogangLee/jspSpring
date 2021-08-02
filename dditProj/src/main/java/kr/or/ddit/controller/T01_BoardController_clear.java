package kr.or.ddit.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board") //클래스 레벨 요청 경로 지정
public class T01_BoardController_clear {
	
	private static final Logger logger = LoggerFactory.getLogger(T01_BoardController_clear.class);

	@RequestMapping(value="/register")
	public String registerForm() {
		log.info("registerForm");
		return "board/register";
	}
	
	@RequestMapping("/modify")
	public String modifyForm() {
		logger.debug("modify");

		return "board/modify";
	}
	
	@RequestMapping("/detail/{boardNo}")
	public ModelAndView detail(@PathVariable("boardNo") int boardNo, ModelAndView mav) {
		log.info("boardNo : " + boardNo);
		
		mav.addObject("boardNo", boardNo);
		mav.setViewName("board/detail");
		
		return mav;
	}
	
}
