package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alert")
public class Alert {
	@RequestMapping("")
	public String WrongApproch() {
		return "alert/alert";
	}

}
