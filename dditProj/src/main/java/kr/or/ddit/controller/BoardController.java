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
public class BoardController {
	
	//쌤이 실무하다가 본 로그 방식... 학생들에게 도움을 주려고.
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	/*
	 <요청 경로 매핑>

	 - RequestMapping 어노테이션
	 - 요청 경로는 반드시 설정해야 하는 필수 정보
	 - 컨트롤러의 클래스 레벨과 메서드 레벨로 지정 가능
	 - 클래스 레벨로 요청 경로 지정 시, 메서드 레벨에서 지정한 경로의 기본 경로로 취급됨
	 - 클래스 레벨의 요청 경로(/board)에 메서드 레벨의 요청 경로(/register)를 덧붙인 형태가 최종 경로가 됨 
	 */
	
	@RequestMapping(value="/register")
	public String registerForm() {
		log.info("registerForm");
		return "board/register";
	}
	
	//속성이 1개(value)일 때는 속성명 생략 가능
	@RequestMapping("/modify")
	public String modifyForm() {
		logger.debug("modify");
		//view/board/modify.jsp => forward, jsp파일을 부르는 것
		return "board/modify";
	}
	/*
		http://localhost:8090/board/detail?boardNo=7	(before, AsIs : 업그레이드 전) **많이 쓴다, 경로 패턴 매핑 방식
		http://localhost:8090/board/detail/7			(after, ToBe : 업그레이드 후)
		
		* 경로 패턴 매핑
		요청 경로를 동적으로 표현이 가능한 경로 패턴을 지정할 수 있다.
		"경로 변수"(@PathVariable)에 해당하는 값을 파라미터 변수에 설정할 수 있다.
	*/
	
	@RequestMapping("/detail/{boardNo}")
	public ModelAndView detail(@PathVariable("boardNo") int boardNo, ModelAndView mav) {
		log.info("boardNo : " + boardNo);
		
		mav.addObject("boardNo", boardNo);
		mav.setViewName("board/detail");
		
		return mav;
	}
	
}
