package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;
import vo.Cashbook;
import vo.Hashtag;

public class CashbookDao {
	public void deleteCashbook(int cashbookNo) {
		Connection conn = null;
		PreparedStatement stmt= null;

		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");

			//삭제 쿼리문
			String sql = "delete from cashbook where cashbook_No=?";
			conn.setAutoCommit(false); 															// 커밋보류
			stmt = conn.prepareStatement(sql);										//cashbook삭제
			stmt.setInt(1,cashbookNo);
			
			
			String sql2="delete from hashtag where cashbook_no=?";
			PreparedStatement stmt2 = null;											//hashtag 삭제 
			stmt2=conn.prepareStatement(sql2);
			stmt2.setInt(1, cashbookNo);
			
			
			stmt2.executeUpdate();
			stmt.executeUpdate();												//cashbookNo를 hashtag에서 참조하고있어서 hashtag업데이트후 cashbook	 업데이트
			conn.commit();														//커밋
			
		} catch(Exception e) {
			try {
				conn.rollback();												//예외발생 시 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	public List<Cashbook> selectCashbookOne(int cashbookNo){
		List<Cashbook> list = new ArrayList<Cashbook>();
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs =null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");

			//조회 쿼리문
			String sql = "select cashbook_no cashbookNo, cash_date cashDate, kind,cash,memo, update_date updateDate,create_date createDate from cashbook where cashbook_no=?";
																				
			stmt = conn.prepareStatement(sql);		
			stmt.setInt(1,cashbookNo);
			rs=stmt.executeQuery();
			Cashbook cashbook = new Cashbook();
			if(rs.next()) {
				cashbook.setCashbookNo(rs.getInt("cashbookNo"));
				cashbook.setCashDate(rs.getString("cashDate"));
				cashbook.setKind(rs.getString("kind"));							//조회된 결과값 cashbook 객체에 저장
				cashbook.setCash(rs.getInt("cash"));
				cashbook.setMemo(rs.getString("memo"));
				cashbook.setUpdateDate(rs.getString("updateDate"));
				cashbook.setCreateDate(rs.getString("createDate"));
				list.add(cashbook);												//cashbook 형식의 list에 저장
			}

		} catch(Exception e) {
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
	public void insertCashbook(Cashbook cashbook,List<String> hashtag) {
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs =null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false);  										//<--자동 커밋을 해제

			String sql = "Insert into cashbook(cash_date,kind,cash,memo,update_date,create_date)"
						+ "values(?,?,?,?,Now(),now())";
			
			
			stmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);		//insert+ select 방금 생성된 행의 키값 (insert된 테이블의 키값을 select 한다.) 방금 입력한 cashbook_no from cashbook;
			stmt.setString(1,cashbook.getCashDate());
			stmt.setString(2,cashbook.getKind());
			stmt.setInt(3,cashbook.getCash());
			stmt.setString(4,cashbook.getMemo());
			stmt.executeUpdate();  												//insert만 실행  
			rs=stmt.getGeneratedKeys();											//select문(기본키) 저장
			int cashbookNo = 0;
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
			}
	
			PreparedStatement stmt2 = null;
			//hashtag를 저장하는 코드
			for(String h: hashtag) {			//hash태그가 있을떄만
	
				String sql2 = "insert into hashtag(cashbook_no, tag, create_date)"
							+" Values(?,?,now())";
					stmt2=conn.prepareStatement(sql2);
					stmt2.setInt(1, cashbookNo);
					stmt2.setString(2,h);
					stmt2.executeUpdate();

			}
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();												//예외발생 시 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}
			
		}
	}
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
				+ "			cash,"
				+ "         memo"
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
				map.put("cashbookNo", rs.getInt("cashbookNO"));
				map.put("day", rs.getInt("day"));
				map.put("kind", rs.getString("kind"));
	            map.put("cash", rs.getInt("cash"));
	            map.put("memo",rs.getString("memo"));
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
