package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;
import java.util.*;
/**
 * Servlet implementation class CashbookListByTag
 */
@WebServlet("/CashbookListByTagController")
public class CashbookListByTagController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashtagDao htd =new HashtagDao();
		String tag = request.getParameter("tag");
		int currentPage=1;
		if(request.getParameter("currentPage") != null) {
			currentPage=(Integer.parseInt(request.getParameter("currentPage")));
		}
		System.out.println("currentPage"+currentPage);
		int rowPerPage=10;
		int count = htd.countByTag(tag);
		int lastPage=(int) (Math.ceil((double)count/(double)rowPerPage));
		int beginRow= (currentPage-1)*rowPerPage;

		//1p 0/9 2p 10/19 3p 21/30 4p 31~40
		
		System.out.println("cashbookListbytagcontroller ->"+ tag+""+beginRow+""+rowPerPage);
		
		List<Map<String,Object>> list =htd.selectCashbookListByTag(tag,beginRow,rowPerPage);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.getRequestDispatcher("/WEB-INF/view/CashbookListByTag.jsp").forward(request, response);
	}
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { this.doGet(request,
	 * response); }
	 */

}
