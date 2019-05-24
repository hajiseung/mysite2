package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.PageVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> list(PageVo pageVo) {
		// limit 함수 시작위치
		if (pageVo.getCurrentPage() == 0) {
			pageVo.setCurrentPageBoardCount(0);
		} else {
			pageVo.setCurrentPageBoardCount((pageVo.getCurrentPage() - 1) * PageVo.getCountPerBlock());
		}
		// 페이지 현 위치의 마지막페이지 보여주는곳
		if (pageVo.getCurrentPage() % 5 != 0) {
			pageVo.setListLast(5 * ((pageVo.getCurrentPage() / 5) + 1));
		} else {
			pageVo.setListLast((5 * ((pageVo.getCurrentPage() / 5) + 1)) - 5);
		}
		pageVo.setListFirst(1 + (PageVo.getCountPerBlock() * ((pageVo.getListLast() / PageVo.getCountPerBlock()) - 1)));

		if (pageVo.getListLast() > pageVo.getTotalcountPage()) {
			pageVo.setListLast(pageVo.getTotalcountPage());
		}

		List<BoardVo> result = boardDao.getList(pageVo);
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
