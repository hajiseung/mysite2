package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> list() {
		List<BoardVo> result = boardDao.getList();

		return result;
	}

	public boolean insert(BoardVo vo) {
		return boardDao.insert(vo);
	}

	public int boardListCount() {
		return boardDao.boardListCount();
	}

	public void upCount(BoardVo boardVo) {
		boardDao.upCount(boardVo);
	}

	public BoardVo getContent(BoardVo boardVo) {
		return boardDao.getContent(boardVo);

	}

	public BoardVo checkUserForModify(BoardVo boardVo) {
		return boardDao.checkUserForModify(boardVo);
	}

	public boolean updateContentsAndTitle(BoardVo boardVo) {
		return boardDao.updateContentsAndTitle(boardVo);
	}

	public boolean delete(BoardVo boardVo) {
		return boardDao.delete(boardVo);

	}

	public int maxOrderno(int group_no) {
		return boardDao.maxOrderno(group_no);
	}

	public void updateGroupAndOrder(BoardVo vo) {
		boardDao.updateGroupAndOrder(vo);
	}

	public List<BoardVo> changeForOrderNo(BoardVo vo) {
		return boardDao.changeForOrderNo(vo);
	}

	public void plusOrderNo(BoardVo result) {
		boardDao.plusOrderNo(result);
	}

}
