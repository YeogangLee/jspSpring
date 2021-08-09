package kr.or.ddit.wk.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.emp.vo.EmpVO;
import kr.or.ddit.site.vo.SiteVO;
import kr.or.ddit.util.Pagination;
import kr.or.ddit.wk.service.WkService;
import kr.or.ddit.wk.vo.WkVO;

//@RequestMapping(value="/wk")
//@Controller
public class WkControllerMy {
	private static Logger logger = LoggerFactory.getLogger(WkControllerMy.class);
	private int size = 3; //한 페이지에서 보여질 글 행의 개수
	
	@Autowired
	WkService wkService;
	
	//페이징 처리 전 insert
//	@RequestMapping(value="/insert", method=RequestMethod.GET)
//	public String insert(Model model) {
//		//사원 정보 목록
//		List<EmpVO> empVoList = this.wkService.selectEmpList();
//		logger.info("empVoList : " + empVoList);
//		
//		//사업장 정보 목록
//		List<SiteVO> siteVoList = this.wkService.selectSiteList();
//		logger.info("siteVoList : " + siteVoList);
//		
//		model.addAttribute("empVoList", empVoList);
//		model.addAttribute("siteVoList",siteVoList);
//		
//		//forwarding
//		return "wk/insert";
//	}
	
	//currentPage : 현재 페이지 번호
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(Model model, @RequestParam(defaultValue="1") String currentPage,
			@RequestParam(defaultValue="1") String currentSitePage) {

		//사원을 위한 용도
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", currentPage);
		
		//사업장을 위한 용도(현재 페이지)
		Map<String, Object> siteMap = new HashMap<String, Object>();
		siteMap.put("currentSitePage", currentSitePage);
		
		//사원 정보 목록(데이터 페이징 처리(map에 currentPage를 넣어줌))
		List<EmpVO> empVoList = this.wkService.selectEmpList(map);
		logger.info("empVoList : " + empVoList);
		
		//사업장 정보 목록
		List<SiteVO> siteVoList = this.wkService.selectSiteList(siteMap);
		logger.info("siteVoList : " + siteVoList);
		
		model.addAttribute("empVoList", empVoList);
		model.addAttribute("siteVoList",siteVoList);
		
		//페이징 영역 Pagination (전체 게시글 개수, 사용자가 요청한 페이지 번호, 한 페이지에 보여질 글 행의 수)
		int total = this.wkService.selectCountEmp();
		Pagination paging = new Pagination(total, Integer.parseInt(currentPage), size);
		
		String pagingStr = "";
		
		//1. [이전]링크
		if(paging.getStartPage() > 3) { //[이전] 4 5 6 
			pagingStr += "<a href='/wk/insert?currentPage=" + (paging.getStartPage() - 3) + "'>[이전]</a>";
		}
		
		//2. 페이징 번호
		for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++) {
			pagingStr += "<a href='/wk/insert?currentPage=" + i + "'>[" + i + "]</a>";
		}
		
		//3. [다음]링크
		//7  8  9 [다음]
		//10 11(totalPage)
		//즉, 페이지 이동 링크 끝 번호가 마지막 페이지보다 작을 때.. 
		if(paging.getEndPage() < paging.getTotalPages()) { //10 11 12(endPage) <- 전체 페이지는 11까지였다.
			pagingStr += "<a href='/wk/insert?currentPage=" + (paging.getStartPage() + 3) + "'>[다음]</a>";
		}
		
		logger.info("pagingStr : " + pagingStr);
//		logger.info("sitePagingStr : " + sitePagingStr);

		//jsp에서 페이징 영역에 활용될 예정
		model.addAttribute("pagingStr", pagingStr);
//		model.addAttribute("pagingStr", sitePagingStr);
		
		//forwarding
		return "wk/insert";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insertPost(@RequestParam Map<String, Object> map) throws ParseException {
		logger.info("map : " + map.toString());
		
		String wkStartDtStr = (String)map.get("wkStartDt");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(wkStartDtStr);

		//근무 테이블 용 Value Object 생성
		WkVO wkVo = new WkVO();
		wkVo.setEmpNum(Integer.parseInt((String)map.get("empNum")));
		wkVo.setSiteNum(Integer.parseInt((String)map.get("siteNum")));
		wkVo.setWkStartDt(date);
		
		logger.info("wkVo : " + wkVo);
		WkVO resultVo = this.wkService.insert(wkVo);
		
		return "redirect:/wk/insert";
	}
	
	//var data = {"empNum":varEmpNum};
	//data:JSON.stringify(data)	//보낼 데이터
	//RequestBody 어노테이션 : Post방식으로 넘어온 HTTP 파라미터를 Map에 매핑
	@ResponseBody
	@RequestMapping(value="/selectEmpDetail",method=RequestMethod.POST)
	public EmpVO selectEmpDetail(@RequestBody Map<String,Object> map) {
		logger.info("map : " + map.get("empNum"));
		//직원번호 : empNum
		String empNumStr = (String)map.get("empNum");
		int empNum = Integer.parseInt(empNumStr);
		
		EmpVO empVo = this.wkService.selectEmpDetail(empNum);
		logger.info("empVo : " + empVo);
		
		return empVo;
	}
	
	//사원 삭제 My
	@RequestMapping(value="/myDelete", method=RequestMethod.POST)
	public String deletePostMy(@RequestBody Map<String, Object> map,
			Model model) {
		logger.info("empNumDel : " + map.get("empNum"));
		
		int empNum = Integer.parseInt((String)map.get("empNum"));
		System.out.println("1");
		int resultSel = this.wkService.selectDelEmpMy(empNum);
		System.out.println("2");
		int resultDel = 0;
		String msg = "";
		
		System.out.println("resultSel : " + resultSel);
		if(resultSel == 0) {
			resultDel = this.wkService.deleteEmpMy(empNum); 
		}
		
		if(resultDel == 0) { //삭제 실패
			msg = "실패";
		}else {
			msg = "성공";
		}
		
		return "redirect:/wk/insert" + msg;
	}
	
//	@RequestMapping(value="/insert/{msg}", method=RequestMethod.GET)
//	public String insertMy(@PathVariable String msg, Model model) {
//		//사원 정보 목록
//		List<EmpVO> empVoList = this.wkService.selectEmpList();
//		logger.info("empVoList : " + empVoList);
//		
//		//사업장 정보 목록
//		List<SiteVO> siteVoList = this.wkService.selectSiteList();
//		logger.info("siteVoList : " + siteVoList);
//		
//		model.addAttribute("empVoList", empVoList);
//		model.addAttribute("siteVoList",siteVoList);
//		logger.info("msg1 : " + msg);
//		System.out.println("msg2 : " + msg);
//		model.addAttribute("msg", msg);
//		
//		//forwarding
//		return "wk/insert";
//	}
	
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public Map<String, Object> deletePost(@RequestBody Map<String, Object> map) {
		logger.info("empNumDel : " + map.get("empNum"));
		
		Map<String, Object> resultMap = new HashMap<String, Object>(); 

		//work테이블에서 사원 존재 체크
		int result = this.wkService.selectCheckWk(map);
		
		if(result > 0) {//존재
			resultMap.put("empNum", "0");
		}else {//없음
			//삭제된 사원 번호 받기 (map키 : empNum)
			resultMap = this.wkService.deleteEmp(map);
		}
		
		return resultMap;
	}
	
}
