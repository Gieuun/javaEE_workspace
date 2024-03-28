package com.sds.openapp.xml;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/*
 * 자바 뿐만 아니라, 일반적인 응용프로그램에서 xml은 문서이기 때문에 직접 데이터를 주고 받을 수 없다
 * 즉, 해석의 과정이 필요한데, 이러한 해석, 분석 과정을 파싱(Pasing)이라한다
 * JAVA에서 XML을 파싱하는 방법은 크게 2가지가 있다
 * 
 * 1)DOM : 태그 마다 1:1 대응되는 객체를 메모리에 올려놓고 그 객체를 통해 문서를 간접 접근하는 방식
 * 			  이 방식은 PC는 메모리가 크기 때문에 상관없지만, 서비스스를 제공받는 클라이언트가 스마트폰이나
 * 			  메모리가 적은 디바이스라면 사용되지 않는다
 * 
 * 2)SAX  :  태그를 메모리에 올리지 않고, 실행부가 태그를 순차적으로 접근하면서 다양한 이벤트를
 * 			   발생시키느 방법, 이 이벤트마다 개발자는 적절한 처리를 하면 됨
 * */
public class ParseMain {

	public static void main(String[] args) {
		//자바의 파싱 방법 중 SAX 파싱을 위한 객체인 SAXparser 객체를 사용해보자
		//SAXParser는 SAXParserFactory 라는 객체를 통해 인스턴스를 얻어와야 한다
		
		//팩토리의 인스턴스 얻기
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		//xml의 위치를 C, D 드라이브 같은 위도우 기반의 경로가 아닌 클래스 패스를 기준으로 가져와보자
		//추후 이 프로그램을 모든 OS에서 실행할 수 있게 끔 플렛폼 중립적으로 개발하기 위해
		//URL은 자원의 위치를 의미한다
		URL url = ClassLoader.getSystemResource("member.xml");
		System.out.println(url);
		URI uri = null;
		try {
			uri =	url.toURI();
			System.out.println(uri.toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		//팩토리로 부터, 파싱을 담당하는 객체인 SAXParser를 얻자
		MyHandler handler = null;
		try {
			SAXParser parser = factory.newSAXParser();//파서의 인스턴스 얻기
			parser.parse(uri.toString(), handler = new MyHandler());
			//결과 보고
			for(Member member : handler.list) {
				System.out.println("사원번호: "+member.getEmpno()+", 이름:"+member.getEname()+", 급여: "+member.getSal());
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
