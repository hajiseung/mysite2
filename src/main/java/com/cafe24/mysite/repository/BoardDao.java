package com.cafe24.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.PageVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public Boolean insert(BoardVo vo) {
		return 1 == sqlSession.insert("board.insert", vo);
	}

	public List<BoardVo> getList(PageVo pageVo) {
		return sqlSession.selectList("board.getlist", pageVo);
	}

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
		return sqlSession.selectOne("board.maxOrderno", group_no);
	}

	public void updateGroupAndOrder(BoardVo vo) {
		sqlSession.update("board.updateGroupAndOrder", vo);
	}

	public List<BoardVo> changeForOrderNo(BoardVo vo) {
		return sqlSession.selectList("board.changeForOrderNo", vo);

	}

	public void plusOrderNo(BoardVo result) {
		sqlSession.update("board.plusOrderNo", result);

	}

}
