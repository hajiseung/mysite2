package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String list(Model model) {
		List<BoardVo> boardVo = boardService.list();
		model.addAttribute("boardVo", boardVo);
		return "board/list";
	}

	@RequestMapping("/writeform")
	public String writeForm(HttpSession session, BoardVo boardvo) {
		UserVo vo = (UserVo) session.getAttribute("authUser");
//		로그인 안 되어있으면 로그인 화면으로 이동
		if (vo == null) {
			return "redirect:/user/login";
		}
		return "board/write";
	}

	@RequestMapping("/writeform/{no}/{order_no}")
	public String writeForm(HttpSession session, @PathVariable("no") long no, @PathVariable("order_no") int order_no,
			BoardVo boardvo, Model model) {
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		if (userVo == null) {
			return "redirect:/user/login";
		}
		BoardVo boardVo = boardService.getContent(boardvo);
		boardVo.setNo(no);
		boardVo.setOrder_no(order_no);
		model.addAttribute("vo", boardVo);
		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpSession session, @ModelAttribute BoardVo vo) {
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		BoardVo boardvo = boardService.getContent(vo);
		vo.setUser_no(userVo.getNo());

		if (boardvo != null) {
			vo.setGroup_no(boardvo.getGroup_no());
			vo.setDepth(boardvo.getDepth());
			vo.setOrder_no(boardvo.getOrder_no());

			// order_no의 갯수를 받아오자
			List<BoardVo> list = boardService.changeForOrderNo(vo);
			System.out.println(list);
			for (BoardVo result : list) {
				boardService.plusOrderNo(result);
			}
		} else {
			vo.setGroup_no(boardService.boardListCount());
		}

		boardService.insert(vo);
		return "redirect:/board";
	}

	@RequestMapping("/view/{no}")
	public String view(@PathVariable(value = "no") long no, BoardVo boardVo, Model model) {
		boardVo.setNo(no);
		boardService.upCount(boardVo);
		boardVo = boardService.getContent(boardVo);
		model.addAttribute("boardVo", boardVo);
		return "board/view";
	}

	@RequestMapping("/modifyform/{no}")
	public String modifyform(HttpSession session, @PathVariable(value = "no") long no, BoardVo boardVo, Model model) {
		UserVo vo = (UserVo) session.getAttribute("authUser");
		boardVo.setNo(no);
		boardVo = boardService.checkUserForModify(boardVo);
		if (vo == null || vo.getNo() != boardVo.getUser_no()) {
			return "redirect:/board";
		} else {
			model.addAttribute("boardVo", boardVo);
			return "board/modify";
		}
	}

	@RequestMapping("/modify/{no}")
	public String modify(@PathVariable(value = "no") long no, @ModelAttribute BoardVo boardVo, Model model) {
		boardVo.setNo(no);
		boardService.updateContentsAndTitle(boardVo);
		boardVo = boardService.checkUserForModify(boardVo);
		model.addAttribute("boardVo", boardVo);
		return "redirect:/board/view/" + boardVo.getNo();
	}

	@RequestMapping("/delete/{user_no}/{no}")
	public String delete(HttpSession session, @PathVariable(value = "user_no") long user_no,
			@PathVariable(value = "no") long no, BoardVo boardVo) {
		UserVo vo = (UserVo) session.getAttribute("authUser");
		if (vo.getNo() == user_no) {
			boardVo.setNo(no);
			boardVo.setUser_no(user_no);
			boardService.delete(boardVo);
		}
		return "redirect:/board";
	}

	// 답글쓰기
	@RequestMapping(value = "/replyform/{no}/{order_no}")
	public String reply(@PathVariable("no") long no, @PathVariable("order_no") int order_no) {
		return "redirect:/board/writeform/" + no + "/" + order_no;
	}
}
