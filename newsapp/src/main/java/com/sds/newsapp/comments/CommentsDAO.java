package com.sds.newsapp.comments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//이 클래스는, 오직 데이터베이스 테이블에 대한 CRUD 만을 수행하는 로직만 들어있어야 한다..
//이렇게 데이터베이스에 대한 CRUD만을 수행하는 객체를 가리켜 어플리케이션 설계 분야에서는 DAO
//Data Access Object 라 한다
public class CommentsDAO {
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="shop";
	String pass="1234";
	

	//댓글 1건 등록 
	public int insert(Comments comments) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0; //쿼리 실행 결과를 담기 위한 변수
		
		try {
			Class.forName(driver); //드라이버 로드 
			
			con = DriverManager.getConnection(url, user, pass); //접속 
			
			String sql="insert into comments(comments_idx, msg, cwriter, news_idx)";
			sql += " values(seq_comments.nextval, ?,?,?)";
			
			pstmt =con.prepareStatement(sql); //쿼리 객체 생성
			
			//바인드 변수 대입
			pstmt.setString(1, comments.getMsg());
			pstmt.setString(2, comments.getCwriter());
			pstmt.setInt(3, comments.getNews_idx());
			
			result = pstmt.executeUpdate(); //쿼리실행 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
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
	
}