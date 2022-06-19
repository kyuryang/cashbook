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

/**
 * Servlet implementation class UpdateMemberController
 */
@WebServlet("/UpdateMemberController")
public class UpdateMemberController extends HttpServlet {
	private MemberDao memberDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	      String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	      if(sessionMemberId == null) {
	         // 로그인 되지 않은 경우
	         response.sendRedirect(request.getContextPath()+"/LoginController");
	         return;
	      }
		memberDao =new MemberDao();
		Member memeber = memberDao.selectMemberOne(sessionMemberId);
		
		request.setAttribute("member", memeber);
		
		request.getRequestDispatcher("/WEB-INF/view/UpdateMember.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberDao =new MemberDao();
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));
		System.out.println(member + "	UpdateMemberController.doPost");
		memberDao.updateMemberPw(member);
		
		response.sendRedirect(request.getContextPath()+"/LogoutController");
		
	}

}
