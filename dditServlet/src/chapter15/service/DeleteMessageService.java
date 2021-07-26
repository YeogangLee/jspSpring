package chapter15.service;

import java.sql.Connection;
import java.sql.SQLException;

import chapter15.dao.MessageDao;
import chapter15.jdbc.ConnectionProvider;
import chapter15.jdbc.JdbcUtil;
import chapter15.vo.Message;

public class DeleteMessageService {
	//싱글톤 패턴
//	private static 
//	private 
//	
//	public static
	
	//싱글톤 패턴
	private static DeleteMessageService instance = new DeleteMessageService(); 
	private DeleteMessageService() {}
	public static DeleteMessageService getInstance() { return instance; }
	
	public void deleteMessage(int messageId, String password) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			MessageDao messageDao = MessageDao.getInstance();  
			//해당 메시지가 존재하는가?
			Message message = messageDao.select(conn, messageId);
			if(message == null) {
				//메세지가 없다면, 일부러 예외를 발생시키는 것
				throw new MessageNotFoundException("메시지 없음");
				//여기서 throw했으니 catch 부분에서 예외를 받아준다.
			}
			
			//비밀번호가 일치하는가? 같으면 true, 다르면 false
			if(!message.matchPassword(password)) {
				System.out.println("1");
				throw new InvalidPasswordException("비밀번호가 다릅니다.");
				//아래 Dao의 delete() 메서드 실행하지 않고, catch로 이동
			}
			
			//해당 데이터 삭제
			//delete, commit 등...
			System.out.println("2");
			messageDao.delete(conn, messageId);
			
			//트랜잭션 종료(T1 종료 -> 새로운 트랜잭션인 T2 시작)
			conn.commit();
			
		}catch(SQLException ex) {
			//catch블록에서 문제 발생시 rollback처리
			//트랜잭션? 데이터베이스 변경을 하기 위해 수행되어야 할 논리적 단위
			//원일고지 -> 원자성 (Atomicity) all or nothing, 일관성(한 방향으로 흘러감), 고립성(화장실), 지속성(TCL 후 지속됨)
			JdbcUtil.rollback(conn);
			ex.printStackTrace();
		
		}catch(InvalidPasswordException | MessageNotFoundException ex) { //jsp에서 오류를 받아 화면에서 뿌려줄 수 있다.
			System.out.println("3");
			JdbcUtil.rollback(conn);
			throw ex;
			//리퀘스트나 세션 객체에 담아 던질 수도 있는데, 이건 오류니까.
			
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
}
