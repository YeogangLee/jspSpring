package kr.or.ddit.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.BookDao;
import kr.or.ddit.service.BookService;

/*
 	서비스 클래스 (= 비즈니스 클래스 = 기능)
 	- 스프링 MVC 구조에서 Controller(관련:웹 브라우저)와 DAO(관련:DB)를 연결해주는 역할.
 	- 서비스 레이어는 인터페이스와 클래스를 함께 사용한다.
 	- 스프링은 직접 클래스를 생성하는 것을 지양한다.
 	  -> 스프링은 인터페이스를 통해 접근하는 것을 권장하는 프레임워크
 	
 	@Service 어노테이션
 	: 스프링에게 서비스 클래스라는 것을 알려주는 역할
 	
 */
@Service
public class BookServiceImpl implements BookService {

	//스프링 이전
//	private BookDao bookDao = BookDao.getInstance();
	
	//스프링 방식
	@Autowired
	BookDao bookDao;
	
	//map : controller로부터 전달받은 파라미터
	@Override
	public String create(Map<String, Object> map) {
		//insert 쿼리 실행, 성공 1 실패 0을 리턴 받는다.
		int affectRwoCount = bookDao.insert(map);
		if(affectRwoCount == 1) {//성공
			System.out.println("BookServiceImpl 성공");
			return "1"; 
		}
		System.out.println("BookServiceImpl 실패");
		return null;
	}

	//@Override 어노테이션 이전에 에러가 없는 것처럼 보일지 몰라도 Override를 통해 시그니처 메서드를 이용해줘야 한다.
	//BookService를 implement한 메서드, 메서드 재정의 => BookService 시그니처 처리가 필요함..
	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		//Controller로부터 받은 파라미터(map) 전달
		return bookDao.selectDetail(map);
	}
	
}
