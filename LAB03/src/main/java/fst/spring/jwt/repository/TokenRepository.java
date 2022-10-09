package fst.spring.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fst.spring.jwt.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
	Token findByToken(String token);
}
