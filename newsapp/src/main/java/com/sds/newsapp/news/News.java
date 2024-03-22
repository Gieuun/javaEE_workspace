package com.sds.newsapp.news;

/* 이 클래스는 로직을 작성하기 위함이 아닌, 오직 데이터 한건을 담기 위한 데이터 전달 객체로써
 * 정의한다...이러한 목적의 객체를 가리켜 어플리케이션 설계 분야에서는 Data Transfer Object
 * DTO라 한다..
 * */
public class News {
	private int news_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	private int cnt; //딸려있는 댓글 수
	
	public int getNews_idx() {
		return news_idx;
	}
	public void setNews_idx(int news_idx) {
		this.news_idx = news_idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}