package kr.or.ddit.emp.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.emp.vo.EmpVO;

@Repository
public class EmpDao {
	
	//root-context.xml
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insert(EmpVO empVo) {
		return this.sqlSessionTemplate.insert("emp.insert", empVo);
	}
}
