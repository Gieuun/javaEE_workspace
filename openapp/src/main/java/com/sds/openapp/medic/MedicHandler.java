package com.sds.openapp.medic;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//읽어들인 xml 문서를 분석하기 위한 핸들러.. 각 태그마다 이벤트가 발생하면 적절한 처리를 해주자
public class MedicHandler extends DefaultHandler{
	ArrayList<Hospital> list; //병원 DTO들을 담게될 리스트
	ListServlet listServlet;
	
	boolean isAddr;//주소 addr
	boolean isXPos;//위도 XPos
	boolean isYPos;//경도 YPos
	boolean isYadmNm;//병원명 yadmNm
	
	Hospital dto;
	
	public MedicHandler(ListServlet listServlet) {
		this.listServlet=listServlet;
	}
	//문서가 시작될때 호출되는 메서드 
	public void startDocument() throws SAXException {
	}
	
	//여는 태그를 발견했을때 호출되는 메서드
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		if(tag.equals("items")) {
			list = new ArrayList<Hospital>();
		}else if(tag.equals("item")) {
			dto = new Hospital(); //empty DTO 생성
		}else if(tag.equals("adrr")) { //주소 시작 태그를 지나가면..
			isAddr=true;
		}else if(tag.equals("XPos")) { //경도를 지나가면
			isXPos=true;
		}else if(tag.equals("YPos")) { //위도를 지나가면
			isYPos=true;
		}else if(tag.equals("yadmNm")) { //병원명 태그를 지나가면..
			isYadmNm=true;
		}
	}
	
	//태그 사이의 문자열을 발견하면 호출되는 메서드 
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length);
		
		//DTO에 data 담되, 현재 true 로 열려있는 태그만..
		if(isAddr) {
			dto.setAddr(data); //주소 담기 
		}else if(isXPos) {
			dto.setLongi(Double.parseDouble(data)); //경도 담기
		}else if(isYPos) {
			dto.setLati(Double.parseDouble(data)); //위도 담기 
		}else if(isYadmNm) {
			dto.setName(data); //병원 명 담기
		}
	}
	
	
	//닫는 태그를 발견했을때 호출되는 메서드 
	public void endElement(String uri, String localName, String tag) throws SAXException {
		if(tag.equals("item")) {
			list.add(dto);//하나의 병원 DTO를 list에 담기
		}else if(tag.equals("adrr")) { //주소 닫는 태그를 지나가면..
			isAddr=false;
		}else if(tag.equals("XPos")) { //경도 닫는 태그를 지나가면
			isXPos=false;
		}else if(tag.equals("YPos")) { //위도 닫는 태그를 지나가면
			isYPos=false;
		}else if(tag.equals("yadmNm")) { //병원명 닫는 태그를 지나가면..
			isYadmNm=false;
		}
	
	}
	
	//문서가 끝날때 호출되는 메서드 
	public void endDocument() throws SAXException {
		listServlet.createResponseData();
	}
}