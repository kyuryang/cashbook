package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashtagDao {
	   public List<Map<String,Object>> selectTagRankList() {							//태그 순위 출력
		      List<Map<String,Object>> list = new ArrayList<>();
		      Connection conn = null;
		      PreparedStatement stmt = null;
		      ResultSet rs = null;
		      try {
		         /*
		             SELECT t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) ranking
		            FROM 
		            (SELECT tag, COUNT(*) cnt
		            FROM hashtag
		            GROUP BY tag) t
		          */
		         Class.forName("org.mariadb.jdbc.Driver");
		         conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
		         String sql = "SELECT t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) rank"
		               + "            FROM"
		               + "            (SELECT tag, COUNT(*) cnt"
		               + "            FROM hashtag"
		               + "            GROUP BY tag) t";
		         stmt = conn.prepareStatement(sql);
		         rs = stmt.executeQuery();
		         while(rs.next()) {
		            Map<String, Object> map = new HashMap<>();
		            map.put("tag", rs.getString("tag"));
		            map.put("cnt", rs.getInt("t.cnt"));
		            map.put("rank", rs.getInt("rank"));
		            list.add(map);
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
		      return list;
		   }
	   public List<Map<String,Object>> selectCashbookListByTag(String tag) {					//태그별 캐쉬목록
		      List<Map<String,Object>> list = new ArrayList<>();
		      Connection conn = null;
		      PreparedStatement stmt = null;
		      ResultSet rs = null;
		      try {
		         /*
		           SELECT t.tag,c.kind,c.cash,c.cash_date cashDate,c.memo
					FROM hashtag t INNER JOIN cashbook c ON t.cashbook_no=c.cashbook_no
					WHERE t.tag='?'
		          */
		         Class.forName("org.mariadb.jdbc.Driver");
		         conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
		         String sql = "  SELECT t.tag,c.kind,c.cash,c.cash_date cashDate,c.memo"
		         		+ "					FROM hashtag t INNER JOIN cashbook c ON t.cashbook_no=c.cashbook_no"
		         		+ "					WHERE t.tag=? order by c.cash_Date";
		         
		         stmt = conn.prepareStatement(sql);
		         stmt.setString(1,tag);
		         rs = stmt.executeQuery();

		         while(rs.next()) {
		            Map<String, Object> map = new HashMap<>();
		            map.put("tag", rs.getString("tag"));
		            map.put("kind", rs.getString("kind"));
		            map.put("cash", rs.getInt("cash"));
		            map.put("cashDate", rs.getString("cashDate"));
		            map.put("memo", rs.getString("memo"));
		            list.add(map);
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
		      return list;
		   }
	   public List<Map<String,Object>> SerachListByTag(String kind,String forwardDate,String backDate) {				//수입,지출 별 cashbook
		   
		   System.out.println(kind+" "+forwardDate+" " + backDate +"  <-- beforesearchDao");		//디버깅
		   if(forwardDate==null || forwardDate.equals("")) {
			   forwardDate="0";															//앞날이 null이면 0값을 넣는다.
		   }
		   if(backDate==null || backDate.equals("")) {	
			   backDate="2122-12-31";														//뒷날이 null이면 지정된 날을 넣어준다.
		   }
		   System.out.println(kind+" "+forwardDate+" " + backDate +"  <-- aftersearchDao");
		      List<Map<String,Object>> list = new ArrayList<>();
		      Connection conn = null;
		      PreparedStatement stmt = null;
		      ResultSet rs = null;
		      try {
		         /*
		             SELECT t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) ranking
		            FROM 
		            (SELECT tag, COUNT(*) cnt
		            FROM hashtag
		            GROUP BY tag) t
		          */
		         Class.forName("org.mariadb.jdbc.Driver");
		         conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
		         String sql = " SELECT t.tag,c.kind,c.cash,c.cash_date cashDate,c.memo"
		         		+ "					FROM hashtag t INNER JOIN cashbook c ON t.cashbook_no=c.cashbook_no"
		         		+ "					WHERE cash_date BETWEEN ? AND ?";
		         
		         
		       
		       if(kind !=null) {
		    	   sql+=" and c.kind=?";
		    	   stmt = conn.prepareStatement(sql);
			         stmt.setString(1,forwardDate);
			         stmt.setString(2,backDate);
			         stmt.setString(3, kind);
		       }else {
		    	   
		    	   stmt = conn.prepareStatement(sql);
			         stmt.setString(1,forwardDate);
			         stmt.setString(2,backDate);
		       }
		       rs = stmt.executeQuery();
		         while(rs.next()) {
			            Map<String, Object> map = new HashMap<>();
			            map.put("tag", rs.getString("tag"));
			            map.put("kind", rs.getString("kind"));
			            map.put("cash", rs.getInt("cash"));
			            map.put("cashDate", rs.getString("cashDate"));
			            map.put("memo", rs.getString("memo"));
			            list.add(map);
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
		      return list;
		   }
	   
}
