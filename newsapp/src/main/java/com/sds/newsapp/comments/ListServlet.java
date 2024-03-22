package com.sds.newsapp.comments;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트의 댓글 요청을 처리하는 서블릿 
public class ListServlet extends HttpServlet{
	
	CommentsDAO commentsDAO;
	
	public ListServlet() {
		commentsDAO =new CommentsDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//파라미터 받기 
		String news_idx = request.getParameter("news_idx");
		
		System.out.println("클라이언트가 전송한 news_idx 는 "+news_idx);
		
		List<Comments> list = commentsDAO.selectAllByFkey(Integer.parseInt(news_idx));
		
		//HTTP 통신에서 객체 자체를 보낼 수 없다.. 특히 아래의 코드는 그냥 변수이다~~! 목록 그 자체가 아님
		//HTT 통신에서는 , 모두 문자열로 취급하여, 게시물을 전송하려면 해당 게시물을 문자열화 시켜서 
		//풀어서 보내줘야 함 
		//결론) List안에 들어있는 DTO들을 다 꺼내어 그 안에 들어있는 데이터들을 문자열로 전송해야 함.. 
		//out.print("");
		
		//json 자체를 자바 코드에서 사용하지 못한다.. 왜?json은 문자열 데이터 포맷일 뿐 언어가 아니므로 
		//따라서 java 에서 json을 다루고 싶다면, 문자열로 취급해야 한다..
		
		StringBuilder sb=new StringBuilder(); 
		sb.append("{");
		sb.append("\"commentsList\" : [");
		
		for(int i=0;i<list.size();i++) { 
			//댓글 수 만큼 반복문 돌리되,  size-1 보다 작은 동안 만 쉼표로 구분해주고, 반대경우 쉼표X
			
			Comments comments = list.get(i);//리스트에서 DTO 1건 꺼내기
			
			sb.append("{");
			sb.append("\"comments_idx\": "+comments.getComments_idx()+",");
			sb.append("\"msg\" : \""+comments.getMsg()+"\",");
			sb.append("\"cwriter\" : \""+comments.getCwriter()+"\",");
			sb.append("\"cregdate\" : \""+comments.getCregdate()+"\", ");
			sb.append("\"news_idx\" : "+comments.getNews_idx()+"");
			if( i < list.size()-1) {
				sb.append("},");
			}else {
				sb.append("}");
			}
		}		
		sb.append("]");
		sb.append("}");		
		
		out.print(sb.toString());
	}
}