package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.dao.ArticleDao;
import article.vo.Article;
import jdbc.ConnectionProvider;
import util.JdbcUtil;

public class ListArticleService {

	private ArticleDao articleDao = new ArticleDao();
	
	//article 테이블로부터 목록 가져오기
	public List<Article> getArticlePage() throws SQLException {
	
		Connection conn = null; 
		
		try {
			conn = ConnectionProvider.getConnection();
			List<Article> content = articleDao.select(conn);
			
			return content;
			
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
}
