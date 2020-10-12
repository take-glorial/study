package kr.co.study.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.study.application.MainService;
import kr.co.study.web.dto.MainDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

	private final MainService service;

	@RequestMapping({"/"})
	public String index(Model model) {
		model.addAttribute("hello", "Hello World");
		model.addAttribute("userCount", service.getUserCount());
		return "thymeleaf/page/index";
	}

	//(Setter 필요)ModelAttribute는 전달받은 파라미터들을 JavaObject로 매핑시키는 것이기 때문
	@PostMapping("/formTest")
	public String formTest(Model model, @ModelAttribute MainDTO dto) {
		log.debug("dto : {}", dto);
		model.addAttribute("mainDTO", dto);
		return "thymeleaf/page/index :: #boardList";
	}

	//(Setter 필요)ModelAttribute는 전달받은 파라미터들을 JavaObject로 매핑시키는 것이기 때문
	@RequestMapping("/ajaxTest")
	public String ajaxTest(Model model, @ModelAttribute MainDTO dto) {
		log.debug("dto : {}", dto);
		model.addAttribute("mainDTO", dto);
		return "thymeleaf/page/index :: #boardList";
	}

	//(Setter 불필요)POST방식으로 Json의 형태로 넘겨온 데이터를 (MessageConverter가)객체로 바인딩
	@PostMapping("/fetchPostTest")
	public String fetchPostTest(Model model, @RequestBody MainDTO dto) {
		log.debug("dto : {}", dto);
		model.addAttribute("mainDTO", dto);
		return "thymeleaf/page/index :: #boardList";
	}

	//(Setter 필요)ModelAttribute는 전달받은 파라미터들을 JavaObject로 매핑시키는 것이기 때문
	@GetMapping("/fetchGetTest")
	public String fetchGetTest(Model model, @ModelAttribute MainDTO dto) {
		log.debug("dto : {}", dto);
		model.addAttribute("mainDTO", dto);
		return "thymeleaf/page/index :: #boardList";
	}
}
