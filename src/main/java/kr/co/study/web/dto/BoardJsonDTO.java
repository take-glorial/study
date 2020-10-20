package kr.co.study.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BoardJsonDTO {

	private String id;
	private String title;

	@NotEmpty(message = "왜 안쓰냐?")
	private String contents;

	private String regId;
	private LocalDate regDate;

}
