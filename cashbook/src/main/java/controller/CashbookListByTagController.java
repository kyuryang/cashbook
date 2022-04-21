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

		String tag = request.getParameter("tag");
		System.out.println("cashbookListbytagcontroller ->"+ tag);
		HashtagDao htd =new HashtagDao();
		List<Map<String,Object>> list =htd.selectCashbookListByTag(tag);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/CashbookListByTag.jsp").forward(request, response);
	}
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { this.doGet(request,
	 * response); }
	 */

}
