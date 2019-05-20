package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@RequestMapping("")
	public String list(Model model) {
		List<GuestbookVo> list = guestbookService.getList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}

	@RequestMapping("/add")
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		boolean result = guestbookService.add(guestbookVo);
		// 오류페이지 해줘야하나?흠..
		return "redirect:/guestbook";
	}

	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "no") long no, Model model) {
		model.addAttribute("no", no);
		return "guestbook/deleteform";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		boolean result = guestbookService.delete(guestbookVo);
		return "redirect:/guestbook";
	}

}
