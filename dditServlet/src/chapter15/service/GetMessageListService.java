package chapter15.service;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import chapter15.dao.MessageDao;
import chapter15.jdbc.ConnectionProvider;
import chapter15.jdbc.JdbcUtil;
import chapter15.vo.Message;

public class GetMessageListService {
	
	//�̱��� ����
	private static GetMessageListService  instance = new GetMessageListService(); 
	private GetMessageListService() {} 
	public static GetMessageListService getInstance() {
		return instance;
	}
	
	//1 ������ �� ȭ�鿡 ǥ���� ���� ������ ����� �Ͽ� �޸𸮿� �ε���
	private static final int MESSAGE_COUNT_PER_PAGE = 3;
	
	//���� ��� ����Ʈ ����, ���� ������ ��ȣ(pageNumber)�� �Ķ���ͷ� ����
//	public List<Message> getMessageList(int pageNumber) { //����¡ ó�� ��
	public MessageListView getMessageList(int pageNumber) { //����¡ ó�� ��
		Connection conn = null;
		int currentPageNumber = pageNumber;
		try {
			conn = ConnectionProvider.getConnection();
			
			List<Message> messageList = null;
			
			//select�� �����ϱ� ���� Dao ��ü�� ����(�̱��� ����)
			MessageDao messageDao = MessageDao.getInstance();
			
			//select �� ��ü ���� ��(ī��θ�Ƽ)�� ����
			int messageTotalCount = messageDao.selectCount(conn);
			//����¡ ���� ������ ��ȣ
			int firstRow = 0;
			//����¡ �� ������ ��ȣ
			int endRow = 0;

			//select ���� ����� ���� ������ ����
			if(messageTotalCount > 0) {
				firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE - 1;
				/*
					 1		2		3		4
					 123	456		789		10 11 12
				 */
				//����¡ ó�� ��
//				messageList = messageDao.selectList(conn);
				//����¡ ó�� �� 
				messageList = messageDao.selectList(conn, firstRow, endRow);
				
			}else {
				//���� ������ ��ȣ�� 0���� ����
				currentPageNumber = 0;
				messageList = Collections.emptyList();
			}
			
			//����¡ ó�� ��
//			return messageList;
			
			//����¡ ó�� ��
			//int messageTotalCount, int currentPageNumber, List<Message> messageList, 
			//int messageCountPerPage, int firstRow, int endRow
			return new MessageListView(messageTotalCount, currentPageNumber, 
										messageList, MESSAGE_COUNT_PER_PAGE, firstRow, endRow);

		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
			
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
}
