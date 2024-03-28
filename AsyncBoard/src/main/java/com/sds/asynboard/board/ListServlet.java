package com.sds.asynboard.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

//목록 요청을 처리하는 서블릿
public class ListServlet extends HttpServlet {
	BoardDAO boardDAO = new BoardDAO();
	
	protected void doGet(HttpServletRequest reqeust, HttpServletResponse response) throws ServletException, IOException {
		//응담 헤더 구성
		response.setContentType("aplication/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println("목록 요청 들어왔음!");
		
		//데이터 베이스 목록을 가녀와서, 클라이언트인 js가 이 해할 수 있는 데이터 형태로 변환해서 보내주자 
		//json
		List boardList = boardDAO.selectAll();
		
		//Gson 라이브러리를 이용하면 자바 Object와 JSOM 스트링과의 변환을 자유롭게 진행할 수 있다
		Gson gson = new Gson();
		String json = gson.toJson(boardList);
		
		out.println(json);
	}
}
