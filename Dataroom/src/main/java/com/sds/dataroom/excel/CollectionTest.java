package com.sds.dataroom.excel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * Collection Framework 이란?
 * -객체를 모아서 처리할때 유용한 기능을 지원하는 자바의 api
 * -java.util 패키지에서 지원
 * 
 * 종류 - 3 가지 모두 오직 객체 자료형만을 다룬다 즉 기본자료형 x
 * 1) List형 : 
 * 
 * 2) Set형 : 
 * 
 * 3) Map형 : Session
 * */
public class CollectionTest {

	public static void main(String[] args) {
		// 순서 상관 없이 모여진 모습을 표현한 api가 바로 Set이다
		// ex) 과자 봉퉁에 들어있는 과자

		Set set = new HashSet();
		set.add("사과");
		set.add("딸기");
		set.add("바나나");
		set.add("포도");
		set.add("레몬");

		Iterator<String> it = set.iterator();
		
		//현재 내위치 에서 다음요소에 객체가 존재 한다면 true, 없다면 false 반환
		while(it.hasNext()) {
			String fruit = it.next();
			System.out.println(fruit);
		}
	}
}
