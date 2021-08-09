package kr.or.ddit.wk.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.emp.vo.EmpVO;
import kr.or.ddit.site.vo.SiteVO;
import kr.or.ddit.wk.vo.WkVO;

public interface WkService {
	//메소드 시그니처
	public WkVO insert(WkVO wkVo);

	public List<EmpVO> selectEmpList(Map<String, Object> map);

	public List<SiteVO> selectSiteList(Map<String, Object> siteMap);

	public EmpVO selectEmpDetail(int empNum);

	public int selectDelEmpMy(int empNum);
	
	public int deleteEmpMy(int empNum);

	public Map<String, Object> deleteEmp(Map<String, Object> empNum);

	public int selectCheckWk(Map<String, Object> map);

	public int selectCountEmp();

	public int selectCountSite();
}
