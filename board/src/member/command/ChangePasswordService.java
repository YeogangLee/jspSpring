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
		//Service에서 connection 객체를 생성함
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			/*
			 여기 만약 5개의 변화가 있다, insert update delete 등등..
			 true라면, 1개를 실행할 때마다 commit이 된다
			 하지만 이게 하나의 트랜잭션 개념이라면, 이게 문제가 되므로
			 전체를 한 번에 커밋해주기 위해 false로 지정 */
			
			Member member = memberDao.selectById(conn, userId);
			
			//select * from member where memberid = ${"userId"} 의 결과가 없을 때..
			if(member==null) {
				throw new MemberNotFoundException();
			}
			//비밀번호가 일치하지 않을 때 오류 발생
			if(!member.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}
			//memberVO의 비밀번호를 신규 비밀번호로 세팅함
			member.changePassword(newPwd);
			memberDao.update(conn, member); //vo를 통으로 던지기, update니까.
			
			conn.commit();
			
		}catch (SQLException e) {
			JdbcUtil.rollback(conn); //에러 발생했으니 rollback
			throw new RuntimeException();
			
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
}
