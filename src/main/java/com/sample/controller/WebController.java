package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

	@RequestMapping({ "/", "/welcome" })
	String home() {
		return "welcome";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(Model model) {

		return "error";
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String books(Model model) {

		return "books";
	}
	
	@RequestMapping(value = "/managementView", method = RequestMethod.GET)
	public String management(Model model) {

		return "management";
	}

}
