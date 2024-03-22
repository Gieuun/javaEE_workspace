package com.sds.newsapp.async;
/*
 * 쓰레드란?
 * 하나의 프로세스 내에서 독립적으로 실행되는 세부 실행단위이다
 * */
public class UseThread {
	
	public static void main(String[] args) {
		MyThread t= new MyThread();
		
		t.start();
		
		System.out.println("B");
		//라인의 순서와 상관없이 B가 먼저 프린트 된다
	}
}
