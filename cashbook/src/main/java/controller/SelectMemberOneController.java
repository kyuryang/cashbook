package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;


@WebServlet("/SelectMemberOneController")
public class SelectMemberOneController extends HttpServlet {
	private MemberDao memberDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	      String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	      if(sessionMemberId == null) {
	         // 로그인 되지 않은 경우
	         response.sendRedirect(request.getContextPath()+"/LoginController");
	         return;
	      }
		memberDao = new MemberDao();
		System.out.println(sessionMemberId+"	<--SelectMemberOneController.sessionMemberId");
		Member member =  memberDao.selectMemberOne(sessionMemberId);
		System.out.println("SelectMemberOneController.selectMemberOne"+member);
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/view/MemberOne.jsp").forward(request, response);
	}


}
