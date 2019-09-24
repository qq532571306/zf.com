package com.entor.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class xceptionController {

	@RequestMapping("/test")
	public String test() {
		throw new RuntimeException();
		//return "index";
	}
	/*@ExceptionHandler
	public String exception(HttpServletRequest request,Exception e) {
		request.setAttribute("e", e);
		return "exception";
		
	}*/
}
