package kr.co.study.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.study.domain.Board;
import kr.co.study.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardMapper boardMapper;

	@Transactional(readOnly = false)
	public List<Board> selectBoardList() {
		return boardMapper.selectBoardList();
	}

}
