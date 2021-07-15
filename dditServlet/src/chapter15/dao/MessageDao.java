package chapter15.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import chapter15.jdbc.JdbcUtil;
import chapter15.vo.Message;

public class MessageDao {
	//�ϳ��� ��ü�� ����ϵ��� �̱��� ������ ����
	private static MessageDao messageDao = new MessageDao();
	private MessageDao() {}
	public static MessageDao getInstance() {return messageDao;}
	
	public int insert(Connection conn, Message messageVo) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into guestbook_message " +
					"(message_id, guest_name, password, message) " +
					"values((select nvl(max(message_id),0) + 1 from guestbook_message), ?, ?, ?)"
					);
			
			pstmt.setString(1, messageVo.getGuestName());
			pstmt.setString(2, messageVo.getPassword());
			pstmt.setString(3, messageVo.getMessage());
			
			//pstmt.executeUpdate() : �ݿ��� ���� ������ return
			return pstmt.executeUpdate();
			
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
}