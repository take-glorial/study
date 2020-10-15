package kr.co.study.application;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.study.domain.Account;
import kr.co.study.repository.SignInMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignInService implements UserDetailsService {

	@Autowired
	PasswordEncoder passwordEncoder;

	private final SignInMapper signInMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Account account = signInMapper.selectUserById(username);
		if(account == null) {
			throw new UsernameNotFoundException("사용자 정보가 존재하지 않습니다.");
		}

		log.debug("User Info : {}", account);

		return new User(account.getId(), passwordEncoder.encode(account.getPassword()), Collections.singleton(new SimpleGrantedAuthority(account.getRole())));

	}

}
