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
	public int updateMemberPw(Member member) {					//회원 비밀번호 수정
		Connection conn=null;
		PreparedStatement stmt=null;
		int row =0;
		String sql="update member set member_pw=password(?) where member_id=?";
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberPw());
			stmt.setString(2, member.getMemberId());
			row=stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return row;
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
	   
	   public Member selectMemberOne(String memberId) {
		   Connection conn = null;
		   PreparedStatement stmt = null;
		   ResultSet rs = null;
		   Member member = new Member();
		   member.setMemberId(memberId);
		   
		   System.out.println(member + "<--memberDao.selectMemberOne");
		   
		   String sql = "select member_id memberId, member_pw memberPw, create_date createDate from member where member_id=?";

		      try {
		    	  conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
		         stmt = conn.prepareStatement(sql);
		         stmt.setString(1, member.getMemberId());
		         rs = stmt.executeQuery();
		         if(rs.next()) {
		        	 
		            member.setMemberPw(rs.getString("memberPw"));
		            member.setCreateDate(rs.getString("createDate"));
		            
		            }
		      } catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         try {
		            conn.close();
		            rs.close();
		            stmt.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		      }
		      System.out.println(member + "<--memberDao.selectMemberOne");
		      return member;
		   }
}
