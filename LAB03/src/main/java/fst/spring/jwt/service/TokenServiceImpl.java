package fst.spring.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fst.spring.jwt.entity.Token;
import fst.spring.jwt.repository.TokenRepository;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenRepository tokenRepository;

	public Token createToken(Token token) {
		return tokenRepository.saveAndFlush(token);
	}

	@Override
	public Token findByToken(String token) {
		return tokenRepository.findByToken(token);
	}
}
