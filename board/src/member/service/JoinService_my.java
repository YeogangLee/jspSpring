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
			//insert, update, delete를 실행하면 자동으로 commit이 일어나도록 하지 않겠다.
			conn.setAutoCommit(false); //디폴트값은 true
			
			//joinReq : [memberid=b001, name=2, password=2, confirmPassword=2]
			//기존에 a001 회원이 이미 있는지 체킹(member가 null이면 신규 회원)
			Member member = memberDao.selectById(conn, joinReq.getMemberid());
			
			if(member!=null) {//이미 해당 아이디가 존재
				JdbcUtil.rollback(conn); //마지막 롤백 또는 커밋 시점으로 돌아간다.
				//중복 id 오류, 우리가 정의한 예외가 나오는 것.
				throw new DuplicateIdException();
				//핸들러에서 서비스를 요청했기 때문에 요청받았던 서비스로 간다.
			}
			
			//해당 아이디가 신규라면
			memberDao.insert(conn, new Member(joinReq.getMemberid(), joinReq.getName(), 
												joinReq.getPassword(), new Date()));
			
			//트랜잭션 종료 및 새로운 트랜잭션 시작, DB에 변화를 반영함
			conn.commit();
			
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
			
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
