package com.sds.newsapp.comments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	
	//특정 뉴스기사에 딸려있는 댓글 목록 가져오기 
	public List selectAllByFkey(int news_idx) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List list=new ArrayList(); //Commnets DTO 의 인스턴스들을 모아놓을 컬렉션	
											//예) 게시물이 5개라면 DTO의 인스턴스도 5개를 생성하여 이 컬렉션에 넣자
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pass);
			
			String sql="select * from comments where news_idx=? order by comments_idx desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_idx); 
			rs=pstmt.executeQuery(); //쿼리수행 후 결과 반환
			
			while(rs.next()) { //커서 한칸 전진 
				//rs에 들어있는 컬럼의 값들을 DTO의 멤버변수로 옮긴다
				Comments comments = new Comments(); //비어있는 dto 생성 
				comments.setComments_idx(rs.getInt("comments_idx"));
				comments.setMsg(rs.getString("msg"));
				comments.setCwriter(rs.getString("cwriter"));
				comments.setCregdate(rs.getString("cregdate"));
				comments.setNews_idx(rs.getInt("news_idx"));
				
				//생성된 DTO를 list에 담자 
				list.add(comments);			
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	
}