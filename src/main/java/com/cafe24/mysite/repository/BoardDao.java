package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.1.191:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		return conn;
	}

	public Boolean insert(BoardVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into board values(null,?,?,now(),?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getUserNo());
			int count = pstmt.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();

				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BoardVo> getList() {
		List<BoardVo> result = new ArrayList<BoardVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select no,title,contents,regDate from board";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String regDate = rs.getString(4);
				BoardVo boardVo = new BoardVo();
				boardVo.setNo(no);
				boardVo.setTitle(title);
				boardVo.setContent(content);
				boardVo.setRegDate(regDate);

				result.add(boardVo);
			}

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
