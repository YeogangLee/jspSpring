package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.vo.User;
import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.vo.Member;

public class LoginService {

	private MemberDao memberDao = new MemberDao();
	
	public User login(String memberid, String password) {
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			//where memberid = #{memberid}
			Member member = memberDao.selectById(conn, memberid);

			//select * from member where memberid = 'a001';의 결과가 없을 때
			if(member==null) {
				//사용자 정의 Exception 생성 (RuntimeException(실행 중에 나타나는 오류) 클래스를 상속받음)
				//그냥 Exception을 상속받아도 되지만, 오류 메세지가 너무 추상적이므로
				//그보다 한 단계 아래에 있는, RuntimeException을 상속받는다.
				
				//throw : 던진다.. 어디로? 여기 서비스를 호출한 LoginHandler로
				throw new LoginFailException();
				
			}else {
				//회원이 있다면.. 다음은 비밀번호가 일치하는지 체크
				//password : 사용자가 입력한 비밀번호
				//member.getPassword() : select 실행 후에 가져온 비밀번호
				if(!member.matchPassword(password)) {
					throw new LoginFailException();
				}
				//vo를 리턴
				System.out.println(member.getMemberid() + ", " + member.getName());
				return new User(member.getMemberid(), member.getName());
			}
			
		}catch (SQLException e) {
			throw new RuntimeException(e); //예외 e를 다른 예외로 또 넘겨?
			
		}
		
	}
}
