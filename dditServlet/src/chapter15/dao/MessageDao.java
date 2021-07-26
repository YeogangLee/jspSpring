package chapter15.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
	//service ��ü���� ����� �޼���(����� ��� ����Ʈ�� ����)
	public List<Message> selectList(Connection conn, int firstRow, int endRow) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			pstmt = conn.prepareStatement("select * from GUESTBOOK_MESSAGE order by message_id");
			pstmt = conn.prepareStatement(
					"SELECT * "
				  + "  FROM (SELECT ROW_NUMBER() OVER (ORDER BY MESSAGE_ID DESC) RNUM, "
				  + "               MESSAGE_ID, GUEST_NAME, PASSWORD, MESSAGE " 
				  + "          FROM GUESTBOOK_MESSAGE) T "
				  + " WHERE T.RNUM BETWEEN " + firstRow + " AND " + endRow);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<Message> messageList = new ArrayList<Message>();
				do {
					Message message = new Message();
					message.setMessageId(rs.getInt("MESSAGE_ID"));
					message.setGuestName(rs.getString("GUEST_NAME"));
					message.setPassword(rs.getString("PASSWORD"));
					message.setMessage(rs.getString("MESSAGE"));
					
					messageList.add(message); // �� �ڵ尡 ���ٰ�  �ؼ�, ������ �߻����� �ʴ´�.
					
				}while(rs.next());
				//do-while���� ����ϴ� ������
				//while(rs.next())�� ����ϸ�
				//�����͸� ��⵵ ���� �������� �Ѿ�� �����̴�.
				return messageList;
				
			}else { //select ����� null ����
				return Collections.emptyList();
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return null;
	}
	
	//��ü ���� ������ ����
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//������ ���� ���� ��ü ���� 
			stmt = conn.createStatement();
			//stmt ���� ��ü�� ���� ������ ����
			rs = stmt.executeQuery("select count(*) from guestbook_message");		//��Ī ��� X
//			rs = stmt.executeQuery("select count(*) cnt from guestbook_message");	//��Ī ���
			//next() �޼��带 �����Ͽ� Ŀ���� �ٶ󺸴� ��ġ�� �÷����� �ƴ�, 1������ �ű��.
			rs.next();
			
			return rs.getInt(1);		//��Ī ��� X
//			return rs.getInt("cnt");	//��Ī ���
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public Message select(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM GUESTBOOK_MESSAGE WHERE MESSAGE_ID = ?");
			pstmt.setInt(1, messageId);
			rs = pstmt.executeQuery();
			
			//rs.next() : ó������ 1���� �ٶ󺸰� ��
			if(rs.next()) {
				//VO(Model�̶���� ��)
				Message message = new Message();
				message.setMessageId(rs.getInt("message_id"));
				message.setGuestName(rs.getString("guest_name"));
				message.setPassword(rs.getString("password"));
				message.setMessage(rs.getString("message"));
				
				return message;
				
			}else {
				return null;
			}
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public int delete(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("DELETE FROM GUESTBOOK_MESSAGE WHERE MESSAGE_ID = ?");
			pstmt.setInt(1, messageId);
			//delete�� �� ���� ���� int������ return��
			return pstmt.executeUpdate();
			
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
}