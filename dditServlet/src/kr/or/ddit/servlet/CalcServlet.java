package kr.or.ddit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = "/homework/w1/d2_calcJson.jsp";
		
		String valA = req.getParameter("valA");
		String valSik = req.getParameter("valSik");
		String valB = req.getParameter("valB");
		int result = 0;
		
		if("+".equals(valSik)) {
			result = Integer.parseInt(valA) + Integer.parseInt(valB); 
			req.setAttribute("result", result);
			
		}else if("-".equals(valSik)) {
			result = Integer.parseInt(valA) - Integer.parseInt(valB);
			req.setAttribute("result", result);
			
		}else if("x".equals(valSik)) {
			result = Integer.parseInt(valA) * Integer.parseInt(valB);
			req.setAttribute("result", result);
			
		}else if("/".equals(valSik)) {
			result = Integer.parseInt(valA) / Integer.parseInt(valB);
			req.setAttribute("result", result);
		}
		
		RequestDispatcher disp = req.getRequestDispatcher(url); // 결과를 받을 URL을 세팅
		disp.forward(req, resp);
		
	}
	
}
