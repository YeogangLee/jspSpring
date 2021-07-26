package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import article.vo.Article;
import article.vo.ArticleContent;
import article.vo.Writer;
import util.JdbcUtil;

public class ArticleDao {

	public Article insert(Connection conn, Article article) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//---- article ���̺�� insert
			pstmt = conn.prepareStatement("insert into article (" + 
												"ARTICLE_NO, " + 
												"WRITER_ID, WRITER_NAME, TITLE, REGDATE, MODDATE, READ_CNT"
											  + ") values("
											  + "(SELECT NVL(MAX(ARTICLE_NO),0)+1 FROM ARTICLE), "
											  + "?, ?, ?, sysdate, sysdate, 0)");
											
			pstmt.setString(1, article.getWriter().getWriterId());
			pstmt.setString(2, article.getWriter().getWriterName());
			pstmt.setString(3, article.getTitle());
			
			//1�� return
			int insertedCount = pstmt.executeUpdate();
			
			//---- article_content ���̺�� insert
			//article ���̺� insert ���� �� 1�� ��ȯ�ǰ�
			//article ���̺� �����Ͱ� ���� insert�� �� article_content�� �����Ͱ� insert�Ǿ�� �ϹǷ�.
			if(insertedCount > 0) {
				stmt = conn.createStatement();
				//���� ���߿� article ���̺� �� article_no�� ������
				rs = stmt.executeQuery("SELECT MAX(ARTICLE_NO) FROM ARTICLE");
				if(rs.next()) { //���ʿ��� 1�� �ٶ�
					int newNum = rs.getInt(1); //MAX(ARTICLE_NO)
					return new Article(newNum, article.getWriter(), article.getTitle(), article.getRegdate(), article.getModdate(), article.getReadCnt());
//					return new ArticleContent(newNum, )
				}
			}
			return null; //��ȯ���� �ִ� �޼����̹Ƿ�, ��ȯ�ϱ� ���� return �̿�
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	//article ���̺��� ��� ��������
	public List<Article> select(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select ROW_NUMBER() OVER (ORDER BY ARTICLE_NO DESC) rnum, " + 
										  "       article_no, writer_id, writer_name, title, regdate, moddate, read_cnt " + 
										  "  from article");
			rs = pstmt.executeQuery();
			
			List<Article> result = new ArrayList<>();
			
			while(rs.next()) {
				result.add(
						new Article(rs.getInt("article_no"), 
						new Writer(rs.getString("writer_id"), rs.getString("writer_name")), 
						rs.getString("title"), rs.getDate("regdate"), rs.getDate("moddate"), rs.getInt("read_cnt")));
			}
		
			return result;
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
				
	}

}
