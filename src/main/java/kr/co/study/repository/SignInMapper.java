package kr.co.study.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.study.domain.Account;

@Repository
@Mapper
public interface SignInMapper {

	Account selectUserById(String id);

}
