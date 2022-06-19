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


//로그인이 되어있는 상태인지 어떻게 아나?? -->특정브라우저에 접속 시 브라우저에게 유니크한아이디를 준다. (특수한 공간 : 세션)  --> (브라우저마다 동시 접속 막는다 , 시간이 지나면 세션을 비교하여 로그인 상태를 변경?) 로그인 정보는 각각의 자기 세션에 저장

//세션에 많은정보를 주면안된다.

/*
	* 요청			컨트롤러					모델						뷰

		접속허가체크) 로그아웃 상태
Login.jsp			InsertMemberController.doGet()	 							InsertMember.jsp
InsertMember.jsp		InsertMemberController.doPost()		MemberDao.insertMember()				redirect -> LoginController     ##여기까지##

		접속허가체크) 로그인 상태
페이지에서 memberId클릭	SelectMemberOneController.doGet()		MemberDao.selectMemberOne()			MemberOne.jsp
	
		접속허가체크) 로그인 상태
MemberOne.jsp		UpdateMemberPwController.doGet()								UpdateMemberPw.jsp	
			
UpdateMemberPw.jsp	UpdateMemberPwController.doGet()		MemberDao.updateMemberPw()			redierct -> LogoutController

		접속허가체크) 로그인 상태
MemberOne.jsp		DeleteMemberController.doGet()								DeleteMember.jsp
DeleteMember.jsp		DeleteMemberController.doPost()		MemberDao.deleteMember()				redierct -> LogoutController


기타 이슈
cashbook insert시 FK memberId입력
cashbook select시 WHERE member_id = sessionMemberId 추가
접속허가체크) 로그인 상태에서 가능하도록 컨트롤러 수정
접속허가체크) 로그인 상태인 모든 뷰에서 sessionMemberId 출력(SelectMemberOneController 링크)


*/
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	//로그인 폼으로간다.  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//(로그인 상태 검사 코드)  --> CashbookListByMonthController로 가서 반대코드로 검사
		
		HttpSession session = request.getSession();
		String sessionMember = (String)session.getAttribute("sessionMemberId");
		
		
		if(sessionMember !=null) {																	//null이 아니면 로그인이 되어있는 상태
			response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
			
			return;  																				//if문 안에서 끝내고싶으면 return 필수
		}
		
		//로그인 되어있는 멤버면 리다이렉트(일단 생략)
		
		request.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
		
	}

	//로그인 액션으로 간다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 되어있는 멤버면    리다이렉트

		//받은 id와 pw 가 유효한지 비교    rs.next가 없으면 로그인 실패  있으면 성공 
		String memberId =  request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		Member member =new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);

		//모델 호출
		MemberDao memberDao = new MemberDao();
		String returnMemberId= memberDao.selectMemberByIdPw(member);
		System.out.println(returnMemberId+" <---membercontroller");
		if(returnMemberId ==null) {
			//로그인 실패시 로그인 폼 재요청
			response.sendRedirect(request.getContextPath()+"/LoginController");		  //sendRedirect -> get방식
			return;
		} 
		//로그인 성공
		HttpSession session = request.getSession(); 									//현재 연결한 클라이언트(브라우저)에대한 세션값을 받아옴
		session.setAttribute("sessionMemberId", returnMemberId);						//세션안에 세션 멤버값을 받아서 비교
		
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
		
	}

}
