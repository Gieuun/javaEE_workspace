package com.sds.newsapp.map;

//상점 정보 1개를 담을 수 있는 DTO 정의
public class Store {
	private int store_idx;
	private String name;
	private double lati; //실수까지 표현해야 하므로
	private double longi; //실수까지 표현해야 하므로
	
	public int getStore_idx() {
		return store_idx;
	}
	public void setStore_idx(int store_idx) {
		this.store_idx = store_idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLati() {
		return lati;
	}
	public void setLati(double lati) {
		this.lati = lati;
	}
	public double getLongi() {
		return longi;
	}
	public void setLongi(double longi) {
		this.longi = longi;
	}
	
	
	
}