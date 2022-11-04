package fst.spring.boot.redis.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import fst.spring.boot.redis.entity.Account;
import fst.spring.boot.redis.entity.repository.AccountRepository;

@Service
public class AccountService {
	public static final String HASH_KEY = "Account";

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	RedisTemplate<Object, Object> redisTemplate;

	public List<Object> findAll() {
		return redisTemplate.opsForHash().values(HASH_KEY);
	}

	public Account getById(long id) {
		return (Account) redisTemplate.opsForHash().get(HASH_KEY, id);
	}

	public Account save(Account account) {
		accountRepository.save(account);
		redisTemplate.opsForHash().put(HASH_KEY, account.getId(), account);
		return account;
	}

	public void delete(long id) {
		accountRepository.deleteById(id);
		redisTemplate.opsForHash().delete(HASH_KEY, id);
	}
}
