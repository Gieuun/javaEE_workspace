package com.sds.PoolApp2.notice;

//이 클래스는 로직 작성이 아닌, 오직 레코드 1건을 담기 위한 객체이므로, 
//db의 컬럼명을 멤버변수로 동일하게 갖는다
public class Notice {
	private int notice_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	
	public int getNotice_idx() {
		return notice_idx;
	}
	public void setNotice_idx(int notice_idx) {
		this.notice_idx = notice_idx;
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
	
	
}