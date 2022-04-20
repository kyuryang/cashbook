package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
import vo.Cashbook;

/**
 * Servlet implementation class InsertCashbookController
 */
@WebServlet("/InsertCashbookController")
public class InsertCashbookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String y = request.getParameter("y");						
		String m = request.getParameter("m");
		String d = request.getParameter("d");
		String cashDate = y+"-"+m+"-"+d;
		request.setAttribute("cashDate", cashDate);
		
		request.getRequestDispatcher("/WEB-INF/view/InsertCashbookForm.jsp").forward(request, response);			//결과가 없으면 forward 있으면 include   getContextPath 안붙여도된다.
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cashDate = request.getParameter("cashDate");
		String kind = request.getParameter("kind");
		int cash =Integer.parseInt(request.getParameter("cash"));
		String memo = request.getParameter("memo");
		
		System.out.println(cashDate + "<--cashDate InsertCashbookController");
		System.out.println(kind + "<--kind InsertCashbookController");
		System.out.println(cash + "<--cash InsertCashbookController");
		System.out.println(memo + "<--memo InsertCashbookController");			
		
		Cashbook cashbook = new Cashbook();
		cashbook.setCashDate(cashDate);
		cashbook.setKind(kind);
		cashbook.setCash(cash);
		cashbook.setMemo(memo);
		
		
		//태그                  <--model이지만 dao는 db를 이용할떄 사용하는거라 controller에 둔다.
		//issue 
		// 1)) #앞에 공백이 없을 때 "안녕하세요#구디 아카데미 #자바#자바 과정입니다."			==> #에 replace를 사용하여 " #"으로 한다.  -->3) 공백문자가 해쉬태그에 등록된다.           
		// 2) ##문자가 있을 때 " 안녕하세요 ##구디 아카데미 #자바 과정입니다."				==> 
		
		
		List<String> hashtag =new ArrayList<>();
		String memo2 = memo.replace("#"," #");   //새로 저장		//자바의 문자는 수정이 불가  ==> 새로만들어진다.
		String[] arr = memo2.split(" ");   //공백을 기준으로 문자를 나눈다.
		for(String s: arr) {
			if(s.startsWith("#")){			//#으로 시작하는 단어는
				String temp = s.replace("#", "");	//#을 공백으로 바꿔준다
				if(temp.length()>0) {		//길이가 0 즉 공백이 아닌것은
					hashtag.add(temp);
				}
			
			}
		}
		System.out.println(hashtag.size() + " <-- hashtag.size() InsertCashbookController");
		for(String h : hashtag) {
			System.out.println(h+"  <--hashtag ");
		}
		
		CashbookDao cashbookDao =new CashbookDao();
		cashbookDao.insertCashbook(cashbook, hashtag);
		
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}

}
