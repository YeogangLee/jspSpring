package kr.or.ddit.wk.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.emp.vo.EmpVO;
import kr.or.ddit.site.vo.SiteVO;
import kr.or.ddit.wk.vo.WkVO;

@Repository
public class WkDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//근무(WK) 테이블에 데이터를 입력. 어떤 사원이 어떤 사업장에 근무하는지..
	public int insert(WkVO wkVo) {
		return this.sqlSessionTemplate.insert("wk.insert", wkVo);
	}
	
	//사원 정보 목록
	public List<EmpVO> selectEmpList(Map<String, Object> map){
		return this.sqlSessionTemplate.selectList("wk.selectEmpList", map);
	}
	
	//사업장 정보 목록
	public List<SiteVO> selectSiteList(Map<String, Object> siteMap){
		return this.sqlSessionTemplate.selectList("wk.selectSiteList", siteMap);
	}
	
	//사원 상세 정보 empNum=1
	public EmpVO selectEmpDetail(int empNum) {
		return this.sqlSessionTemplate.selectOne("wk.selectEmpDetail", empNum);
	}
	
	//삭제할 사원을 WORK 테이블에서 조회
	public int selectDelEmpMy(int empNum) {
		int result = this.sqlSessionTemplate.selectOne("wk.selectDelEmpMy", empNum);
		System.out.println("DAO selectDelEmpMy : result = " + result);
		return result;
	}

	//work에 없는 사원 삭제
	public int deleteEmpMy(int empNum) {
		return this.sqlSessionTemplate.delete("wk.deleteEmpMy", empNum);
	}
	
	//사원 번호 : map.get("empNum"));
	public int deleteEmp(Map<String, Object> empNum) { 
		return this.sqlSessionTemplate.delete("wk.deleteEmp", empNum);
	}
	
	//work테이블에 특정 사원의 정보가 존재하는지
	public int selectCheckWk(Map<String, Object> map) { 
		//존재하면 1 이상, 없으면 0
		return this.sqlSessionTemplate.selectOne("wk.selectCheckWk", map);
	}
	
	//사원의 모든 인원 수 구하기
	public int selectCountEmp() {
		return this.sqlSessionTemplate.selectOne("wk.selectCountEmp");
	}
	
	//모든 사업장 수 구하기
	public int selectCountSite() {
		return this.sqlSessionTemplate.selectOne("wk.selectCountSite");
	}
}
