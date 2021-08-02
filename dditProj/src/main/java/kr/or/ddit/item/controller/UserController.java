package kr.or.ddit.item.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.item.vo.UserVO;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/user/register", method=RequestMethod.GET)
	public String insertUser() {
		return "user/register";	//forward
	}
	
	@ResponseBody
	@RequestMapping(value="/user/register", method=RequestMethod.POST)
	public UserVO insertUserPost(@RequestBody @Valid UserVO userVo, BindingResult bindingResult) {
		logger.info("userVo : " + userVo);
		logger.info("bindingResult : " + bindingResult);
		logger.info("bindingResult.hasErrors() : " + bindingResult.hasErrors());
		//값 입력을 안 하면 에러가 검출되어야 하는데 현재 검출이 안 된다... SEM이 더 연구해보는 걸로
		
//		return "user/success"; //forward
//		return "OK";
		return userVo;
	}
	
}
