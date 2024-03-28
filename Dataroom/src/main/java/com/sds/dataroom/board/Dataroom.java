package com.sds.dataroom.board;

import lombok.Data;

@Data
public class Dataroom {
	private int dataroom_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	private String filename;
}
