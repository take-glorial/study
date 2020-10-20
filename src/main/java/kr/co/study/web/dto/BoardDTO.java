package kr.co.study.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import kr.co.study.domain.Account;
import kr.co.study.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BoardDTO {

	private String id;

	@NotEmpty(message = "제목을 입력하세요.")
	@Size(max = 10, min = 0, message = "1~10자만 입력가능.")
	private String title;

	@NotEmpty(message = "내용을 입력하세요.")
	private String contents;

	private String regId;
	private LocalDate regDate;

	public Board toBoard() {
		return this.toBoard(null);
	}

	public Board toBoard(Account account) {
		return Board.builder()
				.id(this.id)
				.title(this.title)
				.contents(this.contents)
				.regId(account == null ? this.regId : account.getId())
				.regDate(this.regDate)
			.build();
	}

}
