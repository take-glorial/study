package kr.co.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.study.application.SignInService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	SignInService signInService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/webjars/**", "/**/*.html", "/**/*.ico");
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()

			.authorizeRequests()
				.antMatchers("write-down-view-names").permitAll()
				.anyRequest().authenticated()
			.and()

	        .formLogin()
		        .loginPage("/signin")
		        .loginProcessingUrl("/signinProc")
		        .defaultSuccessUrl("/")
		        .failureUrl("/signin?error")
	            .permitAll()
			.and()

			.logout()
				.logoutUrl("/signout")
				.permitAll()
				.invalidateHttpSession(true)
			.and();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(signInService).passwordEncoder(passwordEncoder());
	}
}
