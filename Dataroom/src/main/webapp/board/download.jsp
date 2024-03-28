<%@page import="java.io.OutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	
	//클라이언트가 다운로드 받기 원하는 파일명을 받아오자 
	String filename = request.getParameter("filename");

	//넘어온 파일명을 이용하여 서버 측에 있는 파일에 입력스트림 꽂자!! 왜? 파일의 내용을 스트림으로 
	//읽어서 클라인트에게 출력할예정이므로..
	//application 내장 객체는 javaee api 에서의 자료형은 ServletContext 이다
	String realPath = application.getRealPath("/data/"); 
	File file = new File(realPath+filename);
	
	FileInputStream fis=new FileInputStream(file);
	//out.print(realPath+filename);
	
	//스트림을 생성했으니, 응답정보를 구성해보자, 응답정보는 HTTP 프로토콜에 의해 머리와 몸체로 구성
	
	//어플리케이션 내장객체로 하여금 마임타입을 조사하라
	//text/html,  applicaltion/json,  image/jpeg..., 알수없는 마임타입도 있다..
	String mimeType = application.getMimeType(realPath+filename);
	out.print("당신이 선택한 파일의 마임타입은 "+mimeType);
	
	//표준을 벗어난 알수없는형식의 마임타입의 경우엔 getMimeType() 가  null 을 반환함 
	if(mimeType==null){ //일반적으로 바이너리 파일과 알수없는 형식의 마임타입 
		mimeType="application/octet-stream";		
	}
	response.setContentType(mimeType);
	
	//응답헤더 구성하기	
	response.setHeader("Content-Disposition", "attachment; filename=\""+filename+"\"");
	
	//다운 받게 될 파일의 용량 
	response.setContentLength((int)file.length()); //필수 아님
	
	//빨대꽂은 입력스트림으로 데이터를 읽어마시면서, 클라이언트에게 출력 
	//자료실에서 다루는 파일들은 , 바이너리 파일 이므로 바이트 기반의 출력스트림으로 처리..
	OutputStream os=response.getOutputStream();
	
	int data=-1;
	
	while(true){
		data = fis.read(); //1byte 읽기
		if(data==-1)break;
		os.write(data);
	}
	fis.close();
	os.close();
%>