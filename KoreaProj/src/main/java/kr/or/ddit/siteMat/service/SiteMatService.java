package kr.or.ddit.siteMat.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.site.vo.SiteVO;
import kr.or.ddit.siteMat.vo.SiteMatVO;

public interface SiteMatService {

	public SiteMatVO insert(SiteMatVO siteMatVo);
	
	public List<Map<String, Object>> selectConMatInfo();

	public List<SiteVO> selectSiteList(Map<String, Object> map);

	public List<Map<String, Object>> siteMatList(Map<String, Object> map);

}
