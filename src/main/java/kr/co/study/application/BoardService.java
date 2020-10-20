package kr.co.study.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.study.domain.Board;
import kr.co.study.repository.BoardMapper;
import kr.co.study.web.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardMapper boardMapper;

	@Transactional(readOnly = true)
	public List<BoardDTO> selectBoardList() {
		return boardMapper.selectBoardList().stream()
					.map(board -> BoardDTO.builder()
									.id(board.getId()).title(board.getTitle())
									.contents(board.getContents()).regDate(board.getRegDate()).regId(board.getRegId())
									.build())
					.collect(Collectors.toList());
	}

	@Transactional(readOnly = false)
	public void insertBoard(Board board) {
		boardMapper.insertBoard(board);
	}
}
