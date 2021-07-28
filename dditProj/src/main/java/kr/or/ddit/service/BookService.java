package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

public interface BookService {
	
	//메서드 시그니처
	public String create(Map<String, Object> map);
	
	public Map<String, Object> detail(Map<String, Object> map);

	public List<Map<String, Object>> list(Map<String, Object> map);

	public int edit(Map<String, Object> map);

	public int remove(Map<String, Object> map);
}
