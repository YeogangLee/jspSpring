package chapter15.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import chapter15.dao.MessageDao;
import chapter15.jdbc.ConnectionProvider;
import chapter15.jdbc.JdbcUtil;
import chapter15.vo.Message;

public class WriteMessageService {
	
	//싱글톤 패턴 구현(sem//쓰리쿠션)
	private static WriteMessageService instance = new WriteMessageService();
	public static WriteMessageService getInstance() {return instance;}
	private WriteMessageService() {}
	
	public void write(Message messageVo) {
	
		System.out.println("messageVo : " + messageVo.toString());
		
		Connection conn = null;
		
		//이제 이거 사용 안 한다.
//		conn = DriverManager.getConnection("jdbcDriver", "dbUser", "dbPass");
		try {
			conn = ConnectionProvider.getConnection();
			//MessageDao.insert() 메서드를 이용해서 메시지를 테이블에 insert
			MessageDao messageDao = MessageDao.getInstance();
			messageDao.insert(conn, messageVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn); //dao에서 conn을 끓는게 아닌, 여기서 생성 후 conn 끊기
		}
	}
}
