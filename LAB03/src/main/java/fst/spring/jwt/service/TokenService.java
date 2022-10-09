package fst.spring.jwt.service;

import fst.spring.jwt.entity.Token;

public interface TokenService {
	Token createToken(Token token);

	Token findByToken(String token);
}
