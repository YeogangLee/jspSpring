package kr.or.ddit.emp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.emp.service.EmpService;
import kr.or.ddit.emp.vo.EmpVO;

@RequestMapping("/emp")
@Controller
public class EmpController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	EmpService empService;

	//직원 등록 GET
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(Model model) {
		
		//직급 list
		//원래 DB에서 가져오는 게 가장 좋은데, 지금은 DB에 이 정보들이 없어서...
		List<String> posList = new ArrayList<String>();
		posList.add("사장");
		posList.add("부장");
		posList.add("과장");
		posList.add("대리");
		posList.add("사원");
		
		//부서 list
		List<String> deptList = new ArrayList<String>();
		deptList.add("인사부");
		deptList.add("개발부");
		deptList.add("회계부");
		
		model.addAttribute("posList", posList);
		model.addAttribute("deptList", deptList);
		
		return "emp/insertSem"; //jsp경로
	}

	//직원 등록 POST
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insertPost(Model model, @ModelAttribute EmpVO empVo) {
		int empNum = this.empService.insert(empVo);
		
		if(empNum == 0) {
			return "redirect:/insert";
			
		}else {
//			mav.setViewName("emp/list");			//아무 정보없이 list.jsp로 이동 -> 데이터 출력X
//			mav.setViewName("redirect:/list");		//-> /korea/list 경로로 이동 -> 잘못된 요청
//			mav.setViewName("redirect:/emp/list");	//redirect시 서버루트를 제외한, 클래스 요청매핑까지 포함해서 경로 설정
			
			model.addAttribute("empNum", empNum);
		}
		
		return "emp/insertSuccess";
	}
	
	
}
