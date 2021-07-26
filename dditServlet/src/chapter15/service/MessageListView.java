package chapter15.service;

import java.util.List;

import chapter15.vo.Message;

//페이징 처리를 위한 전용 클래스
public class MessageListView {
	
	private int messageTotalCount;	//전체 레코드 개수(카디널리티)
	private int currentPageNumber;	//현재 페이지 번호
	private List<Message> messageList;	//select 결과
	private int pageTotalCount;		//페이지의 전체 개수
	private int messageCountPerPage;	//페이지 별 행의 개수
	private int firstRow;			//현재 페이지 목록의 시작 행
	private int endRow;				//현재 페이지 목록의 끝 행
	
	public MessageListView(int messageTotalCount, int currentPageNumber, List<Message> messageList, 
			int messageCountPerPage, int firstRow, int endRow) {
		this.messageTotalCount = messageTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.messageList = messageList;
		this.messageCountPerPage = messageCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		
		//전체 페이지 수 구하기
		caculatePageTotalCount();
	}
	
	private void caculatePageTotalCount() {
		//만약 select 결과가 없으면.. messageTotalCount = 0
		if(messageTotalCount==0) {
			pageTotalCount = 0;
		}else {
			//전체 페이지 수 = 전체 행의 수 / 1페이지 당 화면에 보여줄 행의 개수 => 정수 / 정수 = 정수  : 문제 발생
			pageTotalCount = messageTotalCount / messageCountPerPage;
			//ex) 32행 / 1페이지당 5행을 보여주려 함 => 2가 남아서 총 7페이지로 처리되어야 함.
			if(messageTotalCount%messageCountPerPage>0) {
				pageTotalCount++;
			}
		}
		
	}
	
	public int getMessageTotalCount() {
		return messageTotalCount;
	}
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	public List<Message> getMessageList() {
		return messageList;
	}
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	public int getMessageCountPerPage() {
		return messageCountPerPage;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public int getEndRow() {
		return endRow;
	}
	//select 결과가  비어있는지  체크
	public boolean isEmpty() {
		return messageTotalCount == 0;
	}
		
	
	
}
