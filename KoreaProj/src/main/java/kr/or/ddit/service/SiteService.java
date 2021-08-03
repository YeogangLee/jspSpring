package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.SiteVO;

public interface SiteService {
	//등록
	public String create(SiteVO siteVo);
	//전체 목록 조회
	public List<Map<String, Object>> list(Map<String, Object> map);
	//상세 조회
	public Map<String, Object> detail(Map<String, Object> map);
	//수정
	public int update(Map<String, Object> map);
	//삭제
	public int delete(Map<String, Object> map);
	
}
