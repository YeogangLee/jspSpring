package kr.or.ddit.siteMat.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.site.vo.SiteVO;
import kr.or.ddit.siteMat.vo.SiteMatVO;

@Repository
public class SiteMatDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//일종의 디자인 패턴 : insert, delete, update는 int형을 리턴한다
	public int insert(SiteMatVO siteMatVo) {
		return this.sqlSessionTemplate.insert("sitemat.insert", siteMatVo);
	}
	
	//건설 자재 정보 select
	public List<Map<String, Object>> selectConMatInfo() {
		return this.sqlSessionTemplate.selectList("sitemat.selectConMatInfo");
	}
	
	//사업장 검색 쿼리
	public List<SiteVO> selectSiteList(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("sitemat.selectSiteList", map);
		//파라미터로 map안줘도 오류X - ??
		// -> !!
	}
	
	//사업장 자재 목록
	public List<Map<String, Object>> siteMatList(Map<String, Object> map){
		return this.sqlSessionTemplate.selectList("sitemat.siteMatList", map);
	}
	
	
	
}
