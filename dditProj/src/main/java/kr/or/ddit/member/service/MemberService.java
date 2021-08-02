package kr.or.ddit.member.service;

import javax.servlet.http.HttpSession;

import kr.or.ddit.member.vo.MemberTableVO;

public interface MemberService {

	public MemberTableVO selectMember(MemberTableVO memberVo);

	public void logout(HttpSession session);

	
}
