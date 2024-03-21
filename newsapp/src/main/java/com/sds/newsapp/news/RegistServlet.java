package com.sds.newsapp.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistServlet extends HttpServlet { // 서버에서 실행될 수 있도록 서블릿으로 정의한다
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "shop";
	String pass = "1234";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); // 한글 설정을 먼저 해야함(순서 매우 중요)
		PrintWriter out = response.getWriter(); // 스트림을 얻기 전에 한글처리

		// 클라이언트가 전송한 파라미터 받기
		request.setCharacterEncoding("utf-8");

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		System.out.println("작성자" + writer);
		System.out.println("제목" + title);
		System.out.println("내용" + content);

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 드라이버 로드
			Class.forName(driver);
			System.out.println("로드 성공");

			con = DriverManager.getConnection(url, user, pass);
			StringBuffer sb = new StringBuffer();

			if (con != null) {
				sb.append("insert into news(news_idx, title, writer, content)");
				sb.append(" values(seq_news.nextval,?,?,?)");
				/*
				 * 오라클에서는 바인드 변수 사용하려면 :변수명 이지만, jdbc 코드에서는 ?로 처리한다 바인드 변수는 자바의 개념이 아니라 DBMS의
				 * 개념이다 DBMS는 철자가 하나라도 바뀌게 되면 컴파일을 다시 실행하게 된다 쿼리문 중 값이 들어오는 부분에 대해서는 쿼리의 일부로
				 * 처리하지 말고, 바인드 변수라는 기술로 재컴파일을 방지하게 된다(DB의 성능 향상)
				 */
				pstmt = con.prepareStatement(sb.toString()); // 쿼리객체 생성
				pstmt.setString(1, title);
				pstmt.setString(2, writer);
				pstmt.setString(3, content);

				int result = pstmt.executeUpdate(); // 쿼리실행

				out.print("<script>");
				if (result > 0) {
					out.print("alert('등록 성공');");
					out.print("location.href='/news/list.jsp';");
				} else {
					out.print("alert('등록 실패');");
					out.print("history.back();");
				}
				out.print("</script>");
			}
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
		}
	}
}
