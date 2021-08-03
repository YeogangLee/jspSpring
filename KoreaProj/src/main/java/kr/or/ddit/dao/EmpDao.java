package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.EmpVO;

//@Repository
public class EmpDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

//	public int insert(Map<String, Object> map) {
//		return this.sqlSessionTemplate.insert("empMy.insert", map);
//	}

	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("empMy.selectList", map);
	}

	public Map<String, Object> selectDetail(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("empMy.selectDetail", map);
	}

	public int update(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("empMy.update", map);
	}

	public int delete(Map<String, Object> map) {
		return this.sqlSessionTemplate.delete("empMy.delete", map);
	}

	public int insert(EmpVO empVo) {
		return this.sqlSessionTemplate.insert("empMy.insert", empVo);
	}
}
