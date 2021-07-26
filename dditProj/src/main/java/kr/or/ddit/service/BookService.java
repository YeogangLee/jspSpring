package kr.or.ddit.service;

import java.util.Map;

public interface BookService {
	
	//메서드 시그니처
	public String create(Map<String, Object> map);
	
	Map<String, Object> detail(Map<String, Object> map);
}
