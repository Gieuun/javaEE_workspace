package com.sds.asynboard.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

//글 한건에 대한 요청을 처리하는 서블릿
public class DetailServlet extends HttpServlet{
	BoardDAO boardDAO = new BoardDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8"); //응답 헤더를 json 형식으로
		PrintWriter out = response.getWriter();
		
		//파라미터 받기 
		String board_idx = request.getParameter("board_idx");
		System.out.println(board_idx);
		
		//DAO를 이용하여 한거 받기
		Board board = boardDAO.select(Integer.parseInt(board_idx));
		
		//gson을 이용하여 DTO를 json 문자열로 변환
		Gson gson = new Gson();
		String json =  gson.toJson(board);
		
		out.print(json);
		
	}
}
