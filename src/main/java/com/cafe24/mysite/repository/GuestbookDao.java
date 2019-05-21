package com.cafe24.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {

//	@Autowired
//	private DataSource dataSource;

	@Autowired
	private SqlSession sqlsession;

	public Boolean insert(GuestbookVo vo) {
		return 1 == sqlsession.insert("guestbook.insert", vo);
		/*
		 * Boolean result = false; Connection conn = null; PreparedStatement pstmt =
		 * null;
		 * 
		 * try { conn = dataSource.getConnection();
		 * 
		 * String sql = "insert into guestbook values(null,?,?,?,now())"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1, vo.getName());
		 * pstmt.setString(2, vo.getPassword()); pstmt.setString(3, vo.getContent());
		 * 
		 * int count = pstmt.executeUpdate();
		 * 
		 * result = count == 1; } catch (SQLException e) { System.out.println("Error:" +
		 * e); } finally { try { if (pstmt != null) pstmt.close();
		 * 
		 * if (conn != null) conn.close(); } catch (SQLException e) {
		 * e.printStackTrace(); } }
		 * 
		 * return result;
		 */}

	public Boolean delete(GuestbookVo vo) {
		return 1 == sqlsession.delete("guestbook.delete", vo);
		/*
		 * Boolean result = false; Connection conn = null; PreparedStatement pstmt =
		 * null;
		 * 
		 * try { conn = dataSource.getConnection();
		 * 
		 * String sql = "delete from guestbook where no=? and password=?"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setLong(1, vo.getNo()); pstmt.setString(2,
		 * vo.getPassword());
		 * 
		 * int count = pstmt.executeUpdate();
		 * 
		 * result = count == 1; } catch (SQLException e) { System.out.println("Error:" +
		 * e); } finally { try { if (pstmt != null) pstmt.close();
		 * 
		 * if (conn != null) conn.close(); } catch (SQLException e) {
		 * e.printStackTrace(); } }
		 * 
		 * return result;
		 */}

	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = sqlsession.selectList("guestbook.getList2");
		return list;
		/*
		 * List<GuestbookVo> result = new ArrayList<GuestbookVo>();
		 * 
		 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
		 * 
		 * try { conn = dataSource.getConnection();
		 * 
		 * String sql =
		 * "select no, name, content, date_format(reg_date,'%Y-%m-%d %h:%i:%s') from guestbook order by reg_date desc"
		 * ; pstmt = conn.prepareStatement(sql);
		 * 
		 * rs = pstmt.executeQuery();
		 * 
		 * while (rs.next()) { Long no = rs.getLong(1); String name = rs.getString(2);
		 * String contents = rs.getString(3); String regDate = rs.getString(4);
		 * 
		 * GuestbookVo vo = new GuestbookVo(); vo.setNo(no); vo.setName(name);
		 * vo.setContents(contents); vo.setRegDate(regDate);
		 * 
		 * result.add(vo); }
		 * 
		 * } catch (SQLException e) { System.out.println("Error:" + e); } finally { try
		 * { if (rs != null) rs.close(); if (pstmt != null) pstmt.close(); if (conn !=
		 * null) conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		 * 
		 * return result;
		 */}
//	dataSource가 생겨서 필요없어짐
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
}
