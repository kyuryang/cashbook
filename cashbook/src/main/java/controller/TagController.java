package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.*;
import java.util.*;
/**
 * Servlet implementation class TagController
 */
@WebServlet("/TagController")
public class TagController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashtagDao htd = new HashtagDao();
		List<Map<String,Object>> list=htd.selectTagRankList();
		request.setAttribute("list", list);
		System.out.println(list.get(0).get("kind"));
		request.getRequestDispatcher("/WEB-INF/view/TagList.jsp").forward(request, response);
		
	}
	

}
