package com.sds.newsapp.news.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sds.newsapp.news.News;
import com.sds.newsapp.news.NewsDAO;

//뉴스 기사 게시판 등록 폼
public class WriteForm extends JFrame {
	JTextField t_title, t_writer;
	JTextArea area;
	JScrollPane scroll;
	JButton bt_regist;
	NewsDAO newsDAO;

	public WriteForm() {
		//생성
		t_title = new JTextField();
		t_writer = new JTextField();
		area = new JTextArea();
		scroll = new JScrollPane();
		bt_regist = new JButton("등록");
		newsDAO = new NewsDAO(); //DB 연동객체 생성
		
		//스타일 
		Dimension d = new Dimension(280, 40);
		
		t_title.setPreferredSize(d);
		t_writer.setPreferredSize(d);
		area.setPreferredSize(new Dimension(280,200));
		scroll.setPreferredSize(new Dimension(280,200));
		
		//부착
		setLayout(new FlowLayout());
		add(t_title);
		add(t_writer);
		add(scroll);
		add(bt_regist);
		
		//버튼과 리스너연결
		bt_regist.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//뉴스 기사 1건을 채워서 매개변수로 전달하자	
				
				News news = new News(); 
				//뉴스를 담을 DTO 생성
				//객체 지향적으로, 자료를 담을때 배열은 쓰지말자
				
				news.setTitle(t_title.getText()); //제목 채우기
				news.setWriter(t_writer.getText()); //작정사 채우기
				news.setContent(area.getText()); //내용 채우기
				
				int result = newsDAO.insert(news); //채워진 news(DTO) 전달
				if(result>0) {
					JOptionPane.showMessageDialog(WriteForm.this, "등록 성공");
				}else {
					JOptionPane.showMessageDialog(WriteForm.this, "등록 실패");
				}
			}
		});
		
		//윈도우 설정
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new WriteForm();
	}
}


