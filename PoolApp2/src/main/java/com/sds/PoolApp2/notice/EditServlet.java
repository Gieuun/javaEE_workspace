package com.sds.PoolApp2.notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//글 수정 요청을 처리하는 서블릿
public class EditServlet extends HttpServlet{
	
	//클라이언트의 요청이 post 이므로 dopost 재정의
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//비동기 방식의 요청을 받는게 아니므로, 클라이언트의 요청에 응답할 데이터는 일부 데이터가 아닌
		//완전한 페이지를 만들어 응답 정보를 구성해야 한다. 즉 사용자가 원하는 것은 현재 보고 있는 페이지가
		//아닌 다른 페이지를 원하는 것이다.
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//파라미터 받기
		
		//update notice set title =?, writer = ?, content=? where notice_idx = ?;
		
	}
}
