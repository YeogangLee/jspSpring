package member.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.vo.Member;
import util.JdbcUtil;

public class MemberDao {

	public void insert(Connection conn, Member mem) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"insert into member(memberid, name, password, regdate) values(?,?,?,sysdate)"
					);
			pstmt.setString(1, mem.getMemberid());
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mem.getPassword());
//			pstmt.setDate(4, new Date(mem.getRegdate().getDate()));
			pstmt.executeUpdate();			
			
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

	//ȸ�� ���̵� �޾Ƽ� �ش� ���̵��� ������ ������
	public Member selectById(Connection conn, String memberid) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from member where memberid = ?");
			pstmt.setString(1, memberid);
			rs = pstmt.executeQuery();
			
			Member member = null;
			if(rs.next()) {
				member = new Member(
						rs.getString("memberid"),
						rs.getString("name"),
						rs.getString("password"),
						rs.getDate("regdate")
						);
			}
			return member;			
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			//conn�� ���񽺿��� close
		}
	}

	//ȸ���� ��й�ȣ�� ������
	public void update(Connection conn, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update member set name = ?, password = ? where memberid = ?");
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPassword()); //���񽺿��� member vo�� �Ⱦ���, ����� ��й�ȣ�� ���� ��
			pstmt.setString(3, member.getMemberid());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
