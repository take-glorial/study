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
import kr.co.study.web.dto.MainFormDTO;
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

	//ModelAttribute는 전달받은 파라미터들을 JavaObject로 매핑시키는 것이기 때문
	//(기본생성자+Setter)
	//(모든생성자)
	@PostMapping("/ajaxPostTest")
	public String ajaxPostTest(Model model, @ModelAttribute MainFormDTO dto) {
		log.debug("dto : {}", dto);
		model.addAttribute("mainDTO", dto);
		return "thymeleaf/page/index :: #pageTest";
	}

	//ModelAttribute는 전달받은 파라미터들을 JavaObject로 매핑시키는 것이기 때문
	//(기본생성자+Setter)
	//(모든생성자)
	@RequestMapping("/ajaxGetTest")
	public String ajaxGetTest(Model model, @ModelAttribute MainFormDTO dto) {
		log.debug("dto : {}", dto);
		model.addAttribute("mainDTO", dto);
		return "thymeleaf/page/index :: #pageTest";
	}

	//ModelAttribute는 전달받은 파라미터들을 (Refelection)JavaObject로 매핑시키는 것이기 때문
	//(기본생성자+Setter)
	//(모든생성자)
	@GetMapping("/fetchGetTest")
	public String fetchGetTest(Model model, @ModelAttribute MainFormDTO dto) {
		log.debug("dto : {}", dto);
		model.addAttribute("mainDTO", dto);
		return "thymeleaf/page/index :: #pageTest";
	}

	//(Setter 불필요 + 기본생성자가 있어야 함)POST방식으로 Json의 형태로 넘겨온 데이터를 (MessageConverter가)객체로 바인딩
	@PostMapping("/fetchPostTest")
	public String fetchPostTest(Model model, @RequestBody MainDTO dto) {
		log.debug("dto : {}", dto);
		model.addAttribute("mainDTO", dto);
		return "thymeleaf/page/index :: #pageTest";
	}
}
