package kr.or.ddit.siteMat.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.emp.controller.EmpController;
import kr.or.ddit.siteMat.service.SiteMatService;

@RequestMapping("/siteMat")
@Controller
public class SiteMatController {

	private static final Logger logger = LoggerFactory.getLogger(SiteMatController.class);
	
	@Autowired
	SiteMatService siteMatService;
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(Model model) {
		List<Map<String, Object>> list = this.siteMatService.selectConMatInfo();
		logger.info("list : " + list);
		model.addAttribute("list", list);
		
		return "siteMat/insert";
	}
	
	//사업장 검색 : 사업장 번호(site_num), 사업장 명(site_nm), 주소(addr)
	@RequestMapping(value="/selectSite", method=RequestMethod.GET)
	public String selectSite() {
		return "siteMat/selectSite";
	}
			
	
}
