package mvc.hello;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class HelloHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setAttribute("hello", "¿©°­");
		return "/WEB-INF/view/hello.jsp";
	}
	
}
