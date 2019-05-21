package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.CacheNamespace;
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
	public String writeForm(HttpSession session) {
		UserVo vo = (UserVo) session.getAttribute("authUser");

//		로그인 안 되어있으면 로그인 화면으로 이동
		if (vo == null) {
			return "redirect:/user/login";
		}

		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpSession session, @ModelAttribute BoardVo vo) {
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		vo.setUser_no(userVo.getNo());
		vo.setGroup_no(boardService.boardListCount());
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
		boardVo.setNo(no);
		UserVo vo = (UserVo) session.getAttribute("authUser");
		boardVo = boardService.checkUserForModify(boardVo);
		System.out.println(vo);
		System.out.println(boardVo);
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
}
