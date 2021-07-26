package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.vo.JoinRequest;
import member.vo.Member;
import util.JdbcUtil;

public class JoinService {
	
	private MemberDao memberDao = new MemberDao();
	//회원가입
	public void join(JoinRequest joinReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			//insert, update, delete를 실행하면 자동으로 commit이 일어나도록 하지 않겠다.
			conn.setAutoCommit(false);
			//joinReq [memberid=a001, name=홍길동, password=1234, confirmPassword=1234]
			//기존에 a001회원이 이미 있는지 체킹(member가 null이면 신규회원)
			Member member = memberDao.selectById(conn, joinReq.getMemberid());
			if(member!=null) { //이미 해당 아이디가 있다면..
				JdbcUtil.rollback(conn);
				//중복. 아이디. 오류(JoinHandler로 오류를 던짐)
				throw new DuplicateIdException();
			}
			System.out.println("여기1");
			//해당 아이디가 신규라면
			memberDao.insert(conn, 
					new Member(joinReq.getMemberid(), joinReq.getName(), 
							joinReq.getPassword(), new Date()));
			
			//트랜잭션 종료 및 새로운 트랜잭션 시작. DB에 변화 반영함
			conn.commit();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
}














