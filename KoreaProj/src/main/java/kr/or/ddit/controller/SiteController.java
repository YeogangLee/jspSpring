package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.SiteService;
import kr.or.ddit.vo.SiteVO;

//@RequestMapping("/site")
//@Controller
public class SiteController {
	
	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);
	
	@Autowired
	SiteService SiteService;

	//사업장 등록 GET
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("site/create"); //jsp경로
//		mav.addObject("message", "사업장 등록 페이지");
		
		return mav;
	}

	//사업장 등록 POST
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createSubmit(ModelAndView mav, @ModelAttribute SiteVO siteVo) {
		String result = this.SiteService.create(siteVo);
		
		if(result == null) {
			mav.setViewName("redirect:/create");
		}else {
			mav.setViewName("redirect:/site/list");	//redirect시 서버루트를 제외한, 클래스 요청매핑까지 포함해서 경로 설정
		}
		
		return mav;
	}
	
	//전체 목록 조회
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> siteList = this.SiteService.list(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.addObject("siteList", siteList);
		mav.setViewName("site/list"); //forward
		
		if(map.containsKey("keyword")) {
			mav.addObject("keyword", map.get("keyword"));
		}
		
		return mav;
	}
	
	//상세 조회
	@RequestMapping("/detail/{siteNum}")
	public ModelAndView detail(@PathVariable("siteNum") int siteNum, ModelAndView mav) {
		Map<String, Object> map = new HashMap<>();
		map.put("siteNum", siteNum);
		
		Map<String, Object> siteDetailMap = this.SiteService.detail(map);
		
//		mav.addObject("siteNum", siteNum);
		mav.addObject("siteDetailMap", siteDetailMap);
		mav.setViewName("site/detail");
		
		return mav;
	}
	
	//사업장 정보 수정 GET 
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		Map<String, Object> siteDetailMap = this.SiteService.detail(map);
		logger.info("siteDetailMap : " + siteDetailMap);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("siteDetailMap", siteDetailMap);
		mav.setViewName("site/update"); //forward
		
		return mav;
	}
	
	//사업장 정보 수정 POST
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ModelAndView updatePost(@RequestParam Map<String, Object> map, ModelAndView mav) {
		int result = this.SiteService.update(map);
		
		if(result > 0) {//업데이트 성공
			String siteNum = map.get("siteNum").toString(); //toString
			mav.setViewName("redirect:/site/detail/" + siteNum);
			
		}else {
			mav = this.update(map);					//방법1. 메서드 호출 (t.간접적인 redirect)
//			mav.setViewName("redirect:/update");	//방법2. redirect
		}
		return mav;
	}
	
	//사업장 정보 삭제
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView deletePost(@RequestParam Map<String, Object> map, ModelAndView mav) {
		int result = this.SiteService.delete(map);
		System.out.println("삭제 이후 map: " + map);
		
		if(result > 0) {//삭제 성공
//			mav = this.list(map);					//방법1.forward //내 눈에는 간접적 redirect... 메서드 호출이니까.
			mav.addObject("msg", "사업장 번호 " + map.get("siteNum") + " 사업장의 정보가 삭제되었습니다.");
			mav.setViewName("redirect:/site/list");	//방법2.redirect 
		}else {
			mav.setViewName("redirect:/site/detail/" + map.get("map").toString());
		}
		return mav; 
	}
	
}
