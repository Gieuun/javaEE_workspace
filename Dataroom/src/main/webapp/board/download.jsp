<%@page import="java.io.FileInputStream"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	
	//클라이언트가 다운로드 받기 원하는 파일명을 받아오자
	String filename = request.getParameter("filename");

	//넘어온 파일명을 이용하요 서버 측에 있는 파일 입력 스트림을 꽂자 
	// 파일의 내용을 스트림으로 읽어서 클라이언트에게 출력할 예정
	String realPath = application.getRealPath("/data/");
	FileInputStream fs = new FileInputStream(realPath+filename);
	
	out.print(realPath+filename);
	
	//스트림을 생

%>