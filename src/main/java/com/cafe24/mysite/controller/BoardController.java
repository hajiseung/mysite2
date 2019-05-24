package com.cafe24.mysite.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.PageVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping({ "", "?pageNo" })
	public String list(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int currentPage) {
		PageVo pageVo = new PageVo();

		// 전체 게시글 개수
		pageVo.setTotalCount(boardService.boardListCount());

		// 전체 페이지 수
		pageVo.setTotalcountPage(pageVo.getTotalCount());
		pageVo.setCurrentPage(currentPage);

		List<BoardVo> boardVo = boardService.list(pageVo);
		List<Integer> pageList = new ArrayList<Integer>();

		for (int i = 1; i <= pageVo.getTotalcountPage(); i++) {
			pageList.add(i);
		}

		model.addAttribute("boardVo", boardVo);
		model.addAttribute("pageList", pageList);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("pageFirst", pageVo.getListFirst());
		model.addAttribute("pageLast", pageVo.getListLast());

		return "board/list";
	}

	@Auth
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
			System.out.println("list="+list);
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
		if (vo == null) {
			return "redirect:/user/login";
		} else if (vo.getNo() == user_no) {
			boardVo.setNo(no);
			boardVo.setUser_no(user_no);
			boardService.delete(boardVo);
		} else {
			return "redirect:/alert";
		}
		return "redirect:/board";
	}

	// 답글쓰기
	@RequestMapping(value = "/replyform/{no}/{order_no}")
	public String reply(@PathVariable("no") long no, @PathVariable("order_no") int order_no) {
		return "redirect:/board/writeform/" + no + "/" + order_no;
	}
}
