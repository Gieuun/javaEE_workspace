package com.sds.asynboard.pattern;

/*
 * 아래의 강아지를 싱글턴(SingleTon) 패턴으로 정의하여, 외붕의 어떤 클래스가 
 * 절대로 인스턴스를 1개 이상 못 만들게 방어해보자
 * */
public class Dog {
	String name = "강아지";
	
	/*
	 * 강아지가 스스로 생성자에 대해 접근제한을 가했으므로,
	 * 인스턴스를 제공할 의무는 강아지에게 있다
	 * 따라서 강아지 자료형을 제공하는 코드를 추가하자
	 * */
	private static Dog instance;
	int x = 4;
	/*
	 *생성자를 접근제한을 적용을 해버리면 아무도 생성자
	 *호출 못한다 하지만 적어도 1개는 생성해야 하므로, 뭔가 더 보완 해야한다
	 * */
	private Dog() {
			
	}
	
	/*
	 * 외무에서 인스턴스를 만들 수 없기 때문에
	 * 객체가 스스로 인스턴스를 생성해줘야 함
	 * 싱글턴 패턴의 대표적이 모형
	 * */
	public static Dog getInstance() {
		//static으로 선언된 instance가 존재하지 않을때만 new 하자
		if(instance == null) {
			instance = new Dog();			
		}
		return instance;
	}
}

