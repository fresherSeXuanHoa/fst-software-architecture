package fst.spring.cloud.ams.accountservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fst.spring.cloud.ams.accountservice.entity.Account;
import fst.spring.cloud.ams.accountservice.service.AccountService;

@RestController
public class AccountController {
	@Autowired
	AccountService accountService;

	@GetMapping("/accounts")
	public List<Account> findAll() {
		return accountService.findAll();
	}
}
