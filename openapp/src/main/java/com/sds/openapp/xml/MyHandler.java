package com.sds.openapp.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//처리할 xml 파일을 읽어들여, 각 태그마드 이벤트를 발생시키기 위한 객첼르 상속받자
//DefualtHandler를 상속 받는 순간부터, 우리가 다루려고 하는 xml 파일에 대해 이벤트를 처리할 수 있다
//마치 이벤트를 처리하는 Listener 비슷하다
public class MyHandler extends DefaultHandler {
	//태그의 이벤트가 종료되고 나면, 아래의 ArrayList는 DTO들로 채워져 있게 처리할 예정
	ArrayList<Member> list; 
	Member member;
	boolean isEmpno = false;//사원번호를 지나가는지 체크용
	boolean isEname = false;//사원 명을 지나가는지 체크용
	boolean isSal=false;//급여를 지나가는지 체크용
	
	//문서가 시작될때 호출되는 메서드
	public void startDocument() throws SAXException {
		System.out.println("문서를 시작한비다");
		//ArrayList 생성
		list=new ArrayList<Member>();
	}
	
	//여는 태그를 만나면 실행되는 메서드
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		System.out.print("<"+tag+">");
		//시작하는 태그를 만나면 한 사람에 대한 정보를 담을 준비를 해야 하므로, DTO의 인스턴스 1개를 생성하자
		if(tag.equals("member")) { // <member> 여는 태그를 만나면
			member= new Member();
		}else if(tag.equals("empno")) {//사원번호를 지나갈때
			isEmpno = true; //지나갔음을 표시
		}else if(tag.equals("ename")) {//사원이름을 지나갈때
			isEname = true; //지나갔음을 표시
		}else if(tag.equals("sal")) {//급여를를 지나갈때
			isSal = true; //지나갔음을 표시
		}
	}
	
	//태그 사이의 문자열을 만났을때 호출되는 메서드
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length);
		System.out.print(data);
		
		if(isEmpno) {
			member.setEmpno(Integer.parseInt(data)); //사원 번호 넣기
		}else if(isEname) {
			member.setEname(data); //사원 이름 넣기
		}else if(isSal) {
			member.setSal(Integer.parseInt(data));
		}
	}
	
	//닫는 태그를 만나면 호출되는 메서드
	public void endElement(String uri, String localName, String tag) throws SAXException {
		System.out.println("</"+tag+">");
		if(tag.equals("member")) { // </member> 태그를 만나면
			list.add(member); //닫는 태그를 만나면 DTO에 데이터 추가
		}else if(tag.equals("empno")) {//사원번호를 지나갈때
			isEmpno = false; 
		}else if(tag.equals("ename")) {//사원이름을 지나갈때
			isEname = false; 
		}else if(tag.equals("sal")) {//급여를를 지나갈때
			isSal = false; 
		}
	}
	
	//문서의 끝을 만나면 호출되는 메서드
	public void endDocument() throws SAXException {
		System.out.println("문서의 끝입니다");
		System.out.println("문서 검색 후 메모리에 올라간 list수는? "+list.size()+"명 담아졌습니다");
	}
}
