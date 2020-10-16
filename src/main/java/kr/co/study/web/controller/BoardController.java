package kr.co.study.web.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.study.application.BoardService;
import kr.co.study.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;

	@RequestMapping("list")
	public String board(Model model) {

		/*		Map<String, Object> boardInfo = new HashMap<String, Object>();
				boardInfo.put("id", 1);
				boardInfo.put("title", "게시판테스트");
				boardInfo.put("regId", "admin");
				boardInfo.put("regDate", LocalDateTime.now());

				List<Map<String, Object>> boardList = Arrays.asList(boardInfo, boardInfo, boardInfo);*/

		List<Board> boardList = boardService.selectBoardList();
		model.addAttribute("boardList", boardList);

		log.debug("boardList:{}", boardList);

		return "thymeleaf/page/board/list";
	}

}
