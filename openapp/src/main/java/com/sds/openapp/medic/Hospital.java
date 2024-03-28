package com.sds.openapp.medic;

import lombok.Data;

// XML에서, 추출하고 싶은 컬럼만 골라서 알래의 DTO에 속성으로 추가하자
@Data
public class Hospital {
	
	private String name;
	private String addr;
	private double lati;
	private double longi;
	
}
