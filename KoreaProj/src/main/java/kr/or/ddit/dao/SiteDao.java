package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.SiteVO;

//@Repository
public class SiteDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

//	public int insert(Map<String, Object> map) {
//		return this.sqlSessionTemplate.insert("siteMy.insert", map);
//	}

	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("siteMy.selectList", map);
	}

	public Map<String, Object> selectDetail(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("siteMy.selectDetail", map);
	}

	public int update(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("siteMy.update", map);
	}

	public int delete(Map<String, Object> map) {
		return this.sqlSessionTemplate.delete("siteMy.delete", map);
	}

	public int insert(SiteVO siteVo) {
		return this.sqlSessionTemplate.insert("siteMy.insert", siteVo);
	}
}
