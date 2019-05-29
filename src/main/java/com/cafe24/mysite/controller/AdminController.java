package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Auth(value = "ADMIN", test = 1)
//@Auth(role=Auth.Role.ADMIN)

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping({ "", "/main" })
	public String main() {
		return "admin/main";
	}

	@RequestMapping("/user")
	public String user() {
		return "";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

}
