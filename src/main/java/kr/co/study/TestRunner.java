package kr.co.study;

import javax.sql.DataSource;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import kr.co.study.repository.MainMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class TestRunner implements ApplicationRunner {

	private final DataSource ds;

	private final MainMapper mainMapper;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.debug("디버깅 시작");
		log.debug("schema:{}", ds.getConnection().getSchema());
		log.debug("count:{}", mainMapper.selectUserCount());
	}

}
