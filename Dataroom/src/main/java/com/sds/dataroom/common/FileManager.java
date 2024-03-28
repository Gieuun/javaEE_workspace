package com.sds.dataroom.common;

//파일과 관련된 유용한 처리를 모아놓을 객체 
public class FileManager {

	//확장자 구하기
	public static String getExt(String path) {
		return path.substring( path.lastIndexOf(".")+1 , path.length());
	}
	
	//현재 시간으로 파일명 생성 , 확장자는 이 메서드 호출자가 넘겨줘야 함..
	public static String getNameByTime(String ext) {
		long time = System.currentTimeMillis();
		String filename = time+"."+ext;
		return filename;
	}
	/*
	public static void main(String[] args) {
		String result = getNameByTime( getExt("dshjksladj.dk.djl.png"));
		System.out.println(result);
	}
	*/
}