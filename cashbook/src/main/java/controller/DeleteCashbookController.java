package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;

/**
 * Servlet implementation class DeleteCashBookController
 */
@WebServlet("/DeleteCashbookController")
public class DeleteCashbookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cashbookNo= Integer.parseInt(request.getParameter("cashbookNo"));	//cashbookNo값 요청
		System.out.println(cashbookNo+"deletecashbookController");				//cashbookNo 디버깅
		CashbookDao cashbookDao = new CashbookDao();							
		cashbookDao.deleteCashbook(cashbookNo);									//deleteCashbook함수 호출
		
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");	//삭제후 이동
	}
	
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * }
	 */

}
