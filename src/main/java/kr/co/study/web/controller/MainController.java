package kr.co.study.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.study.application.MainService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {

	private final MainService service;

	@RequestMapping(value = {"/"})
	public String index(Model model) {
		model.addAttribute("hello", "Hello World");
		model.addAttribute("userCount", service.getUserCount());
		return "thymeleaf/page/index";
	}
}
