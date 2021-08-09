package kr.or.ddit.wk.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.emp.vo.EmpVO;
import kr.or.ddit.site.vo.SiteVO;
import kr.or.ddit.wk.dao.WkDao;
import kr.or.ddit.wk.service.WkService;
import kr.or.ddit.wk.vo.WkVO;

@Service
public class WkServiceImpl implements WkService {
	@Autowired
	WkDao wkDao;
	
	//근무(WK) 테이블에 데이터를 입력. 어떤 사원이 어떤 사업장에 근무하는지..
	@Override
	public WkVO insert(WkVO wkVo) {
		int result = this.wkDao.insert(wkVo);
		if(result>0) {//insert 성공
			//어떤 정보가 insert되었는지 반환
			return wkVo;
		}else {//insert 실패
			return null;
		}
	}
	
	//사원 정보 목록
	@Override
	public List<EmpVO> selectEmpList(Map<String, Object> map){
		return this.wkDao.selectEmpList(map);
	}
	
	//사업장 정보 목록
	@Override
	public List<SiteVO> selectSiteList(Map<String, Object> siteMap){
		return this.wkDao.selectSiteList(siteMap);
	}
	
	//사원 상세 정보 empNum=1
	@Override
	public EmpVO selectEmpDetail(int empNum) {
		return this.wkDao.selectEmpDetail(empNum);
	}
	
	//삭제할 사원을 work 테이블에서 조회
	@Override
	public int selectDelEmpMy(int empNum) {
		int result = this.wkDao.selectDelEmpMy(empNum);
		System.out.println("Impl selectDelEmpMy : result = " + result);
		
		return this.wkDao.selectDelEmpMy(empNum);
	}
	
	//사원 삭제
	@Override
	public int deleteEmpMy(int empNum) {
		return this.wkDao.deleteEmpMy(empNum);
	}
	
	//사원 번호 : map.get("empNum"));
	@Override
	public Map<String, Object> deleteEmp (Map<String, Object> empNum) { 
		int result = this.wkDao.deleteEmp(empNum);
		if(result>0) {//delete 성공
			return empNum;
		}else {
			return null;
		}
	}
	
	//work테이블에 특정 사원의 정보가 존재하는지. 존재하면 1 이상, 없으면 0
	@Override
	public int selectCheckWk(Map<String, Object> map) { 
		return this.wkDao.selectCheckWk(map);
	}
	
	//모든 사원 수 구하기
	@Override
	public int selectCountEmp() {
		return this.wkDao.selectCountEmp();
	}

	//모든 사업장 수 구하기
	@Override
	public int selectCountSite() {
		return this.wkDao.selectCountSite();
	}
	
}
