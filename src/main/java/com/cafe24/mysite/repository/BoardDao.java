package com.cafe24.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {
//	private Connection getConnection() throws SQLException {
//		Connection conn = null;
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			String url = "jdbc:mariadb://192.168.1.191:3307/webdb";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패:" + e);
//		}
//		return conn;
//	}
	@Autowired
	private SqlSession sqlSession;

	public Boolean insert(BoardVo vo) {
		return 1 == sqlSession.insert("board.insert", vo);
		/*
		 * Boolean result = false; Connection conn = null; PreparedStatement pstmt =
		 * null;
		 * 
		 * try { conn = getConnection();
		 * 
		 * String sql = "insert into board values(null,?,?,now(),?)"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1, vo.getTitle());
		 * pstmt.setString(2, vo.getContent()); pstmt.setLong(3, vo.getUserNo()); int
		 * count = pstmt.executeUpdate();
		 * 
		 * result = count == 1; } catch (SQLException e) { System.out.println("Error:" +
		 * e); } finally { try { if (pstmt != null) pstmt.close();
		 * 
		 * if (conn != null) conn.close(); } catch (SQLException e) {
		 * e.printStackTrace(); } }
		 * 
		 * return result;
		 */}

	public List<BoardVo> getList() {
		return sqlSession.selectList("board.getlist");
		/*
		 * List<BoardVo> result = new ArrayList<BoardVo>();
		 * 
		 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
		 * 
		 * try { conn = getConnection();
		 * 
		 * String sql = "select no,title,contents,regDate from board"; pstmt =
		 * conn.prepareStatement(sql);
		 * 
		 * rs = pstmt.executeQuery();
		 * 
		 * while (rs.next()) { Long no = rs.getLong(1); String title = rs.getString(2);
		 * String content = rs.getString(3); String regDate = rs.getString(4); BoardVo
		 * boardVo = new BoardVo(); boardVo.setNo(no); boardVo.setTitle(title);
		 * boardVo.setContent(content); boardVo.setRegDate(regDate);
		 * 
		 * result.add(boardVo); }
		 * 
		 * } catch (SQLException e) { System.out.println("Error:" + e); } finally { try
		 * { if (rs != null) rs.close(); if (pstmt != null) pstmt.close(); if (conn !=
		 * null) conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		 * 
		 * return result;
		 */}

	public int boardListCount() {

		return sqlSession.selectOne("board.boardListCount");
	}

	public void upCount(BoardVo boardVo) {
		sqlSession.update("board.upCount", boardVo);
	}

	public BoardVo getContent(BoardVo boardVo) {
		return sqlSession.selectOne("board.getContent", boardVo);
	}

	public BoardVo checkUserForModify(BoardVo boardVo) {
		return sqlSession.selectOne("board.checkUserForModify", boardVo);
	}

	public boolean updateContentsAndTitle(BoardVo boardVo) {
		return 1 == sqlSession.update("board.updateContentsAndTitle", boardVo);
	}

	public boolean delete(BoardVo boardVo) {
		return 1 == sqlSession.delete("board.delete", boardVo);
	}

	public int maxOrderno(int group_no) {
		return sqlSession.selectOne("board.maxOrderno",group_no);
	}

	public void updateGroupAndOrder(BoardVo vo) {
		sqlSession.update("board.updateGroupAndOrder",vo);
	}

	public List<BoardVo> changeForOrderNo(BoardVo vo) {
		return sqlSession.selectList("board.changeForOrderNo", vo);
		
	}

	public void plusOrderNo(BoardVo result) {
		sqlSession.update("board.plusOrderNo",result);
		
	}

}
