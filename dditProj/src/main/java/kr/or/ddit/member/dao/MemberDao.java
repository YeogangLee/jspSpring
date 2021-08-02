package kr.or.ddit.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.member.vo.MemberTableVO;

@Repository
public class MemberDao {

	//DAO : Oracle과 통신 -> Connection 객체 필요 -> ConnectionPool에서 미리 만들어진 것 가져오기 
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public MemberTableVO selectMember(MemberTableVO memberVo) {
		//.selectOne("namespace.id", 파라미터)
		return this.sqlSessionTemplate.selectOne("member.select", memberVo);
	}
}
