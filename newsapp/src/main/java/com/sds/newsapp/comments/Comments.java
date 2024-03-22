package com.sds.newsapp.comments;


//이 클래스는 로직을 담기 위함이 아니라, 오직 레코드 1건을 담기 위한 객체로 정의함
//이러한 데이터만을 담기 위한 목적의 객체를 가리켜 DTO라 한다
//DTO는 데이터베이스의 컬럼명을 멤버변수화 시키면 됨
public class Comments {
	private int comments_idx;
	private String msg;
	private String cwriter;
	private String cregdate;
	private int news_idx;
	
	public int getComments_idx() {
		return comments_idx;
	}
	public void setComments_idx(int comments_idx) {
		this.comments_idx = comments_idx;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCwriter() {
		return cwriter;
	}
	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}
	public String getCregdate() {
		return cregdate;
	}
	public void setCregdate(String cregdate) {
		this.cregdate = cregdate;
	}
	public int getNews_idx() {
		return news_idx;
	}
	public void setNews_idx(int news_idx) {
		this.news_idx = news_idx;
	}
	
	
	
}