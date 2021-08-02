package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class T03_ForwardRedirectController {
	/**
	 * 페이지 이동 방법 2가지 - 07.26.월
	 * 1.redirect
	 * 2.forward
	 */
	
	//1.redirect
	@RequestMapping("/move")
	public String move() {
		//redirect : 데이터 전달 불가능, 파라미터만 전달, 주로 다른 메서드로 이동할 때 이용
		return "redirect:/mypage?id=a001&name=kimchulsu&age=23";
 	}
	
	//2.forward
	@RequestMapping("/mypage")
	public String mypage(Model model, @RequestParam String id, @RequestParam String name, @RequestParam int age) {
		System.out.println("id : " + id);
		System.out.println("name : " + name);
		System.out.println("age : " + age);

		//model을 리턴하지 않아도 jsp 페이지에서 이 model을 공유해서 사용할 수 있다 -> forward방식을 이용하기 때문
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "book/mypage";	//forward
	}
	
	
}
