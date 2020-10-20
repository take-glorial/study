package kr.co.study.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.study.application.BoardService;
import kr.co.study.domain.Account;
import kr.co.study.web.dto.BoardDTO;
import kr.co.study.web.dto.BoardJsonDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;

	@GetMapping("list")
	public String list(Model model, HttpSession session) {

		log.debug("session:{}", session.getId());

		/*

		Map<String, Object> boardInfo = new HashMap<String, Object>();
		boardInfo.put("id", 1);
		boardInfo.put("title", "게시판테스트");
		boardInfo.put("regId", "admin");
		boardInfo.put("regDate", LocalDateTime.now());

		List<Map<String, Object>> boardList = Arrays.asList(boardInfo, boardInfo, boardInfo);

		*/

		List<BoardDTO> boardList = boardService.selectBoardList();

		model.addAttribute("boardList", boardList);
		log.debug("boardDtoList:{}", boardList);

		return "thymeleaf/page/board/list";
	}

	@GetMapping("write")
	public String write(@ModelAttribute(name = "board") BoardDTO boardDTO) {
		return "thymeleaf/page/board/write";
	}

	@PostMapping(value = "write")
	public String writeProc(@Valid @ModelAttribute(name = "board") BoardDTO boardDTO, BindingResult bindingResult, Authentication authentication) {
		log.debug("board write Proc : {}", boardDTO);

		if (bindingResult.hasErrors()) {
			return "thymeleaf/page/board/write";
		}

		Account account = (Account) authentication.getPrincipal();
		boardService.insertBoard(boardDTO.toBoard(account));

		return "redirect:list";
	}

	@GetMapping("{id}")
	public String board(@PathVariable(name = "id") String id, Model model, @ModelAttribute(name = "board") BoardDTO boardDTO) {
		log.debug("id:{}", id);

		model.addAttribute("board", BoardDTO.builder().id("1").title("제목").contents("내용\r\n어쩌고저쩌\r\n어쩌고저쩌\r\n어쩌고저쩌\r\n어쩌고저쩌").build());
		return "thymeleaf/page/board/view";
	}

	@PostMapping(value = "writeProcJson")
	public ResponseEntity writeProcJson(@Valid @RequestBody BoardJsonDTO boardDTO, BindingResult bindingResult) {
		log.debug("board write : {}", boardDTO);

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("hello");
		}

		return ResponseEntity.ok().body("hello");
	}

}
