package fst.spring.boot.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fst.spring.boot.redis.entity.Account;
import fst.spring.boot.redis.entity.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;

	@GetMapping("/accounts")
	public List<Object> findAll() {
		return accountService.findAll();
	}

	@GetMapping("/account")
	public Account getById(@RequestParam(name = "id") long id) {
		return accountService.getById(id);
	}

	@PostMapping("/account")
	public Account save(@RequestBody Account account) {
		return accountService.save(account);
	}

	@DeleteMapping("/account")
	public void delete(@RequestParam(name = "id") long id) {
		accountService.delete(id);
	}
}
