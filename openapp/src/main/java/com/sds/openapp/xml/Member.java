package com.sds.openapp.xml;

import lombok.Data;
import lombok.Getter;

//사원 1명을 담기 위한 DTO
//Lombok:getter settr를 자동으로 만들어 주는 라이브러리

//jdk 1.5 (5 version) 부터 프로그래밍 언어 내에서 사용되는 주석을 지원하는데, 이러한 주석을 가리켜
// 어노테이션(Annotation) 이라하면 @로 표기한다.

@Data
public class Member {
	private int empno;
	private String ename;
	private int sal;
	
}
