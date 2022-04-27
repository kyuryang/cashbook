package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Member;

public class MemberDao {
	//회원가입
	public void inserMember(Member m) {
		
		 String memberId = null;
	      Connection conn = null;
	      PreparedStatement stmt = null;
	     int row=0;
	     
	      String sql = "insert into member values(?,password(?),Now())";

	      try {
	         Class.forName("org.mariadb.jdbc.Driver");
	         conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, m.getMemberId());
	         stmt.setString(2, m.getMemberPw());
	         row = stmt.executeUpdate();
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      System.out.println(row +"<-- insertmember");
	      
		
	}
	//회원수정
	public String updateMember(Member member) {					//회원 수정
		
		String sql="update member set ";
//		UPDATE stats SET cnt = cnt+1 WHERE DAY = CURDATE()
		
		return "";
	}
	//회원탈퇴
	public void deleteMemer(Member member) {
		Connection conn=null;
		PreparedStatement stmt=null;
		String sql="delete from member where member_id=? and member_pw=?";
		int row=0;
		
	      try {
		         conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
		         stmt = conn.prepareStatement(sql);
		         stmt.setString(1, member.getMemberId());
		         stmt.setString(2, member.getMemberPw());
		         row =stmt.executeUpdate();
		        
		      } catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         try {
		            conn.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		      }
		      System.out.println(row + "<--탈퇴된 회원 수 ");
		      
	}
	//회원정보
	
	
	//로그인
	   public String selectMemberByIdPw(Member member) {
		      String memberId = null;
		      Connection conn = null;
		      PreparedStatement stmt = null;
		      ResultSet rs = null;
		
		      String sql = "SELECT member_id memberId FROM member WHERE member_id=? AND member_pw=PASSWORD(?)";

		      try {
		         Class.forName("org.mariadb.jdbc.Driver");
		         conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
		         stmt = conn.prepareStatement(sql);
		         stmt.setString(1, member.getMemberId());
		         stmt.setString(2, member.getMemberPw());
		         rs = stmt.executeQuery();
		         if(rs.next()) {
		            memberId = rs.getString("memberId");
		         }
		      } catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         try {
		            conn.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		      }
		      System.out.println(memberId + "<--memberDao");
		      return memberId;
		   }
	   
	   
}
