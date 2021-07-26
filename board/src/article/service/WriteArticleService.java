package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.management.RuntimeErrorException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.vo.Article;
import article.vo.ArticleContent;
import article.vo.WriteRequest;
import jdbc.ConnectionProvider;
import util.JdbcUtil;

public class WriteArticleService {

	//�ۼ���, ����, �ۼ���, ������...
	private ArticleDao articleDao = new ArticleDao();
	//�� ����
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	//�� �ۼ�
	//WriteRequest
	public int write(WriteRequest req) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			//insert�̹Ƿ�.. Ʈ����� ó�� (�������� commit)
			conn.setAutoCommit(false);
			
			// ---- Article ���̺� insert ���� ---- : T1
			Date now = new Date();
			//��û �Ķ���͸� ��� �ִ� �׸��� req -> DB�� �ֱ� ���� �׸��� Article�� �ű��
			Article article = new Article(0, req.getWriter(), req.getTitle(), now, now, 0);
			
			//Article ���̺�� insert
			Article savedArticle = articleDao.insert(conn, article);
			if(savedArticle == null) { //insert ���� �� ..
				throw new RuntimeException("fail to insert article");
			}
			
			//---- Article ���̺� insert �� ----
			//---- Article_content ���̺� insert ���� ---- : T2
			//int articleNo, String content
			//savedArticle ��ü�� articleDao.insert �Ŀ� return ���� Article ��ü�̹Ƿ� ���õ� ������ get���� Ȱ���ϸ� ��
			ArticleContent content = new ArticleContent(savedArticle.getArticleNo(), req.getContent());
			
			//article_content ���̺� insert
			ArticleContent savedContent = contentDao.insert(conn, content);
			if(savedContent == null) { //article_content ���̺� isnert�� �����ϸ�..
				throw new RuntimeException("fail to insert article_content");
			}
			//---- Article_content ���̺� insert �� ----
			
			//T1, T2 ��� ������ Ŀ��
			conn.commit();
			
			//article / article_content ���̺� �Էµ� article_no�� ����
			return savedArticle.getArticleNo();
			
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e; //�ڵ鷯�� throw
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			//�ڵ鷯�� throw
			throw new RuntimeException();
			
		}finally {
			JdbcUtil.close(conn); //���񽺴ϱ� conn�� close
		}
		
	}
}
