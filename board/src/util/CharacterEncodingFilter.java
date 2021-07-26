package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	private String encoding;
	
	//필터를 통해서 요청 캐릭터 인코딩을 설정하므로 JSP마다 요청 캐릭터 인코딩을 설정하지 않아도 됨.
	//편리하지만, Spring 환경에서는 이 작업도 하지 않아도 된다.
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
		if(encoding == null) {
			encoding = "UTF-8";
		}
	}

	
}
