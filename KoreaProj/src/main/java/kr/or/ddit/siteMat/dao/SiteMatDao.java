package kr.or.ddit.siteMat.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.siteMat.vo.SiteMatVO;

@Repository
public class SiteMatDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//일종의 디자인 패턴 : insert, delete, update는 int형을 리턴한다
	public int insert(SiteMatVO siteMatVo) {
		return this.sqlSessionTemplate.insert("sitemat.insert", siteMatVo);
	}
	
	public List<Map<String, Object>> selectConMatInfo() {
		return this.sqlSessionTemplate.selectList("sitemat.selectConMatInfo");
	}
	
}
