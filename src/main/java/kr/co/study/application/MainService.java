package kr.co.study.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.study.repository.MainMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MainService {

	private final MainMapper mapper;

	@Transactional(readOnly = true)
	public Integer getUserCount() {
		return mapper.selectUserCount();
	}

}
