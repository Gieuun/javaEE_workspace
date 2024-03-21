package com.sds.newsapp.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//수정 요청을 처리하는 서블릿 정의 
public class EditServlet extends HttpServlet{
	NewsDAO newsDAO;
	
	public EditServlet() {
		newsDAO = new NewsDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터를 넘겨받아, 오라클 업데이트 시키자..그리고 상세 페이지 보여주자 
		//주의) 직접 오라클 연동하지 말고, 기존의 CRUD를 담당하는 객체인 DAO한테 시키자!!
		//왜? 개발의 효율성때문에...시간 == 돈... 
		response.setContentType("text/html;charset=utf-8"); //page 지시영역과 동일	
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("utf-8"); //다국어 깨지지 않도록 인코딩
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String news_idx = request.getParameter("news_idx");
		
		System.out.println("title is "+title);
		System.out.println("writer is "+writer);
		System.out.println("content is "+content);
		System.out.println("news_idx is "+news_idx);
		
		//update news set title=?, writer=?, content=? where news_idx=?
		
		//DAO에게 update수행 시키자!!
		News news  = new News(); //비어있는 DTO 1개 생성
		
		//DAO에게 파라미터 4개를 전달하기 위해 DTO를 채운다
		news.setNews_idx(Integer.parseInt(news_idx)); //pk 채우기 
		news.setTitle(title); //제목 채우기
		news.setWriter(writer); //작성자 채우기
		news.setContent(content); //내용 채우기 
		
		int result = newsDAO.update(news); //DAO에게 수정하도록 시키고, 그 결과를 받아오자
		
		out.print("<script>");
		if(result >0){
			out.print("alert('수정 성공');");
			out.print("location.href='/news/content.jsp?news_idx="+news_idx+"';");
		}else {
			out.print("alert('수정 실패');");
			out.print("history.back();");
		}
		out.print("</script>");

		
	}
}