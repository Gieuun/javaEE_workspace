package com.sds.newsapp.news;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * 이 클래스건 javaEE , SE 건 상관없이 DB의 특정 테이블에 대해 CRUD
 * Create(=insert), Read(=select), Update, Delete 를  수행하는 중립적 객체로 정의하자
 * --->> 플렛폼에 독립적인 재사용 객체로 정의하기 위해
 * */
public class NewsDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "shop";
	String pass = "1234";

	// Create, insert
	// 아래의 메서드를 호풀하는 자는 1건의 뉴스기사가 이미 채워진 상태로, 매개 변수를 넘겨야한다
	@SuppressWarnings("finally")
	public int insert(News news) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName(driver);
			System.out.println("로드 성공");
			con = DriverManager.getConnection(url, user, pass);

			StringBuffer sb = new StringBuffer();
			sb.append("insert into news(news_idx, title, writer, content)");
			sb.append(" values(seq_news.nextval,?,?,?)");

			pstmt = con.prepareStatement(sb.toString()); // 쿼리객체 생성

			pstmt.setString(1, news.getTitle());
			pstmt.setString(2, news.getWriter());
			pstmt.setString(3, news.getContent());

			result = pstmt.executeUpdate(); // 쿼리실행

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return result;
		}
	}
}
