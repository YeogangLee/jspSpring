package article.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ListArticleService;
import article.vo.Article;
import mvc.command.CommandHandler;

public class ListArticleHandler implements CommandHandler {

	private ListArticleService listService = new ListArticleService();
	
	//http://localhost:8090/article/list.do
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		List<Article> articlePage = listService.getArticlePage();
		req.setAttribute("articlePage", articlePage);
		
		return "/WEB-INF/view/listArticle.jsp";
	}

	
}
