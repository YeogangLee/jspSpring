package kr.or.ddit.member.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberTableVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;
	
	@Override
	public MemberTableVO selectMember(MemberTableVO memberVo) {
		return this.memberDao.selectMember(memberVo);
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate(); //세션 초기화 -> 로그아웃 처리
	}
	
}
