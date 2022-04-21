package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;

/**
 * Servlet implementation class SearchResultByTagController
 */
@WebServlet("/SearchResultByTagController")
public class SearchResultByTagController extends HttpServlet {

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
	String kind=request.getParameter("kind");
	String forwardDate=request.getParameter("forwardDate");
	String backDate=request.getParameter("backDate");
	
	HashtagDao htd = new HashtagDao();
	List<Map<String,Object>> list = htd.SerachListByTag(kind, forwardDate, backDate);
	request.setAttribute("list", list);
	request.getRequestDispatcher("/WEB-INF/view/SearchResult.jsp").forward(request, response);
	}

}
