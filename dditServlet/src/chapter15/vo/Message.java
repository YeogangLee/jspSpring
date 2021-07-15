package chapter15.vo;

//자바빈 클래스
public class Message {
	
	private int messageId;
	private String guestName;
	private String password;
	private String message;
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	//방명록 메시지에 비밀번호가 지정되어 있는지 확인
	public boolean hasPassword() {
		return password!=null&&!password.isEmpty();
	}
	
	//비밀번호 값과 비밀번호 확인 값이 같은지 확인
	public boolean matchPassword(String pwd) {
		return password!=null&&password.equals(pwd);
	}
	
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", guestName=" + guestName + ", password=" + password + ", message="
				+ message + "]";
	}
	
	
}
