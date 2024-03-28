package com.sds.asynboard.pattern;

public class UseDog {
	
	public static void main(String[] args) {
		
//		Dog d1 = new Dog();
//		Dog d2 = new Dog();
//		
//		System.out.println(d1);
//		System.out.println(d2);
		
		//강아지가 new를 못하게 방어하고 있으므로,
		//강아지가 제공하는 static 메서드로 강아지 인스턴스르 얻어보자
	
		Dog d1 = Dog.getInstance();
		Dog d2 = Dog.getInstance();
		System.out.println(d1);
		System.out.println(d2);

	}
	
}
