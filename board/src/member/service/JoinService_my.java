package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.vo.JoinRequest_my;
import member.vo.Member;
import util.JdbcUtil;

public class JoinService_my {
	
	private MemberDao memberDao = new MemberDao();
	
	public void join(JoinRequest_my joinReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			//insert, update, delete�� �����ϸ� �ڵ����� commit�� �Ͼ���� ���� �ʰڴ�.
			conn.setAutoCommit(false); //����Ʈ���� true
			
			//joinReq : [memberid=b001, name=2, password=2, confirmPassword=2]
			//������ a001 ȸ���� �̹� �ִ��� üŷ(member�� null�̸� �ű� ȸ��)
			Member member = memberDao.selectById(conn, joinReq.getMemberid());
			
			if(member!=null) {//�̹� �ش� ���̵� ����
				JdbcUtil.rollback(conn); //������ �ѹ� �Ǵ� Ŀ�� �������� ���ư���.
				//�ߺ� id ����, �츮�� ������ ���ܰ� ������ ��.
				throw new DuplicateIdException();
				//�ڵ鷯���� ���񽺸� ��û�߱� ������ ��û�޾Ҵ� ���񽺷� ����.
			}
			
			//�ش� ���̵� �űԶ��
			memberDao.insert(conn, new Member(joinReq.getMemberid(), joinReq.getName(), 
												joinReq.getPassword(), new Date()));
			
			//Ʈ����� ���� �� ���ο� Ʈ����� ����, DB�� ��ȭ�� �ݿ���
			conn.commit();
			
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
			
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
