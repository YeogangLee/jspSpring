package article.vo;

//작성자 정보를 담는 자바빈 클래스
public class Writer {

	private String writerId;
	private String writerName;
	
	public Writer(String writerId, String writerName) {
		this.writerId = writerId;
		this.writerName = writerName;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	
	
}
