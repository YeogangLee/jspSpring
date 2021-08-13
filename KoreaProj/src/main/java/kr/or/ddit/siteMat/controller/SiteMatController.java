package kr.or.ddit.siteMat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.emp.controller.EmpController;
import kr.or.ddit.siteMat.service.SiteMatService;
import kr.or.ddit.siteMat.vo.SiteMatVO;
import kr.or.ddit.site.vo.SiteVO;

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
	
	//사업장 자재 정보 등록
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insertPost(@ModelAttribute SiteMatVO siteMatVo, Model model) {
		SiteMatVO vo = this.siteMatService.insert(siteMatVo);
		logger.info("siteMatVo : " + siteMatVo);
		logger.info("vo : " + vo);
		
		//사업장 자재 목록의 검색 조건으로 사업장명과 자재명을 일단 빈값으로 처리 => 모든 자재가 담긴 목록을 가져온다
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteNm", "");
		map.put("itemNm", "");
		
		//사업장 자재 목록(사업장명, 자재명 포함)
		List<Map<String, Object>> siteMatList = this.siteMatService.siteMatList(map);
		logger.info("siteMatList : " + siteMatList.get(0).toString());
		
		model.addAttribute("vo", vo);
		//추가된 것
		model.addAttribute("siteMatList", siteMatList);
		
		return "siteMat/list";
	}
	
	//사업장 검색 : 사업장 번호(site_num), 사업장 명(site_nm), 주소(addr)
	@RequestMapping(value="/selectSite", method=RequestMethod.GET)
	public String selectSite(@RequestParam Map<String, Object> map, Model model) {
		//GET방식을 할 때는 이 처리를 해줘야 한다..
		//-> XML파일에서 <if>비교 조건으로 siteNm을 사용하고 있는데,
		//map에 키값을 넣지 않으면, 키 자체가 존재하지 않기 때문에 오류가 발생한다.
		map.put("siteNm", "");
		//map에 정보가 있을 수도 없을 수도 있다.
		List<SiteVO> siteVoList = this.siteMatService.selectSiteList(map);
		logger.info("siteVoList : " + siteVoList.get(0).toString());
		model.addAttribute("siteVoList", siteVoList);
		
		return "siteMat/selectSite";
	}

	@RequestMapping(value="/selectSite", method=RequestMethod.POST)
	public String selectSitePost(@RequestParam Map<String, Object> map, Model model) {
		List<SiteVO> siteVoList = this.siteMatService.selectSiteList(map);
		logger.info("siteVoList : " + siteVoList.get(0).toString());
		model.addAttribute("siteVoList", siteVoList);
		
		return "siteMat/selectSite"; 
	}
	
	//사업장 자재 목록 보기
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listGet(Model model) {
		
		//사업장 자재 목록의 검색 조건으로 사업장명과 자재명을 일단 빈값으로 처리 => 모든 자재가 담긴 목록을 가져온다
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteNm", "");
		map.put("itemNm", "");
		
		//사업장 자재 목록(사업장명, 자재명 포함)
		List<Map<String, Object>> siteMatList = this.siteMatService.siteMatList(map);
		logger.info("siteMatList : " + siteMatList.get(0).toString());
		
		model.addAttribute("siteMatList", siteMatList);
		
		return "siteMat/list";
	}
	
	//사업장 자재 목록 검색
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String listPost(@RequestParam Map<String, Object> map, Model model) {
		logger.info("map : " + map.toString());
		
		List<Map<String, Object>> siteMatList = this.siteMatService.siteMatList(map);
		
		/*
		  조건에 일치하는 데이터가 없을 때, 에러 발생
		 : java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
		 */
		if(siteMatList.size() > 0) {
			logger.info("siteMatList : " + siteMatList.get(0).toString());
		}
		
		model.addAttribute("siteMatList", siteMatList);
		model.addAttribute("map", map); //검색 결과 페이지(list.jsp)에 검색 키워드를 보여주기 위해, 받아온 map을 그대로 사용
		
		return "siteMat/list";
	}
	
	
}
