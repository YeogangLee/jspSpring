package article.vo;

import java.util.Date;

//글 정보, 자바빈 클래스
public class Article {

	private int articleNo;
	private Writer writer;
	private String title;
	private Date regdate;
	private Date moddate;
	private int readCnt;
	
	public Article(int articleNo, Writer writer, String title, Date regdate, Date moddate, int readCnt) {
		this.articleNo = articleNo;
		this.writer = writer;
		this.title = title;
		this.regdate = regdate;
		this.moddate = moddate;
		this.readCnt = readCnt;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getModdate() {
		return moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	
	
}
