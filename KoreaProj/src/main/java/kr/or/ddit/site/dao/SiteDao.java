package kr.or.ddit.site.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.site.vo.SiteVO;

@Repository
public class SiteDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insert(SiteVO siteVo) {
		return this.sqlSessionTemplate.insert("site.insert", siteVo);
	}
	
}
