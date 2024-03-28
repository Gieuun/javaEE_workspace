package com.sds.newsapp.map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

//오직 store 테이블에 대한 CRUD만을 담당하는 객체인 DAO 정의 
public class StoreDAO {
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="shop";
	String pass="1234";	
	
	//등록
	public int insert(Store store) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0; //쿼리 수행 결과를 담게 될 변수 
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			
			String sql="insert into store(store_idx, name, lati, longi) values(seq_store.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, store.getName());
			pstmt.setDouble(2, store.getLati());
			pstmt.setDouble(3, store.getLongi());
			
			result = pstmt.executeUpdate(); //쿼리 실행
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con !=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		return result;
	}
		
	//목록
	public List selectAll() {
		return null;
	}
	
}