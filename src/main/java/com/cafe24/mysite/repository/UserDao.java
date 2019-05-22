package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {
//	@Autowired
//	private DataSource dataSource;
	@Autowired
	private SqlSession sqlsession;
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

	public Boolean insert(UserVo vo) {
		System.out.println(vo);
		int count = sqlsession.insert("user.insert", vo);
		System.out.println(vo);
		return count == 1;
		/*
		 * Boolean result = false; Connection conn = null; PreparedStatement pstmt =
		 * null;
		 * 
		 * try { conn = dataSource.getConnection();
		 * 
		 * String sql = "insert into user values(null,?,?,?,?,now())"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1, vo.getName());
		 * pstmt.setString(2, vo.getEmail()); pstmt.setString(3, vo.getPassword());
		 * pstmt.setString(4, vo.getGender());
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

	public Boolean update(UserVo vo) {
		return 1 == sqlsession.update("user.update", vo);

		/*
		 * Boolean result = false; Connection conn = null; PreparedStatement pstmt =
		 * null;
		 * 
		 * try { conn = dataSource.getConnection();
		 * 
		 * String sql = "update user set name=? ,password=? ,gender=? where no=?"; pstmt
		 * = conn.prepareStatement(sql); pstmt.setString(1, vo.getName());
		 * pstmt.setString(2, vo.getPassword()); pstmt.setString(3, vo.getGender());
		 * pstmt.setLong(4, vo.getNo());
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

	// MeasureExecutionTimeAspect로 AOP해줌
//	public UserVo get(String email) throws UserDaoException {
//		StopWatch sw = new StopWatch();
//		sw.start();
//		Map<String, String> map = new HashMap<>();
//		map.put("email", email);
//		UserVo userVo = sqlsession.selectOne("user.getByEmail", map);
//		sw.stop();
//		Long totalTime = sw.getTotalTimeMillis();
//		System.out.println(totalTime);
//		return userVo;
//	}
	
	public UserVo get(String email) throws UserDaoException {
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		UserVo userVo = sqlsession.selectOne("user.getByEmail", map);
		return userVo;
	}

	public UserVo get(String email, String password) throws UserDaoException {
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		UserVo userVo = sqlsession.selectOne("user.getByEmailAndPassword", map);
		return userVo;
		/*
		 * UserVo result = null;
		 * 
		 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
		 * 
		 * try { conn = dataSource.getConnection();
		 * 
		 * String sql = "select no, name, email from user where email=? and password=?";
		 * pstmt = conn.prepareStatement(sql); pstmt.setString(1, email);
		 * pstmt.setString(2, password);
		 * 
		 * rs = pstmt.executeQuery();
		 * 
		 * while (rs.next()) { Long no = rs.getLong(1); String name = rs.getString(2);
		 * 
		 * result = new UserVo(); result.setNo(no); result.setName(name);
		 * result.setEmail(email);
		 * 
		 * }
		 * 
		 * } catch (SQLException e) { // System.out.println("Error:" + e); throw new
		 * UserDaoException(e.getMessage()); } finally { try { if (rs != null)
		 * rs.close(); if (pstmt != null) pstmt.close(); if (conn != null) conn.close();
		 * } catch (SQLException e) { e.printStackTrace(); } }
		 * 
		 * return result;
		 */}

	public UserVo get(long userNo) {
		return sqlsession.selectOne("user.getByNo", userNo);
		/*
		 * UserVo result = null;
		 * 
		 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
		 * 
		 * try { conn = dataSource.getConnection();
		 * 
		 * String sql = "select name,email,password,gender from user where no=?"; pstmt
		 * = conn.prepareStatement(sql); pstmt.setLong(1, userNo); rs =
		 * pstmt.executeQuery();
		 * 
		 * while (rs.next()) { String name = rs.getString(1); String email =
		 * rs.getString(2); String password = rs.getString(3); String gender =
		 * rs.getString(4);
		 * 
		 * result = new UserVo();
		 * 
		 * result.setName(name); result.setEmail(email); result.setPassword(password);
		 * result.setGender(gender); }
		 * 
		 * } catch (SQLException e) { System.out.println("Error:" + e); } finally { try
		 * { if (rs != null) rs.close(); if (pstmt != null) pstmt.close(); if (conn !=
		 * null) conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		 * 
		 * return result;
		 */}
}
