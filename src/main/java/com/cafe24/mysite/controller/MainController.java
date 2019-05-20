package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.vo.UserVo;

@Controller
public class MainController {

	@RequestMapping({ "/", "/main" })
	public String main() {
		return "main/index";
	}

	@ResponseBody/*String을 메세지로 바꿔줌*/
	@RequestMapping("/hello")
	public String hello() {
		return "<h1>안녕하세요!</h1>";
	}
	
	@ResponseBody/*String을 메세지로 바꿔줌*/
	@RequestMapping("/hello2")
	public UserVo hello2() {
		UserVo userVo = new UserVo();
		userVo.setNo(10L);
		userVo.setEmail("gkgkgkgk@naver.com");
		return userVo;	}
}
