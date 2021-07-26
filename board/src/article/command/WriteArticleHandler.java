package article.command;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.WriteArticleService;
import article.vo.WriteRequest;
import article.vo.Writer;
import mvc.command.CommandHandler;

public class WriteArticleHandler implements CommandHandler{
	
    private static final String FORM_VIEW = "/WEB-INF/view/newArticleForm.jsp";
    
    private WriteArticleService writeService = new WriteArticleService();
    
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
       if(req.getMethod().equalsIgnoreCase("GET")) {
          return FORM_VIEW;
          
       }else if(req.getMethod().equalsIgnoreCase("POST")) {
          return processSubmit(req , res);
          
       }else {
          return null;
       }
    }
   
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
       WriteRequest writeReq = 
       new WriteRequest(
             new Writer(req.getParameter("writerId"), req.getParameter("writerName")), 
             req.getParameter("title"), 
             req.getParameter("content")
             );
          
       System.out.println("writeReq : " + writeReq.toString());
       //writeReq : jsp로부터 요청된 파라미터 정보가 담긴 그릇. writerId, writerName, title, content
       //article / article_content 테이블에 입력된 article_no를 리턴
       int newArticleNo = writeService.write(writeReq);
       req.setAttribute("newArticleNo", newArticleNo);
       
       return "/WEB-INF/view/newArticleSuccess.jsp";
       
    }
}
	