package com.sds.asynboard.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//글 수정 요청을 처리하는 서블릿
public class EditServlet extends HttpServlet{
	BoardDAO boardDAO = new BoardDAO();
	
	protected void doPost(HttpServletRequest reqeust, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//파라미터 받기
		String title = reqeust.getParameter("title");
		String writer = reqeust.getParameter("writer");
		String content = reqeust.getParameter("content");
		String board_idx = reqeust.getParameter("board_idx");
		
		System.out.println(title);
		System.out.println(writer);
		System.out.println(content);
		System.out.println(board_idx);
		
		//낱개로 흩어진 파라미터들을 DTO에 모으자
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setBoard_idx((Integer.parseInt(board_idx)));
		
		//DAO에게 수정 요청 시키기
		int result = boardDAO.update(board);
		if(result>0) {
			out.print("ok");
		}else {
			out.print("fali");
		}
	}
}
