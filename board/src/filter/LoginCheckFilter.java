package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
//		req.getSess... 안 뜬다. -> Http서블릿리퀘스트가 아니므로!
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		
		//로그인 여부 검사
		if(session==null || session.getAttribute("authUser")==null) {//로그인 안 됐을 때...
//			resp.sendRedi... 안뜬다. -> Http서블릿리스폰스가 아니므로!
			HttpServletResponse response = (HttpServletResponse) resp;
			response.sendRedirect(request.getContextPath() + "/login.do");

		}else {
			chain.doFilter(req, resp);
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}
	
}
