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

import kr.or.ddit.service.EmpService;
import kr.or.ddit.vo.EmpVO;

//@RequestMapping("/emp")
//@Controller
public class EmpController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	EmpService empService;

	//직원 등록 GET
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("emp/create"); //jsp경로
//		mav.addObject("message", "직원 등록 페이지");
		
		return mav;
	}

	//직원 등록 POST
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createSubmit(ModelAndView mav, @ModelAttribute EmpVO empVo) {
		String result = this.empService.create(empVo);
		
		if(result == null) {
			mav.setViewName("redirect:/create");
		}else {
//			mav.setViewName("emp/list");		//아무 정보없이 list.jsp로 이동 -> 데이터 출력X
//			mav.setViewName("redirect:/list");	//-> /korea/list 경로로 이동 -> 잘못된 요청
			mav.setViewName("redirect:/emp/list");	//redirect시 서버루트를 제외한, 클래스 요청매핑까지 포함해서 경로 설정
		}
		
		return mav;
	}
	
	//VO 사용x
//	public ModelAndView createSubmit(ModelAndView mav, @RequestParam Map<String, Object> map) {
//		String result = this.empService.create(map);
//		logger.info("insert된 map : " + map);
//		
//		if(result == null) {
//			mav.setViewName("redirect:/create");
//		}else {
////			mav.setViewName("emp/list");		//아무 정보없이 list.jsp로 이동 -> 데이터 출력X
////			mav.setViewName("redirect:/list");	//-> /korea/list 경로로 이동 -> 잘못된 요청
//			mav.setViewName("redirect:/emp/list");	//redirect시 서버루트를 제외한, 클래스 요청매핑까지 포함해서 경로 설정
//		}
//		
//		return mav;
//	}
	
	//전체 목록 조회
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		System.out.println("delete에서 보내기 test1 map : " + map);
		logger.debug("delete에서 보내기 test2 map : " + map); //이건 왜 안찍힐까
		
		List<Map<String, Object>> empList = this.empService.list(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.addObject("empList", empList);
		mav.setViewName("emp/list"); //forward
		
		if(map.containsKey("keyword")) {
			mav.addObject("keyword", map.get("keyword"));
		}
		
		return mav;
	}
	
	//상세 조회
	@RequestMapping("/detail/{empNum}")
	public ModelAndView detail(@PathVariable("empNum") int empNum, ModelAndView mav) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empNum", empNum);
		
		Map<String, Object> empDetailMap = this.empService.detail(map);
		
//		mav.addObject("empNum", empNum);
		mav.addObject("empDetailMap", empDetailMap);
		mav.setViewName("emp/detail");
		
		return mav;
	}
	
	//직원 정보 수정 GET 
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		Map<String, Object> empDetailMap = this.empService.detail(map);
		logger.info("empDetailMap : " + empDetailMap);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("empDetailMap", empDetailMap);
		mav.setViewName("emp/update"); //forward
		
		return mav;
	}
	
	//직원 정보 수정 POST
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ModelAndView updatePost(@RequestParam Map<String, Object> map, ModelAndView mav) {
		int result = this.empService.update(map);
		
		if(result > 0) {//업데이트 성공
			String empNum = map.get("empNum").toString(); //toString
			mav.setViewName("redirect:/emp/detail/" + empNum);
			
		}else {
			mav = this.update(map);					//방법1. 메서드 호출 (t.간접적인 redirect)
//			mav.setViewName("redirect:/update");	//방법2. redirect
		}
		return mav;
	}
	
	//직원 정보 삭제
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView deletePost(@RequestParam Map<String, Object> map, ModelAndView mav) {
		int result = this.empService.delete(map);
		System.out.println("삭제 이후 map: " + map);
		
		if(result > 0) {//삭제 성공
//			mav = this.list(map);					//방법1.forward //내 눈에는 간접적 redirect... 메서드 호출이니까.
			mav.addObject("msg", "사원번호 " + map.get("empNum") + " 직원의 정보가 삭제되었습니다.");
			mav.setViewName("redirect:/emp/list");	//방법2.redirect 
		}else {
			mav.setViewName("redirect:/emp/detail/" + map.get("map").toString());
		}
		return mav; 
	}
	
}
