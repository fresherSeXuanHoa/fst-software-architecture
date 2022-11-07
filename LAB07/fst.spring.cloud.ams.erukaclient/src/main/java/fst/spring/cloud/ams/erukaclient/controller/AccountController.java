package fst.spring.cloud.ams.erukaclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fst.spring.cloud.ams.erukaclient.entity.Account;
import fst.spring.cloud.ams.erukaclient.service.AccountService;

@RestController
public class AccountController {
	@Autowired
	AccountService accountService;

	@GetMapping("/accounts")
	public List<Account> findAll() {
		return accountService.findAll();
	}
}
