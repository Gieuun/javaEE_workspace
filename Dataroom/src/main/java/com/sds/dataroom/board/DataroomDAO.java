package com.sds.dataroom.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sds.dataroom.common.PoolManager;

//dataroom 테이블에 대한 CRUD만을 수행하는 DAO 정의 
public class DataroomDAO {
	PoolManager pool=PoolManager.getInstance();//싱글턴으로 선언된 객체의 인스턴스 얻기
	
	//모든 레코드 가져오기 
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List list = new ArrayList();
		
		con=pool.getConnection();
		
		String sql="select * from dataroom order by dataroom_idx desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); //쿼리 실행 및 표 반환 
			//rs를 제거하기 전에 rs에 있는 내용들을 꺼내어, 레코드 1건마다 DTO와 매핑하자 
			
			while(rs.next()) { //커서 한칸 내리기 
				Dataroom dto = new Dataroom(); //empty 빈 상태의 DTO생성
				dto.setDataroom_idx(rs.getInt("dataroom_idx"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setHit(rs.getInt("hit"));
				dto.setFilename(rs.getString("filename"));
				
				list.add(dto); //리스트에 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt, rs);
		}
		return list;
	}
	
	//레코드 1건 가져오기 
	
	//1건 등록
	public int insert(Dataroom dataroom) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con = pool.getConnection(); //접속이 아닌, 대여이다 
		
		String sql="insert into dataroom(dataroom_idx, title, writer, content, filename)";
		sql+=" values(seq_dataroom.nextval, ?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql); //쿼리문 객체 생성 
			pstmt.setString(1, dataroom.getTitle());
			pstmt.setString(2, dataroom.getWriter());
			pstmt.setString(3, dataroom.getContent());
			pstmt.setString(4, dataroom.getFilename());
			
			result = pstmt.executeUpdate(); //DML 실행
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt); //자원 해제
		}		
		return result;
	}
	
	//1건 수정 
	
	//1건 삭제
	
}