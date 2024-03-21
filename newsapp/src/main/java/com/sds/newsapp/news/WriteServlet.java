package com.sds.newsapp.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*클라이언트의 글쓰기 요청을 받아서 오라클에 넣되, DAO한테 시키자!
 * DAO 는 서버에서 실행되거나, 클라이언트에 파라미터를 받을 능력이 없다
 * 오직 DB와의 CRUD만을 수행하는 중립적 객체(웹용이 아닌 중립적 객체)
 * */
public class WriteServlet extends HttpServlet{
	
	NewsDAO newsDAO=new NewsDAO();

	//클라이언트의 요청이 post 방식임
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//파라미터 받기
		request.setCharacterEncoding("utf-8");

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		//DAO에게 일시키기
		News news = new News(); //비어있는 DTO 인스턴스 1개 생성(뉴스 기사 채워 넣을 예정)
		news.setTitle(title); //empty 뉴스 객체에 데이터 채워 넣기
		news.setWriter(writer);
		news.setContent(content);
		
		int result = newsDAO.insert(news);
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
	}

