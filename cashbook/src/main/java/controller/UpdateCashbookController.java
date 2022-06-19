package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
import vo.Cashbook;

/**
 * Servlet implementation class UpdateCashbookControll
 */
@WebServlet("/UpdateCashbookController")
public class UpdateCashbookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		System.out.println(cashbookNo + "<---cashbookNo cashbookOneController");		//디버깅
		
		CashbookDao cashbookDao = new CashbookDao();		
		List<Cashbook> list = cashbookDao.selectCashbookOne(cashbookNo);				//selectCashbookOne 결과값 list에 저장
		
		request.setAttribute("list", list);												//list를 setAttribute에 저장
		
		request.getRequestDispatcher("/WEB-INF/view/updateCashbook.jsp").forward(request, response);	
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		int cash = Integer.parseInt(request.getParameter("cash"));
	}

}
