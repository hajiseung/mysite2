package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		// @ModelAttribute UserVo userVo이게 join.jsp 페이지 에서 email쪽에 태그를 만들어준다 form:input쪽
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
		System.out.println(result);
		if (result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for (ObjectError error : list) {
//				System.out.println(error);
//			}
			model.addAllAttributes(result.getModel());
			return "/user/join";
		}
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(@RequestParam(value = "email", required = true, defaultValue = "") String email,
//			@RequestParam(value = "password", required = true, defaultValue = "") String password, HttpSession session,
//			Model model) {
//		UserVo authUser = userService.getUser(new UserVo(email, password));
//
//		if (authUser == null) {
//			model.addAttribute("result", "fail");
//			return "user/login";
//		}
//
//		// 세션 설정
//		session.setAttribute("authUser", authUser);
//		return "redirect:/";
//	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update() {
		return "user/update";
	}

	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(/* @AuthUser UserVo authUser, */@ModelAttribute UserVo userVo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		userVo.setNo(authUser.getNo());

		boolean result = userService.update(userVo);

		if (result == false) {
			return "user/update";
		}

		userVo.setPassword("");
		session.setAttribute("authUser", userVo);
		return "redirect:/";
	}

//	@RequestMapping(value = "/logout")
//	public String logout(HttpSession session) {
//		session.removeAttribute("authUser");
//		session.invalidate();
//
//		return "redirect:/";
//	}

//	@ExceptionHandler( UserDaoException.class )
//	public String handleUserDaoException() {
//		return "error/exception";
//	}
}
