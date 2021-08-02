package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class T04_ParameterTestController {
	/**
	 * 스프링에서 요청 형태에 따른, 파라미터를 전달하는 3가지 방법 - 07.27.화 
	 * : 1.get - 2.post - 3."/경로/{변수명}" + @PathVariable("변수명") 
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(T04_ParameterTestController.class);
		
	//1.GET
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerByParameter(String userId, String password) {
		logger.info("registerByParameter에 왔다");
		logger.info("userId" + userId);
		logger.info("password" + password);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("password", password);
		
		return "book/success"; //forward
	}
	
	//3. /경로/{변수명} + @PathVariable("변수명") 자료형 변수명
	@RequestMapping(value="/register/{userId}", method=RequestMethod.GET)
	public String registerByPath(@PathVariable("userId") String userId) {
		logger.info("registerByPath");
		logger.info("userId : " + userId);
		
		return "book/success";
	}
	
	//2.POST
	@RequestMapping("/register02") //POST를 명시하지 않아도 정상적으로 동작되네..
	public String register02(String password, String userId, int coin) {
		logger.info("register02");
		logger.info("userId : " + userId);
		logger.info("password : " + password);
		logger.info("coin : " + coin);
		return "book/success";
	}
}
