package com.sds.asynboard.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sds.asynboard.board.common.PoolManager;

//이 클래스는 오직 board 테이블에 대한 CRUD를 수행하기 위한 객체이다
public class BoardDAO {
	PoolManager pool = PoolManager.getInstance();

	//모든 글 가져오기
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List list = new ArrayList();
		
		con = pool.getConnection(); //접속 x 이미 접속된 Connection 대여
		String sql = "select * from board order by board_idx desc"; //최신글 순으로(내림차순)
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			//rs를 대신할 자료형을 대체 List<Board> 형태로 대체
			while(rs.next()) {
				Board board = new Board();
				//비어있는 Board에 rs의 컬럼 값들을 채워 넣기
				board.setBoard_idx(rs.getInt("board_idx"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt, rs);
		}
		
		return list;
	}
	
	//글 한건 가져오기 , 만일 일치하는 데이터가 없을 경우  Board는  null 이 반환.. 
	//따라서 이 메서드 호출자는 널인지 아닌지에 따라 데이터가 있다 없다를 판단하면 된다..
	public Board select(int board_idx) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board board=null;
		
		con=pool.getConnection(); //접속 객체 대여
		String sql="select * from board where board_idx=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			rs=pstmt.executeQuery(); //쿼리문 실행 후, 레코드 1건 가져오기 
			if(rs.next()) { //레코드가 존재한다면..
				board = new Board(); //empty 상태
				board.setBoard_idx(rs.getInt("board_idx"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt, rs);
		}
		return board;		
	}
	
	//글 1건 등록
 	public int insert(Board board) {
		Connection con = null;
		PreparedStatement pstmt= null;
		int result = 0; // insert DML 수행 결과를 담을 변수
		
		//커넥션은 앞으로 풀매니저에게 빌려오자
		con = pool.getConnection();
		
		String sql = "insert into board(board_idx, title, writer, content)";
		sql+= " values(seq_board.nextval, ?,?,?)";

		try {
			pstmt =con.prepareStatement(sql);
			//바인드 변수 제목, 작성자, 내용 board 에서 꺼내서 변수 대입
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			//쿼리 실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
		return result; // 결과 반환
	}

 	//게시물 1건 수정하기
 	public int update(Board board) {
 		Connection con = null;
 		PreparedStatement pstmt=null;
 		
 		int result = 0;
 		
 		con=pool.getConnection();
 		String sql = "update board set title=?, writer=?, content=? where board_idx=?";
 		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,board.getTitle());
			pstmt.setString(2,board.getWriter());
			pstmt.setString(3,board.getContent());
			pstmt.setInt(4,board.getBoard_idx());
			
			result = pstmt.executeUpdate(); //쿼리 실행
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
 		
		return result;
	}
}


	
