package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@RequestMapping("")
	public String list() {
		return "board/list";
	}
	
	@RequestMapping("/writeform")
	public String writeForm() {
		return "board/write";
	}
	
	@RequestMapping("/write")
	public String write() {
		return null;
	}
}
