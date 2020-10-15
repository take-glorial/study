package kr.co.study.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Account {

	@NonNull
	private String id;

	@NonNull
	private String name;

	@NonNull
	private String password;

	@Setter(AccessLevel.NONE)
	private String role = "ROLE_USER";

}
