package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;

@Controller
public class T02_BookController_clear {

	private static final Logger logger = LoggerFactory.getLogger(T02_BookController_clear.class);
	
	//스프링 이전
	//private BookService bookService = BookService.getInstance();
	
	//스프링
	@Autowired
	BookService bookService;
	
	//책 등록
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createSubmit(ModelAndView mav, @RequestParam Map<String, Object> map) {
		String bookId = this.bookService.create(map);
		
		if(bookId == null) {// insert 실패.
			mav.setViewName("redirect:/create");
		}else {
			mav.setViewName("redirect:/detail/" + map.get("bookId"));
		}
		
		return mav; 
	}
	
	//책 목록 조회
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		System.out.println("sysout map : " + map);
		logger.debug("logger map : " + map);
		
		List<Map<String, Object>> list = this.bookService.list(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("data", list);
		mav.setViewName("book/list"); //forward
		
		logger.debug("list : " + list);

		if(map.containsKey("keyword")) {
			mav.addObject("keyword", map.get("keyword"));
		}
		
		return mav;
	}
	
	//책 상세 조회
	@RequestMapping("/detail/{bookId}")
	public ModelAndView detail(@PathVariable("bookId") int bookId, ModelAndView mav) {
		Map<String, Object> map = new HashMap<>();
		map.put("bookId", bookId);
		
		Map<String, Object> detailMap = this.bookService.detail(map);
		
		mav.addObject("bookId", bookId);
		mav.addObject("data", detailMap);
		mav.setViewName("book/detail");
		
		return mav;
	}
	
	//책 수정 GET 
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookService.detail(map);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap);
		mav.setViewName("/book/update"); //forward
		
		return mav;
	}
	
	//책 수정 POST
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ModelAndView updatePost(@RequestParam Map<String, Object> map, ModelAndView mav) {
		int result = this.bookService.edit(map);
		
		if(result > 0) {//업데이트 성공
			System.out.println("map.get(\"bookId\") : " + map.get("bookId"));
			System.out.println("map.get(\"bookId\").toString() : " + map.get("bookId").toString());
			String bookId = map.get("bookId").toString(); //toString 왜 사용하는 거지?
			mav.setViewName("redirect:/detail/" + bookId);
			
		}else {	//업데이트 실패, 갱신 화면을 바로 다시 보여줌
			mav = this.update(map);					//방법1. 메서드 호출 (t.간접적인 redirect)
//			mav.setViewName("redirect:/update");	//방법2. redirect
		}
		return mav;
	}
	
	//책 삭제
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView deletePost(@RequestParam Map<String, Object> map, ModelAndView mav) {
		logger.debug("map1 : " + map);
		int result = this.bookService.remove(map);
		logger.debug("map2 : " + map);
		
		if(result > 0) {//도서 삭제 성공
//			mav = this.list(map);				//방법1.forward //내 눈에는 간접적 redirect... 메서드 호출이니까.
			mav.setViewName("redirect:/list");	//방법2.redirect 
		}else {			//도서 삭제 실패
			mav.setViewName("redirect:/detail/" + map.get("map").toString());
		}
		
		return mav; 
	}
	
	
}
