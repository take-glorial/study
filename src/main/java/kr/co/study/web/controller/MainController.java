package kr.co.study.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.study.application.MainService;
import kr.co.study.web.dto.MainDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	@RequestMapping(value = "/ajaxTest", method = RequestMethod.POST)
	public String ajaxTest(Model model, @RequestBody MainDTO dto) {
		log.debug("dto : {}", dto);
		model.addAttribute("mainDTO", dto);
		return "thymeleaf/page/index :: #boardList";
	}

	@RequestMapping(value = "/formTest", method = RequestMethod.POST)
	public String formTest(Model model, @ModelAttribute MainDTO dto) {
		log.debug("dto : {}", dto);
		model.addAttribute("mainDTO", dto);
		return "thymeleaf/page/index :: #boardList";
	}

	@PostMapping(value = "/fetchPostTest")
	public String fetchPostTest(Model model, @RequestBody MainDTO dto) throws InterruptedException {
		log.debug("dto : {}", dto);
		model.addAttribute("mainDTO", dto);
		return "thymeleaf/page/index :: #boardList";
	}
}
