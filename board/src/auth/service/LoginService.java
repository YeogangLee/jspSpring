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

			//select * from member where memberid = 'a001';�� ����� ���� ��
			if(member==null) {
				//����� ���� Exception ���� (RuntimeException(���� �߿� ��Ÿ���� ����) Ŭ������ ��ӹ���)
				//�׳� Exception�� ��ӹ޾Ƶ� ������, ���� �޼����� �ʹ� �߻����̹Ƿ�
				//�׺��� �� �ܰ� �Ʒ��� �ִ�, RuntimeException�� ��ӹ޴´�.
				
				//throw : ������.. ����? ���� ���񽺸� ȣ���� LoginHandler��
				throw new LoginFailException();
				
			}else {
				//ȸ���� �ִٸ�.. ������ ��й�ȣ�� ��ġ�ϴ��� üũ
				//password : ����ڰ� �Է��� ��й�ȣ
				//member.getPassword() : select ���� �Ŀ� ������ ��й�ȣ
				if(!member.matchPassword(password)) {
					throw new LoginFailException();
				}
				//vo�� ����
				System.out.println(member.getMemberid() + ", " + member.getName());
				return new User(member.getMemberid(), member.getName());
			}
			
		}catch (SQLException e) {
			throw new RuntimeException(e); //���� e�� �ٸ� ���ܷ� �� �Ѱ�?
			
		}
		
	}
}
