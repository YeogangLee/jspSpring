package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

//서블릿 클래스
public class ControllerUsingURI extends HttpServlet {
	
	//커맨드, 핸들러 인스턴스 매핑 정보 저장
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		// /WEB-INF/commandHandlerURI.properties
		String configFile = getInitParameter("configFile");
		Properties prop = new Properties();
		// D:\A_TeachingMaterial\6.JspSpring\workspace\board\WebContent\WEB-INF\commandHandlerURI.properties
		String configFilePath = getServletContext().getRealPath(configFile);
		
		try {
			FileReader fis = new FileReader(configFilePath);
			prop.load(fis);
			
		}catch(IOException e) {
			throw new ServletException(e);
		}
		
		Iterator keyIter = prop.keySet().iterator();
		// /hello.do=mvc.hello.HelloHandler
		while(keyIter.hasNext()) {
			//hello.do
			String command = (String)keyIter.next();
			//mvc.hello.HelloHandler
			String handlerClassName = prop.getProperty(command);
			
			try {
				//properties파일에 등록된 핸들러 이름 String값을 진짜 핸들러 객체로 만드는 과정
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handlerInstance = (CommandHandler)handlerClass.newInstance();
				
				//키 : hello.do , 값 : mvc.hello.HelloHandler
				commandHandlerMap.put(command, handlerInstance);
			}catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
				
				/*
				 | (single pipe)
				 
				 << Difference Between | (Single Pipe) and || >>
				 : The difference is that the | (single pipe) is bitwise or operator 
				   and when it can also be used in condition design.

				 In above code snippet " | " will check every part of condition 
				 while " || " will check in sequence starting from first. 
				 If any condition in sequence is found to be true then || stops further checking. 
				 so || is more efficient in conditional statements.
				 
				 https://coderwall.com/p/s-5wcq/difference-between-single-pipe-and
				 */
				
			}
			
		}
		
	}//init()
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//http://localhost:8090/hello.do
		String command = req.getRequestURI();
		if(command.indexOf(req.getContextPath())==0) {
			//request.getContextPath() : http://localhost:8090
			//command : /hello.do
			command = command.substring(req.getContextPath().length());
		}
		//commandHandlerMap => /hello.do, mvc.hello.HelloHandler
		//handler => mvc.hello.HelloHandler 객체(인스턴스)
		CommandHandler handler = commandHandlerMap.get(command);
		if(handler==null) {
			handler = new NullHandler();
		}
		
		String viewPage = null;
		try {
			viewPage = handler.process(req, resp);
		}catch(Throwable e) {
			throw new ServletException(e);
		}
		
		if(viewPage!=null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, resp);
		}
	}
	
}
