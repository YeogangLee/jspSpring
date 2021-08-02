package kr.or.ddit.item.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.item.vo.MemberVO;

//@Controller
//@RequestMapping("/member")
public class AjaxController_item {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxController_item.class);

	// a href= : /item/ajax?id=a001&name=rasmus
	@RequestMapping(value="/ajax", method=RequestMethod.GET)
	public void ajax(String id, String name, @RequestParam Map<String, Object> map, Model model) {
		logger.info("id : " + id + " / name : " + name);
		logger.info("map : " + map.toString());
		model.addAttribute("map", map);
		
//		return "item/ajax"; - 메소드의 return값이 String일 때
//		return "item/registerAjax"; // 메소드의 return값이 String일 때
	}
	
//	@ResponseBody <- 어노테이션의 기존 위치, 메서드 명의 return값에 작성해도 무관
	@RequestMapping(value="/useMap", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> useMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "a001");
		map.put("name", "김캡스");
		
		return map;
	}

	// @ResponseBody를 이용한 JSON 리턴
	// : pom.xml에서 jackson-databind에 대한 Dependency가 추가되어 있어야 한다.
	/*
	 VO를 파라미터로 받기 : @ModelAttribute
	 return VO타입 : @ResponseBody  
	 */
	@RequestMapping(value="/useVO", method=RequestMethod.POST)
	public @ResponseBody MemberVO useVO() {
		MemberVO memberVo = new MemberVO();
		memberVo.setId("a001");
		memberVo.setName("김은대");
		
		return memberVo;
	}
	
}
