package com.sds.openapp.store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//맛집 목록 요청을 처리하는 서블릿
public class ListServlet extends HttpServlet {
	
	//GET 방식 요청
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String perPage= request.getParameter("perPage");
		
		System.out.println("요청 받음"+perPage);
	
	//충청도 오픈api 서버에 http 요청을 시도하자..따라서 httpurlconnection 사용하자
		String serviceURL="http://apis.data.go.kr/6430000/cbRecreationalFoodInfoService/getRecreationalFoodInfo";
		String serviceKey="nwf0qWrz2aLC2WgqnNeQzMgoh%2FYEjgldAcoBt1I2AgOwFFJ7wi5hWnVLAPlifcTJraj5gQ2tXdOHF13vvQk5gg%3D%3D";
		
		StringBuilder sb = new StringBuilder(); 
		sb.append(serviceURL);
		sb.append("?serviceKey="+URLEncoder.encode(serviceKey, "UTF-8"));
		sb.append("&perPage="+URLEncoder.encode(perPage, "UTF-8"));
		
		URL url = new URL(sb.toString());
		URLConnection urlCon = url.openConnection();
		HttpURLConnection con = (HttpURLConnection)urlCon; //down casting
		
		//HTTP header 구성
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type","application/json");
		
		//요청에 대한 응답 가져오기
		int result = con.getResponseCode();
		
		InputStream is = null;
		InputStreamReader reader = null;
		BufferedReader buffr = null;
		
		if(result>=200 && result<=300 ) {
			//서버로부터 json 문자열 읽어 들이기 --> 스트림 필요
			System.out.println("open api 서버로부터 응답을 받음");
			
			is = con.getInputStream(); //바이트 기반의 입력스트림
			reader = new InputStreamReader(is); //한자씩 읽어들임
			buffr = new BufferedReader(reader); //1줄씩 읽어들임
			
			//한줄씩 모두 읽어들여보자
			String str = null;
			while(true) {
				str = buffr.readLine();
				if(str == null) break; //루프 빠져나오기
				System.out.println(str); //우리가 보기 위함.. 응답 정보가 가진 PrintWriter에 json 문자열을 차곡차곡 쌓자
				out.print(str);
			}
			
		}
		if(is != null) is.close();
		if(reader != null) reader.close();
		if(buffr != null) buffr.close();
		
		con.disconnect();
		
	}
	
}
