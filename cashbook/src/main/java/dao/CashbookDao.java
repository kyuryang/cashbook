package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;
import vo.Cashbook;

public class CashbookDao {
	public List<Map<String,Object>> selectCashbookListByMonth(int y, int m){
		List<Map<String,Object>> list = new ArrayList<>();
		/*
			select cashbook_no cashbookNo
			,Day(cash_date) day
			,kind
			,cash
			from cashbook 
			where Year(cash_date)= ? and month(cash_date)=?
			order by day(cash_date) asc
			
		*/
		Connection conn=null;
		PreparedStatement stmt =null;
		ResultSet rs =null;
		String sql="select cashbook_no cashbookNo,"
				+ "			Day(cash_date) day,"
				+ "			kind,"
				+ "			cash"
				+ "			from cashbook"
				+ "			where Year(cash_date)= ? and month(cash_date)=?"
				+ "			order by day(cash_date) asc";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt =conn.prepareStatement(sql);
			stmt.setInt(1, y);
			stmt.setInt(2, m);
			rs=stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("cash", rs.getInt("cashbookNO"));
				map.put("day", rs.getInt("day"));
				map.put("kind", rs.getString("kind"));
	            map.put("cash", rs.getInt("cash"));
	            list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return list;
	}
}
