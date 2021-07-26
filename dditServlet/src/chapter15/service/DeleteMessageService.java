package chapter15.service;

import java.sql.Connection;
import java.sql.SQLException;

import chapter15.dao.MessageDao;
import chapter15.jdbc.ConnectionProvider;
import chapter15.jdbc.JdbcUtil;
import chapter15.vo.Message;

public class DeleteMessageService {
	//�̱��� ����
//	private static 
//	private 
//	
//	public static
	
	//�̱��� ����
	private static DeleteMessageService instance = new DeleteMessageService(); 
	private DeleteMessageService() {}
	public static DeleteMessageService getInstance() { return instance; }
	
	public void deleteMessage(int messageId, String password) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			MessageDao messageDao = MessageDao.getInstance();  
			//�ش� �޽����� �����ϴ°�?
			Message message = messageDao.select(conn, messageId);
			if(message == null) {
				//�޼����� ���ٸ�, �Ϻη� ���ܸ� �߻���Ű�� ��
				throw new MessageNotFoundException("�޽��� ����");
				//���⼭ throw������ catch �κп��� ���ܸ� �޾��ش�.
			}
			
			//��й�ȣ�� ��ġ�ϴ°�? ������ true, �ٸ��� false
			if(!message.matchPassword(password)) {
				System.out.println("1");
				throw new InvalidPasswordException("��й�ȣ�� �ٸ��ϴ�.");
				//�Ʒ� Dao�� delete() �޼��� �������� �ʰ�, catch�� �̵�
			}
			
			//�ش� ������ ����
			//delete, commit ��...
			System.out.println("2");
			messageDao.delete(conn, messageId);
			
			//Ʈ����� ����(T1 ���� -> ���ο� Ʈ������� T2 ����)
			conn.commit();
			
		}catch(SQLException ex) {
			//catch��Ͽ��� ���� �߻��� rollbackó��
			//Ʈ�����? �����ͺ��̽� ������ �ϱ� ���� ����Ǿ�� �� ���� ����
			//���ϰ��� -> ���ڼ� (Atomicity) all or nothing, �ϰ���(�� �������� �귯��), ����(ȭ���), ���Ӽ�(TCL �� ���ӵ�)
			JdbcUtil.rollback(conn);
			ex.printStackTrace();
		
		}catch(InvalidPasswordException | MessageNotFoundException ex) { //jsp���� ������ �޾� ȭ�鿡�� �ѷ��� �� �ִ�.
			System.out.println("3");
			JdbcUtil.rollback(conn);
			throw ex;
			//������Ʈ�� ���� ��ü�� ��� ���� ���� �ִµ�, �̰� �����ϱ�.
			
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
}
