package article.vo;

//글 내용. 자바빈 클래스
public class ArticleContent {

	private int articleNo;
	private String content;
	
	public ArticleContent(int articleNo, String content) {
		this.articleNo = articleNo;
		this.content = content;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
