package com.sds.PoolApp2.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//이 객체는 오직 notice 테이블에 대한 CRUD만을 담당하는 객체이며, 이외의 로직은 담지 않는다..
//결국 DB 작업만 전담한다고 하여 DAO(Data Access Object) 라 한다 
public class NoticeDAO {
	PoolManager poolManager = new PoolManager();

	// 커넥션풀로부터 Connection을 대여해와서, 글쓰기
	public int insert(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;

		con = poolManager.getConnection(); // 1개 대여

		String sql = "insert into notice(notice_idx, title, writer, content)";
		sql += " values(seq_notice.nextval, ?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getWriter());
			pstmt.setString(3, notice.getContent());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			poolManager.release(con, pstmt);
		}
		return result;
	}

	// 목록 가져오기
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();

		con = poolManager.getConnection(); // 커넥션 1개 빌리기

		String sql = "select * from notice order by notice_idx desc";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // 쿼리실행

			// rs를 대신할 객체가 필요함...
			while (rs.next()) { // 레코드 수만큼 반복하면서 rs안에 들어있는 레코드들을 꺼내어 DTO에 담고
				// 그 DTO는 다시 list 에 추가하자
				Notice notice = new Notice(); // DTO 인스턴스 생성
				notice.setNotice_idx(rs.getInt("notice_idx"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));

				list.add(notice); // 리스트에 추가하기
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			poolManager.release(con, pstmt, rs);
		}
		return list;
	}

	// 게시물 1건만 가져오기(상세보기)
	public Notice select(int notice_idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = new Notice();

		String sql = "select * from notice where notice_idx=?";
		con=poolManager.getConnection(); //대여
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_idx); //상세 보기글의 idx
			
			rs=pstmt.executeQuery(); //레코드 1건 반환
			
			//rs가 곧 죽을 예정이므로, rs가 가진 1건을 DTO 1개에 담아놓자
			if(rs.next()) {//레코드가 존재한다면
				notice = new Notice();
				//rs의 정보 DTO에 채워 넣기
				notice.setNotice_idx(rs.getInt("notice_idx"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			poolManager.release(con, pstmt, rs);
		}
		
		return notice;
	}
}