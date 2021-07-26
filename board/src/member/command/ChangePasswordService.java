package member.command;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import member.vo.Member;
import util.JdbcUtil;

public class ChangePasswordService {
	
	private MemberDao memberDao = new MemberDao();
	
	public void changePassword(String userId, String curPwd, String newPwd) {
		//Service���� connection ��ü�� ������
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			/*
			 ���� ���� 5���� ��ȭ�� �ִ�, insert update delete ���..
			 true���, 1���� ������ ������ commit�� �ȴ�
			 ������ �̰� �ϳ��� Ʈ����� �����̶��, �̰� ������ �ǹǷ�
			 ��ü�� �� ���� Ŀ�����ֱ� ���� false�� ���� */
			
			Member member = memberDao.selectById(conn, userId);
			
			//select * from member where memberid = ${"userId"} �� ����� ���� ��..
			if(member==null) {
				throw new MemberNotFoundException();
			}
			//��й�ȣ�� ��ġ���� ���� �� ���� �߻�
			if(!member.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}
			//memberVO�� ��й�ȣ�� �ű� ��й�ȣ�� ������
			member.changePassword(newPwd);
			memberDao.update(conn, member); //vo�� ������ ������, update�ϱ�.
			
			conn.commit();
			
		}catch (SQLException e) {
			JdbcUtil.rollback(conn); //���� �߻������� rollback
			throw new RuntimeException();
			
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
}
