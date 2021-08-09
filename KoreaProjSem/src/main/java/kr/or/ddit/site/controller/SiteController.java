package kr.or.ddit.site.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.site.service.SiteService;
import kr.or.ddit.site.vo.SiteVO;

@RequestMapping(value="/site")
@Controller
public class SiteController {
	private static Logger logger = LoggerFactory.getLogger(SiteController.class);
	
	@Autowired
	SiteService siteService;
	
//	@RequestMapping(value="/insert",method=RequestMethod.GET)
//	public String insert() {
//		//forwarding
//		return "site/insert";
//	}
	
//	@RequestMapping(value="/insert",method=RequestMethod.POST)
//	public ModelAndView insertPost(ModelAndView mav,
//				@ModelAttribute SiteVO siteVo) {
//		logger.info("siteVo : " + siteVo.toString());
//		int siteNum = this.siteService.insert(siteVo);
//		
//		if(siteNum==0) {//insert실패
//			mav.setViewName("redirect:/site/insert");
//		}else {//insert 성공
//			mav.addObject("siteNum", siteNum);
//			mav.setViewName("redirect:/site/detail?siteNum="+siteNum);
//		}
//		return mav;
//	}
	
	@RequestMapping(value="/popUp/insert",method=RequestMethod.GET)
	public String insert() {
		//forwarding
		return "site/popUp/insert";
	}
	
	@RequestMapping(value="/popUp/insert",method=RequestMethod.POST)
	public ModelAndView insertPost(ModelAndView mav,
				@ModelAttribute SiteVO siteVo) {
		logger.info("siteVo : " + siteVo.toString());
		int siteNum = this.siteService.insert(siteVo);
		
		if(siteNum==0) {//insert실패
//			mav.setViewName("redirect:/site/success/insert");
			mav.setViewName("redirect:/site/popUp/insert");
			//setViewName에 redirect 이용 가능 ?
			
		}else {//insert 성공
			mav.addObject("siteNum", siteNum);
			
			//1. redirect
			//siteNum 주석 처리 : http://localhost:8090/site/success
			//siteNum 주석 해제 : http://localhost:8090/site/success?siteNum=2021015
			//=> siteNum이 알아서 GET 파라미터로 붙는다,
			//생각해보니까 redirect는 파라미터만 전달할 수 있다고 했었다. redirect로 페이지도 요청할 수 있구나.
			
//			mav.setViewName("redirect:/site/success");
			/*
			 setViewName + (redirect:)
			 -> InternalResourceViewResolver가 동작하지 않고
			  그렇지 않은 경우는 (ex.아래의 forward방식)
			 InternalResourceViewResolver에 설정된
			 prefix 경로(정보)와 suffix 정보가 합쳐져서 .jsp 파일을 찾는다는 점을 기억하도록 하자.
			 (파일: servlet-context.xml)

			  출처: https://developer-joe.tistory.com/222 
			 */
			
			//2. forward
			mav.setViewName("site/success"); 

			//결론
			//forward : addObject를 사용하지 않는데, 살려둔다고 해서 문제되지는 않는다. 
			//redirect : addObject를 사용하지 않는데, 살려두면 경로에 붙어서, addObject값이 포함된 경로를 처리할 수 있어야 한다.
		}
		return mav;
	}
}








